package bdj.uk.co.senab.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

@TargetApi(8)
public class c extends b {
    protected final ScaleGestureDetector f;

    public c(Context context) {
        super(context);
        this.f = new ScaleGestureDetector(context, new OnScaleGestureListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                this.a.a.a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        });
    }

    public boolean a() {
        return this.f.isInProgress();
    }

    public boolean c(MotionEvent motionEvent) {
        Log.d("FroyoGestureDetector", "FroyoGestureDetector onTouchEvent");
        this.f.onTouchEvent(motionEvent);
        return super.c(motionEvent);
    }
}
