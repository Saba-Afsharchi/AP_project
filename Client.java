import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Application {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        try {
            String serverIP = "localhost"; // Replace this with the server IP address
            int serverPort = 12345; // Replace this with the server port number

            socket = new Socket(serverIP, serverPort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        // Read messages from the server and display them in the text area
                        Object message = inputStream.readObject();
                        Platform.runLater(() -> textArea.appendText(message.toString() + "\n"));
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StackPane root = new StackPane(textArea);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ensure the socket is closed when the application is closed
        primaryStage.setOnCloseRequest(event -> {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // Method to send a message from the client to the server
    private void sendMessageToServer(Object message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
