package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeDetailActivity;

class do implements OnClickListener {
    final /* synthetic */ LaiseeNormalGetFragment a;

    do(LaiseeNormalGetFragment laiseeNormalGetFragment) {
        this.a = laiseeNormalGetFragment;
    }

    public void onClick(View view) {
        LaiseeDetailActivity.launch(this.a.getActivity(), this.a.g);
        this.a.getActivity().finish();
    }
}
