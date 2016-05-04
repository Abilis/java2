package ru.java2.lesson5_6.filetransfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abilis on 04.05.2016.
 */
public class Server {

    private final static int PORT = 19000;
    private List<ClientHandler> handlers = new ArrayList<>();
    private static int counter = 0;

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.serverStart();
    }

    private void serverStart() throws IOException {

        System.out.println("Server starting");
        ServerSocket serverSocket = new ServerSocket(PORT);


        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress().toString() + ":" + socket.getPort());

            ClientHandler clientHandler = new ClientHandler(this, socket, counter++);
            handlers.add(clientHandler);
            clientHandler.start();

        }

    }

    class ClientHandler extends Thread {

        private Server server;
        private InputStream in;
        private OutputStream out;

        private BufferedReader textReader;

        private int number;


        public ClientHandler(Server server, Socket socket, int number) throws IOException {
            this.server = server;
            this.in = new BufferedInputStream(socket.getInputStream());
            this.out = new BufferedOutputStream(socket.getOutputStream());
            this.number = number;
            this.textReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {


            String line = null;
            //здесь происходит перенаправление потока двоичных данных от одного клиента другому
            try {
                while ((line = textReader.readLine()) != null) {
                    reSendData(handlers.get(0), handlers.get(1));
                    System.out.println("Что-то произошло в ране. Клиент прислал текст");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void reSendData(ClientHandler from, ClientHandler to) throws IOException {

            System.out.println("Передача данных статовала");

            byte[] buffer = new byte[64];

            int count = 0;
            while ((count = from.in.read(buffer)) != -1) {
                System.out.println("Принято " + count + " байт");
                to.out.write(buffer, 0, count);
                to.out.flush();
                System.out.println("Передано " + count + " байт");
                System.out.println();
            }
            System.out.println("Передача данных закончена");

        }

    }
}
