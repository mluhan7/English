import util.GetHtmlContentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part6 {
    private final static String[] Tran={"复数","过去式","过去分词","现在分词"};
    private Part6(){}
    public static List<List<String>> Distinguish(String[] list) throws Exception
    {
        int[] count=new int[Tran.length];
        List<List<String>>strings=new ArrayList<List<String>>();
        strings.add(new ArrayList<String>());
        strings.add(new ArrayList<String>());
        strings.add(new ArrayList<String>());
        strings.add(new ArrayList<String>());

        for(int j=0;j<Tran.length;j++)
        {
            count[j]=0;
        }
        for(int i=0;i<list.length;i++)
        {
            System.out.print(i+"    ");
            System.out.println(list[i]);
            String mean="";
            if(!list[i].equals("")&&list[i].length()!=1) mean= GetHtmlContentUtils.getTranslateResult(list[i]);
            for(int j=0;j<Tran.length;j++)
            {
                if(mean.indexOf(Tran[j])!=-1)
                {
                    count[j]++;
                    strings.get(j).add(list[i]);
                }
            }
        }
        return strings;
    }
}
