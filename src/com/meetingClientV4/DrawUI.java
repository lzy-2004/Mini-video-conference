package com.meetingClientV4;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawUI extends JFrame {
    private NetConn conn = new NetConn();
    private Graphics g = null;

    public void initUI() {
        this.setTitle("视频通信客户端v0.4");
        this.setDefaultCloseOperation(3);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.g = this.getGraphics();
        if (conn.connOK()) {
            conn.start();
            System.out.println("客户端连接，接收线程启动！");
            startVideoThread();
            System.out.println("发送视频图片的线程启动！");
        }
    }

    public void startVideoThread() {
        Thread t = new Thread() {
            public void run() {
                Webcam webcam = Webcam.getDefault();
                webcam.open();
                BufferedImage image;
                while (true) {
                    image = webcam.getImage();
                    g.drawImage(image, 0, 0, null);
                    conn.sendImage(image);
                    System.out.println("Client发送了一张 " + System.currentTimeMillis());
                }
            }
        };
        t.start();
    }

    public static void main(String[] args) {
        DrawUI du = new DrawUI();
        du.initUI();
    }
}
