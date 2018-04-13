package c.a.h;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class b {
    private static b a;
    private final Context b;
    private final SharedPreferences c = this.b.getSharedPreferences("meta-data", 0);
    private final Editor d = this.c.edit();

    public static void a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context.getApplicationContext());
                }
            }
        }
    }

    public static b a() {
        return a;
    }

    private b(Context context) {
        this.b = context;
    }

    public b a(String str) {
        this.d.putString("skin-name", str);
        return this;
    }

    public String b() {
        return this.c.getString("skin-name", "");
    }

    public b a(int i) {
        this.d.putInt("skin-strategy", i);
        return this;
    }

    public int c() {
        return this.c.getInt("skin-strategy", 0);
    }

    public void d() {
        this.d.apply();
    }
}
