package com.alibaba.baichuan.android.trade.config;

import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.config.b.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

class AlibcConfig$a implements a {
    final /* synthetic */ AlibcConfig a;

    AlibcConfig$a(AlibcConfig alibcConfig) {
        this.a = alibcConfig;
    }

    public void a(com.alibaba.baichuan.android.trade.config.a.a aVar, String str) {
        AlibcConfig.c(this.a).a(aVar);
        AlibcConfig.d(this.a).a(aVar);
        AlibcUserTracker instance = AlibcUserTracker.getInstance();
        if (instance != null) {
            instance.setSampling();
        }
        synchronized (AlibcConfig.class) {
            this.a.expiredTimeStamp = System.currentTimeMillis() + 21600000;
            AlibcLogger.d("AlibcConfig", "当前的时间为过期时间戳为" + this.a.expiredTimeStamp);
        }
    }

    public void a(String str) {
    }
}
