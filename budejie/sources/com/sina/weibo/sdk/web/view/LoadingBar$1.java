package com.sina.weibo.sdk.web.view;

class LoadingBar$1 implements Runnable {
    final /* synthetic */ LoadingBar a;

    LoadingBar$1(LoadingBar loadingBar) {
        this.a = loadingBar;
    }

    public void run() {
        LoadingBar.a(this.a);
        this.a.a(LoadingBar.b(this.a));
    }
}
