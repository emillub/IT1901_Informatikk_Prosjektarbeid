package bookapp.fxui;


import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import bookapp.core.Book;
import bookapp.core.BookReview;
import bookapp.core.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RemoteBookappModelAccessTest {


    // Helper method to create a mock HttpResponse<String>
    private static HttpResponse<String> createMockResponse() {
        @SuppressWarnings("unchecked")  // Suppress the unchecked warning
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        return response;
    }

    @Test
    public void testFetchLibraryErrorResponse() throws IOException, InterruptedException {

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = createMockResponse();

        when(mockResponse.statusCode()).thenReturn(400);  // Simulate a client error response
        when(mockResponse.body()).thenReturn("Error response body");

        when(mockClient.send(any(HttpRequest.class), Mockito.<HttpResponse.BodyHandler<String>>any()))
            .thenReturn(mockResponse);

        RemoteBookappModelAccess access = new RemoteBookappModelAccess(mockClient);

        assertThrows(RuntimeException.class, () -> access.fetchlibrary());
    }

    @Test
    public void testDeleteReviewErrorResponse() throws IOException, InterruptedException {

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = createMockResponse();

        when(mockResponse.statusCode()).thenReturn(400);  // Simulate a client error response
        when(mockResponse.body()).thenReturn("Error response body");

        when(mockClient.send(any(HttpRequest.class), Mockito.<HttpResponse.BodyHandler<String>>any()))
            .thenReturn(mockResponse);

        RemoteBookappModelAccess access = new RemoteBookappModelAccess(mockClient);

        //Response is already configured, only to make method to function
        String book = "Sample Book";
        BookReview review = new BookReview( new Book("Example Book Title", "Example Author"), new User("John Dough"), 5); 

        assertThrows(RuntimeException.class, () -> access.deleteReview(book, review));
    }

    @Test
    public void testAddReviewErrorResponse() throws IOException, InterruptedException {

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = createMockResponse();

        when(mockResponse.statusCode()).thenReturn(400);  
        when(mockResponse.body()).thenReturn("Error response body");

        when(mockClient.send(any(HttpRequest.class), Mockito.<HttpResponse.BodyHandler<String>>any()))
            .thenReturn(mockResponse);

        RemoteBookappModelAccess access = new RemoteBookappModelAccess(mockClient);

        //Response is already configured, only to make method to function
        String book = "Sample Book";
        BookReview review = new BookReview( new Book("Example Book Title", "Example Author"), new User("John Dough"), 5); 

        assertThrows(RuntimeException.class, () -> access.addReview(book, review));
    }

    @Test
    public void testUpdateErrorResponse() throws IOException, InterruptedException {

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = createMockResponse();

        when(mockResponse.statusCode()).thenReturn(400);  
        when(mockResponse.body()).thenReturn("Error response body");

        when(mockClient.send(any(HttpRequest.class), Mockito.<HttpResponse.BodyHandler<String>>any()))
            .thenReturn(mockResponse);

        RemoteBookappModelAccess access = new RemoteBookappModelAccess(mockClient);

        //Response is already configured, only to make method to function
        List<Book> bookList = List.of(new Book("Book Title 1", "Author 1"), new Book("Book Title 2", "Author 2"));

        assertThrows(RuntimeException.class, () -> access.update(bookList));
    }

}
