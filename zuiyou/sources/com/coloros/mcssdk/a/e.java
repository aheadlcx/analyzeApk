package com.coloros.mcssdk.a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.a;
import com.coloros.mcssdk.e.c;
import com.coloros.mcssdk.e.d;

public final class e extends c {
    public final c a(Context context, int i, Intent intent) {
        if (4103 != i) {
            return null;
        }
        c a = a(intent);
        a.a(context, (d) a, "push_transmit");
        return a;
    }

    public final c a(Intent intent) {
        try {
            c dVar = new d();
            dVar.d(Integer.parseInt(com.coloros.mcssdk.c.a.a(intent.getStringExtra("messageID"))));
            dVar.e(com.coloros.mcssdk.c.a.a(intent.getStringExtra("taskID")));
            dVar.f(com.coloros.mcssdk.c.a.a(intent.getStringExtra("appPackage")));
            dVar.b(com.coloros.mcssdk.c.a.a(intent.getStringExtra("content")));
            dVar.c(com.coloros.mcssdk.c.a.a(intent.getStringExtra("description")));
            dVar.d(com.coloros.mcssdk.c.a.a(intent.getStringExtra("appID")));
            dVar.a(com.coloros.mcssdk.c.a.a(intent.getStringExtra("globalID")));
            return dVar;
        } catch (Exception e) {
            com.coloros.mcssdk.c.c.a("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }
}
