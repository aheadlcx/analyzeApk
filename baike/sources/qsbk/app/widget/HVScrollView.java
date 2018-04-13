package qsbk.app.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Scroller;
import java.util.List;

public class HVScrollView extends FrameLayout {
    private final Rect a;
    private long b;
    private Scroller c;
    private boolean d;
    private float e;
    private float f;
    private boolean g;
    private View h;
    private boolean i;
    private VelocityTracker j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private boolean q;

    public HVScrollView(Context context) {
        this(context, null);
    }

    public HVScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Rect();
        this.g = true;
        this.h = null;
        this.i = false;
        this.l = true;
        this.p = -1;
        this.q = true;
        a();
    }

    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (getScrollY() < verticalFadingEdgeLength) {
            return ((float) getScrollY()) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    protected float getLeftFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (getScrollX() < horizontalFadingEdgeLength) {
            return ((float) getScrollX()) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    protected float getRightFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int right = (getChildAt(0).getRight() - getScrollX()) - (getWidth() - getPaddingRight());
        if (right < horizontalFadingEdgeLength) {
            return ((float) right) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmountV() {
        return (int) (0.5f * ((float) (getBottom() - getTop())));
    }

    public int getMaxScrollAmountH() {
        return (int) (0.5f * ((float) (getRight() - getLeft())));
    }

    private void a() {
        this.c = new Scroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.m = viewConfiguration.getScaledTouchSlop();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.o = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public void addView(View view, int i) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i);
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, layoutParams);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i, layoutParams);
    }

    private boolean b() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (getHeight() < (childAt.getHeight() + getPaddingTop()) + getPaddingBottom()) {
            return true;
        }
        return false;
    }

    private boolean c() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (getWidth() < (childAt.getWidth() + getPaddingLeft()) + getPaddingRight()) {
            return true;
        }
        return false;
    }

    public boolean isFillViewport() {
        return this.k;
    }

    public void setFillViewport(boolean z) {
        if (z != this.k) {
            this.k = z;
            requestLayout();
        }
    }

    public boolean isSmoothScrollingEnabled() {
        return this.l;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.l = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.k) {
            int mode = MeasureSpec.getMode(i2);
            int mode2 = MeasureSpec.getMode(i);
            if (!(mode == 0 && mode2 == 0) && getChildCount() > 0) {
                View childAt = getChildAt(0);
                mode2 = getMeasuredHeight();
                int measuredWidth = getMeasuredWidth();
                if (childAt.getMeasuredHeight() < mode2 || childAt.getMeasuredWidth() < measuredWidth) {
                    childAt.measure(MeasureSpec.makeMeasureSpec((measuredWidth - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((mode2 - getPaddingTop()) - getPaddingBottom(), 1073741824));
                }
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        this.a.setEmpty();
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 19:
                if (!b()) {
                    return false;
                }
                if (keyEvent.isAltPressed()) {
                    return fullScrollV(33);
                }
                return arrowScrollV(33);
            case 20:
                if (!b()) {
                    return false;
                }
                if (keyEvent.isAltPressed()) {
                    return fullScrollV(130);
                }
                return arrowScrollV(130);
            case 21:
                if (!c()) {
                    return false;
                }
                if (keyEvent.isAltPressed()) {
                    return fullScrollH(17);
                }
                return arrowScrollH(17);
            case 22:
                if (!c()) {
                    return false;
                }
                if (keyEvent.isAltPressed()) {
                    return fullScrollH(66);
                }
                return arrowScrollH(66);
            default:
                return false;
        }
    }

    private boolean a(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        if (i2 < childAt.getTop() - scrollY || i2 >= childAt.getBottom() - scrollY || i < childAt.getLeft() - scrollX || i >= childAt.getRight() - scrollX) {
            return false;
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        int action = motionEvent.getAction();
        if (action == 2 && this.i) {
            return true;
        }
        float x;
        switch (action & 255) {
            case 0:
                x = motionEvent.getX();
                float y = motionEvent.getY();
                if (!a((int) x, (int) y)) {
                    this.i = false;
                    break;
                }
                this.e = y;
                this.f = x;
                this.p = motionEvent.getPointerId(0);
                if (this.c.isFinished()) {
                    z = false;
                }
                this.i = z;
                break;
            case 1:
            case 3:
                this.i = false;
                this.p = -1;
                break;
            case 2:
                int i = this.p;
                if (i != -1) {
                    i = motionEvent.findPointerIndex(i);
                    x = motionEvent.getY(i);
                    if (((int) Math.abs(x - this.e)) > this.m) {
                        this.i = true;
                        this.e = x;
                    }
                    float x2 = motionEvent.getX(i);
                    if (((int) Math.abs(x2 - this.f)) > this.m) {
                        this.i = true;
                        this.f = x2;
                        break;
                    }
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return this.i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.j == null) {
            this.j = VelocityTracker.obtain();
        }
        this.j.addMovement(motionEvent);
        float x;
        int xVelocity;
        int yVelocity;
        switch (motionEvent.getAction() & 255) {
            case 0:
                x = motionEvent.getX();
                float y = motionEvent.getY();
                boolean a = a((int) x, (int) y);
                this.i = a;
                if (a) {
                    if (!this.c.isFinished()) {
                        this.c.abortAnimation();
                    }
                    this.e = y;
                    this.f = x;
                    this.p = motionEvent.getPointerId(0);
                    break;
                }
                return false;
            case 1:
                if (this.i) {
                    if (this.q) {
                        VelocityTracker velocityTracker = this.j;
                        velocityTracker.computeCurrentVelocity(1000, (float) this.o);
                        xVelocity = (int) velocityTracker.getXVelocity();
                        yVelocity = (int) velocityTracker.getYVelocity();
                        if (getChildCount() > 0 && (Math.abs(xVelocity) > xVelocity || Math.abs(yVelocity) > this.n)) {
                            fling(-xVelocity, -yVelocity);
                        }
                    }
                    this.p = -1;
                    this.i = false;
                    if (this.j != null) {
                        this.j.recycle();
                        this.j = null;
                        break;
                    }
                }
                break;
            case 2:
                if (this.i) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.p);
                    x = motionEvent.getY(findPointerIndex);
                    xVelocity = (int) (this.e - x);
                    this.e = x;
                    float x2 = motionEvent.getX(findPointerIndex);
                    yVelocity = (int) (this.f - x2);
                    this.f = x2;
                    scrollBy(yVelocity, xVelocity);
                    break;
                }
                break;
            case 3:
                if (this.i && getChildCount() > 0) {
                    this.p = -1;
                    this.i = false;
                    if (this.j != null) {
                        this.j.recycle();
                        this.j = null;
                        break;
                    }
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return true;
    }

    private void a(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.p) {
            action = action == 0 ? 1 : 0;
            this.f = motionEvent.getX(action);
            this.e = motionEvent.getY(action);
            this.p = motionEvent.getPointerId(action);
            if (this.j != null) {
                this.j.clear();
            }
        }
    }

    private View a(boolean z, int i, int i2) {
        List focusables = getFocusables(2);
        View view = null;
        Object obj = null;
        int size = focusables.size();
        int i3 = 0;
        while (i3 < size) {
            View view2;
            Object obj2;
            View view3 = (View) focusables.get(i3);
            int top = view3.getTop();
            int bottom = view3.getBottom();
            if (i < bottom && top < i2) {
                Object obj3 = (i >= top || bottom >= i2) ? null : 1;
                if (view == null) {
                    Object obj4 = obj3;
                    view2 = view3;
                    obj2 = obj4;
                } else {
                    Object obj5 = ((!z || top >= view.getTop()) && (z || bottom <= view.getBottom())) ? null : 1;
                    if (obj != null) {
                        if (!(obj3 == null || obj5 == null)) {
                            view2 = view3;
                            obj2 = obj;
                        }
                    } else if (obj3 != null) {
                        view2 = view3;
                        int i4 = 1;
                    } else if (obj5 != null) {
                        view2 = view3;
                        obj2 = obj;
                    }
                }
                i3++;
                view = view2;
                obj = obj2;
            }
            obj2 = obj;
            view2 = view;
            i3++;
            view = view2;
            obj = obj2;
        }
        return view;
    }

    private View b(boolean z, int i, int i2) {
        List focusables = getFocusables(2);
        View view = null;
        Object obj = null;
        int size = focusables.size();
        int i3 = 0;
        while (i3 < size) {
            View view2;
            Object obj2;
            View view3 = (View) focusables.get(i3);
            int left = view3.getLeft();
            int right = view3.getRight();
            if (i < right && left < i2) {
                Object obj3 = (i >= left || right >= i2) ? null : 1;
                if (view == null) {
                    Object obj4 = obj3;
                    view2 = view3;
                    obj2 = obj4;
                } else {
                    Object obj5 = ((!z || left >= view.getLeft()) && (z || right <= view.getRight())) ? null : 1;
                    if (obj != null) {
                        if (!(obj3 == null || obj5 == null)) {
                            view2 = view3;
                            obj2 = obj;
                        }
                    } else if (obj3 != null) {
                        view2 = view3;
                        int i4 = 1;
                    } else if (obj5 != null) {
                        view2 = view3;
                        obj2 = obj;
                    }
                }
                i3++;
                view = view2;
                obj = obj2;
            }
            obj2 = obj;
            view2 = view;
            i3++;
            view = view2;
            obj = obj2;
        }
        return view;
    }

    public boolean fullScrollV(int i) {
        int i2 = i == 130 ? 1 : 0;
        int height = getHeight();
        this.a.top = 0;
        this.a.bottom = height;
        if (i2 != 0) {
            i2 = getChildCount();
            if (i2 > 0) {
                this.a.bottom = getChildAt(i2 - 1).getBottom();
                this.a.top = this.a.bottom - height;
            }
        }
        return a(i, this.a.top, this.a.bottom);
    }

    public boolean fullScrollH(int i) {
        int i2 = i == 66 ? 1 : 0;
        int width = getWidth();
        this.a.left = 0;
        this.a.right = width;
        if (i2 != 0 && getChildCount() > 0) {
            this.a.right = getChildAt(0).getRight();
            this.a.left = this.a.right - width;
        }
        return b(i, this.a.left, this.a.right);
    }

    private boolean a(int i, int i2, int i3) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = scrollY + height;
        boolean z = i == 33;
        View a = a(z, i2, i3);
        if (a == null) {
            a = this;
        }
        if (i2 < scrollY || i3 > i4) {
            a(z ? i2 - scrollY : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (a != findFocus() && a.requestFocus(i)) {
            this.d = true;
            this.d = false;
        }
        return z;
    }

    private boolean b(int i, int i2, int i3) {
        int width = getWidth();
        int scrollX = getScrollX();
        int i4 = scrollX + width;
        boolean z = i == 17;
        View b = b(z, i2, i3);
        if (b == null) {
            b = this;
        }
        if (i2 < scrollX || i3 > i4) {
            b(z ? i2 - scrollX : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (b != findFocus() && b.requestFocus(i)) {
            this.d = true;
            this.d = false;
        }
        return z;
    }

    public boolean arrowScrollV(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmountV = getMaxScrollAmountV();
        if (findNextFocus == null || !a(findNextFocus, maxScrollAmountV, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmountV) {
                maxScrollAmountV = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                int bottom = getChildAt(0).getBottom();
                int scrollY = getScrollY() + getHeight();
                if (bottom - scrollY < maxScrollAmountV) {
                    maxScrollAmountV = bottom - scrollY;
                }
            }
            if (maxScrollAmountV == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmountV = -maxScrollAmountV;
            }
            a(maxScrollAmountV);
        } else {
            findNextFocus.getDrawingRect(this.a);
            offsetDescendantRectToMyCoords(findNextFocus, this.a);
            a(a(this.a));
            findNextFocus.requestFocus(i);
        }
        if (findFocus != null && findFocus.isFocused() && a(findFocus)) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    public boolean arrowScrollH(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmountH = getMaxScrollAmountH();
        if (findNextFocus == null || !a(findNextFocus, maxScrollAmountH)) {
            if (i == 17 && getScrollX() < maxScrollAmountH) {
                maxScrollAmountH = getScrollX();
            } else if (i == 66 && getChildCount() > 0) {
                int right = getChildAt(0).getRight();
                int scrollX = getScrollX() + getWidth();
                if (right - scrollX < maxScrollAmountH) {
                    maxScrollAmountH = right - scrollX;
                }
            }
            if (maxScrollAmountH == 0) {
                return false;
            }
            if (i != 66) {
                maxScrollAmountH = -maxScrollAmountH;
            }
            b(maxScrollAmountH);
        } else {
            findNextFocus.getDrawingRect(this.a);
            offsetDescendantRectToMyCoords(findNextFocus, this.a);
            b(b(this.a));
            findNextFocus.requestFocus(i);
        }
        if (findFocus != null && findFocus.isFocused() && b(findFocus)) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    private boolean a(View view) {
        return !a(view, 0, getHeight());
    }

    private boolean b(View view) {
        return !a(view, 0);
    }

    private boolean a(View view, int i, int i2) {
        view.getDrawingRect(this.a);
        offsetDescendantRectToMyCoords(view, this.a);
        return this.a.bottom + i >= getScrollY() && this.a.top - i <= getScrollY() + i2;
    }

    private boolean a(View view, int i) {
        view.getDrawingRect(this.a);
        offsetDescendantRectToMyCoords(view, this.a);
        return this.a.right + i >= getScrollX() && this.a.left - i <= getScrollX() + getWidth();
    }

    private void a(int i) {
        if (i == 0) {
            return;
        }
        if (this.l) {
            smoothScrollBy(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    private void b(int i) {
        if (i == 0) {
            return;
        }
        if (this.l) {
            smoothScrollBy(i, 0);
        } else {
            scrollBy(i, 0);
        }
    }

    public void smoothScrollBy(int i, int i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.b > 250) {
                int max = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                max = Math.max(0, Math.min(scrollY + i2, max)) - scrollY;
                int max2 = Math.max(0, getChildAt(0).getWidth() - ((getWidth() - getPaddingRight()) - getPaddingLeft()));
                int scrollX = getScrollX();
                this.c.startScroll(scrollX, scrollY, Math.max(0, Math.min(scrollX + i, max2)) - scrollX, max);
                invalidate();
            } else {
                if (!this.c.isFinished()) {
                    this.c.abortAnimation();
                }
                scrollBy(i, i2);
            }
            this.b = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollTo(int i, int i2) {
        smoothScrollBy(i - getScrollX(), i2 - getScrollY());
    }

    protected int computeVerticalScrollRange() {
        return getChildCount() == 0 ? (getHeight() - getPaddingBottom()) - getPaddingTop() : getChildAt(0).getBottom();
    }

    protected int computeHorizontalScrollRange() {
        return getChildCount() == 0 ? (getWidth() - getPaddingLeft()) - getPaddingRight() : getChildAt(0).getRight();
    }

    protected int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    protected int computeHorizontalScrollOffset() {
        return Math.max(0, super.computeHorizontalScrollOffset());
    }

    protected void measureChild(View view, int i, int i2) {
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        view.measure(MeasureSpec.makeMeasureSpec(marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, 0), MeasureSpec.makeMeasureSpec(marginLayoutParams.bottomMargin + marginLayoutParams.topMargin, 0));
    }

    public void computeScroll() {
        if (this.c.computeScrollOffset()) {
            int currX = this.c.getCurrX();
            int currY = this.c.getCurrY();
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                super.scrollTo(c(currX, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth()), c(currY, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight()));
            }
            awakenScrollBars();
            postInvalidate();
        }
    }

    private void c(View view) {
        view.getDrawingRect(this.a);
        offsetDescendantRectToMyCoords(view, this.a);
        int a = a(this.a);
        int b = b(this.a);
        if (b != 0 || a != 0) {
            scrollBy(b, a);
        }
    }

    private boolean a(Rect rect, boolean z) {
        int a = a(rect);
        int b = b(rect);
        boolean z2 = (b == 0 && a == 0) ? false : true;
        if (z2) {
            if (z) {
                scrollBy(b, a);
            } else {
                smoothScrollBy(b, a);
            }
        }
        return z2;
    }

    protected int a(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            i -= verticalFadingEdgeLength;
        }
        if (rect.bottom > i && rect.top > scrollY) {
            if (rect.height() > height) {
                scrollY = (rect.top - scrollY) + 0;
            } else {
                scrollY = (rect.bottom - i) + 0;
            }
            scrollY = Math.min(scrollY, getChildAt(0).getBottom() - i);
        } else if (rect.top >= scrollY || rect.bottom >= i) {
            scrollY = 0;
        } else {
            if (rect.height() > height) {
                scrollY = 0 - (i - rect.bottom);
            } else {
                scrollY = 0 - (scrollY - rect.top);
            }
            scrollY = Math.max(scrollY, -getScrollY());
        }
        return scrollY;
    }

    protected int b(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int width = getWidth();
        int scrollX = getScrollX();
        int i = scrollX + width;
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (rect.left > 0) {
            scrollX += horizontalFadingEdgeLength;
        }
        if (rect.right < getChildAt(0).getWidth()) {
            i -= horizontalFadingEdgeLength;
        }
        if (rect.right > i && rect.left > scrollX) {
            if (rect.width() > width) {
                scrollX = (rect.left - scrollX) + 0;
            } else {
                scrollX = (rect.right - i) + 0;
            }
            scrollX = Math.min(scrollX, getChildAt(0).getRight() - i);
        } else if (rect.left >= scrollX || rect.right >= i) {
            scrollX = 0;
        } else {
            if (rect.width() > width) {
                scrollX = 0 - (i - rect.right);
            } else {
                scrollX = 0 - (scrollX - rect.left);
            }
            scrollX = Math.max(scrollX, -getScrollX());
        }
        return scrollX;
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.d) {
            if (this.g) {
                this.h = view2;
            } else {
                c(view2);
            }
        }
        super.requestChildFocus(view, view2);
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        View findNextFocus;
        if (rect == null) {
            findNextFocus = FocusFinder.getInstance().findNextFocus(this, null, i);
        } else {
            findNextFocus = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        }
        if (findNextFocus == null) {
            return false;
        }
        return findNextFocus.requestFocus(i, rect);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return a(rect, z);
    }

    public void requestLayout() {
        this.g = true;
        super.requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.g = false;
        if (this.h != null && a(this.h, (View) this)) {
            c(this.h);
        }
        this.h = null;
        scrollTo(getScrollX(), getScrollY());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus) {
            if (a(findFocus, 0, i4)) {
                findFocus.getDrawingRect(this.a);
                offsetDescendantRectToMyCoords(findFocus, this.a);
                a(a(this.a));
            }
            if (a(findFocus, getRight() - getLeft())) {
                findFocus.getDrawingRect(this.a);
                offsetDescendantRectToMyCoords(findFocus, this.a);
                b(b(this.a));
            }
        }
    }

    private boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        boolean z = (parent instanceof ViewGroup) && a((View) parent, view2);
        return z;
    }

    public void fling(int i, int i2) {
        if (getChildCount() > 0) {
            int width = (getWidth() - getPaddingRight()) - getPaddingLeft();
            int width2 = getChildAt(0).getWidth();
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            int height2 = getChildAt(0).getHeight();
            this.c.fling(getScrollX(), getScrollY(), i, i2, 0, Math.max(0, width2 - width), 0, Math.max(0, height2 - height));
            invalidate();
        }
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int c = c(i, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth());
            int c2 = c(i2, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight());
            if (c != getScrollX() || c2 != getScrollY()) {
                super.scrollTo(c, c2);
            }
        }
    }

    private int c(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        if (i2 + i > i3) {
            return i3 - i2;
        }
        return i;
    }

    public boolean isFlingEnabled() {
        return this.q;
    }

    public void setFlingEnabled(boolean z) {
        this.q = z;
    }
}
