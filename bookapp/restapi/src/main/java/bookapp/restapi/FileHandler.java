package bookapp.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import bookapp.core.Book;



public class FileHandler {

    private final static String FILE_NAME = "Library.json";
    public static String DIR_PATH = "bookapp/restapi/src/main/resources/bookapp/restapi";
    public static final Book[] LIBRARY = 
    {new Book("Til musikken", "author"), 
    new Book("Maskiner som tenker", "Inga Stromke"),
    new Book("To kill a mockingbird", "Ukjent")};

    //Checks if Library has new books since last time
    private static void CheckIfMoreBooksInLibrary(List<Book> oldBooks){
        if (oldBooks.size() < LIBRARY.length){
            List<String> bookTitles = oldBooks.stream().map(b -> b.getTitle()).collect(Collectors.toList());
            List<Book> newBooksInLibrary = Arrays.asList(LIBRARY).stream()
                .parallel().filter(b -> !bookTitles.contains(b.getTitle())).collect(Collectors.toList());
            oldBooks.addAll(newBooksInLibrary);
        }
    }

    //Writes books to JSON
    private static void writeBooksToFile(List<Book> books){
        try {
            CheckIfMoreBooksInLibrary(books);
            ObjectMapper mapper = new ObjectMapper();
            String filePath = getDefaultFilePath();
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(filePath).toFile(), books);
        } catch (IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
    }


    //Gets book from list of books. Meant to be used on books from file, but can be used on all lists of books
    public static Book getBookFromLibrary(Book book, List<Book> booksInLibrary){
        if(booksInLibrary == null) booksInLibrary = readBooksFromFile();
        //Gets book by checking if matching author and name
        Optional<Book> bookInLibraryOptional = booksInLibrary.stream()
                .filter(b -> b.getTitle().equals(book.getTitle()) && b.getAuthor().equals(book.getAuthor()))
                .findFirst(); 
        
        if (!bookInLibraryOptional.isPresent()) throw new NoSuchElementException("Book not in library");
        
        Book bookInLibrary = bookInLibraryOptional.get();
        return bookInLibrary;
    }

    //Updates book in list of books read from file and rewrites all the books to file
    public static void updateBookInLibrary(Book book){ 
        List<Book> books = readBooksFromFile();
        //Replaces book object in list of books and writes list of books to file
        int index = books.indexOf(getBookFromLibrary(book, books));
        books.remove(index);
        books.add(index, book);
        writeBooksToFile(books);
    }

    


    //Reads books and reviews related to them from file and creates instances of them 
    public static List<Book> readBooksFromFile() {
        try {
            if(fileCreated()){
                System.out.println("Library file not found. Creating...");
                writeBooksToFile(new ArrayList<Book>());
            }

            ObjectMapper mapper = new ObjectMapper();
            String filePath = getDefaultFilePath();
            File file = Path.of(filePath).toFile();
            List<Book> books = mapper.readValue(file, new TypeReference<List<Book>>() {}); 

            return books;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    //Creates file if it doesnt exist
    private static Boolean fileCreated(){
        File file = new File(getDefaultFilePath());
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getDefaultFilePath(){
        try {
            //Checks what directory is the app is running from and finds the correct path from there
            Path path = Path.of(new File("").getAbsolutePath());
            String pathString = path.toString();
            if(pathString.endsWith("gr2349")) {
                pathString = pathString + "/bookapp/restapi/src/main/resources/bookapp/restapi";
            }
            else if (path.toString().endsWith("fxui") || path.toString().endsWith("restapi")){   
                //Removes string until at /bookapp
                while (!pathString.endsWith("bookapp")){ 
                    pathString = pathString.substring(0, pathString.length()-1);
                }
                pathString += "/restapi/src/main/resources/bookapp/restapi";
            }

            Files.createDirectories(Paths.get(pathString)); //Creates the directory if it doesn't exist
            String filePath = pathString + "/" + FILE_NAME;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }   
}
