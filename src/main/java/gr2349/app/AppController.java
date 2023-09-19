package gr2349.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class AppController {

    @FXML
    private Button loginbutton;

    @FXML
    private Label mainwindow;
    
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
 
}
