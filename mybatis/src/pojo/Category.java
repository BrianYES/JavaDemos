package pojo;

import mapper.CategoryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();


//        List<Product> ps = sqlSession.selectList("listProductCategoryBean");
//        for (Product p: ps) {
////            System.out.println("==="+ p.getName());
////            List<ProductCategory> pcs = p.getPcs();
////            for (ProductCategory pc: pcs) {
////                System.out.println(pc.getCategory().getName());
////            }
//            System.out.println(p);
//        }

//        add(sqlSession);
//        delect(sqlSession);
//        update(sqlSession);

//        list(sqlSession);

//        newList(sqlSession);

//        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
//        Category c1 = new Category();
//        c1.setName("长度够短的名称");
//        mapper.add(c1);
//
//        Category c2 = new Category();
//        c2.setName("超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称");
//        mapper.add(c2);
//
//        sqlSession.commit();
//        sqlSession.close();

        Category c1 = sqlSession.selectOne("getCategory", 1);
        System.out.println(c1);
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        Category c2 = sqlSession2.selectOne("getCategory", 1);
        System.out.println(c2);

    }

    public static void newList(SqlSession sqlSession) {
        List<Category> cs = sqlSession.selectList("listCategoryBean");
        for (Category c: cs) {
            System.out.println(c);
        }
    }

    public static void add(SqlSession sqlSession) {
        Category c = new Category();
        c.setName("new Category2");

//        sqlSession.insert("addCategory", c);

        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        mapper.add(c);
    }

    public static void delect(SqlSession sqlSession) {
        sqlSession.delete("deleteCategory", 2);
    }

    public static void update(SqlSession sqlSession) {
        sqlSession.select("getCategory", 3, new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                Category c = (Category) resultContext.getResultObject();
                System.out.println(c);
                c.setName("update category");
                sqlSession.update("updateCategory", c);
            }
        });
    }

    public static void list(SqlSession sqlSession) {
        List<Category> cs = sqlSession.selectList("listCategory");
        for (Category c: cs) {
            System.out.println(c);
        }
    }

    private int id;
    private String name;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
