# 📒 AddressBookApp

> A Spring Boot based Java application developed using Test-Driven Development (TDD) to progressively design and implement a digital Address Book system. The project emphasizes incremental development, layered architecture, and progressive feature expansion to build a scalable and maintainable contact management system.

### 📖 Overview

- Modular Spring Boot project focused on modelling and managing Address Book contacts with structured storage and retrieval capabilities.
- Organized around incremental Use Cases evolving from basic contact creation to advanced features such as search, sorting, persistence, and database integration.
- Emphasizes clean architecture using Controller–Service–Repository layers along with progressive feature additions.
- Focuses on maintainability, testability, and scalable project structure as the application grows.

---

### ✅ Implemented Features

> _Features will be added here as Use Cases are implemented._

- 🧩 **UC1 – Create Contact :**
  - Introduces the core **Contact domain model** representing an individual entry in the Address Book.
  - Defines essential contact attributes including **first name, last name, address, city, state, zip, phone number, and email**.
  - Establishes the foundational data structure that all future Address Book operations will manipulate.

  **Purpose**
  - To define a structured representation of a contact within the Address Book system.
  - To provide the base entity required for adding, editing, searching, and persisting contact information in later use cases.

  **Implementation**
  - Implemented a `Contact` model class within the **model layer** of the application.
  - Encapsulates all contact fields with appropriate constructors and accessor methods.
  - Added **unit tests (`ContactTest`)** to validate object creation and ensure correctness of stored contact attributes.
  - Establishes the foundational domain object used by future **service, repository, and controller layers**.

---

- 🧩 **UC2 – Add Contact to Address Book :**
  - Introduces the ability to add contacts to an Address Book using a REST API.
  - Establishes the basic service architecture required to manage contacts within multiple Address Books.

  **Purpose**
  - Enable the system to store contacts inside an Address Book.
  - Provide a backend API to add contact details such as first name, last name, address, city, state, zip, phone number, and email.

  **Implementation**
  - Implemented an `AddressBook` model that maintains a `List<Contact>` representing stored contacts.
  - Created `AddressBookService` to manage Address Books using a `Map<String, AddressBook>`.
  - Automatically creates an Address Book if it does not already exist.
  - Implemented `AddressBookController` exposing the REST endpoint:
    ```
    POST /addressbooks/{name}/contacts
    ```
  - Added unit tests (`AddressBookServiceTest`) covering contact addition, automatic Address Book creation, and handling multiple contacts.
---

- 🧩 **UC3 – Edit Existing Contact :**
  - Introduces the ability to update an existing contact in an Address Book through a REST API.
  - Enables modification of stored contact details while preserving the contact identity within the Address Book.

  **Purpose**
  - Allow users to update contact information such as address, city, state, zip, phone number, and email.
  - Provide a mechanism to locate a contact using first name and last name within a specific Address Book.

  **Implementation**
  - Added an `updateContact()` method in `AddressBookService` to locate a contact by `firstName` and `lastName` and update the contact details.
  - Implemented a REST endpoint in `AddressBookController`:
    ```
    PUT /addressbooks/{bookName}/contacts
    ```
  - The endpoint accepts `firstName` and `lastName` as query parameters and updated contact data in the request body.
  - Added unit tests to verify successful contact updates and handling of non-existing contacts or missing Address Books.

---

- 🧩 **UC4 – Delete Contact :**
  - Introduces the ability to remove an existing contact from an Address Book using a REST API.
  - Enables management of stored contacts by allowing deletion based on contact identity.

  **Purpose**
  - Allow users to delete a contact from an Address Book using the person's first name and last name.
  - Maintain accurate and up-to-date contact records within the system.

  **Implementation**
  - Implemented a `deleteContact()` method in `AddressBookService` to locate and remove a contact from the `List<Contact>` using `removeIf()`.
  - Added a REST endpoint in `AddressBookController`:
    ```
    DELETE /addressbooks/{bookName}/contacts
    ```
  - The endpoint accepts `firstName` and `lastName` as query parameters to identify the contact to be deleted.
  - Added unit tests verifying successful deletion, handling of non-existing contacts, and cases where the Address Book is not found.

---

- 🧩 **UC5 – Add Multiple Contacts :**
  - Extends the Address Book to support storing and managing multiple contacts using Java collection classes.
  - Enables retrieval of all contacts within a specific Address Book.

  **Purpose**
  - Allow the Address Book to store multiple contact entries instead of a single contact.
  - Provide an API to retrieve all contacts associated with a particular Address Book.

  **Implementation**
  - Utilized `List<Contact>` inside the `AddressBook` model to maintain multiple contacts.
  - Implemented a `getContacts()` method in `AddressBookService` to retrieve all contacts for a given Address Book.
  - Added a REST endpoint in `AddressBookController`:
    ```
    GET /addressbooks/{bookName}/contacts
    ```
  - Added unit tests to verify handling of multiple contacts, empty contact lists, duplicate entries, large datasets, and multiple Address Books.

---

- 🧩 **UC6 – Multiple AddressBooks :**
  - Extends the system to support managing multiple Address Books simultaneously.
  - Each Address Book is uniquely identified by a name and maintained independently.

  **Purpose**
  - Allow the system to organize contacts across multiple Address Books such as personal, work, or family.
  - Ensure contacts are isolated within their respective Address Books.

  **Implementation**
  - Refactored the service layer to manage Address Books using a `Map<String, AddressBook>`.
  - Implemented service methods to create new Address Books and retrieve existing ones.
  - Added REST endpoints in `AddressBookController`:
    ```
    POST /addressbooks/{name}
    GET /addressbooks
    ```
  - Added unit tests to verify creation of multiple Address Books, prevention of duplicate creation, and isolation of contacts between Address Books.

