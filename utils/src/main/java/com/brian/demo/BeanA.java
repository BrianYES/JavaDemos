package com.brian.demo;

import java.util.Date;
import java.util.List;

public class BeanA {

    private int id;
    private Long c_id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "id=" + id +
                ", c_id=" + c_id +
                ", name='" + name + '\'' +
                '}';
    }
}
