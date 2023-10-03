module bookapp.persistence {
    requires transitive bookapp.core;
    requires com.fasterxml.jackson.databind;
    
    exports bookapp.persistence to com.fasterxml.jackson.databind, bookapp.fxui;

}