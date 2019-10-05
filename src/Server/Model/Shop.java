package Server.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Shop extends Database implements Runnable {

    private Inventory theInventory;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private Socket socket;

    public Shop(Inventory inventory, Socket s) {
        theInventory = inventory;
        socket = s;
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param name name of the item to decrease
     * @return the item that has been decreased
     */
    public String decreaseItem(String name) {
        if (theInventory.manageItem(name) == null)
            return "Couldn't not decrease item quantity!\n";
        else
            return "Item quantity was decreased!\n";
    }

    /**
     * lists all the suppliers
     */
    public void listAllSuppliers() {
        ArrayList<Supplier> supplierList = loadSuppliers();
        for (Supplier s : supplierList) {
            System.out.println(s);
        }
    }

    private String outputItem(Item theItem) {
        return "The item information is as follows: \n" + theItem;
    }

    /**
     * @param name name of the item to access
     * @return the quantity of the item
     */
    public String getItemQuantity(String name) {
        int quantity = theInventory.getItemQuantity(name);
        if (quantity < 0)
            return "Item " + name + " could not be found!";
        else
            return "The quantity of Item " + name + " is: " + quantity + "\n";
    }

    /**
     * prints the order
     *
     * @return prints the inventory attributes
     */
    public String printOrder() {
        return theInventory.printOrder();
    }

    public void run() {
        String in = "";
        while (true) {
            theInventory.placeOrders();

            try {
                in = socketIn.readLine();

                if (in.equals("DISPLAY")) {
                    String out = loadItemsTable();
                    socketOut.println(out);
                    socketOut.println("END");

                } else if (in.equals("SEARCHID")) {
                    int id = Integer.parseInt(socketIn.readLine());
                    String out = searchByItemId(id);
                    socketOut.println(out);


                } else if (in.equals("SEARCHNAME")) {
                    String name = socketIn.readLine();
                    String out = searchByItemName(name);
                    socketOut.println(out);


                } else if (in.equals("DECREASEID")) {
                    int id = Integer.parseInt(socketIn.readLine());
                    int amount = Integer.parseInt(socketIn.readLine());
                    String out = changeItemQuantity(amount, id);
                    socketOut.println(out);


                } else if (in.equals("DECREASENAME")) {
                    String name = socketIn.readLine();
                    int amount = Integer.parseInt(socketIn.readLine());
                    String out = changeItemQuantity(amount, name);
                    socketOut.println(out);


                } else if (in.equals("QUIT")) {
                    break;
                }
            } catch (SocketException e) {
            } catch (IOException e) {
            }

        }
    }

}

