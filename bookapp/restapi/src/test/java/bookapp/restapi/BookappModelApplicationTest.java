package bookapp.restapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


import bookapp.core.Book;
import bookapp.core.BookReview;
import bookapp.core.User;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;


@WebMvcTest(BookappModelController.class)
public class BookappModelApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileHandlerService fileHandlerService; 

    private Book book;
    private List<Book> bookList;
    private User user;
    
    @BeforeEach
    private void setUp(){
        //Sample data
        book = new Book("Sample book", "Author");
        bookList = Arrays.asList(book);
        user = new User("Sample User");
        given(fileHandlerService.readBooksFromFile()).willReturn(bookList);
        //Setup mock behavior of FileHandlerService
    }
    
    @Test
    public void getBooksTest() throws Exception {
        // Perform GET request and verify outcome
        mockMvc.perform(get("/api/books/fetchList"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].title", Matchers.equalTo(book.getTitle())));
    }
    
    @Test
    public void postReviewTest() throws Exception {
        String jsonPayload = "{\"reviewer\":{\"name\":\"User\"},\"rating\":2}";
        //No need to mock 'updateBookInLibrary' if it returns void, just verify it's called later
        //Perform POST request and verify outcome
        mockMvc.perform(post("/api/books/post/"+book.getTitle().replace(" ", "%20"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isOk())
        .andExpect(content().string("Review sucessfully added"));
        
        //If review was added, cannot create another
        assertThrows(Exception.class, ()-> mockMvc.perform(post("/api/books/post/"+book.getTitle().replace(" ", "%20"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload)));
        
        //Test book not found
        mockMvc.perform(post("/api/books/post/none")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isNotFound());
    }
    
    @Test
    public void testDeleteReview() throws Exception{
        String bookString = book.getTitle().replace(" ", "%20");
        String userString = user.getName().replace(" ", "%20");
        
        //Test review not found
        mockMvc.perform(delete("/api/books/delete/{bookName}/{reviewer}",bookString,userString))
        .andExpect(status().isNotFound());
        //Test book not found
        mockMvc.perform(delete("/api/books/delete/{bookName}/{reviewer}","wrongbook",userString))
        .andExpect(status().isNotFound());
        //Test review found
        new BookReview(book,user,2);
        mockMvc.perform(delete("/api/books/delete/{bookName}/{reviewer}",bookString,userString))
        .andExpect(status().isOk());
    }
    
    @Test
    public void testUpdateLibrary() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(bookList);

        mockMvc.perform(put("/api/books/updatelibrary")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isOk());
    }
}
