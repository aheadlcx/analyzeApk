package qsbk.app.im;

import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import qsbk.app.QsbkApp;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.PrefrenceKeys;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.GroupChatMsgStore;
import qsbk.app.im.datastore.SyncMsgStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.model.Laisee;
import qsbk.app.utils.image.issue.Logger;

public class SyncManager {
    public static final int SYNC_LIMITATION = 0;
    public static final int SYNC_NO_LIMITATION = 1;
    public static final int SYNC_PING = 0;
    public static final int TYPE_GROUP = 2;
    public static final int TYPE_PUSH = 3;
    public static final int TYPE_USER = 1;
    private static long a = i.MIN_UPLOAD_INTERVAL;
    private static long b = Config.BPLUS_DELAY_TIME;
    private Runnable c;
    private boolean d;
    private ConcurrentHashMap<String, Long> e;
    private ConcurrentHashMap<String, Long> f;
    private ConcurrentHashMap<String, Long> g;

    public SyncManager() {
        this.d = false;
        this.e = new ConcurrentHashMap();
        this.f = new ConcurrentHashMap();
        this.g = new ConcurrentHashMap();
        this.d = false;
        this.c = new jg(this);
    }

    private static final void a(Map map) {
        if (map != null) {
            map.clear();
        }
    }

    private static final String a(int i, String str) {
        String str2 = "";
        if (i == 1) {
            return User.UNDEFINED;
        }
        if (i == 2) {
            return IXAdRequestInfo.GPS + str;
        }
        if (i == 3) {
            return "p";
        }
        return str2;
    }

    private static Map<String, Long> a(List<SyncMsgTable> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Map<String, Long> hashMap = new HashMap(list.size());
        for (SyncMsgTable syncMsgTable : list) {
            hashMap.put(a(syncMsgTable.type, syncMsgTable.id), Long.valueOf(syncMsgTable.localSeqid));
        }
        return hashMap;
    }

    private static List<SyncMsgTable> b(Map<String, Long> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Set<Entry> entrySet = map.entrySet();
        List<SyncMsgTable> arrayList = new ArrayList(entrySet.size());
        for (Entry entry : entrySet) {
            String str = (String) entry.getKey();
            long longValue = entry.getValue() != null ? ((Long) entry.getValue()).longValue() : 0;
            if (longValue != 0) {
                SyncMsgTable syncMsgTable = new SyncMsgTable();
                syncMsgTable.localSeqid = longValue;
                if (TextUtils.equals(User.UNDEFINED, str)) {
                    syncMsgTable.type = 1;
                    syncMsgTable.id = str;
                } else if (TextUtils.equals("p", str)) {
                    syncMsgTable.type = 3;
                    syncMsgTable.id = str;
                } else if (str.startsWith(IXAdRequestInfo.GPS)) {
                    syncMsgTable.type = 2;
                    syncMsgTable.id = str.substring(IXAdRequestInfo.GPS.length());
                }
                arrayList.add(syncMsgTable);
            }
        }
        return arrayList;
    }

    public void init() {
        if (!this.d && QsbkApp.currentUser != null) {
            Map a = a(SyncMsgStore.getSyncMsgStore(QsbkApp.currentUser.userId).getAll());
            if (a == null) {
                sendFirstSyncMsg();
            } else {
                this.e.putAll(a);
                this.f.putAll(a);
                sendLoginSyncMsg();
            }
            this.d = true;
            Util.imSyncTimer.postRunnable(this.c, a);
        }
    }

    private boolean a(int i, String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        CharSequence a = a(i, str);
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        this.e.put(a, Long.valueOf(j));
        return true;
    }

    public boolean update(String str, String str2, long j, long j2) {
        if (TextUtils.equals(str, Laisee.TYPE_P2P)) {
            return update(1, str2, j, j2);
        }
        if (TextUtils.equals(str, Laisee.TYPE_TRIBE)) {
            return update(2, str2, j, j2);
        }
        if (TextUtils.equals(str, "push")) {
            return update(3, str2, j, j2);
        }
        return true;
    }

