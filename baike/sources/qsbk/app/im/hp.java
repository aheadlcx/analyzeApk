package qsbk.app.im;

class hp implements Runnable {
    final /* synthetic */ IMMessageListFragment a;

    hp(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void run() {
        this.a.I.show();
    }
}
