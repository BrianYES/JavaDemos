package com.brian.demo;

import java.util.HashMap;
import java.util.Map;

public class BeanB {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public Map<String, String> customFields() {
//        Map map = new HashMap(2);
//        map.put("bid", "id");
//        map.put("bname", "name");
//        return map;
//    }
}
