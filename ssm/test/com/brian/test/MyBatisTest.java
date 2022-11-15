package com.brian.test;

import com.brian.dao.CategoryDao;
import com.brian.pojo.Category;
import com.brian.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyBatisTest {

    @Autowired
    private CategoryDao dao;

    @Test
    public void testAdd() {
        for (int i = 0; i < 100; i++) {
            Category category = new Category();
            category.setName("Category "+ i);
            this.dao.add(category);
        }
    }

    @Test
    public void testTotal() {
        int total = this.dao.totalCount();
        System.out.println(total);
    }

    @Test
    public void testList() {
        Page p = new Page();
        p.setStart(2);
        p.setCount(3);
        List<Category> cs = this.dao.list(p);
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}
