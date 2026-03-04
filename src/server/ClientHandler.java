package server;

import database.DatabaseHandler;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Connected to Expense Tracker Server");

            while (true) {
                String line = in.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) break;

                String[] parts = line.split(" ");
                String command = parts[0];

                switch (command) {
                    case "register":
                        if (parts.length == 3 && DatabaseHandler.registerUser(parts[1], parts[2])) {
                            out.println("REGISTER_SUCCESS");
                        } else {
                            out.println("REGISTER_FAIL");
                        }
                        break;
                    case "login":
                        if (parts.length == 3 && DatabaseHandler.authenticateUser(parts[1], parts[2])) {
                            out.println("LOGIN_SUCCESS");
                        } else {
                            out.println("LOGIN_FAIL");
                        }
                        break;
                    case "add_expense":
                        // Example input: add_expense 1 25.5 Food 2025-04-24 "Lunch" null
                        if (parts.length >= 7) {
                            try {
                                int userId = Integer.parseInt(parts[1]);
                                double amount = Double.parseDouble(parts[2]);
                                String category = parts[3];
                                String date = parts[4];
                                String description = parts[5].replace("_", " "); // use underscores for spaces
                                Integer groupId = parts[6].equals("null") ? null : Integer.parseInt(parts[6]);

                                boolean success = database.DatabaseHandler.addExpense(userId, amount, category, date, description, groupId);
                                out.println(success ? "EXPENSE_ADDED" : "EXPENSE_FAILED");
                            } catch (Exception e) {
                                out.println("EXPENSE_FAILED: " + e.getMessage());
                            }
                        } else {
                            out.println("EXPENSE_FAILED: Invalid format");
                        }
                        break;
                    default:
                        out.println("UNKNOWN_COMMAND");
                        break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}