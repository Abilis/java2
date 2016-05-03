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

        //данные о клиентах
        private String username;
        private boolean isLogin = false; //залогинен ли клиент

        //список всех команд сервера
        private final String[] commands = new String[]{
                "!login - залогиниться",
                "!help - показать все доступные команды"
        };

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
                send("Для участия чата введите ваше имя");
                while ((line = in.readLine()) != null) {
//                    log.info("Handler[" + number + "]<< " + line);
                    System.out.println("Handler[" + number + "]<< " + line);

                    if (!isLogin) {
                        if (logining(line)) {
                            send("Поздравляем, вы успешно залогинились под именем " + username);
                            broadcast("К нам присоединился " + username + "!");
                            continue;
                        }
                        else {
                            send("Для участия в чате введите ваше имя");
                            continue;
                        }

                    }

                    if(!isCommand(line)) { //если введенная строка не является командой, отослать ее всем
                        server.broadcast(username + ": " + line);
                    }

                }
            } catch (IOException e) {
//                log.error("Failed to read from socket");
                System.out.println("Failed to read from socket");
            } finally {
                Util.closeResource(in);
                Util.closeResource(out);
            }
        }

        //процедура залогинивания.
        public boolean logining(String login) {

            login = login.trim();
            if (login.equals("")) {
                return false;
            }
            else {
                username = login;
                isLogin = true;
                return true;
            }
        }

        //обработка команд. Команда имеет вид !command text
        private boolean isCommand(String str) {

            try {
                String[] commandAsArr = str.split(" ");
                String nameOfCommand = commandAsArr[0];
                String mesOfCommand = "";
                try {
                    mesOfCommand = commandAsArr[1];
                } catch (ArrayIndexOutOfBoundsException ignore) {
                    /*NOP*/
                }

                nameOfCommand = nameOfCommand.trim();
                mesOfCommand = mesOfCommand.trim();

                switch (nameOfCommand) {
                    case "!login":
                        String oldName = username;
                        if (logining(mesOfCommand)) {
                            send("Ваше имя теперь: " + mesOfCommand);
                            broadcast(oldName + " теперь извествен под именем " + mesOfCommand + "!");
                        }
                        else {
                            send("Команда не распознана!");
                        }
                        return true;
                    case "!help":
                        sendAllCommands();
                        return true;
                }
                return false;

            } catch (ArrayIndexOutOfBoundsException ignore) {
                /*NOP*/
            }
            return false;
        }

        //метод отправляет пользователю список всех доступных команд
        private void sendAllCommands() {
            send("Доступные команды:");
            for (String com : commands) {
                send(com);
            }
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
