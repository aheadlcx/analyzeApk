package qsbk.app.fragments;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;

class dp implements OnClickListener {
    final /* synthetic */ LaiseeNormalGetFragment a;

    dp(LaiseeNormalGetFragment laiseeNormalGetFragment) {
        this.a = laiseeNormalGetFragment;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.g.circleTopicId)) {
            CircleTopicActivity.launch(this.a.getActivity(), this.a.g.circleTopicId);
        }
    }
}
