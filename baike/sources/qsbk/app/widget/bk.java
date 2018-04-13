package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;

class bk implements OnClickListener {
    final /* synthetic */ CircleVideoCell a;

    bk(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
        } else {
            CircleArticleActivity.launch(view.getContext(), this.a.getItem(), false);
        }
    }
}
