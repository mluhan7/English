import java.util.HashMap;
import java.util.List;

public class Part7 {
    private Part7(){}
    public static HashMap<String,Integer> Length(String[] list,int length)
    {
        HashMap<String,Integer>hashMap=new HashMap<String,Integer>();
        for(int i=0;i<list.length;i++)
        {
            if(list[i].length()==length)
            {
                Integer value=hashMap.get(list[i]);
                if(value ==null)
                {
                    hashMap.put(list[i],1);
                }
                else{
                    value++;
                    hashMap.put(list[i],value);
                }

            }
        }
        return hashMap;
    }
    public static HashMap<String,Integer> Head(String[] list,String head)
    {
        HashMap<String,Integer>hashMap=new HashMap<String,Integer>();
        for(int i=0;i<list.length;i++)
        {
            if(list[i].startsWith(head))
            {
                Integer value=hashMap.get(list[i]);
                if(value ==null)
                {
                    hashMap.put(list[i],1);
                }
                else{
                    value++;
                    hashMap.put(list[i],value);
                }
            }
        }
        return hashMap;
    }

    public static HashMap<String,Integer> Tail(String[] list,String tail)
    {
        HashMap<String,Integer>hashMap=new HashMap<String,Integer>();
        for(int i=0;i<list.length;i++)
        {
            if(list[i].endsWith(tail))
            {
                Integer value=hashMap.get(list[i]);
                if(value ==null)
                {
                    hashMap.put(list[i],1);
                }
                else{
                    value++;
                    hashMap.put(list[i],value);
                }
            }
        }
        return hashMap;
    }

    public static int Find(String[] list,String word)
    {
        int count=0;
        for(int i=0;i<list.length;i++)
            if(list[i].equals(word))    count++;

        return count;
    }
}
