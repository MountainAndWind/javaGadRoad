package Dome_Udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @description
 * @Author slfang
 * @Time 2019/4/3 15:35
 * @Version 1.0
 **/
public class DataSent {
    public static void main(String[] args) throws IOException {
        /*DatagramSocket socket = new DatagramSocket();  //初始案列
        byte[] bytes = "this is udp 测试".getBytes();
        InetAddress byName = InetAddress.getByName("192.168.103.1");
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length,byName,10002);
        socket.send(packet);
        socket.close();*/
       /*现在键盘手动录入当输入886程序退出*/
        DatagramSocket socket = new DatagramSocket();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = reader.readLine())!=null){
            if("886".equals(line)){
                break;
            }
            //创建打包数据
            byte[] bytes = line.getBytes();
            DatagramPacket sc = new DatagramPacket(bytes,bytes.length,InetAddress.getByName("192.168.103.1"),10086);
            socket.send(sc);
        }
        socket.close();
    }
}
