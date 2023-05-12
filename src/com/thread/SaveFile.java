package com.thread;

import com.opertion.FileOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class SaveFile implements Runnable {

    private static int checkSeconds;
    private static Thread thread;
    public static String filePath;
    public final Path PathCreate = Files.createDirectories(Paths.get("src\\文件备份"));

    static {
        try {
            filePath = FileOperation.getLineStr(2, "src\\Path.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SaveFile() throws IOException {
        SaveFile.checkSeconds = 1000;
    }

    public SaveFile(int checkSeconds) throws IOException {
        SaveFile.checkSeconds = checkSeconds;
    }

    @Override
    public void run() {
        String FileName = Paths.get(String.valueOf(PathCreate), new SimpleDateFormat("yy年MM月dd日HH时mm分ss秒").format(System.currentTimeMillis()) + ".locale").toString();
        try {
            FileOperation.copy(filePath, FileName);
            while (true) {
                if (!FileOperation.isChangedFile(filePath, FileName)) {
                    FileName = Paths.get(String.valueOf(PathCreate), new SimpleDateFormat("yy年MM月dd日HH时mm分ss秒").format(System.currentTimeMillis()) + ".locale").toString();
                    FileOperation.copy(filePath, FileName);
                }
                Thread.sleep(checkSeconds);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        System.out.println("====================备份线程启动成功==================");
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
