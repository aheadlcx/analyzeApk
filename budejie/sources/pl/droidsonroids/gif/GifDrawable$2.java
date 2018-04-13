package pl.droidsonroids.gif;

class GifDrawable$2 implements Runnable {
    final /* synthetic */ GifDrawable a;

    GifDrawable$2(GifDrawable gifDrawable) {
        this.a = gifDrawable;
    }

    public void run() {
        GifDrawable.b(GifDrawable.a(this.a));
        this.a.invalidateSelf();
    }
}
