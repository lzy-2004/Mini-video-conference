package com.meetingServerV4;

import javax.swing.*;
import java.awt.*;

public class ServerUI extends JFrame {
    public void initUI() {
        this.setTitle("视频通信服务端v0.4");
        this.setDefaultCloseOperation(3);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        NetServer ns = new NetServer(g);
        ns.start();
        System.out.println("服务线程已启动");
    }

    public static void main(String[] args) {
        ServerUI su = new ServerUI();
        su.initUI();
    }
}
