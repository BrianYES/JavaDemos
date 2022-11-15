package com.brian.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.brian.annotation.Custom;

public class Test {
    public static void main(String[] args) {

        Test test = new Test();
//        test.testMethod();
//        Annotation[] annotations = Test.class.getAnnotations();
//        for (Annotation a: annotations) {
//            System.out.println(a.toString());
//        }
        Method[] methods = test.getClass().getMethods();
        for (Method m: methods) {
            System.out.println(m.getName());
            if (m.isAnnotationPresent(Custom.class)) {
                Custom annotation = m.getAnnotation(Custom.class);
                String ip = annotation.ip();
                int port = annotation.port();
                System.out.println(ip);
                System.out.println(port);
                System.out.println(annotation.toString());
            }
        }
    }

    @Custom(ip = "127.0.0.1")
    public void testMethod() {

    }
}
