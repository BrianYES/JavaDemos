package com.brian.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.brian.pojo.Product;

public class HibernateTest {

    @Test
    public void testAddProduct() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i = 2; i < 10; i++) {
            Product p = new Product();
            p.setName("iPhone" + i);
            p.setPrice(5200 + i);
            session.save(p);
        }

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testGetProduct() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product p = (Product) session.get(Product.class, 6);
        System.out.println("id=6的产品名称是: "+p.getName());

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testDeleteProduct() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        hibernate在删除一条数据之前，先要通过id把这条记录取出来
        Product p = (Product) session.get(Product.class, 1);
        session.delete(p);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testUpdateProduct() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product p = (Product) session.get(Product.class, 2);
        p.setName("iPhoneX");
        session.update(p);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testHQL() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Product p where p.name like ?");
        query.setString(0, "%iPhone%");
        List<Product> list = query.list();
        for (Product p : list) {
            System.out.println(p.getName());
        }

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testCriteria() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.like("name", "%iPhone%"));
        // 分页
        criteria.setFirstResult(2);
        criteria.setMaxResults(5);
        List<Product> list = criteria.list();
        for (Product p : list) {
            System.out.println(p.getName());
        }

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSQL() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "select * from product p where p.name like '%iPhone%'";
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        for (Object[] os : list) {
            for (Object filed: os) {
                System.out.print(filed+"\t");
            }
            System.out.println();
        }

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
