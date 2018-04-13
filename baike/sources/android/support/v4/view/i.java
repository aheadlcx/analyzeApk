package android.support.v4.view;

class i implements Runnable {
    final /* synthetic */ TopicViewPager a;

    i(TopicViewPager topicViewPager) {
        this.a = topicViewPager;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (!this.a.b) {
            if (this.a.isShown()) {
                this.a.setCurrentItem(this.a.getCurrentItem() + 1, true);
            }
            this.a.postDelayed(this, (long) this.a.a);
        }
    }
}
