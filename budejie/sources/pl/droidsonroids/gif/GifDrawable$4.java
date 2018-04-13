package pl.droidsonroids.gif;

class GifDrawable$4 implements Runnable {
    final /* synthetic */ GifDrawable a;

    GifDrawable$4(GifDrawable gifDrawable) {
        this.a = gifDrawable;
    }

    public void run() {
        this.a.invalidateSelf();
    }
}
