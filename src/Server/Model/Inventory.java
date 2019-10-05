package Server.Model;

import java.util.ArrayList;

public class Inventory extends Database {

    private Order myOrder;

    public Inventory() {
        myOrder = new Order();
    }


    public void placeOrders() {
        ArrayList<Item> items = loadItems(loadSuppliers());
        for (Item i : items)
            if (i != null)
                placeOrder(i);
    }

    /**
     * places an order
     *
     * @param theItem item to order
     */
    protected void placeOrder(Item theItem) {
    public void placeOrder(Item theItem) {
        OrderLine ol = theItem.placeOrder();
        if (ol != null) {
            myOrder.addOrderLine(ol);
        }
    }
    /**
     * searches for item and decreases quantity
     *
     * @param name name of item to search for
     * @return the item
     */
    private Item decreaseItem(String name) {
        Item theItem = searchForItem(name);

        if (theItem == null)
            return null;

        if (theItem.decreaseItemQuantity()) {
            return theItem;
        }
        return null;

    }

    /**
     * @param name name of item to find
     * @return quantity of item
     */
    public int getItemQuantity(String name) {
        Item theItem = searchForItem(name);
        if (theItem == null)
            return -1;
        else
            return theItem.getItemQuantity();
    }

    /**
     * @param name name of item to find
     * @return index of item to find
     */
    public Item searchForItem(String name) {
        ArrayList<Item> itemList = loadItems(loadSuppliers());
        for (Item i : itemList) {
            if (i.getItemName().equals(name))
                return i;
        }
        return null;
    }

    /**
     * @return the String of the inventory
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        ArrayList<Item> itemList = loadItems(loadSuppliers());
        for (Item i : itemList) {
            str.append(i);
        }
        return str.toString();
    }

    public Item searchForItem(int id) {
        ArrayList<Item> itemList = loadItems(loadSuppliers());
        for (Item i : itemList) {
            if (i.getItemId() == id)
                return i;
        }
        return null;
    }

    public String printOrder() {
        return myOrder.toString();
    }

}


