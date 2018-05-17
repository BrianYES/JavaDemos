package com.brian.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanUtils {

    /**
     * 对象的属性值复制
     * 如属性名称不一致 在目标对象类里添加 `public Map<String, String> customFields()`方法
     * Map key: 目标对象的属性名
     * Map value: 源对象的属性名
     *
     * @param srcObj 源对象
     * @param destClazz 目标对象的Class
     * @return 目标对象
     */
    public static <T> T beanCopy(Object srcObj, Class<T> destClazz) {
        try {
            Constructor<T> constructor = destClazz.getConstructor();
            T destObj = constructor.newInstance();

            Class<?> srcClazz = srcObj.getClass();

            Map<String, String> customFieldsMap = null;
            try {
                Method customFieldsMethod = destClazz.getMethod("customFields");
                customFieldsMap = (Map) customFieldsMethod.invoke(destObj);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            for (Field destField : destClazz.getDeclaredFields()) {
                try {
                    String destFieldName = destField.getName();
                    Class<?> destFieldType = destField.getType();

                    String srcFieldName = destFieldName;
                    if (customFieldsMap != null) {
                        srcFieldName = customFieldsMap.get(destFieldName);
                    }
                    Field srcField = srcClazz.getDeclaredField(srcFieldName);
                    Class<?> srcFieldType = srcField.getType();

                    srcField.setAccessible(true);
                    Object srcValue = srcField.get(srcObj);
                    String srcString = srcValue.toString();

                    if (!destFieldType.equals(srcFieldType)) {
                        if ("String".equals(destFieldType.getSimpleName())) {
                            String destValue = srcValue.toString();

                            destField.setAccessible(true);
                            destField.set(destObj, destValue);
                        } else if (destFieldType.getName().indexOf("java.lang.") == 0) {
                            Method method = destClazz.getMethod("parse" + fixparse(destFieldType.getSimpleName()),
                                    String.class);
                            if (method != null) {
                                Object destValue = method.invoke(null, srcString);
                                if (destValue != null) {
                                    destField.setAccessible(true);
                                    destField.set(destObj, destValue);
                                }
                            }
                        } else if ("int".equals(destFieldType.getName())) {
                            int i = Integer.parseInt(srcString);
                            destField.setAccessible(true);
                            destField.set(destObj, i);
                        }
                        else {
                            System.out.println("=====其他类型=====");
                            System.out.println("getName:"+ destFieldType.getName());
                            System.out.println("getSimpleName:"+ destFieldType.getSimpleName());
                        }
                    } else {
                        destField.setAccessible(true);
                        destField.set(destObj, srcValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                System.out.println("src value:"+ srcValue);
//                System.out.println("src value type:"+ srcValue.getClass());
//                System.out.println("src value type2:"+ srcField.getType());
//                System.out.println("src value type2 getName:"+ srcField.getType().getName());
//                System.out.println("src value type2 getSimpleName:"+ srcField.getType().getSimpleName());
//                System.out.println("\n");

//                Integer -> String


//                String -> Integer



//                if (!field.equals(field1)) {
//                    Method valueOf = fieldType.getDeclaredMethod("valueOf", field1.getType());
//
//                    value1 = valueOf.invoke(null, value1);
//                    System.out.println("value2:"+ value1);
//                    System.out.println("value2 type:"+ value1.getClass());
//                }

//                destField.setAccessible(true);
//                destField.set(destObj, srcValue);

//                String upperField = upperCaseFirstChar(fieldName);
//
//                try {
//                    String getMethodName;
//                    if (customFieldsMap != null) {
//                        String s = customFieldsMap.get(fieldName);
//                        getMethodName = "get" + upperCaseFirstChar(s);
//                    } else {
//                        getMethodName = "get" + upperField;
//                    }
//                    Method getMethod = obj.getClass().getMethod(getMethodName);
//                    Object value = getMethod.invoke(obj);
//
//                    Method setMethod = clazz.getMethod("set" + upperField, fieldType);
//                    setMethod.invoke(instance, value);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            return destObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object typeConvert(String value, Class<?> type) {
        String simpleName = type.getSimpleName();

        if ("String".equals(simpleName)) {
            return value;
        } else if ("int".equals(simpleName) || "Integer".equals(simpleName)) {
            return Integer.parseInt(value);
        } else if ("float".equals(simpleName) || "Float".equals(simpleName)) {
            return Float.parseFloat(value);
        } else if ("double".equals(simpleName) || "Double".equals(simpleName)) {
            return Double.parseDouble(value);
        } else if ("long".equals(simpleName) || "Long".equals(simpleName)) {
            return Long.parseLong(value);
        } else {
            System.out.println("=====其他类型=====");
            System.out.println("type getName:"+ type.getName());
            System.out.println("type getSimpleName:"+ type.getSimpleName());
            return null;
        }
    }

    private static String fixparse(String fstype) {
        if ("Integer".equals(fstype)) {
            return "Int";
        } else {
            return fstype;
        }
    }

    private static String upperCaseFirstChar(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
