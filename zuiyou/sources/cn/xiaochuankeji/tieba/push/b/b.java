package cn.xiaochuankeji.tieba.push.b;

import android.content.ContentValues;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.push.c.f;
import cn.xiaochuankeji.tieba.push.data.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.zhihu.matisse.ThumbnailHelper;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b {
    @android.support.annotation.WorkerThread
    public static void c(long r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0036 in list [B:6:0x002d]
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
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "select rowid,count(notify_id) from msg_notify where owner=";
        r1 = r1.append(r2);
        r1 = r1.append(r4);
        r1 = r1.toString();
        r2 = 0;
        r1 = r0.rawQuery(r1, r2);
        r2 = r1.getCount();	 Catch:{ all -> 0x0037 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ all -> 0x0037 }
        if (r2 < r3) goto L_0x002b;	 Catch:{ all -> 0x0037 }
    L_0x0025:
        r2 = "delete from msg_notify where rowid not in (select rowid from msg_notify order by is_read asc,rowid desc,update_time desc limit 200);";	 Catch:{ all -> 0x0037 }
        r0.execSQL(r2);	 Catch:{ all -> 0x0037 }
    L_0x002b:
        if (r1 == 0) goto L_0x0036;
    L_0x002d:
        r0 = r1.isClosed();
        if (r0 != 0) goto L_0x0036;
    L_0x0033:
        r1.close();
    L_0x0036:
        return;
    L_0x0037:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0043;
    L_0x003a:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x0043;
    L_0x0040:
        r1.close();
    L_0x0043:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.push.b.b.c(long):void");
    }

    public static void a(c cVar) {
        int i = 0;
        SQLiteDatabase a = a.a();
        String a2 = android.arch.b.a.c.a("msg_notify").a("notify_id=" + cVar.a + " and " + "owner" + "=" + cVar.j, null).a(new String[]{"member", "like", "ugc", "vote", "review", "danmaku", "share", "content"}).a().a();
        com.izuiyou.a.a.b.c(a2);
        Cursor rawQuery = a.rawQuery(a2, null);
        if (rawQuery != null) {
            try {
                int i2;
                ContentValues contentValues = new ContentValues();
                contentValues.put("notify_id", Long.valueOf(cVar.a));
                contentValues.put("type", Integer.valueOf(cVar.b));
                contentValues.put("owner", Long.valueOf(cVar.j));
                if (cVar.i > 0) {
                    contentValues.put(ThumbnailHelper.TABLE_NAME, Long.valueOf(cVar.i));
                }
                contentValues.put("image_type", Integer.valueOf(cVar.l));
                contentValues.put("brief", cVar.r);
                contentValues.put("pid", Long.valueOf(cVar.c));
                contentValues.put(SpeechConstant.ISV_VID, Long.valueOf(cVar.f));
                contentValues.put("tid", Long.valueOf(cVar.e));
                contentValues.put("p_r_id", Long.valueOf(cVar.g));
                contentValues.put("danmaku_id", Long.valueOf(cVar.h));
                contentValues.put("has_image", Integer.valueOf(cVar.o ? 1 : 0));
                String str = "has_video";
                if (cVar.p) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                contentValues.put(str, Integer.valueOf(i2));
                a2 = "has_sound";
                if (cVar.n) {
                    i = 1;
                }
                contentValues.put(a2, Integer.valueOf(i));
                if ((cVar.o || cVar.p || cVar.n) && TextUtils.isEmpty(cVar.q)) {
                    cVar.q = "  ";
                }
                contentValues.put("is_read", Integer.valueOf(0));
                contentValues.put("update_time", Long.valueOf(cVar.k));
                contentValues.put("is_ugc", Boolean.valueOf(cVar.A));
                if (rawQuery.moveToFirst()) {
                    Object str2 = new String(rawQuery.getBlob(rawQuery.getColumnIndex("member")), Charset.forName("UTF-8"));
                    JSONArray jSONArray = TextUtils.isEmpty(str2) ? new JSONArray() : JSON.parseArray(str2);
                    i = rawQuery.getInt(rawQuery.getColumnIndex("like"));
                    int i3 = rawQuery.getInt(rawQuery.getColumnIndex("ugc"));
                    int i4 = rawQuery.getInt(rawQuery.getColumnIndex("vote"));
                    int i5 = rawQuery.getInt(rawQuery.getColumnIndex("review"));
                    int i6 = rawQuery.getInt(rawQuery.getColumnIndex("danmaku"));
                    int i7 = rawQuery.getInt(rawQuery.getColumnIndex("share"));
                    CharSequence string = rawQuery.getString(rawQuery.getColumnIndex("content"));
                    contentValues.put("like", Integer.valueOf(i + cVar.s));
                    contentValues.put("ugc", Integer.valueOf(i3 + cVar.t));
                    contentValues.put("vote", Integer.valueOf(cVar.u + i4));
                    contentValues.put("review", Integer.valueOf(cVar.v + i5));
                    contentValues.put("danmaku", Integer.valueOf(cVar.w + i6));
                    contentValues.put("share", Integer.valueOf(cVar.x + i7));
                    if (i5 < 1) {
                        contentValues.put("rid", Long.valueOf(cVar.d));
                    }
                    if ((TextUtils.isEmpty(string) || !TextUtils.isEmpty(cVar.q)) && cVar.y != null) {
                        if (jSONArray.size() > 0) {
                            long longValue = cVar.y.getLongValue("id");
                            int b = b(cVar);
                            Iterator it = jSONArray.iterator();
                            while (it.hasNext()) {
                                JSONObject jSONObject = (JSONObject) it.next();
                                if (jSONObject.getIntValue("_m_type") != b) {
                                    it.remove();
                                } else if (longValue == jSONObject.getLongValue("id")) {
                                    it.remove();
                                }
                            }
                        }
                        if (jSONArray.size() > 3) {
                            for (i = jSONArray.size() - 1; i >= 3; i--) {
                                jSONArray.remove(i);
                            }
                        }
                        cVar.y.put("_m_type", Integer.valueOf(b(cVar)));
                        jSONArray.add(0, cVar.y);
                        contentValues.put("member", jSONArray.toJSONString().getBytes(Charset.forName("UTF-8")));
                    }
                    if (!TextUtils.isEmpty(cVar.q)) {
                        contentValues.put("content", cVar.q);
                    }
                    a.update("msg_notify", contentValues, "notify_id=? and owner=?", new String[]{String.valueOf(cVar.a), String.valueOf(cVar.j)});
                } else {
                    JSONArray jSONArray2 = new JSONArray();
                    if (cVar.y != null) {
                        cVar.y.put("_m_type", Integer.valueOf(b(cVar)));
                        jSONArray2.add(0, cVar.y);
                    }
                    contentValues.put("member", jSONArray2.toJSONString().getBytes(Charset.forName("UTF-8")));
                    contentValues.put("rid", Long.valueOf(cVar.d));
                    contentValues.put("like", Integer.valueOf(cVar.s));
                    contentValues.put("ugc", Integer.valueOf(cVar.t));
                    contentValues.put("vote", Integer.valueOf(cVar.u));
                    contentValues.put("review", Integer.valueOf(cVar.v));
                    contentValues.put("danmaku", Integer.valueOf(cVar.w));
                    contentValues.put("share", Integer.valueOf(cVar.x));
                    contentValues.put("content", cVar.q);
                    a.insert("msg_notify", null, contentValues);
                }
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            } catch (Throwable th) {
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            }
        }
        d.a().b();
        d.a().d();
        org.greenrobot.eventbus.c.a().d(new f());
    }

    private static int b(c cVar) {
        if (cVar.x > 0) {
            return 6;
        }
        if (cVar.w > 0) {
            return 5;
        }
        if (cVar.v > 0) {
            return 4;
        }
        if (cVar.u > 0) {
            return 3;
        }
        if (cVar.t > 0) {
            return 2;
        }
        if (cVar.s > 0) {
            return 1;
        }
        return -2;
    }

    public static Cursor a(long j, boolean z) {
        return a.a().rawQuery(android.arch.b.a.c.a("msg_notify").a("owner=" + j + (z ? "" : " and is_read= 0"), null).a(new String[]{"notify_id", "owner", "member", "type", "brief", "is_read", "is_ugc", "pid", "rid", "tid", SpeechConstant.ISV_VID, "p_r_id", "danmaku_id", "content", "has_image", "has_video", "has_sound", "like", "ugc", "vote", "review", "danmaku", "share", ThumbnailHelper.TABLE_NAME, "image_type"}).b("is_read>0,update_time desc").c(String.valueOf(200)).a().a(), null);
    }

    public static List<c> a(long j) {
        Cursor a = a(a.g().c(), true);
        List<c> arrayList = new ArrayList(a.getCount());
        while (a.moveToNext()) {
            JSONArray jSONArray;
            long j2 = a.getLong(a.getColumnIndex("notify_id"));
            int i = a.getInt(a.getColumnIndex("type"));
            Object str = new String(a.getBlob(a.getColumnIndex("member")), Charset.forName("UTF-8"));
            if (TextUtils.isEmpty(str)) {
                jSONArray = new JSONArray();
            } else {
                try {
                    jSONArray = JSON.parseArray(str);
                } finally {
                    if (!a.isClosed()) {
                        a.close();
                    }
                }
            }
            String string = a.getString(a.getColumnIndex("brief"));
            String string2 = a.getString(a.getColumnIndex("content"));
            boolean z = a.getInt(a.getColumnIndex("has_image")) == 1;
            boolean z2 = a.getInt(a.getColumnIndex("has_video")) == 1;
            boolean z3 = a.getInt(a.getColumnIndex("has_sound")) == 1;
            boolean z4 = a.getInt(a.getColumnIndex("is_read")) == 1;
            boolean z5 = a.getInt(a.getColumnIndex("is_ugc")) == 1;
            int i2 = a.getInt(a.getColumnIndex("like"));
            int i3 = a.getInt(a.getColumnIndex("ugc"));
            int i4 = a.getInt(a.getColumnIndex("vote"));
            int i5 = a.getInt(a.getColumnIndex("review"));
            int i6 = a.getInt(a.getColumnIndex("danmaku"));
            c a2 = new c.a().a(z4).a(i).e(z5).c(z).d(z2).b(z3).d(a.getLong(a.getColumnIndex(ThumbnailHelper.TABLE_NAME))).b(a.getInt(a.getColumnIndex("image_type"))).a(j2).e(j).b(a.getLong(a.getColumnIndex("pid"))).c(a.getLong(a.getColumnIndex("rid"))).g(a.getLong(a.getColumnIndex("tid"))).h(a.getLong(a.getColumnIndex(SpeechConstant.ISV_VID))).j(a.getLong(a.getColumnIndex("danmaku_id"))).i(a.getLong(a.getColumnIndex("p_r_id"))).c(i2).d(i3).e(i4).a(string).b(string2).f(i5).g(i6).h(a.getInt(a.getColumnIndex("share"))).a();
            a2.z = jSONArray;
            arrayList.add(a2);
        }
        return arrayList;
    }

    public static boolean b(long j) {
        SQLiteDatabase a = a.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("like", Integer.valueOf(0));
        contentValues.put("ugc", Integer.valueOf(0));
        contentValues.put("vote", Integer.valueOf(0));
        contentValues.put("review", Integer.valueOf(0));
        contentValues.put("danmaku", Integer.valueOf(0));
        contentValues.put("share", Integer.valueOf(0));
        contentValues.put("is_read", Integer.valueOf(1));
        contentValues.put("has_image", Integer.valueOf(0));
        contentValues.put("has_video", Integer.valueOf(0));
        contentValues.put("has_sound", Integer.valueOf(0));
        contentValues.put("content", "");
        if (a.update("msg_notify", contentValues, "owner=?", new String[]{String.valueOf(j)}) > 0) {
            return true;
        }
        return false;
    }

    public static boolean a(long j, long j2) {
        SQLiteDatabase a = a.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("like", Integer.valueOf(0));
        contentValues.put("ugc", Integer.valueOf(0));
        contentValues.put("vote", Integer.valueOf(0));
        contentValues.put("review", Integer.valueOf(0));
        contentValues.put("danmaku", Integer.valueOf(0));
        contentValues.put("share", Integer.valueOf(0));
        contentValues.put("is_read", Integer.valueOf(1));
        contentValues.put("has_image", Integer.valueOf(0));
        contentValues.put("has_video", Integer.valueOf(0));
        contentValues.put("has_sound", Integer.valueOf(0));
        contentValues.put("content", "");
        if (a.update("msg_notify", contentValues, "owner=? and notify_id=? ", new String[]{String.valueOf(j), String.valueOf(j2)}) > 0) {
            return true;
        }
        return false;
    }

    public static boolean b(long j, long j2) {
        if (a.a().delete("msg_notify", "owner=? and notify_id=? ", new String[]{String.valueOf(j), String.valueOf(j2)}) > 0) {
            return true;
        }
        return false;
    }
}
