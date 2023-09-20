package gr2349.app;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;



public class AppController {

    @FXML
    private Button loginbutton, vurderButton;

    @FXML
    private Label mainwindow;

    @FXML 
    private TextField name;

    @FXML
    private Text markertBokText,userNameText;

    @FXML
    private ChoiceBox<Integer> rateChoiceBox;

    @FXML
    private ListView<BookReview> reviewListView;

    @FXML
    private ListView<Object> bookListView;

    @FXML
    private AnchorPane mainPane,loginPane;

    
    private ArrayList<Object> books = new ArrayList<Object>();

    private static FileHandler fileHandler = new FileHandler();
    
    public void loginButtonClick(){
        loadLibrary();
        loginPane.setVisible(false);
        mainPane.setVisible(true);
    }

   
    private void loadLibrary(){//Funksjon for å laste
        Object book = fileHandler.readBookFromFile(FileHandler.DIR_PATH);
        books.add(book);
        updateBookListView();
    } 

    private void updateBookListView(){
        bookListView.setItems(FXCollections.observableArrayList(books));
    }

    public void getUser(ActionEvent event){
        String navn = name.getText();
        User bruker = new User(navn);
    }
    
}
