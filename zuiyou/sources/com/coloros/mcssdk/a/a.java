package com.coloros.mcssdk.a;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.InputDeviceCompat;
import com.coloros.mcssdk.e.c;

public final class a extends c {
    public final c a(Context context, int i, Intent intent) {
        if (InputDeviceCompat.SOURCE_TOUCHSCREEN != i) {
            return null;
        }
        c a = a(intent);
        com.coloros.mcssdk.a.a(context, (com.coloros.mcssdk.e.a) a, "push_transmit");
        return a;
    }

    public final c a(Intent intent) {
        try {
            c aVar = new com.coloros.mcssdk.e.a();
            aVar.d(Integer.parseInt(com.coloros.mcssdk.c.a.a(intent.getStringExtra("messageID"))));
            aVar.e(com.coloros.mcssdk.c.a.a(intent.getStringExtra("taskID")));
            aVar.f(com.coloros.mcssdk.c.a.a(intent.getStringExtra("appPackage")));
            aVar.a(com.coloros.mcssdk.c.a.a(intent.getStringExtra("content")));
            aVar.a(Integer.parseInt(com.coloros.mcssdk.c.a.a(intent.getStringExtra("balanceTime"))));
            aVar.a(Long.parseLong(com.coloros.mcssdk.c.a.a(intent.getStringExtra("startDate"))));
            aVar.b(Long.parseLong(com.coloros.mcssdk.c.a.a(intent.getStringExtra("endDate"))));
            aVar.b(com.coloros.mcssdk.c.a.a(intent.getStringExtra("timeRanges")));
            aVar.c(com.coloros.mcssdk.c.a.a(intent.getStringExtra("title")));
            aVar.d(com.coloros.mcssdk.c.a.a(intent.getStringExtra("rule")));
            aVar.b(Integer.parseInt(com.coloros.mcssdk.c.a.a(intent.getStringExtra("forcedDelivery"))));
            aVar.c(Integer.parseInt(com.coloros.mcssdk.c.a.a(intent.getStringExtra("distinctBycontent"))));
            com.coloros.mcssdk.c.c.a("OnHandleIntent-message:" + aVar.toString());
            return aVar;
        } catch (Exception e) {
            com.coloros.mcssdk.c.c.a("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }
}
