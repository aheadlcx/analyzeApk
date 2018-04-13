package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.text.TextUtils;
import java.util.List;
import qsbk.app.im.SyncMsgTable;
import qsbk.app.utils.AppContext;

public class SyncMsgStore implements IStore {
    private static SyncMsgStore c;
    protected final DatabaseHelper a;
    protected DatabaseHelper$LifeCycleListener b;
    private final DatabaseHelper$RowMapping<List<SyncMsgTable>> d = new bc(this);
    private String e;

    private SyncMsgStore(String str) {
        this.e = str;
        this.a = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.b = new bd(this);
        this.a.addLifeCycleListener(this.b);
    }

    public static synchronized SyncMsgStore getSyncMsgStore(String str) {
        SyncMsgStore syncMsgStore;
        synchronized (SyncMsgStore.class) {
            if (c == null) {
                c = new SyncMsgStore(str);
            } else if (!TextUtils.equals(c.e, str)) {
                c.a();
                c = new SyncMsgStore(str);
            }
            syncMsgStore = c;
        }
        return syncMsgStore;
    }

    public static ContentValues syncMsg2ContentValue(SyncMsgTable syncMsgTable) {
        if (syncMsgTable == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", syncMsgTable.id);
        contentValues.put("type", Integer.valueOf(syncMsgTable.type));
        contentValues.put(DatabaseHelper$SyncMsgRow._CUR, Long.valueOf(syncMsgTable.serverSeqid));
        contentValues.put(DatabaseHelper$SyncMsgRow._PRE, Long.valueOf(syncMsgTable.localSeqid));
        return contentValues;
    }

    public List<SyncMsgTable> getAll() {
        return (List) this.a.query(false, DatabaseHelper.TABLE_SYNCMSG, null, null, null, null, null, null, null, this.d);
    }

    public void update(SyncMsgTable syncMsgTable) {
        if (syncMsgTable != null) {
            ContentValues syncMsg2ContentValue = syncMsg2ContentValue(syncMsgTable);
            if (this.a.update(DatabaseHelper.TABLE_SYNCMSG, syncMsg2ContentValue, "id = ? and type =? ", new String[]{syncMsgTable.id, syncMsgTable.type + ""}) == 0) {
                this.a.insert(DatabaseHelper.TABLE_SYNCMSG, null, syncMsg2ContentValue);
            }
        }
    }

    public void delete(SyncMsgTable syncMsgTable) {
        if (syncMsgTable != null) {
            this.a.delete(DatabaseHelper.TABLE_SYNCMSG, "id =? and type =? ", new String[]{syncMsgTable.id, syncMsgTable.type + ""});
        }
    }

    protected void a() {
        c = null;
        this.b = null;
        this.e = null;
    }
}
