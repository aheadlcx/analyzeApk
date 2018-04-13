package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class eo implements OnClickListener {
    final /* synthetic */ GuessBetView a;

    eo(GuessBetView guessBetView) {
        this.a = guessBetView;
    }

    public void onClick(View view) {
        if (this.a.mListenner != null) {
            this.a.mListenner.clickListenner(this.a.useable, this.a.getSmallRoleId());
        }
    }
}
