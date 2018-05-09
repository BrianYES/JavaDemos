package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductService {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

        ProductService ps = (ProductService) context.getBean("ps");
        ps.doSomeService();

        ProductService ps2 = (ProductService) context.getBean("ps");
        ps2.doSomeService();
    }

    public void doSomeService() {
        System.out.println("doSomeService..."+ this);
    }
}
