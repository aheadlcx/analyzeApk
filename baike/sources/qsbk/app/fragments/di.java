package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeDetailActivity;

class di implements OnClickListener {
    final /* synthetic */ LaiseeGetErrorFragment a;

    di(LaiseeGetErrorFragment laiseeGetErrorFragment) {
        this.a = laiseeGetErrorFragment;
    }

    public void onClick(View view) {
        LaiseeDetailActivity.launch(this.a.getActivity(), this.a.b);
        this.a.getActivity().finish();
    }
}
