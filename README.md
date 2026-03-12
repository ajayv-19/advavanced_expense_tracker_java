# Advanced Expense Tracker (Java)

## Overview
Keeping track of spending shouldn't feel like a chore. This **Advanced Expense Tracker** is a sleek desktop-based solution designed to help you take control of your finances. Built with a focus on simplicity and data visualization, it transforms raw numbers into clear, actionable insights.

Whether you're looking to cut down on coffee runs or manage a monthly budget, this tool provides the structure you need to stay organized.

---

## Key Features

### Effortless Logging
Quickly add, edit, and organize your daily expenses.

### Visual Insights
Don't just look at lists — understand your spending habits through dynamic charts powered by **JFreeChart**.

### Local & Private
Your financial data stays yours. Everything is stored in a local **SQLite database (`expenses.db`)** directly on your machine.

### Modern Interface
Enjoy a smooth and user-friendly experience built with **JavaFX**.

### Clean Architecture
The project follows a **client–server logic separation**, making the code easier to read, maintain, and scale.

---

## Built With

- **Java** — Core programming language used to build the application.
- **JavaFX** — Provides a responsive graphical user interface.
- **SQLite (java.sql)** — Lightweight local database for storing expenses.
- **JFreeChart** — Generates clear and interactive data visualizations.

---

## Project Structure

The project is organized into the following directories:

```
client/      # Handles everything the user sees and interacts with
database/    # Manages communication with the SQLite database
models/      # Defines data models such as the Expense class
server/      # Contains core application logic
resources/   # Stores FXML files that define the UI layout
```

---

## Getting Started

### Prerequisites

Make sure the following are installed:

- **Java 11 or higher**
- **JavaFX SDK**
- **JFreeChart library**

---

## Setup and Run

### 1. Clone the repository

```bash
git clone https://github.com/ajayv-19/advavanced_expense_tracker_java.git
cd advavanced_expense_tracker_java
```

### 2. Open the project

Import the project into your preferred IDE:

- IntelliJ IDEA  
- Eclipse  
- VS Code  

---

### 3. Configure JavaFX

Add JavaFX to your project and include the following VM options:

```plaintext
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
```

Replace `/path/to/javafx-sdk` with the actual path where JavaFX is installed.

---

### 4. Run the application

Locate the **main class inside the `client` package** and run the application from your IDE.

---

## Future Improvements

- Export reports to PDF or Excel
- Cloud synchronization
- Expense categories and budgets
- Mobile companion application
