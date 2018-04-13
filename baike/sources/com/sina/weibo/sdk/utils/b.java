package com.sina.weibo.sdk.utils;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.AidTask.AidInfo;

class b implements Runnable {
    final /* synthetic */ AidTask a;

    b(AidTask aidTask) {
        this.a = aidTask;
    }

    public void run() {
        if (this.a.e.tryLock()) {
            AidInfo a = this.a.a();
            if (a == null) {
                int i = 1;
                do {
                    i++;
                    try {
                        String b = this.a.b();
                        AidInfo parseJson = AidInfo.parseJson(b);
                        this.a.b(b);
                        this.a.d = parseJson;
                        this.a.a(this.a.d);
                        break;
                    } catch (WeiboException e) {
                        LogUtil.e("AidTask", "AidTaskInit WeiboException Msg : " + e.getMessage());
                        if (i >= 3) {
                        }
                    }
                } while (i >= 3);
            } else {
                this.a.d = a;
            }
            this.a.e.unlock();
            return;
        }
        LogUtil.e("AidTask", "tryLock : false, return");
    }
}
