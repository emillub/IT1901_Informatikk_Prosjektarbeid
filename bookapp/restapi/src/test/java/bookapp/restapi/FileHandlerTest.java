package bookapp.restapi;

import org.junit.jupiter.api.Test;

import bookapp.core.Book;
import bookapp.core.BookReview;
import bookapp.core.User;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class FileHandlerTest {


    @Test void testGetFilePath(){
        String expectedEndOFPath = "bookapp/restapi/Library.json";
        assertTrue(FileHandler.getDefaultFilePath().endsWith(expectedEndOFPath));
    }

    private void deleteLibraryIfExists(){
        try {
            Files.deleteIfExists(Paths.get(FileHandler.getDefaultFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test void testCreateLibrary(){
        deleteLibraryIfExists();
        assertFalse(Files.exists(Paths.get(FileHandler.getDefaultFilePath())));
        FileHandler.readBooksFromFile();
        assertTrue(Files.exists(Paths.get(FileHandler.getDefaultFilePath())));
    }

    @Test void readFromBooksFromFile(){
        deleteLibraryIfExists();
        List<Book> books = FileHandler.readBooksFromFile();
        assertTrue(books.toString().equals(Arrays.asList(FileHandler.LIBRARY).toString()));
    }

    @Test void testGetBookFromFile(){
        Book bookInLibrary = FileHandler.LIBRARY[0];
        Book bookNotInLibrary = new Book("book", "Author");
        assertThrows(NoSuchElementException.class,()->FileHandler.updateBookInLibrary(bookNotInLibrary));
        FileHandler.getBookFromLibrary(bookInLibrary,null);
    }

    @Test void testUpdateBook(){
        deleteLibraryIfExists();
        List<Book> books = FileHandler.readBooksFromFile();
        User user1 = new User("User1");
        Book updatedBook = books.get(0);
        user1.writeReview(updatedBook, BookReview.RATING_RANGE[2]);
        assertFalse(updatedBook.toString().equals(FileHandler.getBookFromLibrary(updatedBook,null).toString()));
        
        FileHandler.updateBookInLibrary(updatedBook);
        assertTrue(updatedBook.toString().equals(FileHandler.getBookFromLibrary(updatedBook,null).toString()));
        
        updatedBook.deleteReview(updatedBook.getReviews().get(0));
        FileHandler.updateBookInLibrary(updatedBook);
        assertTrue(updatedBook.getReviews().size() == 0);
    }

    
}


