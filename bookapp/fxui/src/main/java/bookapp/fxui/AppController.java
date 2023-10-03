package bookapp.fxui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import bookapp.core.User;
import bookapp.core.BookReview;
import bookapp.persistence.FileHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    private Button loginbutton, vurderButton;

    @FXML
    private Label mainwindow;

    @FXML 
    private TextField nameTextField;

    @FXML
    private Text markedBookText,userNameText;

    @FXML
    private ChoiceBox<Integer> rateChoiceBox;

    @FXML
    private ListView<BookReview> reviewListView;

    @FXML
    private ListView<Object> bookListView;

    @FXML
    private AnchorPane mainPane,loginPane;

    @FXML
    private HBox vurderHBox;


    private User user;
    
    private ArrayList<Object> books = new ArrayList<Object>();

    private static FileHandler fileHandler = new FileHandler();

    private Book selectedBook;

    @FXML public void initialize(){
        rateChoiceBox.setItems(FXCollections.observableArrayList(BookReview.RATING_RANGE));
        loginPane.setVisible(true);
        mainPane.setVisible(false);
    }
    
    @FXML private void loginButtonClick(){ //alt som skjer etter login
        loadLibrary();
        user = getUser();
        System.out.println(user);
        userNameText.setText("Innlogget som: " + user.getUserName());
        updateVurderHbox();
        loginPane.setVisible(false);
        mainPane.setVisible(true);
    }

    @FXML private void bookListViewClicked(){
        Object selectedItem = bookListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem != selectedBook){
            selectedBook = (Book) selectedItem;
            updateVurderHbox();
            updateMarkedBookText(selectedItem.toString());
        }
    }

   
    private void loadLibrary(){//Funksjon for å laste inn bibliotek
        FileHandler.main(null);
        Object book = fileHandler.readBookFromFile(FileHandler.DIR_PATH);
        books.add(book);
        updateBookListView();
    } 

    private User getUser(){ //Henter bruker fra textfelt
        String name = nameTextField.getText();
        return new User(name);
    }

    private void updateBookListView(){
        bookListView.setItems(FXCollections.observableArrayList(books));    
    }

    private void updateMarkedBookText(String bookTitle){
        markedBookText.setText("Markert bok: " + bookTitle);
    }

    private void updateReviewListView(){
        reviewListView.setItems(FXCollections.observableArrayList(selectedBook.getReviews()));
    }

    private void updateVurderHbox(){
        if (selectedBook == null){
            vurderHBox.setDisable(true);
        } else {
            vurderHBox.setDisable(false);
        }
    }
    
}
