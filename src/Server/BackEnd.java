package Server;

import Server.Controller.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackEnd {


    public static void main(String[] args) throws IOException {
        Server server = new Server(5050);
        server.communicate();
        server.close();

    }


}
