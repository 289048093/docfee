package com.docfee.controller;

import com.docfee.entity.ProductEntity;
import com.docfee.service.ProductService;
import com.docfee.utils.LogUtil;
import com.docfee.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:24
 */
public class ProductController {

    private ProductService productService = new ProductService();

    public ProductService getProductService() {
        return productService;
    }

    public void add(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        productService.add(parseReq(req));
        res.setContentType("text/html;UTF-8;");
        res.getOutputStream().write("add success 添加成功".getBytes("UTF-8"));
    }

    public void query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<ProductEntity> list = productService.query();
//            res.getWriter().print(list);
            req.setAttribute("products", list);
        } catch (SQLException e) {
            LogUtil.error(e);
//        } catch (IOException e) {
//            LogUtil.error(e);
        }
        req.getRequestDispatcher("product/query.jsp").forward(req, res);

    }

    private ProductEntity parseReq(HttpServletRequest req) {
        ProductEntity productEntity = new ProductEntity();
        String id = req.getParameter("id");
        if (id != null) productEntity.setId(Long.parseLong(id));
        productEntity.setName(req.getParameter("name"));
        String price = req.getParameter("price");
        if (!StringUtil.isBlank(price)) productEntity.setPrice(new BigDecimal(price));
        String rate = req.getParameter("rate");
        if (!StringUtil.isBlank(rate)) productEntity.setDefaultRate(new BigDecimal(rate));
        return productEntity;
    }

    public void delete(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        productService.delete(parseReq(req));
        res.sendRedirect("product.query.do");
    }

}
