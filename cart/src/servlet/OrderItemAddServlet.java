package servlet;

import bean.OrderItem;
import bean.Product;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));
        int num = Integer.parseInt(req.getParameter("num"));

        List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("orderItemList");
        if (null == ois) {
            ois = new ArrayList<OrderItem>();
            req.getSession().setAttribute("orderItemList", ois);
        }

        boolean hadOrderItem = false;
        for (OrderItem orderItem: ois) {
            if (orderItem.getProduct().getId() == pid) {
                orderItem.setNum(orderItem.getNum() + num);
                hadOrderItem = true;
                break;
            }
        }

        if (!hadOrderItem) {
            Product product = new ProductDAO().getProduct(pid);

            OrderItem oi = new OrderItem();
            oi.setProduct(product);
            oi.setNum(num);

            ois.add(oi);
        }

        resp.sendRedirect("listOrderItem.jsp");
    }
}
