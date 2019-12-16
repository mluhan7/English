

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FileRead {
    private FileRead(){}
    public static  String[] Readsplit(String path_name) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path_name), "UTF-8"));
        String file="";
        String readline;
        readline=br.readLine();
        while(readline!=null)
        {
            file+=readline;
            readline=br.readLine();
        }
        //String[] list=file.split(" ");
        List<String> list_s= NPL.split(file);

        String[] list=new String[list_s.size()];

        for(int i=0;i<list_s.size();i++)    list[i]=list_s.get(i);

        return list;
    }

    public static  String[] Readgetlema(String path_name) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path_name), "UTF-8"));
        String file="";
        String readline;
        readline=br.readLine();
        while(readline!=null)
        {
            file+=readline;
            readline=br.readLine();
        }
        //String[] list=file.split(" ");
        List<String> list_s= NPL.getlema(file);

        String[] list=new String[list_s.size()];

        for(int i=0;i<list_s.size();i++)    list[i]=list_s.get(i);

        return list;
    }
}
