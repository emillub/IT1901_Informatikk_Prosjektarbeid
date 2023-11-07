package bookapp.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import bookapp.core.Book;
import bookapp.core.BookReview;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookappModelController{

    //GET for the entire list of entities in the JSON file
    @GetMapping("/fetchList")
    public List<Book> getBook(){
            List<Book> booklist = FileHandler.readBooksFromFile();
            return booklist;
    }

    // POST a new review for a book
    //Hvordan kan jeg sende et bookreviewobjekt gjennom en HTTP request?
    @PostMapping("/post/{bookName}")
    public ResponseEntity<String> postReview(@PathVariable String bookName, @RequestBody BookReview review) {
        List<Book> booklist = FileHandler.readBooksFromFile();
        String updatedName = bookName.replace("%20", " ");

        for (Book book : booklist){
            if(book.getTitle().equals(updatedName)){
                book.addReview(review);
                FileHandler.updateBookInLibrary(book);
                return ResponseEntity.ok("Review sucessfully added");
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE a review for a book
    @DeleteMapping("/delete/{bookName}/{reviewer}")
    public ResponseEntity<String> deleteReview(@PathVariable("bookName") String bookName, @PathVariable("reviewer") String reviewer) {
        List<Book> booklist = FileHandler.readBooksFromFile();
        Optional<Book> wantedBook = booklist.stream().filter(b -> b.getTitle().equals(bookName)).findFirst();
        if (wantedBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Book book = wantedBook.get();
        BookReview bookReview = book.getReviews().stream().filter(r -> r.getReviewer().getName().equals(reviewer)).findFirst().get();
        book.deleteReview(bookReview);
        FileHandler.updateBookInLibrary(book);
        return ResponseEntity.ok("review by " + reviewer + " deleted");
        }
    
    @PutMapping("/updatelibrary")
    public ResponseEntity<String> updateLibrary(@RequestBody List<Book> booklist){
        //Assuming I can use booklist as a list of book objects
        for (Book book:booklist){
                FileHandler.updateBookInLibrary(book);
            }
        return ResponseEntity.ok("Sucessfully updated library");
    }

    //Add checks and functionality later
    }


