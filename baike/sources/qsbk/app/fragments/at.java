package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicRecommendActivity;

class at implements OnClickListener {
    final /* synthetic */ CircleTopicWeeklyFragment a;

    at(CircleTopicWeeklyFragment circleTopicWeeklyFragment) {
        this.a = circleTopicWeeklyFragment;
    }

    public void onClick(View view) {
        CircleTopicRecommendActivity.launch(this.a.getActivity());
    }
}
