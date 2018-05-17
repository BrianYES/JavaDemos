package com.brian.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanB {

    private String id;
    private int c_id;
    private String name;

    @Override
    public String toString() {
        return "BeanB{" +
                "id='" + id + '\'' +
                ", c_id=" + c_id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    public Map<String, String> customFields() {
//        Map map = new HashMap(2);
//        map.put("bid", "id");
//        map.put("bname", "name");
//        return map;
//    }
}
