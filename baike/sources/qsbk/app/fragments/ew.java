package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import qsbk.app.activity.MainActivity;

class ew extends BroadcastReceiver {
    final /* synthetic */ LiveTabsFragment a;

    ew(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.v = false;
        this.a.x = this.a.x + 1;
        this.a.g();
        FragmentActivity activity = this.a.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).checkLiveBeginUnread();
        }
    }
}
