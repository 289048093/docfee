package com.docfee.controller;

import com.docfee.entity.ProductEntity;
import com.docfee.service.ProductService;
import com.docfee.utils.LogUtil;
import com.docfee.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:24
 */
public class ProductController {

    private ProductService productService = new ProductService();

    public void add(HttpServletRequest req, HttpServletResponse res) {
        try {
            productService.add( parseReq(req));
        } catch (SQLException e) {
            LogUtil.error(e);
        }
        try {
            res.setContentType("text/html;UTF-8;");
            res.getOutputStream().print("add success 添加成功");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void query(HttpServletRequest req, HttpServletResponse res){
        try {
            productService.query();
        } catch (SQLException e) {
            LogUtil.error(e);
        }
    }

    private ProductEntity parseReq(HttpServletRequest req) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(req.getParameter("name"));
        String price = req.getParameter("price");
        if (!StringUtil.isBlank(price)) productEntity.setPrice(new BigDecimal(price));
        String rate = req.getParameter("rate");
        if (!StringUtil.isBlank(rate)) productEntity.setDefaultRate(new BigDecimal(rate));
        return productEntity;
    }

    public void delete(HttpServletRequest req, HttpServletResponse res) {
        try {
            productService.delete( parseReq(req));
        } catch (SQLException e) {
            LogUtil.error(e);
        }
    }

}
