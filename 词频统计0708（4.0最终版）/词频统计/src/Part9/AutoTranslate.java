package Part9;
import util.Translate.TransApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AutoTranslate {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20190708000315818";
    private static final String SECURITY_KEY = "DSMhTp12ni2Gcg88o7S1";

    public static String Translation(String query) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        //String query = "I'm so beautiful";
        String str = api.getTransResult(query, "en", "zh"); //英文翻译中文
        System.out.println(str);//输出结果，即json字段
        JsonObject jsonObj = (JsonObject)new JsonParser().parse(str);//解析json字段
        String res = jsonObj.get("trans_result").toString();//获取json字段中的 result字段，因为result字段本身即是一个json数组字段，所以要进一步解析
        JsonArray js = new JsonParser().parse(res).getAsJsonArray();//解析json数组字段
        jsonObj = (JsonObject)js.get(0);//result数组中只有一个元素，所以直接取第一个元素
        System.out.println(jsonObj.get("dst").getAsString());//得到dst字段，即译文，并输出
        return jsonObj.get("dst").getAsString();
    }

    public static void main(String[] args) {
        AutoTranslate.Translation("When winter comes, especially the cold wind blows, I don't want to go out of the house. Though the outdoor activity is limited, I still can find a lot of things to do.");
    }
}