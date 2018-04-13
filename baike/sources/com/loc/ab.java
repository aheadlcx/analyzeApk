package com.loc;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

public final class ab extends ad {
    private static boolean b = true;

    protected ab(int i) {
        super(i);
    }

    protected final String a(String str) {
        return p.c(str + t.a(new Date().getTime()));
    }

    protected final String a(List<s> list) {
        return null;
    }

    protected final boolean a(Context context) {
        if (!b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            ar a = aqVar.a();
            if (a == null) {
                return true;
            } else if (a.a()) {
                a.a(false);
                aqVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
