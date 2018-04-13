package com.sprite.ads.third;

import android.os.Handler;
import android.os.Looper;
import com.sprite.ads.SpriteAD;
import com.sprite.ads.internal.utils.AdUtil;

public abstract class RefreshTask implements Runnable {
    private Handler handler = new Handler(Looper.getMainLooper());
    private int refreshTime = ((this.second * 60) * 5);
    private int second = 1000;

    public void cancel() {
        this.handler.removeCallbacks(this);
    }

    public abstract void refreshData();

    public void run() {
        if (AdUtil.isRunningForeground(SpriteAD.getApplicationContext())) {
            refreshData();
        }
        this.handler.postDelayed(this, (long) this.refreshTime);
    }

    public void setRefreshTime(int i) {
        this.refreshTime = this.second * i;
    }

    public void start() {
        this.handler.post(new Runnable() {
            public void run() {
                RefreshTask.this.refreshData();
            }
        });
        startDelayed((long) this.refreshTime);
    }

    public void startDelayed(long j) {
        this.handler.postDelayed(this, j);
    }
}
