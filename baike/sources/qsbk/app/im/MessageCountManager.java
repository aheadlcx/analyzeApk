package qsbk.app.im;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.GroupChatMsgStore;
import qsbk.app.im.datastore.GroupNoticeStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.utils.SharePreferenceUtils;

public class MessageCountManager {
    public static final String NEWFANS_COUNT = "new_fans_count";
    private static final HashMap<String, MessageCountManager> a = new HashMap();
    private final ChatMsgStore b;
    private final GroupChatMsgStore c;
    private final GroupNoticeStore d;
    private final String e;
    private Integer f;
    private int g;
    private int h = 0;
    private List<MessageCountManager$UnreadCountListener> i;
    private boolean j = false;
    private Object k = new Object();

    private MessageCountManager(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e = QsbkApp.currentUser.userId;
        } else {
            this.e = str;
        }
        this.b = ChatMsgStore.getChatMsgStore(this.e);
        this.c = GroupChatMsgStore.getInstance(this.e);
        this.d = GroupNoticeStore.getInstance(this.e);
        Util.imStorageQueue.postRunnable(new ie(this));
        this.h = getUnarchiveCount();
        this.i = new ArrayList();
    }

    public static String getNewFanKey(String str) {
        return NEWFANS_COUNT + str;
    }

    public static synchronized MessageCountManager getMessageCountManager(String str) {
        MessageCountManager messageCountManager;
        synchronized (MessageCountManager.class) {
            messageCountManager = (MessageCountManager) a.get(str);
            if (messageCountManager == null) {
                messageCountManager = new MessageCountManager(str);
                a.clear();
                a.put(str, messageCountManager);
            }
        }
        return messageCountManager;
    }

    public void updateUnreadCount(int i, int i2) {
        this.f = Integer.valueOf(i);
        this.g = Integer.valueOf(i2).intValue();
        notifyListener(this.f.intValue(), this.g);
    }

    public int getUnarchiveCount() {
        return SharePreferenceUtils.getSharePreferencesIntValue(getNewFanKey(this.e));
    }

    public synchronized void setUnarchiveCount(int i) {
        this.h = i;
        SharePreferenceUtils.setSharePreferencesValue(getNewFanKey(this.e), i);
    }

    public synchronized boolean addUnreadCountListener(MessageCountManager$UnreadCountListener messageCountManager$UnreadCountListener) {
        boolean z = false;
        synchronized (this) {
            if (messageCountManager$UnreadCountListener != null) {
                if (!this.i.contains(messageCountManager$UnreadCountListener)) {
                    z = this.i.add(messageCountManager$UnreadCountListener);
                }
            }
        }
        return z;
    }

    public synchronized boolean removeUnreadCountListener(MessageCountManager$UnreadCountListener messageCountManager$UnreadCountListener) {
        return this.i.remove(messageCountManager$UnreadCountListener);
    }

    public synchronized void addUnreadCount(int i, boolean z) {
        if (!z) {
            this.g += i;
            notifyListener(getUnreadCount(), this.g);
        } else if (i != 0) {
            while (this.f == null) {
                synchronized (this.k) {
                    try {
                        this.k.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.f = Integer.valueOf(Math.max(0, this.f.intValue() + i));
            notifyListener(getUnreadCount(), this.g);
        }
    }

    public synchronized int getUnreadCount() {
        int i;
        if (this.f == null) {
            this.j = true;
            i = -1;
        } else {
            i = this.f.intValue();
        }
        return i;
    }

    public synchronized int getUnregardedCount() {
        return this.g;
    }

    public synchronized void notifyListener(int i, int i2) {
        for (MessageCountManager$UnreadCountListener unread : this.i) {
            unread.unread(i, i2);
        }
    }
}
