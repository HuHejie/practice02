package com.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_Server {
    public static void main(String[] args) {
        DatagramSocket UDPSocket = null; // 在 try 块外声明 DatagramSocket，以便在整个方法中使用

        try {
            UDPSocket = new DatagramSocket(9876); // 在端口 9876 上创建 DatagramSocket
            byte[] buffer = new byte[1024]; // 用于接收数据的缓冲区
            System.out.println("服务器正在监听端口 9876");

            while (true) {
                System.out.println("等待接收消息..."); // 等待接收消息的提示
                // 创建数据报包，指定缓冲区和长度
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // 接收数据
                UDPSocket.receive(packet);
                System.out.println("接收到数据包..."); // 收到数据包后的提示

                // 将接收到的数据转换为字符串
                String message = new String(packet.getData(), 0, packet.getLength());
                // 打印接收到的消息
                System.out.println("接收到消息: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 处理异常，打印堆栈跟踪
        } finally {
            // 在 finally 块中关闭 socket，以确保它一定会被关闭
            if (UDPSocket != null && !UDPSocket.isClosed()) {
                UDPSocket.close(); // 关闭 socket
                System.out.println("服务器已关闭"); // 关闭服务器后的提示
            }
        }
    }
}
