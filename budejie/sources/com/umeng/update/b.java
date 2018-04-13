package com.umeng.update;

import android.content.Context;
import com.umeng.update.util.DeltaUpdate;
import u.upd.e;
import u.upd.g;

public class b extends e {
    private static final String c = b.class.getName();
    Context a;
    private final String[] b = new String[]{"http://au.umeng.com/api/check_app_update", "http://au.umeng.co/api/check_app_update"};

    public b(Context context) {
        this.a = context;
    }

    public boolean a() {
        return false;
    }

    public UpdateResponse b() {
        u.upd.b.c(c, String.format("is .so file ready: %b", new Object[]{Boolean.valueOf(DeltaUpdate.a())}));
        g dVar = new d(this.a);
        UpdateResponse updateResponse = null;
        for (String a : this.b) {
            dVar.a(a);
            updateResponse = (UpdateResponse) a(dVar, UpdateResponse.class);
            if (updateResponse != null) {
                break;
            }
        }
        return updateResponse;
    }
}
