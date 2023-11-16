# Bookratingapplication - Project

The project is intended to be a book-rating application.

As a user you are supposed to be able to logg in so that you can access functions in the app. By [realease 3](docs\Release3.md) we now have expanded our app to not work locally, but remotely. This implies that we need to have a server running for the application to work. The application is now also presented as a "shippable product". See [release 3](docs\Release3.md) for more about how this works. There is also new functionality and as a user we can now sort the books by author, rating or title as well as delete reviews. Our focus remains the same as for release 2 which is still on simple functionality with high code quality, efficient cooperation and gitlab synchronizations.


## Structure

The structure of the project has been updated from release 2. We no longer have a module for persistence handeling, but we now have a module called [restAPI](bookapp\restapi) where all of the back-end code is managed. This clearly and cleanly seperates the client side of the application from the server side of the application and allows for an easier implementation of the restful API. The project still resides in the **bookapp**-foler and is run through the parent-pom file. More on this under the section *Building and running the application*. 

Furthermore, we have our modules, which are as follows: **core**, **fxui**, and **restapi**. The **core**-module is only visable to the server side of the application through the **restapi** module and hanles all the core logic of the app. The **restapi**-module is responsible for responding to client requests and ensures correct handeling of the logic domain. The **fxui**-module is now updated to not work locally, but remotely through a restful API. This means that it now has restful API logic that allows for HTTP communication with the server holding the back-end functionality. You can read more on the respective modules in the following readme's:

* [core](docs\ReadMecore.md)
* [fxui](docs\ReadMefxui.md)
* [persistence](docs\ReadMerestapi.md)

These module POM files specify additional necessary additions and dependencies. More details can be found in each module's respective **Readme.md** file.

Our **Core**-module is still the cornerstone of the project, but now only the **restapi**-module is dependent on it for the app to run. Since **fxui**-module is now configured to run remotely, there is no need for dependencies to neither the **restapi**- or the **core**-module. The **restapi**-module is responsible for handeling the logic correctly and is naturally dependent on the **core**-module.


## Configuration requirements

The application uses Maven for building and running and is configured to run with [Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2349/gr2349?new). It's important that the project runs on **java 20.0.2-oracle** and **Maven 3.9.4**. To achieve this, we use SDKMAN to change the Java version. The Java and Maven versions can be obtained by running the following commands:

To change to the correct Java version, you can run the command:

`sdk install java 20.0.2-oracle`

This should be done in the integrated terminal and should, in addition to updating Java, update to the latest version of Maven.

__PlantUML__ - To view the diagram describing the project's dependencies, you need the extension *PlantUML by jebbs*.

## Building and Running the Application

There are now two ways to run the project. It can be run through an IDE like VScode or, as the project is configured as a shipable product, it can be run through an app. The following sections describes the procedure to run both ways respectively.

### Through an IDE 

Following section is written under the assumption that you already have the correct java and maven versions installed.
The first thing we need to do is host the server on our local network. To do this, find the terminal and navigate to the **bookapp\restapi**-folder and run:

`mvn install`
`mvn spring-boot:run`

The server is now hosted on the local network and can be accessed through the IPv4 address. In the current version of the project, the local IPv4 addressed is set to *localhost*, but this can be changed to the local IPv4 address and still work. This is done on line 29 in [BookappModelController](bookapp\fxui\src\main\java\bookapp\fxui\RemoteBookappModelAccess.java).

Now you can open a new terminal and navigate back to the **bookapp**-folder where the entire project resides and enter the following commands:

`mvn clean`
`mvn install`

This will install the necessary files to the project and can proceed. Once the installation is complete you can navigate to the **fxui**-module where the UI is and run:

`mvn javafx:run`

to start the application. It's important that you have executed the above-mentioned commands with the correct Java version for this to work. When running the project through Eclipse Che, you must navigate to *ENDPOINTS* -> *6080-tcp-desktop-ui(6080/http)* -> copy the URL -> open a new tab where the application can be run in a VM of the environment.

