package cn.xiaochuankeji.tieba.ui.videomaker.sticker.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import cn.xiaochuankeji.tieba.a.g;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import com.iflytek.aiui.AIUIConstant;
import com.izuiyou.a.a.b;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class c extends g {

    private static class a {
        private static final c a = new c();
    }

    public static c a() {
        return a.a;
    }

    private c() {
    }

    public List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> a(String str) {
        SQLiteDatabase a = cn.xiaochuankeji.tieba.a.c.a();
        Cursor rawQuery = a.rawQuery("select name,size,width,height,tag,mime_type,path,remote,source,preview,status,percent,create_time,cr from sticker where tag='" + b(str) + "'  order by create_time desc;", null);
        List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a aVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a();
            aVar.p = rawQuery.getString(0);
            aVar.r = rawQuery.getLong(1);
            aVar.h = rawQuery.getInt(2);
            aVar.i = rawQuery.getInt(3);
            aVar.o = a(rawQuery.getInt(4));
            aVar.l = rawQuery.getString(5);
            aVar.d = rawQuery.getString(6);
            aVar.e = ServerImgJson.fromJSONString(rawQuery.getString(7));
            aVar.f = rawQuery.getString(8);
            aVar.g = rawQuery.getString(9);
            aVar.c = rawQuery.getInt(10);
            aVar.s = rawQuery.getFloat(11);
            aVar.q = rawQuery.getString(12);
            File file = new File(aVar.d);
            File file2 = new File(aVar.f);
            if (file.exists() || file2.exists()) {
                try {
                    arrayList.add(aVar);
                } finally {
                    arrayList = rawQuery.isClosed();
                    if (arrayList == null) {
                        rawQuery.close();
                    }
                }
            } else {
                String[] strArr = new String[]{String.valueOf(aVar.p)};
                a.delete("sticker", "name=?", strArr);
            }
        }
        return arrayList;
    }

    public void a(List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> list) {
        SQLiteDatabase a = cn.xiaochuankeji.tieba.a.c.a();
        a.beginTransaction();
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < list.size()) {
            try {
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a) list.get(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, Long.valueOf(((long) i) + currentTimeMillis));
                contentValues.put("name", aVar.p);
                contentValues.put("size", Long.valueOf(aVar.r));
                contentValues.put("width", Integer.valueOf(aVar.h));
                contentValues.put("height", Integer.valueOf(aVar.i));
                contentValues.put(AIUIConstant.KEY_TAG, Integer.valueOf(b(aVar.o)));
                contentValues.put("mime_type", aVar.l);
                contentValues.put(AIUIConstant.RES_TYPE_PATH, aVar.d);
                if (aVar.e != null) {
                    contentValues.put("remote", aVar.e.toJSON());
                }
                contentValues.put("source", aVar.f);
                contentValues.put("preview", aVar.g);
                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(aVar.c));
                contentValues.put("percent", Float.valueOf(aVar.s));
                contentValues.put("cr", aVar.q);
                if (((long) a.updateWithOnConflict("sticker", contentValues, " name=? ", new String[]{String.valueOf(aVar.p)}, 4)) < 1) {
                    a.insert("sticker", null, contentValues);
                }
                i++;
            } catch (Exception e) {
                b.e(e);
            } finally {
                a.endTransaction();
            }
        }
        a.setTransactionSuccessful();
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a aVar) {
        String str = "sticker";
        cn.xiaochuankeji.tieba.a.c.a().delete(str, "name=?", new String[]{String.valueOf(aVar.p)});
    }

    public void a(Collection<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> collection) {
        SQLiteDatabase a = cn.xiaochuankeji.tieba.a.c.a();
        a.beginTransaction();
        try {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                String str = "sticker";
                a.delete(str, "name=?", new String[]{String.valueOf(((cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a) it.next()).p)});
            }
            a.setTransactionSuccessful();
        } catch (Exception e) {
            b.e(e);
        } finally {
            a.endTransaction();
        }
    }

    private String a(int i) {
        if (i == 0) {
            return "自定义";
        }
        if (i == 1) {
            return "贴纸";
        }
        if (i == 2) {
            return "GIF";
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(java.lang.String r6) {
        /*
        r5 = this;
        r2 = 2;
        r1 = 1;
        r0 = 0;
        r3 = -1;
        r4 = r6.hashCode();
        switch(r4) {
            case 70564: goto L_0x0027;
            case 1153028: goto L_0x001c;
            case 32707929: goto L_0x0011;
            default: goto L_0x000b;
        };
    L_0x000b:
        r4 = r3;
    L_0x000c:
        switch(r4) {
            case 0: goto L_0x0010;
            case 1: goto L_0x0032;
            case 2: goto L_0x0034;
            default: goto L_0x000f;
        };
    L_0x000f:
        r0 = r3;
    L_0x0010:
        return r0;
    L_0x0011:
        r4 = "自定义";
        r4 = r6.equals(r4);
        if (r4 == 0) goto L_0x000b;
    L_0x001a:
        r4 = r0;
        goto L_0x000c;
    L_0x001c:
        r4 = "贴纸";
        r4 = r6.equals(r4);
        if (r4 == 0) goto L_0x000b;
    L_0x0025:
        r4 = r1;
        goto L_0x000c;
    L_0x0027:
        r4 = "GIF";
        r4 = r6.equals(r4);
        if (r4 == 0) goto L_0x000b;
    L_0x0030:
        r4 = r2;
        goto L_0x000c;
    L_0x0032:
        r0 = r1;
        goto L_0x0010;
    L_0x0034:
        r0 = r2;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.c.b(java.lang.String):int");
    }
}
