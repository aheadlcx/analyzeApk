package cn.v6.sixrooms.utils;

import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SharedPreferencesUtils$a {
    private static final Method a = a();

    private static Method a() {
        try {
            return Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static void a(Editor editor) {
        try {
            if (a != null) {
                a.invoke(editor, new Object[0]);
                return;
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e2) {
        } catch (InvocationTargetException e3) {
        }
        editor.commit();
    }
}
