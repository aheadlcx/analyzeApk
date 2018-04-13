package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class br {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;

    public br(Context context, String str, String str2, String str3) throws j {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new j("无效的参数 - IllegalArgumentException");
        }
        this.a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public final void a(String str) throws j {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new j("无效的参数 - IllegalArgumentException");
        }
        this.e = str;
    }

    public final byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr = new byte[0];
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr2;
                t.a(byteArrayOutputStream, this.c);
                t.a(byteArrayOutputStream, this.d);
                t.a(byteArrayOutputStream, this.b);
                t.a(byteArrayOutputStream, String.valueOf(n.m(this.a)));
                new SimpleDateFormat("SSS").format(new Date());
                int i = Calendar.getInstance().get(14);
                byteArrayOutputStream.write(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
                Object obj = this.e;
                if (TextUtils.isEmpty(obj)) {
                    bArr2 = new byte[]{(byte) 0, (byte) 0};
                } else {
                    byte length = (byte) (obj.length() % 256);
                    bArr2 = new byte[]{(byte) (obj.length() / 256), length};
                }
                byteArrayOutputStream.write(bArr2);
                byteArrayOutputStream.write(t.a(this.e));
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    w.a(th, "StatisticsEntity", "toDatas");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th5) {
                            th = th5;
                            th.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th6) {
                    th2 = th6;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th8) {
            th2 = th8;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
        return bArr;
    }
}
