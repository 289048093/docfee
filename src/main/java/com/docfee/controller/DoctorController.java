package com.docfee.controller;

import com.docfee.entity.DoctorEntity;
import com.docfee.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午12:12
 */
public class DoctorController {

    private DoctorService doctorService = new DoctorService();

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public void delete(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        doctorService.delete(parseDoc(req));
        res.sendRedirect("doctor.query.do");

    }

    public void query(HttpServletRequest req, HttpServletResponse res) {
        try {
            List<DoctorEntity> list = doctorService.query();
            req.setAttribute("doctors", list);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            req.getRequestDispatcher("doctor/query.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void add(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        doctorService.add(parseDoc(req));
        res.getWriter().print("add success");
    }

    private DoctorEntity parseDoc(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String hospital = req.getParameter("hospital");
        DoctorEntity doctorEntity = new DoctorEntity();
        if (id != null) {
            doctorEntity.setId(Long.parseLong(id));
        }
        doctorEntity.setName(name);
        doctorEntity.setHospital(hospital);
        return doctorEntity;
    }
}
