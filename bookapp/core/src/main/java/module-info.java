module bookapp.core {
    requires com.fasterxml.jackson.databind;
    opens bookapp.core to com.fasterxml.jackson.databind;
    exports bookapp.core;
    
}