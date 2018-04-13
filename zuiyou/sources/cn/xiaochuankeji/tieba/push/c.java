package cn.xiaochuankeji.tieba.push;

import android.content.ContentValues;
import android.os.Environment;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.data.XSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechEvent;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.b;

public class c {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    private static void h() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000c in list [B:21:0x0090]
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
        r0 = 0;
        r1 = 1;
        r1 = cn.xiaochuankeji.tieba.push.b.e.a(r1);
        r2 = cn.xiaochuankeji.tieba.push.b.a.b(r1);
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "select rowid,x_sid from ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r3 = " order by ";
        r2 = r2.append(r3);
        r3 = "x_sid";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r3 = cn.xiaochuankeji.tieba.push.b.a.a();
        r4 = 0;
        r2 = r3.rawQuery(r2, r4);
        if (r2 == 0) goto L_0x000c;
    L_0x003a:
        r4 = r2.getCount();
        r3.beginTransaction();
    L_0x0041:
        r5 = r4 + -1;
        if (r0 >= r5) goto L_0x0073;
    L_0x0045:
        r2.moveToPosition(r0);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r5 = 0;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r5 = r2.getInt(r5);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r6 = 1;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r6 = r2.getLong(r6);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r8 = r0 + 1;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r2.moveToPosition(r8);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r8 = 1;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r8 = r2.getLong(r8);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        if (r6 != 0) goto L_0x0070;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
    L_0x0060:
        r6 = "rowid=?";	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r7 = 1;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r7 = new java.lang.String[r7];	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r8 = 0;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r7[r8] = r5;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r3.delete(r1, r6, r7);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
    L_0x0070:
        r0 = r0 + 1;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        goto L_0x0041;	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
    L_0x0073:
        r3.setTransactionSuccessful();	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r3.endTransaction();
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x000c;
    L_0x007f:
        r2.close();
        goto L_0x000c;
    L_0x0083:
        r0 = move-exception;
        com.izuiyou.a.a.b.e(r0);	 Catch:{ Exception -> 0x0083, all -> 0x0095 }
        r3.endTransaction();
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x000c;
    L_0x0090:
        r2.close();
        goto L_0x000c;
    L_0x0095:
        r0 = move-exception;
        r3.endTransaction();
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x00a2;
    L_0x009f:
        r2.close();
    L_0x00a2:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.push.c.h():void");
    }

    @WorkerThread
    static void a() {
        int i = a.a().getInt("_key_chat_sync", 0);
        if (i == 0) {
            a.a().edit().putInt("_key_chat_sync", 3).apply();
            cn.xiaochuan.push.c.a().a(new Runnable() {
                public void run() {
                    c.d();
                }
            });
        } else if (i == 1) {
            a.a().edit().putInt("_key_chat_sync", 3).apply();
            g();
        } else if (i == 2) {
            List<XSession> b = e.b(1);
            long c = a.g().c();
            SQLiteDatabase a = cn.xiaochuankeji.tieba.push.b.a.a();
            String a2 = e.a(1);
            a.beginTransaction();
            try {
                for (XSession xSession : b) {
                    if (c == xSession.x_sid && xSession.session_id > 0) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("x_sid", Long.valueOf(xSession.x_other.id));
                        a.update(a2, contentValues, "session_id=?", new String[]{String.valueOf(xSession.session_id)});
                    }
                }
                a.setTransactionSuccessful();
            } catch (Exception e) {
            } finally {
                a.endTransaction();
            }
        } else if (i == 3) {
            a.a().edit().putInt("_key_chat_sync", 4).apply();
            cn.xiaochuan.push.c.a().a(new Runnable() {
                public void run() {
                    c.h();
                }
            });
        }
    }

    private static void d() {
        e();
        a.a().edit().remove("kMarkAllMsgHasRead").apply();
        a.a().edit().remove("kVersionData").apply();
        d.i();
        f();
        File C = a.e().C();
        if (C != null && C.exists() && C.isDirectory()) {
            try {
                b.b(C);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void e() {
        Object string = a.c().getString("key_message", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONArray jSONArray = JSON.parseObject(string).getJSONArray("list");
                if (jSONArray != null && !jSONArray.isEmpty()) {
                    for (int i = 0; i < jSONArray.size(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        long longValue = jSONObject.getLongValue("id");
                        if (longValue > 0) {
                            jSONObject.put("pid", Long.valueOf(longValue));
                        }
                        longValue = jSONObject.getLongValue("firstCommentId");
                        if (longValue > 0) {
                            jSONObject.put("rid", Long.valueOf(longValue));
                        }
                        int intValue = jSONObject.getIntValue("msgType");
                        if (intValue == 0) {
                            longValue = jSONObject.getLongValue("oid");
                            if (longValue > 0) {
                                cn.xiaochuankeji.tieba.push.d.b.e(0, longValue, jSONObject);
                            }
                        } else if (intValue == 19) {
                            longValue = jSONObject.getLongValue("oid");
                            if (longValue > 0) {
                                cn.xiaochuankeji.tieba.push.d.b.a(0, longValue, jSONObject);
                            }
                        } else if (intValue == 18) {
                            longValue = jSONObject.getLongValue("oid");
                            if (longValue > 0) {
                                cn.xiaochuankeji.tieba.push.d.b.b(0, longValue, jSONObject);
                            }
                        } else if (intValue == 3) {
                            longValue = jSONObject.getLongValue("oid");
                            if (longValue > 0) {
                                cn.xiaochuankeji.tieba.push.d.b.d(0, longValue, jSONObject);
                            }
                        } else if (intValue == 4) {
                            longValue = jSONObject.getLongValue("oid");
                            if (longValue > 0) {
                                cn.xiaochuankeji.tieba.push.d.b.c(0, longValue, jSONObject);
                            }
                        }
                    }
                    SQLiteDatabase a = cn.xiaochuankeji.tieba.push.b.a.a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("like", Integer.valueOf(0));
                    contentValues.put("ugc", Integer.valueOf(0));
                    contentValues.put("vote", Integer.valueOf(0));
                    contentValues.put("review", Integer.valueOf(0));
                    contentValues.put("danmaku", Integer.valueOf(0));
                    contentValues.put("share", Integer.valueOf(0));
                    contentValues.put("is_read", Integer.valueOf(1));
                    a.update("msg_notify", contentValues, null, null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void f() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xc_push_tieba.txt");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a(long j) {
        return String.valueOf("session_" + j);
    }

    @WorkerThread
    private static void g() {
        String a = a(a.g().c());
        if (cn.xiaochuankeji.tieba.push.b.a.b(a)) {
            SQLiteDatabase a2 = cn.xiaochuankeji.tieba.push.b.a.a();
            a = android.arch.b.a.c.a(a).a(new String[]{SpeechEvent.KEY_EVENT_SESSION_ID, "msg_mid"}).a("status!=0 ", null).a().a();
            com.izuiyou.a.a.b.c(a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                int columnIndex = rawQuery.getColumnIndex(SpeechEvent.KEY_EVENT_SESSION_ID);
                long columnIndex2 = (long) rawQuery.getColumnIndex("msg_mid");
                a2.beginTransaction();
                while (rawQuery.moveToNext()) {
                    try {
                        e.a(rawQuery.getLong(columnIndex), columnIndex2, 1, 3);
                    } finally {
                        a2.endTransaction();
                    }
                }
                a2.setTransactionSuccessful();
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            }
        }
    }
}
