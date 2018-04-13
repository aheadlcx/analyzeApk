package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class dd implements OnClickListener {
    final /* synthetic */ LaiseeEventGetFragment a;

    dd(LaiseeEventGetFragment laiseeEventGetFragment) {
        this.a = laiseeEventGetFragment;
    }

    public void onClick(View view) {
        BindPhoneActivity.launch(this.a.getActivity());
        this.a.getActivity().finish();
    }
}
