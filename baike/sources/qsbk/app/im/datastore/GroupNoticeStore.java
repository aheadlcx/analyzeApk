package qsbk.app.im.datastore;

import android.content.ContentValues;
import java.util.HashMap;
import java.util.List;
import qsbk.app.im.MessageCountManager;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.AppContext;

public class GroupNoticeStore implements IStore {
    protected static HashMap<String, GroupNoticeStore> a = new HashMap();
    protected final DatabaseHelper b;
    protected String c;
    protected DatabaseHelper$LifeCycleListener d;
    private final DatabaseHelper$RowMapping<List<GroupNotice>> e = new av(this);

    private GroupNoticeStore(String str) {
        this.c = str;
        this.b = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.d = new aw(this);
        this.b.addLifeCycleListener(this.d);
    }

    public static synchronized GroupNoticeStore getInstance(String str) {
        GroupNoticeStore groupNoticeStore;
        synchronized (GroupNoticeStore.class) {
            groupNoticeStore = (GroupNoticeStore) a.get(str);
            if (groupNoticeStore == null) {
                groupNoticeStore = new GroupNoticeStore(str);
                a.put(str, groupNoticeStore);
            }
        }
        return groupNoticeStore;
    }

    public static ContentValues chatMsg2ContentValues(GroupNotice groupNotice) {
        if (groupNotice == null) {
            throw new RuntimeException("Msg cannot be null");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(groupNotice.type));
        contentValues.put("json", groupNotice.json.toString());
        contentValues.put("state", Integer.valueOf(groupNotice.state));
        contentValues.put("t", Long.valueOf(groupNotice.time));
        return contentValues;
    }

    protected void a() {
        a.clear();
        this.d = null;
        this.c = null;
    }

    public int deleteMessage(GroupNotice groupNotice) {
        return this.b.delete(DatabaseHelper.TABLE_GROUP_NOTICE, "id = " + groupNotice.id, null);
    }

    public int deleteAllMessages() {
        return this.b.deleteAll(DatabaseHelper.TABLE_GROUP_NOTICE);
    }

    protected int a(String str, String[] strArr) {
        return this.b.rawQuery(str, strArr);
    }

    public int getTotalUnReadCount() {
        return a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_GROUP_NOTICE).append(" where ").append("state").append(" = ?; ").toString(), new String[]{"4"});
    }

    protected void a(String str, Object[] objArr) {
        this.b.execSql(str, objArr);
    }

    public void markAllMessagesToRead(int i) {
        MessageCountManager.getMessageCountManager(this.c).addUnreadCount(-i, true);
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_NOTICE).append(" set ").append("state").append(" = ?;").toString(), new String[]{"5"});
    }

    public void markAllMessagesToRead() {
        MessageCountManager.getMessageCountManager(this.c).addUnreadCount(-2147483647, true);
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_NOTICE).append(" set ").append("state").append(" = ?;").toString(), new String[]{"5"});
    }

    public long insert(GroupNotice groupNotice) {
        if (groupNotice == null) {
            return 0;
        }
        long insert = this.b.insert(DatabaseHelper.TABLE_GROUP_NOTICE, null, chatMsg2ContentValues(groupNotice));
        if (insert <= -1) {
            return insert;
        }
        MessageCountManager.getMessageCountManager(this.c).addUnreadCount(1, true);
        return insert;
    }

    public long update(GroupNotice groupNotice) {
        if (groupNotice == null) {
            return 0;
        }
        int i = groupNotice.id;
        return (long) this.b.update(DatabaseHelper.TABLE_GROUP_NOTICE, chatMsg2ContentValues(groupNotice), "id = " + i, null);
    }

    public List<GroupNotice> getAllNotice() {
        return (List) this.b.query(false, DatabaseHelper.TABLE_GROUP_NOTICE, null, "type != 9", null, null, null, "id desc", null, this.e);
    }

    public List<GroupNotice> getAllRecommend() {
        return (List) this.b.query(false, DatabaseHelper.TABLE_GROUP_NOTICE, null, "type = 9", null, null, null, "id desc", null, this.e);
    }
}
