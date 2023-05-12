package com.opertion;

import com.opertion.TextOperation;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.sun.deploy.cache.Cache.copyFile;

public final class FileOperation {
    private FileOperation() {
    }

    public static void copy(String path1, String path2) throws IOException {
        try (
                FileChannel in = new FileInputStream(path1).getChannel();
                FileChannel out = new FileOutputStream(path2, true).getChannel();
        ) {
            out.transferFrom(in, 0, in.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(Path path1, Path path2) throws IOException {
        try (
                FileChannel in = new FileInputStream(path1.toString()).getChannel();
                FileChannel out = new FileOutputStream(path2.toString(), true).getChannel();
        ) {
            out.transferFrom(in, 0, in.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(String path1, List<String> pathList) throws IOException {
        for (String path : pathList) {
            try (
                    FileChannel in = new FileInputStream(path1).getChannel();
                    FileChannel out = new FileOutputStream(path, true).getChannel();
            ) {
                out.transferFrom(in, 0, in.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static int hasEnglishLine(String path, int num) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        int count = 0;
        while (line != null) {
            if (TextOperation.hasEnglishChar(line, num) && !TextOperation.hasChineseChar(line)) {
                System.out.println(line);
                count++;
            }
            line = bufferedReader.readLine();
        }
        return count;
    }

    public static int hasChineseLine(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        int count = 0;
        while (line != null) {
            if (TextOperation.hasChineseChar(line)) {
                System.out.println(line);
                count++;
            }
            line = bufferedReader.readLine();
        }
        return count;
    }

    public static String getLineStr(int LineNum) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("src\\Path.txt")), StandardCharsets.UTF_8));
        return getString(LineNum, bufferedReader);
    }


    private static String getString(int LineNum, BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        int count = 1;
        while (line != null) {
            if (count == LineNum) {
                return line;
            }
            line = bufferedReader.readLine();
            count++;
        }
        return null;
    }

    public static String getLineStr(int LineNum, String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        return getString(LineNum, bufferedReader);
    }

    public static List<String> fileToList(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        List<String> list = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            list.add(line);
            line = reader.readLine();
        }
        return list;
    }

    public static void listToFile(List<String> list, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(path)), StandardCharsets.UTF_8));
        for (String s : list) {
            writer.write(s);
            writer.newLine();
//            writer.flush();
        }
        writer.close();
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String createRandomFile(String Ext) throws IOException {
        List<String> fileNameAll = getNameListFromFiles("./");
        String fileName;
        while (true) {
            fileName = getRandomString(10) + Ext;
            if (!fileNameAll.toString().contains(fileName)) {
                return fileName;
            }
        }
    }

    public synchronized static List<String> fileToListLock(String path) throws IOException {
        String fileTemp = createRandomFile(".locale");
        Path pathTemp = Paths.get(fileTemp);
        FileLock fileLock = null;
        FileChannel fileChannel = null;
//        Files.deleteIfExists(pathTemp);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
//             FileChannel fileChannel = randomAccessFile.getChannel();
             FileOutputStream fileOutputStream = new FileOutputStream(fileTemp, true);
             FileChannel out = fileOutputStream.getChannel()) {
            fileChannel = randomAccessFile.getChannel();
            fileLock = fileChannel.lock();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int len = 0;
            while ((len = fileChannel.read(byteBuffer)) > 0) {
                System.out.println("文件内容为：" + new String(byteBuffer.array(), 0, len));
            }
            out.transferFrom(fileChannel, 0, fileChannel.size());
            fileLock.release();
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(pathTemp), StandardCharsets.UTF_8));
            List<String> list = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
            out.close();
            while (true) {
                if (Files.deleteIfExists(pathTemp)) {
                    fileChannel.close();
                    return list;
                } else {
                    System.out.println("失败！");
                }
            }
//            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isChangedFile(String path1, String path2) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path1)), StandardCharsets.UTF_8));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path2)), StandardCharsets.UTF_8));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        while (line1 != null && line2 != null) {
            if (!line1.equals(line2)) {
                return false;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }
        return true;
    }

