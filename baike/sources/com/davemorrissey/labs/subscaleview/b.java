package com.davemorrissey.labs.subscaleview;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.AnimationBuilder;

class b extends SimpleOnGestureListener {
    final /* synthetic */ Context a;
    final /* synthetic */ SubsamplingScaleImageView b;

    b(SubsamplingScaleImageView subsamplingScaleImageView, Context context) {
        this.b = subsamplingScaleImageView;
        this.a = context;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.b.x || !this.b.ak || this.b.F == null || motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || ((Math.abs(f) <= 500.0f && Math.abs(f2) <= 500.0f) || this.b.R))) {
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }
        PointF pointF = new PointF(this.b.F.x + (f * 0.25f), this.b.F.y + (0.25f * f2));
        new AnimationBuilder(new PointF((((float) (this.b.getWidth() / 2)) - pointF.x) / this.b.D, (((float) (this.b.getHeight() / 2)) - pointF.y) / this.b.D)).withEasing(1).a(false).a(3).start();
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.b.performClick();
        return true;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (!this.b.y || !this.b.ak || this.b.F == null) {
            return super.onDoubleTapEvent(motionEvent);
        }
        this.b.setGestureDetector(this.a);
        if (this.b.z) {
            this.b.ac = new PointF(motionEvent.getX(), motionEvent.getY());
            this.b.G = new PointF(this.b.F.x, this.b.F.y);
            this.b.E = this.b.D;
            this.b.T = true;
            this.b.R = true;
            this.b.ae = -1.0f;
            this.b.ah = this.b.viewToSourceCoord(this.b.ac);
            this.b.ai = new PointF(motionEvent.getX(), motionEvent.getY());
            this.b.ag = new PointF(this.b.ah.x, this.b.ah.y);
            this.b.af = false;
            return false;
        }
        this.b.a(this.b.viewToSourceCoord(new PointF(motionEvent.getX(), motionEvent.getY())), new PointF(motionEvent.getX(), motionEvent.getY()));
        return true;
    }
}
