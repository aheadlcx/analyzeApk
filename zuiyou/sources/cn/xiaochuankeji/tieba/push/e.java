package cn.xiaochuankeji.tieba.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.push.receiver.MessageReceiver;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.common.a.a;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.chat.ChatActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;

public final class e extends ContextWrapper {
    private static e a;

    private e(Context context) {
        super(context);
        if (VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                NotificationChannel notificationChannel = new NotificationChannel("贴子", "贴子", 4);
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationChannel.setLockscreenVisibility(1);
                notificationManager.createNotificationChannel(notificationChannel);
                notificationChannel = new NotificationChannel("私信", "私信", 3);
                notificationChannel.setLockscreenVisibility(0);
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
                notificationChannel = new NotificationChannel("树洞消息", "树洞消息", 3);
                notificationChannel.setLockscreenVisibility(0);
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
                notificationChannel = new NotificationChannel("下载", "下载", 1);
                notificationChannel.setLockscreenVisibility(1);
                notificationChannel.enableVibration(false);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    public static e a() {
        if (a == null) {
            a = new e(BaseApplication.getAppContext());
        }
        return a;
    }

    public void a(cn.xiaochuan.push.e eVar) {
        if (eVar != null) {
            int i = eVar.g;
            Context appContext = BaseApplication.getAppContext();
            CharSequence string = TextUtils.isEmpty(eVar.c) ? appContext.getString(R.string.app_name) : eVar.c;
            CharSequence charSequence = eVar.d;
            Intent intent = new Intent(appContext, MessageReceiver.class);
            intent.setAction("cn.xiaochuan.push.action.clicked");
            intent.putExtra("p_m_extra_data", eVar);
            PendingIntent broadcast = PendingIntent.getBroadcast(appContext, i, intent, 134217728);
            Intent intent2 = new Intent(appContext, MessageReceiver.class);
            intent2.setAction("cn.xiaochuan.push.action.delete");
            intent2.putExtra("p_m_extra_data", eVar);
            Builder deleteIntent = new Builder(appContext, "贴子").setSmallIcon(R.drawable.mipush_small_notification).setLargeIcon(BitmapFactory.decodeResource(appContext.getResources(), R.drawable.mipush_notification)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setVisibility(1).setContentTitle(string).setContentText(charSequence).setContentIntent(broadcast).setDeleteIntent(PendingIntent.getBroadcast(appContext, i, intent2, 134217728));
            boolean isApplicationInBackground = BaseApplication.isApplicationInBackground();
            if (isApplicationInBackground) {
                deleteIntent.setDefaults(-1);
            } else {
                deleteIntent.setDefaults(0);
            }
            Notification build = deleteIntent.build();
            if (isApplicationInBackground) {
                a.a(BaseApplication.getAppContext(), i, build);
            } else {
                NotificationManagerCompat.from(appContext).notify(i, build);
            }
        }
    }

    public void a(XSession xSession) {
        if (xSession != null && xSession.x_other != null) {
            String str;
            Context appContext = BaseApplication.getAppContext();
            int c = c(xSession);
            int max = Math.max(1, xSession.unread);
            int i = VERSION.SDK_INT < 21 ? R.drawable.mipush_notification : R.drawable.mipush_small_notification;
            Builder contentTitle = new Builder(appContext, xSession.isAnonymous() ? "树洞消息" : "私信").setContentTitle(appContext.getString(R.string.app_name));
            StringBuilder append = new StringBuilder().append(xSession.x_other.name).append(" 给你发了");
            if (max == 1) {
                str = "一";
            } else {
                str = String.valueOf(" " + max + " ");
            }
            StringBuilder append2 = append.append(str);
            if (xSession.session_type == 1) {
                str = "条私信";
            } else {
                str = "条树洞消息";
            }
            Builder autoCancel = contentTitle.setContentText(append2.append(str).toString()).setWhen(System.currentTimeMillis()).setSmallIcon(i).setAutoCancel(true);
            int currentTimeMillis = (int) System.currentTimeMillis();
            Intent intent = new Intent(appContext, ChatActivity.class);
            intent.putExtra("session", xSession);
            intent.putExtra("random", currentTimeMillis);
            intent.addFlags(335544320);
            intent.putExtra("OPEN_FROM_NOTIFICATION", true);
            autoCancel.setContentIntent(PendingIntent.getActivity(appContext, currentTimeMillis, intent, 134217728));
            if (d.b()) {
                autoCancel.setDefaults(-1);
                a.a(BaseApplication.getAppContext(), c, autoCancel.build());
                return;
            }
            autoCancel.setDefaults(0);
            NotificationManagerCompat from = NotificationManagerCompat.from(appContext);
            if (from.areNotificationsEnabled()) {
                from.notify(c, autoCancel.build());
            }
        }
    }

    private int c(XSession xSession) {
        return Math.abs(Long.valueOf(xSession.x_sid).hashCode());
    }

    public void b(XSession xSession) {
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getAppContext().getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(c(xSession));
        }
    }

    public void a(int i, Notification notification) {
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getAppContext().getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.notify(i, notification);
        }
    }
}
