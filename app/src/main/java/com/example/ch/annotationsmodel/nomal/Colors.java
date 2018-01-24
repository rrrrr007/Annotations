package com.example.ch.annotationsmodel.nomal;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Created by ch on 2018/1/24.
 */

public class Colors {
    private int current;
    private int source;
    private String name ;
    public static final int NO = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;

    /**
     *  代替枚举：
     * 声明必要的int常量
     * 声明一个注解为 MyColors
     * 使用@IntDef修饰LightColors,参数设置为待枚举的集合
     * 使用@Retention(RetentionPolicy.SOURCE)指定注解仅存在与源码中,不加入到class文件中
     * SOURCE:在源文件中有效（即源文件保留）
     　CLASS:在class文件中有效（即class保留）
     　RUNTIME:在运行时有效（即运行时保留）
     * Target说明了Annotation所修饰的对象范围：
     * CONSTRUCTOR:用于描述构造器
     　FIELD:用于描述域
     　LOCAL_VARIABLE:用于描述局部变量
     　METHOD:用于描述方法
     　PACKAGE:用于描述包
     　PARAMETER:用于描述参数
     　TYPE:用于描述类、接口(包括注解类型) 或enum声明
     */
    @Target({PARAMETER,FIELD})
    @IntDef({NO,RED,GREEN,BLUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MyColors{
    }

    public void setCurrent(@MyColors int current) {
        this.current = current;
    }

    //@MyColors
    public int getCurrent() {
        return current;
    }

    /**
     * AnimatorRes ：animator资源类型
     AnimRes：anim资源类型
     AnyRes：任意资源类型
     ArrayRes：array资源类型
     AttrRes：attr资源类型
     BoolRes：boolean资源类型
     ColorRes：color资源类型
     DimenRes：dimen资源类型。
     DrawableRes：drawable资源类型。
     FractionRes：fraction资源类型
     IdRes：id资源类型
     IntegerRes：integer资源类型
     InterpolatorRes：interpolator资源类型
     LayoutRes：layout资源类型
     MenuRes：menu资源类型
     PluralsRes：plurals资源类型
     RawRes：raw资源类型
     StringRes：string资源类型
     StyleableRes：styleable资源类型
     StyleRes：style资源类型
     TransitionRes：transition资源类型
     XmlRes：xml资源类型
     * @param source
     */

    public void setSource(@LayoutRes int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }

    /**
     * Nullable注解可以用来标识特定的参数或者返回值可以为null。
     * NonNull注解可以用来标识参数不能为null。
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(@NonNull @Size(max=6) String name) {
        this.name = name;
    }

    @WorkerThread
    public void testThread(Context context){
        Toast.makeText(context, this.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * @Size, @IntRange, @FloatRange
     * 集合不能为空: @Size(min=1)
        字符串最大只能有23个字符: @Size(max=23)
        数组只能有2个元素: @Size(2)
        数组的大小必须是2的倍数 (例如图形API中获取位置的x/y坐标数组: @Size(multiple=2)
     */
    @CallSuper
    public void testCallSuper(){
        this.setName("必须调用父类");
    }

}
