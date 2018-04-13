package qsbk.app.live.widget;

class fi implements Runnable {
    final /* synthetic */ HighLevelUserEnterView a;

    fi(HighLevelUserEnterView highLevelUserEnterView) {
        this.a = highLevelUserEnterView;
    }

    public void run() {
        this.a.isAvailable = true;
        this.a.b();
    }
}
