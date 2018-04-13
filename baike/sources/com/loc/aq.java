package com.loc;

import android.content.Context;
import java.util.List;

public final class aq {
    private af a = a(this.b);
    private Context b;

    public aq(Context context) {
        this.b = context;
    }

    private static af a(Context context) {
        try {
            return new af(context, af.a(am.class));
        } catch (Throwable th) {
            w.a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public final ar a() {
        try {
            if (this.a == null) {
                this.a = a(this.b);
            }
            List b = this.a.b("1=1", ar.class);
            if (b.size() > 0) {
                return (ar) b.get(0);
            }
        } catch (Throwable th) {
            w.a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public final void a(ar arVar) {
        if (arVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b);
                }
                String str = "1=1";
                List b = this.a.b(str, ar.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) arVar);
                } else {
                    this.a.a(str, (Object) arVar);
                }
            } catch (Throwable th) {
                w.a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}
