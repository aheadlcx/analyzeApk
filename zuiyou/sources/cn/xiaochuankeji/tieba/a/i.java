package cn.xiaochuankeji.tieba.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import cn.xiaochuankeji.tieba.analyse.a;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

public class i extends SQLiteOpenHelper {
    public static boolean a(com.tencent.wcdb.database.SQLiteDatabase r6, java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x005a in list [B:8:0x0057]
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
        r5 = 0;
        r0 = 1;
        r1 = 0;
        r2 = "sqlite_master";
        r2 = android.arch.b.a.c.a(r2);
        r3 = 2;
        r3 = new java.lang.String[r3];
        r4 = "type";
        r3[r1] = r4;
        r4 = "name";
        r3[r0] = r4;
        r2 = r2.a(r3);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "type='table' and name='";
        r3 = r3.append(r4);
        r3 = r3.append(r7);
        r4 = "'";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2 = r2.a(r3, r5);
        r2 = r2.a();
        r2 = r2.a();
        com.izuiyou.a.a.b.c(r2);
        r2 = r6.rawQuery(r2, r5);
        if (r2 == 0) goto L_0x0068;
    L_0x004b:
        r3 = r2.getCount();	 Catch:{ all -> 0x005d }
        if (r3 <= 0) goto L_0x005b;
    L_0x0051:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x005a;
    L_0x0057:
        r2.close();
    L_0x005a:
        return r0;
    L_0x005b:
        r0 = r1;
        goto L_0x0051;
    L_0x005d:
        r0 = move-exception;
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0067;
    L_0x0064:
        r2.close();
    L_0x0067:
        throw r0;
    L_0x0068:
        r0 = r1;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.a.i.a(com.tencent.wcdb.database.SQLiteDatabase, java.lang.String):boolean");
    }

    i(@NonNull Context context) {
        super(context, "zuiyou-app", null, null, null, 2, null);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS post_config (_id INTEGER PRIMARY KEY ,post_id INTEGER NOT NULL UNIQUE ,post_json_str TEXT,update_time INTEGER NOT NULL ,mid INTEGER NOT NULL );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS splash_config (_id INTEGER PRIMARY KEY ,start_time INTEGER NOT NULL ,end_time INTEGER NOT NULL ,type INTEGER NOT NULL ,duration INTEGER NOT NULL ,image_http_url TEXT,image_local_uri TEXT,data_tid INTEGER NOT NULL ,data_activity_url TEXT,STATUS INTEGER NOT NULL ,VERSION INTEGER NOT NULL );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS app_config (_id INTEGER PRIMARY KEY ,update_time INTEGER NOT NULL ,config_key TEXT UNIQUE ,config_version INTEGER NOT NULL ,config_value TEXT);");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Throwable e) {
            a.a(e);
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.i("ZYDBHelper", String.format("Upgrading database from version %d to version %d.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        if (i == 1) {
            if (a(sQLiteDatabase, "post_config")) {
                sQLiteDatabase.delete("post_config", null, null);
            }
            if (a(sQLiteDatabase, "splash_config")) {
                sQLiteDatabase.delete("splash_config", null, null);
            }
            if (a(sQLiteDatabase, "app_config")) {
                sQLiteDatabase.delete("app_config", null, null);
            }
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS post_config (_id INTEGER PRIMARY KEY ,post_id INTEGER NOT NULL UNIQUE ,post_json_str TEXT,update_time INTEGER NOT NULL ,mid INTEGER NOT NULL );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS splash_config (_id INTEGER PRIMARY KEY ,start_time INTEGER NOT NULL ,end_time INTEGER NOT NULL ,type INTEGER NOT NULL ,duration INTEGER NOT NULL ,image_http_url TEXT,image_local_uri TEXT,data_tid INTEGER NOT NULL ,data_activity_url TEXT,STATUS INTEGER NOT NULL ,VERSION INTEGER NOT NULL );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS app_config (_id INTEGER PRIMARY KEY ,update_time INTEGER NOT NULL ,config_key TEXT UNIQUE ,config_version INTEGER NOT NULL ,config_value TEXT);");
        }
    }
}
