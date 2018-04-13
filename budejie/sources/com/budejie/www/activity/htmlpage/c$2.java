package com.budejie.www.activity.htmlpage;

import android.app.Dialog;
import android.os.Handler;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.p.a;

class c$2 implements a {
    final /* synthetic */ ListItemObject a;
    final /* synthetic */ String b;
    final /* synthetic */ c c;

    c$2(c cVar, ListItemObject listItemObject, String str) {
        this.c = cVar;
        this.a = listItemObject;
        this.b = str;
    }

    public void a(int i, Dialog dialog) {
        if (i == 3) {
            c.e(this.c).b(this.a, c.d(this.c), new Handler(), this.b);
            dialog.dismiss();
        } else if (i == 4) {
            c.e(this.c).a(this.a, c.d(this.c), new Handler(), this.b);
            dialog.dismiss();
        } else if (i == 8) {
            c.e(this.c).b(this.a, c.b(this.c), this.b);
            dialog.dismiss();
        }
    }
}
