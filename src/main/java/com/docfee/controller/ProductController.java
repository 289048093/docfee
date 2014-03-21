package com.docfee.controller;

import com.docfee.entity.ProductEntity;
import com.docfee.service.ProductService;
import com.docfee.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:24
 */
public class ProductController {

    private ProductService productService = new ProductService();

    public void add(HttpServletRequest req, HttpServletResponse res) {
        productService.add( parseReq(req));
    }

    public void query(HttpServletRequest req, HttpServletResponse res){
        productService.query(parseReq(req));
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
        productService.delete( parseReq(req));
    }

}
