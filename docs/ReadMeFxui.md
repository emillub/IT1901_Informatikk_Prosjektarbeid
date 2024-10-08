# README - Fxui

## Introduction

For release 3 the **fxui**-module has been changed to no longer work locally. It now works remotely, requiring a server for in and output to read from and to.  

* The Fxui module is responsible for the visual interface of the application and utilizes both the **core**-module for objects referenced.
* "Mainwindow.fxml," located in the [resources](../bookapp/fxui/src/main/resources/bookapp/fxui/Mainwindow.fxml) directory, is opened and read using JavaFX and is controlled by the AppController class.
* The [appcontroller](../bookapp\fxui\src\main\java\bookapp\fxui\AppController.java) class is now used to communicate with the restful API implemented, which is responsible for HTTP messages. More on that in [ReadMerestAPI](ReadMeRestapi.md).

## Technologies Used

* JavaFX
  * JavaFX is a part of the Java Development Kit and is a library that allows you to create graphical interfaces for both desktop and mobile applications using Java.
* Scene Builder
  * Scene Builder is an application that makes it easier to create the graphical user interface of a JavaFX app. It was used to create "Mainwindow.fxml," but it is not required to run Bookapp.
* Restful API
  * We have introduced a Restful API implementation to allow the application to work remotely.

## Functionality

* The module is launched from the BookApp class, which reads and opens "Mainwindow.fxml" as a graphical interface.
  * ```start(Stage stage)```
    * Locates "Mainwindow.fxml" in the resources directory and opens it with JavaFX.
  * Most of the apps logic is in the **core**- and **restapi**-module, but the **fxui**-module links these together.
* The AppController class is associated with Mainwindow.fxml and is responsible for all interactivity with the user through the graphical interface.
* The RemoteApiAccess class handles HTTP requests and ensures that the graphical interface reflects the data saved by the **restapi**-module.

## Screenshots

![Showing the login page](/docs/imgs/logInPage.png)
![Showing the main page before review](/docs/imgs/mainpagePreReview.png)
![Showing the main page after review](/docs/imgs/mainpagePostReview.png)