package com.qiniu.android.storage;

import com.qiniu.android.http.ResponseInfo;
import java.io.IOException;
import org.json.JSONObject;

class g implements UpCompletionHandler {
    final /* synthetic */ UpCompletionHandler a;
    final /* synthetic */ f b;

    g(f fVar, UpCompletionHandler upCompletionHandler) {
        this.b = fVar;
        this.a = upCompletionHandler;
    }

    public void complete(String str, ResponseInfo responseInfo, JSONObject jSONObject) {
        if (this.b.l != null) {
            try {
                this.b.l.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.a.complete(str, responseInfo, jSONObject);
    }
}
