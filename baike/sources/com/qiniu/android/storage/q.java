package com.qiniu.android.storage;

import com.qiniu.android.http.ResponseInfo;
import org.json.JSONObject;

class q implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ResponseInfo b;
    final /* synthetic */ JSONObject c;
    final /* synthetic */ p d;

    q(p pVar, String str, ResponseInfo responseInfo, JSONObject jSONObject) {
        this.d = pVar;
        this.a = str;
        this.b = responseInfo;
        this.c = jSONObject;
    }

    public void run() {
        this.d.a.complete(this.a, this.b, this.c);
    }
}
