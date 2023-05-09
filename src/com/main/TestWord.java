package com.main;

import com.opertion.FileOperation;
import com.thread.SaveFile;
import com.opertion.TextOperation;
import com.thread.TotalFileBytesCompare;

import java.io.IOException;
import java.util.Scanner;

public class TestWord {
    public static final String location = "src\\Path.txt";
    public static final String path1;
    public static final String path2;
    public static final String path3;
    public static final String path4;
    public static final String path5;

    static {
        try {
            path1 = FileOperation.getLineStr(1, location);
            path2 = FileOperation.getLineStr(2, location);
            path3 = FileOperation.getLineStr(3, location);
            path4 = FileOperation.getLineStr(4, location);//locale文件的路径
            path5 = FileOperation.getLineStr(5, location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        int allBytes = TextOperation.totalFileBytes(path2);
        System.out.println("已载入文件，总字节数为:  " + allBytes);
        TotalFileBytesCompare totalFileBytesCompare = new TotalFileBytesCompare(path2, allBytes);
        SaveFile saveFile = new SaveFile();
        saveFile.start();
        totalFileBytesCompare.start();
        while (true) {
            String[] result = getInputNum("====================请输入需翻译内容====================");
            int total = Integer.parseInt(result[0]);
            if (!TextOperation.checkEnWords(result[1], path2, path3)) {
                System.out.println("文件中不存在此句！请重新输入");
            } else {
                System.out.println("输入内容字节长度为:" + total + ",换算成汉字：" + total / 3 + "+" + total % 3);
                while (true) {
                    result = getInputNum("====================请输入翻译结果====================");
                    int total2 = Integer.parseInt(result[0]);
                    if (total2 <= total && TextOperation.checkWords(result[1], path1)) {
                        System.out.println("====================检测通过=====================");
                        String array = TextOperation.addChar(total2, total, result[1]);
//                        TextOperation.copyWords(array);
                        System.out.println(array + "                 ");
                        break;
                    } else {
                        System.out.println("内容有误！超出" + (total2 - total) + "字节 请重新输入");
//                        System.out.println("所输入字节数为:" + total2 + "  相差" + Math.abs((total2-total))+"个字节");
                    }
                }
            }
//            int allBytes2 = TextOperation.totalWords(path2);
            if (!totalFileBytesCompare.isSame) {
                System.out.println("文件字节数有误！请排查");
                break;
            }
        }
    }

    public static String[] getInputNum(String str) {
        System.out.println(str);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return new String[]{String.valueOf(TextOperation.totalStrBytes(line)), line};
    }
}