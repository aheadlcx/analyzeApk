package com.spriteapp.booklibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.spriteapp.booklibrary.a$h;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.b.b;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;

public class TextSizeView extends View {
    private static final int DEFAULT_BALL_RADIUS = 8;
    private static final float DEFAULT_HEIGHT = 47.0f;
    public static final int DEFAULT_TEXT_SIZE = 17;
    private static final float DEFAULT_WIDTH = 260.0f;
    private static final int MAX_COUNT = 7;
    private static final int START_VALUE = 0;
    private static final int SUB_VALUE = 2;
    private static final String TAG = "TextSizeView";
    private float downX;
    private int endValue;
    private int mBallColor;
    private Paint mBallLinePaint;
    private Paint mBallPaint;
    private float mBallRadius;
    private b mCallBack;
    private int mDivideWidth;
    private int mLeftColor;
    private Paint mLeftPaint;
    private RectF mRectF;
    private int mRightColor;
    private Paint mRightPaint;
    private int mStartValue;
    private final int mSubValue;
    private int mTextSize;
    private int mWidth;
    private int position;
    private TouchEnum state;

    public enum TouchEnum {
        TOUCH_BALL,
        TOUCH_LEFT,
        TOUCH_RIGHT
    }

    public TextSizeView(Context context) {
        this(context, null);
    }

