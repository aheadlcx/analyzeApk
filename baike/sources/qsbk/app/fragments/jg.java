package qsbk.app.fragments;

import java.util.List;
import qsbk.app.model.QiushiTopic;
import qsbk.app.widget.qiushitopic.QiushiTopicRecommendController.OnTopicRecommendLoadListener;

class jg implements OnTopicRecommendLoadListener {
    final /* synthetic */ QiushiTopicTabFragment a;

    jg(QiushiTopicTabFragment qiushiTopicTabFragment) {
        this.a = qiushiTopicTabFragment;
    }

    public void onTopicsLoad(List<QiushiTopic> list) {
        this.a.Q = list;
        this.a.c();
    }
}
