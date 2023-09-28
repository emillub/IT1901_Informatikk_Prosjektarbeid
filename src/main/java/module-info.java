module gr2349.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    
 opens gr2349.app to javafx.graphics, javafx.fxml;
}