---

- 🧩 **UC7 – Prevent Duplicate Contacts :**
  - Enhances the Address Book system by preventing duplicate contact entries within the same Address Book.
  - Ensures that each contact is uniquely identified by their first name and last name.

  **Purpose**
  - Maintain data integrity by avoiding duplicate contact records.
  - Ensure that the same person cannot be added multiple times within a single Address Book.

  **Implementation**
  - Added validation logic in `AddressBookService` to check existing contacts before adding a new entry.
  - Implemented duplicate detection using Java Streams with `anyMatch()` to verify whether a contact with the same `firstName` and `lastName` already exists.
  - Throws an exception if a duplicate contact is detected, preventing the entry from being added.
  - Added unit tests to verify duplicate detection, successful addition of unique contacts, and allowing identical contacts in different Address Books.

---

- 🧩 **UC8 – View Persons by City or State :**  
  _Pending implementation._

- 🧩 **UC9 – Count Contacts by City or State :**  
  _Pending implementation._

- 🧩 **UC10 – Sort Contacts Alphabetically :**  
  _Pending implementation._

- 🧩 **UC11 – Sort Contacts by City, State, or Zip :**  
  _Pending implementation._

- 🧩 **UC12 – Write Address Book to File :**  
  _Pending implementation._

- 🧩 **UC13 – Read Address Book from File :**  
  _Pending implementation._

- 🧩 **UC14 – Count Contacts in File :**  
  _Pending implementation._

- 🧩 **UC15 – Write Contacts to CSV File :**  
  _Pending implementation._

- 🧩 **UC16 – Read Contacts from CSV File :**  
  _Pending implementation._

- 🧩 **UC17 – Write Contacts to JSON File :**  
  _Pending implementation._

- 🧩 **UC18 – Read Contacts from JSON File :**  
  _Pending implementation._

- 🧩 **UC19 – Add Contacts Using Threads :**  
  _Pending implementation._

- 🧩 **UC20 – Measure Time for Threaded Contact Addition :**  
  _Pending implementation._

- 🧩 **UC21 – Add Multiple Contacts Using Thread Pools :**  
  _Pending implementation._

- 🧩 **UC22 – Measure Thread Pool Performance :**  
  _Pending implementation._

- 🧩 **UC23 – Store Address Book in Database :**  
  _Pending implementation._

- 🧩 **UC24 – Retrieve Contacts from Database :**  
  _Pending implementation._

- 🧩 **UC25 – Update Contact in Database :**  
  _Pending implementation._

---

### 🧰 Tech Stack

- **Java 17+** — core programming language  
- **Spring Boot** — application framework and REST API support  
- **Maven** — build automation and dependency management  
- **JUnit 5** — unit testing framework supporting TDD workflow  
- **Mockito** — mocking framework for service layer testing  

---

### ▶️ Build / Run

- Build the project:
```
mvnw clean install
```

- Run tests:
```
mvnw test
```

- Run the Spring Boot application:
```
mvnw spring-boot:run
```

- Or run the application using git bash:
```
./mvnw spring-boot:run
```
---

### 📂 Project Structure

```

📦 AddressBookApp
│
├── 📁 .git
├── 📁 .mvn
│
├── 📁 src
│   │
│   ├── 📁 main
│   │   │
│   │   ├── 📁 java
│   │   │   └── 📁 com
│   │   │       └── 📁 addressbook
│   │   │           │
│   │   │           ├── 📁 controller
│   │   │           │   └── 📄 AddressBookController.java
│   │   │           │
│   │   │           ├── 📁 dto
│   │   │           │
│   │   │           ├── 📁 model
│   │   │           │   ├── 📄 Contact.java
│   │   │           │   └── 📄 AddressBook.java
│   │   │           │
│   │   │           ├── 📁 repository
│   │   │           │
│   │   │           ├── 📁 service
│   │   │           │   └── 📄 AddressBookService.java
│   │   │           │
│   │   │           ├── 📁 storage
│   │   │           │
│   │   │           ├── 📁 threads
│   │   │           │
│   │   │           ├── 📁 util
│   │   │           │
│   │   │           └── 📄 AddressBookApplication.java
│   │   │
│   │   └── 📁 resources
│   │       └── 📄 application.properties
│   │
│   └── 📁 test
│       └── 📁 java
│           └── 📁 com
│               └── 📁 addressbook
│                   │
│                   ├── 📄 AddressBookApplicationTests.java
│                   ├── 📄 ContactTest.java
│                   └── 📄 AddressBookServiceTest.java
│
├── ⚙️ pom.xml
│
├── 📄 mvnw
├── 📄 mvnw.cmd
│
├── 📄 .gitattributes
├── 🚫 .gitignore
│
├── 📜 LICENSE
└── 📘 README.md
```

---

### ⚙️ Development Approach

> This project follows an incremental **Test-Driven Development (TDD)** workflow:

- Tests are written first to define expected behaviour.
- Implementation code is developed to satisfy the tests.
- Each Use Case introduces new functionality in controlled steps.
- Existing behaviour is preserved through continuous refactoring.
- The system evolves through layered architecture using Controller, Service, and Repository separation.
- Progressive enhancements introduce search, sorting, persistence, multithreading, and database support.

---

### 📄 License

> This project is licensed under the MIT License.

---

### 👨‍💻 Author

**Abhishek Puri Goswami**  
_Java developer focused on clean architecture, object-oriented programming, and incremental software development using Test-Driven Development._

---

<div align="center">
✨ Incrementally developed using Test-Driven Development and progressive feature expansion.
</div>
