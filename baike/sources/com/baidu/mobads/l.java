package com.baidu.mobads;

final class l implements Runnable {
    final /* synthetic */ Runnable a;

    l(Runnable runnable) {
        this.a = runnable;
    }

    public void run() {
        this.a.run();
    }
}
