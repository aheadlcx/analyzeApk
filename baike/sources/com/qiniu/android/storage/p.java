package com.qiniu.android.storage;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AsyncRun;
import org.json.JSONObject;

class p implements UpCompletionHandler {
    final /* synthetic */ UpCompletionHandler a;
    final /* synthetic */ UploadManager b;

    p(UploadManager uploadManager, UpCompletionHandler upCompletionHandler) {
        this.b = uploadManager;
        this.a = upCompletionHandler;
    }

    public void complete(String str, ResponseInfo responseInfo, JSONObject jSONObject) {
        AsyncRun.runInMain(new q(this, str, responseInfo, jSONObject));
    }
}
