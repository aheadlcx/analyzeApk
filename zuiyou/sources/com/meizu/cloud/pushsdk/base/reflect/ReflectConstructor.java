package com.meizu.cloud.pushsdk.base.reflect;

import com.meizu.cloud.pushsdk.base.Logger;
import java.lang.reflect.Constructor;

public class ReflectConstructor {
    private String TAG = "ReflectConstructor";
    private ReflectClass mReflectClass;
    private Class<?>[] mTypes;

    ReflectConstructor(ReflectClass reflectClass, Class<?>... clsArr) {
        this.mReflectClass = reflectClass;
        this.mTypes = clsArr;
    }

    public <T> ReflectResult<T> newInstance(Object... objArr) {
        ReflectResult<T> reflectResult = new ReflectResult();
        try {
            Constructor declaredConstructor = this.mReflectClass.getRealClass().getDeclaredConstructor(this.mTypes);
            declaredConstructor.setAccessible(true);
            reflectResult.value = declaredConstructor.newInstance(objArr);
            reflectResult.ok = true;
        } catch (Throwable e) {
            Logger.get().e(this.TAG, "newInstance", e);
        }
        return reflectResult;
    }
}
