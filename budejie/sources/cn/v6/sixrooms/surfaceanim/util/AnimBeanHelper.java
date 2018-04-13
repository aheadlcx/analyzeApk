package cn.v6.sixrooms.surfaceanim.util;

import android.os.Bundle;
import cn.v6.sixrooms.surfaceanim.annotation.AvailableAnimBean;
import cn.v6.sixrooms.surfaceanim.annotation.AvailableAnimBeanField;
import cn.v6.sixrooms.surfaceanim.exception.InvalidFieldNameException;
import java.io.Serializable;
import java.lang.reflect.Field;

public class AnimBeanHelper {
    public static final String ANIM_BEAN_NAME = "anim_bean_name";

    public static Bundle getAnimBeanBundle(Object obj) throws IllegalAccessException {
        Bundle bundle = new Bundle();
        bundle.putString(ANIM_BEAN_NAME, obj.getClass().getName());
        Class cls = obj.getClass();
        while (true) {
            if (cls.getAnnotation(AvailableAnimBean.class) != null) {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.getAnnotation(AvailableAnimBeanField.class) != null) {
                        field.setAccessible(true);
                        if (field.getName().equals(ANIM_BEAN_NAME)) {
                            throw new InvalidFieldNameException();
                        }
                        bundle.putSerializable(field.getName(), (Serializable) field.get(obj));
                    }
                }
            }
            Class superclass = cls.getSuperclass();
            if (superclass == Object.class) {
                return bundle;
            }
            cls = superclass;
        }
    }

    public static Object getAnimBeanFromBundle(Bundle bundle) {
        Object newInstance;
        InstantiationException e;
        IllegalAccessException e2;
        ClassNotFoundException e3;
        try {
            Class cls = Class.forName(bundle.getString(ANIM_BEAN_NAME));
            newInstance = cls.newInstance();
            while (true) {
                try {
                    if (cls.getAnnotation(AvailableAnimBean.class) != null) {
                        for (Field field : cls.getDeclaredFields()) {
                            if (field.getAnnotation(AvailableAnimBeanField.class) != null) {
                                field.setAccessible(true);
                                field.set(newInstance, bundle.get(field.getName()));
                            }
                        }
                    }
                    Class superclass = cls.getSuperclass();
                    if (superclass == Object.class) {
                        break;
                    }
                    cls = superclass;
                } catch (InstantiationException e4) {
                    e = e4;
                } catch (IllegalAccessException e5) {
                    e2 = e5;
                } catch (ClassNotFoundException e6) {
                    e3 = e6;
                }
            }
        } catch (InstantiationException e7) {
            InstantiationException instantiationException = e7;
            newInstance = null;
            e = instantiationException;
            e.printStackTrace();
            return newInstance;
        } catch (IllegalAccessException e8) {
            IllegalAccessException illegalAccessException = e8;
            newInstance = null;
            e2 = illegalAccessException;
            e2.printStackTrace();
            return newInstance;
        } catch (ClassNotFoundException e9) {
            ClassNotFoundException classNotFoundException = e9;
            newInstance = null;
            e3 = classNotFoundException;
            e3.printStackTrace();
            return newInstance;
        }
        return newInstance;
    }
}
