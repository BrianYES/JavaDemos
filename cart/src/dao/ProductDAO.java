package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

/**
 * ProductDAO
 */
public class ProductDAO {

    public List<Product> listProduct() {
        List<Product> pList = new ArrayList<Product>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql:///cart?characterEncoding=UTF-8", "root", "abc#123");
            String sql = "select * from product order by id desc";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getFloat(3));
                pList.add(p);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pList;
    }

    public Product getProduct(int id) {
        Product product = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql:///cart?characterEncoding=UTF-8", "root", "abc#123");
            String sql = "select * from product WHERE id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
}