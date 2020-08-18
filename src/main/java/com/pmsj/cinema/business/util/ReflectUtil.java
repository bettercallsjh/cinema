package com.pmsj.cinema.business.util;

import com.pmsj.cinema.business.exception.NullParametersException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mhd
 * @className ReflectUtil
 * @description TODO
 * @create 2020/7/9
 * @since 1.0.0
 */
public class ReflectUtil<T> {
    /**
     * 首字母大写
     *
     * @param fildeName
     * @return
     */
    public static String getMethodName(String fildeName) {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 判断对象中属性是否为空
     * strings为null时默认id为空不抛出异常
     */
    public void throwNullParametersException(T obj,String[] strings) {
        Class<?> class1 = obj.getClass();
        String name =  class1.getName().substring(class1.getName().lastIndexOf(".")+1);
        Field[] fields = class1.getDeclaredFields();
        for (Field field : fields) {
            Method m = null;
            try {
                m = (Method) class1.getMethod("get" + getMethodName(field.getName()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object val = null;
            try {
                val = m.invoke(obj);// 调用getter方法获取属性值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            if (strings==null){
                if (val == null&& !getMethodName(field.getName()).equals(name+"Id")) {
                    throw new NullParametersException(getMethodName(field.getName()) + " is null");
                }
            }else{
                if (val == null&&isAllowed(field,strings) ) {
                    throw new NullParametersException(getMethodName(field.getName()) + " is null");
                }
            }




        }

    }



    /**
     * 抛出条件是否允许
     * @param field
     * @param strings
     * @return
     */
    private Boolean isAllowed(Field field,String[] strings){
        Boolean b = true;
        for (String string : strings) {
            if (field.getName().equals(string)){
                return false;
            }
        }
        return b;
    }
}
