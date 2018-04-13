package qsbk.app.nearby.api;

class d implements Runnable {
    final /* synthetic */ LocationHelper a;

    d(LocationHelper locationHelper) {
        this.a = locationHelper;
    }

    public void run() {
        if (this.a.g != null) {
            this.a.g.getLocation(this.a);
        }
    }
}
