package com.brian;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BeanUtilsDemo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Bean bean = new Bean();

        BeanUtils.setProperty(bean, "name", "Brian");
        BeanUtils.setProperty(bean, "name", 456);

        Date date = new Date();
        BeanUtils.setProperty(bean, "birthday", date);

        BeanUtils.setProperty(bean, "weight", "123.321");

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        BeanUtils.setProperty(bean, "lists", list);

        BeanUtils.setProperty(bean, "id", "123");

//        argument type mismatch 类型不匹配 异常
//        PropertyUtils.setProperty(bean, "id", "990");

        String value = BeanUtils.getProperty(bean, "id");
        System.out.println(value);
        System.out.println(value.getClass().getName());
        Object value2 = PropertyUtils.getProperty(bean, "id");
        System.out.println(value2);
        System.out.println(value2.getClass().getName());

        System.out.println(bean);


        Bean bean2 = new Bean();

        Map map = new HashMap();
        map.put("id", "2");
        map.put("name", "tom");
        map.put("weight", "60.5");
        map.put("birthday", "1992-08-24");

        ConvertUtils.register(new Converter() {
            @Override
            public <Date> Date convert(Class<Date> aClass, Object o) {
                String value = (String) o;
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return  (Date) fmt.parse(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);

        try {
            BeanUtils.populate(bean2, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(bean2);

    }

}


