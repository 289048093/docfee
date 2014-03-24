package com.docfee.dao;

import com.docfee.entity.ProductEntity;
import com.docfee.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午3:32
 */
public class ProductDAO {

    public void delete(ProductEntity productEntity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("delete from tb_product where id=?");
            stmt.setLong(1,productEntity.getId());
            stmt.execute();
        } finally {
            DBUtil.close(stmt);
        }
    }

    public void add(ProductEntity productEntity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("insert into tb_product(name,price,default_rate) values(?,?,?)");
            stmt.setString(1, productEntity.getName());
            stmt.setBigDecimal(2, productEntity.getPrice());
            stmt.setBigDecimal(3, productEntity.getDefaultRate());
            stmt.execute();
        } finally {
            DBUtil.close(stmt);
        }
    }

    public List<ProductEntity> query() throws SQLException {
        List<ProductEntity> list = new LinkedList<ProductEntity>();
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("select id,name,price,default_rate from tb_product");
            ResultSet rs = stmt.executeQuery();
            ProductEntity p = null;
            while (rs.next()) {
                p = new ProductEntity();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                 p.setPrice(rs.getBigDecimal("price"));
                 p.setDefaultRate(rs.getBigDecimal("default_rate"));
                list.add(p);
            }

        } finally {
            DBUtil.close(stmt);
        }
        return list;
    }
}
