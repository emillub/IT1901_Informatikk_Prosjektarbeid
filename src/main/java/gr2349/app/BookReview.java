package main.java.gr2349.app;

public class BookReview{

    Book book;
    User user;

    String review;
    int rating;    

    public BookReview(User user, Book book, String review, int rating){

        this.book = book;
        this.user = user;
        this.review = review;
        this.rating = rating;
    }

    public String getReview(){
        return this.review;
    }

    public int getRating(){
        return this.rating;
    }
}