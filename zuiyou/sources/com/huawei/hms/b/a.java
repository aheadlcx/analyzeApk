package com.huawei.hms.b;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Build.VERSION;

public abstract class a {
    private Activity a;
    private AlertDialog b;
    private a c;

    public interface a {
        void a(a aVar);

        void b(a aVar);
    }

    protected abstract String a(Context context);

    protected abstract String b(Context context);

    protected abstract String c(Context context);

    protected abstract String d(Context context);

    protected AlertDialog a(Activity activity) {
        Builder builder = new Builder(e(), f());
        CharSequence c = c(activity);
        if (c != null) {
            builder.setTitle(c);
        }
        c = a((Context) activity);
        if (c != null) {
            builder.setMessage(c);
        }
        c = b(activity);
        if (c != null) {
            builder.setPositiveButton(c, new b(this));
        }
        c = d(activity);
        if (c != null) {
            builder.setNegativeButton(c, new c(this));
        }
        return builder.create();
    }

    public void a(Activity activity, a aVar) {
        this.a = activity;
        this.c = aVar;
        if (this.a == null || this.a.isFinishing()) {
            com.huawei.hms.support.log.a.d("AbstractDialog", "In show, The activity is null or finishing.");
            return;
        }
        this.b = a(this.a);
        this.b.setCanceledOnTouchOutside(false);
        this.b.setOnCancelListener(new d(this));
        this.b.setOnKeyListener(new e(this));
        this.b.show();
    }

    public void a() {
        if (this.b != null) {
            this.b.cancel();
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.dismiss();
        }
    }

    protected void c() {
        if (this.c != null) {
            this.c.b(this);
        }
    }

    protected void d() {
        if (this.c != null) {
            this.c.a(this);
        }
    }

    protected Activity e() {
        return this.a;
    }

    protected int f() {
        if (e(this.a) == 0 || VERSION.SDK_INT < 16) {
            return 3;
        }
        return 0;
    }

    private static int e(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
    }
}
