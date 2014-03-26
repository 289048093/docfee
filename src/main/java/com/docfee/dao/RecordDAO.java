package com.docfee.dao;

import com.docfee.entity.DoctorEntity;
import com.docfee.entity.ProductEntity;
import com.docfee.entity.RecordEntity;
import com.docfee.utils.DBUtil;
import com.docfee.utils.DateUtil;
import com.docfee.vo.RecordVO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午9:57
 */
public class RecordDAO {
    public void deleteByDocIdAndDate(Long docId, Date date) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("delete from tb_record where doctor_id=? and add_date=?");
            stmt.setLong(1, docId);
            stmt.setDate(2, DateUtil.toSqlDate(date));
            stmt.execute();
        } finally {
            DBUtil.close(stmt);
        }
    }

    public void save(RecordVO e) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement("insert into tb_record(doctor_id,product_id,price,rate,num,add_date,fee) values(?,?,?,?,?,?,?)");
            stmt.setLong(1, e.getDoctorId());
            stmt.setLong(2, e.getProductId());
            stmt.setBigDecimal(3, e.getPrice());
            stmt.setBigDecimal(4, e.getRate());
            stmt.setInt(5, e.getNum());
            stmt.setDate(6, DateUtil.toSqlDate(e.getDate()));
            stmt.setBigDecimal(7,e.getFee());
            stmt.execute();
        } finally {
            DBUtil.close(stmt);
        }
    }

    public List<RecordEntity> queryWithDoctorAndProduct(Long docId,Long proId,Date date) throws SQLException {
        StringBuilder sql = new StringBuilder("select re.id recId,re.price recPrice,re.rate recRate,re.num recNum,re.add_date recDate,re.fee recFee, " +
                "doc.id docId,doc.name docName,doc.hospital docHos," +
                "pro.id proId,pro.name proName,pro.price proPrice,pro.default_rate proRate " +
                "from tb_record re left join tb_doctor doc on re.doctor_id=doc.id left join tb_product pro on re.product_id=pro.id where 1=1 ");
        if(docId!=null){
            sql.append(" and doc.id=? ");
        }
        if(proId!=null){
            sql.append(" and pro.id=? ");
        }
        if(date!=null){
            sql.append(" and re.add_date=? ");
        }
        List<RecordEntity> recs = new ArrayList<RecordEntity>();
        PreparedStatement stmt = null;
        try {
            stmt = DBUtil.getCon().prepareStatement(sql.toString());
            int i = 0;
            if(docId!=null){
                stmt.setLong(++i,docId);
            }
            if(proId!=null){
                stmt.setLong(++i,proId);
            }
            if(date!=null){
                stmt.setDate(++i,DateUtil.toSqlDate(date));
            }
            ResultSet rs = stmt.executeQuery();
            RecordEntity rec = null;
            DoctorEntity doc = null;
            ProductEntity pro = null;
            while (rs.next()) {
                rec = new RecordEntity();
                doc = new DoctorEntity();
                pro = new ProductEntity();
                rec.setId(rs.getLong("recId"));
                rec.setPrice(rs.getBigDecimal("recPrice"));
                rec.setRate(rs.getBigDecimal("recRate"));
                rec.setNum(rs.getInt("recNum"));
                rec.setDate(rs.getDate("recDate"));
                rec.setFee(rs.getBigDecimal("recFee"));
                doc.setId(rs.getLong("docId"));
                doc.setName(rs.getString("docName"));
                doc.setHospital(rs.getString("docHos"));
                pro.setId(rs.getLong("proId"));
                pro.setName(rs.getString("proName"));
                pro.setPrice(rs.getBigDecimal("proPrice"));
                pro.setDefaultRate(rs.getBigDecimal("proRate"));
                rec.setDoctor(doc);
                rec.setProduct(pro);
                recs.add(rec);
            }
        } finally {
            DBUtil.close(stmt);
        }
        return recs;
    }
}
