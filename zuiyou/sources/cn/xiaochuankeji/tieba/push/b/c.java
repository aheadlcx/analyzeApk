package cn.xiaochuankeji.tieba.push.b;

import android.content.ContentValues;
import android.text.TextUtils;
import cn.xiaochuan.push.e;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.open.SocialConstants;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;

public class c {
    @android.support.annotation.WorkerThread
    public static void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0025 in list [B:6:0x001c]
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
        r0 = cn.xiaochuankeji.tieba.push.b.a.a();
        r1 = "select rowid,count(msg_id) from msg_push";
        r2 = 0;
        r1 = r0.rawQuery(r1, r2);
        r2 = r1.getCount();	 Catch:{ all -> 0x0026 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ all -> 0x0026 }
        if (r2 < r3) goto L_0x001a;	 Catch:{ all -> 0x0026 }
    L_0x0014:
        r2 = "delete from msg_push where rowid not in (select rowid from msg_push order by create_time desc limit 200);";	 Catch:{ all -> 0x0026 }
        r0.execSQL(r2);	 Catch:{ all -> 0x0026 }
    L_0x001a:
        if (r1 == 0) goto L_0x0025;
    L_0x001c:
        r0 = r1.isClosed();
        if (r0 != 0) goto L_0x0025;
    L_0x0022:
        r1.close();
    L_0x0025:
        return;
    L_0x0026:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0032;
    L_0x0029:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x0032;
    L_0x002f:
        r1.close();
    L_0x0032:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.push.b.c.a():void");
    }

    public static void a(e eVar) {
        int i = 1;
        if (eVar != null && !TextUtils.isEmpty(eVar.b)) {
            SQLiteDatabase a = a.a();
            Cursor rawQuery = a.rawQuery(android.arch.b.a.c.a("msg_push").a(new String[]{"msg_id", "notified"}).a("msg_id='" + eVar.b + "'", null).a().a(), null);
            ContentValues contentValues;
            String str;
            if (rawQuery == null || !rawQuery.moveToFirst()) {
                contentValues = new ContentValues();
                contentValues.put("msg_id", eVar.b);
                contentValues.put("notify_id", Integer.valueOf(eVar.g));
                contentValues.put("title", eVar.c);
                contentValues.put(SocialConstants.PARAM_APP_DESC, eVar.d);
                str = "notified";
                if (!eVar.e) {
                    i = 0;
                }
                contentValues.put(str, Integer.valueOf(i));
                contentValues.put("type", Integer.valueOf(eVar.f));
                String str2 = PushConstants.EXTRA;
                if (eVar.k == null) {
                    str = null;
                } else {
                    str = eVar.k.toString();
                }
                contentValues.put(str2, str);
                if (eVar.h > 0) {
                    contentValues.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, Long.valueOf(eVar.h));
                }
                if (eVar.i > 0) {
                    contentValues.put("click_time", Long.valueOf(eVar.i));
                }
                a.insert("msg_push", null, contentValues);
            } else {
                int i2 = rawQuery.getInt(1);
                contentValues = new ContentValues();
                contentValues.put("msg_id", eVar.b);
                contentValues.put("notify_id", Integer.valueOf(eVar.g));
                contentValues.put("title", eVar.c);
                contentValues.put(SocialConstants.PARAM_APP_DESC, eVar.d);
                if (i2 != 1) {
                    contentValues.put("notified", Integer.valueOf(eVar.e ? 1 : 0));
                }
                contentValues.put("type", Integer.valueOf(eVar.f));
                String str3 = PushConstants.EXTRA;
                if (eVar.k == null) {
                    str = null;
                } else {
                    str = eVar.k.toString();
                }
                contentValues.put(str3, str);
                if (eVar.h > 0) {
                    contentValues.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, Long.valueOf(eVar.h));
                }
                if (eVar.i > 0) {
                    contentValues.put("click_time", Long.valueOf(eVar.i));
                }
                a.update("msg_push", contentValues, "msg_id=?", new String[]{String.valueOf(eVar.b)});
            }
            if (rawQuery != null && !rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
    }

    public static void a(String str, long j, long j2) {
        SQLiteDatabase a = a.a();
        if (a(String.valueOf(str))) {
            ContentValues contentValues = new ContentValues();
            if (j > 0) {
                contentValues.put("click_time", Long.valueOf(j));
            }
            if (j2 > 0) {
                contentValues.put("delete_time", Long.valueOf(j2));
            }
            a.update("msg_push", contentValues, "msg_id=?", new String[]{String.valueOf(str)});
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r7) {
        /*
        r6 = 0;
        r0 = 1;
        r1 = 0;
        r2 = cn.xiaochuankeji.tieba.push.b.a.a();
        r3 = "msg_push";
        r3 = android.arch.b.a.c.a(r3);
        r4 = 2;
        r4 = new java.lang.String[r4];
        r5 = "msg_id";
        r4[r1] = r5;
        r5 = "notified";
        r4[r0] = r5;
        r3 = r3.a(r4);
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "msg_id='";
        r4 = r4.append(r5);
        r4 = r4.append(r7);
        r5 = "'";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3 = r3.a(r4, r6);
        r3 = r3.a();
        r3 = r3.a();
        r2 = r2.rawQuery(r3, r6);
        if (r2 == 0) goto L_0x005e;
    L_0x004c:
        r3 = r2.moveToFirst();	 Catch:{ all -> 0x0060 }
        if (r3 == 0) goto L_0x005e;
    L_0x0052:
        if (r2 == 0) goto L_0x005d;
    L_0x0054:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x005d;
    L_0x005a:
        r2.close();
    L_0x005d:
        return r0;
    L_0x005e:
        r0 = r1;
        goto L_0x0052;
    L_0x0060:
        r0 = move-exception;
        if (r2 == 0) goto L_0x006c;
    L_0x0063:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x006c;
    L_0x0069:
        r2.close();
    L_0x006c:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.push.b.c.a(java.lang.String):boolean");
    }
}
