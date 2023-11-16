package bookapp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Book implements Serializable{
    
    private String title;
    private String author;
    private ArrayList<BookReview> reviewsOfBook = new ArrayList<BookReview>();

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
    public float getAverageRating(){
        if (this.reviewsOfBook == null || this. reviewsOfBook.size() == 0) {
            return 0;
        }
        int amountOfRatings = this.reviewsOfBook.size();
        float sumOfRatings = 0;
        for (int i = 0; i < amountOfRatings; i++) {
            sumOfRatings += reviewsOfBook.get(i).getRating();
        }
        float avgRating = sumOfRatings/amountOfRatings;
        return avgRating;
    } 

    private void validateReview(BookReview review){
        if (reviewsOfBook.contains(review)) throw new IllegalArgumentException("Review already exists");
        
        if (reviewsOfBook.stream().anyMatch(r -> r.getReviewer().getName().equals(review.getReviewer().getName()))){
            throw new IllegalArgumentException("Users cannot write more than one review per book");
        }

    }

    public void addReview(BookReview review){
        validateReview(review);
        reviewsOfBook.add(review);
    }

    public void deleteReview(BookReview review){
        if(!reviewsOfBook.contains(review)){
            throw new IllegalArgumentException("Review not found in this books reviews");
        }
        reviewsOfBook.remove(review);
    }

    public ArrayList<BookReview> getReviews(){
        return this.reviewsOfBook;
    }

    @Override
    public String toString(){
        return this.title + "\nSkrevet av: " + this.author + "\nGjennomsnittsvurdering: " + getAverageRating();
    }
}
