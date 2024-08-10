package com.meetingServerV2;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer extends Thread {
    private Graphics g;

    public NetServer(Graphics g) {
        this.g = g;
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket socket = server.accept();
            InputStream ins = socket.getInputStream();
            OutputStream ous = socket.getOutputStream();
            while (true) {
                int x1 = ins.read();
                int y1 = ins.read();
                int x2 = ins.read();
                int y2 = ins.read();
                System.out.println("服务器收到1条线 x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2);
                g.drawLine(x1, y1, x2, y2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器出错！");
        }
    }
}
