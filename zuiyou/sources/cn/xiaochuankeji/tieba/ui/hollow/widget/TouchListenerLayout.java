package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchListenerLayout extends LinearLayout {
    private float a;
    private a b;

    public interface a {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    public TouchListenerLayout(Context context) {
        super(context);
    }

    public TouchListenerLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchListenerLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.b.a();
                this.a = motionEvent.getRawX();
                return true;
            case 1:
                this.b.e();
                this.a = motionEvent.getRawX() - this.a;
                if (this.a > 140.0f) {
                    this.b.c();
                }
                if (this.a >= -140.0f) {
                    return dispatchTouchEvent;
                }
                this.b.b();
                return dispatchTouchEvent;
            case 2:
                this.b.d();
                return dispatchTouchEvent;
            default:
                return dispatchTouchEvent;
        }
    }

    public void setOnPressListener(a aVar) {
        this.b = aVar;
    }
}
