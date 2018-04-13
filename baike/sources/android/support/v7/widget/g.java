package android.support.v7.widget;

class g implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout a;

    g(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.a = actionBarOverlayLayout;
    }

    public void run() {
        this.a.c();
        this.a.c = this.a.a.animate().translationY((float) (-this.a.a.getHeight())).setListener(this.a.d);
    }
}
