package ru.java2.lesson_5_5_NetChat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Abilis on 10.05.2016.
 */
public class LoadTest {

    private static final int PORT = 19000;
    private static final String HOST = "localhost";

    private static int count = 1;

    public static void main(String[] args) throws Exception {
        new LoadTest().doLoadTest();
    }


    private void doLoadTest() throws Exception {

        for (int i = 1; i < 12; i++) {



            Thread testLoad = new Thread() {
                @Override
                public void run() {

                    try {
                        Socket socket = new Socket(HOST, PORT);

                        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());

                        final int count = LoadTest.count++;
                        System.out.println(console.readLine());
                        out.println(count);
                    } catch (Exception ignore) {
                        /*NOP*/
                    }
                }
            };

            testLoad.start();
        }


    }

}
