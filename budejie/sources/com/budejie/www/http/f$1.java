package com.budejie.www.http;

import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import java.io.File;

class f$1 implements Runnable {
    final /* synthetic */ ListItemObject a;
    final /* synthetic */ boolean b;
    final /* synthetic */ Handler c;
    final /* synthetic */ f d;

    f$1(f fVar, ListItemObject listItemObject, boolean z, Handler handler) {
        this.d = fVar;
        this.a = listItemObject;
        this.b = z;
        this.c = handler;
    }

    public void run() {
        this.a.setCollect(true);
        this.d.b.a(this.a, ai.b(this.d.a), "no");
        if (!this.b) {
            this.c.sendEmptyMessage(6);
            this.c.sendEmptyMessage(2);
            int i = 11;
            if (this.a.isForwardAndCollect()) {
                i = 100001;
            }
            this.c.sendMessage(this.c.obtainMessage(i, this.a.getWid()));
        }
        if (!TextUtils.isEmpty(this.a.getImgUrl())) {
            String b = an.b(this.a.getImgUrl());
            if (TextUtils.isEmpty(b) || !b.endsWith(".gif")) {
                BudejieApplication.a.a(this.d.a, b, null, -1);
                return;
            }
            File a = an.a(this.d.a, b);
            if (a != null && a.exists() && b.length() > 7) {
                an.a(a.getPath(), Environment.getExternalStorageDirectory().toString() + "/budejie/" + b.substring(7).replace("/", "-"));
            }
        }
    }
}
