package com.budejie.www.g;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.p.a;
import com.tencent.mm.sdk.openapi.IWXAPI;
import java.util.HashMap;

class b$2 implements a {
    final /* synthetic */ HashMap a;
    final /* synthetic */ n b;
    final /* synthetic */ ListItemObject c;
    final /* synthetic */ SharedPreferences d;
    final /* synthetic */ String e;
    final /* synthetic */ com.elves.update.a f;
    final /* synthetic */ Handler g;
    final /* synthetic */ IWXAPI h;
    final /* synthetic */ Handler i;
    final /* synthetic */ b j;

    b$2(b bVar, HashMap hashMap, n nVar, ListItemObject listItemObject, SharedPreferences sharedPreferences, String str, com.elves.update.a aVar, Handler handler, IWXAPI iwxapi, Handler handler2) {
        this.j = bVar;
        this.a = hashMap;
        this.b = nVar;
        this.c = listItemObject;
        this.d = sharedPreferences;
        this.e = str;
        this.f = aVar;
        this.g = handler;
        this.h = iwxapi;
        this.i = handler2;
    }

    public void a(int i, Dialog dialog) {
        HashMap hashMap = this.a;
        if (this.a == null || TextUtils.isEmpty((CharSequence) this.a.get("weibo_token"))) {
            hashMap = this.b.a(ai.b(b.a(this.j)));
        }
        if (i == 1 && b.e(this.j)) {
            n.a(b.a(this.j), this.c);
            dialog.cancel();
        } else if (i == 2 && b.e(this.j)) {
            if (!an.a(this.d)) {
                b.a(this.j, "tenct", 0, this.d);
            } else if (TextUtils.isEmpty((CharSequence) hashMap.get("qq_uid")) || "null".equals(hashMap.get("qq_uid"))) {
                b.a(this.j, "tenct", 0, this.d);
            } else {
                this.b.a(b.a(this.j), this.c, "qq", this.e, hashMap, this.f, this.g);
            }
            dialog.cancel();
        } else if (i == 3 && b.e(this.j)) {
            this.j.b(this.c, this.h, this.i);
            dialog.cancel();
        } else if (i == 4 && b.e(this.j)) {
            this.j.a(this.c, this.h, this.i);
            dialog.cancel();
        } else if (i == 5) {
            dialog.cancel();
            this.d.edit().putBoolean("isRecommend", false).commit();
        } else if (i == 6 && b.e(this.j)) {
            this.j.a(this.c, this.i);
            dialog.cancel();
        } else if (i == 12) {
            this.j.b(this.c, this.d);
            dialog.cancel();
        } else if (i == 7) {
            this.j.a(this.c, this.d);
            dialog.cancel();
        } else if (i == 8 && b.e(this.j)) {
            this.j.b(this.c, this.i);
            dialog.cancel();
        }
    }
}
