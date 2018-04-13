package com.budejie.www.activity.base;

import com.budejie.www.activity.base.a.b;

public class a$f extends b<String> {
    final /* synthetic */ a a;

    public a$f(a aVar) {
        this.a = aVar;
        super(aVar, null, null);
    }

    public boolean a(String str, String str2) {
        return a.a(this.a).edit().putString(str, str2).commit();
    }

    public String a(String str) {
        return a.a(this.a).getString(str, "");
    }
}
