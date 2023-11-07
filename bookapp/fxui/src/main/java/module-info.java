module bookapp.fxui {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires bookapp.core;

    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    
    opens bookapp.fxui to javafx.graphics, javafx.fxml;
}