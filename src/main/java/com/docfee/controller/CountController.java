package com.docfee.controller;

import com.docfee.entity.DoctorBidProductEntity;
import com.docfee.entity.DoctorEntity;
import com.docfee.entity.ProductEntity;
import com.docfee.service.DoctorBidProductService;
import com.docfee.service.DoctorService;
import com.docfee.service.ProductService;
import com.docfee.utils.JsonUtil;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午9:56
 */
public class CountController {

    private DoctorBidProductService doctorBidProductService = new DoctorBidProductService();

    private DoctorService doctorService = Servlet.doctorController.getDoctorService();

    private ProductService productService = Servlet.productController.getProductService();

    public void query(HttpServletRequest req, HttpServletResponse res) {
        try {
            List<DoctorEntity> doctors = doctorService.query();
            List<ProductEntity> products = productService.query();
            req.setAttribute("doctors",doctors);
            req.setAttribute("products",products);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            req.getRequestDispatcher("count/count.jsp").forward(req,res);
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void save(HttpServletRequest req,HttpServletResponse res) throws IOException {
          String date = req.getParameter("date");
        String data = req.getParameter("list");
          List<DoctorBidProductEntity> dbps = new ArrayList<DoctorBidProductEntity>();
        JsonUtil.parseCollectionJson(data,ArrayList.class,DoctorBidProductEntity.class);
        doctorBidProductService.save(dbps);
          res.getWriter().write("保存成功");
    }

    static public  void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        DoctorEntity doctorEntity = objectMapper.readValue("{\"id\":\"122\"}",DoctorEntity.class);
        System.out.println(doctorEntity);

        jsonGenerator.flush();
        jsonGenerator.close();
    }



}
