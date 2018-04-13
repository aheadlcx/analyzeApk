package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alipay.sdk.cons.b;
import com.budejie.www.bean.DraftBean;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.util.aa;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class f {
    private Context a;
    private c b = c.a(this.a);
    private SQLiteDatabase c;

    public f(Context context) {
        this.a = context;
    }

    private void c() {
        this.c = this.b.getWritableDatabase();
    }

    private void d() {
        this.c.close();
    }

    public void a(DraftBean draftBean) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            c();
            this.c.beginTransaction();
            contentValues.put(HistoryOpenHelper.COLUMN_UID, Integer.valueOf(draftBean.uid));
            contentValues.put("state", Integer.valueOf(draftBean.state));
            contentValues.put("bimage", draftBean.bimage);
            contentValues.put("voice", draftBean.voice);
            contentValues.put("createTime", draftBean.createTime);
            contentValues.put("content", draftBean.content);
            contentValues.put("bvoiceid", Integer.valueOf(draftBean.bvoiceid));
            contentValues.put("voicetime", Integer.valueOf(draftBean.voicetime));
            contentValues.put("reserve", draftBean.reserve);
            contentValues.put("video", draftBean.video);
            contentValues.put("videotime", Integer.valueOf(draftBean.videotime));
            contentValues.put("theme_id", Integer.valueOf(draftBean.theme_id));
            contentValues.put("theme_type", Integer.valueOf(draftBean.theme_type));
            contentValues.put("theme_name", draftBean.theme_name);
            contentValues.put(b.c, draftBean.tid);
            contentValues.put("landuri", draftBean.landuri);
            contentValues.put("isWatermark", Boolean.valueOf(draftBean.isWatermark));
            contentValues.put("tags", draftBean.plateDataStr);
            contentValues.put(IndexEntry.COLUMN_NAME_WIDTH, draftBean.width);
            contentValues.put(IndexEntry.COLUMN_NAME_HEIGHT, draftBean.height);
            contentValues.put("linkurl", draftBean.linkurl);
            contentValues.put("votes", draftBean.voteDataStr);
            this.c.insert("draft", null, contentValues);
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
            d();
        }
    }

    public DraftBean a(String str) {
        DraftBean draftBean;
        synchronized (c.a) {
            String[] strArr = new String[]{"id", HistoryOpenHelper.COLUMN_UID, "state", "bimage", "voice", "content", "bvoiceid", "voicetime", "createTime", "reserve", "video", "videotime", "theme_id", "theme_type", "theme_name", b.c, "landuri", "isWatermark", "tags", "votes"};
            String[] strArr2 = new String[]{str};
            c();
            Cursor query = this.c.query("draft", strArr, "createTime=?", strArr2, null, null, null);
            if (query.getCount() != 0) {
                query.moveToFirst();
                draftBean = new DraftBean();
                try {
                    draftBean.id = query.getInt(0);
                    draftBean.uid = query.getInt(1);
                    draftBean.state = query.getInt(2);
                    draftBean.bimage = query.getString(3);
                    draftBean.voice = query.getString(4);
                    draftBean.content = query.getString(5);
                    draftBean.bvoiceid = query.getInt(6);
                    draftBean.voicetime = query.getInt(7);
                    draftBean.createTime = query.getString(8);
                    draftBean.reserve = query.getString(9);
                    draftBean.video = query.getString(10);
                    draftBean.videotime = query.getInt(11);
                    draftBean.theme_id = query.getInt(12);
                    draftBean.theme_type = query.getInt(13);
                    draftBean.theme_name = query.getString(14);
                    draftBean.tid = query.getString(15);
                    draftBean.landuri = query.getString(16);
                    int i = query.getInt(17);
                    if (i == 0) {
                        draftBean.isWatermark = false;
                    } else if (i == 1) {
                        draftBean.isWatermark = true;
                    }
                    draftBean.plateDataStr = query.getString(18);
                    Object string = query.getString(19);
                    if (!TextUtils.isEmpty(string)) {
                        VoteData voteData = new VoteData();
                        voteData.votes = new ArrayList();
                        strArr2 = string.split(",");
                        for (String str2 : strArr2) {
                            Vote vote = new Vote();
                            vote.name = str2;
                            voteData.votes.add(vote);
                        }
                        draftBean.voteData = voteData;
                    }
                } catch (Exception e) {
                }
            } else {
                draftBean = null;
            }
            query.close();
            d();
        }
        return draftBean;
    }

    public ArrayList<DraftBean> b(String str) {
        ArrayList<DraftBean> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", HistoryOpenHelper.COLUMN_UID, "state", "bimage", "voice", "content", "bvoiceid", "voicetime", "createTime", "reserve", "video", "videotime", "theme_id", "theme_type", "theme_name", b.c, "landuri", "isWatermark", "tags", IndexEntry.COLUMN_NAME_WIDTH, IndexEntry.COLUMN_NAME_HEIGHT, "linkurl", "votes"};
            c();
            Cursor query = this.c.query("draft", strArr, null, null, null, null, str, null);
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    DraftBean draftBean = new DraftBean();
                    try {
                        draftBean.id = query.getInt(0);
                        draftBean.uid = query.getInt(1);
                        draftBean.state = query.getInt(2);
                        draftBean.bimage = query.getString(3);
                        draftBean.voice = query.getString(4);
                        draftBean.content = query.getString(5);
                        draftBean.bvoiceid = query.getInt(6);
                        draftBean.voicetime = query.getInt(7);
                        draftBean.createTime = query.getString(8);
                        draftBean.reserve = query.getString(9);
                        draftBean.video = query.getString(10);
                        draftBean.videotime = query.getInt(11);
                        if (TextUtils.isEmpty(draftBean.bimage) || !draftBean.bimage.endsWith(".gif")) {
                            draftBean.isGif = false;
                        } else {
                            draftBean.isGif = true;
                        }
                        draftBean.theme_id = query.getInt(12);
                        draftBean.theme_type = query.getInt(13);
                        draftBean.theme_name = query.getString(14);
                        draftBean.tid = query.getString(15);
                        draftBean.landuri = query.getString(16);
                        int i = query.getInt(17);
                        if (i == 0) {
                            draftBean.isWatermark = false;
                        } else if (i == 1) {
                            draftBean.isWatermark = true;
                        }
                        draftBean.plateDataStr = query.getString(18);
                        draftBean.width = query.getString(19);
                        draftBean.height = query.getString(20);
                        draftBean.linkurl = query.getString(21);
                        Object string = query.getString(22);
                        if (!TextUtils.isEmpty(string)) {
                            VoteData voteData = new VoteData();
                            voteData.votes = new ArrayList();
                            String[] split = string.split(",");
                            for (String str2 : split) {
                                Vote vote = new Vote();
                                vote.name = str2;
                                voteData.votes.add(vote);
                            }
                            draftBean.voteData = voteData;
                        }
                        arrayList.add(draftBean);
                    } catch (Exception e) {
                    }
                }
            }
            query.close();
            d();
        }
        return arrayList;
    }

    public ArrayList<String> a(int i) {
        ArrayList<String> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            Cursor query = this.c.query("draft", new String[]{b.c}, "state = ?", new String[]{String.valueOf(i)}, null, null, null, null);
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    try {
                        CharSequence string = query.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(string);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            query.close();
        }
        return arrayList;
    }

    public void a(int i, String str) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", Integer.valueOf(i));
            c();
            this.c.update("draft", contentValues, "createTime=" + str, null);
            d();
        }
    }

    public void a(String str, String str2, String str3) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(b.c, str2);
            contentValues.put("landuri", str3);
            contentValues.put("linkurl", "");
            c();
            this.c.update("draft", contentValues, "createTime=" + str, null);
            d();
        }
    }

    public void a(int i, List<TouGaoItem> list) {
        synchronized (c.a) {
            if (list != null) {
                if (list.size() > 0) {
                    String str;
                    c();
                    ArrayList a = a(i);
                    ArrayList arrayList = new ArrayList();
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        str = (String) it.next();
                        TouGaoItem touGaoItem = new TouGaoItem();
                        touGaoItem.setDataId(str);
                        if (list.contains(touGaoItem)) {
                            arrayList.add(str);
                        }
                    }
                    if (arrayList.size() > 0) {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            str = (String) it2.next();
                            if (i == 1) {
                                d(str);
                            }
                            this.c.delete("draft", "state = ? and tid = ?", new String[]{String.valueOf(i), str});
                        }
                    }
                    d();
                }
            }
        }
    }

    public void a() {
        synchronized (c.a) {
            c();
            this.c.delete("draft", "linkurl != \"\"", new String[0]);
            d();
        }
    }

    private void d(String str) {
        aa.a("sendImage", "deleteImgAndVideoCache()   ");
        Cursor query = this.c.query("draft", new String[]{"bimage", "video"}, "state = ? and tid = ?", new String[]{"1", str}, null, null, null);
        while (query.moveToNext()) {
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("bimage"));
            String string2 = query.getString(query.getColumnIndex("video"));
            aa.a("sendImage", "bimageString:   " + string);
            aa.a("sendImage", "videoString:   " + string2);
            try {
                if (TextUtils.isEmpty(string) && string.contains("-change.")) {
                    new File(string).delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (!TextUtils.isEmpty(string2) && string2.contains("BDJ_VIDEO/complete_video")) {
                    new File(string2).delete();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        query.close();
    }

    public void c(String str) {
        synchronized (c.a) {
            c();
            Cursor query = this.c.query("draft", new String[]{b.c}, "createTime = ?", new String[]{str}, null, null, null);
            Object obj = "";
            while (query.moveToNext()) {
                query.moveToFirst();
                obj = query.getString(query.getColumnIndex(b.c));
            }
            query.close();
            if (!TextUtils.isEmpty(obj)) {
                d(obj);
            }
            this.c.delete("draft", "createTime = " + str, null);
            d();
        }
    }

    public void b() {
        synchronized (c.a) {
            c();
            this.c.delete("draft", null, null);
            d();
        }
    }
}
