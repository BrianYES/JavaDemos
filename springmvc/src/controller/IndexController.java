package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

//    @Override
    @RequestMapping("hello")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws
            Exception {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "Hello Spring MVC");

        String servletPath = request.getServletPath();
        System.out.println("servletPath:"+ servletPath);
        String contextPath = request.getContextPath();
        System.out.println("contextPath:"+ contextPath);
        String servletContextGetContextPath = request.getServletContext().getContextPath();
        System.out.println("servletContextGetContextPath:"+ servletContextGetContextPath);
        String servletContextGetRealPath = request.getServletContext().getRealPath("");
        System.out.println("servletContextGetRealPath:" +servletContextGetRealPath);

        return mav;
    }

    @RequestMapping("jump")
    public ModelAndView jump() {
        return new ModelAndView("redirect:hello");
    }

    @RequestMapping("check")
    public ModelAndView check(HttpSession session, HttpServletRequest request) {
        Integer count = (Integer) session.getAttribute("count");
        if (null == count) {
            count = 0;
        }
        count++;

        session.setAttribute("count", count);
        request.setAttribute("count", 123);
        return new ModelAndView("check");
    }

    @RequestMapping("clear")
    public ModelAndView clear(HttpSession session) {
        session.removeAttribute("count");
        return new ModelAndView("check");
    }
}
