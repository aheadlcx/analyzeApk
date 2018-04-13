package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class dn implements OnClickListener {
    final /* synthetic */ LaiseeNormalGetFragment a;

    dn(LaiseeNormalGetFragment laiseeNormalGetFragment) {
        this.a = laiseeNormalGetFragment;
    }

    public void onClick(View view) {
        BindPhoneActivity.launch(this.a.getActivity());
        this.a.getActivity().finish();
    }
}
