package android.support.v4.app;

class aj implements Runnable {
    final /* synthetic */ ListFragment a;

    aj(ListFragment listFragment) {
        this.a = listFragment;
    }

    public void run() {
        this.a.b.focusableViewAvailable(this.a.b);
    }
}
