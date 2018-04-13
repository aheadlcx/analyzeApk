package com.qiniu.android.storage;

import com.qiniu.android.http.ResponseInfo;

final class k implements Runnable {
    final /* synthetic */ UpCompletionHandler a;
    final /* synthetic */ String b;
    final /* synthetic */ ResponseInfo c;

    k(UpCompletionHandler upCompletionHandler, String str, ResponseInfo responseInfo) {
        this.a = upCompletionHandler;
        this.b = str;
        this.c = responseInfo;
    }

    public void run() {
        this.a.complete(this.b, this.c, null);
    }
}
