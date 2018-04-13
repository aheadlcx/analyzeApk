package com.tencent.smtt.utils;

class h implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ f b;

    h(f fVar, int i) {
        this.b = fVar;
        this.a = i;
    }

    public void run() {
        this.b.e.setText("已下载" + this.a + "%");
    }
}
