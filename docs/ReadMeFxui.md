# README - Fxui

## Introduction

* The Fxui module is responsible for the visual interface of the application and utilizes both the Core and Persistence modules.
* The "Mainwindow.fxml" located in the "bookapp/fxui/src/main/resources" directory is opened and read using JavaFX, controlled by the AppController class.

## Technologies used

* JavaFX
  * JavaFX is part of the Java Development Kit and is a library that allows you to create graphical interfaces for both desktop and mobile applications using Java.
* Scene Builder
  * Scene Builder is an application that facilitates the creation of the graphical interface for a JavaFX app. It is used to write "Mainwindow.fxml" but is not required to run BookApp.

## Functionality

* The module is executed from the BookApp class, which reads and opens "Mainwindow.fxml" as a graphical interface.
  * ```start(Stage stage)```
    * Locates "Mainwindow.fxml" in the resources directory and opens it with JavaFX.
* The AppController class is linked to Mainwindow.fxml and is responsible for all functionality in the user interface. It contains references to all objects. Most of the logic resides in the Core module, but the controller connects user input to functions in the Core and Persistence modules, ensuring that the graphical interface reflects what happens in the core logic.

## Screenshots

![Viser innloggingsside](/docs/imgs/loginpage.png)
![Viser hovedside før review](/docs/imgs/mainpagePreReviewR2.png)
![Viser hovedside etter review](/docs/imgs/mainpagePostReviewR2.png)
