package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class gc implements OnClickListener {
    final /* synthetic */ CircleArticleActivity a;

    gc(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onClick(View view) {
        this.a.x();
    }
}
