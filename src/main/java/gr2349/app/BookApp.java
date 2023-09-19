package gr2349.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;


public class BookApp extends Application {
    //Add-functionality

    public static void main(String[] args) {
        launch(); 
    }

    Button button;

    @Override

    public void start(Stage primaryStage) throws IOException{
        primaryStage.setTitle("Myfirstwindow");
        
        button = new Button("Testbutton");
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout,300,250);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
