package com.meetingClientV4;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class NetConn extends Thread {
    private DataInputStream dins;
    private DataOutputStream dous;

    public boolean connOK() {
        try {
            Socket socket = new Socket("localhost", 9999);
            System.out.println("1-连接服务器成功");
            dins = new DataInputStream(socket.getInputStream());
            dous = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendImage(BufferedImage image) {
        try {
            int w = image.getWidth();
            int h = image.getHeight();
            dous.writeByte(3);
            dous.writeInt(w);
            dous.writeInt(h);
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    dous.writeInt(image.getRGB(i, j));
                }
            }
            System.out.println("Client一张图片发送成功，w: " + w + " h: " + h);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Client发送图片失败");
        }
    }

    public void sendLine(int x1, int y1, int x2, int y2, int c) {
        try {
            dous.writeByte(1);
            dous.writeInt(x1);
            dous.writeInt(y1);
            dous.writeInt(x2);
            dous.writeInt(y2);
            dous.writeInt(c);
            System.out.println("Client发送一条线 x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " color " + c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Client发送一条线 x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " color " + c + " 失败");
        }
    }
}
