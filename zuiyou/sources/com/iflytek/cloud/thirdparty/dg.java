package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public abstract class dg {
    protected static final String[] a = new String[]{"number", "name", FFmpegMediaMetadataRetriever.METADATA_KEY_DATE};
    protected static String[] c;
    protected Context b = null;

    public dg(Context context) {
        this.b = context;
    }

    public abstract Uri a();

    protected void a(Context context) {
        c = new String[100];
        c[0] = "其他";
        c[1] = "住宅";
        c[2] = "手机";
        c[3] = "工作";
        c[4] = "工作传真";
        c[5] = "住宅传真";
        c[6] = "寻呼机";
        c[7] = "其他";
        c[9] = "SIM卡";
        for (int i = 10; i < c.length; i++) {
            c[i] = "自定义电话";
        }
    }

    protected abstract String[] b();

    protected abstract String c();

    public HashMap<String, String> d() {
        Throwable e;
        String[] b = b();
        HashMap<String, String> hashMap = new HashMap();
        Cursor query;
        try {
            query = this.b.getContentResolver().query(a(), b, null, null, c());
            if (query == null) {
                try {
                    cb.a("iFly_ContactManager", "queryContacts------cursor is null");
                } catch (Exception e2) {
                    e = e2;
                }
            } else if (query.getCount() == 0) {
                cb.a("iFly_ContactManager", "queryContacts------cursor getCount == 0");
            } else {
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex(b[0]));
                    String string2 = query.getString(query.getColumnIndex(b[1]));
                    if (string != null) {
                        hashMap.put(string2, string);
                    }
                }
                cb.a("iFly_ContactManager", "queryContacts_20------count = " + query.getCount());
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                cb.a(e);
                if (query != null) {
                    query.close();
                }
                return hashMap;
            } catch (Throwable th) {
                e = th;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
        return hashMap;
    }

    public List<dd> e() {
        Cursor query;
        Throwable e;
        Cursor cursor;
        List<dd> arrayList = new ArrayList();
        try {
            query = this.b.getContentResolver().query(Uri.parse("content://icc/adn"), null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        while (query.moveToNext()) {
                            String string = query.getString(query.getColumnIndex("name"));
                            String string2 = query.getString(query.getColumnIndex("_id"));
                            String a = bu.a(dc.a(query.getString(query.getColumnIndex("number"))));
                            if (string != null) {
                                arrayList.add(new dd(string2, string, null, a, null, c[9]));
                            }
                        }
                        cb.a("iFly_ContactManager", "querySIM-------count = " + query.getCount());
                        if (query != null) {
                            query.close();
                        }
                        return arrayList;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = query;
                    try {
                        cb.a(e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        e = th;
                        query = cursor;
                        if (query != null) {
                            query.close();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw e;
                }
            }
            cb.a("iFly_ContactManager", "querySIM-------cursor getCount = 0 or cursor is null");
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            cb.a(e);
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            e = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
        return arrayList;
    }
}
