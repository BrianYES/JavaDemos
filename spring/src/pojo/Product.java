package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Product {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

        Product p = (Product) context.getBean("product");

        System.out.println(p.getName());
        System.out.println(p.getCategory().getName());
    }

    private int id;
    private String name = "product 2";
    @Autowired
//    @Resource(name = "c")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

//    @Autowired
    public void setCategory(Category category) {
        this.category = category;
    }
}
