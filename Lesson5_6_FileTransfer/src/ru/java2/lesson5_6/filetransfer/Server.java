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

        private BufferedReader in;

        private int number;


        public ClientHandler(Server server, Socket socket, int number) throws IOException {
            this.server = server;
            this.number = number;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {


            String line = null;
            //здесь происходит перенаправление потока двоичных данных от одного клиента другому
            try {
                while ((line = in.readLine()) != null) {

                    System.out.println("Принято сообщение " + line);
                    if (line.equalsIgnoreCase("t")) {   //если клиент присылает "t", значит, он хочет передать файл
                        System.out.println("Принят запрос на передачу файла");
                        reSendData();
                        continue;                       //тут в качестве параметра должен быть клиент-принимающий
                    }                                   //и неплохо бы иметь еще название файла


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void reSendData() {

            ServerSocket serverSocketReceiveFile = null;
            ServerSocket serverSocketTransferFile = null;

            InputStream inFile = null;
            OutputStream outFile = null;

            try {

                //организуем серверные сокеты
                serverSocketReceiveFile = new ServerSocket(PORT_FOR_RECIEVE_FILE);
                serverSocketTransferFile = new ServerSocket(PORT_FOR_TRANSFER_FILE);

                //сокет для приема файла
                System.out.println("Порт для приема файла: " + PORT_FOR_RECIEVE_FILE + ". Ждем клиента");

                Socket socketReceiveFile = serverSocketReceiveFile.accept(); //ждем желающего отправить файл
                System.out.println("Присоединился передающий клиент");

                //тут сервер должен найти клиента-приемника и дать ему команду инициировать запрос на другой порт


                //сокет для передачи файла
                System.out.println("Порт для передачи файла: " + PORT_FOR_TRANSFER_FILE + ". Ждем клиента");

                Socket socketTransferFile = serverSocketTransferFile.accept(); //ждем пока приконнектится тот, кому преднажначен файл
                System.out.println("Присоединился принимающий клиент");


                //организуем поток приема данных от передающего
                inFile = new BufferedInputStream(socketReceiveFile.getInputStream());

                //организуем поток данных к принимающему
                outFile = new BufferedOutputStream(socketTransferFile.getOutputStream());

                System.out.println("Передача данных статовала");

                byte[] buffer = new byte[32]; //буфер

                int count = 0;
                while ((count = inFile.read(buffer)) != -1) {
                    System.out.println("Принято " + count + " байт");
                    outFile.write(buffer, 0, count);
                    outFile.flush();
                    System.out.println("Передано " + count + " байт");
                    System.out.println();
                }
                System.out.println("Передача данных закончена");
            } catch (IOException e) {
                System.out.println("Не удалось передать данные");
            } finally {
                //закрытие ресурсов

                Util.closeQuite(inFile);
                Util.closeQuite(outFile);
                Util.closeQuite(serverSocketReceiveFile);
                Util.closeQuite(serverSocketTransferFile);
            }







        }

    }
}
