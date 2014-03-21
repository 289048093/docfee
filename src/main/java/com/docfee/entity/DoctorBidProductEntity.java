package com.docfee.entity;

import java.math.BigDecimal;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:22
 */
public class DoctorBidProductEntity extends BaseEntity {

    private DoctorEntity doctor;

    private ProductEntity product;

    private BigDecimal rate;

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
}
