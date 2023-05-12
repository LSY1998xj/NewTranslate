package com.main;

import com.opertion.FileOperation;
import com.opertion.TextOperation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class FileCompare {
    public static void main(String[] args) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get("C:\\Users\\admin\\Desktop\\file.txt")));
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(Objects.requireNonNull(FileOperation.getLineStr(2)))), StandardCharsets.UTF_8));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("C:\\Users\\admin\\Desktop\\测试.locale")), StandardCharsets.UTF_8));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        int lineNum = 1;
        int differentNum = 0;
        ArrayList<Integer> differentLine = new ArrayList<>();
        ArrayList<Integer> differentLineBytes = new ArrayList<>();
        System.out.println("开始对比:");
        System.out.println("===================");
        while (line1 != null && line2 != null) {
            int lineBytesNum1 = TextOperation.totalStrBytes(line1);
            int lineBytesNum2 = TextOperation.totalStrBytes(line2);
            if (line1.equals(line2)) {
//                System.out.println("第" + lineNum + "行相同");
                bufferedOutputStream.write(line1.getBytes());
                bufferedOutputStream.write('\n');
                bufferedOutputStream.flush();
            } else {
                if (lineBytesNum1 != lineBytesNum2) {
                    System.out.println("第" + lineNum + "行字节数不同分别为: " + lineBytesNum1 + ',' + lineBytesNum2);
                    System.out.println(line1);
                    System.out.println(line2);
                    differentLineBytes.add(lineNum);
                }
//                System.out.println("第" + lineNum + "行不同");
                FileOperation.MergeMessage(line1, line2, bufferedOutputStream);
                differentNum += 1;
                differentLine.add(lineNum);
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            lineNum++;
        }
        System.out.println("一共对比了" + lineNum + "行" + "\n" + "有" + differentNum + "行不同分别为:");
        int temp = 1;
        for (Integer integer : differentLine) {
            System.out.print(integer + " ");
            if (temp % 30 == 0) {
                System.out.println();
            }
            temp++;
        }
        System.out.println('\n' + "字节数不同的行有"+differentLineBytes.size()+"分别为： ");
        temp = 1;
        for (Integer result : differentLineBytes) {
            System.out.print(result + " ");
            if (temp % 10 == 0) {
                System.out.println(" ");
            }
            temp ++;
        }
        System.out.println();
        System.out.println("============================" + "\n" + "已完成对比,生成新文件完成！");
        reader1.close();
        reader2.close();
        bufferedOutputStream.close();
    }
}
