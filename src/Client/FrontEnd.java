package Client;

import Client.Controller.Client;
import Client.Controller.Listener;
import Client.View.UserGUI;

import java.io.IOException;

/**
 * This implements client application
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class FrontEnd {

    public static void main(String[] args) throws IOException {
//
        Client client = new Client("localhost", 5050);
        UserGUI gui = new UserGUI();
        gui.setListener(new Listener(client));
        gui.updateView(gui);
    }
}