### Through the app

We still need to host the server on our app and this is done the same way as for IDEs. Please view the above section for more information.

Once the server is running we still need maven installed to run our compilation commands. This means you need to enter:

`mvn clean`
`mvn install`

in the terminal under the **bookapp**-folder for the next part to work. In the terminal you can add the following commands:

`mvn javafx:jlink -f ./fxui/pom.xml`
`mvn jpackage:jpackage -f ./fxui/pom.xml`

and you should se a folder-window open. From there you should drag the java app that is shown into the application folder. Once done, you should be able to run the program by launching the app. 

## Dependencies og plugins

The project uses a variety of dependencies. These are:

1. Javafx; for GUI
2. Junit; for testing
3. TextFX; for testing JavaFX
4. Jackson; for file handling 

Additionally, we also use several plugins to achieve all the functionality we desire:

1. Necessary Maven plugins; for compilation and execution
2. Jacoco; for coverage reports
3. Checkstyle and Spotbugs; to ensure code quality
5. Jlink and jpackage; for the preparation of the shippable product.

Configuration for the tools that ensure code quality exists in the [config](bookapp\config) folder, and the content is based on a standard format for respective files.

The code is tested according to Google's official Java code setup and style guidelines, known as the "Google Java Style Guide" for Checkstyle. Furthermore, for Spotbugs, we use exclude.xml. This checks, among other things, for variables being compared to themselves and object exposure.

## Work Habits, Workflow, and Code Quality

This section provides an overview of key choices and approaches that have been made during the project's development related to work habits, workflow, and code quality in accordance with Release 3.

## Work Habits

__Agile Development__ - We follow an agile development methodology throughout the project. This allows us to adapt to changes and deliver regular updates. This methodology includes regular sprints to improve the process. Our sprints have generally been through 1 week sprints, but when necessary we have done more regular meetings. The team uses regular and scheduled meetings during development. To maintain good quality and low redundancy, team members also understand that more urgent meetings may occur.

__Communication__ -  The group uses "Facebook Messenger" as the main channel for communication between the scheduled meetings. This allows us to communicate in brief and schedule meetings and work hours more frequently, as the project may present different challenges that require meetings outside the regular ones.

## Workflow

__GitLab__ - We use GitLab for version control, and all changes are made in separate branches. We utilize the pull and push features to consolidate changes into the master branch. This provides a clear approach to code changes and enables necessary code reviews. For release 3, we branched out of the master to a main *release 3*-branch. The ide is that once the release is done, we merge it to the master branch for a more readable history in the master branch. This release we treated this branch as the master and merged to and from the branch.

__Task Management__ - In GitLab, we use "Issues" and "Tasks" for task management. These are created with a "Label" and a "Milestone." Labels indicate which part of the project the issue belongs to. For example, an issue regarding Readme.md work is linked to the "Documentation/Project Structure" label. Milestones specify which part of the project the issue is being worked on. For example, assignment 1, 2, 3, or 4. During meetings, we agree on the tasks each member should complete by the next meeting, and issues (with relevant information) are created during these meetings. As a result, this gives us a clear overview of progress, and we can easily backtrack in the project if necessary. This supports our agile development methodology. 

We also introduced the issue board to our working routines. Issues and tasks are created as we see fit before they are moved from the *Open* to the *Doing* sections before being moved to the *Closed* section when done. This allows us to see the flow of the project clearer and gives us a better indication of what issues other members are working on. 

## Code Quality

__Testing__ - We have adopted an approach where the main functions of the app are tested. 

__Test Coverage__ - The group uses Jacoco as a means to measure test coverage of the code. Additionally, we have now added Checkstyle and Spotbugs to better maintain code quality. We have tried to ensure high coverage rates of a minimum 70%. 

__Checkstyle__ - We use Checkstyle to check code style. This makes it easier to keep the code structured in terms of style.

__Spotbugs__ - Spotbugs is used to identify where we have bugs in the program more easily. This, of course, makes debugging easier.