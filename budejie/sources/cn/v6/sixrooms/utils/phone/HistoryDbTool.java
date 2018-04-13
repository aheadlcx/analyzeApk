package cn.v6.sixrooms.utils.phone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.v6.sixrooms.pojo.HistroyWatch;
import java.util.ArrayList;
import java.util.List;

public class HistoryDbTool {
    private static SQLiteDatabase a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void delete(android.content.Context r4, java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0029 in list [B:5:0x0024]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        a(r4);
        r0 = a;	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r1 = "HistoryWatchData";	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r3 = "rid in(";	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r2.<init>(r3);	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r2 = r2.append(r5);	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r3 = ")";	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r2 = r2.append(r3);	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r2 = r2.toString();	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r3 = 0;	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r0.delete(r1, r2, r3);	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r0 = a;
        if (r0 == 0) goto L_0x0029;
    L_0x0024:
        r0 = a;
        r0.close();
    L_0x0029:
        return;
    L_0x002a:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ SQLiteException -> 0x002a, all -> 0x0038 }
        r0 = a;
        if (r0 == 0) goto L_0x0029;
    L_0x0032:
        r0 = a;
        r0.close();
        goto L_0x0029;
    L_0x0038:
        r0 = move-exception;
        r1 = a;
        if (r1 == 0) goto L_0x0042;
    L_0x003d:
        r1 = a;
        r1.close();
    L_0x0042:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.utils.phone.HistoryDbTool.delete(android.content.Context, java.lang.String):void");
    }

    private static void a(Context context) {
        a = new HistoryOpenHelper(context).getWritableDatabase();
    }

    public static void add(Context context, HistroyWatch histroyWatch) {
        a(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("rid", histroyWatch.getRid());
        contentValues.put("pic", histroyWatch.getPic());
        contentValues.put(HistoryOpenHelper.COLUMN_USERNAME, histroyWatch.getUsername());
        contentValues.put(HistoryOpenHelper.COLUMN_LEVEL, histroyWatch.getLevel());
        contentValues.put(HistoryOpenHelper.COLUMN_DATE, Long.valueOf(histroyWatch.getDate()));
        contentValues.put(HistoryOpenHelper.COLUMN_UID, histroyWatch.getUid());
        a.insert(HistoryOpenHelper.TABLE_NAME, null, contentValues);
        a.close();
    }

    public static void deleteAll(Context context) {
        a(context);
        a.delete(HistoryOpenHelper.TABLE_NAME, null, null);
        a.close();
    }

    public static List<HistroyWatch> query(Context context) {
        List<HistroyWatch> list = null;
        a(context);
        Cursor query = a.query(HistoryOpenHelper.TABLE_NAME, null, null, null, null, null, null);
        if (query.getCount() == 0) {
            query.close();
            a.close();
        } else {
            List arrayList = new ArrayList();
            while (query.moveToNext()) {
                HistroyWatch histroyWatch = new HistroyWatch();
                histroyWatch.setRid(query.getString(query.getColumnIndex("rid")));
                histroyWatch.setPic(query.getString(query.getColumnIndex("pic")));
                histroyWatch.setUsername(query.getString(query.getColumnIndex(HistoryOpenHelper.COLUMN_USERNAME)));
                histroyWatch.setLevel(query.getString(query.getColumnIndex(HistoryOpenHelper.COLUMN_LEVEL)));
                histroyWatch.setDate(Long.parseLong(query.getString(query.getColumnIndex(HistoryOpenHelper.COLUMN_DATE))));
                histroyWatch.set_id(query.getLong(query.getColumnIndex(HistoryOpenHelper.COLUMN_ID)));
                histroyWatch.setUid(query.getString(query.getColumnIndex(HistoryOpenHelper.COLUMN_UID)));
                arrayList.add(histroyWatch);
            }
            query.close();
            a.close();
            list = new ArrayList();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                list.add(arrayList.get(size));
            }
        }
        return list;
    }
}
