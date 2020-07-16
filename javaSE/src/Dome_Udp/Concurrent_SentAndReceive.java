package Dome_Udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @description  使用多线程进行实现聊天操作
 * @Author slfang
 * @Time 2019/4/3 16:57
 * @Version 1.0
 **/
public class Concurrent_SentAndReceive {

    public static void main(String[] args) throws IOException{

        DatagramSocket sentSocket = new DatagramSocket();
        DatagramSocket receiveSocket = new DatagramSocket(10011);

        Thread threadSent = new ThreadSent(sentSocket);
        Thread threadReceive = new ThreadReceive(receiveSocket);

        threadReceive.start();
        threadSent.start();


    }
    static class ThreadSent extends Thread{

        private DatagramSocket socket;

        public ThreadSent(DatagramSocket socket){
             this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            try {
                while ((line = reader.readLine())!=null){
                    if("886".equals(line)){
                        break;
                    }
                    //创建打包数据
                    byte[] bytes = line.getBytes();
                    DatagramPacket sc = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("192.168.103.1"),10011);
                    socket.send(sc);
                }
            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }finally {
                socket.close();
            }
        }
    }

    static class ThreadReceive extends Thread{

        private DatagramSocket socket;

        public ThreadReceive(DatagramSocket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            while(true){
                //创建一个容器（数据包）
                byte[] bys = new byte[1024];
                int length = bys.length;
                DatagramPacket packet = new DatagramPacket(bys,length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("ip:"+packet.getAddress().getHostAddress()+"数据接收为:"+new String(packet.getData(),0,packet.getLength()));
            }
        }
    }

}
