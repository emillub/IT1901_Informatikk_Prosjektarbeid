package bookapp.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookReviewTest {
Book book1;
    Book book2;
    User user1;
    User user2;

    @BeforeEach
    void setUp(){
        book1 = new Book("Book 1", "Authon 1");
        book2 = new Book("Book 1", "Authon 1");
        user1 = new User("User 1");
        user2 = new User("User 2");
    }

    @Test
    void testConstructor(){
        BookReview review = new BookReview(book1, user1, 1);
        assertNotNull(book1.getReviews().get(0));
        assertEquals(book1.getReviews().get(0).getReviewer(),review.getReviewer());
    }

    @Test
    void testDuplicateReview(){
        BookReview review = new BookReview(book1, user1, 1);
        assertThrows(IllegalArgumentException.class, () -> new BookReview(book1, user1,2));
    }

    @Test
    void testRatingOutOfRange(){
        assertThrows(IllegalArgumentException.class, () -> new BookReview(book1, user1,0));
    }

    @Test
    void testDeleteReview(){
        
        BookReview review = new BookReview(book1, user1, 5);
        assertEquals(book1.getReviews().size(), 1);
        review.deleteReview();
        assertEquals(book1.getReviews().size(), 0);
    }

}
