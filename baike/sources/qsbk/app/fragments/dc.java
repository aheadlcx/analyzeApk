package qsbk.app.fragments;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;

class dc implements OnClickListener {
    final /* synthetic */ LaiseeEventGetFragment a;

    dc(LaiseeEventGetFragment laiseeEventGetFragment) {
        this.a = laiseeEventGetFragment;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.j.circleArticleId)) {
            CircleArticleActivity.launch(this.a.getActivity(), this.a.j.circleArticleId, false);
        }
    }
}
