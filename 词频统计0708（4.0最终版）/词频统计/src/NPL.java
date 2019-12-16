import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
public class NPL {


    /**
     * 词形还原
     * @return List<String> 分词、提取词形还原后的结果
     * */
    public static List<String> split(String text){
        //单词集合
        List<String> wordslist = new ArrayList<>();
        //StanfordCoreNLP词形还原
        Properties props = new Properties();  // set up pipeline properties
        props.put("annotators", "tokenize, ssplit, pos, lemma");   //分词、分句、词性标注和次元信息。
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> words = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap word_temp: words) {
            for (CoreLabel token: word_temp.get(CoreAnnotations.TokensAnnotation.class)) {
                String lema = token.get(CoreAnnotations.TextAnnotation.class);  // 获取对应上面word的词元信息，即我所需要的词形还原后的单词
                wordslist.add(lema);
            }
        }
        return wordslist;
    }

    public static List<String> getlema(String text)
    {
        //单词集合
        List<String> wordslist = new ArrayList<>();
        //StanfordCoreNLP词形还原
        Properties props = new Properties();  // set up pipeline properties
        props.put("annotators", "tokenize, ssplit, pos, lemma");   //分词、分句、词性标注和次元信息。
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> words = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap word_temp: words) {
            for (CoreLabel token: word_temp.get(CoreAnnotations.TokensAnnotation.class)) {
                String lema = token.get(CoreAnnotations.LemmaAnnotation.class);  // 获取对应上面word的词元信息，即我所需要的词形还原后的单词
                wordslist.add(lema);
            }
        }
        return wordslist;
    }
}
