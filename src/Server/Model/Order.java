package Server.Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class for orders
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class Order {

    private Date today;
    /**
     * id of order
     */
    private int orderId;
    /**
     * list of orderlines
     */
    private ArrayList<OrderLine> orderLines;

    /**
     * default constructor of order
     */
    protected Order() {
    private ArrayList<OrderLine> orderLines;

    public Order() {
        today = Calendar.getInstance().getTime();
        orderLines = new ArrayList<OrderLine>();
    }

    /**
     * adds orderline to the order
     *
     * @param ol orderline to add
     */
    protected void addOrderLine(OrderLine ol) {
        orderLines.add(ol);
    }

    /**
     * @return the orderlines
     */
    public void addOrderLine(OrderLine ol) {
        orderLines.add(ol);
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * @param lines set the orderline list
     */
    public void setOrderLines(ArrayList<OrderLine> lines) {
        orderLines = lines;
    }

    /**
     * @return the orderID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId sets the orderID
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the order attributes made to a string
     */
    public String toString() {
        String order = "Order Date: " + today.toString() + "\n\n";
        StringBuilder str = new StringBuilder();
        for (OrderLine ol : orderLines) {
            str.append(ol);
            str.append("------------------------\n");
    public String toString() {
        String order = "Order Date: " + today.toString() + "\n\n";
        StringBuilder str = new StringBuilder();
        for (OrderLine ol : orderLines) {
            str.append(ol);
            str.append("------------------------\n");
        }
        if (str.toString().equals(""))
            str = new StringBuilder("here are currently no orderlines");
        order += str;
        order += "\n";
        return order;
    }

}
