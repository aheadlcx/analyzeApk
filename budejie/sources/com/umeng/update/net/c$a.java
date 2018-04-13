package com.umeng.update.net;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import com.umeng.update.util.b;
import u.upd.c;
import u.upd.i;
import u.upd.k;

class c$a extends b {
    public c$a(Context context) {
        super(context);
    }

    public c$a a(RemoteViews remoteViews) {
        this.c.contentView = remoteViews;
        return this;
    }

    public c$a a(CharSequence charSequence) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setContentText(charSequence);
        }
        this.c.contentView.setTextViewText(i.a(this.b), charSequence);
        return this;
    }

    public c$a b(CharSequence charSequence) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setContentTitle(charSequence);
        }
        this.c.contentView.setTextViewText(i.d(this.b), charSequence);
        return this;
    }

    public c$a a(int i, int i2, boolean z) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setProgress(i, i2, z);
        }
        this.c.contentView.setProgressBar(i.c(this.b), 100, i2, false);
        return this;
    }

    public c$a a() {
        this.c.contentView.setViewVisibility(i.e(this.b), 8);
        this.c.contentView.setViewVisibility(i.f(this.b), 8);
        return this;
    }

    public c$a a(PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        this.c.contentView.setOnClickPendingIntent(i.e(this.b), pendingIntent);
        this.c.contentView.setViewVisibility(i.e(this.b), 0);
        this.c.contentView.setViewVisibility(i.f(this.b), 0);
        this.c.contentView.setOnClickPendingIntent(i.f(this.b), pendingIntent2);
        return this;
    }

    public c$a b() {
        int e = i.e(this.b);
        this.c.contentView.setTextViewText(e, this.b.getResources().getString(k.e(this.b.getApplicationContext())));
        this.c.contentView.setInt(e, "setBackgroundResource", c.a(this.b).b("umeng_common_gradient_green"));
        return this;
    }

    public c$a c() {
        int e = i.e(this.b);
        this.c.contentView.setTextViewText(e, this.b.getResources().getString(k.d(this.b.getApplicationContext())));
        this.c.contentView.setInt(e, "setBackgroundResource", c.a(this.b).b("umeng_common_gradient_orange"));
        return this;
    }

    public Notification d() {
        if (VERSION.SDK_INT >= 16) {
            return this.d.build();
        }
        if (VERSION.SDK_INT >= 14) {
            return this.d.getNotification();
        }
        return this.c;
    }

    public void a(int i, String str, PendingIntent pendingIntent) {
        if (VERSION.SDK_INT >= 16) {
            this.d.addAction(i, str, pendingIntent);
        }
    }
}
