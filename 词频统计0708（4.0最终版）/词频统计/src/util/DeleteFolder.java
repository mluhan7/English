package util;

import java.io.File;

/**
 *  根据路径删除指定的目录或文件，无论存在与否
 *@return 删除成功返回 true，否则返回 false。
 */
public class DeleteFolder{
    public static boolean deleteFile(String sPath) {
        boolean flag = false;  //false即文件未删除，存在
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;//文件删除成功
        }
        return flag;
    }
}