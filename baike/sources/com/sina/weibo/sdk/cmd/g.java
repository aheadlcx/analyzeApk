package com.sina.weibo.sdk.cmd;

import android.content.SharedPreferences;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.AesEncrypt;
import com.sina.weibo.sdk.utils.LogUtil;

class g implements Runnable {
    final /* synthetic */ SharedPreferences a;
    final /* synthetic */ WbAppActivator b;

    g(WbAppActivator wbAppActivator, SharedPreferences sharedPreferences) {
        this.b = wbAppActivator;
        this.a = sharedPreferences;
    }

    public void run() {
        WeiboException weiboException;
        Throwable th;
        LogUtil.v(WbAppActivator.a, "mLock.isLocked()--->" + this.b.e.isLocked());
        if (this.b.e.tryLock()) {
            f fVar = null;
            try {
                f fVar2;
                String a = WbAppActivator.b(this.b.b, this.b.d);
                if (a != null) {
                    fVar2 = new f(AesEncrypt.Decrypt(a));
                    try {
                        this.b.a(fVar2.getInstallCmds());
                        this.b.b(fVar2.getInvokeCmds());
                    } catch (WeiboException e) {
                        WeiboException weiboException2 = e;
                        fVar = fVar2;
                        weiboException = weiboException2;
                        try {
                            LogUtil.e(WbAppActivator.a, weiboException.getMessage());
                            this.b.e.unlock();
                            if (fVar != null) {
                                a.saveFrequency(this.b.b, this.a, (long) fVar.getFrequency());
                                a.saveLastTime(this.b.b, this.a, System.currentTimeMillis());
                            }
                            LogUtil.v(WbAppActivator.a, "after unlock \n mLock.isLocked()--->" + this.b.e.isLocked());
                        } catch (Throwable th2) {
                            th = th2;
                            this.b.e.unlock();
                            if (fVar != null) {
                                a.saveFrequency(this.b.b, this.a, (long) fVar.getFrequency());
                                a.saveLastTime(this.b.b, this.a, System.currentTimeMillis());
                            }
                            LogUtil.v(WbAppActivator.a, "after unlock \n mLock.isLocked()--->" + this.b.e.isLocked());
                            throw th;
                        }
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        fVar = fVar2;
                        th = th4;
                        this.b.e.unlock();
                        if (fVar != null) {
                            a.saveFrequency(this.b.b, this.a, (long) fVar.getFrequency());
                            a.saveLastTime(this.b.b, this.a, System.currentTimeMillis());
                        }
                        LogUtil.v(WbAppActivator.a, "after unlock \n mLock.isLocked()--->" + this.b.e.isLocked());
                        throw th;
                    }
                }
                fVar2 = null;
                this.b.e.unlock();
                if (fVar2 != null) {
                    a.saveFrequency(this.b.b, this.a, (long) fVar2.getFrequency());
                    a.saveLastTime(this.b.b, this.a, System.currentTimeMillis());
                }
                LogUtil.v(WbAppActivator.a, "after unlock \n mLock.isLocked()--->" + this.b.e.isLocked());
            } catch (WeiboException e2) {
                weiboException = e2;
                LogUtil.e(WbAppActivator.a, weiboException.getMessage());
                this.b.e.unlock();
                if (fVar != null) {
                    a.saveFrequency(this.b.b, this.a, (long) fVar.getFrequency());
                    a.saveLastTime(this.b.b, this.a, System.currentTimeMillis());
                }
                LogUtil.v(WbAppActivator.a, "after unlock \n mLock.isLocked()--->" + this.b.e.isLocked());
            }
        }
    }
}
