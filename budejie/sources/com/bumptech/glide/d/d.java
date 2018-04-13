package com.bumptech.glide.d;

import android.content.Context;
import com.bumptech.glide.d.c.a;
import com.umeng.update.UpdateConfig;

public class d {
    public c a(Context context, a aVar) {
        if ((context.checkCallingOrSelfPermission(UpdateConfig.g) == 0 ? 1 : null) != null) {
            return new e(context, aVar);
        }
        return new i();
    }
}
