# Release 2 for IT1901 - Group 49

## Table of contents

1. Introduction
2. Structure and Functionality
3. Reflections
4. Summary

### 1. Introduction

Release 2 introduces several changes to the project compared to Release 1. We have restructured the project to be more optimal, with better testing coverage at a deeper level. We have introduced new tests and methods for debugging the code, along with a Jackson file storage system. Through experience and internal discussions, we have established conventions and coding routines that have proven effective. We have utilized GitLab's functionality further and have coded in pairs. This release file will address the overarching changes and reflections on the work done. For more specific information on running and configurations, please refer to the [readme.md](../readme.md).

### 2. Structure and Functionality

The project for release 1 had a single-module configuration where file handling, GUI, and domain logic coexisted. We had a pom.xml file that handled all configurations. For release 2, we have introduced a three-module structure. We have now separated domain logic, GUI, and file handling into three different modules that communicate; core, fxui, and persistence. A parent pom.xml file defines "rules" for the entire project, while more specific configurations exist within each module's pom.xml file.

We have added additional functionality that now forces users to enter a name if they want to rate a book, along with more sophisticated file handling through a Jackson library with JSON format. This allows us to store input from the program more efficiently. For more detailed information on this, please see [Readme-persistens](ReadMePersistence.md).

The project is also configured for Eclipse Che.

#### User Stories
We have also included several user stories to further describe the purpose of the application and illustrate functionality. These can be found in [brukerhistorier](brukerhistorier.md). For release 2, these are US2 and US3.
  
### 3. Reflections

The freedom around the choices we made for this release lies primarily in work habits and file handling systems. To start with the simplest, we have chosen a document metaphor storage method. This is largely about making it clear to users when their input is saved. More about this in [Readme-persistens](ReadMePersistence.md). 

Through the work done, we have found a structure based on the Scrum technique with some modifications. We started with weekly meetings, where we worked on individual tasks between meetings. However, towards the end of the submission, there was a need for more collaboration on issues, and we started pair programming. As the project is configured to work on our individual computers, we have not utilized Eclipse Che through cloud development. Should we wish to do so, the project is set up to work in Eclipse Che.

### 4. Summary

Release 2 introduces a new structure to the project with expanded functionality. There are now descriptions of the modules in their respective readme files. We now have a working rating system that allows users to interact with the application, and you can see average ratings of the books in the application.

The efficiency of the group has been good, and we have adapted by changing work methods where needed. We have followed conventions agreed upon collectively using GitLab. Therefore, navigating the project should be straightforward.