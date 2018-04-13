package com.tonicartos.widget.stickygridheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.alipay.sdk.util.h;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StickyGridHeadersGridView extends GridView implements OnScrollListener, OnItemClickListener, OnItemLongClickListener, OnItemSelectedListener {
    static final String a = StickyGridHeadersGridView.class.getSimpleName();
    private static final String i = ("Error supporting platform " + VERSION.SDK_INT + ".");
    private OnItemClickListener A;
    private OnItemLongClickListener B;
    private OnItemSelectedListener C;
    private e D;
    private OnScrollListener E;
    private int F;
    private View G;
    private Runnable H;
    private int I;
    private int J;
    public c b;
    public d c;
    protected b d;
    protected boolean e;
    protected int f;
    protected int g;
    boolean h;
    private boolean j;
    private final Rect k;
    private boolean l;
    private boolean m;
    private int n;
    private long o;
    private DataSetObserver p;
    private int q;
    private boolean r;
    private int s;
    private boolean t;
    private float u;
    private int v;
    private boolean w;
    private int x;
    private a y;
    private b z;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new j();
        boolean a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readByte() != (byte) 0;
        }

        public String toString() {
            return "StickyGridHeadersGridView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " areHeadersSticky=" + this.a + h.d;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte((byte) (this.a ? 1 : 0));
        }
    }

    public interface a {
        void a(AdapterView<?> adapterView, View view, long j);
    }

    public interface b {
        boolean a(AdapterView<?> adapterView, View view, long j);
    }

    private class g {
        private int a;
        final /* synthetic */ StickyGridHeadersGridView c;

        private g(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.c = stickyGridHeadersGridView;
        }

        public final void a() {
            this.a = this.c.getWindowAttachCount();
        }

        public final boolean b() {
            return this.c.hasWindowFocus() && this.c.getWindowAttachCount() == this.a;
        }
    }

    private class c extends g implements Runnable {
        final /* synthetic */ StickyGridHeadersGridView a;

        private c(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.a = stickyGridHeadersGridView;
            super();
        }

        public final void run() {
            View a = this.a.a(this.a.f);
            if (a != null) {
                boolean z;
                long a2 = StickyGridHeadersGridView.a(this.a, this.a.f);
                if (!b() || this.a.e) {
                    z = false;
                } else {
                    z = this.a.b(a, a2);
                }
                if (z) {
                    this.a.g = -2;
                    this.a.setPressed(false);
                    a.setPressed(false);
                    return;
                }
                this.a.g = 2;
            }
        }
    }

    final class d implements Runnable {
        final /* synthetic */ StickyGridHeadersGridView a;

        d(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.a = stickyGridHeadersGridView;
        }

        public final void run() {
            if (this.a.g == 0) {
                this.a.g = 1;
                View a = this.a.a(this.a.f);
                if (a != null && !this.a.h) {
                    if (this.a.e) {
                        this.a.g = 2;
                        return;
                    }
                    a.setPressed(true);
                    this.a.setPressed(true);
                    this.a.refreshDrawableState();
                    int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    if (this.a.isLongClickable()) {
                        if (this.a.b == null) {
                            this.a.b = new c();
                        }
                        this.a.b.a();
                        this.a.postDelayed(this.a.b, (long) longPressTimeout);
                        return;
                    }
                    this.a.g = 2;
                }
            }
        }
    }

    private class e extends g implements Runnable {
        int a;
        final /* synthetic */ StickyGridHeadersGridView b;

        private e(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.b = stickyGridHeadersGridView;
            super();
        }

        public final void run() {
            if (!this.b.e && this.b.d != null && this.b.d.getCount() > 0 && this.a != -1 && this.a < this.b.d.getCount() && b()) {
                View a = this.b.a(this.a);
                if (a != null) {
                    this.b.a(a, StickyGridHeadersGridView.a(this.b, this.a));
                }
            }
        }
    }

    class f extends RuntimeException {
        private static final long serialVersionUID = -6512098808936536538L;
        final /* synthetic */ StickyGridHeadersGridView a;

        public f(StickyGridHeadersGridView stickyGridHeadersGridView, Exception exception) {
            this.a = stickyGridHeadersGridView;
            super(StickyGridHeadersGridView.i, exception);
        }
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842865);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = true;
        this.k = new Rect();
        this.o = -1;
        this.p = new g(this);
        this.t = true;
        this.x = 1;
        this.F = 0;
        this.h = false;
        super.setOnScrollListener(this);
        setVerticalFadingEdgeEnabled(false);
        if (!this.w) {
            this.v = -1;
        }
        this.I = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public View a(int i) {
        if (i == -2) {
            return this.G;
        }
        try {
            return (View) getChildAt(i).getTag();
        } catch (Exception e) {
            return null;
        }
    }

    public View getStickiedHeader() {
        return this.G;
    }

    public boolean getStickyHeaderIsTranscluent() {
        return !this.t;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.A.onItemClick(adapterView, view, this.d.c(i).b, j);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return this.B.onItemLongClick(adapterView, view, this.d.c(i).b, j);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.C.onItemSelected(adapterView, view, this.d.c(i).b, j);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        this.C.onNothingSelected(adapterView);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.j = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.j;
        return savedState;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.E != null) {
            this.E.onScroll(absListView, i, i2, i3);
        }
        if (VERSION.SDK_INT >= 8) {
            b(i);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.E != null) {
            this.E.onScrollStateChanged(absListView, i);
        }
        this.F = i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View a;
        View view;
        int action = motionEvent.getAction();
        boolean z = this.h;
        if (this.h) {
            a = a(this.f);
            if (this.f == -2) {
                view = a;
            } else {
                view = getChildAt(this.f);
            }
            if (action == 1 || action == 3) {
                this.h = false;
            }
            if (a != null) {
                a.dispatchTouchEvent(a(motionEvent, this.f));
                a.invalidate();
                a.postDelayed(new h(this, view), (long) ViewConfiguration.getPressedStateDuration());
                invalidate(0, view.getTop(), getWidth(), view.getHeight() + view.getTop());
            }
        }
        switch (action & 255) {
            case 0:
                int i;
                if (this.c == null) {
                    this.c = new d(this);
                }
                postDelayed(this.c, (long) ViewConfiguration.getTapTimeout());
                int y = (int) motionEvent.getY();
                this.u = (float) y;
                float f = (float) y;
                if (this.G == null || f > ((float) this.q)) {
                    y = getFirstVisiblePosition();
                    i = 0;
                    while (y <= getLastVisiblePosition()) {
                        if (getItemIdAtPosition(y) == -1) {
                            View childAt = getChildAt(i);
                            int bottom = childAt.getBottom();
                            int top = childAt.getTop();
                            if (f <= ((float) bottom) && f >= ((float) top)) {
                            }
                        }
                        y += this.x;
                        i += this.x;
                    }
                    i = -1;
                } else {
                    i = -2;
                }
                this.f = i;
                if (!(this.f == -1 || this.F == 2)) {
                    view = a(this.f);
                    if (view != null) {
                        if (view.dispatchTouchEvent(a(motionEvent, this.f))) {
                            this.h = true;
                            view.setPressed(true);
                        }
                        view.invalidate();
                        if (this.f != -2) {
                            view = getChildAt(this.f);
                        }
                        invalidate(0, view.getTop(), getWidth(), view.getHeight() + view.getTop());
                    }
                    this.g = 0;
                    return true;
                }
                break;
            case 1:
                if (this.g == -2) {
                    this.g = -1;
                    return true;
                } else if (!(this.g == -1 || this.f == -1)) {
                    a = a(this.f);
                    if (!(z || a == null)) {
                        if (this.g != 0) {
                            a.setPressed(false);
                        }
                        if (this.D == null) {
                            this.D = new e();
                        }
                        e eVar = this.D;
                        eVar.a = this.f;
                        eVar.a();
                        if (this.g == 0 || this.g == 1) {
                            Handler handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.g == 0 ? this.c : this.b);
                            }
                            if (this.e) {
                                this.g = -1;
                            } else {
                                this.g = 1;
                                a.setPressed(true);
                                setPressed(true);
                                if (this.H != null) {
                                    removeCallbacks(this.H);
                                }
                                this.H = new i(this, a, eVar);
                                postDelayed(this.H, (long) ViewConfiguration.getPressedStateDuration());
                            }
                        } else if (!this.e) {
                            eVar.run();
                        }
                    }
                    this.g = -1;
                    return true;
                }
                break;
            case 2:
                if (this.f != -1 && Math.abs(motionEvent.getY() - this.u) > ((float) this.I)) {
                    this.g = -1;
                    view = a(this.f);
                    if (view != null) {
                        view.setPressed(false);
                        view.invalidate();
                    }
                    Handler handler2 = getHandler();
                    if (handler2 != null) {
                        handler2.removeCallbacks(this.b);
                    }
                    this.f = -1;
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean a(View view, long j) {
        if (this.y == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        this.y.a(this, view, j);
        return true;
    }

    public boolean b(View view, long j) {
        boolean a;
        if (this.z != null) {
            a = this.z.a(this, view, j);
        } else {
            a = false;
        }
        if (a) {
            if (view != null) {
                view.sendAccessibilityEvent(2);
            }
            performHapticFeedback(0);
        }
        return a;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.d == null || this.p == null)) {
            this.d.unregisterDataSetObserver(this.p);
        }
        if (!this.m) {
            this.l = true;
        }
        if (listAdapter instanceof a) {
            listAdapter = (a) listAdapter;
        } else if (listAdapter instanceof d) {
            r3 = new e((d) listAdapter);
        } else {
            r3 = new c(listAdapter);
        }
        this.d = new b(getContext(), this, listAdapter);
        this.d.registerDataSetObserver(this.p);
        c();
        super.setAdapter(this.d);
    }

    public void setAreHeadersSticky(boolean z) {
        if (z != this.j) {
            this.j = z;
            requestLayout();
        }
    }

    public void setClipToPadding(boolean z) {
        super.setClipToPadding(z);
        this.l = z;
        this.m = true;
    }

    public void setColumnWidth(int i) {
        super.setColumnWidth(i);
        this.n = i;
    }

    public void setHeadersIgnorePadding(boolean z) {
        this.r = z;
    }

    public void setHorizontalSpacing(int i) {
        super.setHorizontalSpacing(i);
        this.s = i;
    }

    public void setNumColumns(int i) {
        super.setNumColumns(i);
        this.w = true;
        this.v = i;
        if (i != -1 && this.d != null) {
            this.d.a(i);
        }
    }

    public void setOnHeaderClickListener(a aVar) {
        this.y = aVar;
    }

    public void setOnHeaderLongClickListener(b bVar) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.z = bVar;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.A = onItemClickListener;
        super.setOnItemClickListener(this);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.B = onItemLongClickListener;
        super.setOnItemLongClickListener(this);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.C = onItemSelectedListener;
        super.setOnItemSelectedListener(this);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.E = onScrollListener;
    }

    public void setStickyHeaderIsTranscluent(boolean z) {
        this.t = !z;
    }

    public void setVerticalSpacing(int i) {
        super.setVerticalSpacing(i);
        this.J = i;
    }

    private int getHeaderHeight() {
        if (this.G != null) {
            return this.G.getMeasuredHeight();
        }
        return 0;
    }

    private void b() {
        if (this.G != null) {
            int makeMeasureSpec;
            int makeMeasureSpec2;
            if (this.r) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
            } else {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
            }
            LayoutParams layoutParams = this.G.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            } else {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            }
            this.G.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.G.measure(makeMeasureSpec, makeMeasureSpec2);
            if (this.r) {
                this.G.layout(getLeft(), 0, getRight(), this.G.getMeasuredHeight());
            } else {
                this.G.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.G.getMeasuredHeight());
            }
        }
    }

    private void c() {
        this.q = 0;
        c(null);
        this.o = Long.MIN_VALUE;
    }

    private void b(int i) {
        if (this.d != null && this.d.getCount() != 0 && this.j && getChildAt(0) != null) {
            int i2;
            long b;
            int childCount;
            View view;
            int i3;
            View childAt;
            View view2;
            int i4 = i - this.x;
            if (i4 < 0) {
                i4 = i;
            }
            int i5 = this.x + i;
            if (i5 >= this.d.getCount()) {
                i5 = i;
            }
            if (this.J != 0) {
                if (this.J < 0) {
                    this.d.b(i);
                    if (getChildAt(this.x).getTop() <= 0) {
                        i2 = i5;
                        b = this.d.b(i5);
                    } else {
                        b = this.d.b(i);
                        i2 = i;
                    }
                } else {
                    i5 = getChildAt(0).getTop();
                    if (i5 > 0 && i5 < this.J) {
                        i2 = i4;
                        b = this.d.b(i4);
                    }
                }
                if (this.o != b) {
                    c(this.d.a(i2, this.G, this));
                    b();
                    this.o = b;
                }
                childCount = getChildCount();
                if (childCount != 0) {
                    view = null;
                    i5 = 99999;
                    i3 = 0;
                    while (i3 < childCount) {
                        childAt = super.getChildAt(i3);
                        if (this.l) {
                            i4 = childAt.getTop();
                        } else {
                            i4 = childAt.getTop() - getPaddingTop();
                        }
                        if (i4 >= 0 || this.d.getItemId(getPositionForView(childAt)) != -1 || i4 >= i5) {
                            i4 = i5;
                            view2 = view;
                        } else {
                            view2 = childAt;
                        }
                        i3 = this.x + i3;
                        view = view2;
                        i5 = i4;
                    }
                    i4 = getHeaderHeight();
                    if (view != null) {
                        this.q = i4;
                        if (this.l) {
                            this.q += getPaddingTop();
                            return;
                        }
                        return;
                    } else if (i != 0 && super.getChildAt(0).getTop() > 0 && !this.l) {
                        this.q = 0;
                        return;
                    } else if (this.l) {
                        this.q = Math.min(view.getTop(), i4);
                        if (this.q >= 0) {
                            i4 = this.q;
                        }
                        this.q = i4;
                        return;
                    } else {
                        this.q = Math.min(view.getTop(), getPaddingTop() + i4);
                        this.q = this.q >= getPaddingTop() ? i4 + getPaddingTop() : this.q;
                    }
                }
            }
            b = this.d.b(i);
            i2 = i;
            if (this.o != b) {
                c(this.d.a(i2, this.G, this));
                b();
                this.o = b;
            }
            childCount = getChildCount();
            if (childCount != 0) {
                view = null;
                i5 = 99999;
                i3 = 0;
                while (i3 < childCount) {
                    childAt = super.getChildAt(i3);
                    if (this.l) {
                        i4 = childAt.getTop();
                    } else {
                        i4 = childAt.getTop() - getPaddingTop();
                    }
                    if (i4 >= 0) {
                    }
                    i4 = i5;
                    view2 = view;
                    i3 = this.x + i3;
                    view = view2;
                    i5 = i4;
                }
                i4 = getHeaderHeight();
                if (view != null) {
                    this.q = i4;
                    if (this.l) {
                        this.q += getPaddingTop();
                        return;
                    }
                    return;
                }
                if (i != 0) {
                }
                if (this.l) {
                    this.q = Math.min(view.getTop(), i4);
                    if (this.q >= 0) {
                        i4 = this.q;
                    }
                    this.q = i4;
                    return;
                }
                this.q = Math.min(view.getTop(), getPaddingTop() + i4);
                if (this.q >= getPaddingTop()) {
                }
                this.q = this.q >= getPaddingTop() ? i4 + getPaddingTop() : this.q;
            }
        }
    }

    private void c(View view) {
        b(this.G);
        a(view);
        this.G = view;
    }

    private MotionEvent a(MotionEvent motionEvent, int i) {
        if (i == -2) {
            return motionEvent;
        }
        long downTime = motionEvent.getDownTime();
        long eventTime = motionEvent.getEventTime();
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        int pointerCount2 = motionEvent.getPointerCount();
        int[] iArr = new int[pointerCount2];
        for (int i2 = 0; i2 < pointerCount2; i2++) {
            iArr[i2] = motionEvent.getPointerId(i2);
        }
        int pointerCount3 = motionEvent.getPointerCount();
        PointerCoords[] pointerCoordsArr = new PointerCoords[pointerCount3];
        for (pointerCount2 = 0; pointerCount2 < pointerCount3; pointerCount2++) {
            pointerCoordsArr[pointerCount2] = new PointerCoords();
            motionEvent.getPointerCoords(pointerCount2, pointerCoordsArr[pointerCount2]);
        }
        pointerCount2 = motionEvent.getMetaState();
        float xPrecision = motionEvent.getXPrecision();
        float yPrecision = motionEvent.getYPrecision();
        int deviceId = motionEvent.getDeviceId();
        int edgeFlags = motionEvent.getEdgeFlags();
        int source = motionEvent.getSource();
        int flags = motionEvent.getFlags();
        View childAt = getChildAt(i);
        for (int i3 = 0; i3 < pointerCount; i3++) {
            PointerCoords pointerCoords = pointerCoordsArr[i3];
            pointerCoords.y -= (float) childAt.getTop();
        }
        return MotionEvent.obtain(downTime, eventTime, action, pointerCount, iArr, pointerCoordsArr, pointerCount2, xPrecision, yPrecision, deviceId, edgeFlags, source, flags);
    }

    protected void dispatchDraw(Canvas canvas) {
        Object obj;
        if (VERSION.SDK_INT < 8) {
            b(getFirstVisiblePosition());
        }
        if (this.G != null && this.j && this.G.getVisibility() == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        int headerHeight = getHeaderHeight();
        int i = this.q - headerHeight;
        if (obj != null && this.t) {
            if (this.r) {
                this.k.left = 0;
                this.k.right = getWidth();
            } else {
                this.k.left = getPaddingLeft();
                this.k.right = getWidth() - getPaddingRight();
            }
            this.k.top = this.q;
            this.k.bottom = getHeight();
            canvas.save();
            canvas.clipRect(this.k);
        }
        super.dispatchDraw(canvas);
        List arrayList = new ArrayList();
        int i2 = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            if (getItemIdAtPosition(firstVisiblePosition) == -1) {
                arrayList.add(Integer.valueOf(i2));
            }
            firstVisiblePosition += this.x;
            i2 += this.x;
        }
        int i3 = 0;
        while (i3 < arrayList.size()) {
            View childAt = getChildAt(((Integer) arrayList.get(i3)).intValue());
            try {
                View view = (View) childAt.getTag();
                Object obj2 = (((long) ((b) childAt).getHeaderId()) == this.o && childAt.getTop() < 0 && this.j) ? 1 : null;
                if (view.getVisibility() == 0 && obj2 == null) {
                    if (this.r) {
                        i2 = MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
                    } else {
                        i2 = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
                    }
                    int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                    view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                    view.measure(i2, makeMeasureSpec);
                    if (this.r) {
                        view.layout(getLeft(), 0, getRight(), childAt.getHeight());
                    } else {
                        view.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), childAt.getHeight());
                    }
                    if (this.r) {
                        this.k.left = 0;
                        this.k.right = getWidth();
                    } else {
                        this.k.left = getPaddingLeft();
                        this.k.right = getWidth() - getPaddingRight();
                    }
                    this.k.bottom = childAt.getBottom();
                    this.k.top = childAt.getTop();
                    canvas.save();
                    canvas.clipRect(this.k);
                    if (this.r) {
                        canvas.translate(0.0f, (float) childAt.getTop());
                    } else {
                        canvas.translate((float) getPaddingLeft(), (float) childAt.getTop());
                    }
                    view.draw(canvas);
                    canvas.restore();
                }
                i3++;
            } catch (Exception e) {
                return;
            }
        }
        if (obj != null && this.t) {
            canvas.restore();
        } else if (obj == null) {
            return;
        }
        if (this.r) {
            firstVisiblePosition = getWidth();
        } else {
            firstVisiblePosition = (getWidth() - getPaddingLeft()) - getPaddingRight();
        }
        if (this.G.getWidth() != firstVisiblePosition) {
            if (this.r) {
                firstVisiblePosition = MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
            } else {
                firstVisiblePosition = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
            }
            i2 = MeasureSpec.makeMeasureSpec(0, 0);
            this.G.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.G.measure(firstVisiblePosition, i2);
            if (this.r) {
                this.G.layout(getLeft(), 0, getRight(), this.G.getHeight());
            } else {
                this.G.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.G.getHeight());
            }
        }
        if (this.r) {
            this.k.left = 0;
            this.k.right = getWidth();
        } else {
            this.k.left = getPaddingLeft();
            this.k.right = getWidth() - getPaddingRight();
        }
        this.k.bottom = i + headerHeight;
        if (this.l) {
            this.k.top = getPaddingTop();
        } else {
            this.k.top = 0;
        }
        canvas.save();
        canvas.clipRect(this.k);
        if (this.r) {
            canvas.translate(0.0f, (float) i);
        } else {
            canvas.translate((float) getPaddingLeft(), (float) i);
        }
        if (this.q != headerHeight) {
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (this.q * 255) / headerHeight, 31);
        }
        this.G.draw(canvas);
        if (this.q != headerHeight) {
            canvas.restore();
        }
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1;
        if (this.v == -1) {
            if (this.n > 0) {
                int max = Math.max((MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight(), 0);
                int i4 = max / this.n;
                if (i4 > 0) {
                    while (i4 != 1 && (this.n * i4) + ((i4 - 1) * this.s) > max) {
                        i4--;
                    }
                    i3 = i4;
                }
            } else {
                i3 = 2;
            }
            this.x = i3;
        } else {
            this.x = this.v;
        }
        if (this.d != null) {
            this.d.a(this.x);
        }
        b();
        super.onMeasure(i, i2);
    }

    final void a(View view) {
        if (view != null) {
            try {
                View.class.getDeclaredField("mAttachInfo").setAccessible(true);
                Method declaredMethod = View.class.getDeclaredMethod("dispatchAttachedToWindow", new Class[]{Class.forName("android.view.View$AttachInfo"), Integer.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[]{r0.get(this), Integer.valueOf(8)});
            } catch (Exception e) {
                throw new f(this, e);
            } catch (Exception e2) {
                throw new f(this, e2);
            } catch (Exception e22) {
                throw new f(this, e22);
            } catch (Exception e222) {
                throw new f(this, e222);
            } catch (Exception e2222) {
                throw new f(this, e2222);
            } catch (Exception e22222) {
                throw new f(this, e22222);
            }
        }
    }

    final void b(View view) {
        if (view != null) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("dispatchDetachedFromWindow", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[0]);
            } catch (Exception e) {
                throw new f(this, e);
            } catch (Exception e2) {
                throw new f(this, e2);
            } catch (Exception e22) {
                throw new f(this, e22);
            } catch (Exception e222) {
                throw new f(this, e222);
            }
        }
    }

    static /* synthetic */ long a(StickyGridHeadersGridView stickyGridHeadersGridView, int i) {
        if (i == -2) {
            return stickyGridHeadersGridView.o;
        }
        return stickyGridHeadersGridView.d.b(stickyGridHeadersGridView.getFirstVisiblePosition() + i);
    }
}
