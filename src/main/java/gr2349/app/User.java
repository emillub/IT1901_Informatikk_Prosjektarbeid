package gr2349.app;

import java.util.ArrayList;


public class User{

    String FirstName;
    String LastName;
    String UserName;
    String email;
    ArrayList<BookReview> reviews;

    public User(String FirstName, String LastName, String email, String UserName){

        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = email;
        this.UserName = UserName;
        
    }

    public void setReview(BookReview review){
        this.reviews.add(review);
    }

    public String getUserName(){
        return this.UserName;
    }
    @Override
    public String toString() {
        return this.FirstName + "," + this.LastName + "," + this.UserName + "," + this.email;
    }
}