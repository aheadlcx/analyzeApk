package cn.xiaochuankeji.tieba.network.custom.a;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.network.b;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.network.e;
import com.iflytek.cloud.SpeechConstant;
import com.qq.e.comm.constants.ErrorCode$AdError;
import com.qq.e.comm.constants.ErrorCode$NetWorkError;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.net.ssl.SSLException;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.internal.b.f;
import okhttp3.internal.b.l;
import okhttp3.internal.c;
import okhttp3.t;
import okhttp3.y;
import okhttp3.y$a;

public class a implements t {
    private static a a;
    private HashMap<String, a> b = new HashMap();
    private HashMap<String, a> c = new HashMap();

    private static class a {
        private static boolean a;
        private Object b = new Object();
        private int c = 15;
        private long d;

        private static class a {
            y a;
            aa b;
            Exception c;
            long d;

            private a() {
            }
        }

        public a(boolean z) {
            a = z;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public okhttp3.aa a(okhttp3.t.a r19, okhttp3.y r20) throws java.io.IOException {
            /*
            r18 = this;
            r4 = 0;
            r2 = r20.a();
            r13 = r2.toString();
            r2 = new java.net.URL;
            r2.<init>(r13);
            r5 = r2.getHost();
            r2 = cn.xiaochuankeji.tieba.network.e.a();
            r14 = r2.d(r5);
            r0 = r18;
            r3 = r0.b;
            monitor-enter(r3);
            r0 = r18;
            r11 = r0.c;	 Catch:{ all -> 0x0073 }
            r0 = r18;
            r0 = r0.d;	 Catch:{ all -> 0x0073 }
            r16 = r0;
            monitor-exit(r3);	 Catch:{ all -> 0x0073 }
            r3 = 0;
            r8 = 0;
            r9 = r11;
        L_0x002d:
            r2 = 0;
            r6 = r9 & 1;
            r7 = 1;
            if (r6 != r7) goto L_0x0079;
        L_0x0033:
            r6 = r3 & 1;
            r7 = 1;
            if (r6 == r7) goto L_0x0079;
        L_0x0038:
            r0 = r18;
            r10 = r0.d(r5);
            r2 = android.text.TextUtils.isEmpty(r10);
            if (r2 != 0) goto L_0x01c1;
        L_0x0044:
            r12 = r3 | 1;
            r7 = r13.replace(r5, r10);
            r6 = 1;
            r2 = r18;
            r3 = r19;
            r4 = r20;
            r3 = r2.a(r3, r4, r5, r6, r7);
            r0 = r18;
            r2 = r0.a(r3);
            if (r2 == 0) goto L_0x0076;
        L_0x005d:
            r0 = r18;
            r2 = r0.a(r3, r14);
            if (r2 == 0) goto L_0x0076;
        L_0x0065:
            r2 = 1;
            r4 = r3;
            r3 = r8;
        L_0x0068:
            if (r4 != 0) goto L_0x016b;
        L_0x006a:
            r2 = new java.io.IOException;
            r3 = "lastResult == null";
            r2.<init>(r3);
            throw r2;
        L_0x0073:
            r2 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0073 }
            throw r2;
        L_0x0076:
            r2 = r10;
            r4 = r3;
            r3 = r12;
        L_0x0079:
            r6 = r9 & 2;
            r7 = 2;
            if (r6 != r7) goto L_0x00bc;
        L_0x007e:
            r6 = r3 & 2;
            r7 = 2;
            if (r6 == r7) goto L_0x00bc;
        L_0x0083:
            r0 = r18;
            r8 = r0.c(r5);
            r6 = android.text.TextUtils.isEmpty(r8);
            if (r6 != 0) goto L_0x00bc;
        L_0x008f:
            if (r2 == 0) goto L_0x0097;
        L_0x0091:
            r2 = r8.equals(r2);
            if (r2 != 0) goto L_0x00bc;
        L_0x0097:
            r10 = r3 | 2;
            r7 = r13.replace(r5, r8);
            r6 = 2;
            r2 = r18;
            r3 = r19;
            r4 = r20;
            r3 = r2.a(r3, r4, r5, r6, r7);
            r0 = r18;
            r2 = r0.a(r3);
            if (r2 == 0) goto L_0x01c4;
        L_0x00b0:
            r0 = r18;
            r2 = r0.a(r3, r14);
            if (r2 == 0) goto L_0x01c4;
        L_0x00b8:
            r2 = 2;
            r4 = r3;
            r3 = r8;
            goto L_0x0068;
        L_0x00bc:
            r10 = r8;
            r2 = r3;
            r3 = r4;
        L_0x00bf:
            r4 = r9 & 4;
            r6 = 4;
            if (r4 != r6) goto L_0x0112;
        L_0x00c4:
            r4 = r2 & 4;
            r6 = 4;
            if (r4 == r6) goto L_0x0112;
        L_0x00c9:
            r8 = r2 | 4;
            r6 = 4;
            r2 = r18;
            r3 = r19;
            r4 = r20;
            r7 = r13;
            r2 = r2.a(r3, r4, r5, r6, r7);
            r0 = r18;
            r3 = r0.a(r2);
            if (r3 == 0) goto L_0x00e4;
        L_0x00df:
            r3 = 4;
            r4 = r2;
            r2 = r3;
            r3 = r10;
            goto L_0x0068;
        L_0x00e4:
            r3 = "https";
            r3 = r13.startsWith(r3);
            if (r3 == 0) goto L_0x0110;
        L_0x00ed:
            r2 = "https";
            r3 = "http";
            r7 = r13.replace(r2, r3);
            r6 = 4;
            r2 = r18;
            r3 = r19;
            r4 = r20;
            r2 = r2.a(r3, r4, r5, r6, r7);
            r0 = r18;
            r3 = r0.a(r2);
            if (r3 == 0) goto L_0x0110;
        L_0x010a:
            r3 = 4;
            r4 = r2;
            r2 = r3;
            r3 = r10;
            goto L_0x0068;
        L_0x0110:
            r3 = r2;
            r2 = r8;
        L_0x0112:
            r4 = a;
            if (r4 == 0) goto L_0x0149;
        L_0x0116:
            r4 = r9 & 8;
            r6 = 8;
            if (r4 != r6) goto L_0x0149;
        L_0x011c:
            r4 = r2 & 8;
            r6 = 8;
            if (r4 == r6) goto L_0x0149;
        L_0x0122:
            r8 = r2 | 8;
            r2 = "https";
            r3 = "http";
            r7 = r13.replace(r2, r3);
            r6 = 8;
            r2 = r18;
            r3 = r19;
            r4 = r20;
            r3 = r2.a(r3, r4, r5, r6, r7);
            r0 = r18;
            r2 = r0.a(r3);
            if (r2 == 0) goto L_0x0148;
        L_0x0142:
            r2 = 8;
            r4 = r3;
            r3 = r10;
            goto L_0x0068;
        L_0x0148:
            r2 = r8;
        L_0x0149:
            r4 = 15;
            if (r9 != r4) goto L_0x0153;
        L_0x014d:
            r2 = 15;
            r4 = r3;
            r3 = r10;
            goto L_0x0068;
        L_0x0153:
            if (r14 == 0) goto L_0x0164;
        L_0x0155:
            r6 = java.lang.System.currentTimeMillis();
            r4 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1));
            if (r4 >= 0) goto L_0x0164;
        L_0x015d:
            if (r2 == 0) goto L_0x0164;
        L_0x015f:
            r2 = r11;
            r4 = r3;
            r3 = r10;
            goto L_0x0068;
        L_0x0164:
            r9 = 15;
            r8 = r10;
            r4 = r3;
            r3 = r2;
            goto L_0x002d;
        L_0x016b:
            r0 = r18;
            r0.a(r5, r2, r4);
            if (r9 == r2) goto L_0x0184;
        L_0x0172:
            r6 = r2 & 2;
            r7 = 2;
            if (r6 == r7) goto L_0x0184;
        L_0x0177:
            r6 = android.text.TextUtils.isEmpty(r3);
            if (r6 != 0) goto L_0x0184;
        L_0x017d:
            r6 = cn.xiaochuankeji.tieba.network.e.a();
            r6.a(r5, r3);
        L_0x0184:
            if (r14 == 0) goto L_0x019d;
        L_0x0186:
            r3 = 15;
            if (r2 != r3) goto L_0x019d;
        L_0x018a:
            r0 = r18;
            r3 = r0.b;
            monitor-enter(r3);
            r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x01b8 }
            r10 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
            r6 = r6 + r10;
            r0 = r18;
            r0.d = r6;	 Catch:{ all -> 0x01b8 }
            monitor-exit(r3);	 Catch:{ all -> 0x01b8 }
            r2 = r9;
        L_0x019d:
            if (r9 == r2) goto L_0x01a9;
        L_0x019f:
            r0 = r18;
            r3 = r0.b;
            monitor-enter(r3);
            r0 = r18;
            r0.c = r2;	 Catch:{ all -> 0x01bb }
            monitor-exit(r3);	 Catch:{ all -> 0x01bb }
        L_0x01a9:
            r2 = r4.c;
            if (r2 == 0) goto L_0x01be;
        L_0x01ad:
            r2 = r4.c;
            r2 = r2 instanceof java.io.IOException;
            if (r2 == 0) goto L_0x01be;
        L_0x01b3:
            r2 = r4.c;
            r2 = (java.io.IOException) r2;
            throw r2;
        L_0x01b8:
            r2 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x01b8 }
            throw r2;
        L_0x01bb:
            r2 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x01bb }
            throw r2;
        L_0x01be:
            r2 = r4.b;
            return r2;
        L_0x01c1:
            r2 = r10;
            goto L_0x0079;
        L_0x01c4:
            r2 = r10;
            r10 = r8;
            goto L_0x00bf;
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.network.custom.a.a.a.a(okhttp3.t$a, okhttp3.y):okhttp3.aa");
        }

        public void a() {
            synchronized (this.b) {
                this.c = 15;
            }
        }

        public void a(String str) {
            int i = 15;
            int b = b(str);
            switch (b) {
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 4;
                    break;
                case 4:
                    if (a) {
                        i = 8;
                        break;
                    }
                    break;
            }
            synchronized (this.b) {
                if (this.c <= b) {
                    this.c = i;
                }
            }
        }

        private boolean a(a aVar) {
            if (aVar.c == null || b(aVar)) {
                return true;
            }
            return false;
        }

        private boolean b(a aVar) {
            return aVar.c instanceof ClientErrorException;
        }

        private boolean a(a aVar, boolean z) {
            if (b(aVar)) {
                return true;
            }
            if (aVar.c instanceof SSLException) {
                return false;
            }
            if (e.a().c(aVar.a.a().toString())) {
                return false;
            }
            int b = aVar.b.b();
            if (b == 313 || b == 403 || b == ErrorCode$NetWorkError.RETRY_TIME_JS_ERROR) {
                return false;
            }
            if (!z && (b == ErrorCode$AdError.JSON_PARSE_ERROR || b == ErrorCode$AdError.DETAIl_URL_ERROR)) {
                return false;
            }
            if (b >= 400) {
                return false;
            }
            return true;
        }

        private String a(int i) {
            switch (i) {
                case 1:
                    return "httpdns";
                case 2:
                    return "smartdns";
                case 4:
                    return "localdns";
                case 8:
                    return "localhttp";
                case 15:
                    return SpeechConstant.PLUS_LOCAL_ALL;
                default:
                    return "unknown";
            }
        }

        private int b(String str) {
            if (str.equals("httpdns")) {
                return 1;
            }
            if (str.equals("smartdns")) {
                return 2;
            }
            if (str.equals("localdns")) {
                return 4;
            }
            if (str.equals("localhttp")) {
                return 8;
            }
            return 15;
        }

        private void a(String str, int i, a aVar) {
            String str2 = "";
            if (aVar.a != null) {
                str2 = aVar.a.a().toString();
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (aVar.b != null) {
                Object a = aVar.b.a("Content-Type");
                if (!TextUtils.isEmpty(a)) {
                    stringBuilder.append("Content-Type=" + a);
                    stringBuilder.append(", ");
                }
                stringBuilder.append("status=" + aVar.b.b());
            } else if (aVar.c != null) {
                stringBuilder.append(aVar.c.getMessage());
            }
            e.a().a(str2, str, a(i), aVar.d, stringBuilder.toString());
        }

        private String c(String str) {
            return e.a().a(str);
        }

        private String d(String str) {
            return b.a().a(str);
        }

        private a a(okhttp3.t.a aVar, y yVar, String str, int i, String str2) {
            boolean z = false;
            a aVar2 = new a();
            long currentTimeMillis = System.currentTimeMillis();
            y$a b = yVar.f().a(str2).b("Host", str);
            if (str2.contains(str)) {
                b.b("Host");
            }
            y d = b.d();
            aVar2.a = d;
            try {
                aVar2.b = aVar.a(d).h().a("Host", str).a("AddressType", a(i)).a();
                if (!aVar2.b.c()) {
                    e.a().a(str2, str, a(i), "status=" + aVar2.b.b());
                }
            } catch (Throwable e) {
                aVar2.c = e;
                cn.xiaochuankeji.tieba.analyse.a.a(e);
                if (e instanceof SSLException) {
                    cn.xiaochuankeji.tieba.analyse.a.a(new IllegalStateException("url:" + str2, e));
                    cn.xiaochuankeji.tieba.background.utils.c.a.c().D();
                    aVar2.c = e;
                    a = false;
                }
                Object message = e.getMessage();
                if (!TextUtils.isEmpty(message) && message.equals("Canceled")) {
                    z = true;
                }
                if (!z) {
                    e.a().a(str2, str, a(i), e.getMessage());
                }
            } catch (Throwable e2) {
                cn.xiaochuankeji.tieba.analyse.a.a(new IllegalStateException("url:" + str2, e2));
                aVar2.c = e2;
                cn.xiaochuankeji.tieba.background.utils.c.a.c().D();
            }
            aVar2.d = System.currentTimeMillis() - currentTimeMillis;
            return aVar2;
        }
    }

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public void a(boolean z, boolean z2) {
        synchronized (this.b) {
            a(this.b, z, z2);
        }
        synchronized (this.c) {
            a(this.c, z, z2);
        }
    }

    private void a(HashMap<String, a> hashMap, boolean z, boolean z2) {
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            a aVar = (a) entry.getValue();
            if (e.a().d(str)) {
                if (z) {
                    aVar.a();
                }
            } else if (z2) {
                aVar.a();
            }
        }
    }

    public void a(String str, String str2) {
        String protocol;
        String host;
        IOException iOException;
        Object obj;
        a aVar;
        a aVar2 = null;
        try {
            URL url = new URL(str);
            protocol = url.getProtocol();
            try {
                host = url.getHost();
            } catch (IOException e) {
                IOException iOException2 = e;
                Object obj2 = protocol;
                iOException = iOException2;
                iOException.printStackTrace();
                obj = aVar;
                host = null;
                if (!TextUtils.isEmpty(protocol)) {
                }
                return;
            }
        } catch (IOException e2) {
            iOException = e2;
            aVar = null;
            iOException.printStackTrace();
            obj = aVar;
            host = null;
            if (!TextUtils.isEmpty(protocol)) {
                return;
            }
        }
        if (!TextUtils.isEmpty(protocol) && !TextUtils.isEmpty(host)) {
            if (protocol.equals("http")) {
                synchronized (this.b) {
                    aVar2 = a(this.b, host);
                }
            } else if (protocol.equals("https")) {
                synchronized (this.c) {
                    aVar2 = a(this.c, host);
                }
            }
            if (aVar2 != null) {
                aVar2.a(str2);
            }
        }
    }

    private a a(HashMap<String, a> hashMap, String str) {
        for (Entry entry : hashMap.entrySet()) {
            if (((String) entry.getKey()).equals(str)) {
                return (a) entry.getValue();
            }
        }
        return null;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        int i = 0;
        y a = aVar.a();
        aa aaVar = null;
        while (true) {
            aa a2;
            a a3 = a(a.a().toString());
            if (a3 == null) {
                a2 = aVar.a(a);
            } else {
                a2 = a3.a(aVar, a);
            }
            if (aaVar != null) {
                a2 = a2.h().c(aaVar.h().a(null).a()).a();
            }
            if (a2 == null) {
                cn.xiaochuankeji.tieba.analyse.a.a(new NullPointerException(a.a() + "get null response"));
                if (aaVar != null) {
                    return aaVar;
                }
            }
            a = a(a2);
            if (a == null) {
                return a2;
            }
            c.a(a2.g());
            int i2 = i + 1;
            if (i2 > 20) {
                throw new ProtocolException("Too many follow-up requests: " + i2);
            } else if (a.d() instanceof l) {
                throw new HttpRetryException("Cannot retry streamed HTTP body", a2.b());
            } else {
                i = i2;
                aaVar = a2;
            }
        }
    }

    private y a(aa aaVar) throws IOException {
        if (aaVar == null) {
            throw new IOException("response is null");
        }
        int b = aaVar.b();
        String b2 = aaVar.a().b();
        switch (b) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case TinkerReport.KEY_LOADED_MISSING_DEX_OPT /*307*/:
            case TinkerReport.KEY_LOADED_MISSING_RES /*308*/:
                if (!(b2.equals(Constants.HTTP_GET) || b2.equals("HEAD"))) {
                    return null;
                }
            default:
                return null;
        }
        String a = aaVar.a("Location");
        if (a == null) {
            return null;
        }
        HttpUrl c = aaVar.a().a().c(a);
        if (c == null || !c.b().equals(aaVar.a().a().b())) {
            return null;
        }
        y$a f = aaVar.a().f();
        if (f.c(b2)) {
            if (f.e(b2)) {
                f.a(Constants.HTTP_GET, null);
            } else {
                f.a(b2, null);
            }
            f.b("Transfer-Encoding");
            f.b("Content-Length");
            f.b("Content-Type");
        }
        f.b("Host");
        if (!a(aaVar, c)) {
            f.b("Authorization");
        }
        return f.a(c).d();
    }

