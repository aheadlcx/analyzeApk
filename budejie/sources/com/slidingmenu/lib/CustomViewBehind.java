package com.slidingmenu.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import cn.v6.sixrooms.R;

public class CustomViewBehind extends ViewGroup {
    private int a;
    private CustomViewAbove b;
    private View c;
    private View d;
    private int e;
    private int f;
    private SlidingMenu$a g;
    private boolean h;
    private int i;
    private boolean j;
    private final Paint k;
    private float l;
    private Drawable m;
    private Drawable n;
    private int o;
    private float p;
    private boolean q;
    private Bitmap r;
    private View s;

    public CustomViewBehind(Context context) {
        this(context, null);
    }

    public CustomViewBehind(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.k = new Paint();
        this.q = true;
        this.e = (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics());
    }

    public void setCustomViewAbove(CustomViewAbove customViewAbove) {
        this.b = customViewAbove;
    }

    public void setCanvasTransformer(SlidingMenu$a slidingMenu$a) {
        this.g = slidingMenu$a;
    }

    public void setWidthOffset(int i) {
        this.f = i;
        requestLayout();
    }

    public int getBehindWidth() {
        return this.c.getWidth();
    }

    public void setContent(View view) {
        if (this.c != null) {
            removeView(this.c);
        }
        this.c = view;
        addView(this.c);
    }

    public View getContent() {
        return this.c;
    }

    public void setSecondaryContent(View view) {
        if (this.d != null) {
            removeView(this.d);
        }
        this.d = view;
        addView(this.d);
    }

    public View getSecondaryContent() {
        return this.d;
    }

