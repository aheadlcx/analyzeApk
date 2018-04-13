package qsbk.app.utils;

import java.util.ArrayList;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager.CallBack;

class e extends AsyncTask<Void, Void, ArrayList<CircleTopic>> {
    final /* synthetic */ CallBack a;
    final /* synthetic */ CircleTopicManager b;

    e(CircleTopicManager circleTopicManager, CallBack callBack) {
        this.b = circleTopicManager;
        this.a = callBack;
    }

    protected ArrayList<CircleTopic> a(Void... voidArr) {
        return this.b.loadTopicsFromCache(2);
    }

    protected void a(ArrayList<CircleTopic> arrayList) {
        this.b.lruTopics.clear();
        this.b.lruTopics.addAll(arrayList);
        this.a.onSuccess(arrayList);
        if (arrayList != null && arrayList.size() > 0) {
            this.b.loadLRUTopicsWithUpdate(arrayList, this.a);
        }
    }
}
