package Dome_Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @description
 * @Author slfang
 * @Time 2019/4/3 15:35
 * @Version 1.0
 **/
public class DataReceive {

    public static void main(String[] args) throws IOException {
        /*DatagramSocket receiveSocket = new DatagramSocket(10002);//初始dome
        //创建一个数据包
        byte[] bys = new byte[1024];
        int length = bys.length;
        DatagramPacket packet = new DatagramPacket(bys,length);

        //调用socket对象的接受方法
        receiveSocket.receive(packet);
        System.out.println(packet.getAddress().getHostAddress()+"----"+packet.getPort());
        byte[] data = packet.getData();
        String s = new String(data,0,packet.getLength());
        System.out.println(s);
        receiveSocket.close();*/
        DatagramSocket receiveSocket = new DatagramSocket(10086);


        while(true){
            //创建一个容器（数据包）
            byte[] bys = new byte[1024];
            int length = bys.length;
            DatagramPacket packet = new DatagramPacket(bys,length);
            receiveSocket.receive(packet);
            System.out.println("ip:"+packet.getAddress().getHostAddress()+"数据接收为:"+new String(packet.getData(),0,packet.getLength()));
        }


    }
}
