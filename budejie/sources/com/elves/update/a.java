package com.elves.update;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.budejie.www.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a {
    Context a;
    Notification b;
    Notification c;
    NotificationManager d;

    public a(Context context) {
        this.a = context;
        this.d = (NotificationManager) context.getSystemService("notification");
    }

    public void a(int i, int i2, int i3, String str, boolean z) {
        boolean z2;
        if (this.b == null) {
            CharSequence charSequence;
            this.b = new Notification(R.drawable.down_loading, "正在下载" + str, System.currentTimeMillis());
            this.b.icon = R.drawable.down_loading;
            this.b.flags = 16;
            this.b.when = System.currentTimeMillis();
            this.b.contentView = new RemoteViews(this.a.getPackageName(), R.layout.notifying);
            String str2 = "";
            if (z) {
                charSequence = "正在下载视频：" + str;
            } else {
                charSequence = "正在下载" + str;
            }
            if (TextUtils.isEmpty(str)) {
                this.b.contentView.setTextViewText(R.id.notifyId, "正在下载" + this.a.getText(R.string.app_name));
            } else {
                this.b.contentView.setTextViewText(R.id.notifyId, charSequence);
            }
            this.b.contentIntent = PendingIntent.getActivity(this.a, i, new Intent(), 0);
        }
        RemoteViews remoteViews = this.b.contentView;
        if (i2 <= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        remoteViews.setProgressBar(R.id.notifyBar, i2, i3, z2);
        this.d.notify(i, this.b);
    }

    public void a(int i, int i2, String str, String str2, boolean z) {
        PendingIntent broadcast;
        CharSequence substring = str.substring(str.lastIndexOf("/") + 1);
        CharSequence format = new SimpleDateFormat("hh:MM:ss").format(new Date());
        Notification notification = new Notification(R.drawable.down_fail, "下载失败", System.currentTimeMillis());
        notification.flags = 16;
        notification.contentView = new RemoteViews(this.a.getPackageName(), R.layout.notifyed);
        notification.contentView.setImageViewResource(R.id.notifyLog, i2);
        notification.contentView.setTextViewText(R.id.notifyTitle, substring);
        notification.contentView.setTextViewText(R.id.notifyTime, format);
        String str3 = "";
        if (z) {
            substring = "下载失败,请重新下载";
            broadcast = PendingIntent.getBroadcast(this.a, i, new Intent(), 0);
        } else {
            substring = "下载失败，点击重新下载";
            Intent intent = new Intent("com.budejie.download.failed");
            intent.putExtra("url", str2);
            intent.putExtra("notifyId", i);
            broadcast = PendingIntent.getBroadcast(this.a, i, intent, 0);
        }
        notification.contentView.setTextViewText(R.id.notifyMessage, substring);
        notification.contentIntent = broadcast;
        this.d.notify(i, notification);
    }

    public void a(int i, int i2, String str, boolean z, String str2) {
        CharSequence charSequence;
        PendingIntent activity;
        String substring = str.substring(str.lastIndexOf("/") + 1);
        CharSequence format = new SimpleDateFormat("hh:MM:ss").format(new Date());
        Notification notification = new Notification(R.drawable.down_success, "下载成功", System.currentTimeMillis());
        notification.flags = 16;
        notification.contentView = new RemoteViews(this.a.getPackageName(), R.layout.notifyed);
        notification.contentView.setImageViewResource(R.id.notifyLog, i2);
        String str3 = "";
        Intent intent;
        if (z) {
            charSequence = "下载成功";
            intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.parse(str), "video/mp4");
            activity = PendingIntent.getActivity(this.a, 0, intent, 0);
        } else {
            charSequence = "下载成功，点击安装";
            intent = new Intent("com.budejie.download.successed");
            intent.putExtra("fileName", str);
            intent.putExtra("notifyId", i);
            activity = PendingIntent.getBroadcast(this.a, i, intent, 0);
            str2 = substring;
        }
        notification.contentView.setTextViewText(R.id.notifyMessage, charSequence);
        notification.contentView.setTextViewText(R.id.notifyTitle, str2);
        notification.contentView.setTextViewText(R.id.notifyTime, format);
        notification.contentIntent = activity;
        this.d.notify(i, notification);
    }

    public void a(int i, String str) {
        if (this.c == null) {
            this.c = new Notification();
        }
        this.c.icon = 17301640;
        this.c.flags = 16;
        this.c.tickerText = str;
        this.c.contentView = new RemoteViews(this.a.getPackageName(), R.layout.share_notify);
        this.c.contentView.setImageViewResource(R.id.notifyLog, R.drawable.icon_new);
        this.c.contentView.setTextViewText(R.id.notifyId, str);
        this.c.contentView.setProgressBar(R.id.notifyBar, 0, 1, true);
        this.c.contentIntent = PendingIntent.getActivity(this.a, i, new Intent(), 0);
        this.d.notify(i, this.c);
    }

    public void a(int i, boolean z, int i2) {
        if (this.c == null) {
            this.c = new Notification();
        }
        this.c.flags = 16;
        this.c.contentView = new RemoteViews(this.a.getPackageName(), R.layout.notifyed);
        this.c.contentView.setImageViewResource(R.id.notifyLog, R.drawable.icon_new);
        if (z) {
            this.c.icon = R.drawable.send_ok;
            this.c.tickerText = this.a.getString(i2);
            this.c.contentView.setTextViewText(R.id.notifyMessage, this.a.getString(R.string.operate_success));
        } else {
            this.c.icon = 17301624;
            this.c.tickerText = this.a.getString(i2);
            this.c.contentView.setTextViewText(R.id.notifyMessage, this.a.getString(R.string.operate_fail));
        }
        this.c.contentIntent = PendingIntent.getActivity(this.a, i, new Intent(), 0);
        this.d.notify(i, this.c);
    }

    public void a(int i, boolean z, String str) {
        if (this.c == null) {
            this.c = new Notification();
        }
        this.c.flags = 16;
        this.c.contentView = new RemoteViews(this.a.getPackageName(), R.layout.notifyed);
        this.c.contentView.setImageViewResource(R.id.notifyLog, R.drawable.icon_new);
        if (z) {
            this.c.icon = R.drawable.send_ok;
            this.c.tickerText = str;
            this.c.contentView.setTextViewText(R.id.notifyMessage, this.a.getString(R.string.operate_success));
        } else {
            this.c.icon = 17301624;
            this.c.tickerText = str;
            this.c.contentView.setTextViewText(R.id.notifyMessage, this.a.getString(R.string.operate_fail));
        }
        this.c.contentIntent = PendingIntent.getActivity(this.a, i, new Intent(), 0);
        this.d.notify(i, this.c);
    }

    public void a(int i) {
        this.d.cancel(i);
    }
}
