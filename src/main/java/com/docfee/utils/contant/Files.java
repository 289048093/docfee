package com.docfee.utils.contant;

import com.docfee.utils.FileUtil;

import java.io.File;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午1:37
 */
public enum Files{
    LOG_DIR(FileUtil.getClassPathFilePath("")+ File.separator+"logs"),
    DB_DIR("db");

    public String value;
    private Files(String s){
        value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
