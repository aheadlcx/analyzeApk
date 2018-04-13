package com.meizu.a.a;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Method;

public class a extends b {
    private static Class<?> a = InputMethodManager.class;
    private static Method b;

    public static boolean a(Context context, boolean z) {
        b = b.a(b, a, "setMzInputThemeLight", Boolean.TYPE);
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return false;
        }
        return b.a(b, inputMethodManager, Boolean.valueOf(z));
    }
}
