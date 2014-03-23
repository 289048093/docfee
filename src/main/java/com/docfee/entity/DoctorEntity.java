package com.docfee.entity;

import java.util.List;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午2:20
 */
public class DoctorEntity extends BaseEntity {

    private String name;

    private String hospital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
