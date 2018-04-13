package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.bugly.beta.download.DownloadTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p {
    public static p a = new p();

    public synchronized ContentValues a(String str) {
        ContentValues contentValues;
        if (TextUtils.isEmpty(str)) {
            contentValues = null;
        } else {
            Cursor a = ae.a().a("dl_1002", null, "_dUrl=?", new String[]{str}, null, true);
            if (a == null || !a.moveToFirst()) {
                contentValues = null;
            } else {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("_dUrl", a.getString(a.getColumnIndex("_dUrl")));
                contentValues2.put("_sFile", a.getString(a.getColumnIndex("_sFile")));
                contentValues2.put("_sLen", Long.valueOf(a.getLong(a.getColumnIndex("_sLen"))));
                contentValues2.put("_tLen", Long.valueOf(a.getLong(a.getColumnIndex("_tLen"))));
                contentValues2.put("_MD5", a.getString(a.getColumnIndex("_MD5")));
                contentValues2.put("_DLTIME", Long.valueOf(a.getLong(a.getColumnIndex("_DLTIME"))));
                contentValues = contentValues2;
            }
            if (a != null) {
                a.close();
            }
        }
        return contentValues;
    }

    public synchronized boolean a(DownloadTask downloadTask) {
        boolean z = true;
        synchronized (this) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_dUrl", downloadTask.getDownloadUrl());
            contentValues.put("_sFile", downloadTask.getSaveFile().getAbsolutePath());
            contentValues.put("_sLen", Long.valueOf(downloadTask.getSavedLength()));
            contentValues.put("_tLen", Long.valueOf(downloadTask.getTotalLength()));
            contentValues.put("_MD5", downloadTask.getMD5());
            contentValues.put("_DLTIME", Long.valueOf(downloadTask.getCostTime()));
            if (ae.a().a("dl_1002", contentValues, null, true) < 0) {
                z = false;
            }
        }
        return z;
    }

    public synchronized int b(DownloadTask downloadTask) {
        return ae.a().a("dl_1002", "_dUrl = ?", new String[]{downloadTask.getDownloadUrl()}, null, true);
    }

    public synchronized int b(String str) {
        return ae.a().a("dl_1002", "_sFile = ?", new String[]{str}, null, true);
    }

    public synchronized List<w> a() {
        List<w> arrayList;
        arrayList = new ArrayList();
        Cursor a = ae.a().a("ge_1002", null, null, null, null, true);
        while (a != null && a.moveToNext()) {
            w wVar = (w) ah.a(a.getBlob(a.getColumnIndex("_datas")), w.class);
            if (wVar != null) {
                arrayList.add(wVar);
            }
        }
        if (a != null) {
            a.close();
        }
        return arrayList.isEmpty() ? null : arrayList;
    }

    public synchronized boolean a(w wVar) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            if (wVar != null) {
                byte[] a = ah.a(wVar);
                if (a != null && a.length > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_time", Long.valueOf(wVar.b));
                    contentValues.put("_datas", a);
                    if (ae.a().a("ge_1002", contentValues, null, true) < 0) {
                        z = false;
                    }
                    z2 = z;
                }
            }
        }
        return z2;
    }

    public synchronized int b() {
        return ae.a().a("ge_1002", null, null, null, true);
    }

    public synchronized boolean a(int i, String str, byte[] bArr) {
        boolean z = true;
        synchronized (this) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", Integer.valueOf(i));
            contentValues.put("_tm", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("_tp", str);
            contentValues.put("_dt", bArr);
            if (ae.a().a("st_1002", contentValues, null, true) < 0) {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean c(String str) {
        return ae.a().a("st_1002", "_id = ? and _tp = ? ", new String[]{"1002", str}, null, true) > 0;
    }

    public synchronized Map<String, byte[]> c() {
        Cursor a;
        Map<String, byte[]> map;
        Throwable th;
        try {
            a = ae.a().a("st_1002", null, "_id = 1002", null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                map = null;
            } else {
                try {
                    map = new HashMap();
                    while (a.moveToNext()) {
                        map.put(a.getString(a.getColumnIndex("_tp")), a.getBlob(a.getColumnIndex("_dt")));
                    }
                    if (a != null) {
                        a.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!an.a(th)) {
                            th.printStackTrace();
                        }
                        if (a != null) {
                            a.close();
                        }
                        map = null;
                        return map;
                    } catch (Throwable th3) {
                        th = th3;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (Throwable th4) {
            th = th4;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return map;
    }
}
