package android.support.v7.widget;

class a implements Runnable {
    final /* synthetic */ AbsActionBarView a;

    a(AbsActionBarView absActionBarView) {
        this.a = absActionBarView;
    }

    public void run() {
        this.a.showOverflowMenu();
    }
}
