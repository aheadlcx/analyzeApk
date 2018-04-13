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
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.QiushiNotificationItemView.UserInfo;

public class QiuyouquanNotificationCountManager {
    public static final String QIUYOUQUAN_PUSH_UID = "27685144";
    private static QiuyouquanNotificationCountManager a;
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

    private QiuyouquanNotificationCountManager(String str) {
        this.c = ChatMsgStore.getChatMsgStore(str);
        this.d = new ArrayList();
        b = str;
        postInit();
    }

    public static synchronized QiuyouquanNotificationCountManager getInstance(String str) {
        QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager;
        synchronized (QiuyouquanNotificationCountManager.class) {
            if (a == null) {
                a = new QiuyouquanNotificationCountManager(str);
            } else if (!TextUtils.equals(str, b)) {
                a = new QiuyouquanNotificationCountManager(str);
            }
            qiuyouquanNotificationCountManager = a;
        }
        return qiuyouquanNotificationCountManager;
    }

    public static void tryRemoveAll() {
        if (a != null) {
            a.removeAll();
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
        r0 = r3.e;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        if (r0 == 0) goto L_0x0019;
    L_0x0007:
        r0 = r3.e;	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x0012;
    L_0x000b:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0045 }
        r3.e = r0;	 Catch:{ all -> 0x0045 }
    L_0x0012:
        r0 = r3.f;	 Catch:{ all -> 0x0045 }
        r0.notify();	 Catch:{ all -> 0x0045 }
        monitor-exit(r1);	 Catch:{ all -> 0x0045 }
    L_0x0018:
        return;
    L_0x0019:
        r0 = new qsbk.app.im.QiuyouquanNotificationCountManager$NotificationModel;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r0.<init>();	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r3.g = r0;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r0 = r3.g;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r2 = new qsbk.app.widget.QiushiNotificationItemView$UserInfo;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r2.<init>();	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r0.a = r2;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r0 = qsbk.app.im.datastore.Util.imStorageQueue;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r2 = new qsbk.app.im.iy;	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r0.postRunnable(r2);	 Catch:{ Exception -> 0x0048, all -> 0x005a }
        r0 = r3.e;	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x003e;
    L_0x0037:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0045 }
        r3.e = r0;	 Catch:{ all -> 0x0045 }
    L_0x003e:
        r0 = r3.f;	 Catch:{ all -> 0x0045 }
        r0.notify();	 Catch:{ all -> 0x0045 }
    L_0x0043:
        monitor-exit(r1);	 Catch:{ all -> 0x0045 }
        goto L_0x0018;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0045 }
        throw r0;
    L_0x0048:
        r0 = move-exception;
        r0 = r3.e;	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x0054;
    L_0x004d:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0045 }
        r3.e = r0;	 Catch:{ all -> 0x0045 }
    L_0x0054:
        r0 = r3.f;	 Catch:{ all -> 0x0045 }
        r0.notify();	 Catch:{ all -> 0x0045 }
        goto L_0x0043;
    L_0x005a:
        r0 = move-exception;
        r2 = r3.e;	 Catch:{ all -> 0x0045 }
        if (r2 != 0) goto L_0x0066;
    L_0x005f:
        r2 = 0;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x0045 }
        r3.e = r2;	 Catch:{ all -> 0x0045 }
    L_0x0066:
        r2 = r3.f;	 Catch:{ all -> 0x0045 }
        r2.notify();	 Catch:{ all -> 0x0045 }
        throw r0;	 Catch:{ all -> 0x0045 }
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.im.QiuyouquanNotificationCountManager.init():void");
    }

    public int getUnreadCount() {
        return this.e == null ? 0 : this.e.intValue();
    }

    public void postInit() {
        new iz(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
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

    public void removeAll() {
        synchronized (this.d) {
            this.d.clear();
        }
    }

    public void setRead() {
        Util.imStorageQueue.postRunnable(new ja(this));
        this.e = Integer.valueOf(0);
        if (this.g != null) {
            this.g.b = 0;
        }
        a(null);
    }

    public void setOtherRead() {
        int markOtherMessagesToReadWith = this.c.markOtherMessagesToReadWith(Integer.parseInt(QIUYOUQUAN_PUSH_UID));
        LogUtil.e(markOtherMessagesToReadWith + ":updateNo");
        LogUtil.e(this.e + ":mNewUnreadCount");
        this.e = Integer.valueOf(this.e.intValue() - markOtherMessagesToReadWith);
        this.e = Integer.valueOf(Math.max(0, this.e.intValue()));
        LogUtil.e(this.e + ":mUnread");
        if (this.g != null) {
            this.g.b = this.e.intValue();
        }
        a(null);
    }

    public void setLikedRead() {
        int markLikeMessagesToReadWith = this.c.markLikeMessagesToReadWith(Integer.parseInt(QIUYOUQUAN_PUSH_UID));
        LogUtil.e(markLikeMessagesToReadWith + ":updateNo");
        LogUtil.e(this.e + ":mNewUnreadCount");
        this.e = Integer.valueOf(this.e.intValue() - markLikeMessagesToReadWith);
        this.e = Integer.valueOf(Math.max(0, this.e.intValue()));
        LogUtil.e(this.e + ":mUnread");
        if (this.g != null) {
            this.g.b = this.e.intValue();
        }
        a(null);
    }

    public void clear() {
        this.c.deleteMessagesWith(QIUYOUQUAN_PUSH_UID);
    }

    public void clearLikeMsg() {
        Util.imStorageQueue.postRunnable(new jb(this));
    }

    public void clearAtMsg() {
        Util.imStorageQueue.postRunnable(new jc(this));
    }

    public void clearCirlceCommentLikeMsg() {
        Util.imStorageQueue.postRunnable(new jd(this));
    }

    public void cleanForwardMsg() {
        Util.imStorageQueue.postRunnable(new je(this));
    }

    public void destroy() {
        removeAll();
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
        if (chatMsg != null) {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(chatMsg.data).getJSONObject("jump_data").optJSONObject("from");
            } catch (JSONException e) {
            }
            synchronized (this.g) {
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
        }
        if (this.g != null) {
            this.g.b = this.e.intValue();
            a(this.g);
        }
    }

    private void a(NotificationModel notificationModel) {
        synchronized (this.d) {
            for (NotificationListener onNewNotification : this.d) {
                onNewNotification.onNewNotification(notificationModel);
            }
        }
    }
}
