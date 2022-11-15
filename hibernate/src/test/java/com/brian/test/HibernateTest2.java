package com.brian.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.brian.pojo.Category;
import com.brian.pojo.Product;
import com.brian.pojo.User;

public class HibernateTest2 {

//    多对一 配置`多`的类  外键设置在`多`的类
//    一个Product对应一个Category
//    一个Category对应多个Product
    @Test
    public void testManyToOne() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Category c = new Category();
        c.setName("category100");
        session.save(c);

        Product p = (Product) session.get(Product.class, 8);
        p.setCategory(c);
        session.update(p);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

//    一对多 配置`一`的类 外键设置在`多`的类
//    一个Product对应一个Category
//    一个Category对应多个Product
    @Test
    public void testOneToMany() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Category c = (Category) session.get(Category.class, 116);
        Set<Product> products = c.getProducts();
        for (Product p : products) {
            System.out.println(p.getName());
        }

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

//    多对多 必须有一张中间表来维护User和Product之间的关系
//    一种Product可以被多个User购买
//    一个User可以购买多种Product
    @Test
    public void testManyToMany() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Set<User> users = new HashSet();
        for (int i = 0; i < 3; i++) {
            User u =new User();
            u.setName("user" + i);
            users.add(u);
            session.save(u);
        }

        Product p1 = (Product) session.get(Product.class, 2);
        p1.setUsers(users);
        session.save(p1);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
