import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUtilsDemo {

    private static String url = "jdbc:mysql:///demo";
    private static String user = "root";
    private static String password = "abc#123";

    public static void main(String[] args) throws SQLException {
//        queryList();

        DbUtils.loadDriver("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        QueryRunner qr = new QueryRunner();
        String sql = "insert into user values (?,?)";

        qr.insert(con, sql, new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                }
                return null;
            }
        }, 8, "tom5");

        DbUtils.close(con);
    }

    public static void add() {
        Connection connection = getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "insert into user values (?,?)";
        try {
            qr.update(connection, sql, 1, "brian");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(connection);
    }

    public static void delete() {
        Connection connection = getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "delete from user where id=?";
        try {
            qr.update(connection, sql, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(connection);
    }

    public static void update() {
        Connection connection = getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "update user set name=? where id=?";
        try {
            qr.update(connection, sql, "brian tsui", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(connection);
    }

    public static void query() {
        Connection connection = getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "select * from user where id=?";
        try {
            User user1 = qr.query(connection, sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt(1));
                        user.setName(resultSet.getString(2));
                        return user;
                    }
                    return null;
                }
            }, 1);

            System.out.println(user1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(connection);
    }

    public static void query2() {
        Connection connection = getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "select * from user where id=?";
        try {
            User user = qr.query(connection, sql, new BeanHandler<User>(User.class), 1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryList() {
        Connection connection = getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "select * from user";
        try {
            List<User> users = qr.query(connection, sql, new BeanListHandler<User>(User.class));
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
