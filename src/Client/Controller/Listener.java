package Client.Controller;

import javax.swing.*;
import java.io.IOException;

/**
 * This implements the Listener class
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class Listener {
    private Client client;

    /**
     * Constructs the listener
     *
     * @param client the client
     */
    public Listener(Client client) {
        this.client = client;
    }

    /**
     * @param action action that occurred
     * @return string received from the server
     */
    public String actionPerformed(String action) {
        if (action.equals("DISPLAY")) {
            try {
                return client.displayTools();
            } catch (IOException e) {
                return "Error getting tools";
            }
        } else if (action.equals("SEARCH")) {
            SearchForm dialog = new SearchForm();
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setTitle("Search");
            dialog.pack();
            dialog.setVisible(true);

            int selectedIndex = dialog.searchType.getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    if (dialog.search.getText().equals("")) {
                        return "CLOSE";
                    }
                    int id = Integer.parseInt(dialog.search.getText());
                    return client.search(id);
                } catch (NumberFormatException e) {
                    return "invalid";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (selectedIndex == 1) {
                String name = dialog.search.getText();
                try {
                    return client.search(name);

                } catch (NumberFormatException e) {
                    return "invalid";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (action.equals("DECREASE")) {
            DecreaseForm dialog = new DecreaseForm();
            dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("Decrease Quantity");
            dialog.pack();
            dialog.setVisible(true);
            int selectedIndex = dialog.searchType.getSelectedIndex();
            int amount = 0;
            try {
                amount = 0 - Integer.parseInt(dialog.Quantity.getText());
            } catch (NumberFormatException e) {

            }
            if (selectedIndex == 0) {
                int id;
                try {
                    id = Integer.parseInt(dialog.item.getText());
                    return client.decrease(id, amount);
                } catch (IOException e) {
                    return null;
                } catch (NumberFormatException e) {
                    return "CLOSE";
                }
            } else if (selectedIndex == 1) {
                String name = dialog.item.getText();
                try {
                    return client.decrease(name, amount);
                } catch (IOException e) {
                    return null;
                }
            }

        }
        return "";
    }

}
