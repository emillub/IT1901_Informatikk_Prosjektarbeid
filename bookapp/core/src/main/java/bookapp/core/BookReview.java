package bookapp.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookReview {
    private String reviewer; 
    private int rating;

    @JsonIgnore
    public final static Integer[] RATING_RANGE = {1,2,3,4,5};

    //Constructors
    public BookReview(Book book, String reviewer, int rating){
        addRatingToBook(book);
        this.reviewer = reviewer;
        setRating(rating);
    }
    
    //Getters
    public String getReviewer(){
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

    public void addRatingToBook(Book book){
        book.addReview(this);
    }

    //Validators
    private boolean validRating(int r){
        if (r <= RATING_RANGE[0] || r>RATING_RANGE[1]){ 
            throw new IllegalArgumentException("Rating må være mellom 1 og 5");}
        
        return true;
    }
}
