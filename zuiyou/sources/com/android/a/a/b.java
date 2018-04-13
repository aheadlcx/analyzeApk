package com.android.a.a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class b {
    private static final a a;

    interface a {
        void a(Window window, boolean z);
    }

    private static class c implements a {
        private c() {
        }

        @TargetApi(11)
        public void a(Window window, boolean z) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            if (z) {
                systemUiVisibility |= 8192;
            } else {
                systemUiVisibility &= -8193;
            }
            decorView.setSystemUiVisibility(systemUiVisibility);
        }
    }

    private static class b implements a {
        private b() {
        }

        public void a(Window window, boolean z) {
            int i = 0;
            Class cls = window.getClass();
            try {
                Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
                Object[] objArr = new Object[2];
                if (z) {
                    i = i2;
                }
                objArr[0] = Integer.valueOf(i);
                objArr[1] = Integer.valueOf(i2);
                method.invoke(window, objArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class d implements a {
        private d() {
        }

        public void a(Window window, boolean z) {
            LayoutParams attributes = window.getAttributes();
            try {
                com.meizu.a.a.a.a(window.getContext(), z);
            } catch (Exception e) {
            }
            try {
                Field declaredField = LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                if (z) {
                    i |= i2;
                } else {
                    i = (i ^ -1) & i2;
                }
                declaredField2.setInt(attributes, i);
                window.setAttributes(attributes);
                declaredField.setAccessible(false);
                declaredField2.setAccessible(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    static {
        if (!cn.xiaochuan.a.a.b() || VERSION.SDK_INT < 21) {
            if (cn.xiaochuan.a.a.c() && VERSION.SDK_INT >= 21) {
                a = new d();
            } else if (VERSION.SDK_INT >= 23) {
                a = new c();
            } else {
                a = new a() {
                    public void a(Window window, boolean z) {
                    }
                };
            }
        } else if (VERSION.SDK_INT >= 23) {
            a = new c() {
                private a a = new b();

                public void a(Window window, boolean z) {
                    super.a(window, z);
                    this.a.a(window, z);
                }
            };
        } else {
            a = new b();
        }
    }

    public static void a(Window window, boolean z) {
        a.a(window, z);
    }
}
