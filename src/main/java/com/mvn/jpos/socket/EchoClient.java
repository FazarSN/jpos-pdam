package com.mvn.jpos.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLSocket;

public class EchoClient {

    private Socket clientSocket;
    private OutputStream out;
    private InputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket();
        final InetAddress address = InetAddress.getByName(ip);
        final InetSocketAddress i = new InetSocketAddress(address, port);
        clientSocket.connect(i, 10000);
        if (clientSocket instanceof SSLSocket) {
            ((SSLSocket) clientSocket).startHandshake();
        }

    }

    public String sendMessage(String msg) throws IOException, InterruptedException {
        byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);

        out = clientSocket.getOutputStream();
        out.write(buffer);

        Thread.sleep(5 * 1000);

        in = clientSocket.getInputStream();

        byte[] bin = new byte[8192];
        int lin = in.read(bin);
        byte[] data = new byte[lin];
        System.arraycopy(bin, 0, data, 0, lin);

        return new String(data, 0, data.length, StandardCharsets.UTF_8);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();

    }
}
