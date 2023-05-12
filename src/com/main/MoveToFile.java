package com.main;

import com.opertion.FileOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MoveToFile {
    public static void main(String[] args) throws IOException {
        String fileFnt = FileOperation.getFilePathByExt("fnt", TestWord.path5);
        List<Path> filePngPath = FileOperation.getFilePathByExtAll("png", TestWord.path5);
//        Path filePng = Paths.get(Objects.requireNonNull(FileOperation.getFilePathByExtAll("png", TestWord.path5)));
        List<String> list = new ArrayList<>();
        list.add(TestWord.path4 + "fnt_num_bruskovaya.fnt");
        list.add(TestWord.path4 + "fnt_num_helios_cond.fnt");
        list.add(TestWord.path4 + "fnt_txt_bruskovaya.fnt");
        list.add(TestWord.path4 + "fnt_txt_georgia.fnt");
        list.add(TestWord.path4 + "fnt_txt_georgia_bold.fnt");

        FileOperation.copy(fileFnt, list);
        assert fileFnt != null;
        Files.delete(Paths.get(fileFnt));

        for (Path s : filePngPath) {
            FileOperation.copy(String.valueOf(s), TestWord.path4 + s.getFileName());
            Files.delete(s);
        }
    }

    public static void moveFile(String path1, String path2) throws IOException {
        FileOperation.copy(path1, TestWord.path5 + path2);
        System.out.println(path1);
        System.out.println(TestWord.path5 + path2);
    }


}