package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

class fg implements OnClickListener {
    final /* synthetic */ TextView a;
    final /* synthetic */ LiveUserLevelActivity b;

    fg(LiveUserLevelActivity liveUserLevelActivity, TextView textView) {
        this.b = liveUserLevelActivity;
        this.a = textView;
    }

    public void onClick(View view) {
        if (this.b.t != null) {
            this.b.t.setVisibility(4);
        }
        this.a.setVisibility(0);
        this.b.t = this.a;
    }
}
