package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class dh implements OnClickListener {
    final /* synthetic */ LaiseeGetErrorFragment a;

    dh(LaiseeGetErrorFragment laiseeGetErrorFragment) {
        this.a = laiseeGetErrorFragment;
    }

    public void onClick(View view) {
        this.a.getActivity().finish();
    }
}
