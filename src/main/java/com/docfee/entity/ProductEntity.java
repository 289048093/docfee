package com.docfee.entity;

import java.math.BigDecimal;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:16
 */
public class ProductEntity extends BaseEntity {

    /**
     * name
     */
    private String name;
    /**
     *
     */
    private BigDecimal price;
    /**
     * 反点
     */
    private BigDecimal defaultRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(BigDecimal defaultRate) {
        this.defaultRate = defaultRate;
    }
}
