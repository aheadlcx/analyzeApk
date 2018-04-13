package qsbk.app.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import qsbk.app.QsbkApp;
import qsbk.app.R;

public class VersionService extends Service {
    private NotificationManager a;
    private int b = 0;
    private boolean c = false;
    private Notification d = null;
    private RemoteViews e = null;
    private Handler f = new l(this);

    public void onCreate() {
        super.onCreate();
        this.c = true;
        this.a = (NotificationManager) getSystemService("notification");
        this.f.handleMessage(new Message());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void a(int i) {
        Notification notification;
        if (this.e == null) {
            this.e = new RemoteViews(getPackageName(), R.layout.notification_version);
            this.e.setTextViewText(R.id.n_title, "糗事百科");
            this.e.setTextViewText(R.id.n_text, i + "% ");
            this.e.setProgressBar(R.id.n_progress, 100, i, true);
        }
        if (this.d == null) {
            Intent intent = new Intent(this, getClass());
            intent.addFlags(536870912);
            this.d = new Builder(this).setSmallIcon(R.drawable.notification_icon).setAutoCancel(true).setContent(this.e).setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728)).build();
        }
        this.d.contentView.setTextViewText(R.id.n_text, i + "%");
        this.d.contentView.setProgressBar(R.id.n_progress, 100, i, false);
        if (this.c || QsbkApp.loading_process > 97) {
            notification = this.d;
            notification.defaults |= 1;
            notification = this.d;
            notification.defaults |= 2;
        }
        notification = this.d;
        notification.flags |= 2;
        this.a.notify(0, this.d);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
