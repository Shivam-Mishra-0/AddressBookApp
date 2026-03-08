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
  - Implemented `AddressBookMain` exposing the REST endpoint:
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
  - Implemented a REST endpoint in `AddressBookMain`:
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
  - Added a REST endpoint in `AddressBookMain`:
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
  - Added a REST endpoint in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
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
  - Added a REST endpoint in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
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
  - Added REST endpoints in `AddressBookMain`:
    ```
    POST /addressbooks/{bookName}/save-csv
    GET /addressbooks/load-csv
    ```
  - Added unit tests to validate CSV file creation, reading contacts from CSV, handling multiple contacts, and empty CSV file scenarios.

---

- 🧩 **UC15 – JSON File Support :**
  - Introduces JSON-based persistence for Address Book contacts using the GSON library.
  - Enables exporting contacts to JSON files and loading them back into the system.

  **Purpose**
  - Allow contacts to be stored and retrieved in a structured JSON format.
  - Provide a widely used and flexible format for data storage and exchange.

  **Implementation**
  - Created a utility class `AddressBookJSONIO` to handle JSON serialization and deserialization using the GSON library.
  - Implemented functionality to save contacts from an Address Book to a JSON file and load contacts from a JSON file into memory.
  - Added REST endpoints in `AddressBookMain`:
    ```
    POST /addressbookapp/{bookName}/save-json
    GET /addressbookapp/load-json
    ```
  - Added unit tests to validate JSON file creation, reading JSON files, handling multiple contacts, and empty JSON file scenarios.

---

