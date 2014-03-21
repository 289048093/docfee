package com.docfee.controller;

import com.docfee.utils.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:49
 */
public class ServletTest {

    @Test
    public void packageTest(){
        Package pac = Package.getPackage("com.docfee.controller");
//        System.out.println(pac.toString());
//        System.out.println(Arrays.toString(pac.getPackages()));
        File dir =new File(FileUtil.getClassPathFilePath("com.docfee.controller".replace(".", "/")));
        System.out.println(dir.listFiles()[0].getAbsolutePath());
        System.out.println(new File(".").getAbsolutePath());
    }

}
