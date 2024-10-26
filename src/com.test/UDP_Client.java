package com.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.Scanner;

public class UDP_Client {
    public static void main(String[] args) {
        try (
                DatagramSocket socket = new DatagramSocket();
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("客户端已经启动，可以发送消息。输入 'exit' 以退出。");

            while (true) {
                String message = scanner.nextLine(); // 读取用户输入的消息

                // 检查用户是否输入了 'exit' 命令
                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("退出客户端...");
                    break; // 退出循环
                }

                byte[] data = message.getBytes(); // 将消息转换为字节数组
                InetAddress address = InetAddress.getByName("localhost"); // 定义目标地址为本地服务器
                DatagramPacket packet = new DatagramPacket(data, data.length, address, 9876); // 创建数据包
                socket.send(packet); // 发送数据包
                System.out.println("发送消息: " + message); // 打印发送的消息
            }
        } catch (IOException e) { // 捕获可能的异常
            e.printStackTrace(); // 打印异常堆栈跟踪
        }
    }
}
