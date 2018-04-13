package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.util.Log;

public final class d extends Thread {
    private Context a;
    private String b = "";
    private int c;
    private String d = "";
    private String e = "";

    d(Context context, int i) {
        this.a = context;
        this.c = i;
    }

    d(Context context, int i, String str, String str2) {
        this.a = context;
        this.c = i;
        this.d = str;
        this.e = str2;
    }

    public final void run() {
        try {
            switch (this.c) {
                case 1:
                    b.a(this.a, this.b);
                    return;
                case 2:
                    b.e(this.a, this.b, this.d);
                    return;
                case 3:
                    b.b(this.a, this.b);
                    return;
                case 4:
                    b.c(this.a, null);
                    return;
                case 6:
                    b.g(this.a);
                    return;
                case 9:
                    String h = h.h(this.a);
                    String i = h.i(this.a);
                    if (h == null || h.length() == 0) {
                        Log.e("UploadThread", "unexpected empty appkey");
                        return;
                    }
                    if (i == null || i.length() == 0) {
                        Log.e("UploadThread", "unexpected empty channelId");
                    }
                    b.a(this.a, true);
                    return;
                case 10:
                    b.a(this.a, false);
                    return;
                case 11:
                    b.b(this.a, this.d, this.e);
                    return;
                case 12:
                    b.f(this.a, this.d);
                    return;
                case 13:
                    b.c(this.a, this.d, this.e);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            Log.e("MobileAgent", "Exception occurred when recording usage.");
            e.printStackTrace();
        }
        Log.e("MobileAgent", "Exception occurred when recording usage.");
        e.printStackTrace();
    }
}
