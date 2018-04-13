package qsbk.app.slide;

class bl implements Runnable {
    final /* synthetic */ SlideActivity a;

    bl(SlideActivity slideActivity) {
        this.a = slideActivity;
    }

    public void run() {
        this.a.a(false);
    }
}
