package bookapp.core;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookComparatorTest {
    List<Book> bookList;
    BookComparator comparator;
    Book rating = new Book("cBook","cAuthor");
    Book author = new Book("bBook","aAuthor");
    Book title = new Book("aBook","bAuthor");


    @BeforeEach
    void setUp(){
        bookList = Arrays.asList(rating,author,title);
        for(Book book : bookList){
            new BookReview(book, new User("user"), bookList.size()-bookList.indexOf(book));
        }
    }

    @Test
    void testSorting(){
        bookList.sort(new BookComparator(BookComparator.AUTHOR_NAME));
        assertEquals(Arrays.asList(author,title,rating), bookList);
        bookList.sort(new BookComparator(BookComparator.BOOK_TITLE));
        assertEquals(Arrays.asList(title,author,rating), bookList);
        bookList.sort(new BookComparator(BookComparator.RATING));
        assertEquals(Arrays.asList(rating,author,title), bookList);
    }

}
