package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.r;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.a;

public class be {
    private static String a = (d.a(5) + "-");
    private static AtomicLong b = new AtomicLong(0);

    public static String a() {
        return a + b.incrementAndGet();
    }

    public static ArrayList<ai> a(List<f> list, String str, String str2, int i) {
        if (list == null) {
            b.d("requests can not be null in TinyDataHelper.transToThriftObj().");
            return null;
        } else if (list.size() == 0) {
            b.d("requests.length is 0 in TinyDataHelper.transToThriftObj().");
            return null;
        } else {
            ArrayList<ai> arrayList = new ArrayList();
            int i2 = 0;
            a eVar = new e();
            for (int i3 = 0; i3 < list.size(); i3++) {
                f fVar = (f) list.get(i3);
                if (fVar != null) {
                    int length = au.a(fVar).length;
                    if (length > i) {
                        b.d("TinyData is too big, ignore upload request." + fVar.d());
                    } else {
                        if (i2 + length > i) {
                            ai aiVar = new ai(a(), false);
                            aiVar.d(str);
                            aiVar.b(str2);
                            aiVar.c(r.UploadTinyData.W);
                            aiVar.a(com.xiaomi.channel.commonutils.file.a.a(au.a(eVar)));
                            arrayList.add(aiVar);
                            eVar = new e();
                            i2 = 0;
                        }
                        eVar.a(fVar);
                        i2 += length;
                    }
                }
            }
            if (eVar.a() != 0) {
                ai aiVar2 = new ai(a(), false);
                aiVar2.d(str);
                aiVar2.b(str2);
                aiVar2.c(r.UploadTinyData.W);
                aiVar2.a(com.xiaomi.channel.commonutils.file.a.a(au.a(eVar)));
                arrayList.add(aiVar2);
            }
            return arrayList;
        }
    }

    public static void a(Context context, String str, String str2, long j, String str3) {
        f fVar = new f();
        fVar.d(str);
        fVar.c(str2);
        fVar.a(j);
        fVar.b(str3);
        fVar.a("push_sdk_channel");
        fVar.g(context.getPackageName());
        fVar.e(context.getPackageName());
        fVar.c(true);
        fVar.b(System.currentTimeMillis());
        fVar.f(a());
        bf.a(context, fVar);
    }

    public static boolean a(f fVar, boolean z) {
        if (fVar == null) {
            b.a("item is null, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!z && TextUtils.isEmpty(fVar.a)) {
            b.a("item.channel is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (TextUtils.isEmpty(fVar.g)) {
            b.a("item.category is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (TextUtils.isEmpty(fVar.c)) {
            b.a("item.name is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!d.d(fVar.g)) {
            b.a("item.category can only contain ascii char, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!d.d(fVar.c)) {
            b.a("item.name can only contain ascii char, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (fVar.b == null || fVar.b.length() <= 10240) {
            return false;
        } else {
            b.a("item.data is too large(" + fVar.b.length() + "), max size for data is " + 10240 + " , verfiy ClientUploadDataItem failed.");
            return true;
        }
    }
}
