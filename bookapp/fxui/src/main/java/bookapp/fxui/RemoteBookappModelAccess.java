package bookapp.fxui;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bookapp.core.Book;
import bookapp.restapi.BookappModelController;


//This class handles communication between the UI and the HTTP methods defined in BookappModelController
public class RemoteBookappModelAccess{
    
    private static URI address;
    
    private static final String APPLICATION_JSON = "application/json";  
    
    private static final String ACCEPT_HEADER = "Accept";  
    
    private static final String ADDRESS = "/api/books";
    
    private static final String FETCH = "/fetchList";
    
    private static ObjectMapper mapper = new ObjectMapper();

    //Returns a List<Book> object that contains all information to load the library.
    public List<Book> fetchlibrary(){
        try{
            List<Book> booklist;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080"+ADDRESS+FETCH)).
            header("Content-type", APPLICATION_JSON).build();
            
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); 
            int responseStatus = response.statusCode();
            System.out.println(response.body());
            if (responseStatus>=200 && responseStatus<=300){
                booklist = mapper.readValue(response.body(), new TypeReference<List<Book>>() {});
                return booklist;
            }else {
                throw new RuntimeException("HTTP request failed with status code: " + responseStatus);
            }

        }catch (IOException|InterruptedException e ){
            throw new RuntimeException(e);

        }
    }

    public static void deleteReview(String reviewName){
    //Need this to remove a review in the "database" (in our case the json file)
    }

    public static void addReview(String reviewName){
    //Need this function to add a review to our database (JSON-file)
    }
}
