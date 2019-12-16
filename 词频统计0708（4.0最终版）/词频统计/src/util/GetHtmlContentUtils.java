package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHtmlContentUtils {
    private final static String PreUrl="http://dict-co.iciba.com/api/dictionary.php?w=";                        //百度搜索URL
    private final static String key="&key=54E4B0B3445D08F05BC60978254C75FC";
    private final static String TransResultStartFlag="<acceptation>";
    private final static String TransResultEndFlag="</acceptation>";


    private GetHtmlContentUtils(){}

    public static String getTranslateResult(String urlString) throws Exception {    //传入要搜索的单词
        URL url = new URL(PreUrl+urlString+key);            //生成完整的URL
        // 打开URL
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        // 得到输入流，即获得了网页的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
        //n = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        String preLine="";
        String line;
        int flag=1;
        // 读取输入流的数据，并显示
        String content="";          //翻译结果
        while ((line = reader.readLine()) != null) {            //获取翻译结果的算法

            if(line.indexOf(TransResultStartFlag)!=-1)
            {
                line=line.substring(TransResultStartFlag.length(),line.length());
                //content+=line;
                //content=content.substring(TransResultStartFlag.length()+2,content.indexOf(TransResultEndFlag));
                content+=line;
            }
        }
        return content;//返回翻译结果
    }

}
