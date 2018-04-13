package cn.v6.sixrooms.widgets.phone.photoview;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

final class c implements OnScaleGestureListener {
    final /* synthetic */ c a;

    c(c cVar) {
        this.a = cVar;
    }

    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        this.a.a.onScale(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        return true;
    }

    public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public final void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }
}