    public void setChildrenEnabled(boolean z) {
        this.h = z;
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
        if (this.g != null) {
            invalidate();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.h;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.h;
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.g != null) {
            canvas.save();
            this.g.a(canvas, this.b.getPercentOpen());
            super.dispatchDraw(canvas);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.c.layout(0, 0, i5 - this.f, i6);
        if (this.d != null) {
            this.d.layout(0, 0, i5 - this.f, i6);
        }
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        setMeasuredDimension(defaultSize, defaultSize2);
        defaultSize = getChildMeasureSpec(i, 0, defaultSize - this.f);
        defaultSize2 = getChildMeasureSpec(i2, 0, defaultSize2);
        this.c.measure(defaultSize, defaultSize2);
        if (this.d != null) {
            this.d.measure(defaultSize, defaultSize2);
        }
    }

    public void setMode(int i) {
        if (i == 0 || i == 1) {
            if (this.c != null) {
                this.c.setVisibility(0);
            }
            if (this.d != null) {
                this.d.setVisibility(4);
            }
        }
        this.i = i;
    }

    public int getMode() {
        return this.i;
    }

    public void setScrollScale(float f) {
        this.l = f;
    }

    public float getScrollScale() {
        return this.l;
    }

    public void setShadowDrawable(Drawable drawable) {
        this.m = drawable;
        invalidate();
    }

    public void setSecondaryShadowDrawable(Drawable drawable) {
        this.n = drawable;
        invalidate();
    }

    public void setShadowWidth(int i) {
        this.o = i;
        invalidate();
    }

    public void setFadeEnabled(boolean z) {
        this.j = z;
    }

    public void setFadeDegree(float f) {
        if (f > 1.0f || f < 0.0f) {
            throw new IllegalStateException("The BehindFadeDegree must be between 0.0f and 1.0f");
        }
        this.p = f;
    }

    public int a(int i) {
        if (i > 1) {
            i = 2;
        } else if (i <= 0) {
            i = 0;
        }
        if (this.i == 0 && i > 1) {
            return 0;
        }
        if (this.i != 1 || i > 0) {
            return i;
        }
        return 2;
    }

    public void a(View view, int i, int i2) {
        int i3 = 0;
        if (this.i == 0) {
            if (i >= view.getLeft()) {
                i3 = 4;
            }
            scrollTo((int) (((float) (getBehindWidth() + i)) * this.l), i2);
        } else if (this.i == 1) {
            if (i <= view.getLeft()) {
                i3 = 4;
            }
            scrollTo((int) (((float) (getBehindWidth() - getWidth())) + (((float) (i - getBehindWidth())) * this.l)), i2);
        } else if (this.i == 2) {
            int i4;
            View view2 = this.c;
            if (i >= view.getLeft()) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            view2.setVisibility(i4);
            view2 = this.d;
            if (i <= view.getLeft()) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            view2.setVisibility(i4);
            if (i == 0) {
                i3 = 4;
            }
            if (i <= view.getLeft()) {
                scrollTo((int) (((float) (getBehindWidth() + i)) * this.l), i2);
            } else {
                scrollTo((int) (((float) (getBehindWidth() - getWidth())) + (((float) (i - getBehindWidth())) * this.l)), i2);
            }
        }
        if (i3 == 4) {
            Log.v("CustomViewBehind", "behind INVISIBLE");
        }
        setVisibility(i3);
    }

    public int a(View view, int i) {
        if (this.i != 0) {
            if (this.i != 1) {
                if (this.i == 2) {
                    switch (i) {
                        case 0:
                            return view.getLeft() - getBehindWidth();
                        case 2:
                            return view.getLeft() + getBehindWidth();
                        default:
                            break;
                    }
                }
            }
            switch (i) {
                case 0:
                    return view.getLeft();
                case 2:
                    return view.getLeft() + getBehindWidth();
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return view.getLeft() - getBehindWidth();
            case 2:
                return view.getLeft();
        }
        return view.getLeft();
    }

    public int a(View view) {
        if (this.i == 0 || this.i == 2) {
            return view.getLeft() - getBehindWidth();
        }
        if (this.i == 1) {
            return view.getLeft();
        }
        return 0;
    }

    public int b(View view) {
        if (this.i == 0) {
            return view.getLeft();
        }
        if (this.i == 1 || this.i == 2) {
            return view.getLeft() + getBehindWidth();
        }
        return 0;
    }

    public boolean b(View view, int i) {
        int left = view.getLeft();
        int right = view.getRight();
        if (this.i == 0) {
            if (i < left || i > left + this.e) {
                return false;
            }
            return true;
        } else if (this.i == 1) {
            if (i > right || i < right - this.e) {
                return false;
            }
            return true;
        } else if (this.i != 2) {
            return false;
        } else {
            if (i >= left && i <= left + this.e) {
                return true;
            }
            if (i > right || i < right - this.e) {
                return false;
            }
            return true;
        }
    }

    public void setTouchMode(int i) {
        this.a = i;
    }

    public boolean a(View view, int i, float f) {
        switch (this.a) {
            case 0:
                return b(view, i, f);
            case 1:
                return true;
            default:
                return false;
        }
    }

    public boolean b(View view, int i, float f) {
        if (this.i == 0 || (this.i == 2 && i == 0)) {
            if (f >= ((float) view.getLeft())) {
                return true;
            }
            return false;
        } else if ((this.i == 1 || (this.i == 2 && i == 2)) && f <= ((float) view.getRight())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean a(float f) {
        if (this.i == 0) {
            if (f > 0.0f) {
                return true;
            }
            return false;
        } else if (this.i == 1) {
            if (f >= 0.0f) {
                return false;
            }
            return true;
        } else if (this.i != 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean b(float f) {
        if (this.i == 0) {
            if (f < 0.0f) {
                return true;
            }
            return false;
        } else if (this.i == 1) {
            if (f <= 0.0f) {
                return false;
            }
            return true;
        } else if (this.i != 2) {
            return false;
        } else {
            return true;
        }
    }

    public void a(View view, Canvas canvas) {
        if (this.m != null && this.o > 0) {
            int right;
            if (this.i != 0) {
                if (this.i == 1) {
                    right = view.getRight();
                } else if (this.i != 2) {
                    right = 0;
                } else if (this.n != null) {
                    right = view.getRight();
                    this.n.setBounds(right, 0, this.o + right, getHeight());
                    this.n.draw(canvas);
                }
                this.m.setBounds(right, 0, this.o + right, getHeight());
                this.m.draw(canvas);
            }
            right = view.getLeft() - this.o;
            this.m.setBounds(right, 0, this.o + right, getHeight());
            this.m.draw(canvas);
        }
    }

    public void a(View view, Canvas canvas, float f) {
        int i = 0;
        if (this.j) {
            int left;
            this.k.setColor(Color.argb((int) ((this.p * 255.0f) * Math.abs(1.0f - f)), 0, 0, 0));
            if (this.i == 0) {
                left = view.getLeft() - getBehindWidth();
                i = view.getLeft();
            } else if (this.i == 1) {
                left = view.getRight();
                i = view.getRight() + getBehindWidth();
            } else if (this.i == 2) {
                Canvas canvas2 = canvas;
                canvas2.drawRect((float) (view.getLeft() - getBehindWidth()), 0.0f, (float) view.getLeft(), (float) getHeight(), this.k);
                left = view.getRight();
                i = view.getRight() + getBehindWidth();
            } else {
                left = 0;
            }
            canvas.drawRect((float) left, 0.0f, (float) i, (float) getHeight(), this.k);
        }
    }

    public void b(View view, Canvas canvas, float f) {
        if (this.q && this.r != null && this.s != null && ((String) this.s.getTag(R.id.selected_view)).equals("CustomViewBehindSelectedView")) {
            canvas.save();
            int width = (int) (((float) this.r.getWidth()) * f);
            int left;
            if (this.i == 0) {
                left = view.getLeft();
                width = left - width;
                canvas.clipRect(width, 0, left, getHeight());
                canvas.drawBitmap(this.r, (float) width, (float) getSelectorTop(), null);
            } else if (this.i == 1) {
                left = view.getRight();
                width += left;
                canvas.clipRect(left, 0, width, getHeight());
                canvas.drawBitmap(this.r, (float) (width - this.r.getWidth()), (float) getSelectorTop(), null);
            }
            canvas.restore();
        }
    }

    public void setSelectorEnabled(boolean z) {
        this.q = z;
    }

    public void setSelectedView(View view) {
        if (this.s != null) {
            this.s.setTag(R.id.selected_view, null);
            this.s = null;
        }
        if (view != null && view.getParent() != null) {
            this.s = view;
            this.s.setTag(R.id.selected_view, "CustomViewBehindSelectedView");
            invalidate();
        }
    }

    private int getSelectorTop() {
        return this.s.getTop() + ((this.s.getHeight() - this.r.getHeight()) / 2);
    }

    public void setSelectorBitmap(Bitmap bitmap) {
        this.r = bitmap;
        refreshDrawableState();
    }
}
