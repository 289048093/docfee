package com.docfee.entity;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:16
 */
public abstract class BaseEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
