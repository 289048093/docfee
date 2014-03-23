package com.docfee.service;

import com.docfee.dao.DoctorDAO;
import com.docfee.entity.DoctorEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午12:18
 */
public class DoctorService {

    private DoctorDAO doctorDAO = new DoctorDAO();
    public void delete(DoctorEntity doctorEntity) throws SQLException {
        doctorDAO.delete(doctorEntity);

    }

    public List<DoctorEntity> query() throws SQLException {
        return doctorDAO.query();
    }

    public void add(DoctorEntity doctorEntity) throws SQLException {
        doctorDAO.add(doctorEntity);
    }
}
