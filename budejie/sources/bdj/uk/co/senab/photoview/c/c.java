package bdj.uk.co.senab.photoview.c;

import android.content.Context;
import android.widget.Scroller;

public class c extends d {
    private final Scroller a;

    public c(Context context) {
        this.a = new Scroller(context);
    }

    public boolean a() {
        return this.a.computeScrollOffset();
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void a(boolean z) {
        this.a.forceFinished(z);
    }

    public boolean b() {
        return this.a.isFinished();
    }

    public int c() {
        return this.a.getCurrX();
    }

    public int d() {
        return this.a.getCurrY();
    }
}
