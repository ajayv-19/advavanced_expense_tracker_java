package client;

import java.io.*;
import java.net.Socket;

@SuppressWarnings("resource")
public class ClientConnector {
    private static PrintWriter out;
    private static BufferedReader in;

    static {
        try {
            Socket socket = new Socket("localhost", 9999);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            in.readLine(); // Skip welcome
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String send(String command) {
        try {
            out.println(command);
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}