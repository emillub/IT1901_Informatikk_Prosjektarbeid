package bookapp.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;
    private Book book1;

    @BeforeEach
    void setUp() {
        user = new User("John");
        book1 = new Book("Sample Title 1", "Sample Author 1");
    }

    @Test
    void testConstructor() {
        assertNotNull(user);
        assertThrows(IllegalArgumentException.class, () -> new User(""));
        assertEquals("John", user.getName());
    }

    @Test
    void testWriteReview() {
        user.writeReview(book1, 5);

        // Assuming BookReview's constructor also adds the review to the book's reviews.
        assertEquals(1, book1.getReviews().size());
        assertEquals(user, book1.getReviews().get(0).getReviewer());
    }

    @Test
    void testWriteMultipleReviewsForSameBook() {
        user.writeReview(book1, 5);
        
        // Trying to write another review for the same book by the same user should throw an exception.
        // Assuming this based on previous test logic provided.
        assertThrows(IllegalArgumentException.class, () -> user.writeReview(book1, 4));
    }

    @Test
    void testToString() {
        assertEquals("John", user.toString());
    }
    
    @Test 
    void testDeleteReview(){
        user.writeReview(book1, 4);
        Book book2 = new Book("book", "author");
        user.writeReview(book2, 2);
        user.deleteReview(book1.getReviews().get(0));
        assertTrue(book1.getReviews().isEmpty());
    }
    
    // Add other tests as needed...

}
