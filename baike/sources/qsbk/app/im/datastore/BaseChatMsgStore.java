package qsbk.app.im.datastore;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.fragments.QiushiNotificationListFragment;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.MessageCountManager;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.utils.image.issue.TaskExecutor;

public abstract class BaseChatMsgStore implements IStore {
    public static final int PER_PAGE = 20;
    protected final DatabaseHelper a;
    protected String b;
    protected DatabaseHelper$LifeCycleListener c = new a(this);

    public abstract int deleteAllMessages();

    public abstract int deleteMessagesWith(String str);

    public abstract int deleteMessagesWith(long... jArr);

    public abstract int deleteMessagesWith(String... strArr);

    public abstract int deleteMessagesWithUid(String... strArr);

    public abstract List<ChatMsg> get(int i, int i2, String str);

    public abstract List<ChatMsg> get(int i, int i2, String str, String str2);

    public abstract List<ChatMsg> get(long... jArr);

    public abstract List<ChatMsg> getSendFailMsg();

    public abstract int getTotalUnReadCount();

    public abstract int getUnReadCountWith(String str);

    public abstract int[] getUnreadCountWithIds(String[] strArr);

    public abstract List<String> getUnreadMsgIds(String str);

    public abstract int insert(List<ChatMsg> list);

    public abstract long insert(ChatMsg chatMsg);

    public abstract boolean isChatMsgExist(String str);

    public abstract void markAllMessagesToRead();

    public abstract void markMessagesToReadWith(int i);

    public abstract int setMessageReaded(List<String> list);

    public abstract int setMessageReadedBatch(List<Long> list);

    public abstract int updateMessageData(ChatMsg chatMsg);

    public abstract int updateMessageState(long j, int i);

    public abstract int updateMessageState(String str, int i);

    public abstract int updateMessageStateBatch(List<String> list, int i);

    public BaseChatMsgStore(String str) {
        this.b = str;
        this.a = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.a.addLifeCycleListener(this.c);
    }

    public static void sortMsgsByTime(List<ChatMsg> list) {
        if (!ArrayUtils.isEmpty(list)) {
            ArrayUtils.sort(list, new k());
        }
    }

    protected void a() {
        this.c = null;
        this.b = null;
    }

    public void getAsync(int i, int i2, String str, String str2, Callback<List<ChatMsg>> callback) {
        TaskExecutor.getInstance().addTask(new l(this, i, i2, str, str2, callback));
    }

    protected int a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.rawQuery(str, strArr);
    }

    protected int[] a(String[] strArr, String[][] strArr2) {
        if (strArr == null) {
            return null;
        }
        return this.a.rawQuery(strArr, strArr2);
    }

    protected void a(String str, Object[] objArr) {
        if (!TextUtils.isEmpty(str)) {
            this.a.execSql(str, objArr);
        }
    }

    public void getTotalUnReadCountAsync(Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new m(this, callback));
    }

    public void getUnReadCountWithIdAsync(String str, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new n(this, str, callback));
    }

    public void deleteMessagesWithMsgIdAsync(Callback<Integer> callback, String... strArr) {
        TaskExecutor.getInstance().addTask(new o(this, strArr, callback));
    }

    public void deleteMessagesWithDbIdsAsync(Callback<Integer> callback, long... jArr) {
        TaskExecutor.getInstance().addTask(new p(this, jArr, callback));
    }

    public void deleteMessagesWithIdAsync(String str, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new q(this, str, callback));
    }

    public void deleteAllMessagesAsync(Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new r(this, callback));
    }

    public void updateMessgeStateAsync(String str, int i, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new b(this, str, i, callback));
    }

    public void updateMessageStateAsync(long j, int i, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new c(this, j, i, callback));
    }

    public void markMessagesToReadWithIdAsync(int i, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new d(this, i, callback));
    }

    public void markAllMessagesToReadAsync(Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new e(this, callback));
    }

    public void getUnreadCountWithIdsAsync(Callback<int[]> callback, String[] strArr) {
        TaskExecutor.getInstance().addTask(new f(this, strArr, callback));
    }

    public void getAsync(int i, int i2, String str, Callback<List<ChatMsg>> callback) {
        TaskExecutor.getInstance().addTask(new g(this, i, i2, str, callback));
    }

    public void insertAsync(ChatMsg chatMsg, Callback<Long> callback) {
        TaskExecutor.getInstance().addTask(new h(this, chatMsg, callback));
    }

    public void insertAsync(List<ChatMsg> list, Callback<Void> callback) {
        TaskExecutor.getInstance().addTask(new i(this, list, callback));
    }

    public void getAsync(Callback<List<ChatMsg>> callback, long... jArr) {
        TaskExecutor.getInstance().addTask(new j(this, jArr, callback));
    }

    public synchronized void addUserTotalMsgUnread(int i, boolean z) {
        LogUtil.d("add unread:" + i);
        Logger.getInstance().debug(ChatMsgStore.class.getSimpleName(), "addUserTotalMsgUnread", String.format("增加未读数: %s, %s", new Object[]{Integer.valueOf(i), Thread.currentThread().getStackTrace()[3]}));
        MessageCountManager.getMessageCountManager(this.b).addUnreadCount(i, z);
    }

    public List<ChatMsg> get(String str) {
        return get(0, 20, str, String.valueOf(Long.MAX_VALUE));
    }

    public List<ChatMsg> get(String str, long j) {
        return get(0, 20, str, String.valueOf(j));
    }

    public long saveMessage(ChatMsg chatMsg) {
        long insert = insert(chatMsg);
        if (insert > 0) {
            chatMsg.dbid = insert;
            if (!TextUtils.isEmpty(chatMsg.data) && chatMsg.data.startsWith("{") && chatMsg.data.endsWith(h.d)) {
                try {
                    JSONObject optJSONObject = new JSONObject(chatMsg.data).optJSONObject("jump_data");
                    if (optJSONObject != null) {
                        CharSequence optString = optJSONObject.optString("m_type");
                        if (!TextUtils.isEmpty(optString)) {
                            if ("promote".equals(optString)) {
                                SharePreferenceUtils.setSharePreferencesValue(QiushiNotificationListFragment.NEED_SHOW_FLOWER, true);
                                SharePreferenceUtils.setSharePreferencesValue(QiushiNotificationListFragment.SHOW_FLOWER_DESC, optJSONObject.toString());
                            }
                            if ("prefer".equals(optString)) {
                                SharePreferenceUtils.setSharePreferencesValue(QiushiNotificationListFragment.NEED_SHOW_PREFER, true);
                                SharePreferenceUtils.setSharePreferencesValue(QiushiNotificationListFragment.SHOW_PREFER_DESC, optJSONObject.toString());
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            LogUtil.d("msg.dbid:" + chatMsg.dbid + "消息存储");
        } else {
            LogUtil.e("insert msg error:" + chatMsg.data);
        }
        return insert;
    }
}
