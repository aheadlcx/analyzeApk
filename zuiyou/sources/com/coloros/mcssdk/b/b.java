package com.coloros.mcssdk.b;

import android.content.Context;
import com.coloros.mcssdk.d.a;
import com.coloros.mcssdk.e.c;

public final class b implements c {
    public final void a(Context context, c cVar, a aVar) {
        if (cVar != null && cVar.a() == 4105) {
            com.coloros.mcssdk.e.b bVar = (com.coloros.mcssdk.e.b) cVar;
            if (aVar != null) {
                aVar.processMessage(context, bVar);
            }
        }
    }
}
