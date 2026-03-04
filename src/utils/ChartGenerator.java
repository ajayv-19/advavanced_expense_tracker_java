package utils;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
public class ChartGenerator {
    public static void generateMonthlySpendingChart(int userId, String outputPath) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String sql = "SELECT substr(date, 1, 7) AS month, SUM(amount) as total " +
                     "FROM expenses WHERE user_id = ? GROUP BY month ORDER BY month";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String month = rs.getString("month");
                double total = rs.getDouble("total");
                dataset.addValue(total, "Spending", month);
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Monthly Spending Summary", "Month", "Amount", dataset);

            ChartUtilities.saveChartAsPNG(new File(outputPath), barChart, 800, 600);
            System.out.println("✅ Chart saved to: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}