    private long a(String str) {
        Long l = (Long) this.f.get(str);
        return l != null ? l.longValue() : 0;
    }

    public boolean maintenance(String str, String str2, long j, long j2) {
        if (TextUtils.equals(str, Laisee.TYPE_P2P)) {
            return maintenance(1, str2, j, j2);
        }
        if (TextUtils.equals(str, Laisee.TYPE_TRIBE)) {
            return maintenance(2, str2, j, j2);
        }
        if (TextUtils.equals(str, "push")) {
            return maintenance(3, str2, j, j2);
        }
        return true;
    }

    public boolean maintenance(int i, String str, long j, long j2) {
        String a = a(i, str);
        long a2 = a(a);
        Logger.getInstance().debug("SyncManager", "maintenance", String.format("type:%s, id:%s, serPre:%s, serThis:%s", new Object[]{Integer.valueOf(i), str, Long.valueOf(j), Long.valueOf(j2)}));
        if (a2 != j) {
            return false;
        }
        b(a);
        this.f.put(a, Long.valueOf(j2));
        this.g.put(a, Long.valueOf(SystemClock.elapsedRealtime()));
        return true;
    }

    public boolean syncControl(String str, String[] strArr) {
        int i = 0;
        Logger.getInstance().debug("SyncManager", "syncControl", String.format("controlType %s, id:%s", new Object[]{str, strArr}));
        if (!"stop_sync_tribe".equals(str) || strArr == null || strArr.length <= 0) {
            return false;
        }
        String str2 = QsbkApp.currentUser.userId;
        SyncMsgStore syncMsgStore = SyncMsgStore.getSyncMsgStore(str2);
        int length = strArr.length;
        while (i < length) {
            String str3 = strArr[i];
            String a = a(2, str3);
            b(a);
            this.f.remove(a);
            SyncMsgTable syncMsgTable = new SyncMsgTable();
            syncMsgTable.type = 2;
            syncMsgTable.id = str3;
            syncMsgStore.delete(syncMsgTable);
            i++;
        }
        GroupChatMsgStore.getInstance(str2).deleteMessagesWithUid(strArr);
        ContactListItemStore.getContactStore(str2).delete(strArr, 3);
        return true;
    }

    public boolean update(int i, String str, long j, long j2) {
        String a = a(i, str);
        Long c = c(a);
        long a2 = a(a);
        if (a2 != j && c != null) {
            Logger.getInstance().debug("SyncManager", PrefrenceKeys.UPDATE, String.format("正在会话中type:%s, id:%s, serverPreSeqid:%s, serverThisSeqid:%s, sendSeq:%s", new Object[]{Integer.valueOf(i), str, Long.valueOf(j), Long.valueOf(j2), c}));
            return false;
        } else if (a2 != j) {
            a(i, str, a2);
            e(a);
            Logger.getInstance().debug("SyncManager", PrefrenceKeys.UPDATE, String.format("开始同步type:%s, id:%s, serverPreSeqid:%s, serverThisSeqid:%s, sendSeq:%s", new Object[]{Integer.valueOf(i), str, Long.valueOf(j), Long.valueOf(j2), c}));
            return false;
        } else {
            Logger.getInstance().debug("SyncManager", PrefrenceKeys.UPDATE, String.format("来的消息是正确的type:%s, id:%s, serverPreSeqid:%s, serverThisSeqid:%s, sendSeq:%s", new Object[]{Integer.valueOf(i), str, Long.valueOf(j), Long.valueOf(j2), c}));
            b(a);
            this.f.put(a, Long.valueOf(j2));
            this.g.put(a, Long.valueOf(SystemClock.elapsedRealtime()));
            return true;
        }
    }

