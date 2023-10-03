package bookapp.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bookapp.core.Book;
import bookapp.core.BookReview;



public class FileHandler {

    private final static String FILE_EXTENSION = ".json";
    public static String DIR_PATH = "bookapp\\persistence\\src\\main\\resources\\bookapp\\persistence\\book.json";

    public void writeBookToFile(Book book) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Writing the book object to file as JSON
            mapper.writeValue(new File(DIR_PATH), book);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //Reads books and reviews related to them from file and creates instances of them 
    public List<Book> readBookFromFile(String filepath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Reading a list of books from the JSON file
            List<Book> books = mapper.readValue(new File(filepath), new TypeReference<List<Book>>() {});
    
            // Iterate over each book
            for (Book book : books) {
                List<BookReview> reviews = book.getReviews();
                if (reviews == null) {
                    reviews = new ArrayList<>();
                    book.setReviews(reviews);
                } else {
                    for (BookReview review : reviews) {
                        book.addReview(review);
                    }
                }
                book.setAverageRating();
            }
            return books;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    

    public static void main(String[] args) {
        Book book = new Book("Til musikken", "author");
        FileHandler fh = new FileHandler();
        fh.writeBookToFile(book);
        fh.readBookFromFile(DIR_PATH);
    }

    
}
