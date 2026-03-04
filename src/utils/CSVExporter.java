package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

public class CSVExporter {
    public static void exportExpensesToCSV(int userId, String filePath) {
        String sql = "SELECT amount, category, date, description FROM expenses WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
             PreparedStatement stmt = conn.prepareStatement(sql);
             PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            writer.println("Amount,Category,Date,Description");
            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                String date = rs.getString("date");
                String desc = rs.getString("description");
                writer.printf("%f,%s,%s,%s%n", amount, category, date, desc);
            }

            System.out.println("✅ CSV exported to: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}