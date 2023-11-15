package bookapp.restapi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import bookapp.core.Book;
import bookapp.core.BookReview;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/books")
public class BookappModelController{

    @Autowired
    FileHandlerService fhs;

    //GET for the entire list of entities in the JSON file
    @GetMapping("/fetchList")
    public List<Book> getBook(){
            List<Book> bookList = FileHandler.readBooksFromFile();
            return bookList;
    }

    // POST a new review for a book
    @PostMapping("/post/{bookName}")
    public ResponseEntity<String> postReview(@PathVariable String bookName, @RequestBody BookReview review) {
        List<Book> bookList = FileHandler.readBooksFromFile();
        String updatedName = bookName.replace("%20", " ");

        for (Book book : bookList){
            if(book.getTitle().equals(updatedName)){
                book.addReview(review);
                fhs.updateBookInLibrary(book);
                return ResponseEntity.ok("Review sucessfully added");
            }
        }
        return ResponseEntity.notFound().build();
    }


    // DELETE a review for a book
    @DeleteMapping("/delete/{bookName}/{reviewer}")
    public ResponseEntity<String> deleteReview(@PathVariable("bookName") String bookName, @PathVariable("reviewer") String reviewer) {
        List<Book> bookList = FileHandler.readBooksFromFile();
        Predicate<Book> matchingName = b -> b.getTitle().equals(bookName);
        
        Optional<Book> wantedBook = bookList.stream()
            .filter(matchingName)
            .findFirst();
        
        if (wantedBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Book book = wantedBook.get();
        
        Predicate<BookReview> matchingReviewer = r -> r.getReviewer().getName().equals(userString);
        Optional<BookReview> optionalReview = book.getReviews().stream().filter(matchingReviewer).findFirst();
        // System.out.println("Looking for review: " +booklist.get(0).getReviews().toString());

        if (optionalReview.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        BookReview bookReview = optionalReview.get();
        book.deleteReview(bookReview);
        fhs.updateBookInLibrary(book);
        return ResponseEntity.ok("review by " + reviewer + " deleted");
        }
    
    @PutMapping("/updatelibrary")
    public ResponseEntity<String> updateLibrary(@RequestBody List<Book> booklist){
        //Assuming I can use booklist as a list of book objects
        System.out.println(booklist);
        for (Book book:booklist){
                fhs.updateBookInLibrary(book);
            }
        return ResponseEntity.ok("Sucessfully updated library");
    }
    }


