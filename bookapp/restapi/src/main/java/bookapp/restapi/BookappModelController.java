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
    //Hvordan kan jeg sende et bookreviewobjekt gjennom en HTTP request?
    @GetMapping("/post/{bookName}/{review}")
    public ResponseEntity<String> postReview(@PathVariable String bookName, @RequestBody BookReview review) {
        List<Book> booklist = FileHandler.readBooksFromFile();
        for (Book book : booklist){
            if(book.getTitle().equals(bookName)){
                book.addReview(review);
                return ResponseEntity.ok("Review sucessfully added");
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE a review for a book
    //Hvorfor fungerer dette med @GetMapping, men ikke med @DeleteMapping?? 
    @DeleteMapping("/delete/{bookName}/{reviewName}")
    public ResponseEntity<String> deleteReview(@PathVariable("bookName") String bookName,@PathVariable("reviewName") String reviewName ) {
        List<Book> booklist = FileHandler.readBooksFromFile();
        for (Book book : booklist){
            if (book.getTitle().equals(bookName)){
                for (BookReview reviewer : book.getReviews()){
                    if(reviewer.getReviewer().getName().equals(reviewName)){
                        book.deleteReview(reviewer);
                        FileHandler.updateBookInLibrary(book);
                        return ResponseEntity.ok("Bookreview sucessfully deleted.");
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
        }

    //Nødvendig med PutMapping????    
    // @PutMapping("/updatelibrary")
    // public void updateLibrary(){

    // }
    }


