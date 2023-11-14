package bookapp.fxui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import bookapp.core.Book;
import bookapp.core.BookComparator;
import bookapp.core.BookReview;
import bookapp.core.User;


public class BookAppTest extends ApplicationTest {
    Scene scene;
    Stage stage;
    ListView<Book> bookListView;
    ListView<BookReview> reviewListView;
    Book bookToReview;
    
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
        bookToReview = bookListView.getItems().get(1);
    }

    @Test 
    void testLogIn(){
        Text userNameText = from(scene.getRoot()).lookup("#userNameText").queryText();
        assertTrue(userNameText.getText().contains("Donald Trump"));
    }

    @Test
    void testddReview(){
        click(bookToReview.toString());
        clickOn("#rateChoiceBox");
        click("5");
        click("Vurder");
        assertTrue(!bookToReview.getReviews().isEmpty());
    }
    
    @Test
    void testSortReviews(){
        new BookReview(bookToReview, new User("user"),5);
        assertNotEquals(bookListView.getItems().get(0), bookToReview);
        clickOn("#sortChoiceBox");
        click(BookComparator.RATING);
        assertEquals(bookListView.getItems().get(0),bookToReview);
    }

    @Test
    void testDeleteReview(){
        click(bookToReview.toString());
        click(reviewListView.getItems().get(0).toString());
        clickOn("#deleteReviewButton");
        assertTrue(bookToReview.getReviews().isEmpty());
    }

}