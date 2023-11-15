package bookapp.restapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
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
    private FileHandlerService fileHandlerService; // Mock the FileHandlerService

    private static ObjectMapper mapper = new ObjectMapper();

    // Test for GET request
    @Test
    public void getBooksTest() throws Exception {
        // Create sample data
        Book book = new Book("Sample Book", "Sample Author");
        List<Book> bookList = Arrays.asList(book);

        // // Setup mock behavior of FileHandlerService
        given(fileHandlerService.readBooksFromFile()).willReturn(bookList);

        // Perform GET request and verify outcome
        mockMvc.perform(get("/api/books/fetchList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo(book.getTitle())));
    }

    // Test for POST request
    @Test
    public void postReviewTest() throws Exception {
        // // Sample data
        Book book = new Book("Sample book", "Author");
        List<Book> bookList = Arrays.asList(book);
        // // Setup mock behavior of FileHandlerService
        given(fileHandlerService.readBooksFromFile()).willReturn(bookList);
        String jsonPayload = "{\"reviewer\":{\"name\":\"User\"},\"rating\":2}";
        // // No need to mock 'updateBookInLibrary' if it returns void, just verify it's called later
        
        // // Perform POST request and verify outcome
        mockMvc.perform(post("/api/books/post/"+book.getTitle().replace(" ", "%20"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(content().string("Review sucessfully added"));
        
        //If review was added, cannot create another
        assertThrows(IllegalArgumentException.class, ()-> new BookReview(book, new User("User"), 2));
        
        //Test book not found
        mockMvc.perform(post("/api/books/post/none")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonPayload))
            .andExpect(status().isNotFound());
    }

}
