package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.tencent.stat.DeviceInfo;
import java.util.ArrayList;
import java.util.List;

public class d {
    Context a;
    SQLiteDatabase b;
    c c;

    public d(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void g() {
        this.b = this.c.getWritableDatabase();
    }

    private void h() {
        this.b.close();
    }

    public void a(String str, String str2) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("repost", str2);
            g();
            this.b.update("collectTable", contentValues, "wid= " + str, null);
            h();
        }
    }

    public ArrayList<String> a() {
        ArrayList<String> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"wid"};
            g();
            Cursor query = this.b.query("collectTable", strArr, "data2 is null or data2 <> 'yes' ", null, null, null, null, "");
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    arrayList.add(query.getString(0));
                }
            }
            if (arrayList.size() > 0) {
                aa.a("CollectDB", "登录查询本地收藏，有，弹出收藏同步对话框");
            } else {
                aa.a("CollectDB", "登录查询本地收藏，没有");
            }
            query.close();
            h();
        }
        return arrayList;
    }

    public void b(String str, String str2) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("data2", "yes");
            aa.a("CollectDB", "收藏成功，更新上传状态为yes：" + str);
            contentValues.put("data4", str);
            g();
            this.b.update("collectTable", contentValues, "wid = " + str2, null);
            h();
        }
    }

    public void a(ListItemObject listItemObject, String str, String str2) {
        a(listItemObject, str, str2, "0");
    }

    public void a(ListItemObject listItemObject, String str, String str2, String str3) {
        if (listItemObject.getOriginal_topic() != null) {
            a(listItemObject.getOriginal_topic(), str, "yes", "3");
        }
        synchronized (c.a) {
            try {
                aa.a("CollectDB", "添加收藏,状态为：" + str2);
                ContentValues contentValues = new ContentValues();
                contentValues.put("wid", listItemObject.getWid());
                contentValues.put("name", listItemObject.getName());
                contentValues.put("addtime", listItemObject.getAddtime());
                contentValues.put("passtime", listItemObject.getPasstime());
                contentValues.put("comment", listItemObject.getComment());
                contentValues.put("content", listItemObject.getContent());
                contentValues.put("neturl", listItemObject.getImgUrl());
                contentValues.put("cnd_img", listItemObject.getCnd_img());
                contentValues.put("cnd_img", listItemObject.getCnd_img());
                contentValues.put("type", listItemObject.getType());
                contentValues.put(IndexEntry.COLUMN_NAME_WIDTH, Integer.valueOf(listItemObject.getWidth()));
                contentValues.put(IndexEntry.COLUMN_NAME_HEIGHT, Integer.valueOf(listItemObject.getHeight()));
                contentValues.put(DeviceInfo.TAG_MID, listItemObject.getMid());
                contentValues.put(StatisticCodeTable.PROFILE, listItemObject.getProfile());
                contentValues.put("repost", listItemObject.getRepost());
                contentValues.put("love", Integer.valueOf(listItemObject.getLove()));
                contentValues.put("cai", Integer.valueOf(listItemObject.getCai()));
                contentValues.put("is_gif", listItemObject.getIs_gif());
                contentValues.put("weixinurl", listItemObject.getWeixin_url());
                contentValues.put("gif_first_frame", listItemObject.getGifFistFrame());
                if (TextUtils.isEmpty(str)) {
                    contentValues.put("data4", "");
                } else {
                    contentValues.put("data4", str);
                }
                contentValues.put("data2", str2);
                if ("yes".equals(str2)) {
                    contentValues.put("data3", listItemObject.getAddtime());
                    aa.a("CollectDB", "data3：" + listItemObject.getAddtime());
                } else {
                    String a = an.a(this.a, "yyyy-MM-dd HH:mm:ss");
                    contentValues.put("data3", a);
                    aa.a("CollectDB", "data3：" + a);
                }
                Object voiceUri = listItemObject.getVoiceUri();
                Object voicetime = listItemObject.getVoicetime();
                Object voicelength = listItemObject.getVoicelength();
                if (!TextUtils.isEmpty(voiceUri)) {
                    contentValues.put("voiceuri", voiceUri);
                }
                if (!TextUtils.isEmpty(voicetime)) {
                    contentValues.put("voicetime", voicetime);
                }
                if (!TextUtils.isEmpty(voicelength)) {
                    contentValues.put("voicelength", voicelength);
                }
                voiceUri = listItemObject.getVideouri();
                voicetime = listItemObject.getVideotime();
                if (!TextUtils.isEmpty(voiceUri)) {
                    contentValues.put("videouri", voiceUri);
                }
                if (!TextUtils.isEmpty(voicetime)) {
                    contentValues.put("videotime", voicetime);
                }
                contentValues.put("playcount", listItemObject.getPlaycount());
                contentValues.put("playfcount", listItemObject.getPlayfcount());
                contentValues.put("noVoiceCmt", listItemObject.getNoVoiceCmt());
                contentValues.put("userid", listItemObject.getUid());
                contentValues.put("theme_id", Integer.valueOf(listItemObject.getTheme_id()));
                contentValues.put("theme_type", Integer.valueOf(listItemObject.getTheme_type()));
                contentValues.put("theme_name", listItemObject.getTheme_name());
                if (listItemObject.getRichObject() != null) {
                    contentValues.put("rich_desc", listItemObject.getRichObject().getDesc());
                    contentValues.put("rich_img_url", listItemObject.getRichObject().getImgUrl());
                    contentValues.put("rich_source_rl", listItemObject.getRichObject().getSourceUrl());
                    contentValues.put("rich_title", listItemObject.getRichObject().getTitle());
                }
                contentValues.put("cacheType", str3);
                contentValues.put("original_pid", listItemObject.getOriginal_pid());
                if (listItemObject.getDownloadVideoUris() != null) {
                    contentValues.put("downloadVideoUris", TextUtils.join(",", listItemObject.getDownloadVideoUris()));
                }
                if (listItemObject.getDownloadImageUris() != null) {
                    contentValues.put("downloadImageUris", TextUtils.join(",", listItemObject.getDownloadImageUris()));
                }
                contentValues.put("videouri_back", listItemObject.getVideouriBackup());
                if (a(listItemObject.getWid())) {
                    g();
                    contentValues = new ContentValues();
                    contentValues.put("data2", str2);
                    this.b.update("collectTable", contentValues, "wid=" + listItemObject.getWid(), null);
                    h();
                } else {
                    g();
                    this.b.insert("collectTable", null, contentValues);
                    h();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean a(String str) {
        boolean z;
        synchronized (c.a) {
            g();
            Cursor query = this.b.query("collectTable", new String[]{"id"}, "wid = " + str, null, null, null, null);
            if (query == null || !query.moveToFirst()) {
                z = false;
            } else {
                z = true;
            }
            query.close();
            h();
        }
        return z;
    }

    public void b() {
        synchronized (c.a) {
            g();
            this.b.delete("collectTable", null, null);
            h();
        }
    }

    public void c() {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("data2", "no");
            aa.a("CollectDB", "退出时，清除收藏对话框选择取消，解除收藏和用户的关系，收藏上传状态改为no");
            contentValues.put("data4", "");
            g();
            this.b.update("collectTable", contentValues, null, null);
            h();
        }
    }

    public boolean d() {
        synchronized (c.a) {
            String[] strArr = new String[]{"id"};
            g();
            if (this.b.query("collectTable", strArr, "data2 = 'yes'", null, null, null, null, "").getCount() != 0) {
                aa.a("CollectDB", "退出查询是否有本地收藏：有，弹出清除收藏对话框");
                return true;
            }
            aa.a("CollectDB", "退出查询是否有本地收藏：没有");
            h();
            return false;
        }
    }

    public int e() {
        int delete;
        synchronized (c.a) {
            g();
            delete = this.b.delete("collectTable", "data2 = 'yes'", null);
            aa.a("CollectDB", "退出时清除本地收藏，将上传状态为yes的数据删除");
            h();
        }
        return delete;
    }

    public List<String> f() {
        List<String> arrayList;
        Cursor query;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (c.a) {
            arrayList = new ArrayList();
            try {
                g();
                query = this.b.query("collectTable", new String[]{"wid"}, "cacheType != ?", new String[]{"3"}, null, null, null, null);
                try {
                    if (query.getCount() >= 0) {
                        while (query.moveToNext()) {
                            arrayList.add(query.getString(0));
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (this.b != null) {
                        h();
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        if (this.b != null) {
                            h();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (this.b != null) {
                            h();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                e.printStackTrace();
                if (query != null) {
                    query.close();
                }
                if (this.b != null) {
                    h();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                if (this.b != null) {
                    h();
                }
                throw th;
            }
        }
        return arrayList;
    }
}
