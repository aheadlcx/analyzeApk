package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.utils.Util;

class av implements OnClickListener {
    final /* synthetic */ CircleTopicBanner a;
    final /* synthetic */ CircleTopicRecommendCell b;

    av(CircleTopicRecommendCell circleTopicRecommendCell, CircleTopicBanner circleTopicBanner) {
        this.b = circleTopicRecommendCell;
        this.a = circleTopicBanner;
    }

    public void onClick(View view) {
        this.a.jumpTo(Util.getActivityOrContext(view));
    }
}
