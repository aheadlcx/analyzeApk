package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;

class gl implements OnClickListener {
    final /* synthetic */ MyProfileFragment a;

    gl(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onClick(View view) {
        if (this.a.t != null) {
            StatSDK.onEvent(view.getContext(), "my_splash_ad_click", String.valueOf(this.a.t.id));
            this.a.t.onActionClick(this.a.getActivity());
        }
    }
}
