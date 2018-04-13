package qsbk.app.im.datastore;

import android.content.ContentValues;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.AppContext;

public class GroupStore implements IStore {
    public static final int PER_PAGE = 20;
    protected static HashMap<String, GroupStore> a = new HashMap();
    protected final DatabaseHelper b;
    protected String c;
    protected DatabaseHelper$LifeCycleListener d;
    private final DatabaseHelper$RowMapping<List<GroupInfo>> e = new ax(this);

    private GroupStore(String str) {
        this.c = str;
        this.b = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.d = new ay(this);
        this.b.addLifeCycleListener(this.d);
    }

    public static synchronized GroupStore getInstance(String str) {
        GroupStore groupStore;
        synchronized (GroupStore.class) {
            groupStore = (GroupStore) a.get(str);
            if (groupStore == null) {
                groupStore = new GroupStore(str);
                a.put(str, groupStore);
            }
        }
        return groupStore;
    }

    public static ContentValues groupInfo2ContentValues(GroupInfo groupInfo) {
        if (groupInfo == null) {
            throw new RuntimeException("Group cannot be null");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(groupInfo.dbid));
        contentValues.put("gid", Integer.valueOf(groupInfo.id));
        contentValues.put("field1", groupInfo.icon);
        contentValues.put("field2", groupInfo.name);
        contentValues.put("field3", Integer.valueOf(groupInfo.isOwner ? 1 : 0));
        contentValues.put("field4", groupInfo.description);
        contentValues.put("field5", Integer.valueOf(groupInfo.level));
        contentValues.put("field6", Integer.valueOf(groupInfo.status));
        contentValues.put("field7", Integer.valueOf(groupInfo.statusUpdatedAt));
        contentValues.put("field8", Integer.valueOf(groupInfo.limitMember));
        contentValues.put("field9", Integer.valueOf(groupInfo.memberNum));
        contentValues.put("field10", Integer.valueOf(groupInfo.joinStatus));
        contentValues.put("field11", Integer.valueOf(1));
        return contentValues;
    }

    protected void a() {
        a.clear();
        this.d = null;
        this.c = null;
    }

    public List<GroupInfo> getJoinedGroups() {
        return (List) this.b.query(false, DatabaseHelper.TABLE_GROUPS, null, "field11=?", new String[]{String.valueOf(1)}, null, null, null, null, this.e);
    }

    public GroupInfo get(String str) {
        List list = (List) this.b.query(false, DatabaseHelper.TABLE_GROUPS, null, "gid =? AND field11 =? ", new String[]{str, String.valueOf(1)}, null, null, null, null, this.e);
        return (list == null || list.isEmpty()) ? null : (GroupInfo) list.get(0);
    }

    public int update(GroupInfo groupInfo) {
        return this.b.update(DatabaseHelper.TABLE_GROUPS, groupInfo2ContentValues(groupInfo), "id=" + groupInfo.id, null);
    }

    public int deleteJoinedGroup() {
        return this.b.delete(DatabaseHelper.TABLE_GROUPS, "field11=?", new String[]{String.valueOf(1)});
    }

    public void insert(List<GroupInfo> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            List arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                GroupInfo groupInfo = (GroupInfo) list.get(i);
                if (groupInfo != null) {
                    ContentValues groupInfo2ContentValues = groupInfo2ContentValues(groupInfo);
                    groupInfo2ContentValues.remove("id");
                    arrayList.add(groupInfo2ContentValues);
                }
            }
            this.b.insert(DatabaseHelper.TABLE_GROUPS, null, arrayList);
        }
    }
}
