package qsbk.app.im;

class hq implements Runnable {
    final /* synthetic */ IMMessageListFragment a;

    hq(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void run() {
        this.a.I.dismiss();
    }
}
