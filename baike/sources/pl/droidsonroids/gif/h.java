package pl.droidsonroids.gif;

class h extends a {
    final /* synthetic */ int a;
    final /* synthetic */ GifDrawable b;

    h(GifDrawable gifDrawable, int i) {
        this.b = gifDrawable;
        this.a = i;
        super();
    }

    public void doWork() {
        this.b.h.a(this.a, this.b.g);
        this.b.scheduleSelf(this.b.m, 0);
    }
}
