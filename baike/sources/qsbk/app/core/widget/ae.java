package qsbk.app.core.widget;

class ae implements Runnable {
    final /* synthetic */ VolumeControllerView a;

    ae(VolumeControllerView volumeControllerView) {
        this.a = volumeControllerView;
    }

    public void run() {
        this.a.a();
        this.a.postInvalidate();
        this.a.postDelayed(new af(this), 300);
    }
}
