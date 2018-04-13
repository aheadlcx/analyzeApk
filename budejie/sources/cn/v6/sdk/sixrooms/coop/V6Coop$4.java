package cn.v6.sdk.sixrooms.coop;

import android.app.Activity;

class V6Coop$4 implements Runnable {
    final /* synthetic */ V6Coop this$0;
    final /* synthetic */ Activity val$activity;

    V6Coop$4(V6Coop v6Coop, Activity activity) {
        this.this$0 = v6Coop;
        this.val$activity = activity;
    }

    public void run() {
        if (V6Coop.access$000(this.this$0)) {
            this.this$0.showLoadingActivity(this.val$activity);
        }
    }
}
