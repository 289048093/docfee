package com.docfee.vo;

import com.docfee.entity.DoctorEntity;
import com.docfee.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Jarvis.Lee
 * Date: 14-3-24
 * Time: 上午12:26
 */
public class DoctorBidProductVO extends BaseVO {
    private Long doctorId;

    private Long productId;

    private BigDecimal rate;

    private Date date;

    private BigDecimal price;

    private Integer num;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
