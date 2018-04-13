package com.baidu.location.a;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.d;
import com.baidu.location.b.h;
import com.baidu.location.d.e;
import com.baidu.location.d.j;
import com.baidu.location.f;
import java.util.HashMap;
import java.util.Locale;

public abstract class g {
    public static String c = null;
    public com.baidu.location.b.g a = null;
    public com.baidu.location.b.a b = null;
    final Handler d = new a(this);
    private boolean e = true;
    private boolean f = false;
    private String g = null;

    public class a extends Handler {
        final /* synthetic */ g a;

        public a(g gVar) {
            this.a = gVar;
        }

        public void handleMessage(Message message) {
            if (f.isServing) {
                switch (message.what) {
                    case 21:
                        this.a.a(message);
                        return;
                    case 62:
                    case 63:
                        this.a.a();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    class b extends e {
        String a;
        String b;
        final /* synthetic */ g c;

        public b(g gVar) {
            this.c = gVar;
            this.a = null;
            this.b = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = j.c();
            String encodeTp4 = Jni.encodeTp4(this.b);
            this.b = null;
            if (this.a == null) {
                this.a = s.b();
            }
            this.k.put("bloc", encodeTp4);
            if (this.a != null) {
                this.k.put("up", this.a);
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            if ((j.g || j.h) && this.c.g != null) {
                stringBuffer.append(String.format(Locale.CHINA, "&ki=%s", new Object[]{this.c.g}));
            }
            if (stringBuffer.length() > 0) {
                this.k.put("ext", Jni.encode(stringBuffer.toString()));
            }
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        public void a(String str) {
            this.b = str;
            e();
        }

        public void a(boolean z) {
            Message obtainMessage;
            if (!z || this.j == null) {
                obtainMessage = this.c.d.obtainMessage(63);
                obtainMessage.obj = "HttpStatus error";
                obtainMessage.sendToTarget();
            } else {
                try {
                    BDLocation bDLocation;
                    String str = this.j;
                    g.c = str;
                    try {
                        bDLocation = new BDLocation(str);
                        bDLocation.setOperators(com.baidu.location.b.b.a().h());
                        if (j.a().f()) {
                            bDLocation.setDirection(j.a().h());
                        }
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(0);
                    }
                    this.a = null;
                    if (bDLocation.getLocType() == 0 && bDLocation.getLatitude() == Double.MIN_VALUE && bDLocation.getLongitude() == Double.MIN_VALUE) {
                        obtainMessage = this.c.d.obtainMessage(63);
                        obtainMessage.obj = "HttpStatus error";
                        obtainMessage.sendToTarget();
                    } else {
                        Message obtainMessage2 = this.c.d.obtainMessage(21);
                        obtainMessage2.obj = bDLocation;
                        obtainMessage2.sendToTarget();
                    }
                } catch (Exception e2) {
                    obtainMessage = this.c.d.obtainMessage(63);
                    obtainMessage.obj = "HttpStatus error";
                    obtainMessage.sendToTarget();
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    public String a(String str) {
        if (this.g == null) {
            this.g = h.b(f.getServiceContext());
        }
        if (this.b == null || !this.b.a()) {
            this.b = com.baidu.location.b.b.a().f();
        }
        if (this.a == null || !this.a.f()) {
            this.a = h.a().m();
        }
        Location g = d.a().i() ? d.a().g() : null;
        if ((this.b == null || this.b.c()) && ((this.a == null || this.a.a() == 0) && g == null)) {
            return null;
        }
        return j.a(this.b, this.a, g, b(), 0);
    }

    public abstract void a();

    public abstract void a(Message message);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b() {
        /*
        r8 = this;
        r7 = 1;
        r6 = 0;
        r0 = com.baidu.location.a.a.a();
        r1 = r0.c();
        r0 = com.baidu.location.b.h.h();
        if (r0 == 0) goto L_0x005d;
    L_0x0010:
        r0 = "&cn=32";
    L_0x0012:
        r2 = r8.e;
        if (r2 == 0) goto L_0x0076;
    L_0x0016:
        r8.e = r6;
        r2 = com.baidu.location.b.h.a();
        r2 = r2.o();
        r3 = android.text.TextUtils.isEmpty(r2);
        if (r3 != 0) goto L_0x0045;
    L_0x0026:
        r3 = "02:00:00:00:00:00";
        r3 = r2.equals(r3);
        if (r3 != 0) goto L_0x0045;
    L_0x002e:
        r3 = ":";
        r4 = "";
        r2 = r2.replace(r3, r4);
        r3 = java.util.Locale.CHINA;
        r4 = "%s&mac=%s";
        r5 = 2;
        r5 = new java.lang.Object[r5];
        r5[r6] = r0;
        r5[r7] = r2;
        r0 = java.lang.String.format(r3, r4, r5);
    L_0x0045:
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 17;
        if (r2 <= r3) goto L_0x004b;
    L_0x004b:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r2.append(r0);
        r0 = r0.append(r1);
        r0 = r0.toString();
        return r0;
    L_0x005d:
        r0 = java.util.Locale.CHINA;
        r2 = "&cn=%d";
        r3 = new java.lang.Object[r7];
        r4 = com.baidu.location.b.b.a();
        r4 = r4.e();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r6] = r4;
        r0 = java.lang.String.format(r0, r2, r3);
        goto L_0x0012;
    L_0x0076:
        r2 = r8.f;
        if (r2 != 0) goto L_0x004b;
    L_0x007a:
        r2 = com.baidu.location.a.s.f();
        if (r2 == 0) goto L_0x0091;
    L_0x0080:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r0 = r3.append(r0);
        r0 = r0.append(r2);
        r0 = r0.toString();
    L_0x0091:
        r8.f = r7;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.g.b():java.lang.String");
    }
}
