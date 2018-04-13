package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.AnalyticsConfig;
import java.io.File;
import org.json.JSONObject;

public class aa {
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

    private aa(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = str;
        this.k = bArr.length;
        this.l = bu.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static String a(Context context) {
        SharedPreferences a = ba.a(context);
        if (a == null) {
            return null;
        }
        return a.getString(GameAppOperation.GAME_SIGNATURE, null);
    }

    public void a(String str) {
        this.f = bt.a(str);
    }

    public String a() {
        return bt.a(this.f);
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public static aa a(Context context, String str, byte[] bArr) {
        try {
            String q = bv.q(context);
            String c = bv.c(context);
            SharedPreferences a = ba.a(context);
            String string = a.getString(GameAppOperation.GAME_SIGNATURE, null);
            int i = a.getInt("serial", 1);
            aa aaVar = new aa(bArr, str, (c + q).getBytes());
            aaVar.a(string);
            aaVar.a(i);
            aaVar.b();
            a.edit().putInt("serial", i + 1).putString(GameAppOperation.GAME_SIGNATURE, aaVar.a()).commit();
            aaVar.b(context);
            return aaVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static aa b(Context context, String str, byte[] bArr) {
        try {
            String q = bv.q(context);
            String c = bv.c(context);
            SharedPreferences a = ba.a(context);
            String string = a.getString(GameAppOperation.GAME_SIGNATURE, null);
            int i = a.getInt("serial", 1);
            aa aaVar = new aa(bArr, str, (c + q).getBytes());
            aaVar.a(true);
            aaVar.a(string);
            aaVar.a(i);
            aaVar.b();
            a.edit().putInt("serial", i + 1).putString(GameAppOperation.GAME_SIGNATURE, aaVar.a()).commit();
            aaVar.b(context);
            return aaVar;
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
                this.l = bt.a(this.l, bArr);
            } catch (Exception e) {
            }
        }
        this.g = a(this.f, this.j);
        this.h = e();
    }

    private byte[] a(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        byte[] b = bt.b(this.m);
        byte[] b2 = bt.b(this.l);
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
        stringBuilder.append(bt.a(this.f));
        stringBuilder.append(this.i);
        stringBuilder.append(this.j);
        stringBuilder.append(this.k);
        stringBuilder.append(bt.a(this.g));
        return bt.b(stringBuilder.toString().getBytes());
    }

    public byte[] c() {
        cg bqVar = new bq();
        bqVar.a(this.d);
        bqVar.b(this.e);
        bqVar.c(bt.a(this.f));
        bqVar.a(this.i);
        bqVar.c(this.j);
        bqVar.d(this.k);
        bqVar.a(this.l);
        bqVar.e(this.n ? 1 : 0);
        bqVar.d(bt.a(this.g));
        bqVar.e(bt.a(this.h));
        try {
            return new cp().a(bqVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(Context context) {
        String str = this.e;
        String g = af.a(context).b().g(null);
        String a = bt.a(this.f);
        byte[] bArr = new byte[16];
        System.arraycopy(this.f, 2, bArr, 0, 16);
        String a2 = bt.a(bt.b(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", str);
            if (g != null) {
                jSONObject.put("umid", g);
            }
            jSONObject.put(GameAppOperation.GAME_SIGNATURE, a);
            jSONObject.put("checksum", a2);
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            bw.a(new File(file, "exchangeIdentity.json"), jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appkey", str);
            jSONObject2.put("channel", AnalyticsConfig.getChannel(context));
            if (g != null) {
                jSONObject2.put("umid", bw.b(g));
            }
            bw.a(new File(context.getFilesDir(), "exid.dat"), jSONObject2.toString());
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public String toString() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.d}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.e}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{bt.a(this.f)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.i)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.j)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.k)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{bt.a(this.g)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{bt.a(this.h)}));
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
