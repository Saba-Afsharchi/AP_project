import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ObjectOutputStream> clientOutputStreams;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientOutputStreams = new ArrayList<>();
            System.out.println("Server is running on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                ObjectOutputStream clientOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(clientOutputStream);

                // Create a new thread to handle this client
                ClientHandler handler = new ClientHandler(clientSocket, clientOutputStream);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast a message to all connected clients
    private void broadcastMessage(Object message) {
        for (ObjectOutputStream outputStream : clientOutputStreams) {
            try {
                outputStream.writeObject(message);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 12345; // Replace this with your desired port number
        Server server = new Server(port);
        server.start();
    }

    // This class handles communication with a single client
    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private ObjectInputStream clientInputStream;
        private ObjectOutputStream clientOutputStream;

        public ClientHandler(Socket clientSocket, ObjectOutputStream clientOutputStream) {
            this.clientSocket = clientSocket;
            this.clientOutputStream = clientOutputStream;
        }

        @Override
        public void run() {
            try {
                clientInputStream = new ObjectInputStream(clientSocket.getInputStream());

                while (true) {
                    // Read messages from the client and broadcast to other clients
                    Object message = clientInputStream.readObject();
                    broadcastMessage(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                // Client disconnected or encountered an error, remove from the list
                clientOutputStreams.remove(clientOutputStream);
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            }
        }
    }
}
