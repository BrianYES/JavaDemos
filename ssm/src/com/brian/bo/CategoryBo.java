package com.brian.bo;

import com.brian.dao.CategoryDao;
import com.brian.pojo.Category;
import com.brian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBo {

    @Autowired
    CategoryDao dao;

    public List<Category> getAll() {
        return this.dao.getAll();
    }

    public List<Category> list(Page page) {
        return this.dao.list(page);
    }

    public List<Category> list() {
        return this.dao.list();
    }

    public int totalCount() {
        return this.dao.totalCount();
    }

    public void add(Category category) {
        this.dao.add(category);
    }

    public void addTwo() {
        Category c = new Category();
        c.setName("短的");
        this.dao.add(c);

        Category c2 = new Category();
        c2.setName("第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类第201个分类");
        this.dao.add(c2);
    }
}
