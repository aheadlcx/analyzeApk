package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class hy implements OnClickListener {
    final /* synthetic */ QiushiListFragment a;

    hy(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onClick(View view) {
        this.a.startAudit();
    }
}
