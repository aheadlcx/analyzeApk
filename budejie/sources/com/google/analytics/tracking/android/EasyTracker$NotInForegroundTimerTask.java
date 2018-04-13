package com.google.analytics.tracking.android;

import java.util.TimerTask;

class EasyTracker$NotInForegroundTimerTask extends TimerTask {
    final /* synthetic */ EasyTracker this$0;

    private EasyTracker$NotInForegroundTimerTask(EasyTracker easyTracker) {
        this.this$0 = easyTracker;
    }

    public void run() {
        EasyTracker.access$102(this.this$0, false);
    }
}
