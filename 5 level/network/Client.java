package arhangel.dim.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    protected static Logger log = LoggerFactory.getLogger(Client.class);

    public static final int PORT = 19000;
    public static final String HOST = "localhost";

    private static final String EXIT = "exit";

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(HOST, PORT);

        // потоки данных net
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        // консоль
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in));

        String line;
        String result;

        System.out.println("Please type...");

        while ((line = console.readLine()) != null) {
            if (EXIT.equalsIgnoreCase(line)) {
                log.info("Closing chat");
                break;
            }


            out.println(line);
            out.flush();

            result = in.readLine();
            System.out.println(">> " + result);

        }

    }
}
