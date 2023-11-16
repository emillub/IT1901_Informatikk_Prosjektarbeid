# README - Core

## Introduction

* The Core module consists of three classes: Book, BookReview, and User, and is responsible for handling the core logic of the app.

## Technologies used

* Jackson library
  * The Jackson library converts objects from the core module into JSON format, which is then written to a file.

## Functionality

* The Book class holds information about a book and references to BookReview objects. It represents a book with reviews and has a simple ```toString()```-function used in the interface.
  * ```getAvarageRating()```
    * Iterates over the BookReview objects and calculates the average of all ratings.
  * ```validateReview(BookReview review)``` 
    * Checks that a review does not already exist and if a user has already written a review for the book. Throws an ```IllegalArgumentException``` if so. 
* The BookReview class represents a review of a book. It has references to the User object that created it and the Book object it belongs to. It also has a static int array that determines the values one can give as a rating.
  * ```BookReview(Book book, User reviewer, int rating)```
    * Creates a BookReview object. Checks that it is created by a user and throws an error if not.
  * ```validRating(int r)```
    * Checks if the given rating is valid and throws an error if not.
* The User class handles everything related to users. It contains minimal logic but holds information about the user, and it is from this class that BookReview objects are created.
  * ```writeReview(Book bok, in rating)```
    * Creates a BookReview object with a rating and associates it with a book.

