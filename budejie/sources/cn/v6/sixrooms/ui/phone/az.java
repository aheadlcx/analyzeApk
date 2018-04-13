package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class az implements OnClickListener {
    final /* synthetic */ HistoryActivity a;

    az(HistoryActivity historyActivity) {
        this.a = historyActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
