package android.support.v4.view;

class e implements Runnable {
    final /* synthetic */ LiveBannerViewPager a;

    e(LiveBannerViewPager liveBannerViewPager) {
        this.a = liveBannerViewPager;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (!this.a.b) {
            if (this.a.isShown() && this.a.h) {
                this.a.setCurrentItem(this.a.getCurrentItem() + 1, true);
            }
            this.a.postDelayed(this, (long) this.a.a);
        }
    }
}
