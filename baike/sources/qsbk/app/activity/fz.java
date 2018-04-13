package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class fz implements OnClickListener {
    final /* synthetic */ CircleArticleActivity a;

    fz(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onClick(View view) {
        this.a.w.cancel();
    }
}
