package com.docfee.controller;

import com.docfee.utils.LogUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:28
 */
public class Servlet extends HttpServlet {
    ProductController productController = new ProductController();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/")+1) ;
        String action = null;
        if (uri.matches("^doctor.+$.do")) {
            action = uri.substring("doctor.".length(), uri.length() - 3);


        } else if (uri.matches("^product.+$")) {
            action = uri.substring("product.".length(), uri.length() - 3);
            try {
                Method method = ProductController.class.getMethod(action);
                method.invoke(productController,request,response);
            } catch (NoSuchMethodException e) {
                go404(request,response);
            } catch (InvocationTargetException e) {
                LogUtil.error(e);
            } catch (IllegalAccessException e) {
                LogUtil.error(e);
            }
        } else if (uri.matches("^count.+")) {

        } else {

        }
    }

    private void go404(HttpServletRequest request, HttpServletResponse response) {

    }


}
