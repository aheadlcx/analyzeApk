package pl.droidsonroids.gif;

class GifDrawable$5 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ GifDrawable b;

    GifDrawable$5(GifDrawable gifDrawable, int i) {
        this.b = gifDrawable;
        this.a = i;
    }

    public void run() {
        GifDrawable.a(GifDrawable.a(this.b), this.a, GifDrawable.b(this.b));
        this.b.invalidateSelf();
    }
}
