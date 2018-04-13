package com.meizu.cloud.pushsdk.base.reflect;

import java.util.HashMap;

public class ReflectClass {
    private static HashMap<String, Class<?>> mCachedClasses = new HashMap();
    private Class<?> mClass;
    private String mClassName;
    private Object mClassObject;

    private ReflectClass(String str) {
        this.mClassName = str;
    }

    private ReflectClass(Object obj) {
        this.mClassObject = obj;
    }

    public ReflectClass(Class<?> cls) {
        this.mClass = cls;
    }

    Class<?> getRealClass() throws ClassNotFoundException {
        if (this.mClass != null) {
            return this.mClass;
        }
        if (this.mClassObject != null) {
            return this.mClassObject.getClass();
        }
        Class<?> cls = (Class) mCachedClasses.get(this.mClassName);
        if (cls != null) {
            return cls;
        }
        cls = Class.forName(this.mClassName);
        mCachedClasses.put(this.mClassName, cls);
        return cls;
    }

    public static ReflectClass forName(String str) {
        return new ReflectClass(str);
    }

    public static ReflectClass forObject(Object obj) {
        return new ReflectClass(obj);
    }

    public ReflectMethod method(String str, Class<?>... clsArr) {
        return new ReflectMethod(this, str, clsArr);
    }

    public ReflectField field(String str) {
        return new ReflectField(this, str);
    }

    public ReflectConstructor constructor(Class<?>... clsArr) {
        return new ReflectConstructor(this, clsArr);
    }
}
