package com.docfee.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:22
 */
public class RecordEntity extends BaseEntity {

    private DoctorEntity doctor;

    private ProductEntity product;

    private BigDecimal rate;

    private Date date;

    private BigDecimal price;

    private Integer num;
    /**
     * 返利费用（可能手动收入，如取整）
     */
    private BigDecimal fee;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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


}
