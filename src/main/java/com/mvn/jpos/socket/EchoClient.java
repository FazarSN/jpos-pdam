package com.mvn.jpos.socket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoClient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
    }

    public String sendMessage(String msg) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(msg);
        
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String line;
        StringBuilder data = new StringBuilder();
        while ((line = in.readLine()) != null) {
            data.append(line);
        }
        return data.toString();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();

    }
}
