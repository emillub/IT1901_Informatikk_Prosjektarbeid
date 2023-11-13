package bookapp.fxui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import bookapp.core.User;
import bookapp.core.BookReview;
import bookapp.core.Book;
import bookapp.core.BookComparator;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class AppController {

    @FXML
    private Button loginbutton, vurderButton, deleteReviewButton;

    @FXML
    private Label mainwindow;

    @FXML 
    private TextField nameTextField;

    @FXML
    private Text markedBookText,userNameText;

    @FXML
    private ChoiceBox<Integer> rateChoiceBox;
    @FXML
    private ChoiceBox<String> sortChoiceBox;

    @FXML
    private ListView<BookReview> reviewListView;

    @FXML
    private ListView<Object> bookListView;

    @FXML
    private AnchorPane mainPane,loginPane;

    @FXML
    private HBox vurderHBox;


    private User user;
    
    private ArrayList<Book> bookList = new ArrayList<Book>();

    private RemoteBookappModelAccess controller;
    private Book selectedBook;
    private BookReview selectedBookReview;

    @FXML public void initialize(){
        controller = new RemoteBookappModelAccess();
        rateChoiceBox.setItems(FXCollections.observableArrayList(BookReview.RATING_RANGE));
        rateChoiceBox.setValue(BookReview.RATING_RANGE[0]);
        sortChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(BookComparator.BOOK_TITLE,BookComparator.AUTHOR_NAME,BookComparator.RATING)));
        sortChoiceBox.setValue(BookComparator.BOOK_TITLE);
        loginPane.setVisible(true);
        mainPane.setVisible(false);
    }
    
    @FXML private void loginButtonClick(){ 
        try{
            user = getUser();
        }
        catch(Exception e){
            displayError("Invalid username",e);
        }
        loadLibrary();
        userNameText.setText("Innlogget som: " + user.getName());
        updateVurderHbox();
        loginPane.setVisible(false);
        mainPane.setVisible(true);
    }

    @FXML private void bookListViewClicked(){
        Object selectedItem = bookListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem != selectedBook){
            selectedBookReview = null;
            selectedBook = (Book) selectedItem;
            updateVurderHbox();
            updateMarkedBookText(selectedBook.getTitle());
            updateReviewListView();
        }
    }
    @FXML private void reviewListViewClicked(){
        Object selectedItem = reviewListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem != selectedBookReview){
            selectedBookReview = (BookReview) selectedItem;
            updateVurderHbox();
            updateReviewListView();
        }
    }

    //Adding a review through a HTTP method
    @FXML private void vurderButtonClicked(){
        if (selectedBook == null) return;
        var rating = rateChoiceBox.getSelectionModel().getSelectedItem();
        if(rating == null) return;

        addReview(user, selectedBook, (int)rating);
        updateReviewListView();
        updateBookListView();
        saveLibrary();
    }

    //Deleting a review through a HTTP method 
    @FXML private void deleteReviewButtonClick(){
        delete(selectedBook, selectedBookReview);
        updateReviewListView();
        updateBookListView();
        selectedBookReview = null;
        updateVurderHbox();
        saveLibrary();
    }

    @FXML private void sortChoiceBoxClick(){
        updateBookListView();
    }

   
    private void loadLibrary(){
        List<Book> loadedBooks = controller.fetchlibrary();
        bookList.addAll(loadedBooks);
        updateBookListView();
    } 

    //Deleteing a review through a HTTP request
    private void delete(Book book, BookReview bookreview){
        String bookname = book.getTitle();
        book.deleteReview(bookreview);
        controller.deleteReview(bookname, bookreview); 
        updateBookListView();
    }
    
    //Adding a review through a HTTP request
    private void addReview(User user, Book book, int rating){
        try {
            BookReview review = new BookReview(book, user, rating);
            controller.addReview(book.getTitle(), review);
            updateBookListView();
        } catch (Exception e) {
            displayError("Already reviewed book", e);
        }
    }

    //Saving a library through a HTTP method
    private void saveLibrary(){
        List<Book> listofbooks = new ArrayList<Book>();
        for (Book book : bookList){
            listofbooks.add(book);
        }
        controller.update(listofbooks);
    }

    private User getUser() throws Exception{ 
        String name = nameTextField.getText();
        return new User(name);
    }

    private void updateBookListView(){
        bookList.sort(new BookComparator(sortChoiceBox.getValue()));
        bookListView.setItems(FXCollections.observableArrayList(bookList));    
    }

    private void updateReviewListView(){
        reviewListView.setItems(FXCollections.observableArrayList(selectedBook.getReviews()));
    }

    private void updateMarkedBookText(String bookTitle){
        markedBookText.setText("Markert bok: " + bookTitle);
    }

    private void updateVurderHbox(){
        if (selectedBook == null){
            vurderHBox.setDisable(true);
        } else {
            vurderHBox.setDisable(false);
        }
        if (selectedBookReview == null || !selectedBookReview.getReviewer().getName().equals(user.getName())){
            deleteReviewButton.setDisable(true);
        } else {
            deleteReviewButton.setDisable(false);
        }
    }

    private void displayError(String errorName,Exception e){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(errorName);
        alert.setContentText(e.getMessage());             
        alert.showAndWait();
    }
}
