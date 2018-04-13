package com.qiniu.android.storage;

import com.qiniu.android.common.Zone.QueryHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AsyncRun;

class n implements QueryHandler {
    final /* synthetic */ byte[] a;
    final /* synthetic */ String b;
    final /* synthetic */ UpToken c;
    final /* synthetic */ UpCompletionHandler d;
    final /* synthetic */ UploadOptions e;
    final /* synthetic */ UploadManager f;

    n(UploadManager uploadManager, byte[] bArr, String str, UpToken upToken, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        this.f = uploadManager;
        this.a = bArr;
        this.b = str;
        this.c = upToken;
        this.d = upCompletionHandler;
        this.e = uploadOptions;
    }

    public void onSuccess() {
        AsyncRun.runInMain(new o(this));
    }

    public void onFailure(int i) {
        this.d.complete(this.b, ResponseInfo.invalidToken("invalid token"), null);
    }
}
