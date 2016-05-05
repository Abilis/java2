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
    private final static int PORT_FOR_RECIEVE_FILE = 20000;
    private final static int PORT_FOR_TRANSFER_FILE = 21000;
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
//        private InputStream in;
//        private OutputStream out;

        private BufferedReader textReader;

        private int number;


        public ClientHandler(Server server, Socket socket, int number) throws IOException {
            this.server = server;
//            this.in = new BufferedInputStream(socket.getInputStream());
//            this.out = new BufferedOutputStream(socket.getOutputStream());
            this.number = number;
            this.textReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {


            String line = null;
            //здесь происходит перенаправление потока двоичных данных от одного клиента другому
            try {
                while ((line = textReader.readLine()) != null) {

                    System.out.println("Принято сообщение " + line);
                    if (line.equalsIgnoreCase("t")) {   //если клиент присылает "t", значит, он хочет передать файл
                        System.out.println("Принят запрос на передачу файла");
                        reSendData();                   //тут в качестве параметра должен быть клиент-принимающий
                    }                                   //и неплохо бы иметь еще название файла

                    System.out.println("Что-то произошло в ране. Клиент прислал текст");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void reSendData() throws IOException {

            //организуем серверные сокеты

            //сокет для приема файла
            System.out.println("Порт для приема файла: " + PORT_FOR_RECIEVE_FILE + ". Ждем клиента");
            ServerSocket serverSocketReceiveFile = new ServerSocket(PORT_FOR_RECIEVE_FILE);
            Socket socketReceiveFile = serverSocketReceiveFile.accept(); //ждем желающего отправить файл
            System.out.println("Присоединился клиент " + socketReceiveFile.getPort());

            //тут север должен найти клиента-приемника и дать ему команду инициировать запрос на другой порт


            //сокет для передачи файла
            System.out.println("Порт для передачи файла: " + PORT_FOR_TRANSFER_FILE + ". Ждем клиента");
            ServerSocket serverSocketTransferFile = new ServerSocket(PORT_FOR_TRANSFER_FILE);
            Socket socketTransferFile = serverSocketTransferFile.accept(); //ждем пока приконнектится тот, кому преднажначен файл
            System.out.println("Присоединился клиент " + socketTransferFile.getPort());


            //организуем поток приема данных от передающего
            InputStream inFile = new BufferedInputStream(socketReceiveFile.getInputStream());

            //организуем поток данных к принимающему
            OutputStream outFile = new BufferedOutputStream(socketTransferFile.getOutputStream());

            System.out.println("Передача данных статовала");

            byte[] buffer = new byte[64]; //буфер

            int count = 0;
            while ((count = inFile.read(buffer)) != -1) {
                System.out.println("Принято " + count + " байт");
                outFile.write(buffer, 0, count);
                outFile.flush();
                System.out.println("Передано " + count + " байт");
                System.out.println();
            }
            System.out.println("Передача данных закончена");


            //для порядка закрываем потоки передачи для файла, чтобы потом не забыть перенести в нормальную логику
            inFile.close();
            outFile.close();
            serverSocketReceiveFile.close();
            serverSocketTransferFile.close();

        }

    }
}
