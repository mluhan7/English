import data.CET4;
import data.CET6;

import java.util.ArrayList;
import java.util.List;

public class ppp5 {
    public static List<String> isIn4(String[] list)
    {
        List<String>stringList=new ArrayList<String>();
        for(int i=0;i<list.length;i++)
        {
            for(int j=0;j<CET4.cet4.length;j++)
            {
                if(list[i].equals(CET4.cet4[j])) break;
                if(j==CET4.cet4.length-1)   stringList.add(list[i]);
            }
        }
        return stringList;
    }

    public static List<String> isIn6(String[] list)
    {
        List<String>stringList=new ArrayList<String>();
        for(int i=0;i<list.length;i++)
        {
            for(int j = 0; j< CET6.cet6.length; j++)
            {
                if(list[i].equals(CET6.cet6[j])) break;
                if(j==CET6.cet6.length-1)   stringList.add(list[i]);
            }
        }
        return stringList;
    }

}
