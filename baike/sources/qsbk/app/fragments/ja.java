package qsbk.app.fragments;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;

class ja implements OnClickListener {
    final /* synthetic */ QiushiTopicListFragment a;

    ja(QiushiTopicListFragment qiushiTopicListFragment) {
        this.a = qiushiTopicListFragment;
    }

    public void onClick(View view) {
        FragmentActivity activity = this.a.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).goTab(MainActivity.TAB_QIUYOUCIRCLE_ID);
        }
    }
}
