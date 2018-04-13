package com.spriteapp.booklibrary.widget.readview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region.Op;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import com.spriteapp.booklibrary.e.e;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import java.util.List;

public class PageWidget extends BaseReadView {
    int[] mBackShadowColors;
    GradientDrawable mBackShadowDrawableLR;
    GradientDrawable mBackShadowDrawableRL;
    PointF mBezierControl1 = new PointF();
    PointF mBezierControl2 = new PointF();
    PointF mBezierEnd1 = new PointF();
    PointF mBezierEnd2 = new PointF();
    PointF mBezierStart1 = new PointF();
    PointF mBezierStart2 = new PointF();
    PointF mBeziervertex1 = new PointF();
    PointF mBeziervertex2 = new PointF();
    ColorMatrixColorFilter mColorMatrixFilter;
    private int mCornerX = 1;
    private int mCornerY = 1;
    float mDegrees;
    GradientDrawable mFolderShadowDrawableLR;
    GradientDrawable mFolderShadowDrawableRL;
    int[] mFrontShadowColors;
    GradientDrawable mFrontShadowDrawableHBT;
    GradientDrawable mFrontShadowDrawableHTB;
    GradientDrawable mFrontShadowDrawableVLR;
    GradientDrawable mFrontShadowDrawableVRL;
    boolean mIsRTandLB;
    Matrix mMatrix;
    float[] mMatrixArray = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float mMaxLength = ((float) Math.hypot((double) this.mScreenWidth, (double) this.mScreenHeight));
    float mMiddleX;
    float mMiddleY;
    Paint mPaint = new Paint();
    private Path mPath0 = new Path();
    private Path mPath1 = new Path();
    float mTouchToCornerDis;

