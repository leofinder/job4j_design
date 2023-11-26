package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        String msg = getMessage(str);
                        if (msg != null) {
                            if ("Hello".equals(msg)) {
                                out.write("Hello, dear friends".getBytes());
                            } else if ("Exit".equals(msg)) {
                                out.write("Bye".getBytes());
                                server.close();
                            } else {
                                out.write(msg.getBytes());
                            }
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    private static String getMessage(String str) {
        String msg = null;
        if (str.startsWith("GET")) {
            Pattern pattern = Pattern.compile("^GET .+msg=(.+) .+$");
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()) {
                msg = matcher.group(1);
            }
        }
        return msg;
    }
}
