package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;
import qsbk.app.live.R;

class cf extends AnimatorListenerAdapter {
    final /* synthetic */ TextView a;
    final /* synthetic */ LiveBaseActivity b;

    cf(LiveBaseActivity liveBaseActivity, TextView textView) {
        this.b = liveBaseActivity;
        this.a = textView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.setText(R.string.live_red_envelopes_rob);
    }
}
