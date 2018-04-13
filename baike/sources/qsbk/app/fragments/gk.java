package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class gk extends BroadcastReceiver {
    final /* synthetic */ MyProfileFragment a;

    gk(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.i = true;
    }
}
