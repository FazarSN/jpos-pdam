package com.mvn.jpos.socket;

import java.io.IOException;

import com.mvn.jpos.util.Converter;

import org.jpos.iso.ISOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EchoTest {
    private static String addres;
    private static int port;

    @BeforeClass
    public static void start() {
        addres = "";
        port = 0;
    }

    private EchoClient client = new EchoClient();

    @Before
    public void init() {
        try {
            client.startConnection(addres, port);
        } catch (IOException e) {
            System.out.println("error start connection : " + e);
        }
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() throws ISOException, InterruptedException {
        String message = Converter.fromJSONtoISO("0800");
        String header = String.format("%04d", message.length());
        System.out.println("Request = " + header + message);
        try {
            String resp1 = client.sendMessage(header + message);
            System.out.println("Response = " + resp1);
        } catch (IOException e) {
            System.out.println("error send message : " + e);
        } finally {
            try {
                client.stopConnection();
            } catch (IOException e) {
                System.out.println("error stop connection : " + e);
            }
        }
    }

}