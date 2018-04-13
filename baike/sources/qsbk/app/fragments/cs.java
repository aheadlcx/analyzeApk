package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class cs extends BroadcastReceiver {
    final /* synthetic */ HotCommentCircleFragment a;

    cs(HotCommentCircleFragment hotCommentCircleFragment) {
        this.a = hotCommentCircleFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.e != null && this.a.j != null) {
            this.a.notifyAdapterDataChanged();
        }
    }
}
