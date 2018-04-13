package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class ed implements OnClickListener {
    final /* synthetic */ LaiseeVoiceGetFragment a;

    ed(LaiseeVoiceGetFragment laiseeVoiceGetFragment) {
        this.a = laiseeVoiceGetFragment;
    }

    public void onClick(View view) {
        BindPhoneActivity.launch(this.a.getActivity());
        this.a.getActivity().finish();
    }
}
