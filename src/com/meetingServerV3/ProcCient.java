package com.meetingServerV3;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ProcCient extends Thread{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Graphics g = null;
    public ProcCient(Socket socket,Graphics g){
        this.socket=socket;
        this.g=g;
    }
    public void run(){
        try{
            System.out.println("1-连接服务器成功：");
            dis=new DataInputStream(socket.getInputStream());
            dos=new DataOutputStream(socket.getOutputStream());
            while(true){
                byte type = dis.readByte();
                System.out.println("服务端收到的消息类型是type:"+type);
                if(type==1){
                    processLine();
                }else if(type==2){
                    processText();
                }else{
                    System.out.println("Unknown msg type "+type);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void processLine() throws Exception{
        int x1=dis.readInt();
        int y1=dis.readInt();
        int x2=dis.readInt();
        int y2=dis.readInt();
        int c=dis.readInt();
        Color co = new Color(c);
        g.setColor(co);
        g.drawLine(x1,y1,x2,y2);
        System.out.println("Server收到一条线 x1 "+x1+" y1 "+y1+" x2 "+x2+" y2 "+y2+" color "+c);
    }
    public void processText() throws Exception{
        int byteLen = dis.readInt();
        System.out.println("Server收到文本消息字节长度是"+byteLen);
        byte[] data = new byte[byteLen];
        dis.read(data);
        String msg = new String(data);
        System.out.println("Server收到文本消息内容是"+msg);
    }

}
