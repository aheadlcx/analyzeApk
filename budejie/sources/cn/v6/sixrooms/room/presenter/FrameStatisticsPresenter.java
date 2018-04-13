package cn.v6.sixrooms.room.presenter;

import android.os.Handler;
import cn.v6.sixrooms.avsolution.common.PlayerCallBack;
import cn.v6.sixrooms.room.engine.FrameStatisticsEngine;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.RandomUtils;
import cn.v6.sixrooms.view.interfaces.OnActivityListener;
import java.net.URLEncoder;

public class FrameStatisticsPresenter implements PlayerCallBack, OnActivityListener {
    private static int a = 40;
    private static int b = 20;
    private static final String p = FrameStatisticsPresenter.class.getSimpleName();
    private a c;
    private StringBuilder d = new StringBuilder();
    private int e = 0;
    private int f = 0;
    private FrameStatisticsEngine g;
    private long h = 0;
    private long i = 0;
    private long j = 0;
    private int k = 0;
    private String l;
    private String m;
    private Handler n = new Handler();
    private boolean o = false;

    private class a extends Thread {
        final /* synthetic */ FrameStatisticsPresenter a;

        private a(FrameStatisticsPresenter frameStatisticsPresenter) {
            this.a = frameStatisticsPresenter;
        }

        public final void run() {
            while (!Thread.currentThread().isInterrupted() && this.a.o) {
                if (this.a.e == this.a.f) {
                    LogUtils.d(FrameStatisticsPresenter.p, "sendFrameStatistics22222");
                    this.a.e();
                    if (this.a.d.length() == 0) {
                        this.a.d;
                    }
                    this.a.n.post(new b(this));
                    this.a.e = 0;
                } else {
                    this.a.e = this.a.e + 1;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setParameter(String str, String str2) {
        this.l = str;
        this.m = str2.substring(0, str2.lastIndexOf(":"));
    }

    private static int b() {
        int limitRandom = RandomUtils.getLimitRandom(b, a);
        LogUtils.d(p, "getPeriod---period--" + limitRandom);
        return limitRandom;
    }

    public void onBufferEmpty() {
        LogUtils.d(p, "onBufferEmpty---");
        if (this.c != null) {
            this.h = System.currentTimeMillis();
            this.k = this.e;
        }
    }

    public void onBufferLoad() {
        LogUtils.d(p, "onBufferLoad---");
        c();
    }

    public void onError(int i) {
    }

    public void onVideoEnd() {
        c();
    }

    public void onVideoSizeChange(int i, int i2) {
        c();
    }

    private synchronized void c() {
        if (this.c == null) {
            this.f = b();
            this.j = System.currentTimeMillis();
            this.c = new a();
            this.c.start();
            this.o = true;
        }
        this.i = System.currentTimeMillis();
        d();
    }

    public void onActivityCreate() {
    }

    public void onActivityDestrory() {
        if (this.c != null) {
            this.c.interrupt();
            this.c = null;
        }
        this.o = false;
    }

    private void d() {
        if (this.h > 0 && this.i > 0 && this.i > this.h) {
            LogUtils.d("SixPlayer", "sendFrameStatistics---data--");
            int i = (int) (this.i - this.h);
            LogUtils.d(p, "mStrBuilder---" + this.d.toString());
            if (this.d.length() == 0) {
                this.d.append(this.k + ":" + i);
            } else {
                this.d.append("," + this.k + ":" + i);
            }
            this.i = 0;
            this.h = 0;
            this.k = 0;
        }
    }

    private synchronized void e() {
        if (this.h > 0 && this.i == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            this.i = currentTimeMillis;
            d();
            this.h = currentTimeMillis;
        }
        this.f = b();
        this.k = 0;
    }

    static /* synthetic */ void a(FrameStatisticsPresenter frameStatisticsPresenter, String str) {
        if (frameStatisticsPresenter.g == null) {
            frameStatisticsPresenter.g = new FrameStatisticsEngine(new a(frameStatisticsPresenter));
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = (((int) (currentTimeMillis - frameStatisticsPresenter.j)) / 1000);
        frameStatisticsPresenter.j = currentTimeMillis;
        LogUtils.d(p, "sendFrameStatistics---data--" + str);
        frameStatisticsPresenter.g.sendFrameStatistics(URLEncoder.encode(str), str2, frameStatisticsPresenter.l, frameStatisticsPresenter.m, LoginUtils.getLoginUID());
    }
}
