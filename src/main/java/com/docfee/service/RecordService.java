package com.docfee.service;

import com.docfee.dao.RecordDAO;
import com.docfee.entity.RecordEntity;
import com.docfee.vo.RecordVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午9:57
 */
public class RecordService {

    private RecordDAO recordDAO = new RecordDAO();

    public void save(List<RecordVO> dbps, Long docId, Date date) {
        try {
            recordDAO.deleteByDocIdAndDate(docId, date);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for (RecordVO e : dbps) {
            e.setDoctorId(docId);
            e.setDate(date);
            try {
                recordDAO.save(e);
            } catch (SQLException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }

    public List<RecordEntity> query() {
        try {
            return recordDAO.queryWithDoctorAndProduct(null,null,null);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return new ArrayList<RecordEntity>();
    }

    public List<RecordEntity> query(Long docId, Date date) {
        try {
            return recordDAO.queryWithDoctorAndProduct(docId,null,date);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return new ArrayList<RecordEntity>();
    }
}
