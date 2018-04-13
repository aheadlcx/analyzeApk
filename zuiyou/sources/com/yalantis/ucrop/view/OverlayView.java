package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cn.xiaochuankeji.a.a.a;
import cn.xiaochuankeji.a.a.b;
import cn.xiaochuankeji.a.a.g;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.util.RectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OverlayView extends View {
    public static final boolean DEFAULT_CIRCLE_DIMMED_LAYER = false;
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final int DEFAULT_FREESTYLE_CROP_MODE = 0;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    public static final int FREESTYLE_CROP_MODE_DISABLE = 0;
    public static final int FREESTYLE_CROP_MODE_ENABLE = 1;
    public static final int FREESTYLE_CROP_MODE_ENABLE_WITH_PASS_THROUGH = 2;
    private OverlayViewChangeListener mCallback;
    private boolean mCircleDimmedLayer;
    private Path mCircularPath;
    private Paint mCropFrameCornersPaint;
    private Paint mCropFramePaint;
    protected float[] mCropGridCenter;
    private int mCropGridColumnCount;
    protected float[] mCropGridCorners;
    private Paint mCropGridPaint;
    private int mCropGridRowCount;
    private int mCropRectCornerTouchAreaLineLength;
    private int mCropRectMinSize;
    private final RectF mCropViewRect;
    private int mCurrentTouchCornerIndex;
    private int mDimmedColor;
    private Paint mDimmedStrokePaint;
    private int mFreestyleCropMode;
    private float[] mGridPoints;
    private float mPreviousTouchX;
    private float mPreviousTouchY;
    private boolean mShouldSetupCropBounds;
    private boolean mShowCropFrame;
    private boolean mShowCropGrid;
    private float mTargetAspectRatio;
    private final RectF mTempRect;
    protected int mThisHeight;
    protected int mThisWidth;
    private int mTouchPointThreshold;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FreestyleMode {
    }

    public OverlayView(Context context) {
        this(context, null);
    }

    public OverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCropViewRect = new RectF();
        this.mTempRect = new RectF();
        this.mGridPoints = null;
        this.mCircularPath = new Path();
        this.mDimmedStrokePaint = new Paint(1);
        this.mCropGridPaint = new Paint(1);
        this.mCropFramePaint = new Paint(1);
        this.mCropFrameCornersPaint = new Paint(1);
        this.mFreestyleCropMode = 0;
        this.mPreviousTouchX = -1.0f;
        this.mPreviousTouchY = -1.0f;
        this.mCurrentTouchCornerIndex = -1;
        this.mTouchPointThreshold = getResources().getDimensionPixelSize(b.ucrop_default_crop_rect_corner_touch_threshold);
        this.mCropRectMinSize = getResources().getDimensionPixelSize(b.ucrop_default_crop_rect_min_size);
        this.mCropRectCornerTouchAreaLineLength = getResources().getDimensionPixelSize(b.ucrop_default_crop_rect_corner_touch_area_line_length);
        init();
    }

    public OverlayViewChangeListener getOverlayViewChangeListener() {
        return this.mCallback;
    }

    public void setOverlayViewChangeListener(OverlayViewChangeListener overlayViewChangeListener) {
        this.mCallback = overlayViewChangeListener;
    }

    @NonNull
    public RectF getCropViewRect() {
        return this.mCropViewRect;
    }

    @Deprecated
    public boolean isFreestyleCropEnabled() {
        return this.mFreestyleCropMode == 1;
    }

    @Deprecated
    public void setFreestyleCropEnabled(boolean z) {
        this.mFreestyleCropMode = z ? 1 : 0;
    }

    public int getFreestyleCropMode() {
        return this.mFreestyleCropMode;
    }

    public void setFreestyleCropMode(int i) {
        this.mFreestyleCropMode = i;
        postInvalidate();
    }

    public void setCircleDimmedLayer(boolean z) {
        this.mCircleDimmedLayer = z;
    }

    public void setCropGridRowCount(@IntRange(from = 0) int i) {
        this.mCropGridRowCount = i;
        this.mGridPoints = null;
    }

    public void setCropGridColumnCount(@IntRange(from = 0) int i) {
        this.mCropGridColumnCount = i;
        this.mGridPoints = null;
    }

    public void setShowCropFrame(boolean z) {
        this.mShowCropFrame = z;
    }

    public void setShowCropGrid(boolean z) {
        this.mShowCropGrid = z;
    }

    public void setDimmedColor(@ColorInt int i) {
        this.mDimmedColor = i;
    }

    public void setCropFrameStrokeWidth(@IntRange(from = 0) int i) {
        this.mCropFramePaint.setStrokeWidth((float) i);
    }

    public void setCropGridStrokeWidth(@IntRange(from = 0) int i) {
        this.mCropGridPaint.setStrokeWidth((float) i);
    }

    public void setCropFrameColor(@ColorInt int i) {
        this.mCropFramePaint.setColor(i);
    }

    public void setCropGridColor(@ColorInt int i) {
        this.mCropGridPaint.setColor(i);
    }

    public void setTargetAspectRatio(float f) {
        this.mTargetAspectRatio = f;
        if (this.mThisWidth > 0) {
            setupCropBounds();
            postInvalidate();
            return;
        }
        this.mShouldSetupCropBounds = true;
    }

    public void setupCropBounds() {
        int i = (int) (((float) this.mThisWidth) / this.mTargetAspectRatio);
        int i2;
        if (i > this.mThisHeight) {
            i = (int) (((float) this.mThisHeight) * this.mTargetAspectRatio);
            i2 = (this.mThisWidth - i) / 2;
            this.mCropViewRect.set((float) (getPaddingLeft() + i2), (float) getPaddingTop(), (float) ((i + getPaddingLeft()) + i2), (float) (getPaddingTop() + this.mThisHeight));
        } else {
            i2 = (this.mThisHeight - i) / 2;
            this.mCropViewRect.set((float) getPaddingLeft(), (float) (getPaddingTop() + i2), (float) (getPaddingLeft() + this.mThisWidth), (float) ((i + getPaddingTop()) + i2));
        }
        if (this.mCallback != null) {
            this.mCallback.onCropRectUpdated(this.mCropViewRect);
        }
        updateGridPoints();
    }

    private void updateGridPoints() {
        this.mCropGridCorners = RectUtils.getCornersFromRect(this.mCropViewRect);
        this.mCropGridCenter = RectUtils.getCenterFromRect(this.mCropViewRect);
        this.mGridPoints = null;
        this.mCircularPath.reset();
        this.mCircularPath.addCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, Direction.CW);
    }

    protected void init() {
        if (VERSION.SDK_INT < 18 && VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            this.mThisWidth = (getWidth() - getPaddingRight()) - paddingLeft;
            this.mThisHeight = height - paddingTop;
            if (this.mShouldSetupCropBounds) {
                this.mShouldSetupCropBounds = false;
                setTargetAspectRatio(this.mTargetAspectRatio);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDimmedLayer(canvas);
        drawCropGrid(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (this.mCropViewRect.isEmpty() || this.mFreestyleCropMode == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if ((motionEvent.getAction() & 255) == 0) {
            this.mCurrentTouchCornerIndex = getCurrentTouchIndex(x, y);
            if (this.mCurrentTouchCornerIndex == -1) {
                z = false;
            }
            if (!z) {
                this.mPreviousTouchX = -1.0f;
                this.mPreviousTouchY = -1.0f;
                return z;
            } else if (this.mPreviousTouchX >= 0.0f) {
                return z;
            } else {
                this.mPreviousTouchX = x;
                this.mPreviousTouchY = y;
                return z;
            }
        } else if ((motionEvent.getAction() & 255) == 2 && motionEvent.getPointerCount() == 1 && this.mCurrentTouchCornerIndex != -1) {
            float min = Math.min(Math.max(x, (float) getPaddingLeft()), (float) (getWidth() - getPaddingRight()));
            x = Math.min(Math.max(y, (float) getPaddingTop()), (float) (getHeight() - getPaddingBottom()));
            updateCropViewRect(min, x);
            this.mPreviousTouchX = min;
            this.mPreviousTouchY = x;
            return true;
        } else {
            if ((motionEvent.getAction() & 255) == 1) {
                this.mPreviousTouchX = -1.0f;
                this.mPreviousTouchY = -1.0f;
                this.mCurrentTouchCornerIndex = -1;
                if (this.mCallback != null) {
                    this.mCallback.onCropRectUpdated(this.mCropViewRect);
                }
            }
            return false;
        }
    }

    private void updateCropViewRect(float f, float f2) {
        Object obj;
        float f3;
        float f4;
        float f5;
        float f6;
        Object obj2 = 1;
        this.mTempRect.set(this.mCropViewRect);
        switch (this.mCurrentTouchCornerIndex) {
            case 0:
                this.mTempRect.set(f, f2, this.mCropViewRect.right, this.mCropViewRect.bottom);
                break;
            case 1:
                this.mTempRect.set(this.mCropViewRect.left, f2, f, this.mCropViewRect.bottom);
                break;
            case 2:
                this.mTempRect.set(this.mCropViewRect.left, this.mCropViewRect.top, f, f2);
                break;
            case 3:
                this.mTempRect.set(f, this.mCropViewRect.top, this.mCropViewRect.right, f2);
                break;
            case 4:
                this.mTempRect.offset(f - this.mPreviousTouchX, f2 - this.mPreviousTouchY);
                if (this.mTempRect.left > ((float) getLeft()) && this.mTempRect.top > ((float) getTop()) && this.mTempRect.right < ((float) getRight()) && this.mTempRect.bottom < ((float) getBottom())) {
                    this.mCropViewRect.set(this.mTempRect);
                    updateGridPoints();
                    postInvalidate();
                    return;
                }
                return;
        }
        if (this.mTempRect.height() >= ((float) this.mCropRectMinSize)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (this.mTempRect.width() < ((float) this.mCropRectMinSize)) {
            obj2 = null;
        }
        RectF rectF = this.mCropViewRect;
        if (obj2 != null) {
            f3 = this.mTempRect.left;
        } else {
            f3 = this.mCropViewRect.left;
        }
        if (obj != null) {
            f4 = this.mTempRect.top;
        } else {
            f4 = this.mCropViewRect.top;
        }
        if (obj2 != null) {
            f5 = this.mTempRect.right;
        } else {
            f5 = this.mCropViewRect.right;
        }
        if (obj != null) {
            f6 = this.mTempRect.bottom;
        } else {
            f6 = this.mCropViewRect.bottom;
        }
        rectF.set(f3, f4, f5, f6);
        if (obj != null || obj2 != null) {
            updateGridPoints();
            postInvalidate();
        }
    }

    private int getCurrentTouchIndex(float f, float f2) {
        int i = -1;
        double d = (double) this.mTouchPointThreshold;
        int i2 = 0;
        while (i2 < 8) {
            int i3;
            double sqrt = Math.sqrt(Math.pow((double) (f - this.mCropGridCorners[i2]), 2.0d) + Math.pow((double) (f2 - this.mCropGridCorners[i2 + 1]), 2.0d));
            if (sqrt < d) {
                i3 = i2 / 2;
            } else {
                sqrt = d;
                i3 = i;
            }
            i2 += 2;
            i = i3;
            d = sqrt;
        }
        if (this.mFreestyleCropMode == 1 && i < 0 && this.mCropViewRect.contains(f, f2)) {
            return 4;
        }
        return i;
    }

    protected void drawDimmedLayer(@NonNull Canvas canvas) {
        canvas.save();
        if (this.mCircleDimmedLayer) {
            canvas.clipPath(this.mCircularPath, Op.DIFFERENCE);
        } else {
            canvas.clipRect(this.mCropViewRect, Op.DIFFERENCE);
        }
        canvas.drawColor(this.mDimmedColor);
        canvas.restore();
        if (this.mCircleDimmedLayer) {
            canvas.drawCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, this.mDimmedStrokePaint);
        }
    }

    protected void drawCropGrid(@NonNull Canvas canvas) {
        int i = 0;
        if (this.mShowCropGrid) {
            if (this.mGridPoints == null && !this.mCropViewRect.isEmpty()) {
                int i2;
                this.mGridPoints = new float[((this.mCropGridRowCount * 4) + (this.mCropGridColumnCount * 4))];
                int i3 = 0;
                for (int i4 = 0; i4 < this.mCropGridRowCount; i4++) {
                    int i5 = i3 + 1;
                    this.mGridPoints[i3] = this.mCropViewRect.left;
                    i2 = i5 + 1;
                    this.mGridPoints[i5] = (this.mCropViewRect.height() * ((((float) i4) + 1.0f) / ((float) (this.mCropGridRowCount + 1)))) + this.mCropViewRect.top;
                    i5 = i2 + 1;
                    this.mGridPoints[i2] = this.mCropViewRect.right;
                    i3 = i5 + 1;
                    this.mGridPoints[i5] = (this.mCropViewRect.height() * ((((float) i4) + 1.0f) / ((float) (this.mCropGridRowCount + 1)))) + this.mCropViewRect.top;
                }
                while (i < this.mCropGridColumnCount) {
                    i2 = i3 + 1;
                    this.mGridPoints[i3] = (this.mCropViewRect.width() * ((((float) i) + 1.0f) / ((float) (this.mCropGridColumnCount + 1)))) + this.mCropViewRect.left;
                    i3 = i2 + 1;
                    this.mGridPoints[i2] = this.mCropViewRect.top;
                    i2 = i3 + 1;
                    this.mGridPoints[i3] = (this.mCropViewRect.width() * ((((float) i) + 1.0f) / ((float) (this.mCropGridColumnCount + 1)))) + this.mCropViewRect.left;
                    i3 = i2 + 1;
                    this.mGridPoints[i2] = this.mCropViewRect.bottom;
                    i++;
                }
            }
            if (this.mGridPoints != null) {
                canvas.drawLines(this.mGridPoints, this.mCropGridPaint);
            }
        }
        if (this.mShowCropFrame) {
            canvas.drawRect(this.mCropViewRect, this.mCropFramePaint);
        }
        if (this.mFreestyleCropMode != 0) {
            canvas.save();
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset((float) this.mCropRectCornerTouchAreaLineLength, (float) (-this.mCropRectCornerTouchAreaLineLength));
            canvas.clipRect(this.mTempRect, Op.DIFFERENCE);
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset((float) (-this.mCropRectCornerTouchAreaLineLength), (float) this.mCropRectCornerTouchAreaLineLength);
            canvas.clipRect(this.mTempRect, Op.DIFFERENCE);
            canvas.drawRect(this.mCropViewRect, this.mCropFrameCornersPaint);
            canvas.restore();
        }
    }

    protected void processStyledAttributes(@NonNull TypedArray typedArray) {
        this.mCircleDimmedLayer = typedArray.getBoolean(g.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        this.mDimmedColor = typedArray.getColor(g.ucrop_UCropView_ucrop_dimmed_color, getResources().getColor(a.ucrop_color_default_dimmed));
        this.mDimmedStrokePaint.setColor(this.mDimmedColor);
        this.mDimmedStrokePaint.setStyle(Style.STROKE);
        this.mDimmedStrokePaint.setStrokeWidth(1.0f);
        initCropFrameStyle(typedArray);
        this.mShowCropFrame = typedArray.getBoolean(g.ucrop_UCropView_ucrop_show_frame, true);
        initCropGridStyle(typedArray);
        this.mShowCropGrid = typedArray.getBoolean(g.ucrop_UCropView_ucrop_show_grid, true);
    }

    private void initCropFrameStyle(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(g.ucrop_UCropView_ucrop_frame_stroke_size, getResources().getDimensionPixelSize(b.ucrop_default_crop_frame_stoke_width));
        int color = typedArray.getColor(g.ucrop_UCropView_ucrop_frame_color, getResources().getColor(a.ucrop_color_default_crop_frame));
        this.mCropFramePaint.setStrokeWidth((float) dimensionPixelSize);
        this.mCropFramePaint.setColor(color);
        this.mCropFramePaint.setStyle(Style.STROKE);
        this.mCropFrameCornersPaint.setStrokeWidth((float) (dimensionPixelSize * 3));
        this.mCropFrameCornersPaint.setColor(color);
        this.mCropFrameCornersPaint.setStyle(Style.STROKE);
    }

    private void initCropGridStyle(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(g.ucrop_UCropView_ucrop_grid_stroke_size, getResources().getDimensionPixelSize(b.ucrop_default_crop_grid_stoke_width));
        int color = typedArray.getColor(g.ucrop_UCropView_ucrop_grid_color, getResources().getColor(a.ucrop_color_default_crop_grid));
        this.mCropGridPaint.setStrokeWidth((float) dimensionPixelSize);
        this.mCropGridPaint.setColor(color);
        this.mCropGridRowCount = typedArray.getInt(g.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.mCropGridColumnCount = typedArray.getInt(g.ucrop_UCropView_ucrop_grid_column_count, 2);
    }
}
