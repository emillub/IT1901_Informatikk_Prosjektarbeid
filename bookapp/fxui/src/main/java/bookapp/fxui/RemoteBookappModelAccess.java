package bookapp.fxui;

import java.util.List;
import bookapp.core.Book;


//This class handles communication between the UI and the HTTP methods defined in BookappModelController
public class RemoteBookappModelAccess{
    
    private static final String APPLICATION_JSON = "application/json";  

    private static final String ACCEPT_HEADER = "Accept";  


    public static List<Book> loadLibrary(){
    //Need this to return either a list of books or raw jsonobjects dependent on how AppController works
        return null;
    }

    public static void deleteReview(String reviewName){
    //Need this to remove a review in the "database" (in our case the json file)
    }

    public static void addReview(String reviewName){
    //Need this function to add a review to our database (JSON-file)
    }
}
