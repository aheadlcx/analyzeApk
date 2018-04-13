package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class hx implements OnClickListener {
    final /* synthetic */ QiushiListFragment a;

    hx(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onClick(View view) {
        this.a.startPublish();
    }
}
