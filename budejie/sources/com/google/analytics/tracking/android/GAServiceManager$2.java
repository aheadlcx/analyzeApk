package com.google.analytics.tracking.android;

import android.os.Handler.Callback;
import android.os.Message;

class GAServiceManager$2 implements Callback {
    final /* synthetic */ GAServiceManager this$0;

    GAServiceManager$2(GAServiceManager gAServiceManager) {
        this.this$0 = gAServiceManager;
    }

    public boolean handleMessage(Message message) {
        if (1 == message.what && GAServiceManager.access$100().equals(message.obj)) {
            GAUsage.getInstance().setDisableUsage(true);
            this.this$0.dispatchLocalHits();
            GAUsage.getInstance().setDisableUsage(false);
            if (GAServiceManager.access$200(this.this$0) > 0 && !GAServiceManager.access$300(this.this$0)) {
                GAServiceManager.access$400(this.this$0).sendMessageDelayed(GAServiceManager.access$400(this.this$0).obtainMessage(1, GAServiceManager.access$100()), (long) (GAServiceManager.access$200(this.this$0) * 1000));
            }
        }
        return true;
    }
}
