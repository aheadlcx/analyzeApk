package android.support.v7.widget;

class f implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout a;

    f(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.a = actionBarOverlayLayout;
    }

    public void run() {
        this.a.c();
        this.a.c = this.a.a.animate().translationY(0.0f).setListener(this.a.d);
    }
}
