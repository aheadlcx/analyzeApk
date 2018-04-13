package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;

class gx implements OnClickListener {
    final /* synthetic */ CircleArticleImageViewer a;

    gx(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public void onClick(View view) {
        this.a.onCircleShareStart(this.a.i, ShareUtils$OnCircleShareStartListener.TYPE_SHARE, null);
    }
}
