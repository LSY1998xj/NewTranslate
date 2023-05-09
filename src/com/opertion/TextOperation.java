package com.opertion;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class TextOperation {
    private static final String threeBytesChars = "…‘’。，（）！、—：？“”；";

    private TextOperation() {
    }

    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[一-龥]");
    }

    public static boolean isEnglishChar(char c) {
        return String.valueOf(c).matches("[a-zA-Z]");
    }

    public static boolean isMathsChar(char c) {
        return String.valueOf(c).matches("[0-9]");
    }

    public static boolean isThreeBytesChar(char c) {
        return threeBytesChars.contains(String.valueOf(c));
    }

    public static String changeStrToCompare(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isEnglishChar(c) || isMathsChar(c) || isChineseChar(c)) {
                sb.append(c);
            }
        }
        return sb.toString().trim();
    }

    public static boolean hasEnglishChar(String str,int num) {
        for (int i = num; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).matches("[a-zA-Z]"))
                return true;
        }
        return false;
    }

    public static boolean hasChineseChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).matches("[\u4e00-\u9fa5]"))
                return true;
        }
        return false;
    }

    public static int totalStrBytes(String str) {
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (TextOperation.isChineseChar(c) || isThreeBytesChar(c)) {
                total += 3;
            } else {
                total += 1;
            }
        }
        return total;
    }

    public static boolean checkEnWords(String str, String path) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        String line = reader.readLine();
        boolean flag = false;
        int lineNum = 1;
        while (line != null) {
            if (line.contains(str)) {
                System.out.println("存在于第" + lineNum + "行  " + "此行内容为:" + line);
                flag = true;
            }
            line = reader.readLine();
            lineNum++;
        }
        reader.close();
        return flag;
    }

    public static boolean checkEnWords(String str, String path, String path3) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        String line = reader.readLine();
        boolean flag = false;
        int lineNum = 1;
        while (line != null) {
            if (line.contains(str)) {
                System.out.println("存在于第" + lineNum + "行  " + "此行内容为:" + line);
                transWords(changeStrToCompare(line), path3);
                flag = true;
            }
            line = reader.readLine();
            lineNum++;
        }
        reader.close();
        return flag;
    }

    public static int totalFileBytes(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        String line = reader.readLine();
        int total = 0;
        while (line != null) {
            total += totalStrBytes(line);
            line = reader.readLine();
        }
        reader.close();
        return total;
    }

    public static void copyWords(String str) {
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static String addChar(int a, int b, String str) {
        StringBuilder sb = new StringBuilder(str);
        int count = 0;
        if (a > b) {
            System.out.println("error");
            System.exit(0);
        } else {
            for (int i = 0; i < (b - a); i++) {
                sb.append('.');
                count++;
            }
        }
        System.out.println("需要补充" + count + "位");
        return sb.toString();
    }

    public static boolean checkWords(String str, String path) throws IOException {
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
            String line1 = reader.readLine();
            boolean isExist = false;
            while (line1 != null) {
                if (line1.contains(String.valueOf(str.charAt(i))) && TextOperation.isChineseChar(str.charAt(i))) {
                    System.out.println(str.charAt(i) + "  存在");
                    reader.close();
                    isExist = true;
                    break;
                }
                line1 = reader.readLine();
            }
            if (!isExist && TextOperation.isChineseChar(str.charAt(i))) {
                System.out.println(str.charAt(i) + " 不存在");
                flag = false;
            }
            reader.close();
        }
        return flag;
    }

    public static void transWords(String str, String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8));
        String line = reader.readLine();
        boolean flag = false;
        while (line != null) {
            if (changeStrToCompare(line).equals(str)) {
                System.out.println("找到内容------>" + line);
                line = reader.readLine();
                System.out.println("推荐翻译------>" + line + "----->已复制至剪贴板" + '\n' + "================================================");
//                copyWords(line);
                flag = true;
                break;
            }
            line = reader.readLine();

        }
        if (!flag) {
            System.out.println("未查询到此句翻译！！！");
        }
        reader.close();
    }

}


