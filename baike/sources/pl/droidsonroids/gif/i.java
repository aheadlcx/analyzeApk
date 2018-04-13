package pl.droidsonroids.gif;

class i extends a {
    final /* synthetic */ int a;
    final /* synthetic */ GifDrawable b;

    i(GifDrawable gifDrawable, int i) {
        this.b = gifDrawable;
        this.a = i;
        super();
    }

    public void doWork() {
        this.b.h.b(this.a, this.b.g);
        this.b.scheduleSelf(this.b.m, 0);
    }
}