    public PageWidget(Context context, String str, List<BookChapterResponse> list, OnReadStateChangeListener onReadStateChangeListener) {
        super(context, str, list, onReadStateChangeListener);
        this.mPaint.setStyle(Style.FILL);
        createDrawable();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{0.55f, 0.0f, 0.0f, 0.0f, 80.0f, 0.0f, 0.55f, 0.0f, 0.0f, 80.0f, 0.0f, 0.0f, 0.55f, 0.0f, 80.0f, 0.0f, 0.0f, 0.0f, 0.2f, 0.0f});
        this.mColorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        this.mMatrix = new Matrix();
        this.mTouch.x = 0.01f;
        this.mTouch.y = 0.01f;
    }

    public void calcCornerXY(float f, float f2) {
        boolean z = false;
        if (f <= ((float) (this.mScreenWidth / 2))) {
            this.mCornerX = 0;
        } else {
            this.mCornerX = this.mScreenWidth;
        }
        if (f2 <= ((float) (this.mScreenHeight / 2))) {
            this.mCornerY = 0;
        } else {
            this.mCornerY = this.mScreenHeight;
        }
        if ((this.mCornerX == 0 && this.mCornerY == this.mScreenHeight) || (this.mCornerX == this.mScreenWidth && this.mCornerY == 0)) {
            z = true;
        }
        this.mIsRTandLB = z;
    }

    public PointF getCross(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        PointF pointF5 = new PointF();
        float f = (pointF2.y - pointF.y) / (pointF2.x - pointF.x);
        float f2 = ((pointF.x * pointF2.y) - (pointF2.x * pointF.y)) / (pointF.x - pointF2.x);
        pointF5.x = ((((pointF3.x * pointF4.y) - (pointF4.x * pointF3.y)) / (pointF3.x - pointF4.x)) - f2) / (f - ((pointF4.y - pointF3.y) / (pointF4.x - pointF3.x)));
        pointF5.y = (f * pointF5.x) + f2;
        return pointF5;
    }

    protected void calcPoints() {
        this.mMiddleX = (this.mTouch.x + ((float) this.mCornerX)) / 2.0f;
        this.mMiddleY = (this.mTouch.y + ((float) this.mCornerY)) / 2.0f;
        this.mBezierControl1.x = this.mMiddleX - (((((float) this.mCornerY) - this.mMiddleY) * (((float) this.mCornerY) - this.mMiddleY)) / (((float) this.mCornerX) - this.mMiddleX));
        this.mBezierControl1.y = (float) this.mCornerY;
        this.mBezierControl2.x = (float) this.mCornerX;
        if (((float) this.mCornerY) - this.mMiddleY == 0.0f) {
            this.mBezierControl2.y = this.mMiddleY - (((((float) this.mCornerX) - this.mMiddleX) * (((float) this.mCornerX) - this.mMiddleX)) / 0.1f);
        } else {
            this.mBezierControl2.y = this.mMiddleY - (((((float) this.mCornerX) - this.mMiddleX) * (((float) this.mCornerX) - this.mMiddleX)) / (((float) this.mCornerY) - this.mMiddleY));
        }
        this.mBezierStart1.x = this.mBezierControl1.x - ((((float) this.mCornerX) - this.mBezierControl1.x) / 2.0f);
        this.mBezierStart1.y = (float) this.mCornerY;
        if (this.mTouch.x > 0.0f && this.mTouch.x < ((float) this.mScreenWidth) && (this.mBezierStart1.x < 0.0f || this.mBezierStart1.x > ((float) this.mScreenWidth))) {
            if (this.mBezierStart1.x < 0.0f) {
                this.mBezierStart1.x = ((float) this.mScreenWidth) - this.mBezierStart1.x;
            }
            float abs = Math.abs(((float) this.mCornerX) - this.mTouch.x);
            float f = (((float) this.mScreenWidth) * abs) / this.mBezierStart1.x;
            this.mTouch.x = Math.abs(((float) this.mCornerX) - f);
            abs = (Math.abs(((float) this.mCornerX) - this.mTouch.x) * Math.abs(((float) this.mCornerY) - this.mTouch.y)) / abs;
            this.mTouch.y = Math.abs(((float) this.mCornerY) - abs);
            this.mMiddleX = (this.mTouch.x + ((float) this.mCornerX)) / 2.0f;
            this.mMiddleY = (this.mTouch.y + ((float) this.mCornerY)) / 2.0f;
            this.mBezierControl1.x = this.mMiddleX - (((((float) this.mCornerY) - this.mMiddleY) * (((float) this.mCornerY) - this.mMiddleY)) / (((float) this.mCornerX) - this.mMiddleX));
            this.mBezierControl1.y = (float) this.mCornerY;
            this.mBezierControl2.x = (float) this.mCornerX;
            if (((float) this.mCornerY) - this.mMiddleY == 0.0f) {
                this.mBezierControl2.y = this.mMiddleY - (((((float) this.mCornerX) - this.mMiddleX) * (((float) this.mCornerX) - this.mMiddleX)) / 0.1f);
            } else {
                this.mBezierControl2.y = this.mMiddleY - (((((float) this.mCornerX) - this.mMiddleX) * (((float) this.mCornerX) - this.mMiddleX)) / (((float) this.mCornerY) - this.mMiddleY));
            }
            this.mBezierStart1.x = this.mBezierControl1.x - ((((float) this.mCornerX) - this.mBezierControl1.x) / 2.0f);
        }
        this.mBezierStart2.x = (float) this.mCornerX;
        this.mBezierStart2.y = this.mBezierControl2.y - ((((float) this.mCornerY) - this.mBezierControl2.y) / 2.0f);
        this.mTouchToCornerDis = (float) Math.hypot((double) (this.mTouch.x - ((float) this.mCornerX)), (double) (this.mTouch.y - ((float) this.mCornerY)));
        this.mBezierEnd1 = getCross(this.mTouch, this.mBezierControl1, this.mBezierStart1, this.mBezierStart2);
        this.mBezierEnd2 = getCross(this.mTouch, this.mBezierControl2, this.mBezierStart1, this.mBezierStart2);
        this.mBeziervertex1.x = ((this.mBezierStart1.x + (this.mBezierControl1.x * 2.0f)) + this.mBezierEnd1.x) / 4.0f;
        this.mBeziervertex1.y = (((this.mBezierControl1.y * 2.0f) + this.mBezierStart1.y) + this.mBezierEnd1.y) / 4.0f;
        this.mBeziervertex2.x = ((this.mBezierStart2.x + (this.mBezierControl2.x * 2.0f)) + this.mBezierEnd2.x) / 4.0f;
        this.mBeziervertex2.y = (((this.mBezierControl2.y * 2.0f) + this.mBezierStart2.y) + this.mBezierEnd2.y) / 4.0f;
    }

    protected void drawCurrentPageArea(Canvas canvas) {
        this.mPath0.reset();
        this.mPath0.moveTo(this.mBezierStart1.x, this.mBezierStart1.y);
        this.mPath0.quadTo(this.mBezierControl1.x, this.mBezierControl1.y, this.mBezierEnd1.x, this.mBezierEnd1.y);
        this.mPath0.lineTo(this.mTouch.x, this.mTouch.y);
        this.mPath0.lineTo(this.mBezierEnd2.x, this.mBezierEnd2.y);
        this.mPath0.quadTo(this.mBezierControl2.x, this.mBezierControl2.y, this.mBezierStart2.x, this.mBezierStart2.y);
        this.mPath0.lineTo((float) this.mCornerX, (float) this.mCornerY);
        this.mPath0.close();
        canvas.save();
        canvas.clipPath(this.mPath0, Op.XOR);
        canvas.drawBitmap(this.mCurPageBitmap, 0.0f, 0.0f, null);
        try {
            canvas.restore();
        } catch (Exception e) {
        }
    }

    protected void drawNextPageAreaAndShadow(Canvas canvas) {
        int i;
        int i2;
        GradientDrawable gradientDrawable;
        this.mPath1.reset();
        this.mPath1.moveTo(this.mBezierStart1.x, this.mBezierStart1.y);
        this.mPath1.lineTo(this.mBeziervertex1.x, this.mBeziervertex1.y);
        this.mPath1.lineTo(this.mBeziervertex2.x, this.mBeziervertex2.y);
        this.mPath1.lineTo(this.mBezierStart2.x, this.mBezierStart2.y);
        this.mPath1.lineTo((float) this.mCornerX, (float) this.mCornerY);
        this.mPath1.close();
        this.mDegrees = (float) Math.toDegrees(Math.atan2((double) (this.mBezierControl1.x - ((float) this.mCornerX)), (double) (this.mBezierControl2.y - ((float) this.mCornerY))));
        if (this.mIsRTandLB) {
            i = (int) this.mBezierStart1.x;
            i2 = (int) (this.mBezierStart1.x + (this.mTouchToCornerDis / 4.0f));
            gradientDrawable = this.mBackShadowDrawableLR;
        } else {
            i = (int) (this.mBezierStart1.x - (this.mTouchToCornerDis / 4.0f));
            i2 = (int) this.mBezierStart1.x;
            gradientDrawable = this.mBackShadowDrawableRL;
        }
        canvas.save();
        try {
            canvas.clipPath(this.mPath0);
            canvas.clipPath(this.mPath1, Op.INTERSECT);
        } catch (Exception e) {
        }
        canvas.drawBitmap(this.mNextPageBitmap, 0.0f, 0.0f, null);
        canvas.rotate(this.mDegrees, this.mBezierStart1.x, this.mBezierStart1.y);
        gradientDrawable.setBounds(i, (int) this.mBezierStart1.y, i2, (int) (this.mMaxLength + this.mBezierStart1.y));
        gradientDrawable.draw(canvas);
        canvas.restore();
    }

    public void setBitmaps(Bitmap bitmap, Bitmap bitmap2) {
        this.mCurPageBitmap = bitmap;
        this.mNextPageBitmap = bitmap2;
    }

    private void createDrawable() {
        int[] iArr = new int[]{3355443, -1338821837};
        this.mFolderShadowDrawableRL = new GradientDrawable(Orientation.RIGHT_LEFT, iArr);
        this.mFolderShadowDrawableRL.setGradientType(0);
        this.mFolderShadowDrawableLR = new GradientDrawable(Orientation.LEFT_RIGHT, iArr);
        this.mFolderShadowDrawableLR.setGradientType(0);
        this.mBackShadowColors = new int[]{-15658735, 1118481};
        this.mBackShadowDrawableRL = new GradientDrawable(Orientation.RIGHT_LEFT, this.mBackShadowColors);
        this.mBackShadowDrawableRL.setGradientType(0);
        this.mBackShadowDrawableLR = new GradientDrawable(Orientation.LEFT_RIGHT, this.mBackShadowColors);
        this.mBackShadowDrawableLR.setGradientType(0);
        this.mFrontShadowColors = new int[]{-2146365167, 1118481};
        this.mFrontShadowDrawableVLR = new GradientDrawable(Orientation.LEFT_RIGHT, this.mFrontShadowColors);
        this.mFrontShadowDrawableVLR.setGradientType(0);
        this.mFrontShadowDrawableVRL = new GradientDrawable(Orientation.RIGHT_LEFT, this.mFrontShadowColors);
        this.mFrontShadowDrawableVRL.setGradientType(0);
        this.mFrontShadowDrawableHTB = new GradientDrawable(Orientation.TOP_BOTTOM, this.mFrontShadowColors);
        this.mFrontShadowDrawableHTB.setGradientType(0);
        this.mFrontShadowDrawableHBT = new GradientDrawable(Orientation.BOTTOM_TOP, this.mFrontShadowColors);
        this.mFrontShadowDrawableHBT.setGradientType(0);
    }

    public void drawCurrentPageShadow(Canvas canvas) {
        double atan2;
        float f;
        int i;
        int i2;
        GradientDrawable gradientDrawable;
        int i3;
        GradientDrawable gradientDrawable2;
        float f2;
        if (this.mIsRTandLB) {
            atan2 = 0.7853981633974483d - Math.atan2((double) (this.mBezierControl1.y - this.mTouch.y), (double) (this.mTouch.x - this.mBezierControl1.x));
        } else {
            atan2 = 0.7853981633974483d - Math.atan2((double) (this.mTouch.y - this.mBezierControl1.y), (double) (this.mTouch.x - this.mBezierControl1.x));
        }
        double cos = Math.cos(atan2) * 35.35d;
        atan2 = Math.sin(atan2) * 35.35d;
        float f3 = (float) (cos + ((double) this.mTouch.x));
        if (this.mIsRTandLB) {
            f = (float) (atan2 + ((double) this.mTouch.y));
        } else {
            f = (float) (((double) this.mTouch.y) - atan2);
        }
        this.mPath1.reset();
        this.mPath1.moveTo(f3, f);
        this.mPath1.lineTo(this.mTouch.x, this.mTouch.y);
        this.mPath1.lineTo(this.mBezierControl1.x, this.mBezierControl1.y);
        this.mPath1.lineTo(this.mBezierStart1.x, this.mBezierStart1.y);
        this.mPath1.close();
        canvas.save();
        try {
            canvas.clipPath(this.mPath0, Op.XOR);
            canvas.clipPath(this.mPath1, Op.INTERSECT);
        } catch (Exception e) {
        }
        if (this.mIsRTandLB) {
            i = (int) this.mBezierControl1.x;
            i2 = ((int) this.mBezierControl1.x) + 25;
            gradientDrawable = this.mFrontShadowDrawableVLR;
        } else {
            i = (int) (this.mBezierControl1.x - 25.0f);
            i2 = ((int) this.mBezierControl1.x) + 1;
            gradientDrawable = this.mFrontShadowDrawableVRL;
        }
        canvas.rotate((float) Math.toDegrees(Math.atan2((double) (this.mTouch.x - this.mBezierControl1.x), (double) (this.mBezierControl1.y - this.mTouch.y))), this.mBezierControl1.x, this.mBezierControl1.y);
        gradientDrawable.setBounds(i, (int) (this.mBezierControl1.y - this.mMaxLength), i2, (int) this.mBezierControl1.y);
        gradientDrawable.draw(canvas);
        canvas.restore();
        this.mPath1.reset();
        this.mPath1.moveTo(f3, f);
        this.mPath1.lineTo(this.mTouch.x, this.mTouch.y);
        this.mPath1.lineTo(this.mBezierControl2.x, this.mBezierControl2.y);
        this.mPath1.lineTo(this.mBezierStart2.x, this.mBezierStart2.y);
        this.mPath1.close();
        canvas.save();
        try {
            canvas.clipPath(this.mPath0, Op.XOR);
            canvas.clipPath(this.mPath1, Op.INTERSECT);
        } catch (Exception e2) {
        }
        if (this.mIsRTandLB) {
            i2 = (int) this.mBezierControl2.y;
            i3 = (int) (this.mBezierControl2.y + 25.0f);
            gradientDrawable2 = this.mFrontShadowDrawableHTB;
        } else {
            i2 = (int) (this.mBezierControl2.y - 25.0f);
            i3 = (int) (this.mBezierControl2.y + 1.0f);
            gradientDrawable2 = this.mFrontShadowDrawableHBT;
        }
        canvas.rotate((float) Math.toDegrees(Math.atan2((double) (this.mBezierControl2.y - this.mTouch.y), (double) (this.mBezierControl2.x - this.mTouch.x))), this.mBezierControl2.x, this.mBezierControl2.y);
        if (this.mBezierControl2.y < 0.0f) {
            f2 = this.mBezierControl2.y - ((float) this.mScreenHeight);
        } else {
            f2 = this.mBezierControl2.y;
        }
        i = (int) Math.hypot((double) this.mBezierControl2.x, (double) f2);
        if (((float) i) > this.mMaxLength) {
            gradientDrawable2.setBounds(((int) (this.mBezierControl2.x - 25.0f)) - i, i2, ((int) (this.mBezierControl2.x + this.mMaxLength)) - i, i3);
        } else {
            gradientDrawable2.setBounds((int) (this.mBezierControl2.x - this.mMaxLength), i2, (int) this.mBezierControl2.x, i3);
        }
        gradientDrawable2.draw(canvas);
        canvas.restore();
    }

    protected void drawCurrentBackArea(Canvas canvas) {
        int i;
        int i2;
        GradientDrawable gradientDrawable;
        float min = Math.min(Math.abs(((float) (((int) (this.mBezierStart1.x + this.mBezierControl1.x)) / 2)) - this.mBezierControl1.x), Math.abs(((float) (((int) (this.mBezierStart2.y + this.mBezierControl2.y)) / 2)) - this.mBezierControl2.y));
        this.mPath1.reset();
        this.mPath1.moveTo(this.mBeziervertex2.x, this.mBeziervertex2.y);
        this.mPath1.lineTo(this.mBeziervertex1.x, this.mBeziervertex1.y);
        this.mPath1.lineTo(this.mBezierEnd1.x, this.mBezierEnd1.y);
        this.mPath1.lineTo(this.mTouch.x, this.mTouch.y);
        this.mPath1.lineTo(this.mBezierEnd2.x, this.mBezierEnd2.y);
        this.mPath1.close();
        if (this.mIsRTandLB) {
            i = (int) (this.mBezierStart1.x - 1.0f);
            i2 = (int) ((min + this.mBezierStart1.x) + 1.0f);
            gradientDrawable = this.mFolderShadowDrawableLR;
        } else {
            i = (int) ((this.mBezierStart1.x - min) - 1.0f);
            i2 = (int) (this.mBezierStart1.x + 1.0f);
            gradientDrawable = this.mFolderShadowDrawableRL;
        }
        canvas.save();
        try {
            canvas.clipPath(this.mPath0);
            canvas.clipPath(this.mPath1, Op.INTERSECT);
        } catch (Exception e) {
        }
        this.mPaint.setColorFilter(this.mColorMatrixFilter);
        float hypot = (float) Math.hypot((double) (((float) this.mCornerX) - this.mBezierControl1.x), (double) (this.mBezierControl2.y - ((float) this.mCornerY)));
        float f = (((float) this.mCornerX) - this.mBezierControl1.x) / hypot;
        hypot = (this.mBezierControl2.y - ((float) this.mCornerY)) / hypot;
        this.mMatrixArray[0] = 1.0f - ((2.0f * hypot) * hypot);
        this.mMatrixArray[1] = hypot * (2.0f * f);
        this.mMatrixArray[3] = this.mMatrixArray[1];
        this.mMatrixArray[4] = 1.0f - (f * (2.0f * f));
        this.mMatrix.reset();
        this.mMatrix.setValues(this.mMatrixArray);
        this.mMatrix.preTranslate(-this.mBezierControl1.x, -this.mBezierControl1.y);
        this.mMatrix.postTranslate(this.mBezierControl1.x, this.mBezierControl1.y);
        canvas.drawBitmap(this.mCurPageBitmap, this.mMatrix, this.mPaint);
        this.mPaint.setColorFilter(null);
        canvas.rotate(this.mDegrees, this.mBezierStart1.x, this.mBezierStart1.y);
        gradientDrawable.setBounds(i, (int) this.mBezierStart1.y, i2, (int) (this.mBezierStart1.y + this.mMaxLength));
        gradientDrawable.draw(canvas);
        canvas.restore();
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mScroller.computeScrollOffset()) {
            float currY = (float) this.mScroller.getCurrY();
            this.mTouch.x = (float) this.mScroller.getCurrX();
            this.mTouch.y = currY;
            postInvalidate();
        }
    }

    protected void startAnimation() {
        int i;
        int i2;
        if (this.mCornerX > 0) {
            i = -((int) (((float) this.mScreenWidth) + this.mTouch.x));
        } else {
            i = (int) ((((float) this.mScreenWidth) - this.mTouch.x) + ((float) this.mScreenWidth));
        }
        if (this.mCornerY > 0) {
            i2 = (int) (((float) this.mScreenHeight) - this.mTouch.y);
        } else {
            i2 = (int) (1.0f - this.mTouch.y);
        }
        this.mScroller.startScroll((int) this.mTouch.x, (int) this.mTouch.y, i, i2, 700);
    }

    public void abortAnimation() {
        if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
    }

    public void restoreAnimation() {
        int i;
        int i2;
        if (this.mCornerX > 0) {
            i = (int) (((float) this.mScreenWidth) - this.mTouch.x);
        } else {
            i = (int) (-this.mTouch.x);
        }
        if (this.mCornerY > 0) {
            i2 = (int) (((float) this.mScreenHeight) - this.mTouch.y);
        } else {
            i2 = (int) (1.0f - this.mTouch.y);
        }
        this.mScroller.startScroll((int) this.mTouch.x, (int) this.mTouch.y, i, i2, 300);
    }

    public synchronized void setTheme(int i) {
        resetTouchPoint();
        calcCornerXY(this.mTouch.x, this.mTouch.y);
        Bitmap a = e.a(i);
        if (!(a == null || this.pagefactory == null)) {
            this.pagefactory.setBgBitmap(a);
            this.pagefactory.convertBatteryBitmap();
            if (this.isPrepared) {
                this.pagefactory.onDraw(this.mCurrentPageCanvas);
                this.pagefactory.onDraw(this.mNextPageCanvas);
                postInvalidate();
            }
        }
    }

    public void jumpToChapter(int i) {
        calcCornerXY(this.mTouch.x, this.mTouch.y);
        super.jumpToChapter(i);
    }
}
