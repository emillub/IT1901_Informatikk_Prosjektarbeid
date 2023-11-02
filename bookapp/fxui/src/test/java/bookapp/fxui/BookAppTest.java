package bookapp.fxui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import bookapp.core.Book;
import bookapp.core.BookReview;


public class BookAppTest extends ApplicationTest {
    Scene scene;
    Stage stage;
    ListView<Book> bookListView;
    ListView<BookReview> reviewListView;
    Book book;
    
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Mainwindow.fxml"));
        Parent parent = fxmlLoader.load();
        scene = new Scene(parent);
        this.stage = stage;
        stage.setScene(scene);
        stage.show();
    }

    private void click(String... labels) {
        for (var label : labels) {
            clickOn(LabeledMatchers.hasText(label));
        }
    }

   @BeforeEach
    void setUp(){
        clickOn("#nameTextField").write("Donald Trump");
        click("Log in");
        bookListView = from(scene.getRoot()).lookup("#bookListView").queryListView();
        reviewListView = from(scene.getRoot()).lookup("#reviewListView").queryListView();
        book = bookListView.getItems().get(0);
    }

    @Test 
    void testLogIn(){
        Text userNameText = from(scene.getRoot()).lookup("#userNameText").queryText();
        assertTrue(userNameText.getText().contains("Donald Trump"));
    }

    @Test
    void testddReview(){
        click(book.toString());
        clickOn("#rateChoiceBox");
        click("5");
        click("Vurder");
        assertTrue(!book.getReviews().isEmpty());
    }

    @Test
    void testDeleteReview(){
        click(book.toString());
        click(reviewListView.getItems().get(0).toString());
        //click("Donald Trump");
        clickOn("#deleteReviewButton");
        assertTrue(book.getReviews().isEmpty());
    }
}