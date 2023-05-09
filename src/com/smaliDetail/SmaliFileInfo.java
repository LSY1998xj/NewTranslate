package com.smaliDetail;

import com.opertion.FileOperation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.deploy.cache.Cache.copyFile;

public class SmaliFileInfo implements Serializable {
    private final String smaliFilePath;
    private List<File> smaliDirList;
    private List<File> smailFileList;
    private List<File> smailFileHas0local;
    private Map<File, List<String>> smaliFile0localInfo;

    public List<File> getSmailFileList() {
        return smailFileList;
    }

    public SmaliFile smaliFile;
    public volatile static boolean[] initComplete = {false, false, false, false};

    public SmaliFileInfo(String path) throws IOException {
        this.smaliFilePath = path;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    copyFile(new File("src/com/smaliDetail/smaliMess.smali"), new File(path + "\\com\\smaliMess.smali"));
                    initComplete[0] = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SmaliFileInfo.this.smaliDirList = FileOperation.searchDirInDirToList(path);
                System.out.println("SmaliFileInfo.smaliDirList初始化成功!");
                initComplete[1] = true;
            }
        }).start();
        SmaliFileInfo.this.smailFileList = FileOperation.searchFileInDirToList(path, ".smali");
        System.out.println("SmaliFileInfo.smailFileList初始化成功!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SmaliFileInfo.this.smailFileHas0local = initSmailFileHas0local();
                    initComplete[2] = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("SmaliFileInfo.smailFileHas0local初始化成功!");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SmaliFileInfo.this.smaliFile0localInfo = initSmailFileHas0localInfo();
                    initComplete[3] = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("SmaliFileInfo.smaliFile0localInfo初始化成功!");
            }
        }).start();

        while (true) {
            if (initComplete[0] && initComplete[1] && initComplete[2] && initComplete[3]) {
                System.out.println("初始化已全部完成！");
                break;
            }
        }
    }

    public List<File> initSmailFileHas0local() throws IOException {
        List<File> resultList = new ArrayList<>();
        for (File file : smailFileList) {
            List<String> list = FileOperation.fileToList(String.valueOf(file));
            for (String s : list) {
                if (s.trim().contains(".locals 0")) {
                    resultList.add(file);
                    break;
                }
            }

        }
        return resultList;
    }

    public List<File> getSmailFileHas0local() {
        return smailFileHas0local;
    }

    public Map<File, List<String>> getSmaliFile0localInfo() {
        return smaliFile0localInfo;
    }

    public Map<File, List<String>> initSmailFileHas0localInfo() throws IOException {
        Map<File, List<String>> resultMap = new HashMap<>();
        for (File file : smailFileList) {
            List<String> fileMethodName0local = new ArrayList<>();
            List<String> list = FileOperation.fileToList(String.valueOf(file));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).trim().contains(".locals 0")) {
                    String methodName = getMethodName(list.get(i - 1));
                    fileMethodName0local.add(methodName);
                    resultMap.put(file, fileMethodName0local);
                    break;
                }
            }
        }
        return resultMap;
    }

    public String getSmaliFilePath() {
        return smaliFilePath;
    }

    private String getMethodName(String strLine) {
        int lastLeftBracketIndex = strLine.lastIndexOf('(');
        return strLine.substring(strLine.substring(0, lastLeftBracketIndex).lastIndexOf(" ") + 1, lastLeftBracketIndex);
    }

    public void serializeSmaliFile(String serName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("src\\com\\smaliDetail", serName)));) {
            System.out.println("开始序列化!");
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            System.out.println("序列化成功!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SmaliFileInfo deSerializeSmaliFile(String deSerName) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get("src\\com\\smaliDetail", deSerName)));
        System.out.println("反序列化成功!");
        return (SmaliFileInfo) objectInputStream.readObject();
    }

    protected File getSmaliFile(String fileName) {
        for (File file : smailFileList) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    public List<File> getSmaliDirList() {
        return smaliDirList;
    }
}