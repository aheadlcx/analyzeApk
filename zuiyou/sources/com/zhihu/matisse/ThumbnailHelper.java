package com.zhihu.matisse;

import android.support.v4.app.NotificationCompat;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.zhihu.matisse.thumbnail.greendao.ThumbnailEntity;
import java.util.ArrayList;
import java.util.List;

public class ThumbnailHelper {
    static final String CREATE_TABLE = "CREATE TABLE thumbnail (_id INTEGER PRIMARY KEY AUTOINCREMENT ,orig_id INTEGER NOT NULL ,orig_path TEXT,thumbnail_path TEXT,status INTEGER NOT NULL ,last_generate_time INTEGER NOT NULL );";
    public static final String TABLE_NAME = "thumbnail";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long save(com.zhihu.matisse.thumbnail.greendao.ThumbnailEntity r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0005 in list [B:12:0x0093]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = 0;
        if (r8 != 0) goto L_0x0006;
    L_0x0003:
        r0 = -1;
    L_0x0005:
        return r0;
    L_0x0006:
        r0 = com.zhihu.matisse.MediasDatabase.getDatabase();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "select _id from thumbnail  where _id=";
        r1 = r1.append(r2);
        r2 = r8.getId();
        r1 = r1.append(r2);
        r2 = ";";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = r0.rawQuery(r1, r3, r3);
        if (r2 == 0) goto L_0x00b6;
    L_0x002f:
        r1 = new android.content.ContentValues;
        r1.<init>();
        r3 = "orig_id";
        r4 = r8.getOrigId();
        r4 = java.lang.Long.valueOf(r4);
        r1.put(r3, r4);
        r3 = "thumbnail_path";
        r4 = r8.getThumbnailPath();
        r1.put(r3, r4);
        r3 = "status";
        r4 = r8.getStatus();
        r4 = java.lang.Integer.valueOf(r4);
        r1.put(r3, r4);
        r3 = "last_generate_time";
        r4 = r8.getLastGenerateTime();
        r4 = java.lang.Long.valueOf(r4);
        r1.put(r3, r4);
        r3 = r2.moveToNext();	 Catch:{ all -> 0x00ab }
        if (r3 == 0) goto L_0x0098;	 Catch:{ all -> 0x00ab }
    L_0x006e:
        r3 = "thumbnail";	 Catch:{ all -> 0x00ab }
        r4 = "_id=?";	 Catch:{ all -> 0x00ab }
        r5 = 1;	 Catch:{ all -> 0x00ab }
        r5 = new java.lang.String[r5];	 Catch:{ all -> 0x00ab }
        r6 = 0;	 Catch:{ all -> 0x00ab }
        r7 = r8.getId();	 Catch:{ all -> 0x00ab }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ all -> 0x00ab }
        r5[r6] = r7;	 Catch:{ all -> 0x00ab }
        r0.update(r3, r1, r4, r5);	 Catch:{ all -> 0x00ab }
        r0 = r8.getId();	 Catch:{ all -> 0x00ab }
        r0 = r0.longValue();	 Catch:{ all -> 0x00ab }
        r3 = r2.isClosed();
        if (r3 != 0) goto L_0x0005;
    L_0x0093:
        r2.close();
        goto L_0x0005;
    L_0x0098:
        r3 = "thumbnail";	 Catch:{ all -> 0x00ab }
        r4 = 0;	 Catch:{ all -> 0x00ab }
        r0 = r0.insert(r3, r4, r1);	 Catch:{ all -> 0x00ab }
        r3 = r2.isClosed();
        if (r3 != 0) goto L_0x0005;
    L_0x00a6:
        r2.close();
        goto L_0x0005;
    L_0x00ab:
        r0 = move-exception;
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x00b5;
    L_0x00b2:
        r2.close();
    L_0x00b5:
        throw r0;
    L_0x00b6:
        r0 = 0;
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zhihu.matisse.ThumbnailHelper.save(com.zhihu.matisse.thumbnail.greendao.ThumbnailEntity):long");
    }

    public static List<ThumbnailEntity> loadAll() {
        Cursor query = MediasDatabase.getDatabase().query(TABLE_NAME, new String[]{"_id", "orig_id", "orig_path", "thumbnail_path", NotificationCompat.CATEGORY_STATUS, "last_generate_time"}, null, null, null, null, null);
        List arrayList = new ArrayList();
        if (query != null) {
            while (query.moveToNext()) {
                long j = query.getLong(0);
                arrayList.add(new ThumbnailEntity(Long.valueOf(j), query.getLong(1), query.getString(2), query.getString(3), query.getInt(4), query.getLong(5)));
            }
            if (!query.isClosed()) {
                query.close();
            }
        }
        return arrayList;
    }

    public static void delete(List<ThumbnailEntity> list) {
        if (list != null && !list.isEmpty()) {
            SQLiteDatabase database = MediasDatabase.getDatabase();
            database.beginTransaction();
            try {
                for (ThumbnailEntity thumbnailEntity : list) {
                    database.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(thumbnailEntity.getId())});
                }
                database.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                database.endTransaction();
            }
        }
    }
}
