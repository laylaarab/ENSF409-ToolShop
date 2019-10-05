package Client.View;

import Client.Controller.Listener;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This implements the GUI
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class UserGUI {
    /**
     * panel containing other items of gui
     */
    private JPanel panel;
    /**
     * button to show all tools
     */
    private JButton showToolsButton;
    /**
     * area to show the list of items
     */
    private JTextArea ItemList;
    /**
     * field to input text
     */
    private JTextField textField1;
    /**
     * button to search for an item
     */
    private JButton searchButton;
    private JButton decreaseQuantityButton;
    private JButton checkQuantityButton;
    //    private JTable ItemTable;

    /**
     * listener object to react to action in gui
     */

    private Listener listener;
    private JTable items;
    private JScrollPane scroll;

    /**
     * Default constructor for the GUI
     */
    public UserGUI() {
    }

    /**
     * sets the listener
     *
     * @param listener the listener
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Displays the tools of the "Show Tools" button is pressed
     */
    private void displayTools() {
        showToolsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = listener.actionPerformed("DISPLAY");
                String[] headers = {"Item ID", "Item Name", "Quantity", "Price"};
                String[] temp = response.split(";");

                String[][] data = new String[temp.length][];

                for (int i = 0; i < temp.length; i++) {
                    data[i] = temp[i].split("/");
                }

                TableModel model = new DefaultTableModel(data, headers) {
                    public boolean isCellEditable(int row, int column) { return false; }
                };

                items = new JTable(model);

                if (scroll != null)
                    panel.remove(scroll);

                scroll = new JScrollPane(items);
                panel.add(scroll);

                items.setBackground(new Color(-1657945));
                panel.validate();
            }
        });

    }


    /**
     * searches for the tool if "search" button is pressed
     */
    private void searchTools() {
        searchButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = listener.actionPerformed("SEARCH");

                if (response.equals("null")) {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(new JFrame(), "Item not found!");
                } else if (response.equals("invalid")) {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(null, "Please input valid ID", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(null, response.replaceAll(";", "\n"), "Item", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }


    private void decreaseItemQuantity() {
        decreaseItemQuantityButton.addActionListener(new ActionListener() {

            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = listener.actionPerformed("DECREASE");
                if (response.equals("null")) {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(new JFrame(), "Could not decrease quantity!");
                } else if (response.equals("invalid")) {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(null, "Please input valid item Name or ID", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(null, response.replaceAll(";", "\n"), "Quantity Decrease", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    /**
     * updates the GUI
     *
     * @param gui the gui to be updated
     */
    public void updateView(UserGUI gui) {
        JFrame frame = new JFrame("Toolshop");
        frame.setContentPane(gui.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        gui.displayTools();
        gui.searchTools();
        gui.decreaseItemQuantity();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBackground(new Color(-1657945));
        panel.setMinimumSize(new Dimension(500, 500));
        panel.setPreferredSize(new Dimension(500, 500));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setBackground(new Color(-17217));
        toolBar1.setFloatable(false);
        panel.add(toolBar1, BorderLayout.NORTH);
        showToolsButton = new JButton();
        showToolsButton.setBackground(new Color(-1140324));
        showToolsButton.setText("Show Tools");
        toolBar1.add(showToolsButton);
        searchButton = new JButton();
        searchButton.setBackground(new Color(-1140324));
        searchButton.setText("Search");
        toolBar1.add(searchButton);
        decreaseItemQuantityButton = new JButton();
        decreaseItemQuantityButton.setBackground(new Color(-1140324));
        decreaseItemQuantityButton.setForeground(new Color(-12828863));
        decreaseItemQuantityButton.setText("Decrease Item Quantity");
        toolBar1.add(decreaseItemQuantityButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
