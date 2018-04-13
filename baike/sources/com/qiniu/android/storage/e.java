package com.qiniu.android.storage;

import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.http.ResponseInfo;
import org.json.JSONObject;

class e implements CompletionHandler {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void complete(ResponseInfo responseInfo, JSONObject jSONObject) {
        if (responseInfo.isOK()) {
            this.a.a.d.progress(this.a.c, 1.0d);
        }
        this.a.b.complete(this.a.c, responseInfo, jSONObject);
    }
}
