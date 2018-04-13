package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.c;
import com.xiaomi.xmpush.thrift.o;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class av {
    private static String a = (d.a(5) + Constants.ACCEPT_TIME_SEPARATOR_SERVER);
    private static AtomicLong b = new AtomicLong(0);

    public static class a implements com.xiaomi.push.service.aw.b {
        private final Context a;

        public a(Context context) {
            this.a = context;
        }

        public void a(ArrayList<b> arrayList) {
        }

        public boolean a(b bVar) {
            return com.xiaomi.channel.commonutils.network.d.d(this.a);
        }
    }

    public static class b {
        public String a;
        public int b;
        public com.xiaomi.xmpush.thrift.d c = new com.xiaomi.xmpush.thrift.d();

        public String toString() {
            return "TinyDataRequest:{id:" + this.a + ", uploadHint:" + this.b + ", channel:" + this.c.a + ", category:" + this.c.g + ", name:" + this.c.c + ", counter: " + this.c.d + ", data: " + this.c.b + ", fromSDK:" + this.c.f + ",  }";
        }
    }

    public static String a() {
        return a + b.incrementAndGet();
    }

    public static ArrayList<ae> a(ArrayList<b> arrayList, String str, String str2) {
        if (arrayList == null) {
            com.xiaomi.channel.commonutils.logger.b.d("requests can not be null in TinyDataHelper.transToThriftObj().");
            return null;
        } else if (arrayList.size() == 0) {
            com.xiaomi.channel.commonutils.logger.b.d("requests.length is 0 in TinyDataHelper.transToThriftObj().");
            return null;
        } else {
            ArrayList<ae> arrayList2 = new ArrayList();
            int i = 0;
            org.apache.thrift.a cVar = new c();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                b bVar = (b) arrayList.get(i2);
                if (!(bVar == null || bVar.c == null)) {
                    int length = aq.a(bVar.c).length;
                    if (length > 30720) {
                        com.xiaomi.channel.commonutils.logger.b.d("TinyData is too big, ignore upload request." + bVar.toString());
                    } else {
                        if (i + length > 30720) {
                            ae aeVar = new ae(a(), false);
                            aeVar.d(str);
                            aeVar.b(str2);
                            aeVar.c(o.UploadTinyData.N);
                            aeVar.a(com.xiaomi.channel.commonutils.file.a.a(aq.a(cVar)));
                            arrayList2.add(aeVar);
                            cVar = new c();
                            i = 0;
                        }
                        cVar.a(bVar.c);
                        i += length;
                    }
                }
            }
            if (cVar.a() != 0) {
                ae aeVar2 = new ae(a(), false);
                aeVar2.d(str);
                aeVar2.b(str2);
                aeVar2.c(o.UploadTinyData.N);
                aeVar2.a(com.xiaomi.channel.commonutils.file.a.a(aq.a(cVar)));
                arrayList2.add(aeVar2);
            }
            return arrayList2;
        }
    }

    public static boolean a(String str, String str2, long j, String str3) {
        if (str == null) {
            com.xiaomi.channel.commonutils.logger.b.a("Value of parameter category can not be null.");
            return true;
        } else if (str2 == null) {
            com.xiaomi.channel.commonutils.logger.b.a("Value of parameter Name can not be null");
            return true;
        } else if (!d.d(str)) {
            com.xiaomi.channel.commonutils.logger.b.a("Value of parameter catetory invalid, can only contain ascii char.");
            return true;
        } else if (!d.d(str2)) {
            com.xiaomi.channel.commonutils.logger.b.a("Value of parameter name invalid, can only contain ascii char.");
            return true;
        } else if (str3 == null || str3.length() <= 10240) {
            return false;
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("Parameter data is too large(" + str3.length() + "), max size for data is " + 10240);
            return true;
        }
    }
}
