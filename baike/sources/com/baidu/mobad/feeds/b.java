package com.baidu.mobad.feeds;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.a.d.onNativeFail(NativeErrorCode.LOAD_AD_FAILED);
    }
}
