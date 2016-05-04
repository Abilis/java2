package ru.java2.lesson_5_5_NetChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedServer {

//    protected static Logger log = LoggerFactory.getLogger("ThreadedServer");
    private static final int PORT = 19000;
    private static int counter = 0;

    private static List<User> users = new ArrayList<>(); //список подключенных пользователей

    // список обработчиков для клиентов
    private List<ClientHandler> handlers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ThreadedServer server = new ThreadedServer();
        server.startServer();
    }

    public void startServer() throws Exception {
//        log.info("Starting server...");
        System.out.println("Starting server...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {

            // блокируемся и ждем клиента
            Socket socket = serverSocket.accept();
//            log.info("Client connected: " + socket.getInetAddress().toString() + ":" + socket.getPort());
            System.out.println("Client connected: " + socket.getInetAddress().toString() + ":" + socket.getPort());

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

        //текущий пользователь
        private User currentUser;

        public ClientHandler(ThreadedServer server, Socket socket, int counter) throws Exception {
            this.server = server;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            number = counter;                           //id пользователя совпадает с номером потока
            currentUser = new User(number, "", "");     //создаем пользователя
            users.add(currentUser);                     //и добавляем его в список подключенных пользователей
        }


        // Отправка сообщения в сокет, связанный с клиентом
        public void send(String message) {
            out.println(message);
            out.flush();
        }

        @Override
        public void run() {

            // В отдельном потоке ждем данных от клиента

            //Приветствие при первом подключении
            send("Для участия в чате введите имя");

            try {
                String line = null;
                while ((line = in.readLine()) != null) {
//                    log.info("Handler[" + number + "]<< " + line);
                    System.out.println("Handler[" + number + "]<< " + line);

                    //если пользователь не залогинен, он должен ввести имя
                    if (!currentUser.isLogin()) {
                        if (!firstUserLogging(line)) {
                            send("Некорректные данные. Введите ваше имя");
                        }
                        continue;
                    }

                    //если мы оказались здесь, значит пользователь залогинен. Нужно проверить, не является ли введенная строка командой


                    server.broadcast(currentUser.getCurrentNick() + ": " + line);
                }
            } catch (IOException e) {
//                log.error("Failed to read from socket");
                System.out.println("Failed to read from socket");
            } finally {
                Util.closeResource(in);
                Util.closeResource(out);
            }
        }

        //метод залогинивает пользователя под именем login в первый раз. True - при успехе, False - при неуспехе
        private boolean firstUserLogging(String nick) {

            nick = nick.trim();

            if (nick.length() == 0) {
                return false;
            }

            currentUser.setCurrentNick(nick);   //устанавливаем пользователю ник
            currentUser.setIsLogin();           //ставим флаг о залогинивании

            //добавляем подключившегося пользователя в список пользователей
            users.add(currentUser);

            //оповещаем всех о присоединении нового пользователя
            broadcast("К нам присоединился " + currentUser.getCurrentNick() + "!");

            return true;
        }






    }

    // рассылаем всем подписчикам
    public void broadcast(String msg) {
//        log.info("Broadcast to all: " + msg);
        System.out.println("Broadcast to all: " + msg);
        for (ClientHandler handler : handlers) {
            handler.send(msg);
        }
    }


}
