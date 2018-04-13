package com.sina.weibo.sdk.web.view;

class a implements Runnable {
    final /* synthetic */ LoadingBar a;

    a(LoadingBar loadingBar) {
        this.a = loadingBar;
    }

    public void run() {
        this.a.a = this.a.a + 1;
        this.a.drawProgress(this.a.a);
    }
}
