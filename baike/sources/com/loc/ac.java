package com.loc;

import android.content.Context;
import android.os.Looper;
import java.util.List;

public final class ac extends ad {
    private static boolean b = true;

    protected ac(int i) {
        super(i);
    }

    protected final String a(List<s> list) {
        return null;
    }

    protected final boolean a(Context context) {
        if (n.m(context) != 1 || !b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            ar a = aqVar.a();
            if (a == null) {
                return true;
            } else if (a.b()) {
                a.b(false);
                aqVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
