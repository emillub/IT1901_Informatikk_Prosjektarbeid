package bookapp.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class BookApp extends Application { 
    public static void main(String[] args) {
        launch(); 
    }

 @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Mainwindow.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    }
