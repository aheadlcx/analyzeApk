package com.tencent.weibo.sdk.android.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.List;

public class LetterListView extends View {
    List<String> b;
    int choose = -1;
    OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    Paint paint = new Paint();
    boolean showBkg = false;

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(int i);
    }

    public LetterListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public LetterListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LetterListView(Context context, List<String> list) {
        super(context);
        this.b = list;
    }

    public void setB(List<String> list) {
        this.b = list;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.showBkg) {
            canvas.drawColor(Color.parseColor("#00000000"));
        }
        int height = getHeight();
        int width = getWidth() - 30;
        if (this.b.size() > 0) {
            int size = height / this.b.size();
            for (int i = 0; i < this.b.size(); i++) {
                this.paint.setColor(Color.parseColor("#2796c4"));
                this.paint.setTextSize(17.0f);
                this.paint.setTypeface(Typeface.DEFAULT_BOLD);
                this.paint.setAntiAlias(true);
                if (i == this.choose) {
                    this.paint.setColor(-7829368);
                    this.paint.setFakeBoldText(true);
                }
                canvas.drawText(((String) this.b.get(i)).toUpperCase(), ((float) (width / 2)) - (this.paint.measureText((String) this.b.get(i)) / 2.0f), (float) ((size * i) + size), this.paint);
                this.paint.reset();
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.choose;
        OnTouchingLetterChangedListener onTouchingLetterChangedListener = this.onTouchingLetterChangedListener;
        int height = (int) ((y / ((float) getHeight())) * ((float) this.b.size()));
        switch (action) {
            case 0:
                this.showBkg = true;
                if (i != height && onTouchingLetterChangedListener != null && height >= 0 && height < this.b.size()) {
                    onTouchingLetterChangedListener.onTouchingLetterChanged(height);
                    this.choose = height;
                    invalidate();
                    break;
                }
            case 1:
                this.showBkg = false;
                this.choose = -1;
                invalidate();
                break;
            case 2:
                if (i != height && onTouchingLetterChangedListener != null && height >= 0 && height < this.b.size()) {
                    onTouchingLetterChangedListener.onTouchingLetterChanged(height);
                    this.choose = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
}
