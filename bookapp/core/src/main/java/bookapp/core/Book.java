package bookapp.core;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Book implements Serializable{
    
    private Book book;
    private String title;
    private String author;
    private ArrayList<BookReview> reviewsOfBook;

    public Book(){}
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

    @JsonIgnore
    public Book getBook() {
        return this.book;
    }

    @JsonIgnore
    public float getAverageRating(){
        //Unngår divisionByZero error og reviewsOfBook is null error
        if (this.reviewsOfBook == null || this. reviewsOfBook.size() == 0) {
            return 0;
        }
        int amountOfRatings = this.reviewsOfBook.size();
        //iterere over finne avg rating 
        int sumOfRatings = 0;
        for (int i = 0; i < amountOfRatings; i++) {
            sumOfRatings += reviewsOfBook.get(i).getRating();
        }
        return sumOfRatings/amountOfRatings;
    } 

    public void addReview(BookReview review){
        reviewsOfBook.add(review);
    }

    public void setReviews(List<BookReview> reviews) {
        if (reviews == null) return;
        this.reviewsOfBook = new ArrayList<>(reviews);
    }

    public ArrayList<BookReview> getReviews(){
        return this.reviewsOfBook;
    }

    @Override
    public String toString(){
        return this.title + "\nSkrevet av: " + this.author + "\nGjennomsnittsvurdering: " + getAverageRating();
    }
}
