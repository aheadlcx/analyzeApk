package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alipay.sdk.util.h;
import com.budejie.www.bean.UserItem;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.type.UpdateUserInfo;
import com.tencent.connect.common.Constants;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class m {
    Context a;
    SQLiteDatabase b;
    c c;
    private SharedPreferences d = this.a.getSharedPreferences("weiboprefer", 0);

    public m(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void a() {
        this.b = this.c.getWritableDatabase();
    }

    private void b() {
        this.b.close();
    }

    public String a(String str) {
        String stringBuffer;
        synchronized (c.a) {
            StringBuffer stringBuffer2 = new StringBuffer();
            if (!TextUtils.isEmpty(str)) {
                a();
                Cursor query = this.b.query("weibo", new String[]{"sina", "tenct", "kongjian"}, "uid=" + str, null, null, null, null);
                if (query.moveToFirst()) {
                    stringBuffer2.append(query.getString(0) + h.b);
                    stringBuffer2.append(query.getString(1) + h.b);
                    stringBuffer2.append(query.getString(2));
                }
                query.close();
                b();
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(java.lang.String r12) {
        /*
        r11 = this;
        r8 = 1;
        r9 = 0;
        r10 = com.budejie.www.c.c.a;
        monitor-enter(r10);
        r0 = android.text.TextUtils.isEmpty(r12);	 Catch:{ all -> 0x0052 }
        if (r0 != 0) goto L_0x004f;
    L_0x000b:
        r11.a();	 Catch:{ all -> 0x0052 }
        r0 = r11.b;	 Catch:{ all -> 0x0052 }
        r1 = "weibo";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ all -> 0x0052 }
        r3 = 0;
        r4 = "phone";
        r2[r3] = r4;	 Catch:{ all -> 0x0052 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0052 }
        r3.<init>();	 Catch:{ all -> 0x0052 }
        r4 = "uid=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0052 }
        r3 = r3.append(r12);	 Catch:{ all -> 0x0052 }
        r3 = r3.toString();	 Catch:{ all -> 0x0052 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0052 }
        r1 = r0.moveToFirst();	 Catch:{ all -> 0x0052 }
        if (r1 == 0) goto L_0x0049;
    L_0x003b:
        r1 = 0;
        r1 = r0.getString(r1);	 Catch:{ all -> 0x0052 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0052 }
        if (r1 != 0) goto L_0x0049;
    L_0x0046:
        monitor-exit(r10);	 Catch:{ all -> 0x0052 }
        r0 = r8;
    L_0x0048:
        return r0;
    L_0x0049:
        r0.close();	 Catch:{ all -> 0x0052 }
        r11.b();	 Catch:{ all -> 0x0052 }
    L_0x004f:
        monitor-exit(r10);	 Catch:{ all -> 0x0052 }
        r0 = r9;
        goto L_0x0048;
    L_0x0052:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0052 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.c.m.b(java.lang.String):boolean");
    }

    public String c(String str) {
        String str2;
        synchronized (c.a) {
            String str3 = "";
            if (TextUtils.isEmpty(str)) {
                str2 = str3;
            } else {
                a();
                Cursor query = this.b.query("weibo", new String[]{"data1"}, "uid=" + str, null, null, null, null);
                if (query.moveToFirst()) {
                    str2 = query.getString(query.getColumnIndex("data1"));
                    if (str2 == null) {
                        str2 = "0";
                    }
                } else {
                    str2 = str3;
                }
                query.close();
                b();
            }
        }
        return str2;
    }

    public void a(String str, Map<String, String> map) {
        this.d.edit().putString("sex", (String) map.get("sex")).commit();
        synchronized (c.a) {
            if (!TextUtils.isEmpty(str)) {
                boolean d = d(str);
                ContentValues contentValues = new ContentValues();
                contentValues.put(HistoryOpenHelper.COLUMN_UID, str);
                contentValues.put("sex", (String) map.get("sex"));
                contentValues.put("name", (String) map.get(HistoryOpenHelper.COLUMN_USERNAME));
                contentValues.put("portrait", (String) map.get(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY));
                contentValues.put("introduction", (String) map.get("introduction"));
                contentValues.put("follow_count", (String) map.get("follow_count"));
                contentValues.put("fans_count", (String) map.get("fans_count"));
                Log.i("qq_uid", (String) map.get("qq_uid"));
                Log.i("qq_token", (String) map.get("qq_token"));
                if (TextUtils.isEmpty((CharSequence) map.get("qq_uid")) || TextUtils.isEmpty((CharSequence) map.get("qq_token"))) {
                    contentValues.put("tenct", "");
                } else {
                    contentValues.put("tenct", "qq_uid=" + ((String) map.get("qq_uid")) + ";qq_token=" + ((String) map.get("qq_token")));
                }
                if (TextUtils.isEmpty((CharSequence) map.get("weibo_uid")) || TextUtils.isEmpty((CharSequence) map.get("weibo_token"))) {
                    contentValues.put("sina", "");
                } else {
                    contentValues.put("sina", "weibo_uid=" + ((String) map.get("weibo_uid")) + ";weibo_token=" + ((String) map.get("weibo_token")));
                }
                if (TextUtils.isEmpty((CharSequence) map.get("qzone_uid")) || TextUtils.isEmpty((CharSequence) map.get("qzone_token"))) {
                    contentValues.put("kongjian", "");
                } else {
                    contentValues.put("kongjian", "qzone_uid=" + ((String) map.get("qzone_uid")) + ";qzone_token=" + ((String) map.get("qzone_token")));
                }
                contentValues.put("phone", (String) map.get("phone"));
                contentValues.put("grade", (String) map.get("grade"));
                contentValues.put("Is_black_user", (String) map.get("Is_black_user"));
                contentValues.put("Is_black_device", (String) map.get("Is_black_device"));
                contentValues.put("qq", (String) map.get("qq"));
                contentValues.put("degree", (String) map.get("degree"));
                contentValues.put("birthday", (String) map.get("birthday"));
                contentValues.put("tiezi_count", (String) map.get("tiezi_count"));
                contentValues.put(HistoryOpenHelper.COLUMN_LEVEL, (String) map.get(HistoryOpenHelper.COLUMN_LEVEL));
                a();
                if (d) {
                    this.b.update("weibo", contentValues, "uid=" + str, null);
                } else {
                    this.b.insert("weibo", null, contentValues);
                }
                b();
            }
        }
    }

    public void a(String str, long j) {
        synchronized (c.a) {
            boolean d = d(str);
            ContentValues contentValues = new ContentValues();
            contentValues.put(HistoryOpenHelper.COLUMN_UID, str);
            contentValues.put("data1", Long.valueOf(j));
            a();
            if (d) {
                this.b.update("weibo", contentValues, "uid=" + str, null);
            } else {
                this.b.insert("weibo", null, contentValues);
            }
            b();
        }
    }

    public boolean d(String str) {
        boolean z;
        synchronized (c.a) {
            a();
            Cursor query = this.b.query("weibo", new String[]{"id"}, "uid=" + str, null, null, null, null);
            if (query.moveToFirst()) {
                z = true;
            } else {
                z = false;
            }
            query.close();
        }
        return z;
    }

    public UserItem e(String str) {
        synchronized (c.a) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            UserItem userItem = new UserItem();
            a();
            Cursor query = this.b.query("weibo", new String[]{"name,sex,portrait,introduction,background_image,follow_count,fans_count,bookmark,phone,birthday,qq,level,credit,experience,sina_v,jie_v,v_desc,age_group,degree,trade_history,trade_ruler,grade,Is_black_user,Is_black_device,tiezi_count,sina,tenct,kongjian"}, "uid=" + str, null, null, null, null);
            if (query.moveToFirst()) {
                userItem.setId(str);
                userItem.setProfile(query.getString(query.getColumnIndex("portrait")));
                userItem.setSex(query.getString(query.getColumnIndex("sex")));
                userItem.setName(query.getString(query.getColumnIndex("name")));
                userItem.setInstroduce(query.getString(query.getColumnIndex("introduction")));
                userItem.setBackgroundImage(query.getString(query.getColumnIndex("background_image")));
                userItem.setFollowCount(query.getString(query.getColumnIndex("follow_count")));
                userItem.setFansCount(query.getString(query.getColumnIndex("fans_count")));
                userItem.setBookmark(query.getString(query.getColumnIndex("bookmark")));
                userItem.setPhone(query.getString(query.getColumnIndex("phone")));
                userItem.setBirthday(query.getString(query.getColumnIndex("birthday")));
                userItem.setQQ(query.getString(query.getColumnIndex("qq")));
                userItem.setLevel(query.getString(query.getColumnIndex(HistoryOpenHelper.COLUMN_LEVEL)));
                userItem.setCredit(query.getString(query.getColumnIndex("credit")));
                userItem.setExperience(query.getString(query.getColumnIndex("experience")));
                userItem.setSina_v(query.getString(query.getColumnIndex("sina_v")));
                userItem.setJie_v(query.getString(query.getColumnIndex("jie_v")));
                userItem.setV_desc(query.getString(query.getColumnIndex("v_desc")));
                userItem.setAge_group(query.getString(query.getColumnIndex("age_group")));
                userItem.setDegree(query.getString(query.getColumnIndex("degree")));
                userItem.setTrade_history(query.getString(query.getColumnIndex("trade_history")));
                userItem.setTrade_ruler(query.getString(query.getColumnIndex("trade_ruler")));
                userItem.setGrade(query.getString(query.getColumnIndex("grade")));
                userItem.setIs_black_user(query.getString(query.getColumnIndex("Is_black_user")));
                userItem.setIs_black_device(query.getString(query.getColumnIndex("Is_black_device")));
                userItem.setTiezi_count(query.getString(query.getColumnIndex("tiezi_count")));
                userItem.setToken(NetWorkUtil.a(this.a, "j"));
            }
            query.close();
            b();
            return userItem;
        }
    }

    public int f(String str) {
        int i = 0;
        synchronized (c.a) {
            if (TextUtils.isEmpty(str)) {
                return i;
            }
            int parseInt;
            a();
            Cursor query = this.b.query("weibo", new String[]{"follow_count"}, "uid=" + str, null, null, null, null);
            if (query.moveToFirst()) {
                try {
                    parseInt = Integer.parseInt(query.getString(query.getColumnIndex("follow_count")));
                } catch (NumberFormatException e) {
                    parseInt = i;
                }
            } else {
                parseInt = i;
            }
            query.close();
            b();
            return parseInt;
        }
    }

    public void a(String str, String str2, String str3) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            if ("portrait".equals(str)) {
                contentValues.put("portrait", str2);
            } else if ("name".equals(str)) {
                contentValues.put("name", str2);
            } else if ("sex".equals(str)) {
                contentValues.put("sex", str2);
            } else if ("sina".equals(str)) {
                contentValues.put("sina", "");
            } else if ("tencent".equals(str)) {
                contentValues.put("tenct", "");
            } else if (Constants.SOURCE_QZONE.equals(str)) {
                contentValues.put("kongjian", "");
            } else if ("introduction".equals(str)) {
                contentValues.put("introduction", str2);
            } else if ("background_image".equals(str)) {
                contentValues.put("background_image", str2);
            } else if ("follow_count".equals(str)) {
                contentValues.put("follow_count", str2);
            } else if ("fans_count".equals(str)) {
                contentValues.put("fans_count", str2);
            } else if ("bookmark".equals(str)) {
                contentValues.put("bookmark", str2);
            } else if ("phone".equals(str)) {
                contentValues.put("phone", str2);
            } else if ("birthday".equals(str)) {
                contentValues.put("birthday", str2);
            } else if ("degree".equals(str)) {
                contentValues.put("degree", str2);
            } else if ("age_group".equals(str)) {
                contentValues.put("age_group", str2);
            } else if ("qq".equals(str)) {
                contentValues.put("qq", str2);
            } else if ("tiezi_count".equals(str)) {
                contentValues.put("tiezi_count", str2);
            }
            a();
            this.b.update("weibo", contentValues, "uid=" + str3, null);
            b();
        }
    }

    public void a(UpdateUserInfo updateUserInfo, String str) {
        synchronized (c.a) {
            a();
            ContentValues contentValues = new ContentValues();
            Object fansCount = updateUserInfo.getFansCount();
            if (TextUtils.isEmpty(fansCount)) {
                contentValues.put("fans_count", "0");
            } else {
                contentValues.put("fans_count", fansCount);
            }
            fansCount = updateUserInfo.getFollowCount();
            if (TextUtils.isEmpty(fansCount)) {
                contentValues.put("follow_count", "0");
            } else {
                contentValues.put("follow_count", fansCount);
            }
            fansCount = updateUserInfo.getProfileImage();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("portrait", fansCount);
            }
            fansCount = updateUserInfo.getSex();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("sex", fansCount);
            }
            fansCount = updateUserInfo.getBookmark();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("bookmark", fansCount);
            }
            fansCount = updateUserInfo.getPhone();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("phone", fansCount);
            }
            fansCount = updateUserInfo.getLevel();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put(HistoryOpenHelper.COLUMN_LEVEL, fansCount);
            }
            fansCount = updateUserInfo.getCredit();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("credit", fansCount);
            }
            fansCount = updateUserInfo.getExperience();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("experience", fansCount);
            }
            fansCount = updateUserInfo.getSina_v();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("sina_v", fansCount);
            }
            fansCount = updateUserInfo.getJie_v();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("jie_v", fansCount);
            }
            fansCount = updateUserInfo.getV_desc();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("v_desc", fansCount);
            }
            fansCount = updateUserInfo.getAge_group();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("age_group", fansCount);
            }
            fansCount = updateUserInfo.getDegree();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("degree", fansCount);
            }
            fansCount = updateUserInfo.getQq();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("qq", fansCount);
            }
            fansCount = updateUserInfo.getBirthday();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("birthday", fansCount);
            }
            fansCount = updateUserInfo.getTrade_history();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("trade_history", fansCount);
            }
            CharSequence trade_ruler = updateUserInfo.getTrade_ruler();
            if (!TextUtils.isEmpty(trade_ruler)) {
                contentValues.put("trade_ruler", trade_ruler);
            }
            String introduction = updateUserInfo.getIntroduction();
            if (!TextUtils.isEmpty(trade_ruler)) {
                contentValues.put("introduction", introduction);
            }
            fansCount = updateUserInfo.getGrade();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("grade", fansCount);
            }
            fansCount = updateUserInfo.getIs_black_user();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("Is_black_user", fansCount);
            }
            fansCount = updateUserInfo.getIs_black_device();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("Is_black_device", fansCount);
            }
            fansCount = updateUserInfo.getTiezi_count();
            if (!TextUtils.isEmpty(fansCount)) {
                contentValues.put("tiezi_count", fansCount);
            }
            if (d(str)) {
                this.b.update("weibo", contentValues, "uid=" + str, null);
            } else {
                this.b.insert("weibo", null, contentValues);
            }
            b();
        }
    }
}
