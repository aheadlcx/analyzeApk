package com.qiniu.android.http;

final class c implements Runnable {
    final /* synthetic */ CompletionHandler a;
    final /* synthetic */ ResponseInfo b;

    c(CompletionHandler completionHandler, ResponseInfo responseInfo) {
        this.a = completionHandler;
        this.b = responseInfo;
    }

    public void run() {
        this.a.complete(this.b, this.b.response);
    }
}
