package gr2349.app;

import java.util.ArrayList;


public class User{

    private String Name;


    public User(String Name){
        this.Name = Name;
    }

    public String getUserName(){
        return this.Name;
    }

    public void writeReview(Book book, int Rating){
        BookReview review = new BookReview(book, Name, Rating);
        review.addRatingToBook(book);
    }

    @Override
    public String toString() {
        return this.Name;
    }
}