package qsbk.app.fragments;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;

class hn implements OnClickListener {
    final /* synthetic */ NewsFragment a;

    hn(NewsFragment newsFragment) {
        this.a = newsFragment;
    }

    public void onClick(View view) {
        FragmentActivity activity = this.a.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).goTab(MainActivity.TAB_QIUYOUCIRCLE_ID);
        }
    }
}
