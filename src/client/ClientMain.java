package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine());

            while (true) {
                System.out.print(">> ");
                String command = scanner.nextLine();
                out.println(command);

                String response = in.readLine();
                System.out.println("Server: " + response);

                if (command.equalsIgnoreCase("exit")) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}