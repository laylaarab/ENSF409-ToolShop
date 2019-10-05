package Server.Model;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Database {

    private Connection connection;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToolShop?serverTimezone=GMT",
                    "root", "Iig82cb3!");
        } catch (SQLException e) {
        }
    }

    public ResultSet select(String queryStr) {
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(queryStr);
            resultSet = statement.executeQuery(queryStr);
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to execute query: " + queryStr, e);
        }
        return resultSet;
    }


    public void changeItemQuantity(int amount, Item item) {
        String query = "UPDATE Items SET ItemQuantity = ? WHERE itemId = ?";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, item.getItemQuantity() + amount);
            preparedStmt.setInt(2, item.getItemId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String changeItemQuantity(int amount, int ID) {
        Item item = getItemFromID(ID);
        if (item == null){
            return null;
        }
        if ((item.getItemQuantity() + amount) < 0)
            return null;
        else {
            changeItemQuantity(amount, item);
            item.setItemQuantity(item.getItemQuantity() + amount);
            return item.getItemName() + " has been changed successfully;There are " + item.getItemQuantity() + " in stock";
        }

    }

    public String changeItemQuantity(int amount, String name) {
        Item item = getItemFromName(name);
        if (item == null){
            return null;
        }
        System.out.println(item.toString());
        if ((item.getItemQuantity() + amount) < 0)
            return null;
        else {
            changeItemQuantity(amount, item);
            item.setItemQuantity(item.getItemQuantity() + amount);
            return item.getItemName() + " has been changed successfully;There are " + item.getItemQuantity() + " in stock";
        }

    }

    public Item getItemFromID(int id) {
        ArrayList<Item> items = loadItems(loadSuppliers());
        for (Item i : items) {
            if (i.getItemId() == id)
                return i;
        }
        return null;
    }

    public Item getItemFromName(String name) {
        ArrayList<Item> items = loadItems(loadSuppliers());
        for (Item i : items) {
            if (i.getItemName().equals(name))
                return i;
        }
        return null;
    }

    public String searchByItemId(int id) {
        ArrayList<Item> items = loadItems(loadSuppliers());
        for (Item i : items) {
            if (i.getItemId() == id)
                return i.toString();
        }
        return null;
    }

    public String searchByItemName(String name) {
        ArrayList<Item> items = loadItems(loadSuppliers());
        for (Item i : items) {
            if (i.getItemName().equals(name))
                return i.toString();
        }
        return null;
    }

    /**
     * load all the supplier information from the database
     */
    public ArrayList<Supplier> loadSuppliers() {
        ResultSet rs = select("SELECT * FROM Suppliers");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try {
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("supId"), rs.getString("supName"), rs.getString("supAddress"),
                        rs.getString("supContactName")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    /**
     * load all the item information from the database
     *
     * @param s object where it stores the information
     */
    public ArrayList<Item> loadItems(ArrayList<Supplier> s) {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rs = select("SELECT * FROM Items");
        try {
            while (rs.next()) {
                Item myItem = new Item(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("itemQuantity"),
                        rs.getDouble("itemPrice"), findSupplier(rs.getInt("supId"), s));
                items.add(myItem);
                Supplier supplier = myItem.getTheSupplier();
                supplier.getItemList().add(myItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public String loadItemsTable() {
        try {
            ArrayList<Supplier> s = loadSuppliers();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Items");
            String out = "";
            NumberFormat format = NumberFormat.getCurrencyInstance();
            while (rs.next()) {
                String price = format.format(rs.getDouble("itemPrice"));
                out += (rs.getInt("itemId") + "/" + rs.getString("itemName") +
                        "/" + rs.getInt("ItemQuantity") + "/" + price + ";");
            }
            return out;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * finds the supplier by ID
     *
     * @param supplierId supplier's id
     * @param suppliers  list of suppliers
     * @return the supplier if found, null if otherwise
     */
    private Supplier findSupplier(int supplierId, ArrayList<Supplier> suppliers) {
        Supplier theSupplier = null;
        for (Supplier s : suppliers) {
            if (s.getSupId() == supplierId) {
                theSupplier = s;
                break;
            }
        }
        return theSupplier;
    }

    public Connection getConnection() {
        return connection;
    }
}