package com.coloros.mcssdk.b;

import android.content.Context;
import android.support.v4.view.InputDeviceCompat;
import com.coloros.mcssdk.e.c;

public final class a implements c {
    public final void a(Context context, c cVar, com.coloros.mcssdk.d.a aVar) {
        if (cVar != null && cVar.a() == InputDeviceCompat.SOURCE_TOUCHSCREEN) {
            com.coloros.mcssdk.e.a aVar2 = (com.coloros.mcssdk.e.a) cVar;
            if (aVar != null) {
                aVar.processMessage(context, aVar2);
            }
        }
    }
}
