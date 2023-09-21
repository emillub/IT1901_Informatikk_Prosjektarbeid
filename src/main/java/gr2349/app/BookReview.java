package gr2349.app;

public class BookReview {
    private Book book;
    private String reviewer; //Endre til et brukerobjektet som skriver vurderingen
    private int rating;

    private final static int[] RATING_RANGE = {1,2,3,4,5};

    //Constructors
    public BookReview(Book book, String reviewer, int rating){
        this.book = book;
        this.reviewer = reviewer;
        setRating(rating);
    }
    
    //Getters
    public Book getBook(){
        return book;
    }
    public String getReviewer(){
        return reviewer;
    }
    public int getRating(){
        return rating;
    }

    //Setters - Hva skal man kunne endre paa?
    public void setRating(int r){
        if (validRating(r)){
            this.rating = r;
        }
    }

    //Validators
    private boolean validRating(int r){
        if (r <= RATING_RANGE[0] || r>RATING_RANGE[1]){ 
            throw new IllegalArgumentException("Rating må være mellom 1 og 5");}
        
        return true;
    }
}
