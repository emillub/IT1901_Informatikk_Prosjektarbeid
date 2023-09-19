package gr2349.app;

public class BookReview {
    private Book book;
    private int reviewer; //Endre til et brukerobjektet som skriver vurderingen
    private int rating;
    private String title;
    private String content;

    //Constructors
    public BookReview(Book book, int reviewer, int rating, String title, String content){
        this.book = book;
        this.reviewer = reviewer;
        setRating(rating);
        this.title = title;
        this.content = content;
    }
    
    //Getters
    public Book getBook(){
        return book;
    }
    public int getReviewer(){
        return reviewer;
    }
    public int getRating(){
        return rating;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    
    //Setters - Hva skal man kunne endre paa?
    public void setRating(int r){
        if (validRating(r)){
            this.rating = r;
        }
    }

    //Validators
    private boolean validRating(int r){
        if (r <= 0 || r>5){ 
            throw new IllegalArgumentException("Rating has to be between 1 and 5");}
        
        return true;
    }
}
