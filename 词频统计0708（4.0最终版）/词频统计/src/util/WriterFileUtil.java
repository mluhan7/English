package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterFileUtil {
    public static void writeFile(String str,String Path) {
        //String Path = "F:\\实训项目\\英文文档\\四级文库\\output.txt";
            try {
                File writeName = new File(Path); // 相对路径，如果没有则要建立一个新的output.txt文件
                writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
                try (FileWriter writer = new FileWriter(writeName, true);
                     BufferedWriter out = new BufferedWriter(writer)
                ) {
                    out.write(str + "\r\n"); // \r\n即为换行
                    //out.append(str+"\r\n");
                    //out.write("\r\n"); // \r\n即为换行
                    out.flush(); // 把缓存区内容压入文件
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
/*
    public static void main(String[] args) {
        String s = "ate"+":"+1;
        String a = "ate"+":"+12;
        WriterFileUtil.writeFile(s);
        WriterFileUtil.writeFile(a);
    }*/
}
