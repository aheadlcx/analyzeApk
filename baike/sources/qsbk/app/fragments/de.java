package qsbk.app.fragments;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;

class de implements OnClickListener {
    final /* synthetic */ LaiseeEventGetFragment a;

    de(LaiseeEventGetFragment laiseeEventGetFragment) {
        this.a = laiseeEventGetFragment;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.j.circleTopicId)) {
            CircleTopicActivity.launch(this.a.getActivity(), this.a.j.circleTopicId);
        }
    }
}
