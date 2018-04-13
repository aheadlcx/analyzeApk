package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ge implements OnClickListener {
    final /* synthetic */ CircleArticleActivity a;

    ge(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onClick(View view) {
        if (this.a.l != null) {
            this.a.f();
        }
    }
}
