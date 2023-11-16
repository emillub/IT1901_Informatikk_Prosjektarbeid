package bookapp.restapi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import bookapp.core.Book;
import bookapp.core.BookReview;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/books")
public class ApiController{

    @Autowired
    FileHandlerService fhs;
    protected final static String GET_ADRESS = "/fetchList";
    protected final static String POST_ADRESS = "/post/{bookName}";
    protected final static String DELETE_ADRESS = "/delete/{bookName}/{reviewer}";
    protected final static String PUT_ADRESS = "/updatelibrary";

    protected final static String POST_OK_STATUS = "Review successfully added";
    protected final static String DELETE_OK_STATUS = "Review deleted";
    protected final static String PUT_OK_STATUS = "Successfully updated library";
    
    //GET for the entire list of entities in the JSON file
    @GetMapping(GET_ADRESS)
    public List<Book> getLibrary(){
            List<Book> bookList = fhs.readBooksFromFile();
            return bookList;
    }

    // POST a new review for a book
    @PostMapping(POST_ADRESS)
    public ResponseEntity<String> postReview(@PathVariable String bookName, @RequestBody BookReview review) {
        List<Book> bookList = fhs.readBooksFromFile();
        String updatedName = bookName.replace("%20", " ");

        for (Book book : bookList){
            if(book.getTitle().equals(updatedName)){
                book.addReview(review);
                fhs.updateBookInLibrary(book);
                return ResponseEntity.ok(POST_OK_STATUS);
            }
        }
        return ResponseEntity.notFound().build();
    }


    // DELETE a review for a book
    @DeleteMapping(DELETE_ADRESS)
    public ResponseEntity<String> deleteReview(@PathVariable("bookName") String bookName, @PathVariable("reviewer") String reviewer) {
        String userString = reviewer.replace("%20", " ");
        String bookString = bookName.replace("%20", " ");

        List<Book> bookList = fhs.readBooksFromFile();
        
        Predicate<Book> matchingName = b -> b.getTitle().equals(bookString);
        Optional<Book> wantedBook = bookList.stream()
            .filter(matchingName)
            .findFirst();
        
        if (wantedBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Book book = wantedBook.get();
        
        Predicate<BookReview> matchingReviewer = r -> r.getReviewer().getName().equals(userString);
        Optional<BookReview> optionalReview = book.getReviews().stream().filter(matchingReviewer).findFirst();

        if (optionalReview.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        BookReview bookReview = optionalReview.get();
        book.deleteReview(bookReview);
        fhs.updateBookInLibrary(book);
        return ResponseEntity.ok(DELETE_OK_STATUS);
        }
    
    @PutMapping(PUT_ADRESS)
    public ResponseEntity<String> updateLibrary(@RequestBody List<Book> bookList){
        for (Book book:bookList){
                fhs.updateBookInLibrary(book);
            }
        return ResponseEntity.ok(PUT_OK_STATUS);
    }
    }


