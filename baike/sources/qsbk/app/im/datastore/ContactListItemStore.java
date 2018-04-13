package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.MessageCountManager;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.image.issue.TaskExecutor;

public class ContactListItemStore implements IStore {
    public static final int DEFAULT_PAGE_COUNT = 30;
    private static final HashMap<String, ContactListItemStore> a = new HashMap();
    private static final DatabaseHelper$RowMapping<List<ContactListItem>> b = new w();
    private final DatabaseHelper c;
    private String d;
    private HashMap<String, ContactListItem> e = new HashMap();
    private DatabaseHelper$LifeCycleListener f;

    private ContactListItemStore(String str) {
        this.d = str;
        this.c = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.f = new y(this);
        this.c.addLifeCycleListener(this.f);
    }

    public static synchronized ContactListItemStore getContactStore(String str) {
        ContactListItemStore contactListItemStore;
        synchronized (ContactListItemStore.class) {
            contactListItemStore = (ContactListItemStore) a.get(str);
            if (contactListItemStore == null) {
                contactListItemStore = new ContactListItemStore(str);
                a.put(str, contactListItemStore);
            }
        }
        return contactListItemStore;
    }

    public static ContentValues contactListItem2ContentValues(ContactListItem contactListItem) {
        if (contactListItem == null) {
            throw new RuntimeException("ContactListItem can not be null");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", contactListItem.id);
        contentValues.put("icon", contactListItem.icon);
        contentValues.put("data", contactListItem.mLastContent);
        contentValues.put("msgid", Long.valueOf(contactListItem.msgId));
        contentValues.put("name", contactListItem.name);
        contentValues.put("t", Long.valueOf(contactListItem.mLastUpdateTime));
        contentValues.put("type", Integer.valueOf(contactListItem.type));
        contentValues.put("at", Long.valueOf(contactListItem.atMsgId));
        contentValues.put(DatabaseHelper$ChatRow._AT_TYPE, Integer.valueOf(contactListItem.atType));
        return contentValues;
    }

    public void release() {
        a.clear();
        this.f = null;
        this.e.clear();
    }

    public List<ContactListItem> get(int i, int i2) {
        int i3 = (i + 1) * i2;
        return (List) this.c.query(false, DatabaseHelper.TABLE_CHAT, null, null, null, null, null, "t desc", String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)}), b);
    }

    public List<ContactListItem> getNum(int i) {
        String format = String.format("%s", new Object[]{Integer.valueOf(i)});
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("type").append(" = ").append(" 0 ").append(" OR ").append("type").append(" = ").append(" 3 ");
        return (List) this.c.query(false, DatabaseHelper.TABLE_CHAT, null, stringBuilder.toString(), null, null, null, "t desc", format, b);
    }

    public List<ContactListItem> getAll() {
        return (List) this.c.query(false, DatabaseHelper.TABLE_CHAT, null, null, null, null, null, "t desc", null, b);
    }

    public void getAllAsync(Callback<List<ContactListItem>> callback) {
        TaskExecutor.getInstance().addTask(new z(this, callback));
    }

    public List<ContactListItem> get(int i, long j, boolean z) {
        return (List) this.c.query(false, DatabaseHelper.TABLE_CHAT, null, "t " + (z ? " < " : " >= ") + " ? ", new String[]{j + ""}, null, null, "t desc", "0," + i, b);
    }

    public void getAsync(int i, long j, boolean z, Callback<List<ContactListItem>> callback) {
        TaskExecutor.getInstance().addTask(new aa(this, i, j, z, callback));
    }

    public void getAsync(int i, int i2, Callback<List<ContactListItem>> callback) {
        TaskExecutor.getInstance().addTask(new ab(this, i, i2, callback));
    }

    public int update(ContactListItem contactListItem) {
        if (contactListItem == null || TextUtils.isEmpty(contactListItem.id)) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", contactListItem.id);
        contentValues.put("icon", contactListItem.icon);
        contentValues.put("data", contactListItem.mLastContent);
        contentValues.put("msgid", Long.valueOf(contactListItem.msgId));
        contentValues.put("name", contactListItem.name);
        contentValues.put("t", Long.valueOf(contactListItem.mLastUpdateTime));
        contentValues.put("type", Integer.valueOf(contactListItem.type));
        contentValues.put("at", Long.valueOf(contactListItem.atMsgId));
        contentValues.put(DatabaseHelper$ChatRow._AT_TYPE, Integer.valueOf(contactListItem.atType));
        return this.c.update(DatabaseHelper.TABLE_CHAT, contentValues, "uid = ? and type = ? ", new String[]{contactListItem.id, contactListItem.type + ""});
    }

    public void updateAsync(ContactListItem contactListItem, Callback<Integer> callback) {
        if (contactListItem != null && !TextUtils.isEmpty(contactListItem.id)) {
            TaskExecutor.getInstance().addTask(new ac(this, contactListItem, callback));
        }
    }

    private int a(String str, String[] strArr) {
        if (!TextUtils.isEmpty(str)) {
            this.c.execSql(str, strArr);
        }
        return 0;
    }

    public int updateData(String str, String str2, long j, int i) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return a(new StringBuffer("update ").append(DatabaseHelper.TABLE_CHAT).append(" set ").append("data").append(" = ?, ").append("t").append(" = ? ").append(" where ").append("uid").append(" =? and ").append("type").append(" = ? ;").toString(), new String[]{str2, j + "", str, i + ""});
    }

    public void updateDataAsync(String str, String str2, long j, int i, Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new ad(this, str, str2, j, i, callback));
    }

    public ContactListItem getWithUser(String str) {
        return get(str, 0);
    }

    public ContactListItem getWithUser(String str, int i) {
        if (i == 0) {
            return getWithUser(str);
        }
        return get(str, i);
    }

    public ContactListItem getWithGroupNotice(String str) {
        return get(str, 6);
    }

    public ContactListItem getWithGroup(String str) {
        return get(str, 3);
    }

    public ContactListItem get(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List list = (List) this.c.query(false, DatabaseHelper.TABLE_CHAT, null, "uid =? and type =? ", new String[]{str, i + ""}, null, null, null, null, b);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (ContactListItem) list.get(0);
    }

    public void insert(List<ContactListItem> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            List arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                ContactListItem contactListItem = (ContactListItem) list.get(i);
                if (contactListItem != null) {
                    arrayList.add(contactListItem2ContentValues(contactListItem));
                }
            }
            this.c.replace(DatabaseHelper.TABLE_CHAT, null, arrayList);
        }
    }

    public long insert(ContactListItem contactListItem) {
        if (contactListItem == null || TextUtils.isEmpty(contactListItem.id)) {
            return 0;
        }
        return this.c.insert(DatabaseHelper.TABLE_CHAT, null, contactListItem2ContentValues(contactListItem));
    }

    public void insertOrReplace(ContactListItem contactListItem) {
        if (contactListItem != null && !TextUtils.isEmpty(contactListItem.id)) {
            List arrayList = new ArrayList(1);
            arrayList.add(contactListItem2ContentValues(contactListItem));
            this.c.replace(DatabaseHelper.TABLE_CHAT, null, arrayList);
        }
    }

    public void insertAsync(ContactListItem contactListItem, Callback<Long> callback) {
        if (contactListItem != null && !TextUtils.isEmpty(contactListItem.id)) {
            TaskExecutor.getInstance().addTask(new ae(this, contactListItem, callback));
        }
    }

    public int delete(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.c.delete(DatabaseHelper.TABLE_CHAT, "uid =? and type = ?", new String[]{str, i + ""});
    }

    public int delete(String[] strArr, int i) {
        int i2 = 0;
        if (strArr == null || strArr.length == 0) {
            return 0;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr2 = new String[(strArr.length + 1)];
        while (i2 < strArr.length) {
            stringBuffer.append('?').append(',');
            strArr2[i2] = String.valueOf(strArr[i2]);
            i2++;
        }
        strArr2[strArr.length] = i + "";
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return this.c.delete(DatabaseHelper.TABLE_CHAT, "uid in( " + stringBuffer.toString() + ") and " + "type" + " = ?", strArr2);
    }

    public int deleteAll() {
        return this.c.deleteAll(DatabaseHelper.TABLE_CHAT);
    }

    public void deleteAllAsync(Callback<Integer> callback) {
        TaskExecutor.getInstance().addTask(new af(this, callback));
    }

    public void deleteAsync(Callback<Integer> callback, String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            TaskExecutor.getInstance().addTask(new x(this, str, i, callback));
        }
    }

    public void updateUnarchiveCount(int i) {
        MessageCountManager.getMessageCountManager(this.d).setUnarchiveCount(i);
    }
}
