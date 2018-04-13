package qsbk.app.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.ScrollView;
import qsbk.app.utils.DebugUtil;

public class MyScrollView extends ScrollView {
    private static final String a = MyScrollView.class.getName();
    private static final boolean b = DebugUtil.DEBUG;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private OnDirection h;
    private int i;
    private int j;

    public interface OnDirection {
        void toRight(View view);
    }

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.d = false;
        this.j = -1;
        a();
    }

    private void a() {
        this.i = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.g = (int) (getContext().getResources().getDisplayMetrics().density * 50.0f);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            super.onTouchEvent(motionEvent);
        } catch (Exception e) {
        }
        int findPointerIndex;
        switch (motionEvent.getAction() & 255) {
            case 0:
                Log.e(a, this.d + "action down...");
                this.e = (int) motionEvent.getX();
                this.f = (int) motionEvent.getY();
                this.j = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
            case 3:
                findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.j);
                if (findPointerIndex != -1) {
                    int x = (int) (MotionEventCompat.getX(motionEvent, findPointerIndex) - ((float) this.e));
                    findPointerIndex = (int) (MotionEventCompat.getY(motionEvent, findPointerIndex) - ((float) this.f));
                    if (b) {
                        Log.e(a, String.format("Down:(%s), Up:(%s), Distance(%s)", new Object[]{Integer.valueOf(this.e), Float.valueOf(r1), Integer.valueOf(x)}));
                    }
                    if (x < 0 && Math.abs(x) > this.g && Math.abs(x) > Math.abs(findPointerIndex)) {
                        this.c = true;
                        this.d = true;
                    }
                    if (this.c && this.h != null) {
                        this.c = false;
                        this.h.toRight(this);
                    }
                    this.j = -1;
                    break;
                }
                Log.e(a, "Invalid pointerId=" + this.j + " in onTouchEvent");
                break;
            case 2:
                findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.j);
                if (findPointerIndex != -1) {
                    int x2 = this.e - ((int) MotionEventCompat.getX(motionEvent, findPointerIndex));
                    findPointerIndex = this.f - ((int) MotionEventCompat.getY(motionEvent, findPointerIndex));
                    if (!this.d && Math.abs(x2) > this.i && Math.abs(x2) > Math.abs(findPointerIndex)) {
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        this.d = true;
                        break;
                    }
                }
                Log.e(a, "Invalid pointerId=" + this.j + " in onTouchEvent");
                break;
            case 5:
                this.j = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.e = (int) motionEvent.getX();
                this.f = (int) motionEvent.getY();
                this.j = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
            case 1:
            case 3:
                this.d = false;
                this.j = -1;
                break;
            case 2:
                i = this.j;
                if (i != -1) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
                    if (findPointerIndex != -1) {
                        i = (int) motionEvent.getX(findPointerIndex);
                        if (Math.abs(i - this.e) > this.i) {
                            this.d = true;
                            this.e = i;
                            this.f = (int) motionEvent.getY(findPointerIndex);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                                break;
                            }
                        }
                    }
                    Log.e(a, "Invalid pointerId=" + i + " in onInterceptTouchEvent");
                    break;
                }
                break;
            case 5:
                i = MotionEventCompat.getActionIndex(motionEvent);
                this.e = (int) motionEvent.getX(i);
                this.f = (int) motionEvent.getY(i);
                this.j = MotionEventCompat.getPointerId(motionEvent, i);
                break;
            case 6:
                i = MotionEventCompat.findPointerIndex(motionEvent, this.j);
                if (i != -1) {
                    this.e = (int) motionEvent.getX(i);
                    this.f = (int) motionEvent.getY(i);
                    break;
                }
                Log.e(a, "Invalid pointerId=" + this.j + " in onInterceptTouchEvent");
                break;
        }
        return this.d;
    }

    public void setOnDerection(OnDirection onDirection) {
        this.h = onDirection;
    }
}