    private boolean a(aa aaVar, HttpUrl httpUrl) {
        HttpUrl a = aaVar.a().a();
        return a.f().equals(httpUrl.f()) && a.g() == httpUrl.g() && a.b().equals(httpUrl.b());
    }

    private a a(String str) {
        String protocol;
        Object host;
        String str2;
        IOException iOException;
        try {
            URL url = new URL(str);
            protocol = url.getProtocol();
            try {
                host = url.getHost();
            } catch (IOException e) {
                IOException iOException2 = e;
                str2 = protocol;
                iOException = iOException2;
                iOException.printStackTrace();
                protocol = str2;
                host = null;
                if (!TextUtils.isEmpty(protocol)) {
                }
                return null;
            }
        } catch (IOException e2) {
            iOException = e2;
            str2 = null;
            iOException.printStackTrace();
            protocol = str2;
            host = null;
            if (TextUtils.isEmpty(protocol)) {
            }
            return null;
        }
        if (TextUtils.isEmpty(protocol) || TextUtils.isEmpty(host)) {
            return null;
        }
        a aVar;
        a aVar2;
        if (protocol.equals("http")) {
            synchronized (this.b) {
                if (this.b.containsKey(host)) {
                    aVar = (a) this.b.get(host);
                    return aVar;
                }
                boolean z;
                if (host.equals("file.izuiyou.com")) {
                    z = true;
                } else {
                    z = false;
                }
                aVar2 = new a(z);
                this.b.put(host, aVar2);
                return aVar2;
            }
        } else if (!protocol.equals("https")) {
            return null;
        } else {
            synchronized (this.c) {
                if (this.c.containsKey(host)) {
                    aVar = (a) this.c.get(host);
                    return aVar;
                }
                aVar2 = new a(true);
                this.c.put(host, aVar2);
                return aVar2;
            }
        }
    }
}
