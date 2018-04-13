package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class en implements OnClickListener {
    final /* synthetic */ GuessBetView a;

    en(GuessBetView guessBetView) {
        this.a = guessBetView;
    }

    public void onClick(View view) {
        if (this.a.mListenner != null) {
            this.a.mListenner.clickListenner(this.a.useable, this.a.getBigRoleId());
        }
    }
}
