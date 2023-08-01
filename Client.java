import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to server running on localhost at port 12345

            // Create input and output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create a Scanner to read keyboard inputs
            Scanner scanner = new Scanner(System.in);

            // Send data to the server and read the response
            String messageToServer;
            while (true) {
                System.out.print("Enter your message: ");
                messageToServer = scanner.nextLine();
                out.println(messageToServer);
                String responseFromServer = in.readLine();
                System.out.println("Server: " + responseFromServer);

                // Exit loop if the user types "exit"
                if (messageToServer.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            // Close streams, scanner, and socket
            in.close();
            out.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
