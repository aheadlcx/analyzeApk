package qsbk.app.activity.base;

import java.util.Collection;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleTopicManager.CallBack;

class m implements CallBack {
    final /* synthetic */ BaseArticleListViewFragment a;

    m(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void onSuccess(Collection<CircleTopic> collection) {
        CircleTopicManager.sortRecommendToics(collection);
        this.a.insertRecommendTopics();
    }

    public void onFailure(int i, String str) {
    }
}
