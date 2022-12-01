package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.example.Server.port;


public class Player_1 {

    public static void main(String[] args) throws Exception {
        try (Socket clientSocket = new Socket("localhost", port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader readerWord = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            out.println("1");
            String answer = in.readLine();
            System.out.println(answer);

            String word = readerWord.readLine();
            out.println(word);
            String answer2 = in.readLine();
            System.out.println(answer2);
        }
    }
}
