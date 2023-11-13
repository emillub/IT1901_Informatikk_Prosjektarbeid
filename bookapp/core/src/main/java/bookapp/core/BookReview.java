package bookapp.core;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookReview implements Serializable{
    private User reviewer; 
    private int rating;
    private Book book;

    @JsonIgnore
    public final static Integer[] RATING_RANGE = {1,2,3,4,5}; 
    //Constructors
    public BookReview(){}
    public BookReview(Book book, User reviewer, int rating){
        if(reviewer == null) throw new IllegalArgumentException("BookReview-object must be created with a user");
        this.reviewer = reviewer;
        this.book = book;
        book.addReview(this);
        setRating(rating);
    }

    public void deleteReview(){
        this.book.deleteReview(this);
    }
    
    //Getters
    public User getReviewer(){
        return reviewer;
    }
    public int getRating(){
        return rating;
    }
    @JsonIgnore
    public Book getBook(){
        return book;
    }

    //Setters
    public void setRating(int r){
        if (validRating(r)){
            this.rating = r;
        }
    }

    //Validators
    private boolean validRating(int r){
        if (r < RATING_RANGE[0] || r>RATING_RANGE[RATING_RANGE.length-1]){ 
            throw new IllegalArgumentException("Rating needs to be between 1 and 5");}
        
        return true;
    }

    public String toString(){
        return "Gitt "+rating + " stjerner \n av " + reviewer.getName();
    }
}
