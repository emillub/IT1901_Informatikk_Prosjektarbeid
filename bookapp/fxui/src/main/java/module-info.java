module bookapp.fxui {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires bookapp.core;
    requires bookapp.restapi;

    opens bookapp.fxui to javafx.graphics, javafx.fxml;
}  