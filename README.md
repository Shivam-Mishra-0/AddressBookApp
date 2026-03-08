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
  - Implemented `AddressBookConsole` exposing the REST endpoint:
    ```
    POST /addressbooks/{name}/contacts
    ```
  - Added unit tests (`ContactTest`) covering contact addition, automatic Address Book creation, and handling multiple contacts.
---

- 🧩 **UC3 – Edit Existing Contact :**
  - Introduces the ability to update an existing contact in an Address Book through a REST API.
  - Enables modification of stored contact details while preserving the contact identity within the Address Book.

  **Purpose**
  - Allow users to update contact information such as address, city, state, zip, phone number, and email.
  - Provide a mechanism to locate a contact using first name and last name within a specific Address Book.

  **Implementation**
  - Added an `updateContact()` method in `AddressBookService` to locate a contact by `firstName` and `lastName` and update the contact details.
  - Implemented a REST endpoint in `AddressBookConsole`:
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
  - Added a REST endpoint in `AddressBookConsole`:
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
  - Added a REST endpoint in `AddressBookConsole`:
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
  - Added REST endpoints in `AddressBookConsoler`:
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

- 🧩 **UC8 – Search Person by City or State :**
  - Introduces the ability to search contacts by city or state across multiple Address Books.
  - Enables efficient filtering of contacts based on location information.

  **Purpose**
  - Allow users to quickly find contacts belonging to a specific city or state.
  - Support searching across all Address Books in the system.

  **Implementation**
  - Implemented search functionality in `AddressBookService` using Java Streams.
  - Combined contacts from all Address Books and filtered them based on the specified city or state.
  - Added REST endpoints in `AddressBookConsole`:
    ```
    GET /addressbooks/search/city/{city}
    GET /addressbooks/search/state/{state}
    ```
  - Added unit tests to validate search results, case-insensitive matching, multiple results, and scenarios where no contacts are found.

---

- 🧩 **UC9 – View Persons by City or State :**
  - Introduces the ability to view contacts grouped by city or state across Address Books.
  - Organizes contacts based on location for easier viewing and analysis.

  **Purpose**
  - Allow users to see all contacts grouped by their city or state.
  - Provide a structured way to organize contacts based on location.

  **Implementation**
  - Implemented grouping logic in `AddressBookService` using Java Streams and `Collectors.groupingBy()`.
  - Contacts are grouped into a dictionary structure:
    ```
    Map<String, List<Contact>>
    ```
  - Added REST endpoints in `AddressBookConsole`:
    ```
    GET /addressbooks/view/city
    GET /addressbooks/view/state
    ```
  - Added unit tests to verify grouping functionality, handling of multiple Address Books, and scenarios with empty datasets.

---

- 🧩 **UC10 – Count Contacts by City or State :**
  - Introduces the ability to count the number of contacts grouped by city or state across Address Books.
  - Provides statistical insight into how contacts are distributed across locations.

  **Purpose**
  - Allow users to determine how many contacts belong to each city or state.
  - Provide aggregated contact statistics across all Address Books.

  **Implementation**
  - Implemented counting logic in `AddressBookService` using Java Streams with `Collectors.groupingBy()` and `Collectors.counting()`.
  - Contacts are aggregated into the structure:
    ```
    Map<String, Long>
    ```
  - Added REST endpoints in `AddressBookConsole`:
    ```
    GET /addressbooks/count/city
    GET /addressbooks/count/state
    ```
  - Added unit tests to validate counting across multiple Address Books, handling of empty datasets, and contacts belonging to different locations.

---

- 🧩 **UC11 – Sort Contacts Alphabetically by Name :**
  - Introduces the ability to sort contacts alphabetically by their first name within an Address Book.
  - Improves usability by presenting contacts in an organized and readable order.

  **Purpose**
  - Allow users to view contacts arranged alphabetically for easier navigation.
  - Provide a consistent way to display contact lists within an Address Book.

  **Implementation**
  - Implemented sorting logic in `AddressBookService` using Java Streams and `Comparator.comparing()` based on the `firstName` field.
  - Added a REST endpoint in `AddressBookConsole`:
    ```
    GET /addressbooks/{bookName}/sort/name
    ```
  - Overrode the `toString()` method in the `Contact` model to produce readable output when displaying contact entries.
  - Added unit tests to validate sorting functionality, handling of empty Address Books, and multiple contacts.

---

- 🧩 **UC12 – Sort Contacts by City, State, or Zip :**
  - Extends the sorting capability to allow contacts to be ordered by city, state, or zip code within an Address Book.
  - Provides flexible ways to organize contacts based on location attributes.

  **Purpose**
  - Allow users to view contacts sorted by city, state, or zip code.
  - Improve organization and accessibility of contact information based on geographical attributes.

  **Implementation**
  - Implemented sorting logic in `AddressBookService` using Java Streams and `Comparator.comparing()` for the fields `city`, `state`, and `zip`.
  - Added REST endpoints in `AddressBookConsole`:
    ```
    GET /addressbooks/{bookName}/sort/city
    GET /addressbooks/{bookName}/sort/state
    GET /addressbooks/{bookName}/sort/zip
    ```
  - Added unit tests to validate sorting functionality, including handling empty Address Books and single contact scenarios.

---

- 🧩 **UC13 – File IO Support :**
  - Introduces file persistence for Address Book contacts using Java File IO.
  - Enables saving contacts to a file and loading them back into memory.

  **Purpose**
  - Allow the Address Book data to be stored permanently outside application memory.
  - Enable restoring contacts from a saved file when required.

  **Implementation**
  - Created a utility class `AddressBookFileIO` to manage file operations using `BufferedWriter` and `BufferedReader`.
  - Implemented functionality to save contacts from an Address Book to a file and load contacts from a file into memory.
  - Added REST endpoints in `AddressBookConsole`:
    ```
    POST /addressbooks/{bookName}/save
    GET /addressbooks/load
    ```
  - Added unit tests to validate file creation, file reading, handling of empty files, and persistence of multiple contacts.

---

- 🧩 **UC14 – CSV File Support :**
  - Introduces structured CSV-based persistence for Address Book contacts using the OpenCSV library.
  - Enables exporting contacts to CSV files and loading them back into the system.

  **Purpose**
  - Allow contacts to be stored and retrieved using a structured CSV file format.
  - Provide a standardized and portable format for contact data storage.

  **Implementation**
  - Created a utility class `AddressBookCSVIO` to manage CSV operations using OpenCSV's `CSVReader` and `CSVWriter`.
  - Implemented functionality to save contacts from an Address Book to a CSV file and load contacts from a CSV file into memory.
  - Added REST endpoints in `AddressBookConsole`:
    ```
    POST /addressbooks/{bookName}/save-csv
    GET /addressbooks/load-csv
    ```
  - Added unit tests to validate CSV file creation, reading contacts from CSV, handling multiple contacts, and empty CSV file scenarios.

---

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
|
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

**Shivam Mishra**  
_Java developer focused on clean architecture, object-oriented programming, and incremental software development using Test-Driven Development._

---

<div align="center">
✨ Incrementally developed using Test-Driven Development and progressive feature expansion.
</div>
