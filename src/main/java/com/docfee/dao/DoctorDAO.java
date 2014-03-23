package com.docfee.dao;

import com.docfee.entity.DoctorEntity;
import com.docfee.entity.ProductEntity;
import com.docfee.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午12:24
 */
public class DoctorDAO {
    public void delete(DoctorEntity doctorEntity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("delete from tb_doctor where id=?");
            stmt.setLong(1,doctorEntity.getId());
            stmt.execute();
        } finally {
            DBUtil.close(stmt);
        }
    }


    public void add(DoctorEntity doctorEntity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("insert into tb_doctor(name,hospital) values(?,?)");
            stmt.setString(1, doctorEntity.getName());
            stmt.setString(2, doctorEntity.getHospital());
            stmt.execute();
        } finally {
            DBUtil.close(stmt);
        }
    }

    public List<DoctorEntity> query() throws SQLException {
        List<DoctorEntity> list = new LinkedList<DoctorEntity>();
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("select id,name,hospital from tb_doctor");
            ResultSet rs = stmt.executeQuery();
            DoctorEntity p = null;
            while (rs.next()) {
                p = new DoctorEntity();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setHospital(rs.getString("hospital"));
                list.add(p);
            }

        } finally {
            DBUtil.close(stmt);
        }
        return list;
    }
}
