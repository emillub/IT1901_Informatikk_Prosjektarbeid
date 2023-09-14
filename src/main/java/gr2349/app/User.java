package main.java.gr2349.app;

public class User{

    String FirstName;
    String LastName;
    String UserName;
    String email;
    private String password;
    ArrayList<Book> reviews;

    public User(FirstName, LastName, email, UserName, password){

        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = email;
        this.UserName = UserName;
        this.password = password;

    }

    public void setReview(review){
        //Antar review tas inn som objekt av klassen review
        //Burde ta inn bok-objekt og opprette bok-review objekt, i tillegg til å legge til i brukerern sine reviews
        this.reviews.append(review);
    }

    public String getUserName(){
        return this.UserName;
    }

    public boolean login(password){
        if {this.password == password}{return True;}
        else{return False;}
    }
    
}