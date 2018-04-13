package com.budejie.www.activity.image;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.MediaStore.Video;
import android.util.Log;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class a {
    private static a j;
    final String a = getClass().getSimpleName();
    Context b;
    ContentResolver c;
    HashMap<String, String> d = new HashMap();
    HashMap<String, String> e = new HashMap();
    HashMap<String, d> f = new HashMap();
    HashMap<String, d> g = new HashMap();
    boolean h = false;
    private boolean i;
    private int k = 40;
    private String l = "mp4";
    private Comparator m = new a$1(this);

    private a() {
    }

    public static a a() {
        if (j == null) {
            j = new a();
        }
        return j;
    }

    public void a(Context context) {
        this.b = context;
        this.c = context.getContentResolver();
    }

    public void a(Context context, boolean z) {
        this.b = context;
        this.i = z;
        this.c = context.getContentResolver();
    }

    void b() {
        Log.d(this.a, "buildBucketList imageAndVideo=" + this.i);
        long currentTimeMillis = System.currentTimeMillis();
        c();
        d();
        e();
        this.h = true;
        Log.d(this.a, "use time: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    private void c() {
        d(false);
        if (this.i) {
            d(true);
        }
    }

    private void d(boolean z) {
        String str;
        String str2;
        String str3;
        Uri uri;
        Uri uri2 = Thumbnails.EXTERNAL_CONTENT_URI;
        String str4 = HistoryOpenHelper.COLUMN_ID;
        String str5 = "image_id";
        String str6 = "_data";
        if (z) {
            uri2 = Video.Thumbnails.EXTERNAL_CONTENT_URI;
            str = "_data";
            str2 = "video_id";
            str3 = HistoryOpenHelper.COLUMN_ID;
            uri = uri2;
        } else {
            str = str6;
            str2 = str5;
            str3 = str4;
            uri = uri2;
        }
        Cursor query = this.c.query(uri, new String[]{str3, str2, str}, null, null, null);
        if (query != null && query.moveToFirst()) {
            int columnIndex = query.getColumnIndex(str3);
            int columnIndex2 = query.getColumnIndex(str2);
            int columnIndex3 = query.getColumnIndex(str);
            do {
                query.getInt(columnIndex);
                int i = query.getInt(columnIndex2);
                b(z).put("" + i, query.getString(columnIndex3));
            } while (query.moveToNext());
        }
    }

    private void d() {
        e(false);
        if (this.i) {
            e(true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e(boolean r26) {
        /*
        r25 = this;
        r9 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        r8 = "_id";
        r7 = "bucket_id";
        r6 = "bucket_display_name";
        r5 = "_data";
        r4 = "mime_type";
        r3 = "date_modified";
        r2 = "_size";
        if (r26 == 0) goto L_0x0191;
    L_0x0012:
        r9 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        r8 = "_id";
        r7 = "bucket_id";
        r6 = "bucket_display_name";
        r5 = "_data";
        r4 = "mime_type";
        r3 = "date_modified";
        r2 = "_size";
        r10 = r4;
        r11 = r5;
        r12 = r6;
        r13 = r7;
        r14 = r8;
        r8 = r2;
        r24 = r3;
        r3 = r9;
        r9 = r24;
    L_0x002d:
        r2 = 7;
        r4 = new java.lang.String[r2];
        r2 = 0;
        r4[r2] = r14;
        r2 = 1;
        r4[r2] = r13;
        r2 = 2;
        r4[r2] = r12;
        r2 = 3;
        r4[r2] = r11;
        r2 = 4;
        r4[r2] = r10;
        r2 = 5;
        r4[r2] = r9;
        r2 = 6;
        r4[r2] = r8;
        r0 = r25;
        r2 = r0.c;
        r5 = 0;
        r6 = 0;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r7 = r7.append(r9);
        r15 = " desc";
        r7 = r7.append(r15);
        r7 = r7.toString();
        r4 = r2.query(r3, r4, r5, r6, r7);
        if (r4 != 0) goto L_0x0065;
    L_0x0064:
        return;
    L_0x0065:
        r2 = r4.moveToFirst();
        if (r2 == 0) goto L_0x0103;
    L_0x006b:
        r5 = r4.getColumnIndexOrThrow(r14);
        r6 = r4.getColumnIndexOrThrow(r13);
        r7 = r4.getColumnIndexOrThrow(r12);
        r11 = r4.getColumnIndexOrThrow(r11);
        r10 = r4.getColumnIndexOrThrow(r10);
        r9 = r4.getColumnIndexOrThrow(r9);
        r8 = r4.getColumnIndexOrThrow(r8);
        r12 = new com.budejie.www.activity.image.d;
        r12.<init>();
        r2 = new java.util.ArrayList;
        r2.<init>();
        r12.c = r2;
        r0 = r25;
        r2 = r0.b;
        r3 = 2131232006; // 0x7f080506 float:1.808011E38 double:1.0529685175E-314;
        r2 = r2.getString(r3);
        r12.b = r2;
    L_0x00a0:
        r13 = r4.getString(r5);
        r3 = r4.getString(r6);
        r14 = r4.getString(r7);
        r15 = r4.getString(r11);
        r16 = r4.getString(r10);
        r18 = r4.getLong(r9);
        r2 = r4.getString(r8);
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ NumberFormatException -> 0x0185 }
        r17 = r2.intValue();	 Catch:{ NumberFormatException -> 0x0185 }
        r20 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r17 = r17 / r20;
        r0 = r17;
        r0 = (double) r0;	 Catch:{ NumberFormatException -> 0x0185 }
        r20 = r0;
        if (r26 == 0) goto L_0x0108;
    L_0x00cf:
        r0 = r25;
        r0 = r0.k;	 Catch:{ NumberFormatException -> 0x0185 }
        r17 = r0;
        r0 = r17;
        r0 = (double) r0;	 Catch:{ NumberFormatException -> 0x0185 }
        r22 = r0;
        r17 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
        if (r17 > 0) goto L_0x00f0;
    L_0x00de:
        r17 = android.text.TextUtils.isEmpty(r16);	 Catch:{ NumberFormatException -> 0x0185 }
        if (r17 != 0) goto L_0x0108;
    L_0x00e4:
        r0 = r25;
        r0 = r0.l;	 Catch:{ NumberFormatException -> 0x0185 }
        r17 = r0;
        r17 = r16.contains(r17);	 Catch:{ NumberFormatException -> 0x0185 }
        if (r17 != 0) goto L_0x0108;
    L_0x00f0:
        r2 = r4.moveToNext();
        if (r2 != 0) goto L_0x00a0;
    L_0x00f6:
        r2 = r12.a;
        if (r2 <= 0) goto L_0x0103;
    L_0x00fa:
        r2 = r25.a(r26);
        r3 = r12.b;
        r2.put(r3, r12);
    L_0x0103:
        r4.close();
        goto L_0x0064;
    L_0x0108:
        r2 = r2.intValue();	 Catch:{ NumberFormatException -> 0x0185 }
        r2 = r2 / 1024;
        r17 = 15;
        r0 = r17;
        if (r2 < r0) goto L_0x00f0;
    L_0x0114:
        r2 = r25.a(r26);
        r2 = r2.get(r3);
        r2 = (com.budejie.www.activity.image.d) r2;
        if (r2 != 0) goto L_0x018f;
    L_0x0120:
        r2 = new com.budejie.www.activity.image.d;
        r2.<init>();
        r17 = r25.a(r26);
        r0 = r17;
        r0.put(r3, r2);
        r3 = new java.util.ArrayList;
        r3.<init>();
        r2.c = r3;
        r2.b = r14;
        r3 = r2;
    L_0x0138:
        r2 = r3.b;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x014a;
    L_0x0140:
        r2 = r3.b;
        r14 = "cache";
        r2 = r2.equalsIgnoreCase(r14);
        if (r2 != 0) goto L_0x00f0;
    L_0x014a:
        r2 = r3.a;
        r2 = r2 + 1;
        r3.a = r2;
        r2 = r12.a;
        r2 = r2 + 1;
        r12.a = r2;
        r14 = new com.budejie.www.activity.image.ImageItem;
        r14.<init>();
        r14.imageId = r13;
        r14.imagePath = r15;
        r2 = r25.b(r26);
        r2 = r2.get(r13);
        r2 = (java.lang.String) r2;
        r14.thumbnailPath = r2;
        r0 = r16;
        r14.mimeType = r0;
        r0 = r18;
        r14.modifiedTime = r0;
        if (r26 == 0) goto L_0x018a;
    L_0x0175:
        r2 = "video";
        r14.type = r2;
    L_0x0179:
        r2 = r3.c;
        r2.add(r14);
        r2 = r12.c;
        r2.add(r14);
        goto L_0x00f0;
    L_0x0185:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0114;
    L_0x018a:
        r2 = "image";
        r14.type = r2;
        goto L_0x0179;
    L_0x018f:
        r3 = r2;
        goto L_0x0138;
    L_0x0191:
        r10 = r4;
        r11 = r5;
        r12 = r6;
        r13 = r7;
        r14 = r8;
        r8 = r2;
        r24 = r3;
        r3 = r9;
        r9 = r24;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.image.a.e(boolean):void");
    }

    public HashMap<String, d> a(boolean z) {
        if (z) {
            return this.f;
        }
        return this.g;
    }

    public HashMap<String, String> b(boolean z) {
        if (z) {
            return this.e;
        }
        return this.d;
    }

    private void e() {
        for (Entry key : this.f.entrySet()) {
            String str = (String) key.getKey();
            if (this.g.containsKey(str)) {
                d dVar = (d) this.g.get(str);
                d dVar2 = (d) this.f.get(str);
                dVar.a += dVar2.a;
                dVar.c.addAll(dVar2.c);
                Collections.sort(dVar.c, this.m);
                this.g.put(str, dVar);
            } else {
                this.g.put(str, this.f.get(str));
            }
        }
    }

    public List<d> c(boolean z) {
        if (z || !(z || this.h)) {
            this.d.clear();
            this.f.clear();
            this.g.clear();
            this.f.clear();
            b();
        }
        List<d> arrayList = new ArrayList();
        d dVar = (d) this.g.remove(this.b.getString(R.string.select_image_all_pic));
        if (dVar != null) {
            arrayList.add(dVar);
        }
        for (Entry value : this.g.entrySet()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }
}
