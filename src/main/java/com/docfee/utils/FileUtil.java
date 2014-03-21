package com.docfee.utils;

import java.io.File;
import java.net.URL;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 上午11:53
 */
public class FileUtil {


    public static String getClassPathFilePath(String fileName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        if (url == null) {
            url = FileUtil.class.getClassLoader().getResource(fileName);
        }
        if (url != null) {
            return url.getFile();
        } else {
            return null;
        }
    }

    public static boolean mkDir(String dirName){
        File file =  new File(dirName);
        return !StringUtil.isBlank(dirName) && file.mkdir();
    }


}
