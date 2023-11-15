package bookapp.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
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

    // Test for GET request
    @Test
    public void getBooksTest() throws Exception {
        // Create sample data
        Book book = new Book("Sample Book", "Sample Author");
        List<Book> bookList = Arrays.asList(book);

        // Setup mock behavior of FileHandlerService
        given(fileHandlerService.readBooksFromFile()).willReturn(bookList);

        // Perform GET request and verify outcome
        mockMvc.perform(get("/api/books/fetchList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(book.getTitle()));
    }

    // Test for POST request
    @Test
    public void postReviewTest() throws Exception {
        // Sample data
        String sampleTitle = "Sample Book";
        User sampleUser = new User("Sample User");
        String jsonPayload = "{\"book\":{\"title\":\"" + sampleTitle + "\",\"author\":\"Sample Author\"},\"reviewer\":{\"name\":\"Sample User\"},\"rating\":5}";
    
        List<Book> mockBookList = Collections.singletonList(new Book(sampleTitle, sampleUser.getName()));

        // Setup mock behavior
        given(fileHandlerService.readBooksFromFile()).willReturn(mockBookList);
        // No need to mock 'updateBookInLibrary' if it returns void, just verify it's called later
    
        // Perform POST request and verify outcome
        mockMvc.perform(post("/api/books/post/{bookName}", sampleTitle)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(content().string("Review successfully added"));
    
        // Verify interactions with mock
        verify(fileHandlerService).updateBookInLibrary(any(Book.class));
    }

}
