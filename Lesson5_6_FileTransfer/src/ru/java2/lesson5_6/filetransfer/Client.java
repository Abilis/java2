package ru.java2.lesson5_6.filetransfer;

import java.io.*;
import java.net.Socket;

/**
 * Created by Abilis on 04.05.2016.
 * Для передачи файла нужно запустить сервер, потом пару клиентов.
 * Имена файлов отправителя и получателя захардкожены в полях этого класса чуть ниже.
 * На отправителе нужно ввести t
 * Через некоторое время (можно через большое), на получателе следует ввести r
 *
 * Процесс передачи файла демонстрируется в консоли клиентов и сервера.
 * Для большей демонстративности в процесс передачи на стороне отправителя включены задержки
 *
 * Исключения не обрабатываются. Передача обычных сообщений между клиентами не реализована
 */
public class Client {

    private final static int PORT = 19000;
    private final static int PORT_FOR_FILE_TRANSFER = 20000;
    private final static int PORT_FOR_FILE_RECEIVE = 21000;
    private final static String HOST = "localhost";
    private final static String fileName1 = "D:\\temp\\testfile\\file3.txt";
    private final static String fileName2 = "D:\\temp\\testfile\\file4.txt";

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

        private BufferedReader readerFromConsol = new BufferedReader(new InputStreamReader(System.in));
        private PrintWriter textOut;

        public TransferData(Socket socket) throws IOException {
            textOut = new PrintWriter(socket.getOutputStream());
        }

        @Override
        public void run() {

            System.out.println("Для передачи файла вветите t");
            System.out.println("После этого у получателя введите r");

            String line = null;
            try {
                while ((line = readerFromConsol.readLine()) != null) {

                    if (line.equalsIgnoreCase("t")) {   //клиент передает "t", что дает серверу указание начать передачу
                        textOut.println(line);
                        textOut.flush();
                        System.out.println("Отправлено сообщение " + line);
                        sendFile();                     //данных от этого клиента другому
                        continue;
                    }

                    else if (line.equalsIgnoreCase("r")) {  //эта штука не передается серверу, а нужна только для вызова
                        recieveFile();                      //метода у клиента на прием файла
                        continue;
                    }

                    textOut.println(line);
                    textOut.flush();
                    System.out.println("Отправлено сообщение " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //метод передает файл в сокет
        private void sendFile() {

            Socket socketFileTransfer = null;
            FileInputStream fileInputStream = null;
            File file1 = null;
            OutputStream out = null;

            try {
                System.out.println("Передача данных сейчас начнется");


                Thread.sleep(5000);


                //Создаем новый сокет для отправки файла
                socketFileTransfer = new Socket(HOST, PORT_FOR_FILE_TRANSFER);

                //организуем поток данных в сокет
                out = new BufferedOutputStream(socketFileTransfer.getOutputStream());

                //организуем поток данных из файла
                file1 = new File(fileName1);
                fileInputStream = new FileInputStream(file1);

                System.out.println("Передача данных стартовала");

                byte[] buffer = new byte[32]; //буфер


                int count = 0;
                while ((count = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    out.flush();
                    System.out.println("Отправлено " + count + " байт");
                    Thread.sleep(1000);
                }

                Thread.sleep(5000);

                System.out.println("Передача данных закончена");

            } catch (IOException | InterruptedException e) {
                System.out.println("Не удалось отправить файл");
            }
            finally {
                //закрываем потоки
                Util.closeQuite(fileInputStream);
                Util.closeQuite(out);
                Util.closeQuite(socketFileTransfer);

            }

        }


        //метод принимает файл из сокета
        private void recieveFile() {

            FileOutputStream fileOutputStream = null;
            Socket socketFileReceive = null;
            File file2 = null;
            InputStream in = null;

            try {
                //создаем новый сокет для приема файла
                socketFileReceive = new Socket(HOST, PORT_FOR_FILE_RECEIVE);

                //Организуем поток данных из сокета
                in = new BufferedInputStream(socketFileReceive.getInputStream());

                //Организуем поток данных в файл
                file2 = new File(fileName2);
                fileOutputStream = new FileOutputStream(file2);

                System.out.println("Прием данных стартовал");
                byte[] buffer = new byte[32]; //буфер


                int count = 0;

                while ((count = in.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0 , count);
                    fileOutputStream.flush();
                    System.out.println("Принято " + count + " байт");
                }

                System.out.println("Прием данных завершен");
            } catch (IOException e) {
                System.out.println("Не удалось принять файл");
                file2.delete();

            } finally {
                //закрываем потоки
                Util.closeQuite(in);
                Util.closeQuite(socketFileReceive);
                Util.closeQuite(fileOutputStream);
            }




        }
    }
}
