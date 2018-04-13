package com.qiniu.android.storage;

import com.qiniu.android.http.ResponseInfo;
import org.json.JSONObject;

class m implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ResponseInfo b;
    final /* synthetic */ JSONObject c;
    final /* synthetic */ l d;

    m(l lVar, String str, ResponseInfo responseInfo, JSONObject jSONObject) {
        this.d = lVar;
        this.a = str;
        this.b = responseInfo;
        this.c = jSONObject;
    }

    public void run() {
        this.d.a.complete(this.a, this.b, this.c);
    }
}
