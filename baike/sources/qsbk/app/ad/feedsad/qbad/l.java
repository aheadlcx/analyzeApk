package qsbk.app.ad.feedsad.qbad;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;

class l implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ QbAdItem b;

    l(QbAdItem qbAdItem, CircleArticle circleArticle) {
        this.b = qbAdItem;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
        } else {
            CircleArticleActivity.launch(view.getContext(), this.a, false);
        }
    }
}
