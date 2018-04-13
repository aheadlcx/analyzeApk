package com.meizu.cloud.pushsdk.base.reflect;

import com.meizu.cloud.pushsdk.base.Logger;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class ReflectMethod {
    private static HashMap<String, Method> mCachedMethods = new HashMap();
    private String TAG = "ReflectMethod";
    private String mMethodName;
    private ReflectClass mReflectClass;
    private Class<?>[] mTypes;

    private class NULL {
        private NULL() {
        }
    }

    ReflectMethod(ReflectClass reflectClass, String str, Class<?>... clsArr) {
        this.mReflectClass = reflectClass;
        this.mMethodName = str;
        this.mTypes = clsArr;
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        int i = 0;
        while (i < clsArr2.length) {
            if (clsArr2[i] != NULL.class && !wrapper(clsArr[i]).isAssignableFrom(wrapper(clsArr2[i]))) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean isSimilarSignature(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    private Method similarMethod() throws NoSuchMethodException, ClassNotFoundException {
        int i = 0;
        Class realClass = this.mReflectClass.getRealClass();
        for (Method method : realClass.getMethods()) {
            Method method2;
            if (isSimilarSignature(method2, this.mMethodName, this.mTypes)) {
                break;
            }
        }
        Method[] declaredMethods = realClass.getDeclaredMethods();
        int length = declaredMethods.length;
        while (i < length) {
            method2 = declaredMethods[i];
            if (isSimilarSignature(method2, this.mMethodName, this.mTypes)) {
                return method2;
            }
            i++;
        }
        throw new NoSuchMethodException("No similar method " + this.mMethodName + " with params " + Arrays.toString(this.mTypes) + " could be found on type " + realClass);
    }

    private String getKey() throws ClassNotFoundException {
        StringBuffer stringBuffer = new StringBuffer(this.mReflectClass.getRealClass().getName());
        stringBuffer.append(this.mMethodName);
        for (Class name : this.mTypes) {
            stringBuffer.append(name.getName());
        }
        return stringBuffer.toString();
    }

    public <T> ReflectResult<T> invoke(Object obj, Object... objArr) {
        ReflectResult<T> reflectResult = new ReflectResult();
        try {
            String key = getKey();
            Method method = (Method) mCachedMethods.get(key);
            if (method == null) {
                if (this.mTypes.length == objArr.length) {
                    method = this.mReflectClass.getRealClass().getMethod(this.mMethodName, this.mTypes);
                } else {
                    if (objArr.length > 0) {
                        this.mTypes = new Class[objArr.length];
                        for (int i = 0; i < objArr.length; i++) {
                            this.mTypes[i] = objArr[i].getClass();
                        }
                    }
                    method = similarMethod();
                }
                mCachedMethods.put(key, method);
            }
            method.setAccessible(true);
            reflectResult.value = method.invoke(obj, objArr);
            reflectResult.ok = true;
        } catch (Throwable e) {
            Logger.get().e(this.TAG, "invoke", e);
        }
        return reflectResult;
    }

    public <T> ReflectResult<T> invokeStatic(Object... objArr) {
        try {
            return invoke(this.mReflectClass.getRealClass(), objArr);
        } catch (ClassNotFoundException e) {
            return new ReflectResult();
        }
    }

    private Class<?> wrapper(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (!cls.isPrimitive()) {
            return cls;
        }
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        if (Short.TYPE == cls) {
            return Short.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Void.TYPE == cls) {
            return Void.class;
        }
        return cls;
    }
}
