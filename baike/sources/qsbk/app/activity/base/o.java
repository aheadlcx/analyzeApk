package qsbk.app.activity.base;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;

class o implements OnClickListener {
    final /* synthetic */ BaseArticleListViewFragment a;

    o(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void onClick(View view) {
        FragmentActivity activity = this.a.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).goTab(MainActivity.TAB_QIUYOUCIRCLE_ID);
        }
    }
}
