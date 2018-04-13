package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class eg implements OnClickListener {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    eg(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.getActivity(), 11);
    }
}
