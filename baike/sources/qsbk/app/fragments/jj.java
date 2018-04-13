package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.QiushiTopicListActivity;

class jj implements OnClickListener {
    final /* synthetic */ QiushiTopicTabFragment a;

    jj(QiushiTopicTabFragment qiushiTopicTabFragment) {
        this.a = qiushiTopicTabFragment;
    }

    public void onClick(View view) {
        QiushiTopicListActivity.launch(this.a.getActivity(), 0);
    }
}
