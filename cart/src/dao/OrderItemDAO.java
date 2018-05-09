package dao;

import bean.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OrderItemDAO {

    public void addOrderItem(OrderItem oi) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql:///cart?characterEncoding=UTF-8", "root", "abc#123");
            String sql = "INSERT INTO orderitem VALUES (null,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, oi.getProduct().getId());
            ps.setInt(2, oi.getNum());
            ps.setInt(3, oi.getOrder().getId());
            ps.execute();

            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
