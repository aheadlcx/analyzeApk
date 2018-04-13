package qsbk.app.live.widget;

class fm implements Runnable {
    final /* synthetic */ HighLevelUserEnterView a;

    fm(HighLevelUserEnterView highLevelUserEnterView) {
        this.a = highLevelUserEnterView;
    }

    public void run() {
        this.a.u.stopFlipping();
    }
}
