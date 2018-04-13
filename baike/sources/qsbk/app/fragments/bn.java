package qsbk.app.fragments;

import java.util.Collection;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager.CallBack;

class bn implements CallBack {
    final /* synthetic */ CircleTopicsFragment a;

    bn(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onSuccess(Collection<CircleTopic> collection) {
        if (!CircleTopicsFragment.q(this.a)) {
            CircleTopicsFragment.i(this.a);
        }
    }

    public void onFailure(int i, String str) {
    }
}
