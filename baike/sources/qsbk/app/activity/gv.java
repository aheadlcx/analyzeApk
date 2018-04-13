package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class gv implements OnClickListener {
    final /* synthetic */ CircleArticleImageViewer a;

    gv(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            CircleArticleActivity.launch(view.getContext(), this.a.i, true);
        } else {
            CircleArticleActivity.launch(view.getContext(), this.a.i, true);
        }
        this.a.finish();
    }
}
