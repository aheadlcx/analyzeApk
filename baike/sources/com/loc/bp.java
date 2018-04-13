package com.loc;

import android.content.Context;

public final class bp {
    private Context a;
    private s b;
    private String c;

    public bp(Context context, s sVar, String str) {
        this.a = context.getApplicationContext();
        this.b = sVar;
        this.c = str;
    }

    private static String a(Context context, s sVar, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sdkversion\":\"").append(sVar.c()).append("\",\"product\":\"").append(sVar.a()).append("\",\"nt\":\"").append(n.c(context)).append("\",\"details\":").append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    final byte[] a() {
        return t.a(m.b(t.b(t.a(a(this.a, this.b, this.c)))));
    }
}
