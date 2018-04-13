package com.spriteapp.booklibrary.widget.readview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region.Op;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import com.spriteapp.booklibrary.e.e;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import java.util.List;

public class OverlappedWidget extends BaseReadView {
    public static final float DRAW_VALUE = 0.5f;
    GradientDrawable mBackShadowDrawableLR;
    GradientDrawable mBackShadowDrawableRL;
    private Path mPath0;

    public OverlappedWidget(Context context, String str, List<BookChapterResponse> list, OnReadStateChangeListener onReadStateChangeListener) {
        super(context, str, list, onReadStateChangeListener);
        this.mTouch.x = 0.01f;
        this.mTouch.y = 0.01f;
        this.mPath0 = new Path();
        int[] iArr = new int[]{-1436129690, 6710886};
        this.mBackShadowDrawableRL = new GradientDrawable(Orientation.RIGHT_LEFT, iArr);
        this.mBackShadowDrawableRL.setGradientType(0);
        this.mBackShadowDrawableLR = new GradientDrawable(Orientation.LEFT_RIGHT, iArr);
        this.mBackShadowDrawableLR.setGradientType(0);
    }

    protected void drawCurrentPageArea(Canvas canvas) {
        this.mPath0.reset();
        canvas.save();
        if (this.actiondownX > ((float) (this.mScreenWidth >> 1))) {
            this.mPath0.moveTo(((float) this.mScreenWidth) + this.touch_down, 0.0f);
            this.mPath0.lineTo(((float) this.mScreenWidth) + this.touch_down, (float) this.mScreenHeight);
            this.mPath0.lineTo((float) this.mScreenWidth, (float) this.mScreenHeight);
            this.mPath0.lineTo((float) this.mScreenWidth, 0.0f);
            this.mPath0.lineTo(((float) this.mScreenWidth) + this.touch_down, 0.0f);
            this.mPath0.close();
            canvas.clipPath(this.mPath0, Op.XOR);
            canvas.drawBitmap(this.mCurPageBitmap, this.touch_down, 0.0f, null);
        } else {
            this.mPath0.moveTo(this.touch_down, 0.0f);
            this.mPath0.lineTo(this.touch_down, (float) this.mScreenHeight);
            this.mPath0.lineTo((float) this.mScreenWidth, (float) this.mScreenHeight);
            this.mPath0.lineTo((float) this.mScreenWidth, 0.0f);
            this.mPath0.lineTo(this.touch_down, 0.0f);
            this.mPath0.close();
            canvas.clipPath(this.mPath0);
            canvas.drawBitmap(this.mCurPageBitmap, this.touch_down, 0.0f, null);
        }
        try {
            canvas.restore();
        } catch (Exception e) {
        }
    }

    protected void drawCurrentPageShadow(Canvas canvas) {
        if (!SharedPreferencesUtil.getInstance().getBoolean("hua_xi_is_night_mode")) {
            GradientDrawable gradientDrawable;
            canvas.save();
            if (this.actiondownX > ((float) (this.mScreenWidth >> 1))) {
                gradientDrawable = this.mBackShadowDrawableLR;
                gradientDrawable.setBounds((int) ((((float) this.mScreenWidth) + this.touch_down) - 0.5f), 0, (int) ((((float) this.mScreenWidth) + this.touch_down) + 0.5f), this.mScreenHeight);
            } else {
                gradientDrawable = this.mBackShadowDrawableRL;
                gradientDrawable.setBounds((int) (this.touch_down - 0.5f), 0, (int) (this.touch_down + 0.5f), this.mScreenHeight);
            }
            gradientDrawable.draw(canvas);
            try {
                canvas.restore();
            } catch (Exception e) {
            }
        }
    }

    protected void drawCurrentBackArea(Canvas canvas) {
    }

    protected void drawNextPageAreaAndShadow(Canvas canvas) {
        canvas.save();
        if (this.actiondownX > ((float) (this.mScreenWidth >> 1))) {
            canvas.clipPath(this.mPath0);
            canvas.drawBitmap(this.mNextPageBitmap, 0.0f, 0.0f, null);
        } else {
            canvas.clipPath(this.mPath0, Op.XOR);
            canvas.drawBitmap(this.mNextPageBitmap, 0.0f, 0.0f, null);
        }
        try {
            canvas.restore();
        } catch (Exception e) {
        }
    }

    protected void calcPoints() {
    }

    protected void calcCornerXY(float f, float f2) {
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mScroller.computeScrollOffset()) {
            float currX = (float) this.mScroller.getCurrX();
            float currY = (float) this.mScroller.getCurrY();
            if (this.actiondownX > ((float) (this.mScreenWidth >> 1))) {
                this.touch_down = -(((float) this.mScreenWidth) - currX);
            } else {
                this.touch_down = currX;
            }
            this.mTouch.y = currY;
            postInvalidate();
        }
    }

    protected void startAnimation() {
        if (this.actiondownX > ((float) (this.mScreenWidth / 2))) {
            this.mScroller.startScroll((int) (((float) this.mScreenWidth) + this.touch_down), (int) this.mTouch.y, (int) (-(((float) this.mScreenWidth) + this.touch_down)), 0, 700);
            return;
        }
        this.mScroller.startScroll((int) this.touch_down, (int) this.mTouch.y, (int) (((float) this.mScreenWidth) - this.touch_down), 0, 700);
    }

    protected void abortAnimation() {
        if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
    }

    protected void restoreAnimation() {
        int i;
        if (this.actiondownX > ((float) (this.mScreenWidth / 2))) {
            i = (int) (((float) this.mScreenWidth) - this.mTouch.x);
        } else {
            i = (int) (-this.mTouch.x);
        }
        this.mScroller.startScroll((int) this.mTouch.x, (int) this.mTouch.y, i, 0, 300);
    }

    public void setBitmaps(Bitmap bitmap, Bitmap bitmap2) {
        this.mCurPageBitmap = bitmap;
        this.mNextPageBitmap = bitmap2;
    }

    public synchronized void setTheme(int i) {
        resetTouchPoint();
        Bitmap a = e.a(i);
        if (a != null) {
            this.pagefactory.setBgBitmap(a);
            this.pagefactory.convertBatteryBitmap();
            if (this.isPrepared) {
                this.pagefactory.onDraw(this.mCurrentPageCanvas);
                this.pagefactory.onDraw(this.mNextPageCanvas);
                postInvalidate();
            }
        }
    }
}
