package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class qn implements OnClickListener {
    final /* synthetic */ LaiseeDetailActivity a;

    qn(LaiseeDetailActivity laiseeDetailActivity) {
        this.a = laiseeDetailActivity;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.a, this.a.f.circleTopicId);
    }
}
