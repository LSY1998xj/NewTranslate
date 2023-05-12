package com.main;

import com.opertion.FileOperation;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AddOutMessage {
    public static boolean strContainType(String str) {
        String[] split = str.split(" ");
        for (String s : split) {
            if (s.equals("boolean") || s.equals("String") ||
                    s.equals("Char") || s.equals("void") ||
                    s.equals("int") || s.equals("List")) {
                return true;
            }
        }

        return false;
    }

    public static String onCreateTest(Path path, String fileName) {
        if (fileName.equals("onCreate")) {
            return path.getFileName() + "." + fileName;
        }
        return fileName;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "F:\\studio\\dlqs\\app\\src\\main\\java\\org\\cocos2dx\\cpp\\BaseActivity.java";
        List<String> fileList = FileOperation.fileToList(filePath);
        for (int i = 0; i < fileList.size(); i++) {
            String strTrim = fileList.get(i).trim();
            String fileString = fileList.get(i);
            if (strContainType(fileString) && strTrim.lastIndexOf('{') == strTrim.length() - 1) {
                System.out.println(fileString);
                String functionName = fileString.substring(fileString.substring(0, fileString.lastIndexOf('(')).lastIndexOf(" ") + 1, fileString.lastIndexOf('('));
                functionName = onCreateTest(Paths.get(filePath), functionName);
                String addStr = "System.out.println(\"" + functionName + "调用" + "\");";
                fileString += addStr;
                fileList.set(i, fileString);
            }
        }
        for (String str : fileList) {
            System.out.println(str);
        }
        FileOperation.listToFile(fileList, filePath);
    }
}
