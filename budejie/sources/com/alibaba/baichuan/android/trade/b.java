package com.alibaba.baichuan.android.trade;

import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;

final class b implements Runnable {
    final /* synthetic */ AlibcTradeInitCallback a;

    b(AlibcTradeInitCallback alibcTradeInitCallback) {
        this.a = alibcTradeInitCallback;
    }

    public void run() {
        AlibcTradeSDK.a(this.a);
    }
}
