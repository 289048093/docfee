package com.docfee.dao;

import com.docfee.entity.ProductEntity;
import com.docfee.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午3:32
 */
public class ProductDAO {

    public void delete(ProductEntity productEntity) {

    }

    public void add(ProductEntity productEntity) {

    }

    public List<ProductEntity> query() throws SQLException {
        List<ProductEntity> list = new LinkedList<ProductEntity>();
        Statement stmt = null;
        try {
            stmt = DBUtil.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("select id,name,price,default_rate) from tb_product");
            ProductEntity p = null;
            while (rs.next()) {
                p = new ProductEntity();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setPrice(new BigDecimal(rs.getFloat("price")));
                p.setDefaultRate(new BigDecimal(rs.getFloat("default_rate")));
            }

        } finally {
            DBUtil.close(stmt);
        }
        return  list;
    }
}
