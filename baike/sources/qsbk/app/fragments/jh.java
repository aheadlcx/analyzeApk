package qsbk.app.fragments;

import java.util.List;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.widget.qiushitopic.QiushiTopicBannerController.OnTopicBannerLoadListener;

class jh implements OnTopicBannerLoadListener {
    final /* synthetic */ QiushiTopicTabFragment a;

    jh(QiushiTopicTabFragment qiushiTopicTabFragment) {
        this.a = qiushiTopicTabFragment;
    }

    public void onTopicBannerLoad(List<QiushiTopicBanner> list) {
        this.a.S.clear();
        this.a.S.addAll(list);
        this.a.c();
    }
}
