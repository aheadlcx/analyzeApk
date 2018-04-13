package com.sina.weibo.sdk.utils;

import android.os.Message;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.AidTask.AidInfo;
import com.sina.weibo.sdk.utils.AidTask.AidResultCallBack;

class c implements Runnable {
    final /* synthetic */ AidResultCallBack a;
    final /* synthetic */ AidTask b;

    c(AidTask aidTask, AidResultCallBack aidResultCallBack) {
        this.b = aidTask;
        this.a = aidResultCallBack;
    }

    public void run() {
        this.b.e.lock();
        Object a = this.b.a();
        Object obj = null;
        if (a == null) {
            try {
                String b = this.b.b();
                a = AidInfo.parseJson(b);
                this.b.b(b);
                this.b.d = a;
            } catch (WeiboException e) {
                obj = e;
                LogUtil.e("AidTask", "AidTaskInit WeiboException Msg : " + obj.getMessage());
            }
        }
        this.b.e.unlock();
        Message obtain = Message.obtain();
        if (a != null) {
            obtain.what = 1001;
            obtain.obj = a;
        } else {
            obtain.what = 1002;
            obtain.obj = obj;
        }
        this.b.f.setCallback(this.a);
        this.b.f.sendMessage(obtain);
    }
}
