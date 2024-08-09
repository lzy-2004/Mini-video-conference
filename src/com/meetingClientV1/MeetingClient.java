package com.meetingClientV1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MeetingClient {
    public void conn2Server() throws Exception {
        Socket socket = new Socket("localhost", 9999);
        System.out.println("1-连接服务器成功");
        InputStream ins = socket.getInputStream();
        OutputStream ous = socket.getOutputStream();
        for (byte b = 0; b < 5; b++) {
            ous.write(b * 10);
            System.out.println("客户端发出一个字节:" + b);
            Thread.sleep(100);
        }
        while (true) {
            int t = ins.read();
            System.out.println("读到服务器发来的字节:" + t + " char " + (char) t);
        }
    }

    public static void main(String[] args) throws Exception {
        MeetingClient mc = new MeetingClient();
        mc.conn2Server();
    }
}
