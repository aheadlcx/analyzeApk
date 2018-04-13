package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class as implements OnClickListener {
    final /* synthetic */ CircleTopicWeeklyFragment a;

    as(CircleTopicWeeklyFragment circleTopicWeeklyFragment) {
        this.a = circleTopicWeeklyFragment;
    }

    public void onClick(View view) {
        this.a.getActivity().onBackPressed();
    }
}
