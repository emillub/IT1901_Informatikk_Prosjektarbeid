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
    @PostMapping("/post/{bookName}/{review}")
    public ResponseEntity<String> postReview(@PathVariable String bookName, @RequestBody BookReview review) {
        List<Book> booklist = FileHandler.readBooksFromFile();
        for (Book book : booklist){
            if (book.getTitle().equals(bookName)){
                book.getReviews().add(review); 
                return ResponseEntity.ok("Review posted successfully."); // Return success message
            } 
        }
        return ResponseEntity.notFound().build(); // Book not found
    }

    // DELETE a review for a book
    @DeleteMapping("/delete/{bookName}/{reviewer}")
    public ResponseEntity<String> deleteReview(@PathVariable String bookName, @PathVariable String reviewer) {
        List<Book> booklist = FileHandler.readBooksFromFile();
        for (Book book : booklist){
            if (book.getTitle().equals(bookName)){
                for (BookReview rev : book.getReviews()){
                    if (rev.getReviewer().getName().equals((reviewer))){
                        book.getReviews().remove(rev);
                        return ResponseEntity.ok("Review deleted successfully."); // Return success message
                    }
                }
                // Break iteration, if the book has been found by name and reviewer is not found we don't need to iterate through the rest
            } 
        }
        return ResponseEntity.notFound().build(); 
    }

}
//Må ha mapping mellom back-end funksjoner og uri (portaddresse e.g. 8080/domene/new?).
//Første man bør gjøre; lage en funksjon som returnerer noe. Sjekke at man klarer å teste en funksjon fra UIet

