package bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class User {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
//        User user = new User();
//
//        PropertyDescriptor pd = new PropertyDescriptor("name", User.class);
//        Method mGetName = pd.getWriteMethod();
//        mGetName.invoke(user,"brian");
//        System.out.println(user.getName());

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop: pds) {
            System.out.println(prop.getName());
            System.out.println(prop.getDisplayName());
        }
    }
}
