package com.meetingServerV3;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TestVideo extends JFrame {
    public void showFrame() {
        this.setTitle("webCam测试");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.showVideo();
    }

    public void showVideo() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        Graphics g = this.getGraphics();
        while (true) {
            BufferedImage im = webcam.getImage();
            g.drawImage(im, 50, 50, null);
        }
    }

    public static void main(String[] args) {
        TestVideo tv = new TestVideo();
        tv.showFrame();
    }
}
