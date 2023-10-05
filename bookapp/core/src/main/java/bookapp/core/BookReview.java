package bookapp.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookReview {
    private User reviewer; 
    private int rating;

    @JsonIgnore
    public final static Integer[] RATING_RANGE = {1,2,3,4,5}; //Er int-array så den kan brukes til å opprette choiceBox i GUI
    //Constructors
    public BookReview(){}
    public BookReview(Book book, User reviewer, int rating){
        if(reviewer == null) throw new IllegalArgumentException("BookReview-objekt må opprettes med en bruker");
        this.reviewer = reviewer;
        book.addReview(this);
        setRating(rating);
    }
    
    //Getters
    public User getReviewer(){
        return reviewer;
    }
    public int getRating(){
        return rating;
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
            throw new IllegalArgumentException("Rating må være mellom 1 og 5");}
        
        return true;
    }

    public String toString(){
        return "Gitt "+rating + " stjerner \n av " + reviewer.getName();
    }
}
