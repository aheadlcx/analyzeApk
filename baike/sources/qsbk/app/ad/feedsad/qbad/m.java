package qsbk.app.ad.feedsad.qbad;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoImmersionCircleActivity;
import qsbk.app.model.CircleArticle;

class m implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ QbAdItem b;

    m(QbAdItem qbAdItem, CircleArticle circleArticle) {
        this.b = qbAdItem;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        VideoImmersionCircleActivity.launch(view.getContext(), this.a, 0, this.a.id, false, true);
        this.b.addAdActionCount();
    }
}