    public static void MergeMessage(String str1, String str2, BufferedOutputStream bw) throws IOException {
        if (TextOperation.hasChineseChar(str1)) {
            bw.write(str1.getBytes());
        } else {
            bw.write(str2.getBytes());
        }
        bw.write('\n');
        bw.flush();
    }

    // 修改文件名
    public static List<String> getNameListFromFiles(String path) {  //得到某路径下所有的文件名
        List<String> fileName = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for (File value : files) {
            fileName.add(value.getName());
        }
        return fileName;
    }

    public static boolean isStringMatchEnd(String shortStr, String longStr) {   //将shortStr 与 longStr 从后向前对比，看是否一致
        if (shortStr.length() > longStr.length()) {
            return false;
        }
        String newLongStr = longStr.substring(longStr.length() - shortStr.length());
        return newLongStr.equals(shortStr);
    }

    public static String getFilePathByExt(String ext, String path) {
        List<String> fileName = getNameListFromFiles(path);
        for (String s : fileName) {
            if (isStringMatchEnd(ext, s)) {
                return path + s;
            }
        }
        return null;
    }

    public static List<Path> getFilePathByExtAll(String ext, String path) {
        List<String> fileName = getNameListFromFiles(path);
        List<Path> fileNameExt = new ArrayList<>();
        for (String s : fileName) {
            if (isStringMatchEnd(ext, s)) {
                Path path2 = Paths.get(path + s);
                System.out.println(path2);
                fileNameExt.add(path2);
            }
        }
        return fileNameExt;
    }

    public static void hasMessInFile(List<String> lists, Path path) throws IOException {
        for (String list : lists) {
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8));
            boolean isExist = false;
            String line1 = reader1.readLine();
            while (line1 != null) {
                if (line1.contains(list)) {
//                    System.out.println(list + "  找到！");
                    isExist = true;
                    break;
                }
                line1 = reader1.readLine();
            }
            if (!isExist) {
                System.out.println(list + "  不存在！");
            }
        }
    }

    public static boolean hasFileExt(File file, String... ext) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1) {
            for (String e : ext) {
                if (fileName.substring(fileName.lastIndexOf(".")).equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void findFilesContainExtAll(File file, List<File> allFileList, String... ext) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File value : files) {
                findFilesContainExtAll(value, allFileList, ext);
            }
        } else {
            if (hasFileExt(file, ext)) {
                allFileList.add(file.getAbsoluteFile());
            }
        }
    }

    public static List<File> getFilesContainExtAll(String path, String... ext) throws IOException {
        File file = new File(path);
        List<File> fileList = new ArrayList<>();
        findFilesContainExtAll(file, fileList, ext);
        return fileList;
    }

    public static void filesMoveToDirectory(List<File> fileList, String directoryPath) throws IOException {
        for (File file : fileList) {
            copyFile(new File(file.getAbsolutePath()), new File(directoryPath + file.getName()));
            System.out.println(file.getAbsoluteFile());
        }
    }

    public static boolean directoryHasFile(File file, String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File fileCompare : files) {
                System.out.println(fileCompare.getName());
                if (fileCompare.getName().equals(file.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<File> searchDirToList(String path, String ext) {
        File dir = new File(path);
        //定义集合
        List<File> list = new ArrayList<>();
        List<File> listFile = new ArrayList<>();
        //将参数dir对象添加到集合
        list.add(dir);
        //循环，条件：集合非空
        while (!list.isEmpty()) {
            //取出，并删除集合的第一个File对象
            File file = list.remove(0);
            //打印这个File对象
            if (!file.isDirectory() && hasFileExt(file, ext)) {
                listFile.add(file.getAbsoluteFile());
            }
            //如果此File对象是一个目录
            if (file.isDirectory()) {
                //获取此File对象下所有子文件和子目录的数组，并添加到集合的前面
                list.addAll(0, Arrays.asList(Objects.requireNonNull(file.listFiles())));
            }
            //继续下一次循环......
        }
        return listFile;
    }

}



