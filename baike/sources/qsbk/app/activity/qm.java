package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class qm implements OnClickListener {
    final /* synthetic */ LaiseeDetailActivity a;

    qm(LaiseeDetailActivity laiseeDetailActivity) {
        this.a = laiseeDetailActivity;
    }

    public void onClick(View view) {
        CircleArticleActivity.launch(this.a, this.a.f.circleArticleId, true);
    }
}
