package bookapp.restapi;

import org.springframework.stereotype.Service;
import bookapp.core.Book;
import bookapp.core.BookReview;

import java.util.List;

// Wraps the static methods in FileHandler: readBooksFromFile, updateBookInLibrary and getBookFromLibrary
@Service
public class FileHandlerService {

    public List<Book> readBooksFromFile() {
        return FileHandler.readBooksFromFile();
    }

    public void updateBookInLibrary(Book book) {
        FileHandler.updateBookInLibrary(book);
    }

    public Book getBookFromLibrary(Book book, List<Book> booksInLibrary) {
        return FileHandler.getBookFromLibrary(book, booksInLibrary);
    }
}
