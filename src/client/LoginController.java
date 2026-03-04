package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleLogin() {
        String u = usernameField.getText();
        String p = passwordField.getText();
        String response = ClientConnector.send("login " + u + " " + p);

        if ("LOGIN_SUCCESS".equals(response)) {
            messageLabel.setText("Login successful!");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/gui/dashboard.fxml"));
                Parent dashboardRoot = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(dashboardRoot));
                stage.setTitle("Expense Tracker - Dashboard");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            messageLabel.setText("Login failed. Try again.");
        }
    }

    @FXML
    private void handleRegister() {
        String u = usernameField.getText();
        String p = passwordField.getText();
        String response = ClientConnector.send("register " + u + " " + p);

        if ("REGISTER_SUCCESS".equals(response)) {
            messageLabel.setText("Registration complete! You can now login.");
        } else {
            messageLabel.setText("Username already exists or invalid.");
        }
    }
}