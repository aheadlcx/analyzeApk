package com.baidu.mobstat;

import android.content.Context;

enum aq extends ao {
    aq(String str, int i, int i2) {
        super(str, i, i2);
    }

    public void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        l a = au.a(context);
        be beVar = new be();
        beVar.a = false;
        beVar.b = "M";
        beVar.c = false;
        a.a(applicationContext, beVar.a());
    }
}
