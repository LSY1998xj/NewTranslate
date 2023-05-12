package com.main;

import com.opertion.FileOperation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class smali {
    public static void main(String[] args) throws IOException {
        String smaliPath = "C:\\Users\\admin\\Desktop\\YZJS\\Drake-n-Trap\\app\\src\\main\\smali";
        List<File> list = FileOperation.getFilesContainExtAll(smaliPath, ".smali");
        for (File file : list) {
            System.out.println(file.getName());
        }
    }
}
