package cn.xiaochuankeji.tieba.ui.widget.pullzoom;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class PullZoomHeader extends RelativeLayout {
    private ArrayList<View> a = new ArrayList();

    public PullZoomHeader(Context context) {
        super(context);
    }

    public PullZoomHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullZoomHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (view != null && a(view).contains(rawX, rawY)) {
                    view.performClick();
                    return true;
                }
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public static RectF a(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF((float) iArr[0], (float) iArr[1], (float) (iArr[0] + view.getWidth()), (float) (iArr[1] + view.getHeight()));
    }
}
