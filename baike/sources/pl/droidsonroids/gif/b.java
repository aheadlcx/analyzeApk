package pl.droidsonroids.gif;

class b implements Runnable {
    final /* synthetic */ GifDrawable a;

    b(GifDrawable gifDrawable) {
        this.a = gifDrawable;
    }

    public void run() {
        this.a.invalidateSelf();
    }
}
