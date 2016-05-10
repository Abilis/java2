package ru.java2.lesson_5_5_NetChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


public class ThreadedClient {

//    protected static Logger log = LoggerFactory.getLogger(ThreadedClient.class);

    public static final int PORT_FOR_FILE = 20000;
    public static final int PORT = 19000;
    public static final String HOST = "localhost";
    private static final String EXIT = "exit";
    private static final String DISCONNECT = "!exit";
    private static final String DISCONNECT_FROM_SERVER = "exit_from_server";

    public static void main(String[] args) throws Exception {

        ThreadedClient client = new ThreadedClient();
        client.startClient();

    }

    public void startClient() {
        Socket socket = null;
        BufferedReader in = null;
        try {
            socket = new Socket(HOST, PORT);
            ConsoleThread console = new ConsoleThread(socket);
            console.start();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;
            while ((line = in.readLine()) != null) {
                if (line.contains(DISCONNECT_FROM_SERVER)) {
                    console.interrupt();
                    break;
                }
                System.out.println(">> " + line);
            }

        } catch (Exception ignore) {
            /*NOP*/
        } finally {
            Util.closeResource(in);
            Util.closeResource(socket);
        }
    }

    public void send(String message) {

    }

    class ConsoleThread extends Thread implements Serializable {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out;

        public ConsoleThread(Socket socket) throws Exception {
            out = new PrintWriter(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = console.readLine()) != null) {
                    if (EXIT.equalsIgnoreCase(line) || DISCONNECT.equalsIgnoreCase(line)) {
                        out.println("!exit");
                        out.flush();
//                        log.info("Closing chat");
                        System.out.println("Closing chat");
                        break;
                    }

                    //здесь нужна какая-то обработка для передачи и приема файла

                    out.println(line);
                    out.flush();

                    if (isInterrupted()) {
                        System.out.println("Сервер разорвал соединение!");
                        break;
                    }
                }
            } catch (Exception ignore) {
                /*NOP*/
            } finally {
                Util.closeResource(out);
            }
        }

        //метод передает файл в сокет
        private void sendFile(String fileName1) throws IOException {

            byte[] buffer = new byte[64 * 1024];

//            String fileName11 = "D:\temp\testfile\file3.txt";

            //создание потока из файла
            File file = new File(fileName1);
            FileInputStream fileInputStream = new FileInputStream(file);

            //создаем новый сокет для передачи файла
            Socket fileSocket = new Socket(HOST, PORT_FOR_FILE);

            //сюда будет писать файл в виде байтового массива
            OutputStream outFile = fileSocket.getOutputStream();

            int count;
            while ((count = fileInputStream.read(buffer)) > 0) {
                outFile.write(buffer, 0, count);
                outFile.flush();
            }



        }

        //метод принимает файл из сокета
        private void recieveFile(String fileName2) throws IOException {

            byte[] buffer = new byte[64 * 1024];

//            String fileName22 = "D:\temp\testfile\file4.txt";

            //создание потока для записи в файл
            File file = new File(fileName2);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            //создаем новый сокет для приема файла
            Socket fileSocket = new Socket(HOST, PORT_FOR_FILE);
            InputStream inFile = fileSocket.getInputStream();

            int count;
            while ((count = inFile.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
                fileOutputStream.flush();
            }


        }
    }

}
