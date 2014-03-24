package com.docfee.controller;

import com.docfee.entity.DoctorEntity;
import com.docfee.entity.ProductEntity;
import com.docfee.entity.RecordEntity;
import com.docfee.service.RecordService;
import com.docfee.service.DoctorService;
import com.docfee.service.ProductService;
import com.docfee.utils.DateUtil;
import com.docfee.utils.JsonUtil;
import com.docfee.vo.RecordVO;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 上午9:56
 */
public class CountController {

    private RecordService recordService = new RecordService();

    private DoctorService doctorService = Servlet.doctorController.getDoctorService();

    private ProductService productService = Servlet.productController.getProductService();

    public void init(HttpServletRequest req, HttpServletResponse res) {
        try {
            List<DoctorEntity> doctors = doctorService.query();
            List<ProductEntity> products = productService.query();
            req.setAttribute("doctors", doctors);
            req.setAttribute("products", products);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            req.getRequestDispatcher("count/count.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void save(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String dateStr = req.getParameter("date");
        String docIdStr = req.getParameter("docId");
        if(docIdStr==null){
            res.getWriter().write("参数错误：docId is null");
        }
        Long docId = Long.parseLong(docIdStr);
        String dataStr = req.getParameter("datalist");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Date date = DateUtil.parse(dateStr,"yyyy-MM");
        List<RecordVO> dbps = JsonUtil.parseCollectionJson(dataStr, RecordVO.class);
        recordService.save(dbps, docId, date);
        res.getWriter().write("保存成功");
    }

    public void query(HttpServletRequest req, HttpServletResponse res) {
        List<RecordEntity> vos = recordService.query();
        req.setAttribute("list",vos);

        try {
            req.getRequestDispatcher("count/list.jsp").forward(req,res);
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    static public void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        DoctorEntity doctorEntity = objectMapper.readValue("{\"id\":\"122\"}", DoctorEntity.class);
        System.out.println(doctorEntity);

        jsonGenerator.flush();
        jsonGenerator.close();
    }


}
