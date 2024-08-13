package com.meetingServerV4;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer extends Thread {
    private DataInputStream dins;
    private DataOutputStream dous;
    private Graphics g = null;

    public NetServer(Graphics g) {
        this.g = g;
    }

    public void run() {
        try {
            ServerSocket sever = new ServerSocket(9999);
            Socket socket = sever.accept();
            System.out.println("进入了一个客户端连接");
            dins = new DataInputStream(socket.getInputStream());
            dous = new DataOutputStream(socket.getOutputStream());
            while (true) {
                byte type = dins.readByte();
                System.out.println("服务端收到的消息类型是type:" + type);
                if (type == 1) {
                    readLine();
                } else if (type == 3) {
                    readImage();
                } else {
                    System.out.println("Unknown msg type" + type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readLine() throws Exception {
        int x1 = dins.readInt();
        int y1 = dins.readInt();
        int x2 = dins.readInt();
        int y2 = dins.readInt();
        int c = dins.readInt();//颜色值
        Color co = new Color(c);
        g.setColor(co);
        g.drawLine(x1, y1, x2, y2);
        System.out.println("Server收到一条线 x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " color " + c);
    }

    public void readImage() {
        try {
            int w = dins.readInt();//宽
            int h = dins.readInt();//高
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int v = dins.readInt();
                    Color c = new Color(v);
                    g.setColor(c);
                    g.drawLine(i, j, i, j);
                }
            }
            System.out.println("Server读取一张图片成功，w: " + w + " h: " + h);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server读取一张图片失败,text:");
        }
    }
}
