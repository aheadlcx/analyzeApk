package qsbk.app.live.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class ap implements OnEditorActionListener {
    final /* synthetic */ LiveBaseActivity a;

    ap(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.a.r();
        return true;
    }
}
