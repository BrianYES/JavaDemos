package com.brian;

import java.io.IOException;
import java.lang.reflect.Constructor;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

public class JavassistTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        newClass();
    }

    public static void newClass() throws NotFoundException, CannotCompileException, IOException {
        ClassPool classPool = ClassPool.getDefault();

        // 添加新类
        CtClass ctClass = classPool.makeClass("com.brian.Point");

        // 添加属性
        CtField xField = new CtField(classPool.get("float"), "x", ctClass);
        xField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(xField);
        // 添加Setter和Getter方法
        ctClass.addMethod(CtNewMethod.setter("setX", xField));
        ctClass.addMethod(CtNewMethod.getter("getX", xField));

        CtField yField = new CtField(classPool.get("float"), "y", ctClass);
        yField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(yField);
        ctClass.addMethod(CtNewMethod.setter("setY", yField));
        ctClass.addMethod(CtNewMethod.getter("getY", yField));

        // 添加新的构造方法
        CtConstructor constructor = new CtConstructor(new CtClass[]{classPool.get("float"), classPool.get("float")}, ctClass);
        constructor.setModifiers(Modifier.PUBLIC);
        constructor.setBody("{this.x = $1; this.y = $2;}");
        ctClass.addConstructor(constructor);

        // 添加新的方法
        // CtMethod(CtClass returnType, String mname, CtClass[] parameters, CtClass declaring)
        CtMethod method = new CtMethod(classPool.get(String.class.getName()), "toString", null, ctClass);
        method.setModifiers(Modifier.PUBLIC);
        method.setBody("{return \"Point{x:\" + this.x + \", y:\" + this.y + \"}\";}");
        ctClass.addMethod(method);

        // 保存.class文件
        ctClass.writeFile("target/classes");

        // 转化成Class对象
//        Class clazz = ctClass.toClass();
//        try {
////            Object obj = clazz.newInstance();
////            clazz.getMethod("setX", float.class).invoke(obj, 10.0f);
////            clazz.getMethod("setY", float.class).invoke(obj, 20.0f);
//
//            Constructor constructor1 = clazz.getDeclaredConstructor(float.class, float.class);
//            Object obj = constructor1.newInstance(10.0f, 20.0f);
//
//            Object toString = clazz.getMethod("toString").invoke(obj);
//            System.out.println(toString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
