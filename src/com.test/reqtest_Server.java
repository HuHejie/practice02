package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class reqtest_Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) { // 创建在8080端口上监听的服务器套接字
            System.out.println("服务器已启动，等待客户端连接...");

            while (true) { // 循环以接受多个客户端连接
                Socket socket = serverSocket.accept(); // 接受客户端连接
                System.out.println("客户端已连接: " + socket.getRemoteSocketAddress());
                InputStream in = socket.getInputStream(); // 获取输入流
                OutputStream out = socket.getOutputStream(); // 获取输出流

                int i;
                // 读取客户端发来的请求数据（如GET请求），不必处理，只需读取
                while (in.available() > 0 && (i = in.read()) != -1) {
                    System.out.print((char) i);
                }

                // 发送带有 HTTP 头部的响应
                String httpResponse =
                        "HTTP/1.1 200 OK\r\n" +  // 状态行：200 OK 表示请求成功
                                "Content-Type: text/plain\r\n" +  // 响应头部：内容类型是纯文本
                                "Content-Length: 13\r\n" +  // 响应头部：响应的内容长度
                                //"Connection: close\r\n" + // 添加Connection: close，表示服务器会关闭连接
                                "\r\n" +  // 空行表示头部结束，下面是内容部分
                                "Hello, World!"; // 响应的内容

                out.write(httpResponse.getBytes()); // 将响应消息转换为字节并发送
                out.flush(); // 确保数据已经全部发送

                System.out.println("发送响应: " + httpResponse.trim());
                socket.close(); // 关闭与客户端的连接
                System.out.println("客户端连接已关闭: " + socket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
