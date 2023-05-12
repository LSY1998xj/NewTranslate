package com.tcp;

import com.main.TestWord;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;

// 服务器

public class TCPServer {
    // year+"年"+month+"月"+day+"日"+....+"毫秒"
    public static void main(String[] args) throws Exception {
        // 1.创建ServerSocket对象,指定端口号 8885
        ServerSocket ss = new ServerSocket(8885);
        IsChangedFile isChangedFile = new IsChangedFile();
        isChangedFile.start();
        while (true) {
            // 2.使用ServerSocket对象调用accept()方法,接收请求,建立连接,返回Socket对象
            Socket socket = ss.accept();
            System.out.println(socket.getInetAddress() + "客户端已接入");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileLock fileLock;
                    try (RandomAccessFile randomAccessFile = new RandomAccessFile(TestWord.path2, "rw");
                         FileChannel fileChannel = randomAccessFile.getChannel();
                         InputStream is = socket.getInputStream();
                         OutputStream os = socket.getOutputStream()) {
                        System.out.println("Server进程开始加锁");
                        Lock lock = IsChangedFile.lock;
                        lock.lock();
                        fileLock = fileChannel.lock();
                        System.out.println("Server进程加锁完毕");
//                        FileOutputStream fos = new FileOutputStream(TestWord.path2);

                        int len;
                        byte[] bys = new byte[8192];
                        // 6.循环读取
                        while ((len = is.read(bys)) != -1) {
                            // 7.在循环中,写出数据目的文件中
                            fileChannel.write(ByteBuffer.wrap(bys, 0, len));
                            System.out.println("TCP接收" + new String(bys));
                        }
                        fileLock.release();
                        lock.unlock();
                        System.out.println("Server进程解锁");
                        // 8.写出数据到通道中
                        os.write("修改成功！".getBytes());

                        // 9.释放资源
                        socket.close();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
