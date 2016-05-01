package arhangel.dim.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedServer {

    protected static Logger log = LoggerFactory.getLogger("ThreadedServer");
    private static final int PORT = 19000;
    private static int counter = 0;

    // список обработчиков для клиентов
    private List<ClientHandler> handlers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ThreadedServer server = new ThreadedServer();
        server.startServer();
    }

    public void startServer() throws Exception {
        log.info("Starting server...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {

            // блокируемся и ждем клиента
            Socket socket = serverSocket.accept();
            log.info("Client connected: " + socket.getInetAddress().toString() + ":" + socket.getPort());

            // создаем обработчик
            ClientHandler handler = new ClientHandler(this, socket, counter++);
            handlers.add(handler);
            handler.start();
        }
    }

    /*
    Для каждого присоединившегося пользователя создается поток обработки независимый от остальных
     */

    class ClientHandler extends Thread {

        private ThreadedServer server;
        private BufferedReader in;
        private PrintWriter out;

        // номер, чтобы различать потоки
        private int number;

        public ClientHandler(ThreadedServer server, Socket socket, int counter) throws Exception {
            this.server = server;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            number = counter;
        }


        // Отправка сообщения в сокет, связанный с клиентом
        public void send(String message) {
            out.println(message);
            out.flush();
        }

        @Override
        public void run() {

            // В отдельном потоке ждем данных от клиента
            try {
                String line = null;
                while ((line = in.readLine()) != null) {
                    log.info("Handler[" + number + "]<< " + line);
                    server.broadcast(line);
                }
            } catch (IOException e) {
                log.error("Failed to read from socket");
            } finally {
                Util.closeResource(in);
                Util.closeResource(out);
            }
        }
    }

    // рассылаем всем подписчикам
    public void broadcast(String msg) {
        log.info("Broadcast to all: " + msg);
        for (ClientHandler handler : handlers) {
            handler.send(msg);
        }
    }


}
