package qsbk.app.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;

class xw implements OnPreDrawListener {
    final /* synthetic */ int[] a;
    final /* synthetic */ NewImageViewer b;

    xw(NewImageViewer newImageViewer, int[] iArr) {
        this.b = newImageViewer;
        this.a = iArr;
    }

    public boolean onPreDraw() {
        this.b.k.getViewTreeObserver().removeOnPreDrawListener(this);
        this.b.o = new Rect(this.a[0], this.a[1], this.a[0] + this.b.k.getWidth(), this.a[1] + this.b.k.getHeight());
        if (this.b.m != null && this.b.m.length > this.b.c) {
            this.b.k.setPreBounds(this.b.m[this.b.c]);
        }
        this.b.k.setAfterBounds(this.b.o);
        this.b.k.setPreScaleType(ScaleType.CENTER_CROP);
        this.b.k.setOnEnterAnimListener(new xx(this));
        this.b.k.startEnterAnim();
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.b.k, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(0), Integer.valueOf(-16777216)});
        ofObject.setDuration((long) this.b.k.getAnimDuration());
        ofObject.start();
        this.b.d.animate().alpha(1.0f).setDuration((long) this.b.k.getAnimDuration()).start();
        return true;
    }
}
