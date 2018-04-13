package com.budejie.www.h;

import android.app.Activity;
import android.content.Context;
import com.budejie.www.bean.UserItem;
import com.budejie.www.http.n;
import com.budejie.www.util.an;
import java.util.HashMap;

public class b {
    private static b d;
    n a;
    HashMap<String, String> b;
    UserItem c;

    private b() {
    }

    public static b a() {
        if (d == null) {
            d = new b();
        }
        return d;
    }

    public HashMap<String, String> a(String str, Activity activity) {
        if (this.a == null) {
            this.a = new n(activity);
        }
        if (this.b == null) {
            this.b = this.a.a(str);
        }
        return this.b;
    }

    public UserItem a(Activity activity) {
        if (this.c == null) {
            this.c = an.g((Context) activity);
        }
        return this.c;
    }
}
