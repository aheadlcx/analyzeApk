package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.utils.Util;

class as implements OnClickListener {
    final /* synthetic */ CircleTopicBanner a;
    final /* synthetic */ CircleTopicBannerCell b;

    as(CircleTopicBannerCell circleTopicBannerCell, CircleTopicBanner circleTopicBanner) {
        this.b = circleTopicBannerCell;
        this.a = circleTopicBanner;
    }

    public void onClick(View view) {
        this.a.jumpTo(Util.getActivityOrContext(view));
    }
}
