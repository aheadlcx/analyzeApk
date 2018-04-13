package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class cp implements OnClickListener {
    final /* synthetic */ FollowLiveFragment a;

    cp(FollowLiveFragment followLiveFragment) {
        this.a = followLiveFragment;
    }

    public void onClick(View view) {
        LoginPermissionClickDelegate.startLoginActivity(this.a.getActivity());
    }
}