    public void remove(int i, String str) {
        b(a(i, str));
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e.remove(str);
        }
    }

    public Long get(int i, String str) {
        return c(a(i, str));
    }

    private Long c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (Long) this.e.get(str);
    }

    public void onConnectLost() {
        onDestroy();
    }

    public void onDestroy() {
        Util.imSyncTimer.cancelRunnable(this.c);
        save();
        a(this.e);
        a(this.f);
        a(this.g);
        this.d = false;
    }

    public void save() {
        if (QsbkApp.currentUser != null) {
            SyncMsgStore syncMsgStore = SyncMsgStore.getSyncMsgStore(QsbkApp.currentUser.userId);
            if (syncMsgStore != null) {
                this.f.putAll(this.e);
                List<SyncMsgTable> b = b(this.f);
                if (b != null && b.size() > 0) {
                    for (SyncMsgTable update : b) {
                        syncMsgStore.update(update);
                    }
                }
            }
        }
    }

    private void b(int i, String str) {
        if (QsbkApp.currentUser != null) {
            int i2;
            Set entrySet;
            if (i == 0) {
                entrySet = this.f.entrySet();
            } else {
                entrySet = this.e.entrySet();
            }
            SyncMsg syncMsg = new SyncMsg();
            syncMsg.sync_type = i;
            if (!TextUtils.isEmpty(str)) {
                a(syncMsg, str, ((Long) this.e.get(str)).longValue());
                i2 = 1;
            } else if (r0.isEmpty() && i == 1) {
                i2 = 1;
            } else {
                i2 = 0;
                for (Entry entry : r0) {
                    int i3;
                    String str2 = (String) entry.getKey();
                    long longValue = ((Long) entry.getValue()).longValue();
                    if (i != 0) {
                        a(syncMsg, str2, longValue);
                        i3 = 1;
                    } else if (d(str2)) {
                        Logger.getInstance().debug("SyncManager", "sendSyncMsgInternal", "valid for ping " + str2 + Constants.ACCEPT_TIME_SEPARATOR_SP + longValue);
                        a(syncMsg, str2, longValue);
                        i3 = 1;
                    } else {
                        i3 = i2;
                    }
                    i2 = i3;
                }
            }
            if (i2 == 0) {
                Logger.getInstance().debug("SyncManager", "NoValid sendSyncMsgInternal", String.format("sync_type:%s, keyOnly:%s", new Object[]{Integer.valueOf(i), str}));
                return;
            }
            UserChatManager userChatManager = UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token);
            Logger.getInstance().debug("SyncManager", "sendSyncMsgInternal", String.format("sendSyncMsgInternal specific key: %s, sync_type: %s, syncMsg: %s", new Object[]{str, Integer.valueOf(i), syncMsg}));
            if (userChatManager.isConnected()) {
                userChatManager.sendSyncMsg(syncMsg);
            } else {
                userChatManager.connectLater();
            }
        }
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(str) || this.e.get(str) != null) {
            return false;
        }
        boolean z;
        Long l = (Long) this.g.get(str);
        if (SystemClock.elapsedRealtime() - (l == null ? 0 : l.longValue()) > b) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void a(SyncMsg syncMsg, String str, long j) {
        if (TextUtils.equals(str, "p")) {
            syncMsg.push = j;
        } else if (TextUtils.equals(str, User.UNDEFINED)) {
            syncMsg.p2p = j;
        } else if (str.startsWith(IXAdRequestInfo.GPS)) {
            syncMsg.addTribeSeqId(str.replace(IXAdRequestInfo.GPS, ""), j);
        } else {
            Logger.getInstance().debug("SyncManager", "updateSyncMsg", String.format("unknow key %s and value %s ", new Object[]{str, Long.valueOf(j)}));
        }
    }

    private void e(String str) {
        b(1, str);
    }

    public void sendLoginSyncMsg() {
        b(1, null);
        this.e.clear();
    }

    public void sendFirstSyncMsg() {
        b(1, null);
    }

    private void b() {
        b(0, null);
    }
}
