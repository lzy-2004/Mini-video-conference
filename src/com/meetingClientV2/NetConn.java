package com.meetingClientV2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetConn {
    private OutputStream ous;
    private InputStream ins;

    public boolean conn2Server() {
        try {
            Socket socket = new Socket("localhost", 9999);
            System.out.println("1-连接服务器成功，取得输入/输出流");
            ins = socket.getInputStream();
            ous = socket.getOutputStream();
            System.out.println("2-客户端连接服务器OK");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendLine(int x1, int y1, int x2, int y2) {
        try {
            ous.write(x1);
            ous.write(y1);
            ous.write(y1);
            ous.write(y2);
            System.out.println("客户端发送线 x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " 成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端发送线 x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2 + " 失败");
        }
    }
}
