# README - RestAPI

## Introduction

By release 3 the intentions of this module has changed and now revolves around hosting a server, handeling HTTP messages and managing the storage of user data. Data is stored in "Library.json," located in the "bookapp/restAPI/src/main/resources" directory while our server is hosted using springboot from the BookappModelApplication-file.  

## Technologies Used

We have implemented our own restAPI to handle incomming client HTTP messages. We use the following:
* Jackson Library; the Jackson library converts objects from the core module into JSON format, which is then written to a file.
* Spring; this module has a dependency to org.springframework.boot which allows us to host a server on our local network. This is necessary to transmitt HTTP messages and essential if we want our application to work remotely.
* HTTP message responses; we have made HTTP responses for Get, Put, Post and Delete methods. All are defined in [BookappModelController](bookapp\restapi\src\main\java\bookapp\restapi\BookappModelController.java)

## Functionality


### Storing user data
The storage of user data is done through the jackson library and this is mainly accomplished through the "writeBooksToFile" and "readBooksFromFile" functions.
* ```writeBooksToFile()```
  * This function takes a list of Book objects as input. An instance of ObjectMapper then goes into the various Book instances, retrieves relevant information, and writes it to a file.
* ```readBooksFromFile()```
  * This function does not take any parameters because the necessary information is in "Library.json." The function accesses this file and creates instances of core classes based on the information in the JSON file.

### Hosting the server
This is done through the spring framework and an instance of a new server is created through the "BookappModelApplication"-file. This is a single method object that initialaizes and opens the server on the local network. 

### HTTP Handeling
We have implemented our own restAPI which handles HTTP messages. This is done through four functions in the "BookappModelController":
* ```getBook()```
  * This function does not take parameters but upon receiving a HTTP Get request message, it returns a List<Book>-object through a HTTP response.
* ```postReview()```
  * This function lets users post a review to the "database"/Library.json-file through a HTTP Post message. It extracts the bookname and reviewer before it posts it to the data storage.
* ```deleteReview()```
  * Works quite similar to the Post-function. Extracts the bookname and reviewer before it searches through the database and deletes the review if it finds it.
* ```updateLibrary()```
  * This function is used to update the library in the database, takes no parameters and returns nothing.

These methods are structured in a similar fashion with the methods first creating a new HTTP request and 
a new client that sends the request. The body of the HTTP messages determine the format of the message sent and the URI is specified in the URI field. This allows for fluent communication between the client side and server side.

## Overview - Releases

1. Release 2 - Writing to File Using Jackson Library
  * File Format
  * Implicit Storage or Document Metaphor
2. Release 3 - Turning the persistence moduel into a server and database host.
  * Client server architecture
  * Implications

### Release 2

#### 1. Define/Document File Format

##### File Format

* Data is stored in JSON format. JSON is flexible and suitable for structurally organized data.

* User data (Review and User) is written to a file every time the "Review" button is used. The data is stored in Book objects, which are written to "Library.json."

##### Example

```
{ "books": [ { "title": "Example Book 1", "reviews": [ { "user": "Username 1", "rating": 4 }, { "user": "Username 2", "rating": 3 } ] }, ... ] }
```

##### Explanation

* Here, we have a main key "books" that contains a list of books. Each book has a "title" and a list of "reviews."

* Each "review" contains a "user" and a "rating."

#### 2. Choice and Reflection between Implicit Storage and Document Metaphor

##### Document Metaphor (desktop)

###### Advantages

* Users are often familiar with this metaphor because it mimics familiar physical objects and environments, such as desktops and documents.

* Provides a clear visualization of data and actions.

###### Disadvantages

* Can sometimes be less efficient for simple applications.

* Takes up more screen space.

##### Implicit Storage (app)

###### Advantages

* Provides a quick and seamless user experience. Users don't need to worry about manual storage.

* Reduces the possibility of user errors, such as forgetting to save.

###### Disadvantages

* Users may be uncertain whether the data is actually saved.

* Can lead to unintentional overwriting of desired data.

##### Conclusion for Your Application

* The application uses a document metaphor for storing user data:

1. Manual Storage: Using the "Review" button to save a review is similar to the practice of manually saving a document. This gives the user full control over when data is saved, and it provides a clear indication of the action being performed.

2. Visualization of Objects: By presenting books in a list and allowing users to select a specific book before providing a review, the application simulates the action of choosing a physical document (in this case, a book) before giving a review.

3. Sequential Interaction: Users must first select a book, then input a review, and finally confirm the review by pressing "Review." This step-by-step process resembles the sequential nature of dealing with physical documents.

### Release 3

#### The client architecture
The client server architecture is a widely adopted model that presents a user as a client who can request information from a server. The server is an "isolated" entity from the client and only knows how communicate with the client through HTTP messages. A quick pros and cons consideration for the application follows: 

##### Pros
 * The model is widely used and can potentially scale to allow for more users and permantent server. 
 * Our application now mimicks how real-world applications works. This allows for better understanding of the API layer of the internet for us developers.

 ##### Cons
 * As the client-server model requires communication between two entities, the application needs a network to now work. For release two it did not require this as all functionality was handeled locally.
 * During implementation, there are now more moving parts that need to correspond together. Our server is not hosted externally meaning it always needs to be turned on during usage of the app.

 #### Conclusion
 There are several pros and cons to using the model we have right now. As the remote access presents challenges when used ofline, we could have implemented a direct access system that lets a user use the application offline, but it was not a requirement. Our method is functional and given us valuable insight API development through apps.