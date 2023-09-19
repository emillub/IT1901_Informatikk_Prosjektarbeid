package gr2349.app;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Book {
    
    private Book book;
    private String title;
    private String genre;
    private String author;
    private int pages;
    private ArrayList<BookReview> reviewsOfBook;
    private float rating;

    public Book(String title, String genre, String author, int pages) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPages() {
        return this.pages;
    }

    public Book getBook() {
        return this.book;
    }

    public void getAverageRating(){
        int amountOfRatings = this.reviewsOfBook.size();
        //iterere over finne avg rating 
        int sumOfRatings = 0;
        for (int i = 0; i < amountOfRatings; i++) {
            sumOfRatings += reviewsOfBook[i].rating;
        }
        this.rating = sumOfRatings/amountOfRatings;
    } 
}
