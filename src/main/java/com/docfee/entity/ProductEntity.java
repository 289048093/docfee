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

    @Override
    public String toString() {
        return String.format("{'id':'%1$d','name':'%2$s','price':'%3$s','defaultRate':'%4$s'}",getId(),name,price,defaultRate);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        ProductEntity pro = new ProductEntity();
        pro.setId(123L);
        pro.setPrice(new BigDecimal("123.12"));
        System.out.println(pro);
    }
}
