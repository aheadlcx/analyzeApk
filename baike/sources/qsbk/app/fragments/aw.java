package qsbk.app.fragments;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;

class aw implements OnClickListener {
    final /* synthetic */ CircleTopicWeeklyFragment a;

    aw(CircleTopicWeeklyFragment circleTopicWeeklyFragment) {
        this.a = circleTopicWeeklyFragment;
    }

    public void onClick(View view) {
        FragmentActivity activity = this.a.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).goTab(MainActivity.TAB_QIUYOUCIRCLE_ID);
        }
    }
}
