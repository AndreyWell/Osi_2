package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static int port = 8080;

    public static String lastWord = "???";

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("START");
            // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                Socket fromClientSocket = serverSocket.accept();
                try (
                        Socket socket = fromClientSocket;
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {

                    String inputWord;

                    while ((inputWord = in.readLine()) != null) {
                        if (inputWord.equals("1")) {
                            if (lastWord.equals("???")) {
                                out.println(lastWord);
                                lastWord = inputWord;
                            } else {
                                out.println(String.format("Текущее слово: %s", lastWord));
                            }
                        } else if (lastWord.equals("1")) {
                            lastWord = inputWord;
                            out.println("\nOK");
                            System.out.println("First word: " + lastWord);
                        } else {
                            String more = inputWord;
                            if (more.toLowerCase().substring(0, 1).equals(
                                    lastWord.toLowerCase().substring(lastWord.length() - 1))) {
                                lastWord = more;
                                out.println(String.format("OK - Текущее слово: %s", lastWord));
                                System.out.println("Next word: " + more);
                            } else {
                                out.println(String.format("NOT OK - Текущее слово: %s", lastWord));
                            }
                        }
                    }
                }
            }
        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
