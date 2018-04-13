package cn.v6.sixrooms.widgets.phone.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.OverScroller;
import android.widget.Scroller;

public abstract class ScrollerProxy {

    @TargetApi(9)
    private static class a extends ScrollerProxy {
        private OverScroller a;

        public a(Context context) {
            this.a = new OverScroller(context);
        }

        public final boolean computeScrollOffset() {
            return this.a.computeScrollOffset();
        }

        public final void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        }

        public final void forceFinished(boolean z) {
            this.a.forceFinished(z);
        }

        public final int getCurrX() {
            return this.a.getCurrX();
        }

        public final int getCurrY() {
            return this.a.getCurrY();
        }
    }

    private static class b extends ScrollerProxy {
        private Scroller a;

        public b(Context context) {
            this.a = new Scroller(context);
        }

        public final boolean computeScrollOffset() {
            return this.a.computeScrollOffset();
        }

        public final void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public final void forceFinished(boolean z) {
            this.a.forceFinished(z);
        }

        public final int getCurrX() {
            return this.a.getCurrX();
        }

        public final int getCurrY() {
            return this.a.getCurrY();
        }
    }

    public abstract boolean computeScrollOffset();

    public abstract void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void forceFinished(boolean z);

    public abstract int getCurrX();

    public abstract int getCurrY();

    public static ScrollerProxy getScroller(Context context) {
        if (VERSION.SDK_INT < 9) {
            return new b(context);
        }
        return new a(context);
    }
}
