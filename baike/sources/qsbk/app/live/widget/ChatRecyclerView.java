package qsbk.app.live.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ChatRecyclerView extends RecyclerView {
    int I;
    int J;
    boolean K = false;

    public ChatRecyclerView(Context context) {
        super(context);
    }

    public ChatRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChatRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        int rawX = (int) motionEvent.getRawX();
        switch (motionEvent.getAction()) {
            case 0:
                this.K = false;
                this.I = rawY;
                this.J = rawX;
                break;
            case 2:
                int i = rawX - this.J;
                int i2 = rawY - this.I;
                this.I = rawY;
                this.J = rawX;
                if (Math.abs(i) > 5) {
                    if (Math.abs(i2) * 10 >= Math.abs(i)) {
                        this.K = false;
                        break;
                    }
                    this.K = true;
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.K || super.onTouchEvent(motionEvent);
    }

    protected float getBottomFadingEdgeStrength() {
        return 0.0f;
    }
}
