package Server.Model;

public class Item extends Database {

    private int itemId;
    /**
     * name of the tool
     */
    private String itemName;
    /**
     * quantity of the tool
     */
    private int itemQuantity;
    /**
     * price of the tool
     */
    private double itemPrice;
    private Supplier theSupplier;
    private static final int MINIMUMUMBER = 40;

    public Item(int id, String name, int quantity, double price, Supplier sup) {
        itemId = id;
        itemName = name;
        itemQuantity = quantity;
        itemPrice = price;
        theSupplier = sup;
    }

    /**
     * @return true if item has been decreased
     */
    public boolean decreaseItemQuantity() {
        if (itemQuantity > 0) {
            itemQuantity--;
            changeItemQuantity(-1, this);
            return true;
        } else
            return false;

    }

    /**
     * places an order for the item
     *
     * @return
     */
    public OrderLine placeOrder() {
        OrderLine ol;
        if (getItemQuantity() < MINIMUMUMBER) {
            int amount = MINIMUMUMBER - itemQuantity;
            ol = new OrderLine(this, amount);
            changeItemQuantity(amount, this);
            return ol;
        }
        return null;
    }

    /**
     * @return the itemID
     */
    protected int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the value for ID to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return returns the item name
     */
    protected String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the name for the item to be set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the item quantity
     */
    protected int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * @param itemQuantity the quantity of the item to set
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * @return the item price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice price of the item to be set
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * @param sup supplier to set
     */
    public void setTheSupplier(Supplier sup) {
        theSupplier = sup;
    }

    /**
     * @return supplier member variable
     */
    public Supplier getTheSupplier() {
        return theSupplier;
    }

    /**
     * @return makes the member variables into a string
     */
    public String toString() {
        return "Item ID: " + itemId + ";Item Name: " + itemName + ";Item Quantity: " +
                itemQuantity + ";Supplier: " + theSupplier.getSupName();
    }

}
