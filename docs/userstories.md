# User Stories (US)
Here we will write our user stories. User stories is used to describe which needs our application adresses and what is required to fulfill them. 


## User Story Convention: 
### Title: Feature (US number)
Story: "As a (Who/Role), I want to...(What/Goal) so that...(Why/Benefit)"

#### Requirements:
* Feature 1
* Feature 2

### Interface (US 1)
As a user, I want to have an overview of books and the ability to see reviews of them so that I can decide if they are worth reading.

### Requirements:
* GUI page for displaying books
* GUI page for displaying reviews per book

### Reviews (US 2)
As a user, I want to leave a signed review from 1-5 for books I have read so others can know if they are worth reading.

#### Requirements: 
*   User must have the ability to enter their name in the app
*   BookReview class must take a name parameter
*   User class must have the ability to create a review
*   User must have the ability to choose a book and review it

### Storage (US 3)
As a user, I want my reviews to be saved so that both others and I can see them again later.

### Requirements:
*   File handling system capable of both writing and reading from a file
*   Auto-saving
*   Ensuring the existence of the directory

### Sorting books (US 4)
As a user, I want to be able to sort the books in the library based on their ratings, author and title so that I easily can locate the books.

### Requirements:
*   Comparator class for comparing the books that needs to be sorted. 
*   A friendly user interface so its intuitive how to sort the books, a dropdown menu which presents the sorting factors.
*   Sort on the general ASCII table, meaning upper case letter comes before small letter. 

### Delete review (US 5)
As a user, I want to be able to delete a review so that I can give a new rating if I change my mind about the book.

### Requirements
*   HTTP request for deleting reviews and updating to the server.
*   Functionality for removing the review from the library. 