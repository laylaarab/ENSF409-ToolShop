package Client.Controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * This class implements a client that forms connection to server
 */
public class Client {
    /**
     * Output of the client
     */
    private PrintWriter socketOut;

    /**
     * Socket that connects the client to the server
     */
    private Socket socket;
    /**
     * Input to the client
     */
    private BufferedReader socketIn;

    private BufferedReader stdIn;

    private boolean isActive = false;


    /**
     * Constructs the client
     *
     * @param serverName name of the server
     * @param portNumber port number
     */
    public Client(String serverName, int portNumber) {
        try {
            socket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * disconnects the client from the server
     */
    protected void close() {
        isActive = false;
        try {
            socketIn.close();
            socketOut.close();
            stdIn.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }

    /**
     * returns the string received from the server when the client sens DISPLAY
     *
     * @return tools in a string
     * @throws IOException io exception
     */
    protected String displayTools() throws IOException {
        socketOut.println("DISPLAY");
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (!response.equals("END")) {
            data.append(response);
            data.append(";");
            response = socketIn.readLine();
        }
        return data.toString();
    }

    /**
     * sends an item id to the server to search by id then returns what the server sends back
     * @param id item id
     * @return the item information the server sends back
     * @throws IOException
     */
    public String search(int id) throws IOException {
        socketOut.println("SEARCHID");
        socketOut.println(id);
        return socketIn.readLine();
    }

    /**
     * sends an item name to the server to search by id then returns what the server sends back
     * @param name item name
     * @return the item information the server sends back
     * @throws IOException
     */
    public String search(String name) throws IOException {
        socketOut.println("SEARCHNAME");
        socketOut.println(name);
        return socketIn.readLine();
    }

    /**
     * sends an item id followed by the amount to decrease item stock by then returns what the server sends back
     * @param id item id
     * @param amount amount to decrese stock by
     * @return null if the operation was unsuccessful, the item name and the quantity left in stock if successful
     * @throws IOException
     */
    public String decrease(int id, int amount) throws IOException {
        socketOut.println("DECREASEID");
        socketOut.println(id + "\n" + amount);
        return socketIn.readLine();
    }

    /**
     * sends an item name followed by the amount to decrease item stock by then returns what the server sends back
     * @param name item name
     * @param amount amount to decrease stock by
     * @return null if the operation was unsuccessful, the item name and the quantity left in stock if successful
     * @throws IOException
     */
    public String decrease(String name, int amount) throws IOException {
        socketOut.println("DECREASENAME");
        socketOut.println(name + "\n" + amount);
        return socketIn.readLine();
    }


}
