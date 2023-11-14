package bookapp.restapi;

import org.springframework.stereotype.Service;
import bookapp.core.Book;
import bookapp.core.BookReview;

import java.util.List;

@Service
public class FileHandlerService {

    // Wraps the readBooksFromFile static method
    public List<Book> readBooksFromFile() {
        return FileHandler.readBooksFromFile();
    }

    // Wraps the updateBookInLibrary static method
    public void updateBookInLibrary(Book book) {
        FileHandler.updateBookInLibrary(book);
    }

    // Wraps the getBookFromLibrary static method
    public Book getBookFromLibrary(Book book, List<Book> booksInLibrary) {
        return FileHandler.getBookFromLibrary(book, booksInLibrary);
    }

    // Wraps the removeReview static method
    public void removeReview(Book book, BookReview review) {
        FileHandler.removeReview(book, review);
    }
}
