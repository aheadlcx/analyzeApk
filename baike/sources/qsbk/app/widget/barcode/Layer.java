package qsbk.app.widget.barcode;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class Layer extends FrameLayout {
    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                View a = a((int) motionEvent.getX(), (int) motionEvent.getY());
                if (a != getChildAt(getChildCount() - 1) && (a instanceof BarcodeView)) {
                    removeView(a);
                    addView(a);
                    break;
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    protected View a(int i, int i2) {
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            view = getChildAt(childCount);
            if (view instanceof BarcodeView) {
                Rect innerRect = ((BarcodeView) view).getInnerRect();
                if (i >= innerRect.left && i <= innerRect.right && i2 >= innerRect.top && i2 <= innerRect.bottom) {
                    break;
                }
            }
        }
        return view;
    }
}
