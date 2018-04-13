package android.support.v7.app;

class s implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV9 a;

    s(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.a = appCompatDelegateImplV9;
    }

    public void run() {
        if ((this.a.s & 1) != 0) {
            this.a.c(0);
        }
        if ((this.a.s & 4096) != 0) {
            this.a.c(108);
        }
        this.a.r = false;
        this.a.s = 0;
    }
}
