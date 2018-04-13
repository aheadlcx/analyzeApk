package com.qiniu.android.storage;

import com.qiniu.android.common.Zone.QueryHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AsyncRun;
import java.io.File;

class r implements QueryHandler {
    final /* synthetic */ File a;
    final /* synthetic */ String b;
    final /* synthetic */ UpToken c;
    final /* synthetic */ UpCompletionHandler d;
    final /* synthetic */ UploadOptions e;
    final /* synthetic */ UploadManager f;

    r(UploadManager uploadManager, File file, String str, UpToken upToken, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        this.f = uploadManager;
        this.a = file;
        this.b = str;
        this.c = upToken;
        this.d = upCompletionHandler;
        this.e = uploadOptions;
    }

    public void onSuccess() {
        if (this.a.length() <= ((long) this.f.a.putThreshold)) {
            b.a(this.f.b, this.f.a, this.a, this.b, this.c, this.d, this.e);
            return;
        }
        AsyncRun.runInMain(new f(this.f.b, this.f.a, this.a, this.b, this.c, this.d, this.e, this.f.a.keyGen.gen(this.b, this.a)));
    }

    public void onFailure(int i) {
        this.d.complete(this.b, ResponseInfo.invalidToken("invalid token"), null);
    }
}
