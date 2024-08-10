package com.meetingServerV2;

import javax.swing.*;
import java.awt.*;

public class ServerUI extends JFrame {
    public void initUI() {
        this.setTitle("网画服务器端 v0.1");
        this.setDefaultCloseOperation(3);
        this.setSize(500, 400);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        NetServer ns = new NetServer(g);
        ns.start();
        System.out.println("服务器线程已启动");
    }

    public static void main(String[] args) {
        ServerUI su = new ServerUI();
        su.initUI();
    }
}
