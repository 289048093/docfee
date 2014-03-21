package com.docfee.service;

import com.docfee.dao.ProductDAO;
import com.docfee.entity.ProductEntity;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午3:31
 */
public class ProductService {
    private ProductDAO productDAO = new ProductDAO();


    public void add(ProductEntity productEntity) {
        productDAO.add(productEntity);
    }

    public void delete(ProductEntity productEntity) {
        productDAO.delete(productEntity) ;
    }

    public void query() {
        productDAO.query();
    }
}
