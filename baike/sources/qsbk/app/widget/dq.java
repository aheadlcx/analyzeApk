package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.utils.Util;

class dq implements OnClickListener {
    final /* synthetic */ QiushiTopicBanner a;
    final /* synthetic */ QiushiTopicBannerCell b;

    dq(QiushiTopicBannerCell qiushiTopicBannerCell, QiushiTopicBanner qiushiTopicBanner) {
        this.b = qiushiTopicBannerCell;
        this.a = qiushiTopicBanner;
    }

    public void onClick(View view) {
        this.a.jumpTo(Util.getActivityOrContext(view));
    }
}
