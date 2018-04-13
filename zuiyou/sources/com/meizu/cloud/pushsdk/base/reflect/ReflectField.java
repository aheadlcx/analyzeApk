package com.meizu.cloud.pushsdk.base.reflect;

import com.meizu.cloud.pushsdk.base.Logger;
import java.lang.reflect.Field;

public class ReflectField {
    private String TAG = "ReflectField";
    private String mFieldName;
    private ReflectClass mReflectClass;

    ReflectField(ReflectClass reflectClass, String str) {
        this.mReflectClass = reflectClass;
        this.mFieldName = str;
    }

    private Field getField() throws ClassNotFoundException, NoSuchFieldException {
        Field declaredField = this.mReflectClass.getRealClass().getDeclaredField(this.mFieldName);
        declaredField.setAccessible(true);
        return declaredField;
    }

    public <T> ReflectResult<T> set(Object obj, T t) {
        ReflectResult<T> reflectResult = new ReflectResult();
        try {
            getField().set(obj, t);
            reflectResult.value = t;
            reflectResult.ok = true;
        } catch (Throwable e) {
            Logger.get().e(this.TAG, "set", e);
        }
        return reflectResult;
    }

    public <T> ReflectResult<T> setStatic(T t) {
        try {
            return set(this.mReflectClass.getRealClass(), t);
        } catch (Throwable e) {
            Logger.get().e(this.TAG, "setStatic", e);
            return new ReflectResult();
        }
    }

    public <T> ReflectResult<T> get(Object obj) {
        ReflectResult<T> reflectResult = new ReflectResult();
        try {
            reflectResult.value = getField().get(obj);
            reflectResult.ok = true;
        } catch (Throwable e) {
            Logger.get().e(this.TAG, "get", e);
        }
        return reflectResult;
    }

    public <T> ReflectResult<T> getStatic() {
        try {
            return get(this.mReflectClass.getRealClass());
        } catch (Throwable e) {
            Logger.get().e(this.TAG, "getStatic", e);
            return new ReflectResult();
        }
    }
}
