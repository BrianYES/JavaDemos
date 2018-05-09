package servlet;

import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.OrderDAO;
import dao.OrderItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (null == user) {
            resp.sendRedirect("login.jsp");
        } else {
            Order order = new Order();
            order.setUser(user);

            new OrderDAO().addOrder(order);

            List<OrderItem> orderItems = (List<OrderItem>) req.getSession().getAttribute("orderItemList");

            for (OrderItem oi: orderItems) {
                oi.setOrder(order);
                new OrderItemDAO().addOrderItem(oi);
            }

            orderItems.clear();
//            req.getSession().removeAttribute("orderItemList");

            resp.sendRedirect("index.jsp");
        }
    }
}
