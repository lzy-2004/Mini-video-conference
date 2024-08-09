package com.meetingServerV1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MeetingServer {
    public void upServer(int port) throws Exception {
        ServerSocket server = new ServerSocket(port);//创建服务器对象
        System.out.println("1-服务器创建成功 " + port);
        Socket socket = server.accept();
        System.out.println("2-客户端进入 " + socket.getRemoteSocketAddress().toString());
        InputStream ins = socket.getInputStream();
        OutputStream ous = socket.getOutputStream();
        for (byte b = 0; b < 6; b++) {
            ous.write(b * 8);
            System.out.println("服务端发送一个字节：" + b);
            Thread.sleep(100);
        }
        while (true) {
            int t = ins.read();
            System.out.println("读到客户端发来的字节" + t);
        }
    }

    public static void main(String[] args) throws Exception {
        MeetingServer ms = new MeetingServer();
        ms.upServer(9999);
    }
}
