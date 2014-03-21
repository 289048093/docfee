package com.docfee.controller;

import com.docfee.mvc.RequestMapping;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:39
 */
public class ProductControllerTest {

    @Test
    public void addTest() throws InvocationTargetException, IllegalAccessException {
        ProductController controller = new ProductController();
        Method[] methods = controller.getClass().getDeclaredMethods();
        for(Method method:methods){
            System.out.println(method.getAnnotation(RequestMapping.class).value());
            System.out.println(method);
            method.invoke(controller);
        }

    }



}
