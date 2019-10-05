package Server.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.SQLException;


import Server.Model.*;


/**
 * Connection to client
 */
public class Server {

    /**
     * Input to server
     */
    private BufferedReader socketIn;

    /**
     * Output to client
     */
    private PrintWriter socketOut;
    /**
     * The socket that connects between server and client
     */
    private Socket socket;

    /**
     * Used for executing the method within class ShopController
     */
    public ExecutorService pool;

    /**
     * Allows aSocket to be connected between server and client
     */
    private ServerSocket serverSocket;

    /**
     * The database in which the records are stored
     */
    Database database;

    /**
     * @param portNum port number
     */
    public Server(int portNum) {
        try {
            serverSocket = new ServerSocket(portNum);
//            socket = serverSocket.accept();
//            socketOut = new PrintWriter((socket.getOutputStream()), true);
//            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pool = Executors.newCachedThreadPool();
            database = new Database();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is now running...");
    }

    /**
     * Initializes the shop and connects the clients
     */
    public void communicate() {
        Inventory theInventory = new Inventory();
       while (true) {
            try {
                Shop shop = new Shop(theInventory, serverSocket.accept());
                pool.execute(shop);
            } catch (IOException e) {
                e.printStackTrace();
                pool.shutdown();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(5050);
        server.communicate();
        server.close();
    }

    /**
     * closes all the sockets
     */
    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            socket.close();

            try {
                database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }


}


