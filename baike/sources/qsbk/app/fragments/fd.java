package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LiveFollowActivity;

class fd implements OnClickListener {
    final /* synthetic */ LiveTabsFragment a;

    fd(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onClick(View view) {
        this.a.f();
        LiveFollowActivity.launch(this.a.getActivity());
    }
}
