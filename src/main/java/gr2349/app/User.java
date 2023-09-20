package gr2349.app;

import java.util.ArrayList;


public class User{

    String Name;


    public User(String Name){

        this.Name = Name;

    }

    public String getUserName(){
        return this.Name;
    }
    @Override
    public String toString() {
        return this.Name;
    }
}