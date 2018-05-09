package com.brian;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class HelloFilter2 implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Hello Filter2");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String encoding = config.getInitParameter("encoding");
        System.out.println("encoding: "+ encoding);
    }

}
