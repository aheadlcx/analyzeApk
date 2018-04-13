package com.budejie.www.download;

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
import com.budejie.www.R;

public class e implements h {
    private Context a;
    private NotificationManager b;
    private Notification c;
    private int d = ((int) System.currentTimeMillis());
    private String e;

    public e(Context context) {
        this.a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    public void a(String str) {
        this.e = str;
    }

    @NonNull
    private RemoteViews b(String str, int i) {
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), R.layout.notification_down);
        remoteViews.setTextViewText(R.id.notifiTvName, str);
        remoteViews.setTextViewText(R.id.notifiTvRate, i + "%");
        remoteViews.setViewVisibility(R.id.notifiTvResult, 8);
        try {
            remoteViews.setImageViewBitmap(R.id.notifiIvIcon, a(this.a.getPackageManager().getApplicationIcon(this.a.getPackageName())));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        remoteViews.setProgressBar(R.id.notifiPbRate, 100, i, false);
        return remoteViews;
    }

    private static Bitmap a(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
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
        intent.setAction("stop.down");
        intent.putExtra("downId", this.d);
        intent.putExtra("downloadUrl", this.e);
        b.setOnClickPendingIntent(R.id.notifiPasuse, PendingIntent.getBroadcast(this.a, this.d, intent, 268435456));
        this.b.notify(this.d, this.c);
    }

    public void a(String str, String str2) {
        Notification notification = new Notification();
        notification.icon = 17301633;
        notification.tickerText = str;
        notification.flags = 16;
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), R.layout.notification_down);
        remoteViews.setTextViewText(R.id.notifiTvName, str);
        remoteViews.setTextViewText(R.id.notifiTvResult, str2);
        remoteViews.setViewVisibility(R.id.notifiTvResult, 0);
        remoteViews.setViewVisibility(R.id.notifiTvRate, 8);
        remoteViews.setViewVisibility(R.id.notifiPbRate, 8);
        remoteViews.setViewVisibility(R.id.notifiPasuse, 8);
        try {
            remoteViews.setImageViewBitmap(R.id.notifiIvIcon, a(this.a.getPackageManager().getApplicationIcon(this.a.getPackageName())));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        notification.contentView = remoteViews;
        notification.contentIntent = PendingIntent.getActivity(this.a, 0, new Intent(), 268435456);
        this.b.notify(this.d, notification);
    }

    public void a() {
        this.b.cancel(this.d);
    }
}
