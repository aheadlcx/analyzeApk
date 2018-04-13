package qsbk.app.im;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.widget.QiushiNotificationItemView.UserInfo;

public class QiushiNotificationCountManager {
    public static final String QIUSHI_PUSH_UID = "21089551";
    private static QiushiNotificationCountManager a;
    private static String b;
    private ChatMsgStore c;
    private List<NotificationListener> d;
    private Integer e;
    private byte[] f = new byte[0];
    private NotificationModel g;
    private boolean h = false;

    public interface NotificationListener {
        void onNewNotification(NotificationModel notificationModel);
    }

    public static class NotificationModel {
        UserInfo a;
        int b;

        public NotificationModel(UserInfo userInfo, int i) {
            this.b = i;
            this.a = userInfo;
        }

        public NotificationModel(String str, String str2, String str3, int i) {
            this.a = new UserInfo(str, str2, str3);
            this.b = i;
        }

        public int getUnReadCount() {
            return this.b;
        }

        public UserInfo getUserInfo() {
            return this.a;
        }
    }

    private QiushiNotificationCountManager(String str) {
        this.c = ChatMsgStore.getChatMsgStore(str);
        this.d = new ArrayList();
        b = str;
        postInit();
    }

    public static synchronized QiushiNotificationCountManager getInstance(String str) {
        QiushiNotificationCountManager qiushiNotificationCountManager;
        synchronized (QiushiNotificationCountManager.class) {
            if (a == null) {
                a = new QiushiNotificationCountManager(str);
            } else if (!TextUtils.equals(str, b)) {
                a = new QiushiNotificationCountManager(str);
            }
            qiushiNotificationCountManager = a;
        }
        return qiushiNotificationCountManager;
    }

    public static void tryRemoveAll() {
        if (a != null) {
            a.removeAllListener();
        }
    }

    public NotificationModel getLastNotification() {
        return this.g;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init() {
        /*
        r3 = this;
        r1 = r3.f;
        monitor-enter(r1);
        r0 = r3.e;	 Catch:{ Exception -> 0x0038, all -> 0x004a }
        if (r0 == 0) goto L_0x0019;
    L_0x0007:
        r0 = r3.e;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x0012;
    L_0x000b:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0035 }
        r3.e = r0;	 Catch:{ all -> 0x0035 }
    L_0x0012:
        r0 = r3.f;	 Catch:{ all -> 0x0035 }
        r0.notify();	 Catch:{ all -> 0x0035 }
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
    L_0x0018:
        return;
    L_0x0019:
        r0 = qsbk.app.im.datastore.Util.imStorageQueue;	 Catch:{ Exception -> 0x0038, all -> 0x004a }
        r2 = new qsbk.app.im.ir;	 Catch:{ Exception -> 0x0038, all -> 0x004a }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0038, all -> 0x004a }
        r0.postRunnable(r2);	 Catch:{ Exception -> 0x0038, all -> 0x004a }
        r0 = r3.e;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x002e;
    L_0x0027:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0035 }
        r3.e = r0;	 Catch:{ all -> 0x0035 }
    L_0x002e:
        r0 = r3.f;	 Catch:{ all -> 0x0035 }
        r0.notify();	 Catch:{ all -> 0x0035 }
    L_0x0033:
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        goto L_0x0018;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        throw r0;
    L_0x0038:
        r0 = move-exception;
        r0 = r3.e;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x0044;
    L_0x003d:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0035 }
        r3.e = r0;	 Catch:{ all -> 0x0035 }
    L_0x0044:
        r0 = r3.f;	 Catch:{ all -> 0x0035 }
        r0.notify();	 Catch:{ all -> 0x0035 }
        goto L_0x0033;
    L_0x004a:
        r0 = move-exception;
        r2 = r3.e;	 Catch:{ all -> 0x0035 }
        if (r2 != 0) goto L_0x0056;
    L_0x004f:
        r2 = 0;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x0035 }
        r3.e = r2;	 Catch:{ all -> 0x0035 }
    L_0x0056:
        r2 = r3.f;	 Catch:{ all -> 0x0035 }
        r2.notify();	 Catch:{ all -> 0x0035 }
        throw r0;	 Catch:{ all -> 0x0035 }
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.im.QiushiNotificationCountManager.init():void");
    }

    public int getUnreadCount() {
        return this.e == null ? 0 : this.e.intValue();
    }

    public void postInit() {
        new is(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void addListener(NotificationListener notificationListener) {
        synchronized (this.d) {
            if (!this.d.contains(notificationListener)) {
                this.d.add(notificationListener);
            }
        }
    }

    public boolean removeListener(NotificationListener notificationListener) {
        boolean remove;
        synchronized (this.d) {
            remove = this.d.remove(notificationListener);
        }
        return remove;
    }

    public void removeAllListener() {
        synchronized (this.d) {
            this.d.clear();
        }
    }

    public void setRead() {
        Util.imStorageQueue.postRunnable(new it(this));
        this.e = Integer.valueOf(0);
        if (this.g != null) {
            this.g.b = 0;
        }
        a(null);
    }

    public void clear() {
        Util.imStorageQueue.postRunnable(new iu(this));
    }

    public void clearSmileMsg() {
        Util.imStorageQueue.postRunnable(new iv(this));
    }

    public void clearCommentLikeMsg() {
        Util.imStorageQueue.postRunnable(new iw(this));
    }

    public void clearAtInfoMsg() {
        Util.imStorageQueue.postRunnable(new ix(this));
    }

    public void destroy() {
        removeAllListener();
        a = null;
    }

    public void unread(ChatMsg chatMsg) {
        if (chatMsg != null) {
            while (this.e == null) {
                synchronized (this.f) {
                    try {
                        this.h = true;
                        this.f.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (this.h) {
                this.h = false;
            } else {
                Integer num = this.e;
                this.e = Integer.valueOf(this.e.intValue() + 1);
            }
            a(chatMsg);
        }
    }

    private void a(ChatMsg chatMsg) {
        if (this.g == null) {
            this.g = new NotificationModel();
        }
        if (this.g.a == null) {
            this.g.a = new UserInfo();
        }
        if (chatMsg != null) {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(chatMsg.data).getJSONObject("jump_data").optJSONObject("from");
            } catch (JSONException e) {
            }
            if (jSONObject == null) {
                this.g.a.id = chatMsg.from;
                this.g.a.name = chatMsg.getFromNick();
                this.g.a.icon = chatMsg.getFromIcon();
            } else {
                this.g.a.name = jSONObject.optString(QsbkDatabase.LOGIN);
                this.g.a.icon = jSONObject.optString("icon");
                this.g.a.id = jSONObject.optString("id");
            }
        }
        this.g.b = this.e.intValue();
        a(this.g);
    }

    private void a(NotificationModel notificationModel) {
        synchronized (this.d) {
            for (NotificationListener onNewNotification : this.d) {
                onNewNotification.onNewNotification(notificationModel);
            }
        }
    }
}
