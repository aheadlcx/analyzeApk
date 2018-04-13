package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ProgressBar;

public class TouchResponseProgressBar extends ProgressBar {
    OnProgressListener a;
    Runnable b = new ex(this);
    Runnable c = new ey(this);
    private boolean d;
    private MotionEvent e;

    public interface OnProgressListener {
        boolean onProgress(int i);
    }

    public TouchResponseProgressBar(Context context) {
        super(context);
    }

    public TouchResponseProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchResponseProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        this.e = motionEvent;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.d = true;
                post(this.b);
                removeCallbacks(this.c);
                break;
            case 1:
            case 3:
                removeCallbacks(this.b);
                post(this.c);
                this.d = false;
                break;
        }
        return true;
    }

    protected void onDetachedFromWindow() {
        reset();
        super.onDetachedFromWindow();
    }

    public void reset() {
        setProgress(0);
        removeCallbacks(this.c);
        removeCallbacks(this.b);
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i);
        if (!(!this.d || this.a == null || this.a.onProgress(getProgress()))) {
            this.d = false;
            reset();
            this.e.setAction(3);
            dispatchTouchEvent(this.e);
        }
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.a = onProgressListener;
    }
}
