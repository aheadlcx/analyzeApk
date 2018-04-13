package qsbk.app.fragments;

class aq implements Runnable {
    final /* synthetic */ CircleTopicRecommendFragment a;

    aq(CircleTopicRecommendFragment circleTopicRecommendFragment) {
        this.a = circleTopicRecommendFragment;
    }

    public void run() {
        this.a.d.autoRefresh();
    }
}
