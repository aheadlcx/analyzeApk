package qsbk.app.im;

class hj implements Runnable {
    final /* synthetic */ IMMessageListFragment a;

    hj(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void run() {
        this.a.s.destroy(false);
    }
}
