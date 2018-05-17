package com.brian.test;

import org.junit.Test;

import com.brian.demo.BeanA;
import com.brian.demo.BeanB;
import com.brian.utils.BeanUtils;

public class TestBeanUtils {

    @Test
    public void test() {
        BeanA a = new BeanA();
        a.setId(1);
        a.setC_id(new Long(2));
        a.setName("Brian");

        BeanB beanB = BeanUtils.beanCopy(a, BeanB.class);
        System.out.println(beanB);

        Long v = new Long(12);
        String s = v.toString();
        System.out.println(s);
        System.out.println(Integer.parseInt(s));
    }
}
