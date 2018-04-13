package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

class gg implements OnClickListener {
    final /* synthetic */ CircleArticleActivity a;

    gg(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onClick(View view) {
        this.a.T = null;
        this.a.G.setHint(R.string.comment_input_hint);
        this.a.U.setVisibility(8);
    }
}
