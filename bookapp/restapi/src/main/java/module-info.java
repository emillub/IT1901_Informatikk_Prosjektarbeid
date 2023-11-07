module bookapp.restapi {
    requires transitive bookapp.core;
    requires com.fasterxml.jackson.databind;
    requires spring.boot;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    
    exports bookapp.restapi to com.fasterxml.jackson.databind;

}
