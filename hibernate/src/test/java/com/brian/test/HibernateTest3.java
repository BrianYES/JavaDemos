package com.brian.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.brian.pojo.Product;

public class HibernateTest3 {

    @Test
    public void testLoad() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product p1 = (Product) session.get(Product.class, 2);
        System.out.println("log1");
        System.out.println(p1.getName());
        System.out.println(p1.getUsers());
        System.out.println("log2");

//        Product p2 = session.load(Product.class, 2);
//        System.out.println("log3");
//        System.out.println(p2.getName());
//        System.out.println("log4");

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
