package com.tcp;

import com.main.TestWord;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;

// 客户端
public class Client {
    public volatile static String changedContentClient;

    public static void main(String[] args) throws Exception {
        IsChangedFile isChangedFile = new IsChangedFile();
        isChangedFile.start();
//        SocketConnect("192.168.1.2", 8882);
//        SocketConnect("192.168.1.3", 8883);
        SocketConnect("192.168.1.7", 8885);
    }

    public static void SocketConnect(String host, int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (IsChangedFile.isChanged) {
                        FileLock fileLock=null;
                        try (RandomAccessFile randomAccessFile = new RandomAccessFile(TestWord.path2, "rw");
                             FileChannel fileChannel = randomAccessFile.getChannel();
                             Socket socket = new Socket(host, port);
                             OutputStream os = socket.getOutputStream()) {
                            Lock lock = IsChangedFile.lock;
                            System.out.println("client进程加锁");
                            lock.lock();
                            fileLock = fileChannel.lock();
                            System.out.println("client进程加锁完毕");
//                            FileInputStream fis = new FileInputStream(TestWord.path2);
                            // 2.创建Socket对象,指定要连接的服务器的ip地址和端口号

                            // 3.通过Socket对象获得输出流,关联连接通道

                            // 4.定义变量,用来存储读取到的字节数据
                            byte[] bys = new byte[8192];
                            int len;

                            // 5.循环读取
                            while ((len = fileChannel.read(ByteBuffer.wrap(bys))) != -1) {
                                // 6.在循环中,写出数据到通道中
                                os.write(bys, 0, len);
                                System.out.println("Client发送"+new String(bys,0,len));
                            }
                            fileLock.release();
                            lock.unlock();
                            System.out.println("client进程解锁");
                            // 想办法,告诉服务器,我客户端写完了数据,我再也不会写数据了
                            socket.shutdownOutput();// 注意

//                            System.out.println("=======客户端开始接收端口号为" + port + "的服务端数据=======");
                            // 7.通过Socket对象获取输入流,关联连接通道
                            InputStream is = socket.getInputStream();

                            // 8.读取服务器回写的数据
                            int read = is.read(bys);
                            // 9.打印服务器回写的数据
                            System.out.println(port + "服务端:" + new String(bys, 0, read));
//                            Thread.sleep(1000);
                        } catch (IOException | RuntimeException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();
    }
}