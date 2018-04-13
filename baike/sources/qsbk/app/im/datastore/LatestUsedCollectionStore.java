package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.utils.AppContext;

public class LatestUsedCollectionStore implements IStore {
    private static final HashMap<String, LatestUsedCollectionStore> a = new HashMap();
    private static final DatabaseHelper$RowMapping<List<LatestUsedCollectionData>> b = new az();
    private final DatabaseHelper c;
    private String d = null;
    private DatabaseHelper$LifeCycleListener e;

    private LatestUsedCollectionStore(String str) {
        this.d = str;
        this.c = DatabaseHelper.getInstance(AppContext.getContext(), str);
        this.e = new ba(this);
        this.c.addLifeCycleListener(this.e);
    }

    public static synchronized LatestUsedCollectionStore getCollectionStore(String str) {
        LatestUsedCollectionStore latestUsedCollectionStore;
        synchronized (LatestUsedCollectionStore.class) {
            latestUsedCollectionStore = (LatestUsedCollectionStore) a.get(str);
            if (latestUsedCollectionStore == null) {
                latestUsedCollectionStore = new LatestUsedCollectionStore(str);
                a.put(str, latestUsedCollectionStore);
            }
        }
        return latestUsedCollectionStore;
    }

    public static final ContentValues draft2ContentValues(LatestUsedCollectionData latestUsedCollectionData) {
        ContentValues contentValues = new ContentValues();
        if (latestUsedCollectionData != null) {
            if (latestUsedCollectionData.id > 0) {
                contentValues.put("id", Integer.valueOf(latestUsedCollectionData.id));
            }
            if (latestUsedCollectionData.usedTime > 0) {
                contentValues.put(DatabaseHelper$LatestUsedCollectionRow._USEDTIME, Long.valueOf(latestUsedCollectionData.usedTime));
            } else {
                contentValues.put(DatabaseHelper$LatestUsedCollectionRow._USEDTIME, Integer.valueOf(0));
            }
            if (!TextUtils.isEmpty(latestUsedCollectionData.data)) {
                contentValues.put("data", latestUsedCollectionData.data);
            }
            if (latestUsedCollectionData.type > 0) {
                contentValues.put("type", Integer.valueOf(latestUsedCollectionData.type));
            }
        }
        return contentValues;
    }

    private void a() {
        a.clear();
        this.e = null;
        this.d = null;
    }

    public List<LatestUsedCollectionData> getAll() {
        return (List) this.c.query(true, DatabaseHelper.TABLE_LATEST_USED_COLLECTION, null, null, null, null, null, "usedtime desc", null, b);
    }

    public List<LatestUsedCollectionData> get(int i, int i2) {
        if (i < 0 || i2 < 0) {
            return null;
        }
        int i3 = (i + 1) * i2;
        return (List) this.c.query(false, DatabaseHelper.TABLE_LATEST_USED_COLLECTION, null, null, null, null, null, "usedtime DESC ", String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)}), b);
    }

    public LatestUsedCollectionData get(int i) {
        if (i < 0) {
            return null;
        }
        List list = (List) this.c.query(false, DatabaseHelper.TABLE_LATEST_USED_COLLECTION, null, "id =? ", new String[]{i + ""}, null, null, null, null, b);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (LatestUsedCollectionData) list.get(0);
    }

    public long insert(LatestUsedCollectionData latestUsedCollectionData) {
        if (latestUsedCollectionData == null) {
            return 0;
        }
        return this.c.insert(DatabaseHelper.TABLE_LATEST_USED_COLLECTION, null, draft2ContentValues(latestUsedCollectionData));
    }

    public long insert(int i, String str, long j) {
        LatestUsedCollectionData latestUsedCollectionData = new LatestUsedCollectionData();
        latestUsedCollectionData.data = str;
        latestUsedCollectionData.type = i;
        latestUsedCollectionData.data = str;
        return insert(latestUsedCollectionData);
    }

    public int update(int i, int i2, String str, long j) {
        LatestUsedCollectionData latestUsedCollectionData = new LatestUsedCollectionData();
        latestUsedCollectionData.data = str;
        latestUsedCollectionData.id = i;
        latestUsedCollectionData.type = i2;
        latestUsedCollectionData.usedTime = j;
        return update(latestUsedCollectionData);
    }

    public int update(int i, long j) {
        LatestUsedCollectionData latestUsedCollectionData = new LatestUsedCollectionData();
        latestUsedCollectionData.id = i;
        latestUsedCollectionData.usedTime = j;
        return update(latestUsedCollectionData, 1);
    }

    public int update(LatestUsedCollectionData latestUsedCollectionData) {
        if (latestUsedCollectionData.id < 0 || TextUtils.isEmpty(latestUsedCollectionData.data)) {
            return 0;
        }
        return this.c.update(DatabaseHelper.TABLE_LATEST_USED_COLLECTION, draft2ContentValues(latestUsedCollectionData), "id =? ", new String[]{latestUsedCollectionData.id + ""});
    }

    public int update(LatestUsedCollectionData latestUsedCollectionData, int i) {
        if (latestUsedCollectionData.id < 0 || latestUsedCollectionData.usedTime < 0) {
            return 0;
        }
        return this.c.update(DatabaseHelper.TABLE_LATEST_USED_COLLECTION, draft2ContentValues(latestUsedCollectionData), "id =? ", new String[]{latestUsedCollectionData.id + ""});
    }

    public int findLocal(LatestUsedCollectionData latestUsedCollectionData) {
        if (latestUsedCollectionData.type == 3 && !TextUtils.isEmpty(latestUsedCollectionData.data)) {
            this.c.query(true, DatabaseHelper.TABLE_LATEST_USED_COLLECTION, null, "type =?", new String[]{"3"}, null, null, "usedtime DESC ", null, b);
        }
        return 0;
    }

    public int delete(int i) {
        if (i < 0) {
            return -1;
        }
        return this.c.delete(DatabaseHelper.TABLE_LATEST_USED_COLLECTION, "id =? ", new String[]{i + ""});
    }

    public int delete(ArrayList<Integer> arrayList) {
        if (arrayList.size() <= 0) {
            return -1;
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            strArr[i] = arrayList.get(i) + "";
            stringBuffer.append('?').append(',');
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return this.c.delete(DatabaseHelper.TABLE_LATEST_USED_COLLECTION, "id in (" + stringBuffer.toString() + " ) ", strArr);
    }

    public int deleteAll() {
        return this.c.deleteAll(DatabaseHelper.TABLE_LATEST_USED_COLLECTION);
    }
}