    public TextSizeView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextSizeView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextSize = 17;
        this.mSubValue = ScreenUtil.dpToPxInt(2.0f);
        initAttrs(context, attributeSet, i);
    }

    private void initAttrs(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a$h.TextSizeView, i, 0);
        this.mBallColor = obtainStyledAttributes.getColor(a$h.TextSizeView_ball_color, -16711936);
        this.mLeftColor = obtainStyledAttributes.getColor(a$h.TextSizeView_left_color, -16776961);
        this.mRightColor = obtainStyledAttributes.getColor(a$h.TextSizeView_right_color, -3355444);
        this.mBallRadius = obtainStyledAttributes.getDimension(a$h.TextSizeView_ball_radius, (float) dp2px(8));
        this.mStartValue = (int) obtainStyledAttributes.getDimension(a$h.TextSizeView_start_value, (float) dp2px(0));
        obtainStyledAttributes.recycle();
        initPaint();
    }

    private void initPaint() {
        this.mBallPaint = new Paint();
        this.mLeftPaint = new Paint();
        this.mRightPaint = new Paint();
        initPaint(this.mBallPaint, this.mBallColor);
        initBallLinePaint();
        initPaint(this.mLeftPaint, this.mLeftColor);
        initPaint(this.mRightPaint, this.mRightColor);
        this.mLeftPaint.setStrokeWidth((float) dp2px(1));
        this.mRightPaint.setStrokeWidth((float) dp2px(1));
    }

    private void initBallLinePaint() {
        this.mBallLinePaint = new Paint();
        this.mBallLinePaint.setAntiAlias(true);
        this.mBallLinePaint.setStyle(Style.FILL_AND_STROKE);
        this.mBallLinePaint.setStrokeCap(Cap.BUTT);
        this.mBallLinePaint.setColor(this.mLeftColor);
    }

    private void initPaint(Paint paint, int i) {
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStyle(Style.FILL);
        paint.setStrokeCap(Cap.BUTT);
    }

    public void changeMode(boolean z) {
        this.mBallLinePaint.setColor(getResources().getColor(z ? a.book_reader_main_night_color : a.book_reader_main_color));
        this.mRightPaint.setColor(getResources().getColor(z ? a.book_reader_common_text_color : a.book_reader_divide_line_color));
        this.mBallPaint.setColor(getResources().getColor(z ? a.book_reader_read_bottom_night_background : a.book_reader_white));
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.position = 0;
                this.downX = motionEvent.getX();
                if (this.downX > ((float) this.mStartValue) && this.downX < ((float) this.mStartValue) + (this.mBallRadius * 2.0f)) {
                    this.state = TouchEnum.TOUCH_BALL;
                    break;
                }
                if (this.downX < this.mBallRadius * 2.0f) {
                    this.downX = 0.0f;
                } else if (this.downX > ((float) this.endValue)) {
                    this.downX = (float) this.endValue;
                }
                this.mStartValue = (int) this.downX;
                this.state = TouchEnum.TOUCH_BALL;
                break;
                break;
            case 1:
                getUpPosition((int) motionEvent.getX());
                break;
            case 2:
                if (this.state == TouchEnum.TOUCH_BALL) {
                    float x = motionEvent.getX();
                    this.mStartValue = (int) ((x - this.downX) + ((float) this.mStartValue));
                    if (this.mStartValue < 0) {
                        this.mStartValue = 0;
                    } else if (this.mStartValue > this.endValue) {
                        this.mStartValue = this.endValue;
                    }
                    invalidate();
                    this.downX = x;
                    break;
                }
                break;
        }
        return true;
    }

    public void setCallBack(b bVar) {
        this.mCallBack = bVar;
    }

    private void getUpPosition(int i) {
        int i2;
        this.position = i / this.mDivideWidth;
        if (Math.abs(i - (this.mDivideWidth * this.position)) > this.mDivideWidth / 2) {
            i2 = this.position + 1;
            this.position = i2;
        } else {
            i2 = this.position;
        }
        this.position = i2;
        if (this.position == 7) {
            this.position--;
        }
        this.mStartValue = 0;
        invalidate();
        setTextSize();
        if (this.mCallBack != null) {
            this.mCallBack.sendTextSize(this.mTextSize);
        }
    }

    public int plusTextSize() {
        if (this.position == 6) {
            return this.mTextSize;
        }
        this.position++;
        setTextSize();
        invalidate();
        return this.mTextSize;
    }

    public int subTextSize() {
        if (this.position == 0) {
            return this.mTextSize;
        }
        this.position--;
        setTextSize();
        invalidate();
        return this.mTextSize;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getWidth();
        this.endValue = (int) (((float) this.mWidth) - (this.mBallRadius * 2.0f));
        this.mRectF = new RectF(0.0f, 0.0f, (float) this.mWidth, (float) i2);
        this.mDivideWidth = (int) (((((float) this.mWidth) - (this.mBallRadius * 2.0f)) - ((float) (this.mSubValue * 2))) / 6.0f);
    }

    public void setPosition(int i) {
        this.position = i;
        this.mTextSize = 17;
        this.mTextSize = (int) (((float) this.mTextSize) + (1.5f * ((float) i)));
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int height = getHeight() / 2;
        Canvas canvas2 = canvas;
        canvas2.drawLine(((float) this.mSubValue) + this.mBallRadius, (float) height, (((float) this.mWidth) - this.mBallRadius) - ((float) this.mSubValue), (float) height, this.mRightPaint);
        for (int i = 0; i < 7; i++) {
            canvas2 = canvas;
            canvas2.drawLine(((float) this.mSubValue) + (((float) (this.mDivideWidth * i)) + this.mBallRadius), (float) (height - ScreenUtil.dpToPxInt(5.0f)), ((float) this.mSubValue) + (((float) (this.mDivideWidth * i)) + this.mBallRadius), (float) (ScreenUtil.dpToPxInt(5.0f) + height), this.mRightPaint);
        }
        float f = ((float) ((this.position * this.mDivideWidth) + this.mStartValue)) + this.mBallRadius;
        canvas.drawCircle(((float) this.mSubValue) + f, (float) height, this.mBallRadius + ((float) dp2px(1)), this.mBallLinePaint);
        canvas.drawCircle(f + ((float) this.mSubValue), (float) height, this.mBallRadius, this.mBallPaint);
    }

    public void setTextSize() {
        this.mTextSize = 17;
        this.mTextSize = (int) (((float) this.mTextSize) + (1.5f * ((float) this.position)));
        SharedPreferencesUtil.getInstance().putInt("hua_xi_read_text_size_position", this.position);
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = (int) TypedValue.applyDimension(1, DEFAULT_WIDTH, getResources().getDisplayMetrics());
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = (int) TypedValue.applyDimension(1, DEFAULT_HEIGHT, getResources().getDisplayMetrics());
        }
        setMeasuredDimension(size, size2);
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }
}
