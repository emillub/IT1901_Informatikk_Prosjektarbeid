package bookapp.core;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable{

    private String name;
    
    @JsonCreator
    public User(@JsonProperty("name") String name){
        validateName(name);
        this.name = name;
    }

    private void validateName(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("User name cannot be blank");
        }
    }

    public String getName(){
        return this.name;
    }

    public void writeReview(Book book, int rating){
        new BookReview(book, this, rating);
    }

    public void deleteReview(BookReview review){
        review.deleteReview();
    }

    @Override
    public String toString() {
        return this.name;
    }
}