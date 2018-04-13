package com.spriteapp.booklibrary.widget.readview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import com.spriteapp.booklibrary.a.b;
import com.spriteapp.booklibrary.b.a;
import com.spriteapp.booklibrary.e.c;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import java.util.List;

public abstract class BaseReadView extends View {
    protected float actiondownX;
    protected float actiondownY;
    protected String bookId;
    private boolean cancel = false;
    private boolean center = false;
    private int dx;
    private int dy;
    private long et = 0;
    public boolean isPrepared = false;
    protected OnReadStateChangeListener listener;
    protected Bitmap mCurPageBitmap;
    protected Canvas mCurrentPageCanvas;
    protected Bitmap mNextPageBitmap;
    protected Canvas mNextPageCanvas;
    protected int mScreenHeight;
    protected int mScreenWidth;
    Scroller mScroller;
    protected PointF mTouch = new PointF();
    private TouchPageListener mTouchPageListener;
    protected PageFactory pagefactory = null;
    protected float touch_down = 0.0f;

    public interface TouchPageListener {
        void touchPage();
    }

    protected abstract void abortAnimation();

    protected abstract void calcCornerXY(float f, float f2);

    protected abstract void calcPoints();

    protected abstract void drawCurrentBackArea(Canvas canvas);

    protected abstract void drawCurrentPageArea(Canvas canvas);

    protected abstract void drawCurrentPageShadow(Canvas canvas);

    protected abstract void drawNextPageAreaAndShadow(Canvas canvas);

    protected abstract void restoreAnimation();

    protected abstract void setBitmaps(Bitmap bitmap, Bitmap bitmap2);

    public abstract void setTheme(int i);

    protected abstract void startAnimation();

    public BaseReadView(Context context, String str, List<BookChapterResponse> list, OnReadStateChangeListener onReadStateChangeListener) {
        super(context);
        this.listener = onReadStateChangeListener;
        this.bookId = str;
        this.mScreenWidth = ScreenUtil.getScreenWidth();
        this.mScreenHeight = ScreenUtil.getScreenHeight();
        this.mScroller = new Scroller(getContext());
        try {
            this.mCurPageBitmap = Bitmap.createBitmap(this.mScreenWidth, this.mScreenHeight, Config.RGB_565);
            this.mNextPageBitmap = Bitmap.createBitmap(this.mScreenWidth, this.mScreenHeight, Config.RGB_565);
            this.mCurrentPageCanvas = new Canvas(this.mCurPageBitmap);
            this.mNextPageCanvas = new Canvas(this.mNextPageBitmap);
            this.pagefactory = new PageFactory(getContext(), str, list);
            this.pagefactory.setOnReadStateChangeListener(onReadStateChangeListener);
        } catch (OutOfMemoryError e) {
        }
    }

    public PageFactory getPageFactory() {
        return this.pagefactory;
    }

