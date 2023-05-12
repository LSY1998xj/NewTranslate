package com.thread;

import com.opertion.TextOperation;

import javax.swing.*;
import java.io.IOException;

public class TotalFileBytesCompare implements Runnable {
    private Thread thread;
    private final int total;
    private final String path;
    public boolean isSame = true;

    public TotalFileBytesCompare(String path, int total) {
        this.path = path;
        this.total = total;
        System.out.println("====================已创建检测线程====================");
    }

    public void run() {
        while (true) {
            try {
                if (total == TextOperation.totalFileBytes(path)) {
                    Thread.sleep(1000);
                } else {
                    isSame = false;
                    while (total != TextOperation.totalFileBytes(path)) {
                        JOptionPane.showMessageDialog(null, "字节数错误！请排查", "alert", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "修改成功", "alert", JOptionPane.ERROR_MESSAGE);
                    isSame = true;
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        System.out.println("====================检测线程启动成功==================");
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}