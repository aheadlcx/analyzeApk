package qsbk.app.activity;

import android.os.SystemClock;
import qsbk.app.im.MessageCountManager;
import qsbk.app.utils.SharePreferenceUtils;

public class MainActivity$ShowTipsRunnable implements Runnable {
    final /* synthetic */ MainActivity a;
    private long b = 0;
    private int c;
    private int d;

    public MainActivity$ShowTipsRunnable(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void setCount(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public void run() {
        int sharePreferencesIntValue = SharePreferenceUtils.getSharePreferencesIntValue(MessageCountManager.NEWFANS_COUNT);
        if (this.c > 0 || sharePreferencesIntValue > 0) {
            this.a.setTips(MainActivity.TAB_MESSAGE_ID, this.c + sharePreferencesIntValue > 99 ? "99+" : (sharePreferencesIntValue + this.c) + "");
            this.a.hideSmallTips(MainActivity.TAB_MESSAGE_ID);
        } else {
            if (this.d > 0) {
                this.a.setSmallTips(MainActivity.TAB_MESSAGE_ID);
            } else {
                this.a.hideSmallTips(MainActivity.TAB_MESSAGE_ID);
            }
            this.a.hideTips(MainActivity.TAB_MESSAGE_ID);
        }
        this.b = SystemClock.uptimeMillis();
    }

    public void fire() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.b == 0) {
            this.b = uptimeMillis;
            this.a.getMainUIHandler().removeCallbacks(this);
            this.a.getMainUIHandler().post(this);
        } else if (uptimeMillis - this.b > 500) {
            this.a.getMainUIHandler().removeCallbacks(this);
            this.a.getMainUIHandler().post(this);
            this.b = SystemClock.uptimeMillis();
        } else {
            this.a.getMainUIHandler().removeCallbacks(this);
            this.a.getMainUIHandler().postDelayed(this, 500 - (uptimeMillis - this.b));
        }
    }
}
