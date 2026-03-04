package models;

public class Expense {
    private int id;
    private int userId;
    private double amount;
    private String category;
    private String date;
    private String description;
    private Integer groupId;

    public Expense(int userId, double amount, String category, String date, String description, Integer groupId) {
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
        this.groupId = groupId;
    }

    // Getters and Setters omitted for brevity
}