package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.cloud.thirdparty.ak.a;
import com.iflytek.cloud.thirdparty.ak.b;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class aj {
    private static String a = b;
    private static String b = (AIUISetting.getAIUIPath() + "data/");
    private static int c = 0;
    private static boolean d = false;
    private static b e;
    private a f;

    public static String a() {
        return a;
    }

    private static String a(Context context, String str) {
        int i = 1;
        a a = ak.a(b);
        if (a.c("index.txt")) {
            byte[] bArr = new byte[a.e()];
            a.a(bArr);
            a.c();
            try {
                i = Integer.parseInt(new String(bArr));
            } catch (NumberFormatException e) {
            }
        }
        c = i;
        a.a("history", ".txt");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----------------------------------\n").append(new Date().toString()).append(" ").append(str).append(i).append("\n");
        a.a(stringBuffer.toString());
        a.a();
        String str2 = b + str + i + "/";
        try {
            a.a("index", ".txt", false);
            a.a(String.valueOf(i + 1).getBytes("utf-8"), true);
            a.d();
            return str2;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str2;
        } catch (IOException e3) {
            e3.printStackTrace();
            return str2;
        }
    }

    public static void a(Context context, String str, String str2) {
        if (!d) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            a = a(context, str2);
        } else {
            a = str;
        }
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            b = str;
            if (e != null) {
                e.a(b);
            }
        }
    }

    public static void a(boolean z, long j, long j2, int i) {
        d = z;
        if (z) {
            if (e == null) {
                if (-1 != j) {
                    e = new b(b, j, j2, i);
                    e.setName("AIUI:DataSizeDeamonThread");
                    e.start();
                }
            } else if (-1 == j) {
                e.a();
                e = null;
            }
        } else if (e != null) {
            e.a();
            e = null;
        }
    }

    public static int b() {
        return c;
    }

    public static boolean c() {
        return d;
    }

    public void a(String str, String str2, boolean z) {
        if (this.f != null) {
            this.f.a(str, str2, z);
        }
    }

    public boolean a(byte[] bArr, boolean z) {
        if (this.f == null) {
            return false;
        }
        try {
            this.f.a(bArr, z);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void d() {
        if (this.f != null) {
            this.f.d();
        }
    }

    public void e() {
        if (d) {
            if (this.f != null) {
                this.f.b();
            }
            this.f = ak.a(a);
        }
    }
}
