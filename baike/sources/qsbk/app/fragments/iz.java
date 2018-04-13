package qsbk.app.fragments;

class iz implements Runnable {
    final /* synthetic */ QiushiTopicListFragment a;

    iz(QiushiTopicListFragment qiushiTopicListFragment) {
        this.a = qiushiTopicListFragment;
    }

    public void run() {
        this.a.f.autoRefresh();
    }
}
