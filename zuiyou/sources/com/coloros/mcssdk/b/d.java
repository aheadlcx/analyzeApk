package com.coloros.mcssdk.b;

import android.content.Context;
import com.coloros.mcssdk.d.a;
import com.coloros.mcssdk.e.c;

public final class d implements c {
    public final void a(Context context, c cVar, a aVar) {
        if (cVar != null && cVar.a() == 4103) {
            com.coloros.mcssdk.e.d dVar = (com.coloros.mcssdk.e.d) cVar;
            if (aVar != null) {
                aVar.processMessage(context, dVar);
            }
        }
    }
}
