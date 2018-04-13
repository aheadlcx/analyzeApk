package com.androidex.util;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;

public class ClickControlledSpinner extends Spinner implements OnDismissListener {
    private boolean isMoved = false;
    private boolean isOnClicked;
    private ClickControlledSpinner mClickControlledSpinner;
    private OnClickMyListener onClickMyListener;
    private Point touchedPoint = new Point();

    public interface OnClickMyListener {
        void onSpinnerClick(ClickControlledSpinner clickControlledSpinner);
    }

    public ClickControlledSpinner(Context context) {
        super(context);
    }

    public ClickControlledSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ClickControlledSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 0:
                this.touchedPoint.x = rawX;
                this.touchedPoint.y = rawY;
                break;
            case 1:
                if (!this.isMoved) {
                    onClick();
                    break;
                }
                if (rawY - this.touchedPoint.y <= 20 && this.touchedPoint.y - rawY <= 20) {
                    onClick();
                }
                this.isMoved = false;
                break;
            case 2:
                this.isMoved = true;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void onClick() {
        if (this.onClickMyListener != null && isEnabled()) {
            new ClickControlledSpinner$1(this).start();
        }
    }

    public void setOnClickMyListener(OnClickMyListener onClickMyListener, ClickControlledSpinner clickControlledSpinner) {
        this.onClickMyListener = onClickMyListener;
        this.mClickControlledSpinner = clickControlledSpinner;
    }

    public boolean isOnClicked() {
        return this.isOnClicked;
    }

    public void setOnClicked(boolean z) {
        this.isOnClicked = z;
    }

    public void onDismiss() {
    }
}
