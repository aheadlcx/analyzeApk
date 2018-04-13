package cn.xiaochuankeji.aop.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface b {
    String[] a() default {""};

    String b() default "";

    int c() default 0;

    String d() default "";

    int e() default 0;

    String f() default "";

    int g() default 0;

    String h() default "";

    int i() default 0;

    String j() default "";

    int k() default 0;

    boolean l() default false;

    boolean m() default false;
}
