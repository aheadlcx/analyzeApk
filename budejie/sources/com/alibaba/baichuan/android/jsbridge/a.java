package com.alibaba.baichuan.android.jsbridge;

import android.os.AsyncTask;

class a extends AsyncTask {
    final /* synthetic */ AlibcJsCallMethodContext a;
    final /* synthetic */ String b;
    final /* synthetic */ AlibcJsBridge c;

    a(AlibcJsBridge alibcJsBridge, AlibcJsCallMethodContext alibcJsCallMethodContext, String str) {
        this.c = alibcJsBridge;
        this.a = alibcJsCallMethodContext;
        this.b = str;
    }

    protected Void a(Void... voidArr) {
        this.c.a(this.a, this.b);
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }
}
