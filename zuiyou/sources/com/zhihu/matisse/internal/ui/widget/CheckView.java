package com.zhihu.matisse.internal.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.zhihu.matisse.R;

public class CheckView extends View {
    private static final float BG_RADIUS = 9.5f;
    private static final int CONTENT_SIZE = 14;
    private static final float SHADOW_WIDTH = 4.0f;
    private static final int SIZE = 42;
    private static final float STROKE_RADIUS = 10.0f;
    private static final float STROKE_WIDTH = 1.5f;
    public static final int UNCHECKED = Integer.MIN_VALUE;
    private Paint mBackgroundPaint;
    private Drawable mCheckDrawable;
    private Rect mCheckRect;
    private boolean mChecked;
    private int mCheckedNum;
    private boolean mCountable;
    private float mDensity;
    private boolean mEnabled = true;
    private Paint mShadowPaint;
    private Paint mStrokePaint;
    private TextPaint mTextPaint;

    public CheckView(Context context) {
        super(context);
        init(context);
    }

    public CheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CheckView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    protected void onMeasure(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec((int) (42.0f * this.mDensity), 1073741824);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    private void init(Context context) {
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mStrokePaint = new Paint();
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setStyle(Style.STROKE);
        this.mStrokePaint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
        this.mStrokePaint.setStrokeWidth(STROKE_WIDTH * this.mDensity);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_borderColor});
        int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), R.color.zhihu_item_checkCircle_borderColor, getContext().getTheme()));
        obtainStyledAttributes.recycle();
        this.mStrokePaint.setColor(color);
        this.mCheckDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_check_white_18dp, context.getTheme());
    }

    public void setChecked(boolean z) {
        if (this.mCountable) {
            throw new IllegalStateException("CheckView is countable, call setCheckedNum() instead.");
        }
        this.mChecked = z;
        invalidate();
    }

    public void setCountable(boolean z) {
        this.mCountable = z;
    }

    public void setCheckedNum(int i) {
        if (!this.mCountable) {
            throw new IllegalStateException("CheckView is not countable, call setChecked() instead.");
        } else if (i == Integer.MIN_VALUE || i > 0) {
            this.mCheckedNum = i;
            invalidate();
        } else {
            throw new IllegalArgumentException("checked num can't be negative.");
        }
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initShadowPaint();
        canvas.drawCircle((this.mDensity * 42.0f) / 2.0f, (this.mDensity * 42.0f) / 2.0f, 14.75f * this.mDensity, this.mShadowPaint);
        canvas.drawCircle((this.mDensity * 42.0f) / 2.0f, (this.mDensity * 42.0f) / 2.0f, 10.0f * this.mDensity, this.mStrokePaint);
        if (this.mChecked || this.mCheckedNum != Integer.MIN_VALUE) {
            initBackgroundPaint();
            canvas.drawCircle((this.mDensity * 42.0f) / 2.0f, (this.mDensity * 42.0f) / 2.0f, BG_RADIUS * this.mDensity, this.mBackgroundPaint);
        }
        if (!this.mCountable || this.mCheckedNum == Integer.MIN_VALUE) {
            this.mCheckDrawable.setBounds(getCheckRect());
            this.mCheckDrawable.draw(canvas);
        } else {
            initTextPaint();
            String valueOf = String.valueOf(this.mCheckedNum);
            canvas.drawText(valueOf, (float) (((int) (((float) canvas.getWidth()) - this.mTextPaint.measureText(valueOf))) / 2), (float) (((int) ((((float) canvas.getHeight()) - this.mTextPaint.descent()) - this.mTextPaint.ascent())) / 2), this.mTextPaint);
        }
        setAlpha(this.mEnabled ? 1.0f : 0.5f);
    }

    private void initShadowPaint() {
        if (this.mShadowPaint == null) {
            this.mShadowPaint = new Paint();
            this.mShadowPaint.setAntiAlias(true);
            float f = 10.75f - STROKE_WIDTH;
            float f2 = 10.75f + SHADOW_WIDTH;
            float f3 = (f - SHADOW_WIDTH) / f2;
            float f4 = f / f2;
            float f5 = 10.75f / f2;
            this.mShadowPaint.setShader(new RadialGradient((this.mDensity * 42.0f) / 2.0f, (this.mDensity * 42.0f) / 2.0f, f2 * this.mDensity, new int[]{Color.parseColor("#00000000"), Color.parseColor("#0D000000"), Color.parseColor("#0D000000"), Color.parseColor("#00000000")}, new float[]{f3, f4, f5, 1.0f}, TileMode.CLAMP));
        }
    }

    private void initBackgroundPaint() {
        if (this.mBackgroundPaint == null) {
            this.mBackgroundPaint = new Paint();
            this.mBackgroundPaint.setAntiAlias(true);
            this.mBackgroundPaint.setStyle(Style.FILL);
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_backgroundColor});
            int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), R.color.zhihu_item_checkCircle_backgroundColor, getContext().getTheme()));
            obtainStyledAttributes.recycle();
            this.mBackgroundPaint.setColor(color);
        }
    }

    private void initTextPaint() {
        if (this.mTextPaint == null) {
            this.mTextPaint = new TextPaint();
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setColor(-1);
            this.mTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            this.mTextPaint.setTextSize(12.0f * this.mDensity);
        }
    }

    private Rect getCheckRect() {
        if (this.mCheckRect == null) {
            int i = (int) (((this.mDensity * 42.0f) / 2.0f) - ((14.0f * this.mDensity) / 2.0f));
            this.mCheckRect = new Rect(i, i, (int) ((this.mDensity * 42.0f) - ((float) i)), (int) ((this.mDensity * 42.0f) - ((float) i)));
        }
        return this.mCheckRect;
    }
}
