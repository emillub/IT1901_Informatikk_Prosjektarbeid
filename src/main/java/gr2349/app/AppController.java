package gr2349.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    private ListView<BookReview> reviewList;
    @FXML
    private ListView<Book> bookList;


    
    public void handleclick(ActionEvent event){
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Mainwindow.fxml"));
        Parent mainwindow = fxmlLoader.load();
        Stage newstage = (Stage) loginbutton.getScene().getWindow();
        newstage.setScene(new Scene(mainwindow));
        newstage.show();

    } catch (IOException e){
            e.printStackTrace();
        }
    }

    

    public void nameinput(ActionEvent event){
        String navn = name.getText();
        User bruker = new User(navn);
    }
    
}
