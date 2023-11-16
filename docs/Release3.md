# Release 3 IT1901 - Group 49


## Table of contents


1. Introduction
2. Architecture
3. Functionality
4. Working habits
5. Relflection and Summary


### 1. Introduction
In release 3, significant modifications have been implemented to transition the project towards utilizing a RESTful API server. The core aspect of this alteration involves the adaption of the persistence module to align with REST API standards. The persistence module is changed to be compatible with a rest API. This new module is named, restapi. The introduction of the 'restapi' module entails a reconfiguration of data handling and communication protocols to adhere strictly to the principles and specifications of a RESTful API. More about changes in the following sections.

### 2. Architecture
The persistence module has been removed, and its functionality has been implemented into a new module called restapi. This module is responsible for the server side of the application.

Besides retaining the persistence module's previous functionality, the new module facilitates communication between the frontend and backend through HTTP. Read more about this in the [readMeRestApi.md.] (docs/ReadMeRestapi.md)

Additionaly we have implemented a wrapper class for FileHandler called FileHandlerService to mitigate the fact that FileHandler is a static class. By implementing this wrapper, we can more easily conduct integration tests as it allows us to create a "MockBean" version of the wrapper class without altering FileHandler's functionality.


### 3. Functionality
New functionality in this module includes the ability for users to sort books (US4) and delete reviews (US5), see [userstories.md] (docs/userstories.md). To support these changes, we introduced another class called BookComparator, responsible for comparing books in regards to rating, title, and author name.

A requirement for this release was the implementation of a RestAPI server, enabling the UI to communicate with the backend side of the application through HTTP requests. These changes were implemented using the Spring framework, read more about this in the [readMeRestApi.md.] (docs/ReadMeRestapi.md).


### 4. Working habits
The workflow remains consistent with previous practices, involving working branches where developers address their assigned issues. Branches may encompass multiple issues if they relate to the same files or are interconnected. It's up to each developer to assess issue correlation.

Weekly meetings are held where developers present their progress before merging their work.

A significant change is the introduction of a development branch for merging work before the final release, instead of direct merges into the master branch. This strategy keeps the prior release stable until the development branch is ready.This approach minimizes the risk of introducing breaking changes to the master branch.

Another change is the shift to English for commit messages, code comments, and documentation, replacing Norwegian. This decision was based on the team being more comfortable writing in English, additionaly if the team were to include someone who didn't speak Norwegian our documentation wouldn't have the same value. 

### 5. Reflection and Summary
This release demonstrates a robust approach to software development, adapting to modern architectural standards while maintaining a stable development environment. The move towards a RESTful API architecture not only aligns with current industry practices but also enhances the application's scalability and maintainability. 

The adoption of a development branch before final releases shows a mature approach to version control and risk management. It ensures that the master branch remains stable and reduces the likelihood of introducing breaking changes.

Furthermore, the decision to switch to English for all documentation and communication reflects a thoughtful consideration for global collaboration and inclusivity. It signifies the team's readiness to operate in a diverse, international environment, which is crucial in the current global software development landscape.

Overall, these changes illustrate a forward-thinking and adaptive approach to software development, positioning the project well for future growth and collaboration.
