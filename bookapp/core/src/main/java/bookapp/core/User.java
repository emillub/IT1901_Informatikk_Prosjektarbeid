package bookapp.core;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class User implements Serializable{

    private String name;
    
    @JsonCreator
    public User(@JsonProperty("name") String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void writeReview(Book book, int rating){
        new BookReview(book, this, rating);
    }

    public void deleteReview(BookReview review){
        review.getBook().deleteReview(review);
    }

    @Override
    public String toString() {
        return this.name;
    }
}