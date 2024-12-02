# Movie Theater Management Application-ACMEPLEX

## Description
This is a **Spring Boot Movie Theater Management Application- Acmeplex** that enables users to browse movies, view theater details, and make bookings, make payment and get ticket and receipt confimation. It uses a MySQL database for data persistence.

---

## Database Configuration

To run the application, ensure the following database schema and data are properly set up in MySQL.

# How to Run This Project

## Steps to Get Started

1. **Clone the Repository**
   - Clone this repository to your local system:
     ```bash
     git clone https://github.com/OMPATEL20/ENSF--614--ACMEPLEX.git
     ```

2. **Setup the Database**
   - Open your MySQL database.
   - Execute the script `QUERIES FOR ACMEPLEX`, which is available in the project structure, to set up the necessary tables and data.
 Also Open the file ENSF_614_Group-15\ENSF--614--ACMEPLEX\src\main\resources\application.properties and enter your Db server details. For db.user and db.password
3. **Open the Project**
   - Open the project in your preferred IDE (e.g., Visual Studio Code or Spring Tool Suite).

4. **Navigate to the Project Directory**
   - In the terminal, navigate to the project directory:
     ```bash
     cd E*
     ```

5. **Run the Application**
   - Run the Spring Boot application using Maven:
     ```bash
     mvn clean install
     
     java -jar target/Acmeplex-0.0.1-SNAPSHOT.jar
     ```

6. **Access the Application**
   - The application will be running locally at:
     [http://localhost:8080](http://localhost:8080)
