import java.util.ArrayList;
import java.util.List;

public class Part8 {

    public static List<String> Compare(String[] list1, String[] list2)
    {
        List<String>list=new ArrayList<String>();
        list.add(list1.length+"");
        list.add(list2.length+"");
        for(int i=0;i<list1.length;i++)
        {
            for(int j=0;j<list2.length;j++)
            {
                if(list1[i].equals(list2[j]))
                {
                    list.add(list1[i]);
                    continue;
                }
            }
        }
        return list;
    }
}
