package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.WalletActivity;

class dj implements OnClickListener {
    final /* synthetic */ LaiseeGetErrorFragment a;

    dj(LaiseeGetErrorFragment laiseeGetErrorFragment) {
        this.a = laiseeGetErrorFragment;
    }

    public void onClick(View view) {
        WalletActivity.launch(this.a.getActivity());
        this.a.getActivity().finish();
    }
}