- 🧩 **UC16 – Retrieve Contacts from Database & Storage Layer Refactor :**
  - Introduces database integration to retrieve contacts using JDBC and refactors the storage design to support multiple persistence formats through a storage abstraction layer.

  **Purpose**
  - Enable retrieval of contacts stored in a relational database.
  - Decouple storage logic from business logic to support multiple storage formats such as file, CSV, and JSON.

  **Implementation**
  - Externalized database configuration in `application.properties`, allowing Spring Boot to automatically configure a `DataSource`.
  - Implemented a `DBConnection` to execute SQL queries and map database rows to `Contact` objects.
  - Added a REST endpoint in `AddressBookMain`:
    ```
    GET /addressbooks/db/contacts
    ````
  - Introduced a storage abstraction using the `ContactStorage` interface defining common operations for saving and loading contacts.
  - Implemented three storage strategies:
    - `FileStorage` – Handles standard Java File IO operations.
    - `CSVStorage` – Supports CSV persistence using the OpenCSV library.
    - `JSONStorage` – Supports JSON serialization and deserialization using the Gson library.
  - Added integration tests using `@SpringBootTest` to validate database connectivity and data retrieval.

---

- 🧩 **UC17 – Update Contact Information in Database :**
  - Extends the database integration to support updating existing contact information stored in the database.
  - Enables modification of contact details through JDBC update operations.

  **Purpose**
  - Allow the system to update stored contact records in the database.
  - Provide a mechanism to modify specific contact attributes such as city based on the person’s name.

  **Implementation**
  - Extended the `DBConnection` to execute an SQL `UPDATE` query using JDBC `PreparedStatement`.
  - Implemented logic to update the city of a contact identified by the person's name.
  - Added a REST endpoint in `AddressBookController`:
    ```
    PUT /addressbooks/db/update-city
    ```
  - Added integration tests to verify successful database updates and ensure correct JDBC interaction.

  **Outcome**
  - The AddressBook system can now update existing contact records directly in the database using JDBC update operations.

---

- 🧩 **UC18 – Retrieve Contacts by Date Range :**
  - Enhances database querying capabilities to retrieve contacts created within a specified date range.
  - Enables filtering of contact records based on their creation timestamp.

  **Purpose**
  - Allow users to fetch contacts added to the Address Book during a specific time period.
  - Provide date-based filtering for better data analysis and retrieval.

  **Implementation**
  - Added a `date_added` column in the database to track when contacts are created.
  - Extended `DBConnection` to execute a JDBC query using `PreparedStatement` that retrieves contacts whose `date_added` values fall between two given dates.
  - Added a REST endpoint in `AddressBookMain`:
    ```
    GET /addressbooks/db/contacts-by-date
    ```
  - Added unit tests to validate correct database filtering and retrieval of contacts within the specified date range.
  
  **Outcome**
  - The AddressBook system can now retrieve contacts from the database based on a given date range using JDBC queries.

---

---

- 🧩 **UC19 – Retrieve Contact Counts by City or State from Database :**
  - Enhances the AddressBook system to retrieve and compute the number of contacts stored in the database grouped by city and state, while introducing DTO-based data transfer for API responses.

  **Purpose**
  - Allow the application to analyze contact distribution based on geographic locations.
  - Provide aggregated statistics showing how many contacts belong to each city or state.
  - Improve API design by using DTO objects instead of exposing internal domain models.

  **Implementation**
  - Reused the existing JDBC database connectivity and `DBConnection` to retrieve contacts from the database.
  - Implemented grouping and counting logic in the service layer using Java Streams with `groupingBy()` and `counting()`.
  - Added REST endpoints in `AddressBookMain`:
  ```
  GET /addressbooks/db/count/city
  GET /addressbooks/db/count/state
  ```
  - Introduced `ContactDTO` to represent API request and response data.
  - Updated controller methods to return DTO objects while the service layer handles conversion between DTOs and model entities.
  - Added unit tests to validate correct grouping, counting, and DTO-based responses.

  **Outcome**
  - The AddressBook system can now compute and display the number of contacts stored in the database for each city or state.
  - The API now uses a DTO layer, improving separation between external API data and internal domain models.

---

- 🧩 **UC20 – Add Contact to Database Using JDBC :**
  - Enhances the AddressBook system to support inserting new contact records directly into the database using JDBC.

  **Purpose**
  - Allow the application to persist newly created contacts in the database.
  - Ensure reliable and secure database insertion with proper transaction handling.

  **Implementation**
  - Extended `DBConnection` with an SQL `INSERT` query implemented using JDBC `PreparedStatement`.
  - Implemented transaction management using `setAutoCommit(false)`, `commit()`, and `rollback()` to ensure database consistency.
  - Added a service method in `AddressBookDBService` to handle contact insertion through the repository layer.
  - Added a REST endpoint in `AddressBookMain`:
  ```
  POST /addressbooks/db/add-contact
  ```
  - Added unit tests to verify successful database insertion and correct interaction with the repository layer.

  **Outcome**
  - The AddressBook system can now insert new contact records into the database using JDBC with proper transaction management and layered architecture support.

---

- 🧩 **UC21 – Add Multiple Contacts to Database Using Multithreading :**
  - Enhances the AddressBook system to support inserting multiple contact records into the database concurrently using multithreading.

  **Purpose**
  - Improve performance when inserting multiple contacts by executing database operations concurrently.
  - Demonstrate the use of Java multithreading for handling parallel database operations.

  **Implementation**
  - Created a `threads` package and introduced an `AddContactTask` class implementing `Runnable` to handle individual contact insertion tasks.
  - Reused the existing `addContact()` method in `DBConnection` to perform JDBC database insertion.
  - Implemented a service method in `AddressBookDBService` to create and manage multiple threads, each responsible for inserting one contact into the database.
  - Added a REST endpoint in `AddressBookMain`:
  ```
  POST /addressbooks/db/add-multiple
  ```
  - Added tests to verify successful insertion of multiple contacts and ensure all threads complete execution before returning the response.

  **Outcome**
  - The AddressBook system can now insert multiple contacts into the database concurrently using multithreading, enabling efficient batch insertion operations.

---

- 🧩 **UC22 – Read Contacts from JSON Server using REST Assured :**
  - Enhances the AddressBook system to retrieve contact records from an external JSON server using REST API calls executed through automated tests.

  **Purpose**
  - Enable the application to integrate with an external REST service providing contact data.
  - Demonstrate REST API testing using the REST Assured library.

  **Implementation**
  - Installed and configured **json-server** to simulate a REST API using a `db.json` file containing sample contact records.
  - Started the JSON server on **port 3000**, exposing REST endpoints such as:
    ```
    GET /contacts
    ```
  - Added the **REST Assured dependency** to the project to perform HTTP requests within JUnit tests.
  - Implemented a test case that sends a **GET request** to the JSON server endpoint and validates the response status code and returned contact data.

  **Outcome**
  - The AddressBook system can now retrieve contact data from an external JSON server using REST API calls executed from automated tests, enabling integration testing of external REST services.

---

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
