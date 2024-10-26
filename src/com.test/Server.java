package com.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // 每次程序运行都会覆盖旧文件
            File outputFile = new File("received_file.txt");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFile, false)); // false 表示覆盖文件内容

            // 接收来自客户端的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received: " + line);
            }

            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
