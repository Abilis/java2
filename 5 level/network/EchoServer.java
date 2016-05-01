package arhangel.dim.network;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    protected static Logger log = LoggerFactory.getLogger(EchoServer.class);

    public static final int PORT = 19000;


    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);

        log.info("Waiting for a client...");


        // Здесь исполнение заблокируется, пока не придет запрос на соединение
        Socket socket = serverSocket.accept();

        log.info("Client connected: " + socket.getPort());

        // потоки данных (сетевые)
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());


        String line = null;

        // Ждем на readLine() пока не придут данные
        while ((line = in.readLine()) != null) {
            log.info("Client said: " + line);
            out.println(line);
            out.flush();
        }

    }
}
