package com.smaliDetail;
import com.opertion.FileOperation;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class SmaliFile extends SmaliFileInfo {
    //    private static final long serialVersionUID = 1L;
    private final String smaliFilePath;
    private final List<File> smailFileList;
    private List<File> smailFileHas0local;
    private Map<File, List<String>> smaliFile0localInfo;
    public volatile static boolean[] initComplete = {false, false};
    ExecutorService service;

    public SmaliFile(String path) throws IOException, InterruptedException {
        super(path);
        service = new ThreadPoolExecutor(1000, 2000, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
        this.smaliFilePath = super.getSmaliFilePath();
        this.smailFileList = super.getSmailFileList();
        this.smailFileHas0local = super.getSmailFileHas0local();
        this.smaliFile0localInfo = super.getSmaliFile0localInfo();
    }

    private String getMethodName(String strLine) {
        int lastLeftBracketIndex = strLine.lastIndexOf('(');
        return strLine.substring(strLine.substring(0, lastLeftBracketIndex).lastIndexOf(" ") + 1, lastLeftBracketIndex);
    }

    private void methodNameInfoOutput(File file) throws IOException {
        List<String> list = FileOperation.fileToList(String.valueOf(file));
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            String strLine = list.get(i);
            if (strLine.contains(".method") && list.get(i + 1).contains(".locals") && !list.get(i + 2).contains("调用") && !list.get(i + 1).contains(".locals 0")) {
                flag = true;
                String addContent = getMethodName(strLine);
                list.add(i + 2, "    const-string v0, \"" + file.getName() + '.' + addContent + "调用\"");
                list.add(i + 3, "    invoke-static {v0}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V");
            }
        }
        if (flag) {
            service.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        FileOperation.listToFile(list, file.getAbsolutePath());
                        System.out.println(file.getName() + "已插桩!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }));
        }

    }

    public void addSmaliFileDetailInfo() throws IOException {
        for (File file : smailFileList) {
            methodNameInfoOutput(file);
        }
    }

    public void methodNameOutputInfoDelete() throws IOException {
        for (File file : smailFileList) {
            boolean flag = false;
            List<String> list = FileOperation.fileToList(String.valueOf(file));
            for (int i = 0; i < list.size(); i++) {
                flag = true;
                if (list.get(i).contains("调用")) {
                    list.remove(i);
                    list.remove(i);
                    i -= 2;
                }
            }
            if (flag) {
                service.submit(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FileOperation.listToFile(list, file.getAbsolutePath());
                            System.out.println(file.getName() + "已删除!");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
            }
        }
    }

    public boolean addSmaliFileDetail(String fileName) throws IOException {
        File file = getSmaliFile(fileName);
        if (file == null) {
            return false;
        } else {
            methodNameInfoOutput(file);
            return true;
        }
    }

    protected File getSmaliFile(String fileName) {
        for (File file : smailFileList) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        SmaliFile smaliFile = new SmaliFile("C:\\Users\\admin\\Desktop\\YZJS\\fei-yue-tai-kong\\app\\src\\main\\smali");
        smaliFile.addSmaliFileDetailInfo();
        smaliFile.service.shutdown();
    }
}