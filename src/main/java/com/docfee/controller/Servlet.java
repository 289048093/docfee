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
    static ProductController productController = new ProductController();
    static DoctorController doctorController = new DoctorController();

    static CountController countController = new CountController();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/")+1) ;
        String action = null;
        if (uri.matches("^doctor.+.do$")) {
            action = uri.substring("doctor.".length(), uri.length() - 3);
            try {
                Method method = DoctorController.class.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
                if(method!=null) method.invoke(doctorController,request,response);
            } catch (NoSuchMethodException e) {
                go404(request,response);
            } catch (InvocationTargetException e) {
                LogUtil.error(e);
            } catch (IllegalAccessException e) {
                LogUtil.error(e);
            }

        } else if (uri.matches("^product.+.do$")) {
            action = uri.substring("product.".length(), uri.length() - 3);
            try {
                Method method = ProductController.class.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
               if(method!=null) method.invoke(productController,request,response);
            } catch (NoSuchMethodException e) {
                go404(request,response);
            } catch (InvocationTargetException e) {
                LogUtil.error(e);
            } catch (IllegalAccessException e) {
                LogUtil.error(e);
            }
        } else if (uri.matches("^count.+.do$")) {
            action = uri.substring("count.".length(), uri.length() - 3);
            try {
                Method method = CountController.class.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
                if(method!=null) method.invoke(countController,request,response);
            } catch (NoSuchMethodException e) {
                go404(request,response);
            } catch (InvocationTargetException e) {
                LogUtil.error(e);
            } catch (IllegalAccessException e) {
                LogUtil.error(e);
            }
        } else {

        }
    }

    private void go404(HttpServletRequest request, HttpServletResponse response) {

    }


}
