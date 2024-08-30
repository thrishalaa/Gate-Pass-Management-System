# Gate Pass Management System

## Description
The Gate Pass Management System is a console-based application designed to facilitate the management of gate pass requests within an educational institution. It allows users to submit gate pass requests and administrators to process those requests.

## Features
* User Gate Pass Submission: Users can submit gate pass requests by providing their name, roll number, year, class, section, and reason for the gate pass.
* Admin Actions: Administrators can view pending gate pass requests, approve or reject them, and view the status of processed requests.
* Database Integration: The system uses a MySQL database to store information about pending, accepted, and rejected gate pass requests.
* Timestamp Tracking: Timestamps are recorded for each gate pass request, indicating when the request was submitted, accepted, or rejected.
* Data Validation: Roll number validation is implemented to ensure correct formatting before accepting gate pass requests.
 
## Technologies Used
* Java: Core language used for development.
* MySQL: Database management system for storing gate pass request data.
* JDBC: Java Database Connectivity for interacting with the MySQL database.
* Git: Version control system for managing project code.

## Usage
To run the Gate Pass Management System, follow these steps:

1. Ensure you have Java and MySQL installed on your system.
2. Clone the repository to your local machine.
3. Set up the MySQL database by executing the SQL scripts provided in the database directory.
4. Compile the Java files using a Java compiler.
5. Run the Main class to start the console-based application.
Follow the prompts to submit gate pass requests or process requests as an administrator.
