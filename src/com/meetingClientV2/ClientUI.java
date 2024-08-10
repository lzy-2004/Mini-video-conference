package com.meetingClientV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientUI extends JFrame {
    private NetConn conn = new NetConn();

    public void initUI() {
        this.setTitle("网画客户器端 v0.1");
        this.setDefaultCloseOperation(3);
        this.setSize(500, 400);
        this.setVisible(true);
        if (conn.conn2Server()) {
            System.out.println("客户端连接成功！");
        }
        Graphics g = this.getGraphics();
        this.addMouseListener(new MouseAdapter() {
            int x1, y1, x2, y2;

            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                g.drawLine(x1, y1, x2, y2);
                conn.sendLine(x1, y1, x2, y2);
            }
        });
    }

    public static void main(String[] args) {
        ClientUI cu = new ClientUI();
        cu.initUI();
    }
}
