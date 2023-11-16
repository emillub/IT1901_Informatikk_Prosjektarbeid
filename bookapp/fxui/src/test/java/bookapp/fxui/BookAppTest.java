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

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import bookapp.core.Book;
import bookapp.core.BookComparator;
import bookapp.core.BookReview;
import bookapp.core.User;

@TestMethodOrder(OrderAnnotation.class)
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

    void logIn(){
        clickOn("#nameTextField").write("Donald Trump");
        click("Logg inn");
        bookListView = from(scene.getRoot()).lookup("#bookListView").queryListView();
        reviewListView = from(scene.getRoot()).lookup("#reviewListView").queryListView();
        bookToReview = bookListView.getItems().get(0);
    }

    @Test @Order(1)
    void testBlankName(){
        click("Logg inn");
        click("OK");
    }
    @Test @Order(2)
    void testLogIn(){
        logIn();
        Text userNameText = from(scene.getRoot()).lookup("#userNameText").queryText();
        assertTrue(userNameText.getText().contains("Donald Trump"));
    }
    
    @Test @Order(3)
    void testAddReview(){
        logIn();
        click(bookToReview.toString());
        click(bookToReview.toString());
        clickOn("#rateChoiceBox");
        click("3");
        click("Vurder");
        assertTrue(!bookToReview.getReviews().isEmpty());
        clickOn("#rateChoiceBox");
        click("3");
        click("Vurder");
        click("OK");
    }
    
    @Test @Order(4)
    void testSortReviews(){
        logIn();
        Book highestRatedBook = bookListView.getItems().get(1);
        new BookReview(highestRatedBook, new User("user"),5);
        assertNotEquals(bookListView.getItems().get(0), highestRatedBook);
        clickOn("#sortChoiceBox");
        click(BookComparator.RATING);
        assertEquals(bookListView.getItems().get(0),highestRatedBook);
    }
    
    @Test @Order(5)
    void testDeleteReview(){
        logIn();
        assertTrue(from(scene.getRoot()).lookup("#deleteReviewButton").queryButton().disableProperty().get());
        click(bookToReview.toString());
        click(reviewListView.getItems().get(0).toString());
        click(reviewListView.getItems().get(0).toString());
        clickOn("#deleteReviewButton");
        assertTrue(bookToReview.getReviews().isEmpty());
    }

}