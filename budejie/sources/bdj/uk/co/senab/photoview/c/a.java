package bdj.uk.co.senab.photoview.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

@TargetApi(9)
public class a extends d {
    protected final OverScroller a;
    private boolean b = false;

    public a(Context context) {
        this.a = new OverScroller(context);
    }

    public boolean a() {
        if (this.b) {
            this.a.computeScrollOffset();
            this.b = false;
        }
        return this.a.computeScrollOffset();
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
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
