package com.brian.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brian.dao.CategoryDAO;
import com.brian.pojo.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class SpringHibernateTest {

    @Autowired
    private CategoryDAO dao;

    @Test
    public void test() {
        List<Category> list = dao.find("from Category c");
        for (Category c : list) {
            System.out.println(c.getName());
        }
    }

}
