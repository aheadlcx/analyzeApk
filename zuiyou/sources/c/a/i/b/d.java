package c.a.i.b;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;
import c.a.d.a.a;
import c.a.i.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class d extends e {
    private static final String b = d.class.getSimpleName();
    private static Class<?> d;
    private static int e = 0;
    final EditText a;
    private Object c;

    public static void a(@DrawableRes int i) {
        e = i;
    }

    public d(EditText editText) {
        this.a = editText;
    }

    public void a(AttributeSet attributeSet, int i) {
        b();
    }

    public void a() {
        try {
            e = e.b(e);
            if (e != 0) {
                Drawable b = a.a().b(e);
                Object c = c();
                Class d = d();
                Field declaredField = d.getDeclaredField("mCursorDrawable");
                declaredField.setAccessible(true);
                declaredField.set(c, new Drawable[]{b, b});
                Method declaredMethod = d.getDeclaredMethod("updateCursorsPositions", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(c, new Object[0]);
            }
        } catch (Exception e) {
        }
    }

    private Object c() throws Exception {
        if (this.c == null) {
            Field declaredField = TextView.class.getDeclaredField("mEditor");
            declaredField.setAccessible(true);
            this.c = declaredField.get(this.a);
        }
        return this.c;
    }

    private Class<?> d() throws Exception {
        if (d == null) {
            d = Class.forName("android.widget.Editor");
        }
        return d;
    }

    public void b() {
        a();
    }

    public static d a(f fVar) {
        return new d(fVar);
    }
}
