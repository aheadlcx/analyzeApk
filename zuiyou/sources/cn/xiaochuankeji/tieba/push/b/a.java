package cn.xiaochuankeji.tieba.push.b;

import android.content.Context;
import android.support.annotation.NonNull;
import cn.xiaochuan.base.BaseApplication;
import com.izuiyou.a.a.b;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;
import com.tencent.wcdb.database.SQLiteTrace;
import com.tencent.wcdb.repair.RepairKit.MasterInfo;
import java.nio.charset.Charset;
import java.util.List;

public class a extends SQLiteOpenHelper {
    static final SQLiteCipherSpec a = new SQLiteCipherSpec();
    private static volatile SQLiteDatabase c;
    private static SQLiteTrace d = new SQLiteTrace() {
        public void onSQLExecuted(SQLiteDatabase sQLiteDatabase, String str, int i, long j) {
            b.b("trace", str + "     cost " + j + " milliseconds ");
        }

        public void onConnectionObtained(SQLiteDatabase sQLiteDatabase, String str, long j, boolean z) {
        }

        public void onConnectionPoolBusy(SQLiteDatabase sQLiteDatabase, String str, List<String> list, String str2) {
        }

        public void onDatabaseCorrupted(SQLiteDatabase sQLiteDatabase) {
        }
    };
    private byte[] b;

    public static boolean b(java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x005e in list [B:8:0x005b]
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
        r6 = 0;
        r0 = 1;
        r1 = 0;
        r2 = a();
        r3 = "sqlite_master";
        r3 = android.arch.b.a.c.a(r3);
        r4 = 2;
        r4 = new java.lang.String[r4];
        r5 = "type";
        r4[r1] = r5;
        r5 = "name";
        r4[r0] = r5;
        r3 = r3.a(r4);
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "type='table' and name='";
        r4 = r4.append(r5);
        r4 = r4.append(r7);
        r5 = "'";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3 = r3.a(r4, r6);
        r3 = r3.a();
        r3 = r3.a();
        com.izuiyou.a.a.b.c(r3);
        r2 = r2.rawQuery(r3, r6);
        if (r2 == 0) goto L_0x006c;
    L_0x004f:
        r3 = r2.getCount();	 Catch:{ all -> 0x0061 }
        if (r3 <= 0) goto L_0x005f;
    L_0x0055:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x005e;
    L_0x005b:
        r2.close();
    L_0x005e:
        return r0;
    L_0x005f:
        r0 = r1;
        goto L_0x0055;
    L_0x0061:
        r0 = move-exception;
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x006b;
    L_0x0068:
        r2.close();
    L_0x006b:
        throw r0;
    L_0x006c:
        r0 = r1;
        goto L_0x005e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.push.b.a.b(java.lang.String):boolean");
    }

    public a(@NonNull Context context, @NonNull byte[] bArr) {
        super(context, "message.db", null, a, null, 4, null);
        this.b = null;
    }

    public a(Context context) {
        this(context, a(context));
    }

    public static byte[] a(Context context) {
        return (context.getPackageName() + "_" + "message.db").getBytes(Charset.defaultCharset());
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS msg_push (msg_id text,notify_id integer,title text,desc text,notified integer(1),type integer,extra text,create_time integer(64),invalid_time integer(64),click_time integer(64),delete_time integer(64),PRIMARY KEY (msg_id));");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS notify_index ON msg_push(msg_id);");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS msg_notify (notify_id integer(64),owner integer(64),type integer,pid integer(64),rid integer(64),tid integer(64),vid integer(64),p_r_id integer(64),danmaku_id integer(64),is_read integer(1),is_ugc integer(1),has_sound integer(1),has_image integer(1),has_video integer(1),content text,brief text,like integer,ugc integer,vote integer,review integer,danmaku integer,share integer,member blob,thumbnail integer(64),update_time integer(64),image_type integer);");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Throwable e) {
            cn.xiaochuankeji.tieba.analyse.a.a(e);
        } finally {
            sQLiteDatabase.endTransaction();
        }
        MasterInfo.save(sQLiteDatabase, sQLiteDatabase.getPath() + "-mbak", this.b);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Object[] objArr = new Object[1];
        objArr[0] = String.format("Upgrading database from version %d to version %d.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        b.b("MessageDBHelper", objArr);
        MasterInfo.save(sQLiteDatabase, sQLiteDatabase.getPath() + "-mbak", this.b);
    }

    public static SQLiteDatabase a() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(BaseApplication.getAppContext()).getWritableDatabase();
                }
            }
        }
        return c;
    }

    public static synchronized void a(String str) {
        synchronized (a.class) {
            a();
        }
    }
}
