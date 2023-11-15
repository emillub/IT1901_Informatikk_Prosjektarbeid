package bookapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        book = new Book("Test Book", "John Doe");
        user1 = new User("User1");
        user2 = new User("User2");
    }

    @Test
    void testAddReview() {
        new BookReview(book, user1, 4);
        new BookReview(book, user2, 5);

        assertEquals(2, book.getReviews().size());
    }

    @Test
    void testAverageRating() {
        new BookReview(book, user1, 4);
        new BookReview(book, user2, 4);

        float expected = 4.0f;
        assertEquals(expected, book.getAverageRating());
    }

    @Test
    void testAddMultipleReviewsFromSameUser() {
        new BookReview(book, user1, 4);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BookReview(book, user1, 5);
        });

        String expectedMessage = "Users cannot write more than one review per book";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testValidRating() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BookReview(book, user1, 6);
        });

        String expectedMessage = "Rating needs to be between 1 and 5";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testDeleteReview(){
        new BookReview(book, user1, 4);
        BookReview rev2 = new BookReview(book, user2, 3);
        
        assertEquals(book.getReviews().size(),2);
        book.deleteReview(rev2);
        assertTrue(book.getReviews().get(0).getReviewer().equals(user1));
        assertThrows(IllegalArgumentException.class, () -> book.deleteReview(rev2));
        assertThrows(IllegalArgumentException.class, () -> rev2.deleteReview());
    } 
}