    public synchronized void init() {
        if (!this.isPrepared) {
            try {
                int[] b = c.a().b(this.bookId);
                if (this.pagefactory.openBook(b[0], new int[]{b[1], b[2]}) == 0) {
                    this.listener.onLoadChapterFailure(b[0]);
                } else {
                    this.pagefactory.onDraw(this.mCurrentPageCanvas);
                    postInvalidate();
                    this.isPrepared = true;
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.pagefactory == null) {
            return true;
        }
        int x;
        int y;
        switch (motionEvent.getAction()) {
            case 0:
                this.et = System.currentTimeMillis();
                this.dx = (int) motionEvent.getX();
                this.dy = (int) motionEvent.getY();
                this.mTouch.x = (float) this.dx;
                this.mTouch.y = (float) this.dy;
                this.actiondownX = (float) this.dx;
                this.actiondownY = (float) this.dy;
                this.touch_down = 0.0f;
                this.pagefactory.onDraw(this.mCurrentPageCanvas);
                boolean z2 = this.actiondownX >= ((float) (this.mScreenWidth / 3)) && this.actiondownX <= ((float) ((this.mScreenWidth * 2) / 3)) && this.actiondownY >= ((float) (this.mScreenHeight / 3)) && this.actiondownY <= ((float) ((this.mScreenHeight * 2) / 3));
                boolean z3;
                if (this.actiondownY <= getResources().getDimension(b.book_reader_top_bar_height) || this.actiondownY >= ((float) this.mScreenHeight) - getResources().getDimension(b.book_reader_bottom_layout_height)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z2 || r3) {
                    this.center = true;
                    break;
                } else if (this.actiondownX > ScreenUtil.dpToPx(20.0f)) {
                    this.center = false;
                    calcCornerXY(this.actiondownX, this.actiondownY);
                    BookStatus prePage;
                    if (this.actiondownX < ((float) (this.mScreenWidth / 2))) {
                        if (this.mTouchPageListener != null) {
                            this.mTouchPageListener.touchPage();
                        }
                        prePage = this.pagefactory.prePage();
                        if (prePage == BookStatus.NO_PRE_PAGE) {
                            ToastUtil.showSingleToast("没有上一页啦");
                            return false;
                        } else if (prePage != BookStatus.LOAD_SUCCESS) {
                            return false;
                        } else {
                            abortAnimation();
                            this.pagefactory.onDraw(this.mNextPageCanvas);
                        }
                    } else if (this.actiondownX >= ((float) (this.mScreenWidth / 2))) {
                        if (this.mTouchPageListener != null) {
                            this.mTouchPageListener.touchPage();
                        }
                        prePage = this.pagefactory.nextPage();
                        if (prePage == BookStatus.NO_NEXT_PAGE) {
                            ToastUtil.showSingleToast("没有下一页啦");
                            return false;
                        } else if (prePage != BookStatus.LOAD_SUCCESS) {
                            return false;
                        } else {
                            abortAnimation();
                            this.pagefactory.onDraw(this.mNextPageCanvas);
                        }
                    }
                    this.listener.onFlip();
                    setBitmaps(this.mCurPageBitmap, this.mNextPageBitmap);
                    break;
                } else {
                    return false;
                }
                break;
            case 1:
            case 3:
                long currentTimeMillis = System.currentTimeMillis();
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                if (this.center) {
                    if (this instanceof PageWidget) {
                        this.mTouch.x = 0.1f;
                        this.mTouch.y = 0.1f;
                    }
                    if (Math.abs(((float) x) - this.actiondownX) < 5.0f && Math.abs(((float) y) - this.actiondownY) < 5.0f) {
                        this.listener.onCenterClick();
                        return false;
                    }
                } else if (Math.abs(x - this.dx) >= 10 || Math.abs(y - this.dy) >= 10) {
                    if (this.cancel) {
                        this.pagefactory.cancelPage();
                        restoreAnimation();
                        postInvalidate();
                    } else {
                        startAnimation();
                        postInvalidate();
                    }
                    this.cancel = false;
                    this.center = false;
                    break;
                } else {
                    if (currentTimeMillis - this.et < 1000) {
                        startAnimation();
                    } else {
                        this.pagefactory.cancelPage();
                        restoreAnimation();
                    }
                    postInvalidate();
                    return true;
                }
                break;
            case 2:
                if (!this.center) {
                    x = (int) motionEvent.getX();
                    y = (int) motionEvent.getY();
                    if ((this.actiondownX < ((float) (this.mScreenWidth / 2)) && ((float) x) < this.mTouch.x) || (this.actiondownX > ((float) (this.mScreenWidth / 2)) && ((float) x) > this.mTouch.x)) {
                        z = true;
                    }
                    this.cancel = z;
                    this.mTouch.x = (float) x;
                    this.mTouch.y = (float) y;
                    this.touch_down = this.mTouch.x - this.actiondownX;
                    postInvalidate();
                    break;
                }
                break;
        }
        return true;
    }

    public void setTouchPageListener(TouchPageListener touchPageListener) {
        this.mTouchPageListener = touchPageListener;
    }

    protected void onDraw(Canvas canvas) {
        if (this.pagefactory != null) {
            calcPoints();
            drawCurrentPageArea(canvas);
            drawNextPageAreaAndShadow(canvas);
            drawCurrentPageShadow(canvas);
            drawCurrentBackArea(canvas);
        }
    }

    protected void resetTouchPoint() {
        this.mTouch.x = 0.1f;
        this.mTouch.y = 0.1f;
        this.touch_down = 0.0f;
        calcCornerXY(this.mTouch.x, this.mTouch.y);
    }

    public void jumpToChapter(int i) {
        resetTouchPoint();
        this.pagefactory.openBook(i, new int[]{0, 0});
        this.pagefactory.onDraw(this.mCurrentPageCanvas);
        this.pagefactory.onDraw(this.mNextPageCanvas);
        postInvalidate();
    }

    public void setSelectPage(int i) {
        resetTouchPoint();
        if (this.isPrepared && this.pagefactory != null) {
            this.pagefactory.setSelectPage(i);
            this.pagefactory.onDraw(this.mCurrentPageCanvas);
            postInvalidate();
        }
    }

    public int getChapterTotalPage() {
        if (this.pagefactory == null) {
            return 0;
        }
        return this.pagefactory.getChapterTotalPage();
    }

    public void setProgressCallback(a aVar) {
        if (this.pagefactory != null) {
            this.pagefactory.setProgressCallback(aVar);
        }
    }

    public void setCurrentChapter() {
        if (this.pagefactory != null) {
            this.pagefactory.setCurrentChapter();
        }
    }

    public int setChapterTotalPage() {
        if (this.pagefactory == null) {
            return 0;
        }
        return this.pagefactory.setChapterTotalPage();
    }

    public float getCurrentProgress() {
        if (this.pagefactory == null) {
            return 0.0f;
        }
        return this.pagefactory.getCurrentProgress();
    }

    public synchronized void setFontSize(int i) {
        resetTouchPoint();
        this.pagefactory.setTextFont(i);
        if (this.isPrepared) {
            this.pagefactory.onDraw(this.mCurrentPageCanvas);
            this.pagefactory.onDraw(this.mNextPageCanvas);
            c.a().a(i);
            postInvalidate();
        }
    }

    public synchronized void setTextColor(int i, int i2) {
        resetTouchPoint();
        this.pagefactory.setTextColor(i, i2);
        if (this.isPrepared) {
            this.pagefactory.onDraw(this.mCurrentPageCanvas);
            this.pagefactory.onDraw(this.mNextPageCanvas);
            postInvalidate();
        }
    }

    public void setBattery(int i) {
        if (this.pagefactory != null) {
            this.pagefactory.setBattery(i);
            if (this.isPrepared) {
                this.pagefactory.onDraw(this.mCurrentPageCanvas);
                postInvalidate();
            }
        }
    }

    public void setTime(String str) {
        if (this.pagefactory != null) {
            this.pagefactory.setTime(str);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if ((this instanceof PageWidget) && this.mTouch != null) {
            this.mTouch.x = 0.1f;
            this.mTouch.y = 0.1f;
            if (z) {
                restoreAnimation();
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.pagefactory != null) {
            this.pagefactory.recycle();
        }
        if (!(this.mCurPageBitmap == null || this.mCurPageBitmap.isRecycled())) {
            this.mCurPageBitmap.recycle();
            this.mCurPageBitmap = null;
        }
        if (this.mNextPageBitmap != null && !this.mNextPageBitmap.isRecycled()) {
            this.mNextPageBitmap.recycle();
            this.mNextPageBitmap = null;
        }
    }
}
