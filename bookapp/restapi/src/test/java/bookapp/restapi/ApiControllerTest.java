package bookapp.restapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import bookapp.core.Book;
import bookapp.core.BookReview;
import bookapp.core.User;
import java.util.Arrays;
import java.util.List;


@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileHandlerService fileHandlerService; 

    private final String ADRESS_BASE = "/api/books";
    private final String GET_ADRESS = ADRESS_BASE+ApiController.GET_ADRESS;;
    private final String POST_ADRESS = ADRESS_BASE+ApiController.POST_ADRESS;;
    private final String DELETE_ADRESS = ADRESS_BASE+ApiController.DELETE_ADRESS;
    private final String PUT_ADRESS = ADRESS_BASE+ApiController.PUT_ADRESS;

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
    }
    
    @Test
    public void getLibraryTest() throws Exception {
        mockMvc.perform(get(GET_ADRESS))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].title", Matchers.equalTo(book.getTitle())));
    }
    
    @Test
    public void postReviewTest() throws Exception {
        String bookString = book.getTitle().replace(" ", "%20");
        String jsonPayload = "{\"reviewer\":{\"name\":\"User\"},\"rating\":2}";
        
        // // Perform POST request and verify outcome
        mockMvc.perform(post(POST_ADRESS,bookString)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isOk())
        .andExpect(content().string(ApiController.POST_OK_STATUS));
        
        //If review was added, cannot create another
        assertThrows(Exception.class, ()-> mockMvc.perform(post(POST_ADRESS,bookString)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload)));
        
        //Test book not found
        mockMvc.perform(post(POST_ADRESS,"wrongbook")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isNotFound());
    }
    
    @Test
    public void testDeleteReview() throws Exception{
        String bookString = book.getTitle().replace(" ", "%20");
        String userString = user.getName().replace(" ", "%20");
        
        //Test review not found
        mockMvc.perform(delete(DELETE_ADRESS,bookString,userString))
        .andExpect(status().isNotFound());
        //Test book not found
        mockMvc.perform(delete(DELETE_ADRESS,"wrongbook",userString))
        .andExpect(status().isNotFound());
        //Test review found
        new BookReview(book,user,2);
        mockMvc.perform(delete(DELETE_ADRESS,bookString,userString))
        .andExpect(status().isOk());
    }
    
    @Test
    public void testUpdateLibrary() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(bookList);

        mockMvc.perform(put(PUT_ADRESS)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isOk());
    }
}
