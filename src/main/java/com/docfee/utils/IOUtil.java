package com.docfee.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-24
 * Time: 下午1:05
 */
public class IOUtil {

    public static void closeQuietly(Closeable clo){
        if(clo!=null){
            try {
                clo.close();
            } catch (IOException e) {
                //ignore
            }
        }
    }
}
