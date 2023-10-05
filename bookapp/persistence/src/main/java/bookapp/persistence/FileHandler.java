package bookapp.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bookapp.core.Book;
import bookapp.core.BookReview;
import bookapp.core.User;



public class FileHandler {

    private final static String FILE_NAME = "Library.json";
    public static String DIR_PATH = "bookapp/persistence/src/main/resources/bookapp/persistance";
    public static final Book[] LIBRARY = 
    {new Book("Til musikken", "author"), 
    new Book("Maskiner som tenker", "Inga Stromke"),
    new Book("To kill a mockingbird", "Ukjent")};

    private static void createLibrary(){
        writeBooksToFile(Arrays.asList(LIBRARY));
    }

    private static void writeBooksToFile(List<Book> books){
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Writing the book object to file as JSON
            String filePath = getDefaultFilePath();
            mapper.writeValue(Paths.get(filePath).toFile(), books);
        } catch (Exception ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
    }

    public static void updateBookInLibrary(Book book){
        List<Book> books = readBooksFromFile();
        Book bookInLibrary = books.stream().filter(b -> b.getTitle().equals(book.getTitle())).findFirst().get();
        int index = books.indexOf(bookInLibrary);
        books.remove(index);
        books.add(index, book);
        writeBooksToFile(books);

    }

    


    //Reads books and reviews related to them from file and creates instances of them 
    public static List<Book> readBooksFromFile() {
        try {
            if(fileCreated()){
                System.out.println("Fil eksisterer ikke. Oppretter nå");
                createLibrary();
            }
            ObjectMapper mapper = new ObjectMapper();
            // Reading a list of books from the JSON file
            String filePath = getDefaultFilePath();
            File file = Path.of(filePath).toFile();
            System.out.println("Leser fra " + filePath);
            //System.out.println(file);
            //System.out.println(FileHandler.class.getResource(""));
            List<Book> books = mapper.readValue(file, new TypeReference<List<Book>>() {});
            return books;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Boolean fileCreated(){
        File file = new File(getDefaultFilePath());
        try {
            return file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    protected static String getDefaultFilePath(){
        try {
            Path path = Path.of(new File("").getAbsolutePath());
            Files.createDirectories(path);
            String filePath = path + "/" + FILE_NAME;
            System.out.println(System.getProperty("user.dir"));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }   
    

    public static void main(String[] args) {
        User user1 = new User("user1");
        System.out.println(FileHandler.readBooksFromFile());
    }



    
}
