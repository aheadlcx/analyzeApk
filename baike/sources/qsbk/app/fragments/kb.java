package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;

class kb implements OnClickListener {
    final /* synthetic */ QiuyouCircleFragment a;

    kb(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onClick(View view) {
        if (this.a.x != null) {
            StatSDK.onEvent(view.getContext(), "circle_splash_ad_click", String.valueOf(this.a.x.id));
            this.a.x.onActionClick(this.a.getActivity());
        }
    }
}
