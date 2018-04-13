package qsbk.app.widget;

import java.util.List;
import qsbk.app.model.QiushiTopic;
import qsbk.app.widget.qiushitopic.QiushiTopicRecommendController.OnTopicRecommendLoadListener;

class dz implements OnTopicRecommendLoadListener {
    final /* synthetic */ QiushiTopicRecommendCell a;

    dz(QiushiTopicRecommendCell qiushiTopicRecommendCell) {
        this.a = qiushiTopicRecommendCell;
    }

    public void onTopicsLoad(List<QiushiTopic> list) {
        if (list != null && list.size() > 0) {
            this.a.performUpdate(this.a.getPosition(), null, list);
        }
    }
}
