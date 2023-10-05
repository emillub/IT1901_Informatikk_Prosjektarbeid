package bookapp.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bookapp.core.Book;
import bookapp.core.User;
import bookapp.persistence.FileHandler;

public class FileHandlerTest {
    FileHandler fh = new FileHandler();
    Book book1;
    User user1;

    @BeforeEach
    private void setUp(){
        book1 = new Book("book 1", "author 1");
        user1 = new User("user1");
        user1.writeReview(book1, 5);
    }

    @Test
    private void testWriteBookToFile(){
        fh.writeBookToFile(book1);
    }

}
