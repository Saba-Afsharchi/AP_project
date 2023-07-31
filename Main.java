import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private String ip = "localhost";
    private int port = 22222;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis ;
    private ServerSocket serverSocket;
    public static void main(String[] args) {

        TheMainClass play = new TheMainClass();
    }
    private void listenerforserverrequest(){
        Socket socket = null ;
        try{
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            boolean accepted = true;
            System.out.println("CLIENT HAS REQUESTED ! , REQUEST ACCEPTED ");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
           Boolean accepted = true;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
            return false;
        }
        System.out.println("Successfully connected to the server.");
        return true;
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
