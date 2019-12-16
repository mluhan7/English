package Part3;


import util.ReadDoc;
import util.ReadPdf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cute
 *
 *
 * 实现从文件中读入英文文章，统计单词个数,并按值从大到小输出
 */

public class CountWord {

    public static Map<String, Integer> countword(String filename) throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if(filename.lastIndexOf(".txt")>0) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            StringBuffer sb = new StringBuffer();
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    sb = sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Pattern pattern = Pattern.compile("[a-zA-Z']+");
            Matcher matcher = pattern.matcher(sb);
            String word = "";
            Integer num = null;
            int total = 0;

            while (matcher.find()) {
                word = matcher.group();
                total++;
                if (map.containsKey(word)) {
                    num = map.get(word);
                    num += 1;
                } else {
                    num = 1;
                }
                map.put(word, num);
            }
            map.put("Total", total);
            //map.put("Different",map.size()-1);

        /*
        Sort.CharDownSortMap(map);
        Sort.DownSortMap(map);
        Sort.UpSortMap(map);
        ExceedWords.SortEceedWords(map);*/
        }
        if(filename.lastIndexOf(".doc")>0){
            String line = ReadDoc.readWord(filename);
            StringBuffer sb = new StringBuffer();
            sb.append(line);
            Pattern pattern = Pattern.compile("[a-zA-Z']+");
            Matcher matcher = pattern.matcher(sb);
            String word = "";
            Integer num = null;
            int total = 0;

            while (matcher.find()) {
                word = matcher.group();
                total++;
                if (map.containsKey(word)) {
                    num = map.get(word);
                    num += 1;
                } else {
                    num = 1;
                }
                map.put(word, num);
            }
            map.put("Total", total);
        }


        if(filename.lastIndexOf(".pdf")>0){
            String line = ReadPdf.readPdfToTxt(filename);
            StringBuffer sb = new StringBuffer();
            sb.append(line);
            Pattern pattern = Pattern.compile("[a-zA-Z']+");
            Matcher matcher = pattern.matcher(sb);
            String word = "";
            Integer num = null;
            int total = 0;

            while (matcher.find()) {
                word = matcher.group();
                total++;
                if (map.containsKey(word)) {
                    num = map.get(word);
                    num += 1;
                } else {
                    num = 1;
                }
                map.put(word, num);
            }
            map.put("Total", total);
        }
    return map;
    }





    public static int FindWord(String word,String path) throws IOException {
        Map<String,Integer> map = new HashMap<>();
        map = countword(path);
        int count2;
        count2 = map.get(word);
        System.out.println(word);
        System.out.println(path);
        System.out.println(count2);
        return count2;
    }


/*

    public static void main(String[] args) {

        System.out.println(countword("F:\\实训项目\\英文文档\\四级文库\\news.txt"));
        System.out.println("hahahaha");
    }*/
}
