package dao;

import bean.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDAO {

    public void addOrder(Order order) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql:///cart?characterEncoding=UTF-8", "root", "abc#123");
            String sql = "INSERT INTO order_ VALUES(null,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, order.getUser().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                order.setId(id);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
