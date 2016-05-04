package ru.java2.lesson5_6.filetransfer;

import java.io.*;
import java.net.Socket;

/**
 * Created by Abilis on 04.05.2016.
 */
public class Client {

    private final static int PORT = 19000;
    private final static String HOST = "localhost";
    private final static String fileName1 = "D:\\Temp\\file1.txt";
    private final static String fileName2 = "D:\\Temp\\file2.txt";

    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.startClient();


    }

    private void startClient() throws IOException {

        Socket socket = new Socket(HOST, PORT);
        TransferData transferData = new TransferData(socket);
        transferData.start();

    }

    class TransferData extends Thread {

        private PrintWriter textOut;
        private OutputStream out;
        private InputStream in;
        private FileInputStream fileInputStream;
        private FileOutputStream fileOutputStream;
        private BufferedReader readerFromConsol;

        public TransferData(Socket socket) throws IOException {
            textOut = new PrintWriter(socket.getOutputStream());
            out = new BufferedOutputStream(socket.getOutputStream());
            in = new BufferedInputStream(socket.getInputStream());
            readerFromConsol = new BufferedReader(new InputStreamReader(System.in));
        }

        @Override
        public void run() {

            String line = null;
            try {
                while ((line = readerFromConsol.readLine()) != null) {

                    if (line.equals("r")) {
                        recieveFile();
                        break;
                    }

                    textOut.write(line);
                    textOut.flush();
                    sendFile();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //метод передает файл в сокет
        private void sendFile() throws IOException {

            System.out.println("Передача данных сейчас начнется");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Передача данных стартовала");

            byte[] buffer = new byte[32];

            File file1 = new File(fileName1);
            fileInputStream = new FileInputStream(file1);

            byte[] preBuffer = new byte[]{1, 2, 3, 4};
            out.write(preBuffer);
            out.flush();

            try {
            int count = 0;
                while ((count = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    out.flush();
                    System.out.println("Отправлено " + count + " байт");
                    Thread.sleep(5000);
                }

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Передача данных закончена");

        }


        //метод принимает файл из сокета
        private void recieveFile() throws IOException {

            System.out.println("Прием данных стартовал");
            byte[] buffer = new byte[64];

            File file2 = new File(fileName2);

            fileOutputStream = new FileOutputStream(file2);

            int count = 0;

            while ((count = in.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0 , count);
                fileOutputStream.flush();
                System.out.println("Принято " + count + " байт");
            }

            System.out.println("Прием данных завершен");
        }
    }
}
