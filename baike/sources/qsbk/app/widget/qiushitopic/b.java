package qsbk.app.widget.qiushitopic;

import java.util.List;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.QiushiTopic;
import qsbk.app.widget.qiushitopic.QiushiTopicRecommendController.OnTopicRecommendLoadListener;

final class b extends AsyncTask<Void, List<QiushiTopic>, List<QiushiTopic>> {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ OnTopicRecommendLoadListener c;

    b(int i, String str, OnTopicRecommendLoadListener onTopicRecommendLoadListener) {
        this.a = i;
        this.b = str;
        this.c = onTopicRecommendLoadListener;
    }

    protected /* synthetic */ void b(Object[] objArr) {
        a((List[]) objArr);
    }

    protected List<QiushiTopic> a(Void... voidArr) {
        List<QiushiTopic> list = null;
        if (this.a != 1) {
            list = QiushiTopicRecommendController.loadFromCache();
        }
        if (this.a == 3) {
            return list;
        }
        d(new List[]{list});
        return QiushiTopicRecommendController.loadFromApi(this.b);
    }

    protected void a(List<QiushiTopic>... listArr) {
        super.b(listArr);
        if (listArr != null && listArr.length > 0) {
            List list = listArr[0];
            if (list != null && list.size() > 0 && this.c != null) {
                this.c.onTopicsLoad(list);
            }
        }
    }

    protected void a(List<QiushiTopic> list) {
        super.a(list);
        if (list != null && list.size() > 0 && this.c != null) {
            this.c.onTopicsLoad(list);
        }
    }
}
