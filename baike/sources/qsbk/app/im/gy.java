package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;

class gy implements OnClickListener {
    final /* synthetic */ IMMessageListFragment a;

    gy(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onClick(View view) {
        if (this.a.G != null) {
            StatSDK.onEvent(view.getContext(), "im_splash_ad_click", String.valueOf(this.a.G.id));
            this.a.G.onActionClick(this.a.getActivity());
        }
    }
}
