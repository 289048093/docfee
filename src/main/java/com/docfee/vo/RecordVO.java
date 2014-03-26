package com.docfee.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Jarvis.Lee
 * Date: 14-3-24
 * Time: 上午12:26
 */
public class RecordVO extends BaseVO {
    private Long doctorId;

    private String doctorName;

    private Long productId;

    private String productName;

    private BigDecimal rate;

    private Date date;

    private BigDecimal price;

    private Integer num;

    /**
     * 返利费用（可能手动收入，如取整）
     */
    private BigDecimal fee;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

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

    @Override
    public String toString() {
        return String.format("{id:%1$d,doctorId:%2$s,productId:%3$s,price:%4$s,rate:%5$s,num:%6$s,date:%7$tF}", getId(), doctorId, productId, price, rate, num, date);
    }

    public static void main(String[] args) {
        RecordVO vo = new RecordVO();
//        vo.setDate(new Date());
        System.out.println(vo);
    }
}
