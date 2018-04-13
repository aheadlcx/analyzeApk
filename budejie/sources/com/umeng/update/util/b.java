package com.umeng.update.util;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import java.lang.reflect.Field;

public class b {
    protected Context b;
    protected Notification c = new Notification();
    protected Builder d;

    public b(Context context) {
        this.b = context.getApplicationContext();
        if (VERSION.SDK_INT >= 14) {
            this.d = new Builder(context);
        }
    }

    public void e() {
        if (VERSION.SDK_INT >= 16) {
            try {
                Field declaredField = Builder.class.getDeclaredField("mActions");
                declaredField.setAccessible(true);
                declaredField.set(this.d, declaredField.get(this.d).getClass().newInstance());
            } catch (Exception e) {
            }
        }
    }

    public b b(RemoteViews remoteViews) {
        if (VERSION.SDK_INT < 16 && VERSION.SDK_INT >= 14) {
            this.d.setContent(remoteViews);
        }
        this.c.contentView = remoteViews;
        return this;
    }

    public b a(PendingIntent pendingIntent) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setContentIntent(pendingIntent);
        }
        this.c.contentIntent = pendingIntent;
        return this;
    }

    public b a(boolean z) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setOngoing(z);
        }
        Notification notification;
        if (z) {
            notification = this.c;
            notification.flags |= 2;
        } else {
            notification = this.c;
            notification.flags &= -3;
        }
        return this;
    }

    public b b(boolean z) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setAutoCancel(z);
        }
        Notification notification;
        if (z) {
            notification = this.c;
            notification.flags |= 16;
        } else {
            notification = this.c;
            notification.flags &= -17;
        }
        return this;
    }

    public b a(int i) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setSmallIcon(i);
        }
        this.c.icon = i;
        return this;
    }

    public b d(CharSequence charSequence) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setTicker(charSequence);
        }
        this.c.tickerText = charSequence;
        return this;
    }

    public b a(long j) {
        if (VERSION.SDK_INT >= 14) {
            this.d.setWhen(j);
        }
        this.c.when = j;
        return this;
    }
}
