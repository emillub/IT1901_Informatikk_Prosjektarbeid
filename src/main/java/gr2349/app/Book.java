package gr2349.app;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book implements Serializable{
    
    private Book book;
    private String title;
    private String author;

    @JsonIgnore //Henter ikke reviews av boken når boken skrives til fil
    private ArrayList<BookReview> reviewsOfBook;
    private float rating;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public Book getBook() {
        return this.book;
    }

    public void getAverageRating(){
        int amountOfRatings = this.reviewsOfBook.size();
        //iterere over finne avg rating 
        int sumOfRatings = 0;
        for (int i = 0; i < amountOfRatings; i++) {
            sumOfRatings += reviewsOfBook.get(i).getRating();
        }
        this.rating = sumOfRatings/amountOfRatings;
    } 

    public void addReview(BookReview review){
        reviewsOfBook.add(review);
    }

    @Override
    public String toString(){
        return this.title;
    }
}
