package qsbk.app.fragments;

import java.util.List;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.QiushiTopic;
import qsbk.app.widget.qiushitopic.QiushiTopicRecommendController;

class ji extends AsyncTask<Object, List<QiushiTopic>, List<QiushiTopic>> {
    final /* synthetic */ int a;
    final /* synthetic */ QiushiTopicTabFragment b;

    ji(QiushiTopicTabFragment qiushiTopicTabFragment, int i) {
        this.b = qiushiTopicTabFragment;
        this.a = i;
    }

    protected /* synthetic */ Object a(Object[] objArr) {
        return c(objArr);
    }

    protected /* synthetic */ void b(Object[] objArr) {
        a((List[]) objArr);
    }

    protected List<QiushiTopic> c(Object... objArr) {
        if (this.a != 1) {
            List<QiushiTopic> loadFromCache = QiushiTopicRecommendController.loadFromCache();
            if (this.a == 3) {
                return loadFromCache;
            }
            d(new List[]{loadFromCache});
        }
        return QiushiTopicRecommendController.loadFromApi(Constants.QIUSHI_TOPIC_RECOMMEND);
    }

    protected void a(List<QiushiTopic>... listArr) {
        super.b(listArr);
        if (listArr != null && listArr.length > 0) {
            this.b.Q.clear();
            this.b.Q.addAll(listArr[0]);
            this.b.c();
        }
    }

    protected void a(List<QiushiTopic> list) {
        super.a(list);
        if (list != null && list.size() > 0) {
            this.b.Q.clear();
            this.b.Q.addAll(list);
            this.b.c();
        }
    }
}
