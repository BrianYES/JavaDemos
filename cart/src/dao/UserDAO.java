package dao;

import bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User getUser(String name, String password) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql:///cart?characterEncoding=UTF-8", "root", "abc#123");
            String sql = "SELECT * FROM user WHERE name=? AND password=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
