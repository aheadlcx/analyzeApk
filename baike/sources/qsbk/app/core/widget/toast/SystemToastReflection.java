package qsbk.app.core.widget.toast;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SystemToastReflection extends SystemToast {
    private final Handler b = new Handler();
    private final Runnable c = new c(this);
    private boolean d = false;
    private Method e;
    private Object f;

    protected SystemToastReflection() {
    }

    public SystemToastReflection(Context context) {
        this.a = new Toast(context);
    }

    public static SystemToastReflection makeText(Context context, CharSequence charSequence, int i) {
        SystemToastReflection systemToastReflection = new SystemToastReflection();
        systemToastReflection.a = Toast.makeText(context, charSequence, i);
        return systemToastReflection;
    }

    public static SystemToastReflection makeText(Context context, @StringRes int i, int i2) throws NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    public void show() {
        try {
            a();
            if (this.f != null) {
                Field declaredField = this.f.getClass().getDeclaredField("mNextView");
                declaredField.setAccessible(true);
                declaredField.set(this.f, getView());
                if (this.e != null) {
                    this.e.invoke(this.f, (Object[]) null);
                }
                this.b.postDelayed(this.c, this.a.getDuration() == 0 ? 2000 : 3500);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() throws Throwable {
        if (!this.d) {
            Field declaredField = Toast.class.getDeclaredField("mTN");
            declaredField.setAccessible(true);
            this.f = declaredField.get(this.a);
            this.e = this.f.getClass().getDeclaredMethod("show", (Class[]) null);
            this.d = true;
        }
    }
}
