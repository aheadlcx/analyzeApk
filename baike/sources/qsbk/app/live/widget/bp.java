package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.PreferenceUtils;

class bp implements OnClickListener {
    final /* synthetic */ long a;
    final /* synthetic */ GameBetButton b;

    bp(GameBetButton gameBetButton, long j) {
        this.b = gameBetButton;
        this.a = j;
    }

    public void onClick(View view) {
        for (View selected : this.b.a) {
            selected.setSelected(false);
        }
        this.b.b = view;
        this.b.b.setSelected(true);
        this.b.c = this.a;
        PreferenceUtils.instance().putLong("bet_option_selected_id", this.b.c);
    }
}
