package com.umeng.analytics.c;

import a.a.a.g;
import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.a.b;
import com.umeng.a.d;
import com.umeng.a.e;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.d.m;
import com.umeng.analytics.g.a;
import java.io.File;
import org.json.JSONObject;

public class c {
    private final byte[] a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    private final int b = 1;
    private final int c = 0;
    private String d = "1.0";
    private String e = null;
    private byte[] f = null;
    private byte[] g = null;
    private byte[] h = null;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private byte[] l = null;
    private byte[] m = null;
    private boolean n = false;

    private c(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = str;
        this.k = bArr.length;
        this.l = com.umeng.a.c.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static String a(Context context) {
        SharedPreferences a = m.a(context);
        if (a == null) {
            return null;
        }
        return a.getString("signature", null);
    }

    public void a(String str) {
        this.f = b.a(str);
    }

    public String a() {
        return b.a(this.f);
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public static c a(Context context, String str, byte[] bArr) {
        try {
            String n = d.n(context);
            String c = d.c(context);
            SharedPreferences a = m.a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            c cVar = new c(bArr, str, (c + n).getBytes());
            cVar.a(string);
            cVar.a(i);
            cVar.b();
            a.edit().putInt("serial", i + 1).putString("signature", cVar.a()).commit();
            cVar.b(context);
            return cVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static c b(Context context, String str, byte[] bArr) {
        try {
            String n = d.n(context);
            String c = d.c(context);
            SharedPreferences a = m.a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            c cVar = new c(bArr, str, (c + n).getBytes());
            cVar.a(true);
            cVar.a(string);
            cVar.a(i);
            cVar.b();
            a.edit().putInt("serial", i + 1).putString("signature", cVar.a()).commit();
            cVar.b(context);
            return cVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b() {
        if (this.f == null) {
            this.f = d();
        }
        if (this.n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f, 1, bArr, 0, 16);
                this.l = b.a(this.l, bArr);
            } catch (Exception e) {
            }
        }
        this.g = a(this.f, this.j);
        this.h = e();
    }

    private byte[] a(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        byte[] b = b.b(this.m);
        byte[] b2 = b.b(this.l);
        int length = b.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = b2[i2];
            bArr2[(i2 * 2) + 1] = b[i2];
        }
        for (i2 = 0; i2 < 2; i2++) {
            bArr2[i2] = bArr[i2];
            bArr2[(bArr2.length - i2) - 1] = bArr[(bArr.length - i2) - 1];
        }
        byte[] bArr3 = new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        while (i3 < bArr2.length) {
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr3[i3 % 4]);
            i3++;
        }
        return bArr2;
    }

    private byte[] d() {
        return a(this.a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b.a(this.f));
        stringBuilder.append(this.i);
        stringBuilder.append(this.j);
        stringBuilder.append(this.k);
        stringBuilder.append(b.a(this.g));
        return b.b(stringBuilder.toString().getBytes());
    }

    public byte[] c() {
        a.a.a.d aVar = new a();
        aVar.a(this.d);
        aVar.b(this.e);
        aVar.c(b.a(this.f));
        aVar.a(this.i);
        aVar.c(this.j);
        aVar.d(this.k);
        aVar.a(this.l);
        aVar.e(this.n ? 1 : 0);
        aVar.d(b.a(this.g));
        aVar.e(b.a(this.h));
        try {
            return new g().a(aVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(Context context) {
        String str = this.e;
        String g = h.a(context).b().g(null);
        String a = b.a(this.f);
        byte[] bArr = new byte[16];
        System.arraycopy(this.f, 2, bArr, 0, 16);
        String a2 = b.a(b.b(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.umeng.analytics.b.g.a, str);
            if (g != null) {
                jSONObject.put("umid", g);
            }
            jSONObject.put("signature", a);
            jSONObject.put("checksum", a2);
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            e.a(new File(file, "exchangeIdentity.json"), jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(com.umeng.analytics.b.g.a, str);
            jSONObject2.put(com.umeng.analytics.b.g.b, AnalyticsConfig.getChannel(context));
            if (g != null) {
                jSONObject2.put("umid", e.b(g));
            }
            e.a(new File(context.getFilesDir(), "exid.dat"), jSONObject2.toString());
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public String toString() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.d}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.e}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{b.a(this.f)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.i)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.j)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.k)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{b.a(this.g)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{b.a(this.h)}));
        String str = "codex : %d";
        Object[] objArr = new Object[1];
        if (!this.n) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        stringBuilder.append(String.format(str, objArr));
        return stringBuilder.toString();
    }
}
