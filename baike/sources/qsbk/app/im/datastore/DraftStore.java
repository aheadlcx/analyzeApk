package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.TaskExecutor;

public class DraftStore implements IStore {
    private static final HashMap<String, DraftStore> a = new HashMap();
    private static final DatabaseHelper$RowMapping<List<ChatMsg>> b = new ag();
    private final DatabaseHelper c;
    private String d = null;
    private DatabaseHelper$LifeCycleListener e;

    private DraftStore(String str) {
        this.d = str;
        this.c = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.e = new ai(this);
        this.c.addLifeCycleListener(this.e);
    }

    public static synchronized DraftStore getDraftStore(String str) {
        DraftStore draftStore;
        synchronized (DraftStore.class) {
            draftStore = (DraftStore) a.get(str);
            if (draftStore == null) {
                draftStore = new DraftStore(str);
                a.put(str, draftStore);
            }
        }
        return draftStore;
    }

    public static final ContentValues draft2ContentValues(ChatMsg chatMsg) {
        ContentValues contentValues = new ContentValues();
        if (chatMsg != null) {
            contentValues.put("data", chatMsg.data);
            contentValues.put("uid", chatMsg.uid);
            contentValues.put("t", Long.valueOf(chatMsg.time));
            String str = "type";
            if (TextUtils.isEmpty(chatMsg.gid)) {
                contentValues.put(str, Integer.valueOf(0));
            } else {
                contentValues.put(str, Integer.valueOf(0));
            }
        }
        return contentValues;
    }

    private void a() {
        a.clear();
        this.e = null;
        this.d = null;
    }

    public List<ChatMsg> getAll() {
        return (List) this.c.query(false, DatabaseHelper.TABLE_DRAFTS, null, null, null, null, null, "t desc", null, b);
    }

    public List<ChatMsg> get(int i, int i2) {
        if (i < 0 || i2 < 0) {
            return null;
        }
        int i3 = (i + 1) * i2;
        return (List) this.c.query(false, DatabaseHelper.TABLE_DRAFTS, null, null, null, null, null, "t DESC ", String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)}), b);
    }

    public void getAsync(int i, int i2, Callback<List<ChatMsg>> callback) {
        TaskExecutor.getInstance().addTask(new aj(this, i, i2, callback));
    }

    public ChatMsg get(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List list = (List) this.c.query(false, DatabaseHelper.TABLE_DRAFTS, null, "uid =? ", new String[]{str}, null, null, null, null, b);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (ChatMsg) list.get(0);
    }

    public ChatMsg getUserDraft(String str) {
        return get(str, 0);
    }

    public ChatMsg getGroupDraft(String str) {
        return get(str, 3);
    }

    public void getAsync(String str, int i, Callback<ChatMsg> callback) {
        TaskExecutor.getInstance().addTask(new ak(this, str, i, callback));
    }

    public List<ChatMsg> getUserDraft(String... strArr) {
        return get(0, strArr);
    }

    public List<ChatMsg> getGroupDraft(String... strArr) {
        return get(3, strArr);
    }

    public List<ChatMsg> get(int i, String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        int length = strArr.length;
        if (length <= 500) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < length; i2++) {
                stringBuffer.append('?').append(',');
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            Object obj = new String[(strArr.length + 1)];
            System.arraycopy(strArr, 0, obj, 0, strArr.length);
            obj[strArr.length] = i + "";
            return (List) this.c.query(false, DatabaseHelper.TABLE_DRAFTS, null, "uid in(" + stringBuffer.toString() + ") and " + "type" + " = ? ", obj, null, null, null, null, b);
        }
        List<ChatMsg> arrayList = new ArrayList(get(i, ArrayUtils.copyOfRange(strArr, 0, 500)));
        arrayList.addAll(get(i, ArrayUtils.copyOfRange(strArr, 500, strArr.length)));
        return arrayList;
    }

    public void get(Callback<List<ChatMsg>> callback, int i, String... strArr) {
        TaskExecutor.getInstance().addTask(new al(this, i, strArr, callback));
    }

    public long insert(ChatMsg chatMsg) {
        if (chatMsg == null || TextUtils.isEmpty(chatMsg.uid)) {
            return 0;
        }
        chatMsg.status = 6;
        return this.c.insert(DatabaseHelper.TABLE_DRAFTS, null, draft2ContentValues(chatMsg));
    }

    public void insert(ChatMsg chatMsg, Callback<Long> callback) {
        TaskExecutor.getInstance().addTask(new am(this, chatMsg, callback));
    }

    public int update(String str, String str2, long j) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.data = str2;
        chatMsg.uid = str;
        chatMsg.time = j;
        return update(chatMsg);
    }

    public long insert(String str, String str2, long j) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.data = str2;
        chatMsg.uid = str;
        chatMsg.time = j;
        return insert(chatMsg);
    }

    public int update(ChatMsg chatMsg) {
        if (TextUtils.isEmpty(chatMsg.uid) || TextUtils.isEmpty(chatMsg.data)) {
            return 0;
        }
        chatMsg.status = 6;
        return this.c.update(DatabaseHelper.TABLE_DRAFTS, draft2ContentValues(chatMsg), "uid =? ", new String[]{chatMsg.uid});
    }

    public void updateAsync(String str, String str2, long j, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new an(this, str, str2, j, callback));
    }

    public void updateAsync(ChatMsg chatMsg, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new ao(this, chatMsg, callback));
    }

    public int delete(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.c.delete(DatabaseHelper.TABLE_DRAFTS, "uid =? ", new String[]{str});
    }

    public void deleteAsync(String str, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new ap(this, str, callback));
    }

    public int deleteAll() {
        return this.c.deleteAll(DatabaseHelper.TABLE_DRAFTS);
    }

    public void deleteAllAsync(Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new ah(this, callback));
    }
}
