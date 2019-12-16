import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void read()throws Exception
    {
        List<String>list=new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\HP\\Desktop\\数据存储.txt"), "UTF-8"));
        String readline;
        readline=br.readLine();
        while(readline!=null)
        {
            list.add(readline);
            readline=br.readLine();
        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        for(int i=0;i<list.size();i++)
        {
            System.out.print("\""+list.get(i)+"\",");
        }
    }
}
