package qsbk.app.widget.qiushitopic;

import java.util.List;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.widget.qiushitopic.QiushiTopicBannerController.OnTopicBannerLoadListener;

final class a extends AsyncTask<Void, List<QiushiTopicBanner>, List<QiushiTopicBanner>> {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ OnTopicBannerLoadListener c;

    a(int i, String str, OnTopicBannerLoadListener onTopicBannerLoadListener) {
        this.a = i;
        this.b = str;
        this.c = onTopicBannerLoadListener;
    }

    protected /* synthetic */ void b(Object[] objArr) {
        a((List[]) objArr);
    }

    protected List<QiushiTopicBanner> a(Void... voidArr) {
        List<QiushiTopicBanner> list = null;
        if (this.a != 1) {
            list = QiushiTopicBannerController.loadFromCache();
        }
        if (this.a == 2) {
            return list;
        }
        d(new List[]{list});
        return QiushiTopicBannerController.loadFromApi(this.b);
    }

    protected void a(List<QiushiTopicBanner>... listArr) {
        super.b(listArr);
        if (listArr != null && listArr.length > 0) {
            List list = listArr[0];
            if (list != null && list.size() > 0 && this.c != null) {
                this.c.onTopicBannerLoad(list);
            }
        }
    }

    protected void a(List<QiushiTopicBanner> list) {
        super.a(list);
        if (list != null && list.size() > 0 && this.c != null) {
            this.c.onTopicBannerLoad(list);
        }
    }
}
