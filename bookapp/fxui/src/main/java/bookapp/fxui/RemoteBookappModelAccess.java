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
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bookapp.core.Book;
import bookapp.core.BookReview;
import bookapp.restapi.BookappModelController;


//This class handles communication between the UI and the HTTP methods defined in BookappModelController
public class RemoteBookappModelAccess{
    
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

    public void deleteReview(String BookName, BookReview review){
        //Need this to remove a review in the "database" (in our case the json file)
        try {
            String updatedName = BookName.replace(" ","%20");
            System.out.println("http://localhost:8080" + ADDRESS + "/delete/" + updatedName+"/" + review.getReviewer().getName());

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080" + ADDRESS + "/delete/" + updatedName + "/" + review.getReviewer().getName()))
            .header("Content-Type", APPLICATION_JSON).header(review.getReviewer().getName(), APPLICATION_JSON)
            .DELETE()
            .build();
            
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int responseStatus = response.statusCode();
            if (responseStatus>=200 && responseStatus<=300) {
            //Succesfully deleted the review
            System.out.println("Sucsessfully deleted review");
            } else {
                throw new RuntimeException("HTTP request failed with status code: " + responseStatus);
            }
        }catch (IOException|InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    

    public void addReview(String BookName, BookReview review){
    //Need this function to add a review to our database (JSON-file)
        try {
            String reviewerjson = mapper.writeValueAsString(review);
            String updatedName = BookName.replace(" ","%20");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080" + ADDRESS + "/post/" + updatedName))
            .header("Content-Type", APPLICATION_JSON)
            .POST(BodyPublishers.ofString(reviewerjson))
            .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int responseStatus = response.statusCode();
            if (responseStatus>=200 && responseStatus<=300) {
                //Successfully added a review
                System.out.println("Successfully added review");
            } else {
                throw new RuntimeException("HTTP request failed with status code: " + responseStatus);
            }

        }catch(IOException|InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(List<Book> lib){
        try {
            String library = mapper.writeValueAsString(lib);
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080" + ADDRESS + "/updatelibrary"))
            .header("Content-Type", APPLICATION_JSON)
            .PUT(BodyPublishers.ofString(library))
            .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int responseStatus = response.statusCode();
            if (responseStatus>=200 && responseStatus<=300) {
                System.out.println("Succsessfully updated the booklist");
            } else {
                throw new RuntimeException("HTTP request failed with status code: " + responseStatus);
            }

        }catch(IOException|InterruptedException e) {
            throw new RuntimeException(e);
        }
    } 

}
