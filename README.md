# PetCareManager

A Java web application to manage a dog's care: profiles, vet and grooming appointments, daily care logs, expenses and reminders. Built with Servlets, JSP and JDBC on a MySQL database, following the MVC and DAO patterns.

## Features
- **Home dashboard** with quick links to every module
- **Dogs:** add, view, edit and delete dog profiles (name, breed, age, gender, weight, colour)
- **Appointments:** schedule Vet, Grooming, Training or other visits with date, time, status and notes
- **Care Logs:** record activities such as Walk, Feed, Bath, Medicine and Play
- **Expenses:** track spending by category (Food, Vet, Grooming, Medicine, Others)
- **Reminders:** set reminders for vaccinations, vet visits and medicine with a PENDING, DONE or MISSED status
- Full create, read, update and delete (CRUD) in all five modules

## Tech Stack
| Layer | Technology |
|---|---|
| Language | Java |
| Web | Servlets, JSP |
| Database access | JDBC (PreparedStatement) |
| Database | MySQL |
| UI | Bootstrap 5 |
| IDE | Eclipse |

## Project Structure
```
src/main
├── java
│   ├── model      # Dog, Appointment, CareLog, Expense, Reminder
│   ├── dao        # database operations for each model
│   ├── servlet    # controllers (one per module)
│   └── util       # DBConnection
└── webapp
    ├── index.jsp  # home dashboard
    ├── images
    └── WEB-INF    # list and form JSP views, MySQL connector jar
```

## How to Run
1. Install Java (JDK), MySQL and Apache Tomcat (or another servlet container).
2. Create the database and tables by running `schema.sql` in MySQL.
3. Open `src/main/java/util/DBConnection.java` and set your MySQL username and password.
4. Import the project into Eclipse (Dynamic Web Project) and add it to your Tomcat server.
5. Start the server and open `http://localhost:8080/PetCareManager/`.

## Planned Improvements
- User login and multiple owners (the owner is currently fixed)
- Input validation and escaping of output
- Delete actions using POST requests

## Author
Reethi K B
