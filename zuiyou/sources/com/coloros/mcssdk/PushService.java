package com.coloros.mcssdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.coloros.mcssdk.c.d;
import com.coloros.mcssdk.d.a;
import com.coloros.mcssdk.e.b;
import com.coloros.mcssdk.e.c;
import java.util.List;

public class PushService extends Service implements a {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        List<c> a = com.coloros.mcssdk.a.c.a(getApplicationContext(), intent);
        List<com.coloros.mcssdk.b.c> b = a.c().b();
        if (a == null || a.size() == 0 || b == null || b.size() == 0) {
            return super.onStartCommand(intent, i, i2);
        }
        for (c cVar : a) {
            if (cVar != null) {
                for (com.coloros.mcssdk.b.c cVar2 : b) {
                    if (cVar2 != null) {
                        try {
                            cVar2.a(getApplicationContext(), cVar, this);
                        } catch (Exception e) {
                            com.coloros.mcssdk.c.c.b("process Exception:" + e.getMessage());
                        }
                    }
                }
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void processMessage(Context context, com.coloros.mcssdk.e.a aVar) {
    }

    public void processMessage(Context context, b bVar) {
        if (a.c().d() != null) {
            switch (bVar.b()) {
                case 12289:
                    if (bVar.d() == 0) {
                        a.c().a(bVar.c());
                    }
                    a.c().d().a(bVar.d(), bVar.c());
                    return;
                case 12290:
                    a.c().d().a(bVar.d());
                    return;
                case 12292:
                    a.c().d().b(bVar.d(), b.a(bVar.c(), "alias", "aliasId", "aliasName"));
                    return;
                case 12293:
                    a.c().d().a(bVar.d(), b.a(bVar.c(), "alias", "aliasId", "aliasName"));
                    return;
                case 12294:
                    a.c().d().c(bVar.d(), b.a(bVar.c(), "alias", "aliasId", "aliasName"));
                    return;
                case 12295:
                    a.c().d().g(bVar.d(), b.a(bVar.c(), "tags", "tagId", "tagName"));
                    return;
                case 12296:
                    a.c().d().i(bVar.d(), b.a(bVar.c(), "tags", "tagId", "tagName"));
                    return;
                case 12297:
                    a.c().d().h(bVar.d(), b.a(bVar.c(), "tags", "tagId", "tagName"));
                    return;
                case 12298:
                    a.c().d().b(bVar.d(), bVar.c());
                    return;
                case 12301:
                    a.c().d().d(bVar.d(), b.a(bVar.c(), "tags", "accountId", "accountName"));
                    return;
                case 12302:
                    a.c().d().f(bVar.d(), b.a(bVar.c(), "tags", "accountId", "accountName"));
                    return;
                case 12303:
                    a.c().d().e(bVar.d(), b.a(bVar.c(), "tags", "accountId", "accountName"));
                    return;
                case 12306:
                    a.c().d().a(bVar.d(), d.a(bVar.c()));
                    return;
                case 12309:
                    a.c().d().b(bVar.d(), d.a(bVar.c()));
                    return;
                default:
                    return;
            }
        }
    }

    public void processMessage(Context context, com.coloros.mcssdk.e.d dVar) {
    }
}
