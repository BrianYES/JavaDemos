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
     * @param obj 源对象
     * @param clazz 目标对象的Class
     * @return 目标对象
     */
    public static <T> T beanCopy(Object obj, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T instance = constructor.newInstance();

            Map<String, String> customFieldsMap = null;
            try {
                Method customFieldsMethod = clazz.getMethod("customFields");
                customFieldsMap = (Map) customFieldsMethod.invoke(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

                String upperField = upperCaseFirstChar(fieldName);

                try {
                    String getMethodName;
                    if (customFieldsMap != null) {
                        String s = customFieldsMap.get(fieldName);
                        getMethodName = "get" + upperCaseFirstChar(s);
                    } else {
                        getMethodName = "get" + upperField;
                    }
                    Method getMethod = obj.getClass().getMethod(getMethodName);
                    Object value = getMethod.invoke(obj);

                    Method setMethod = clazz.getMethod("set" + upperField, fieldType);
                    setMethod.invoke(instance, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String upperCaseFirstChar(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
