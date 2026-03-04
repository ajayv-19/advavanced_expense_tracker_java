package client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.ChartGenerator;
import utils.CSVExporter;

import java.time.LocalDate;

public class DashboardController {

    @FXML private TextField amountField;
    @FXML private TextField categoryField;
    @FXML private DatePicker datePicker;
    @FXML private TextField descriptionField;
    @FXML private Label messageLabel;

    private int loggedInUserId = 1;  // 🔥 Hardcoded for now (later dynamic after login)

    @FXML
    private void handleAddExpense() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            LocalDate date = datePicker.getValue();
            String description = descriptionField.getText().replace(" ", "_");

            if (category.isEmpty() || date == null || description.isEmpty()) {
                messageLabel.setText("❌ All fields are required!");
                return;
            }

            // Command to Server
            String command = String.format("add_expense %d %.2f %s %s %s null",
                    loggedInUserId, amount, category, date.toString(), description);
            String response = ClientConnector.send(command);

            if ("EXPENSE_ADDED".equals(response)) {
                messageLabel.setText("✅ Expense added successfully!");
                clearFields();
            } else {
                messageLabel.setText("❌ Failed to add expense.");
            }
        } catch (Exception e) {
            messageLabel.setText("❌ Invalid input!");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGenerateChart() {
        try {
            ChartGenerator.generateMonthlySpendingChart(loggedInUserId, "monthly_spending.png");
            messageLabel.setText("✅ Monthly Spending Chart generated!");
        } catch (Exception e) {
            messageLabel.setText("❌ Failed to generate chart.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExportCSV() {
        try {
            CSVExporter.exportExpensesToCSV(loggedInUserId, "my_expenses.csv");
            messageLabel.setText("✅ Expenses exported to CSV!");
        } catch (Exception e) {
            messageLabel.setText("❌ Failed to export CSV.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        System.exit(0);
    }

    private void clearFields() {
        amountField.clear();
        categoryField.clear();
        datePicker.setValue(null);
        descriptionField.clear();
    }
}