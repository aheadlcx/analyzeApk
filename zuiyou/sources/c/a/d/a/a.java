package c.a.d.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import c.a.c.c;

public class a {
    private static volatile a a;
    private final Context b;
    private Resources c;
    private String d;
    private String e;
    private c f;
    private boolean g;

    private a(Context context) {
        this.b = context.getApplicationContext();
        b();
    }

    public static void a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
    }

    public static a a() {
        return a;
    }

    public void b() {
        this.c = this.b.getResources();
        this.d = this.b.getPackageName();
        this.e = "";
        this.f = null;
        this.g = true;
    }

    public void a(Resources resources, String str, String str2, c cVar) {
        this.c = resources;
        this.d = str;
        this.e = str2;
        this.f = cVar;
        this.g = TextUtils.isEmpty(str2);
    }

    public Resources c() {
        return this.c;
    }

    public int a(int i) {
        if (i == 0) {
            return 0;
        }
        if (!this.g) {
            int e = e(i);
            if (e != 0) {
                return this.c.getColor(e);
            }
        }
        return ContextCompat.getColor(this.b, i);
    }

    public Drawable a(Context context, int i) {
        if (i == 0) {
            return null;
        }
        if (!this.g) {
            int e = e(i);
            if (e != 0) {
                try {
                    return this.c.getDrawable(e);
                } catch (Exception e2) {
                }
            }
        }
        return AppCompatResources.getDrawable(context, i);
    }

    public Drawable b(int i) {
        if (i == 0) {
            return null;
        }
        if (!this.g) {
            int e = e(i);
            if (e != 0) {
                return this.c.getDrawable(e);
            }
        }
        return ContextCompat.getDrawable(this.b, i);
    }

    public ColorStateList c(int i) {
        if (i == 0) {
            return null;
        }
        if (!this.g) {
            int e = e(i);
            if (e != 0) {
                return this.c.getColorStateList(e);
            }
        }
        return ContextCompat.getColorStateList(this.b, i);
    }

    private int e(int i) {
        String str = null;
        try {
            if (this.f != null) {
                str = this.f.a(this.b, this.e, i);
            }
            if (TextUtils.isEmpty(str)) {
                str = this.b.getResources().getResourceEntryName(i);
            }
            return this.c.getIdentifier(str, this.b.getResources().getResourceTypeName(i), this.d);
        } catch (Exception e) {
            return 0;
        }
    }

    @DrawableRes
    public int d(int i) {
        if (this.g) {
            return i;
        }
        int e = e(i);
        return e != 0 ? e : i;
    }
}
