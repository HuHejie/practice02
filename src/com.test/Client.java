package com.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to the server");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter text or file path (type 'exit' to quit): ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Exiting...");
                    break;
                }

                File file = new File(input);
                if (file.exists() && file.isFile()) {
                    // 输入是文件路径，读取文件内容并发送
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));
                    String fileLine;
                    while ((fileLine = fileReader.readLine()) != null) {
                        writer.println(fileLine); // 发送文件内容
                    }
                    fileReader.close();
                    System.out.println("File content sent.");
                } else {
                    // 输入是普通文本，直接发送
                    writer.println(input);
                    System.out.println("Text sent.");
                }
            }

            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
