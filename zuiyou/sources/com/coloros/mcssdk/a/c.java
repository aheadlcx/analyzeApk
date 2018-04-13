package com.coloros.mcssdk.a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.c.a;
import java.util.ArrayList;
import java.util.List;

public abstract class c implements d {
    public static List<com.coloros.mcssdk.e.c> a(Context context, Intent intent) {
        if (intent == null) {
            return null;
        }
        int parseInt;
        int i = 4096;
        try {
            parseInt = Integer.parseInt(a.a(intent.getStringExtra("type")));
        } catch (Exception e) {
            com.coloros.mcssdk.c.c.b("MessageParser--getMessageByIntent--Exception:" + e.getMessage());
            parseInt = i;
        }
        com.coloros.mcssdk.c.c.a("MessageParser--getMessageByIntent--type:" + parseInt);
        List<com.coloros.mcssdk.e.c> arrayList = new ArrayList();
        for (d dVar : com.coloros.mcssdk.a.c().a()) {
            if (dVar != null) {
                com.coloros.mcssdk.e.c a = dVar.a(context, parseInt, intent);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        }
        return arrayList;
    }
}
