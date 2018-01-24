package com.example.ch.annotationsmodel.custom;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ch on 2018/1/24.
 */

public class AnnotateUtils {
    public static void findViewById(final Activity activity) {
        Class<? extends Activity> object = activity.getClass();// 获取activity的Class
        Method[] methods = object.getDeclaredMethods();//通过Class获取activity的所有方法
        Field[] fields = object.getDeclaredFields(); // 通过Class获取activity的所有字段
        for (Field field : fields) { // 遍历所有字段
            // 获取字段的注解，如果没有ViewInject注解，则返回null
            InJect viewInject = field.getAnnotation(InJect.class);
            if (viewInject != null) {
                int viewId = viewInject.id(); // 获取字段注解的参数，这就是我们传进去控件Id
                if (viewId != -1) {
                    try {
                        // 获取类中的findViewById方法，参数为int
                        Method method = object.getMethod("findViewById", int.class);
                        // 执行该方法，返回一个Object类型的View实例
                        Object resView = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        // 把字段的值设置为该View的实例
                        field.set(activity, resView);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (final Method m : methods) {
            InJect click = m.getAnnotation(InJect.class);//通过反射api获取方法上面的注解
            if (click != null) {
                if (click.id() == -1) return;
                View view = activity.findViewById(click.id());//通过注解的值获取View控件
                if (view == null) return;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            m.invoke(activity);//通过反射来调用被注解修饰的方法
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
