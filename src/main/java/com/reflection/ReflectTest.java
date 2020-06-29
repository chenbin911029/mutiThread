package com.reflection;

import com.alibaba.fastjson.JSON;
import com.reflection.domain.ReflectionSimple;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class ReflectTest {

    @Test
    public void simpleTest() {
        Method[] methods = ReflectionSimple.class.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method.getName());
        }
    }

    @Test
    public void classTest() {
        String className = "reflection.domain.ReflectionSimple";
        try {
            Class clazz = Class.forName(className);
            System.out.println(clazz.toString());
            Class superClazz = clazz.getSuperclass();
            System.out.println(superClazz.toString());
            Class[] interfaces = clazz.getInterfaces();
            Constructor[] constructors = clazz.getConstructors();
            Method[] methods = clazz.getMethods();
            Field[] fields = clazz.getFields();
            System.out.println(fields.length);
            Annotation[] annotations = clazz.getAnnotations();
            System.out.println(annotations.length);
            Field field = clazz.getField("day");
            System.out.println("field.getName() = " + field.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void constructorTest() {
        try {
            Constructor constructor = ReflectionSimple.class.getConstructor();
            ReflectionSimple reflection = (ReflectionSimple)constructor.newInstance();
            System.out.println(JSON.toJSONString(reflection));

            Class clazz = ReflectionSimple.class;
            Method[] methods = clazz.getMethods();
            System.out.println(JSON.toJSONString(methods));
            Method method = clazz.getMethod("getDate");
            System.out.println(method);
            Class[] parameterTypes = method.getParameterTypes();
            System.out.println(JSON.toJSONString(parameterTypes));
            Class returnType = method.getReturnType();
            System.out.println(returnType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void methodTest() {
        Class clazz = ReflectionSimple.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println("getter: " + method);
            }
            if (isSetter(method)) {
                System.out.println("setter: " + method);
            }
        }
    }

    @Test
    public void privateFieldTest() {
        Class clazz = ReflectionSimple.class;
        try {
            ReflectionSimple simple = new ReflectionSimple(new Date(),"I like.");
            Field privateField = clazz.getDeclaredField("day");
            privateField.setAccessible(true);
            Date fieldValue = (Date) privateField.get(simple);
            System.out.println("fieldValue = " + fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void privateMethodTest() {
        ReflectionSimple simple = new ReflectionSimple(new Date(),"I like.");
        Class clazz = ReflectionSimple.class;
        try {
            Method method = clazz.getDeclaredMethod("getDayFeeling");
            method.setAccessible(true);
            try {
                String value = (String)method.invoke(simple);
                System.out.println("returnValue = " +value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter方法判定
     * @param method
     * @return
     */
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    /**
     * setter判定
     * @param method
     * @return
     */
    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }
}
