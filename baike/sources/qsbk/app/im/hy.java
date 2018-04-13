package qsbk.app.im;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import qsbk.app.im.IMNotifyManager.NotificationBuilt;
import qsbk.app.push.NotificationIDs;

class hy implements NotificationBuilt {
    final /* synthetic */ Context a;
    final /* synthetic */ IMNotifyManager b;

    hy(IMNotifyManager iMNotifyManager, Context context) {
        this.b = iMNotifyManager;
        this.a = context;
    }

    public void onBuilt(Notification notification) {
        if (notification != null) {
            ((NotificationManager) this.a.getSystemService("notification")).notify(NotificationIDs.NOTIFICATION_ID, notification);
        }
    }
}
