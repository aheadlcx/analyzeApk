package com.taobao.applink.c;

import android.os.AsyncTask;

class d extends AsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ b d;

    d(b bVar, String str, String str2, String str3) {
        this.d = bVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    protected Void a(Void... voidArr) {
        this.d.b(this.a, this.b, this.c);
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }
}
