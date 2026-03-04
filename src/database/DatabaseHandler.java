package database;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:expenses.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL);";

            String expenseTable = "CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "amount REAL," +
                    "category TEXT," +
                    "date TEXT," +
                    "description TEXT," +
                    "group_id INTEGER DEFAULT NULL);";

            String groupTable = "CREATE TABLE IF NOT EXISTS groups (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT);";

            stmt.execute(userTable);
            stmt.execute(expenseTable);
            stmt.execute(groupTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);  // ⚠️ Later: Use hashed password
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean addExpense(int userId, double amount, String category, String date, String description, Integer groupId) {
        String sql = "INSERT INTO expenses (user_id, amount, category, date, description, group_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, category);
            pstmt.setString(4, date);
            pstmt.setString(5, description);
            if (groupId != null) {
                pstmt.setInt(6, groupId);
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Add Expense Failed: " + e.getMessage());
            return false;
        }
    }
    
}