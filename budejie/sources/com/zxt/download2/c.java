package com.zxt.download2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import com.budejie.www.R;
import java.io.File;

public class c implements b {
    private Context a;
    private Notification b;
    private int c;
    private NotificationManager d;
    private int e = 0;

    public c(Context context, f fVar) {
        this.a = context;
        this.c = fVar.getUrl().hashCode();
        this.d = (NotificationManager) this.a.getSystemService("notification");
        this.b = b(fVar.b());
    }

    public void d() {
        this.b.contentView.setTextViewText(R.id.notify_state, this.a.getString(R.string.download_stopped));
        this.d.notify(this.c, this.b);
        this.d.cancel(this.c);
    }

    public void b() {
        this.d.notify(this.c, this.b);
    }

    public void a(int i, int i2, int i3) {
        int i4 = (i * 100) / i2;
        if (i4 - this.e > 1) {
            this.e = i4;
            this.b.contentView.setTextViewText(R.id.notify_state, String.format(this.a.getString(R.string.download_speed), new Object[]{Integer.valueOf(this.e), Integer.valueOf(i3)}));
            this.b.contentView.setProgressBar(R.id.notify_processbar, 100, i4, false);
            this.d.notify(this.c, this.b);
        }
    }

    public void c() {
        this.b.contentView.setTextViewText(R.id.notify_state, this.a.getString(R.string.download_paused));
        this.b.contentView.setProgressBar(R.id.notify_processbar, 100, this.e, false);
        this.d.notify(this.c, this.b);
    }

    public void a(String str) {
        this.b.contentView.setImageViewResource(R.id.noitfy_icon, R.drawable.down_success);
        this.b.contentView.setTextViewText(R.id.notify_state, this.a.getString(R.string.download_finished));
        this.b.contentView.setProgressBar(R.id.notify_processbar, 100, 100, false);
        Notification notification = this.b;
        notification.flags |= 16;
        notification = this.b;
        notification.defaults |= 1;
        notification = this.b;
        notification.defaults |= 4;
        Intent intent = new Intent(this.a, DownloadListActivity.class);
        intent.putExtra("isDownloaded", true);
        this.b.contentIntent = PendingIntent.getActivity(this.a, 0, intent, 134217728);
        this.d.notify(this.c, this.b);
        c(str);
    }

    public void e() {
        this.b.contentView.setImageViewResource(R.id.noitfy_icon, R.drawable.down_fail);
        this.b.contentView.setTextViewText(R.id.notify_state, this.a.getString(R.string.download_failed));
        this.b.contentView.setProgressBar(R.id.notify_processbar, 100, 0, true);
        this.d.notify(this.c, this.b);
        this.d.cancel(this.c);
    }

    private void c(String str) {
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(fromFile);
            this.a.sendBroadcast(intent);
        } catch (Exception e) {
        }
    }

    public Notification b(String str) {
        Notification notification = new Notification(R.drawable.down_loading, this.a.getString(R.string.downloading_msg) + str, System.currentTimeMillis());
        notification.contentView = new RemoteViews(this.a.getPackageName(), R.layout.download_notify);
        notification.contentView.setProgressBar(R.id.notify_processbar, 100, 0, false);
        notification.contentView.setTextViewText(R.id.notify_state, this.a.getString(R.string.downloading_msg));
        notification.contentView.setTextViewText(R.id.notify_text, str);
        Intent intent = new Intent(this.a, DownloadListActivity.class);
        intent.putExtra("isDownloaded", false);
        notification.contentIntent = PendingIntent.getActivity(this.a, 0, intent, 134217728);
        return notification;
    }
}
