package com.sprite.ads.internal.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;
import com.sprite.ads.internal.utils.ResUtil;
import com.sprite.ads.internal.utils.ResUtil.ResType;

public class b implements d {
    private Context a;
    private NotificationManager b;
    private Notification c;
    private int d = ((int) System.currentTimeMillis());
    private String e;

    public b(Context context) {
        this.a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    private static Bitmap a(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    @NonNull
    private RemoteViews b(String str, int i) {
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), ResUtil.a(this.a, ResType.b, "notification_down"));
        remoteViews.setTextViewText(ResUtil.a(this.a, ResType.a, "notifiTvName"), str);
        remoteViews.setTextViewText(ResUtil.a(this.a, ResType.a, "notifiTvRate"), i + "%");
        remoteViews.setViewVisibility(ResUtil.a(this.a, ResType.a, "notifiTvResult"), 8);
        try {
            remoteViews.setImageViewBitmap(ResUtil.a(this.a, ResType.a, "notifiIvIcon"), a(this.a.getPackageManager().getApplicationIcon(this.a.getPackageName())));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        remoteViews.setProgressBar(ResUtil.a(this.a, ResType.a, "notifiPbRate"), 100, i, false);
        return remoteViews;
    }

    public void a() {
        this.b.cancel(this.d);
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(String str, int i) {
        if (this.c == null) {
            this.c = new Notification();
        }
        this.c.icon = 17301633;
        this.c.contentIntent = PendingIntent.getBroadcast(this.a, 0, new Intent(), 268435456);
        this.c.tickerText = str;
        this.c.flags = 2;
        RemoteViews b = b(str, i);
        this.c.contentView = b;
        Intent intent = new Intent();
        intent.setAction("ad.stop.down");
        intent.putExtra("downId", this.d);
        intent.putExtra("downloadUrl", this.e);
        b.setOnClickPendingIntent(ResUtil.a(this.a, ResType.a, "notifiPasuse"), PendingIntent.getBroadcast(this.a, this.d, intent, 268435456));
        this.b.notify(this.d, this.c);
    }

    public void a(String str, String str2) {
        Notification notification = new Notification();
        notification.icon = 17301633;
        notification.tickerText = str;
        notification.flags = 16;
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), ResUtil.a(this.a, ResType.b, "notification_down"));
        remoteViews.setTextViewText(ResUtil.a(this.a, ResType.a, "notifiTvName"), str);
        remoteViews.setTextViewText(ResUtil.a(this.a, ResType.a, "notifiTvResult"), str2);
        remoteViews.setViewVisibility(ResUtil.a(this.a, ResType.a, "notifiTvResult"), 0);
        remoteViews.setViewVisibility(ResUtil.a(this.a, ResType.a, "notifiTvRate"), 8);
        remoteViews.setViewVisibility(ResUtil.a(this.a, ResType.a, "notifiPbRate"), 8);
        remoteViews.setViewVisibility(ResUtil.a(this.a, ResType.a, "notifiPasuse"), 8);
        try {
            remoteViews.setImageViewBitmap(ResUtil.a(this.a, ResType.a, "notifiIvIcon"), a(this.a.getPackageManager().getApplicationIcon(this.a.getPackageName())));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        notification.contentView = remoteViews;
        notification.contentIntent = PendingIntent.getActivity(this.a, 0, new Intent(), 268435456);
        this.b.notify(this.d, notification);
    }
}
