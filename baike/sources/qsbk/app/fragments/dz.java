package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class dz implements OnClickListener {
    final /* synthetic */ LaiseeVoiceGetFragment a;

    dz(LaiseeVoiceGetFragment laiseeVoiceGetFragment) {
        this.a = laiseeVoiceGetFragment;
    }

    public void onClick(View view) {
        this.a.getActivity().finish();
    }
}
