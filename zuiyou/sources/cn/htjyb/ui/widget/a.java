package cn.htjyb.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class a extends Toast {

    private class a implements Callback {
        final /* synthetic */ a a;
        private final Handler b;

        public a(a aVar, Handler handler) {
            this.a = aVar;
            this.b = handler;
        }

        public boolean handleMessage(Message message) {
            try {
                this.b.handleMessage(message);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return true;
        }
    }

    private class b implements Runnable {
        final /* synthetic */ a a;
        private final Runnable b;

        public b(a aVar, Runnable runnable) {
            this.a = aVar;
            this.b = runnable;
        }

        public void run() {
            try {
                this.b.run();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public a(Context context) {
        super(context);
    }

    public static Toast a(Context context, CharSequence charSequence, int i) {
        Toast aVar = new a(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        Resources resources = context.getResources();
        View inflate = layoutInflater.inflate(resources.getIdentifier("transient_notification", "layout", "android"), null);
        ((TextView) inflate.findViewById(resources.getIdentifier("message", "id", "android"))).setText(charSequence);
        aVar.setView(inflate);
        aVar.setDuration(i);
        return aVar;
    }

    public void show() {
        if (a()) {
            b();
        }
        super.show();
    }

    protected boolean a() {
        return VERSION.SDK_INT == 25;
    }

    private void b() {
        try {
            Object a = a((Object) this, "mTN");
            if (a != null) {
                boolean z;
                Object a2 = a(a, "mShow");
                if (a2 == null || !(a2 instanceof Runnable)) {
                    z = false;
                } else {
                    z = a(a, "mShow", new b(this, (Runnable) a2));
                }
                if (!z) {
                    Object a3 = a(a, "mHandler");
                    if (a3 != null && (a3 instanceof Handler)) {
                        z = a(a3, "mCallback", new a(this, (Handler) a3));
                    }
                }
                if (!z) {
                    Log.e("ToastCompat", "tryToHack error.");
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean a(Object obj, String str, Object obj2) {
        Field b = b(obj, str);
        if (b != null) {
            try {
                if (Modifier.isFinal(b.getModifiers())) {
                    Field declaredField = Field.class.getDeclaredField("accessFlags");
                    declaredField.setAccessible(true);
                    declaredField.setInt(b, b.getModifiers() & -17);
                }
                if (!b.isAccessible()) {
                    b.setAccessible(true);
                }
                b.set(obj, obj2);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static Object a(Object obj, String str) {
        return a(obj, b(obj, str));
    }

    private static Object a(Object obj, Field field) {
        if (field != null) {
            try {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Field b(Object obj, String str) {
        Class cls = obj.getClass();
        while (cls != Object.class) {
            try {
                return cls.getDeclaredField(str);
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }
}
