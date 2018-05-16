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
        a.setName("Brian");
        a.setAddress("hangzhou");

        BeanB beanB = BeanUtils.beanCopy(a, BeanB.class);
        System.out.println(beanB);
    }
}
