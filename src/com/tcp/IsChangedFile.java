package com.tcp;

import com.opertion.FileOperation;
import com.main.TestWord;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IsChangedFile implements Runnable {
    private static Thread thread;
    private static final String path = TestWord.path2;
    public static volatile boolean isChanged = false;
    public static List<String> file;
    public static volatile Lock lock = new ReentrantLock();
    public static volatile String changedContent;

    static {
        try {
            lock.lock();
            file = FileOperation.fileToListLock(path);
            lock.unlock();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(ManagementFactory.getRuntimeMXBean().getName() + "已加锁");
                List<String> fileList = FileOperation.fileToListLock(path);
                if (equals(fileList, file)) {
                    isChanged = false;
                    System.out.println("isChanged为F");
                } else {
                    file = fileList;
                    isChanged = true;
                    System.out.println("isChanged为T");
                }
                lock.unlock();
                System.out.println(ManagementFactory.getRuntimeMXBean().getName() + "已解锁");
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("====================线程启动成功==================");
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public boolean equals(List<String> list1, List<String> list2) throws IOException {
        System.out.println("equals开始调用");
        StringBuilder stringBuilder = new StringBuilder();
        if (list1.size() != list2.size()) {
            System.out.println("文件行数改变请排查!");
            System.out.println("equals调用完毕");
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\com\\tcp\\Log.txt", true));
                stringBuilder.append("第").append(i + 1).append("行发生改动:  ");
                stringBuilder.append(list2.get(i));
                stringBuilder.append("-------->");
                stringBuilder.append(list1.get(i));
                changedContent = stringBuilder.toString();
                System.out.println(changedContent);
                bufferedWriter.append(changedContent);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
                System.out.println("equals调用完毕");
                return false;
            }
        }
        System.out.println("equals调用完毕");
        return true;
    }
}
