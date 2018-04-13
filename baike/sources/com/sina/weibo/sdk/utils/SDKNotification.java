package com.sina.weibo.sdk.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;

public class SDKNotification {
    private Context a;
    private Notification b;

    public static class SDKNotificationBuilder {
        private String a;
        private String b;
        private String c;
        private PendingIntent d;
        private long[] e;
        private Uri f;

        public static SDKNotificationBuilder buildUpon() {
            return new SDKNotificationBuilder();
        }

        public SDKNotificationBuilder setTickerText(String str) {
            this.a = str;
            return this;
        }

        public SDKNotificationBuilder setNotificationTitle(String str) {
            this.b = str;
            return this;
        }

        public SDKNotificationBuilder setNotificationContent(String str) {
            this.c = str;
            return this;
        }

        public SDKNotificationBuilder setNotificationPendingIntent(PendingIntent pendingIntent) {
            this.d = pendingIntent;
            return this;
        }

        public SDKNotificationBuilder setVibrate(long[] jArr) {
            this.e = jArr;
            return this;
        }

        public SDKNotificationBuilder setSoundUri(Uri uri) {
            this.f = uri;
            return this;
        }

        public SDKNotification build(Context context) {
            Builder builder = new Builder(context);
            builder.setAutoCancel(true);
            builder.setContentIntent(this.d);
            builder.setTicker(this.a);
            builder.setSmallIcon(a(context));
            builder.setWhen(System.currentTimeMillis());
            if (this.f != null) {
                builder.setSound(this.f);
            }
            if (this.e != null) {
                builder.setVibrate(this.e);
            }
            builder.setLargeIcon(((BitmapDrawable) ResourceManager.getDrawable(context, "weibosdk_notification_icon.png")).getBitmap());
            builder.setContentTitle(this.b);
            builder.setContentText(this.c);
            return new SDKNotification(context, builder.build());
        }

        private static int a(Context context) {
            int a = a(context, "com_sina_weibo_sdk_weibo_logo", "drawable");
            return a > 0 ? a : 17301659;
        }

        private static int a(Context context, String str, String str2) {
            String packageName = context.getApplicationContext().getPackageName();
            try {
                return context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(str, str2, packageName);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    private SDKNotification(Context context, Notification notification) {
        this.a = context.getApplicationContext();
        this.b = notification;
    }

    public void show(int i) {
        if (this.b != null) {
            ((NotificationManager) this.a.getSystemService("notification")).notify(i, this.b);
        }
    }
}
