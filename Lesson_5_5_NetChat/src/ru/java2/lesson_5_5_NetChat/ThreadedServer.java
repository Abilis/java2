package ru.java2.lesson_5_5_NetChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.java2.lesson_5_5_NetChat.exceptions.NoSuchUserException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreadedServer {

//    protected static Logger log = LoggerFactory.getLogger("ThreadedServer");
    private static final int PORT = 19000;
    private static int counter = 0;

    private static List<User> users = new ArrayList<>(); //список подключенных пользователей

    private static final String GREATING_MESSAGE = ", добро пожаловать в чат! Сменить ник можно командой !login <new_nick>. " +
            "Проосмотреть все доступные команды можно написав !help";

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
            currentUser = new User(number, "", "", in, out);     //создаем пользователя
        }


        // Отправка сообщения в сокет, связанный с клиентом
        public void send(String message) {
            out.println(message);
            out.flush();
        }

        //отправка сообщения в определенный сокет
        private void send(String message, PrintWriter out) {
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
                    //если строка - команда, нужно ее обработать. Иначе - это обычное сообщение, нужно разослать всем
                    if (!isCommand(line)) {
                        server.broadcast(currentUser.getCurrentNick() + ": " + line);
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

        //метод залогинивает пользователя под именем login в первый раз. true - при успехе, false - при неуспехе
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

            //выводим приветственное сообщение для подключившегося
            send(currentUser.getCurrentNick() + GREATING_MESSAGE);

            return true;
        }

        //метод проверяет, является ли введенная пользователем строка командой
        private boolean isCommand(String line) {

            line = line.trim();

            if (line.length() == 0) { //пустая строка
                return false;
            }

            String[] lineAsArr = line.split(" ");
            String firstElem = lineAsArr[0];

            switch (firstElem) {
                case "!login": //команда смены ника. Нужен второй аргумент
                    processLogin(line);
                    return true;
                case "!help": //команда выводит список всех доступных команд
                    printHelp();
                    return true;
                case "!exit": //команда отключает пользователя от чата

                    return true;
                case "!private": //команда посылает приватное сообщение другому пользователю. Нужен второй аргумент
                    sendPrivate(line);
                    return true;
                case "!users": //команда выводит список подключенных пользователей
                    printUsers();
                    return true;

            }

            return false;
        }

        //метод обрабатывает команду !help
        private void printHelp() {
            Map<String, String> commands = Commands.getCommands();

            send("Доступные команды:");
            for (Map.Entry<String, String> pair : commands.entrySet()) {
                send(pair.getKey() + pair.getValue());
            }
        }

        //метод обрабатывает команду !users
        private void printUsers() {
            send("Подключенные пользователи (" + users.size() + "):");

            for (User user : users) {
                send(user.getCurrentNick());
            }
        }

        //метод обрабатывает команду !login
        private void processLogin(String str) {

            try {
                String[] strAsArr = str.split(" ");
                String newNick = strAsArr[1];
                changeNick(newNick);

            } catch (ArrayIndexOutOfBoundsException e) {
                send("Команда не распознана");
            }

        }

        //метод меняет ник пользователя
        private void changeNick(String newNick) {

            String oldNick = currentUser.getCurrentNick();
            currentUser.setOldNick(oldNick);
            currentUser.setCurrentNick(newNick);

            send("Вы успешно сменили свой ник на " + currentUser.getCurrentNick());
            broadcast(currentUser.getOldNick() + " теперь известен под именем " + currentUser.getCurrentNick() + "!");
        }

        //метод оправляет приватное сообщение пользователю с ником userTo, если такой подключен
        private void sendPrivate(String str) {

            try {
                String[] strAsArr = str.split(" ");
                String userTo = strAsArr[1];

                //вытаскиваем сообщение, которое нужно переслать приватно
                String msg = "";
                for (int i = 2; i < strAsArr.length; i++) {
                    msg += strAsArr[i] + " ";
                }

                msg = msg.substring(0, msg.length() - 1);

                if (msg.equals("")) {
                    send("Сообщение не может быть пустым!");
                    return;
                }

                sendPrivateMessage(msg, userTo);

            } catch (ArrayIndexOutOfBoundsException e1) {
                send("Команда не распознана");
            } catch (NoSuchUserException e2) {
                send(e2.getMessage());
            }

        }

        //метод оправляет приватное сообщение пользователю с ником userTo, если такой подключен
        private void sendPrivateMessage(String message, String userTo) throws NoSuchUserException {

//            userTo = userTo.trim();

            for (User user : users) {
                if (user.getCurrentNick().equals(userTo)) {
                    PrintWriter out = user.getOut();
                    message = currentUser.getCurrentNick() + "[priv]: " + message;
                    send(message, out);
                    send("Сообщение отправлено!");
                    return;
                }
            }
            //если вышли из цикла, пользователь userTo отсутствует среди подключенных пользователей
            throw new NoSuchUserException("Пользователь с именем " + userTo + " не подключен!" +
                    " !users - вывести всех подключенных пользователей");
        }

    }

    // рассылаем всем подписчикам
    public void broadcast(String msg) {
//        log.info("Broadcast to all: " + msg);
        System.out.println("Broadcast to all: " + msg);

        //рассылаем только тем пользователям, которые есть в списке. Это залогиненные пользователи
        for (User user : users) {
            handlers.get(user.getId()).send(msg);

//        for (ClientHandler handler : handlers) {
//            handler.send(msg);
        }
    }


}
