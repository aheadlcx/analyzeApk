package qsbk.app.share.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class a extends BroadcastReceiver {
    final /* synthetic */ ChooseQiuyouFragment a;

    a(ChooseQiuyouFragment chooseQiuyouFragment) {
        this.a = chooseQiuyouFragment;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.getActivity().finish();
    }
}
