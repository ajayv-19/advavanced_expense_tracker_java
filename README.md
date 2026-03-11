Advanced Expense Tracker (Java)
Overview
Keeping track of spending shouldn't feel like a chore. This Advanced Expense Tracker is a sleek, desktop-based solution designed to help you take control of your finances. Built with a focus on simplicity and data visualization, it transforms raw numbers into clear, actionable insights.

Whether you're looking to cut down on coffee runs or manage a monthly budget, this tool provides the structure you need to stay organized.

Key Features
Effortless Logging: Quickly add, edit, and organize your daily expenses.

Visual Insights: Don't just look at lists—see your spending habits through dynamic charts powered by JFreeChart.

Local & Private: Your financial data stays yours. Everything is stored in a local SQLite database (expenses.db) right on your machine.

Modern Interface: Enjoy a smooth, user-friendly experience crafted with JavaFX.

Clean Architecture: Built using a client-server logic split, making the code easy to read, maintain, and scale.
 
Built With
Java: The backbone of the application.

JavaFX: For a responsive and intuitive user interface.

SQLite (java.sql): For reliable, lightweight data storage.

JFreeChart: For turning data into beautiful, easy-to-read graphs.

Project Structure
The code is organized logically to keep things tidy:

client/: Handles everything the user sees and interacts with.

database/: The "brain" that talks to your local data storage.

models/: Simple templates for how an "Expense" is defined.

server/: Manages the core logic and processing.

resources/: Holds the FXML files that define the visual layout.

Getting Started
Prerequisites
Java 11 or higher

JavaFX SDK (required to run the GUI)

JFreeChart library

Setup & Run
Grab the code:

Bash
git clone https://github.com/ajayv-19/advavanced_expense_tracker_java.git
cd advavanced_expense_tracker_java
Open in your favorite IDE:
Import the project into IntelliJ, Eclipse, or VS Code.

Configure JavaFX:
Add the JavaFX library to your project structure and include the following in your VM options:

Plaintext
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
Run:
Locate the main class in the client package and hit Run!
