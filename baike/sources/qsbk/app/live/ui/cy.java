package qsbk.app.live.ui;

import android.support.v4.app.FragmentActivity;

class cy implements Runnable {
    final /* synthetic */ cx a;

    cy(cx cxVar) {
        this.a = cxVar;
    }

    public void run() {
        FragmentActivity activity = this.a.b.b.getActivity();
        if (activity == null) {
            return;
        }
        if (activity instanceof LivePullActivity) {
            ((LivePullActivity) activity).toCloseLive();
        } else if (activity instanceof LivePushActivity) {
            ((LivePushActivity) activity).doCloseLive(false, false);
        }
    }
}
