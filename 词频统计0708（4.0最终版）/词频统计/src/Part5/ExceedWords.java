package Part5;

import JDBC.DBTest;
import util.DeleteFolder;
import util.WriterFileUtil;


import java.util.ArrayList;
import java.util.Map;

import static util.SpiltUtil.getlema;

public class ExceedWords {

    static String  ExceedWords = "F:\\实训项目\\英文文档\\output\\ExceedWords.txt";
    public static String SortEceedWords(Map<String,Integer> oldmap,String vocabulary){

       // WriterFileUtil writerFileUtil = new WriterFileUtil();
        String article = "";
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());
        ArrayList<String> wordslist = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getKey();
            String reword;

            reword = getlema(word);
            if(reword.length()>1) {
                if (DBTest.SortWords(reword,vocabulary)) {
                    article = article+word+"\n";
                    System.out.println("超纲词：" + word);
                    wordslist.add(word);
                }
            }
        }
        System.out.println("长度："+wordslist.size());

        /*if (DeleteFolder.deleteFile(ExceedWords)) {
            for (int i = 0; i < wordslist.size(); i++) {

                writerFileUtil.writeFile(wordslist.get(i) + "\n" ,ExceedWords);
            }
        }*/
        return article;
        }



    }
