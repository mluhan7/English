package util;

import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadDoc {
    public static String readWord(String path) {
        String buffer = "";

        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else {
                System.out.println("请选择后缀为.doc的文件");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }
    /*
    public static void main(String[] args) {
        ReadDoc tp = new ReadDoc();
        String content = tp.readWord("D:\\1.doc"); //文件存放的地址
        System.out.println("content===="+content);
    }*/
}