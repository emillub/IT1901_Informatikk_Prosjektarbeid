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
    public static String DIR_PATH = "bookapp/persistence/src/main/resources/bookapp/persistance/book.json";
    private List<Book> bookList = new ArrayList<>();

    public void writeBookToFile(Book book) {
        bookList.add(book);
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Writing the book object to file as JSON
            mapper.writeValue(new File(DIR_PATH), bookList);
        } catch (Exception ex) {
            System.out.println("Error writing book: " +book.getTitle());
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
        Book book1 = new Book("To kill a mockingbird", "Ukjent");
        Book book2 = new Book("Maskiner som tenker", "Inga Stromke");

        FileHandler fh = new FileHandler();
        fh.writeBookToFile(book);
        fh.writeBookToFile(book1);
        fh.writeBookToFile(book2);
        fh.readBookFromFile(DIR_PATH);
    }



    
}
