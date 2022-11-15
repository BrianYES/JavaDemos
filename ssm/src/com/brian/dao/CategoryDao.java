package com.brian.dao;

import com.brian.pojo.Category;
import com.brian.util.Page;

import java.util.List;

public interface CategoryDao {

    public void add(Category category);

    public void delete(Category category);
    public void delete(int id);

    public void update(Category category);

    public Category getOne(int id);
    public List<Category> getAll();

    public List<Category> list();
    public List<Category> list(Page page);

    public int totalCount();
}
