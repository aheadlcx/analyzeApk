package com.alibaba.baichuan.android.trade.c.a.a;

import com.alibaba.baichuan.android.trade.c.a.a.b.a;
import com.alibaba.baichuan.android.trade.c.a.a.b.c;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.tencent.stat.DeviceInfo;
import java.util.concurrent.CountDownLatch;

class f implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ a b;
    final /* synthetic */ c c;
    final /* synthetic */ CountDownLatch d;
    final /* synthetic */ e e;

    f(e eVar, a aVar, a aVar2, c cVar, CountDownLatch countDownLatch) {
        this.e = eVar;
        this.a = aVar;
        this.b = aVar2;
        this.c = cVar;
        this.d = countDownLatch;
    }

    public void run() {
        try {
            this.a.a = Boolean.valueOf(this.b.a(this.c));
        } catch (Throwable th) {
            String str = DeviceInfo.TAG_IMEI;
            StringBuilder append = new StringBuilder().append("fail to execute the Handler ");
            String str2 = (this.b == null || this.b.a() == null) ? null : this.b.a().a;
            AlibcLogger.e(str, append.append(str2).toString(), th);
        } finally {
            this.d.countDown();
        }
    }
}
