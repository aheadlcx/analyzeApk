package com.iflytek.cloud.thirdparty;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

public class o {
    public static byte[] a;
    private ah b;

    public o(ah ahVar) {
        this.b = ahVar;
    }

    public static String a(int i) {
        switch (i) {
            case 1:
                return "idle";
            case 2:
                return "ready";
            case 3:
                return "working";
            default:
                return "idle";
        }
    }

    public void a(String str, String str2, String str3, String str4, byte[] bArr, String str5, String str6) throws n {
        if (TextUtils.isEmpty(str4)) {
            str4 = "null";
        }
        String a = al.a(NotificationCompat.CATEGORY_EVENT);
        ce ceVar = new ce();
        ceVar.a("stream_id", a);
        ceVar.a(NotificationCompat.CATEGORY_EVENT, str);
        ceVar.a("state", str2);
        ceVar.a("last_state", str3);
        ceVar.a("operation", str4);
        ceVar.a("source", str6);
        ceVar.a("scene", ac.a("global", "scene", ""), false);
        ceVar.a("userparams", ac.c());
        if (bArr == null) {
            Object obj = "null";
            ceVar.a("event_only", "1");
            bArr = new byte[1];
            ceVar.a("data_type", "null");
        } else {
            ceVar.a("event_only", "0");
            ceVar.a("data_type", str5);
        }
        if ("audio".equals(obj)) {
            ceVar = ac.b(ceVar);
        } else {
            ac.a(ceVar);
            ceVar.a(i.a);
        }
        if (this.b != null) {
            ceVar.a("dts", "5");
            this.b.a(ac.d(ceVar), ac.c(ceVar), bArr, bArr.length);
        }
    }
}
