package qsbk.app.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import java.lang.reflect.Field;
import java.util.List;

public class OverScrollView extends FrameLayout implements OnTouchListener {
    private static final String i = OverScrollView.class.getSimpleName();
    private boolean A;
    private LoadMoreListener B;
    private int C;
    private int D;
    private Runnable E;
    protected Context a;
    protected View b;
    Field c;
    Field d;
    boolean e;
    int f;
    boolean g;
    DisplayMetrics h;
    private final Rect j;
    private long k;
    private Scroller l;
    private Runnable m;
    private boolean n;
    private float o;
    private boolean p;
    private View q;
    private boolean r;
    private VelocityTracker s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private int y;
    private OnScrollListener z;

    public interface LoadMoreListener {
        void onLoadMore();
    }

    public interface OnScrollListener {
        void onScroll(OverScrollView overScrollView, int i, boolean z, boolean z2);

        void onScrollChanged(OverScrollView overScrollView, int i, int i2, int i3, int i4);
    }

    public OverScrollView(Context context) {
        this(context, null);
    }

    public OverScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new Rect();
        this.g = false;
        this.p = true;
        this.q = null;
        this.r = false;
        this.u = true;
        this.y = -1;
        this.A = true;
        this.C = 150;
        this.D = 0;
        this.E = new cp(this);
        this.a = context;
        b();
        setFillViewport(true);
        a();
    }

    private void a() {
        this.h = this.a.getResources().getDisplayMetrics();
        this.l = new Scroller(getContext(), new OvershootInterpolator(0.75f));
        this.m = new cq(this);
        this.f = getPaddingTop();
        try {
            this.d = View.class.getDeclaredField("mScrollX");
            this.c = View.class.getDeclaredField("mScrollY");
        } catch (Exception e) {
            this.e = true;
        }
    }

    private void b(int i) {
        if (this.c != null) {
            try {
                this.c.setInt(this, i);
            } catch (Exception e) {
            }
        }
    }

    private void c(int i) {
        if (this.d != null) {
            try {
                this.d.setInt(this, i);
            } catch (Exception e) {
            }
        }
    }

    public OnScrollListener getOnScrollListener() {
        return this.z;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.z = onScrollListener;
    }

    public boolean isOverScrollEnable() {
        return this.A;
    }

    public void setOverScrollEnable(boolean z) {
        this.A = z;
    }

    public int getLoadMoreThreshold() {
        return this.C;
    }

    public void setLoadMoreThreshold(int i) {
        this.C = i;
    }

    public void setOnLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.B = loadMoreListener;
    }

    public LoadMoreListener getLoadMoreListener() {
        return this.B;
    }

    public void initChildPointer() {
        this.b = getChildAt(0);
        this.b.setPadding(0, 0, 0, 1500);
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

    public int getMaxScrollAmount() {
        return (int) (0.5f * ((float) (getBottom() - getTop())));
    }

    private void b() {
        this.l = new Scroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.a);
        this.v = viewConfiguration.getScaledTouchSlop();
        this.w = viewConfiguration.getScaledMinimumFlingVelocity();
        this.x = viewConfiguration.getScaledMaximumFlingVelocity();
        setOnTouchListener(this);
        post(new cr(this));
    }

    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
        initChildPointer();
    }

    public void addView(View view, int i) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i);
        initChildPointer();
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, layoutParams);
        initChildPointer();
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i, layoutParams);
    }

    private boolean c() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (getHeight() < (childAt.getHeight() + getPaddingTop()) + getPaddingBottom()) {
            return true;
        }
        return false;
    }

    public boolean isFillViewport() {
        return this.t;
    }

    public void setFillViewport(boolean z) {
        if (z != this.t) {
            this.t = z;
            requestLayout();
        }
    }

    public boolean isSmoothScrollingEnabled() {
        return this.u;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.u = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.t && MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            int measuredHeight = getMeasuredHeight();
            if (childAt.getMeasuredHeight() < measuredHeight) {
                childAt.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), ((FrameLayout.LayoutParams) childAt.getLayoutParams()).width), MeasureSpec.makeMeasureSpec((measuredHeight - getPaddingTop()) - getPaddingBottom(), 1073741824));
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int i = 33;
        this.j.setEmpty();
        if (c()) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            switch (keyEvent.getKeyCode()) {
                case 19:
                    if (keyEvent.isAltPressed()) {
                        return fullScroll(33);
                    }
                    return arrowScroll(33);
                case 20:
                    if (keyEvent.isAltPressed()) {
                        return fullScroll(130);
                    }
                    return arrowScroll(130);
                case 62:
                    if (!keyEvent.isShiftPressed()) {
                        i = 130;
                    }
                    pageScroll(i);
                    return false;
                default:
                    return false;
            }
        } else if (!isFocused() || keyEvent.getKeyCode() == 4) {
            return false;
        } else {
            boolean z;
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            findFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            if (findFocus == null || findFocus == this || !findFocus.requestFocus(130)) {
                z = false;
            } else {
                z = true;
            }
            return z;
        }
    }

    public boolean inChild(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        if (i2 < childAt.getTop() - scrollY || i2 >= childAt.getBottom() - scrollY || i < childAt.getLeft() || i >= childAt.getRight()) {
            return false;
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        int action = motionEvent.getAction();
        if (action == 2 && this.r) {
            return true;
        }
        switch (action & 255) {
            case 0:
                float y = motionEvent.getY();
                if (!inChild((int) motionEvent.getX(), (int) y)) {
                    this.r = false;
                    break;
                }
                this.o = y;
                this.y = motionEvent.getPointerId(0);
                if (this.l.isFinished()) {
                    z = false;
                }
                this.r = z;
                break;
            case 1:
            case 3:
                this.r = false;
                this.y = -1;
                break;
            case 2:
                int i = this.y;
                if (i != -1) {
                    float y2 = motionEvent.getY(motionEvent.findPointerIndex(i));
                    if (((int) Math.abs(y2 - this.o)) > this.v) {
                        this.r = true;
                        this.o = y2;
                        break;
                    }
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return this.r;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.s == null) {
            this.s = VelocityTracker.obtain();
        }
        this.s.addMovement(motionEvent);
        float y;
        switch (motionEvent.getAction() & 255) {
            case 0:
                y = motionEvent.getY();
                boolean inChild = inChild((int) motionEvent.getX(), (int) y);
                this.r = inChild;
                if (inChild) {
                    if (!this.l.isFinished()) {
                        this.l.abortAnimation();
                    }
                    this.o = y;
                    this.y = motionEvent.getPointerId(0);
                    break;
                }
                return false;
            case 1:
                if (this.r) {
                    VelocityTracker velocityTracker = this.s;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.x);
                    int yVelocity = (int) velocityTracker.getYVelocity(this.y);
                    if (getChildCount() > 0 && Math.abs(yVelocity) > this.w) {
                        fling(-yVelocity);
                    }
                    this.y = -1;
                    this.r = false;
                    if (this.s != null) {
                        this.s.recycle();
                        this.s = null;
                        break;
                    }
                }
                break;
            case 2:
                if (this.r) {
                    y = motionEvent.getY(motionEvent.findPointerIndex(this.y));
                    int i = (int) (this.o - y);
                    this.o = y;
                    boolean isTopOverScrolled = isTopOverScrolled();
                    boolean isBottomOverScrolled = isBottomOverScrolled();
                    if (!isTopOverScrolled && !isBottomOverScrolled) {
                        scrollBy(0, i);
                        break;
                    }
                    if (this.A) {
                        scrollBy(0, i / 2);
                    }
                    if (this.z != null) {
                        this.z.onScroll(this, i, isTopOverScrolled, isBottomOverScrolled);
                        break;
                    }
                }
                break;
            case 3:
                if (this.r && getChildCount() > 0) {
                    this.y = -1;
                    this.r = false;
                    if (this.s != null) {
                        this.s.recycle();
                        this.s = null;
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

    public void scrollBy(int i, int i2) {
        super.scrollBy(i, i2);
    }

    public boolean isTopOverScrolled() {
        return getScrollY() < this.b.getPaddingTop();
    }

    public boolean isBottomOverScrolled() {
        return getScrollY() > (this.b.getBottom() - this.b.getPaddingBottom()) - getHeight();
    }

    public boolean isOverScrolled() {
        return isTopOverScrolled() || isBottomOverScrolled();
    }

    private void a(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.y) {
            action = action == 0 ? 1 : 0;
            this.o = motionEvent.getY(action);
            this.y = motionEvent.getPointerId(action);
            if (this.s != null) {
                this.s.clear();
            }
        }
    }

    private View a(boolean z, int i, View view) {
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength() / 2;
        int i2 = i + verticalFadingEdgeLength;
        verticalFadingEdgeLength = (getHeight() + i) - verticalFadingEdgeLength;
        return (view == null || view.getTop() >= verticalFadingEdgeLength || view.getBottom() <= i2) ? a(z, i2, verticalFadingEdgeLength) : view;
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

    public boolean pageScroll(int i) {
        int i2 = i == 130 ? 1 : 0;
        int height = getHeight();
        if (i2 != 0) {
            this.j.top = getScrollY() + height;
            i2 = getChildCount();
            if (i2 > 0) {
                View childAt = getChildAt(i2 - 1);
                if (this.j.top + height > childAt.getBottom()) {
                    this.j.top = childAt.getBottom() - height;
                }
            }
        } else {
            this.j.top = getScrollY() - height;
            if (this.j.top < 0) {
                this.j.top = 0;
            }
        }
        this.j.bottom = this.j.top + height;
        return a(i, this.j.top, this.j.bottom);
    }

    public boolean fullScroll(int i) {
        int i2 = i == 130 ? 1 : 0;
        int height = getHeight();
        this.j.top = 0;
        this.j.bottom = height;
        if (i2 != 0) {
            i2 = getChildCount();
            if (i2 > 0) {
                this.j.bottom = getChildAt(i2 - 1).getBottom();
                this.j.top = this.j.bottom - height;
            }
        }
        return a(i, this.j.top, this.j.bottom);
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
            d(z ? i2 - scrollY : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (a != findFocus() && a.requestFocus(i)) {
            this.n = true;
            this.n = false;
        }
        return z;
    }

    public boolean arrowScroll(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !a(findNextFocus, maxScrollAmount, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                int bottom = getChildAt(0).getBottom();
                int scrollY = getScrollY() + getHeight();
                if (bottom - scrollY < maxScrollAmount) {
                    maxScrollAmount = bottom - scrollY;
                }
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            d(maxScrollAmount);
        } else {
            findNextFocus.getDrawingRect(this.j);
            offsetDescendantRectToMyCoords(findNextFocus, this.j);
            d(a(this.j));
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

    private boolean a(View view) {
        return !a(view, 0, getHeight());
    }

    private boolean a(View view, int i, int i2) {
        view.getDrawingRect(this.j);
        offsetDescendantRectToMyCoords(view, this.j);
        return this.j.bottom + i >= getScrollY() && this.j.top - i <= getScrollY() + i2;
    }

    private void d(int i) {
        if (i == 0) {
            return;
        }
        if (this.u) {
            smoothScrollBy(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    public final void smoothScrollBy(int i, int i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.k > 250) {
                int max = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                this.l.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(scrollY + i2, max)) - scrollY);
                invalidate();
            } else {
                if (!this.l.isFinished()) {
                    this.l.abortAnimation();
                }
                scrollBy(i, i2);
            }
            this.k = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollToTop() {
        smoothScrollTo(0, this.b.getPaddingTop());
    }

    public final void smoothScrollToBottom() {
        smoothScrollTo(0, (this.b.getHeight() - this.b.getPaddingTop()) - getHeight());
    }

    public final void smoothScrollTo(int i, int i2) {
        smoothScrollBy(i - getScrollX(), i2 - getScrollY());
    }

    protected int computeVerticalScrollRange() {
        return getChildCount() == 0 ? (getHeight() - getPaddingBottom()) - getPaddingTop() : getChildAt(0).getBottom();
    }

    protected int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    protected void measureChild(View view, int i, int i2) {
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, (((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin) + i2, marginLayoutParams.width), MeasureSpec.makeMeasureSpec(marginLayoutParams.bottomMargin + marginLayoutParams.topMargin, 0));
    }

    public void computeScroll() {
        if (this.e) {
            super.computeScroll();
        } else if (this.l.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.l.getCurrX();
            int currY = this.l.getCurrY();
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                currX = b(currX, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth());
                currY = b(currY, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight());
                if (!(currX == scrollX && currY == scrollY)) {
                    c(currX);
                    b(currY);
                    onScrollChanged(currX, currY, scrollX, scrollY);
                }
            }
            awakenScrollBars();
            postInvalidate();
        }
    }

    private void b(View view) {
        view.getDrawingRect(this.j);
        offsetDescendantRectToMyCoords(view, this.j);
        int a = a(this.j);
        if (a != 0) {
            scrollBy(0, a);
        }
    }

    private boolean a(Rect rect, boolean z) {
        int a = a(rect);
        boolean z2 = a != 0;
        if (z2) {
            if (z) {
                scrollBy(0, a);
            } else {
                smoothScrollBy(0, a);
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

    public void requestChildFocus(View view, View view2) {
        if (!this.n) {
            if (this.p) {
                this.q = view2;
            } else {
                b(view2);
            }
        }
        super.requestChildFocus(view, view2);
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        View findNextFocus;
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        if (rect == null) {
            findNextFocus = FocusFinder.getInstance().findNextFocus(this, null, i);
        } else {
            findNextFocus = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        }
        if (findNextFocus == null || a(findNextFocus)) {
            return false;
        }
        return findNextFocus.requestFocus(i, rect);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return a(rect, z);
    }

    public void requestLayout() {
        this.p = true;
        super.requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.p = false;
        if (this.q != null && a(this.q, (View) this)) {
            b(this.q);
        }
        this.q = null;
        scrollTo(getScrollX(), getScrollY());
        post(this.E);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && a(findFocus, 0, i4)) {
            findFocus.getDrawingRect(this.j);
            offsetDescendantRectToMyCoords(findFocus, this.j);
            d(a(this.j));
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        int height = getHeight();
        int paddingTop = this.b.getPaddingTop();
        int height2 = this.b.getHeight() - this.b.getPaddingBottom();
        if (this.z != null) {
            this.z.onScrollChanged(this, i, i2, i3, i4);
        }
        if (!this.g || (i2 >= paddingTop && i2 <= height2 - height)) {
            super.onScrollChanged(i, i2, i3, i4);
            return;
        }
        if (i2 < paddingTop) {
            this.l.startScroll(0, i2, 0, paddingTop - i2, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        } else if (i2 > height2 - height) {
            this.l.startScroll(0, i2, 0, (height2 - height) - i2, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        }
        post(this.m);
        this.g = false;
    }

    private boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        boolean z = (parent instanceof ViewGroup) && a((View) parent, view2);
        return z;
    }

    public void fling(int i) {
        if (getChildCount() > 0) {
            this.l.fling(getScrollX(), getScrollY(), 0, i, 0, 0, 0, Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop())));
            boolean z = i > 0;
            View a = a(z, this.l.getFinalY(), findFocus());
            if (a == null) {
                a = this;
            }
            if (a != findFocus()) {
                if (a.requestFocus(z ? 130 : 33)) {
                    this.n = true;
                    this.n = false;
                }
            }
            invalidate();
        }
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int b = b(i, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth());
            int b2 = b(i2, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight());
            if (b != getScrollX() || b2 != getScrollY()) {
                super.scrollTo(b, b2);
            }
        }
    }

    private int b(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        if (i2 + i > i3) {
            return i3 - i2;
        }
        return i;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.l.forceFinished(true);
        removeCallbacks(this.m);
        if (motionEvent.getAction() == 1) {
            this.D = 0;
            return d();
        } else if (motionEvent.getAction() != 3) {
            return false;
        } else {
            this.D = 0;
            return d();
        }
    }

    private boolean d() {
        int height = getHeight();
        int paddingTop = this.b.getPaddingTop();
        int height2 = this.b.getHeight() - this.b.getPaddingBottom();
        int scrollY = getScrollY();
        if (scrollY < paddingTop) {
            a(scrollY);
            height2 = paddingTop - scrollY;
        } else if (scrollY + height > height2) {
            if ((this.b.getHeight() - this.b.getPaddingTop()) - this.b.getPaddingBottom() < height) {
                height = paddingTop - scrollY;
            } else {
                height = (height2 - height) - scrollY;
            }
            height2 = height + a(scrollY);
        } else {
            this.g = true;
            return false;
        }
        this.D = height2;
        if (height2 != 0) {
            this.l.startScroll(0, scrollY, 0, height2, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            post(this.m);
        }
        this.f = scrollY;
        return true;
    }

    protected int a(int i) {
        return 0;
    }
}
