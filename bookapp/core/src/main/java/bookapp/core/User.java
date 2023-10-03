package bookapp.core;


public class User{

    private String name;


    public User(String name){
        this.name = name;
    }

    public String getUserName(){
        return this.name;
    }

    public void writeReview(Book book, int Rating){
        BookReview review = new BookReview(book, name, Rating);
        review.addRatingToBook(book);
    }

    @Override
    public String toString() {
        return this.name;
    }
}