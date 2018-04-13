package android.support.v4.view;

class m implements Runnable {
    final /* synthetic */ ViewPager a;

    m(ViewPager viewPager) {
        this.a = viewPager;
    }

    public void run() {
        this.a.setScrollState(0);
        this.a.populate();
    }
}
