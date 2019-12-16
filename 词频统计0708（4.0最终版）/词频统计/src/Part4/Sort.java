package Part4;


import util.DeleteFolder;
import util.WriterFileUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Sort {

    /*
    static String  path = "G:\\暑训\\output\\output.txt";


    static String outputpath = "G:\\暑训\\output\\output.txt";
    static  String ExceedWordsPath = "G:\\暑训\\output\\ExceedWords.txt";
    static String UpSortpath = "G:\\暑训\\output\\UpSortWords.txt";
    static String DownSortpath = "G:\\暑训\\output\\DownSortWords.txt";
    static String CharUpSortpath = "G:\\暑训\\output\\CharUpSort.txt";
    static String CharDownSortpath = "G:\\暑训\\output\\CharDownSort.txt";
    static String vocabulary = "cet4";*/

    //词频降序
    public static String DownSortMap(Map<String,Integer> oldmap) {
        System.out.println("降序");
        oldmap.remove("Total");
        oldmap.remove("Different");
        String article = "";
        //WriterFileUtil writerFileUtil = new WriterFileUtil();

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();  //降序
            }
        });

        for (int i = 0; i < list.size(); i++) {
            article = article+list.get(i).getKey() + ": " + list.get(i).getValue()+"\n";
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }

        /*写进本地文档中
        if (DeleteFolder.deleteFile(DownSortpath)) {
            for (int i = 0; i < list.size(); i++) {

                writerFileUtil.writeFile(list.get(i).getKey() + ": " + list.get(i).getValue(),DownSortpath);
            }
        }*/

        return article;
    }

    //词频升序
    public static String UpSortMap(Map<String,Integer> oldmap) {
        //WriterFileUtil writerFileUtil = new WriterFileUtil();
        oldmap.remove("Total");
        oldmap.remove("Different");
        String article = "";
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();  //降序
            }
        });

        for (int i = 0; i < list.size(); i++) {
            article = article+list.get(i).getKey() + ": " + list.get(i).getValue()+"\n";
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
        /*
        if (DeleteFolder.deleteFile(path)) {
            for (int i = 0; i < list.size(); i++) {

                writerFileUtil.writeFile(list.get(i).getKey() + ": " + list.get(i).getValue(),path);
                //System
            }
            if (DeleteFolder.deleteFile(UpSortpath)) {
                for (int i = 0; i < list.size(); i++) {

                    writerFileUtil.writeFile(list.get(i).getKey() + ": " + list.get(i).getValue(),UpSortpath);
                }
            }
        }*/

            return article;
    }

    public static String CharUpSortMap(Map<String,Integer> oldmap){
       // WriterFileUtil writerFileUtil = new WriterFileUtil();
        String article = "";
        oldmap.remove("Total");
        oldmap.remove("Different");
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                String s1 = o1.getKey().toLowerCase();
                String s2 = o2.getKey().toLowerCase();
                return s1.compareTo(s2);//从a-z的排
            }
        });

        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).getKey());
            article = article+list.get(i).getKey()+"\n";
        }
        /*
        if(DeleteFolder.deleteFile(CharUpSortpath)){
            for (int i = 0; i < list.size(); i++) {

                writerFileUtil.writeFile(list.get(i).getKey(),CharUpSortpath);
            }
        }*/


        return article;
    }

    public static String CharDownSortMap(Map<String,Integer> oldmap){
        //WriterFileUtil writerFileUtil = new WriterFileUtil();
        String article = "";
        oldmap.remove("Different");
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                String s1 = o1.getKey().toLowerCase();
                String s2 = o2.getKey().toLowerCase();
                return s1.compareTo(s2);//从a-z的排
            }
        });

        for(int i = list.size()-1; i>=0;i--){
            System.out.println(list.get(i).getKey());
            article = article+list.get(i).getKey()+"\n";
        }
        /*
        if(DeleteFolder.deleteFile(CharDownSortpath)) {
            for (int i = list.size() - 1; i >= 0; i--) {

                writerFileUtil.writeFile(list.get(i).getKey(),CharDownSortpath);
            }
        }*/

        return article;
    }




}
