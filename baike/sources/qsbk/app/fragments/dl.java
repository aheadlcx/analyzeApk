package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class dl implements OnClickListener {
    final /* synthetic */ LaiseeNormalGetFragment a;

    dl(LaiseeNormalGetFragment laiseeNormalGetFragment) {
        this.a = laiseeNormalGetFragment;
    }

    public void onClick(View view) {
        this.a.getActivity().finish();
    }
}
