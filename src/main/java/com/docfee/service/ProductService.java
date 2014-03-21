package com.docfee.service;

import com.docfee.dao.ProductDAO;
import com.docfee.entity.ProductEntity;

import java.sql.SQLException;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午3:31
 */
public class ProductService {
    private ProductDAO productDAO = new ProductDAO();


    public void add(ProductEntity productEntity) throws SQLException {
        productDAO.add(productEntity);
    }

    public void delete(ProductEntity productEntity) throws SQLException {
        productDAO.delete(productEntity) ;
    }

    public void query() throws SQLException {
        productDAO.query();
    }
}
