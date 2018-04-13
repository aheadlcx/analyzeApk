package qsbk.app.ad.feedsad.qbad;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class h implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ QbAdItem b;

    h(QbAdItem qbAdItem, CircleArticle circleArticle) {
        this.b = qbAdItem;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(Util.getActivityOrContext(view), this.a.topic, -1);
    }
}
