package com.alibaba.baichuan.android.trade.config;

class a implements Runnable {
    final /* synthetic */ AlibcConfig a;

    a(AlibcConfig alibcConfig) {
        this.a = alibcConfig;
    }

    public void run() {
        if (AlibcConfig.a(this.a) != null) {
            AlibcConfig.a(this.a).a();
        }
        AlibcConfig.b(this.a).postDelayed(this.a.a, 1800000);
    }
}
