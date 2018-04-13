package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;

class ij implements OnClickListener {
    final /* synthetic */ QiushiListFragment a;

    ij(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onClick(View view) {
        if (this.a.w != null) {
            StatSDK.onEvent(view.getContext(), "qiushi_splash_ad_click", String.valueOf(this.a.w.id));
            this.a.w.onActionClick(this.a.getActivity());
        }
    }
}
