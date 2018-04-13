package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.recyclerview.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.alipay.sdk.util.h;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements NestedScrollingChild2, ScrollingView {
    static final Interpolator H = new bo();
    public static final int HORIZONTAL = 0;
    private static final int[] I = new int[]{16843830};
    public static final int INVALID_TYPE = -1;
    private static final int[] J = new int[]{16842987};
    private static final boolean K;
    private static final boolean L;
    private static final boolean M;
    private static final Class<?>[] N = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    public static final int VERTICAL = 1;
    static final boolean a;
    static final boolean b;
    static final boolean c;
    a A;
    final State B;
    boolean C;
    boolean D;
    boolean E;
    RecyclerViewAccessibilityDelegate F;
    @VisibleForTesting
    final List<ViewHolder> G;
    private final c O;
    private SavedState P;
    private final Rect Q;
    private final ArrayList<OnItemTouchListener> R;
    private OnItemTouchListener S;
    private int T;
    private boolean U;
    private int V;
    private final AccessibilityManager W;
    private NestedScrollingChildHelper aA;
    private final int[] aB;
    private final int[] aC;
    private final int[] aD;
    private Runnable aE;
    private final b aF;
    private List<OnChildAttachStateChangeListener> aa;
    private int ab;
    private int ac;
    private EdgeEffect ad;
    private EdgeEffect ae;
    private EdgeEffect af;
    private EdgeEffect ag;
    private int ah;
    private int ai;
    private VelocityTracker aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private int ao;
    private OnFlingListener ap;
    private final int aq;
    private final int ar;
    private float as;
    private float at;
    private boolean au;
    private OnScrollListener av;
    private List<OnScrollListener> aw;
    private a ax;
    private ChildDrawingOrderCallback ay;
    private final int[] az;
    final Recycler d;
    o e;
    ab f;
    final dg g;
    boolean h;
    final Runnable i;
    final Rect j;
    final RectF k;
    Adapter l;
    @VisibleForTesting
    LayoutManager m;
    RecyclerListener n;
    final ArrayList<ItemDecoration> o;
    boolean p;
    boolean q;
    boolean r;
    @VisibleForTesting
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    ItemAnimator x;
    final d y;
    ao z;

    public static abstract class ViewHolder {
        private static final List<Object> n = Collections.EMPTY_LIST;
        WeakReference<RecyclerView> a;
        int b = -1;
        int c = -1;
        long d = -1;
        int e = -1;
        int f = -1;
        ViewHolder g = null;
        ViewHolder h = null;
        List<Object> i = null;
        public final View itemView;
        List<Object> j = null;
        @VisibleForTesting
        int k = -1;
        RecyclerView l;
        private int m;
        private int o = 0;
        private Recycler p = null;
        private boolean q = false;
        private int r = 0;

        public ViewHolder(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        void a(int i, int i2, boolean z) {
            b(8);
            a(i2, z);
            this.b = i;
        }

        void a(int i, boolean z) {
            if (this.c == -1) {
                this.c = this.b;
            }
            if (this.f == -1) {
                this.f = this.b;
            }
            if (z) {
                this.f += i;
            }
            this.b += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).e = true;
            }
        }

        void a() {
            this.c = -1;
            this.f = -1;
        }

        void b() {
            if (this.c == -1) {
                this.c = this.b;
            }
        }

        boolean c() {
            return (this.m & 128) != 0;
        }

        @Deprecated
        public final int getPosition() {
            return this.f == -1 ? this.b : this.f;
        }

        public final int getLayoutPosition() {
            return this.f == -1 ? this.b : this.f;
        }

        public final int getAdapterPosition() {
            if (this.l == null) {
                return -1;
            }
            return this.l.d(this);
        }

        public final int getOldPosition() {
            return this.c;
        }

        public final long getItemId() {
            return this.d;
        }

        public final int getItemViewType() {
            return this.e;
        }

        boolean d() {
            return this.p != null;
        }

        void e() {
            this.p.c(this);
        }

        boolean f() {
            return (this.m & 32) != 0;
        }

        void g() {
            this.m &= -33;
        }

        void h() {
            this.m &= -257;
        }

        void i() {
            this.m &= -129;
        }

        void a(Recycler recycler, boolean z) {
            this.p = recycler;
            this.q = z;
        }

        boolean j() {
            return (this.m & 4) != 0;
        }

        boolean k() {
            return (this.m & 2) != 0;
        }

        boolean l() {
            return (this.m & 1) != 0;
        }

        boolean m() {
            return (this.m & 8) != 0;
        }

        boolean a(int i) {
            return (this.m & i) != 0;
        }

        boolean n() {
            return (this.m & 256) != 0;
        }

        boolean o() {
            return (this.m & 512) != 0 || j();
        }

        void a(int i, int i2) {
            this.m = (this.m & (i2 ^ -1)) | (i & i2);
        }

        void b(int i) {
            this.m |= i;
        }

        void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((this.m & 1024) == 0) {
                t();
                this.i.add(obj);
            }
        }

        private void t() {
            if (this.i == null) {
                this.i = new ArrayList();
                this.j = Collections.unmodifiableList(this.i);
            }
        }

        void p() {
            if (this.i != null) {
                this.i.clear();
            }
            this.m &= -1025;
        }

        List<Object> q() {
            if ((this.m & 1024) != 0) {
                return n;
            }
            if (this.i == null || this.i.size() == 0) {
                return n;
            }
            return this.j;
        }

        void r() {
            this.m = 0;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.f = -1;
            this.o = 0;
            this.g = null;
            this.h = null;
            p();
            this.r = 0;
            this.k = -1;
            RecyclerView.c(this);
        }

        private void a(RecyclerView recyclerView) {
            this.r = ViewCompat.getImportantForAccessibility(this.itemView);
            recyclerView.a(this, 4);
        }

        private void b(RecyclerView recyclerView) {
            recyclerView.a(this, this.r);
            this.r = 0;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.b + " id=" + this.d + ", oldPos=" + this.c + ", pLpos:" + this.f);
            if (d()) {
                stringBuilder.append(" scrap ").append(this.q ? "[changeScrap]" : "[attachedScrap]");
            }
            if (j()) {
                stringBuilder.append(" invalid");
            }
            if (!l()) {
                stringBuilder.append(" unbound");
            }
            if (k()) {
                stringBuilder.append(" update");
            }
            if (m()) {
                stringBuilder.append(" removed");
            }
            if (c()) {
                stringBuilder.append(" ignored");
            }
            if (n()) {
                stringBuilder.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                stringBuilder.append(" not recyclable(" + this.o + ")");
            }
            if (o()) {
                stringBuilder.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                stringBuilder.append(" no parent");
            }
            stringBuilder.append(h.d);
            return stringBuilder.toString();
        }

        public final void setIsRecyclable(boolean z) {
            this.o = z ? this.o - 1 : this.o + 1;
            if (this.o < 0) {
                this.o = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && this.o == 1) {
                this.m |= 16;
            } else if (z && this.o == 0) {
                this.m &= -17;
            }
        }

        public final boolean isRecyclable() {
            return (this.m & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        private boolean u() {
            return (this.m & 16) != 0;
        }

        private boolean v() {
            return (this.m & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        boolean s() {
            return (this.m & 2) != 0;
        }
    }

    public static abstract class Adapter<VH extends ViewHolder> {
        private final a a = new a();
        private boolean b = false;

        public abstract int getItemCount();

        public abstract void onBindViewHolder(VH vh, int i);

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        public void onBindViewHolder(VH vh, int i, List<Object> list) {
            onBindViewHolder(vh, i);
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i) {
            TraceCompat.beginSection("RV CreateView");
            VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
            onCreateViewHolder.e = i;
            TraceCompat.endSection();
            return onCreateViewHolder;
        }

        public final void bindViewHolder(VH vh, int i) {
            vh.b = i;
            if (hasStableIds()) {
                vh.d = getItemId(i);
            }
            vh.a(1, 519);
            TraceCompat.beginSection("RV OnBindView");
            onBindViewHolder(vh, i, vh.q());
            vh.p();
            android.view.ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).e = true;
            }
            TraceCompat.endSection();
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public void setHasStableIds(boolean z) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.b = z;
        }

        public long getItemId(int i) {
            return -1;
        }

        public final boolean hasStableIds() {
            return this.b;
        }

        public void onViewRecycled(VH vh) {
        }

        public boolean onFailedToRecycleView(VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public final boolean hasObservers() {
            return this.a.hasObservers();
        }

        public void registerAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
            this.a.registerObserver(adapterDataObserver);
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
            this.a.unregisterObserver(adapterDataObserver);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public final void notifyDataSetChanged() {
            this.a.notifyChanged();
        }

        public final void notifyItemChanged(int i) {
            this.a.notifyItemRangeChanged(i, 1);
        }

        public final void notifyItemChanged(int i, Object obj) {
            this.a.notifyItemRangeChanged(i, 1, obj);
        }

        public final void notifyItemRangeChanged(int i, int i2) {
            this.a.notifyItemRangeChanged(i, i2);
        }

        public final void notifyItemRangeChanged(int i, int i2, Object obj) {
            this.a.notifyItemRangeChanged(i, i2, obj);
        }

        public final void notifyItemInserted(int i) {
            this.a.notifyItemRangeInserted(i, 1);
        }

        public final void notifyItemMoved(int i, int i2) {
            this.a.notifyItemMoved(i, i2);
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            this.a.notifyItemRangeInserted(i, i2);
        }

        public final void notifyItemRemoved(int i) {
            this.a.notifyItemRangeRemoved(i, 1);
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            this.a.notifyItemRangeRemoved(i, i2);
        }
    }

    public static abstract class ItemAnimator {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private a a = null;
        private ArrayList<ItemAnimatorFinishedListener> b = new ArrayList();
        private long c = 120;
        private long d = 120;
        private long e = 250;
        private long f = 250;

        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }

        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        public static class ItemHolderInfo {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;

            public ItemHolderInfo setFrom(ViewHolder viewHolder) {
                return setFrom(viewHolder, 0);
            }

            public ItemHolderInfo setFrom(ViewHolder viewHolder, int i) {
                View view = viewHolder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }

        interface a {
            void onAnimationFinished(ViewHolder viewHolder);
        }

        public abstract boolean animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract void endAnimation(ViewHolder viewHolder);

        public abstract void endAnimations();

        public abstract boolean isRunning();

        public abstract void runPendingAnimations();

        public long getMoveDuration() {
            return this.e;
        }

        public void setMoveDuration(long j) {
            this.e = j;
        }

        public long getAddDuration() {
            return this.c;
        }

        public void setAddDuration(long j) {
            this.c = j;
        }

        public long getRemoveDuration() {
            return this.d;
        }

        public void setRemoveDuration(long j) {
            this.d = j;
        }

        public long getChangeDuration() {
            return this.f;
        }

        public void setChangeDuration(long j) {
            this.f = j;
        }

        void a(a aVar) {
            this.a = aVar;
        }

        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder, int i, @NonNull List<Object> list) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        static int b(ViewHolder viewHolder) {
            int d = viewHolder.m & 14;
            if (viewHolder.j()) {
                return 4;
            }
            if ((d & 4) != 0) {
                return d;
            }
            int oldPosition = viewHolder.getOldPosition();
            int adapterPosition = viewHolder.getAdapterPosition();
            if (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) {
                return d;
            }
            return d | 2048;
        }

        public final void dispatchAnimationFinished(ViewHolder viewHolder) {
            onAnimationFinished(viewHolder);
            if (this.a != null) {
                this.a.onAnimationFinished(viewHolder);
            }
        }

        public void onAnimationFinished(ViewHolder viewHolder) {
        }

        public final void dispatchAnimationStarted(ViewHolder viewHolder) {
            onAnimationStarted(viewHolder);
        }

        public void onAnimationStarted(ViewHolder viewHolder) {
        }

        public final boolean isRunning(ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean isRunning = isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (isRunning) {
                    this.b.add(itemAnimatorFinishedListener);
                } else {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                }
            }
            return isRunning;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder, @NonNull List<Object> list) {
            return canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationsFinished() {
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((ItemAnimatorFinishedListener) this.b.get(i)).onAnimationsFinished();
            }
            this.b.clear();
        }

        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }
    }

    public static abstract class ItemDecoration {
        public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
            onDraw(canvas, recyclerView);
        }

        @Deprecated
        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
            onDrawOver(canvas, recyclerView);
        }

        @Deprecated
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        }

        @Deprecated
        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        ViewHolder c;
        final Rect d = new Rect();
        boolean e = true;
        boolean f = false;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public boolean viewNeedsUpdate() {
            return this.c.k();
        }

        public boolean isViewInvalid() {
            return this.c.j();
        }

        public boolean isItemRemoved() {
            return this.c.m();
        }

        public boolean isItemChanged() {
            return this.c.s();
        }

        @Deprecated
        public int getViewPosition() {
            return this.c.getPosition();
        }

        public int getViewLayoutPosition() {
            return this.c.getLayoutPosition();
        }

        public int getViewAdapterPosition() {
            return this.c.getAdapterPosition();
        }
    }

    public static abstract class LayoutManager {
        private final b a = new bs(this);
        private final b b = new bt(this);
        private boolean c = true;
        private boolean d = true;
        private int e;
        private int f;
        private int g;
        private int h;
        ab p;
        RecyclerView q;
        ViewBoundsCheck r = new ViewBoundsCheck(this.a);
        ViewBoundsCheck s = new ViewBoundsCheck(this.b);
        @Nullable
        SmoothScroller t;
        boolean u = false;
        boolean v = false;
        boolean w = false;
        int x;
        boolean y;

        public interface LayoutPrefetchRegistry {
            void addPosition(int i, int i2);
        }

        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        void a(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.q = null;
                this.p = null;
                this.g = 0;
                this.h = 0;
            } else {
                this.q = recyclerView;
                this.p = recyclerView.f;
                this.g = recyclerView.getWidth();
                this.h = recyclerView.getHeight();
            }
            this.e = 1073741824;
            this.f = 1073741824;
        }

        void c(int i, int i2) {
            this.g = MeasureSpec.getSize(i);
            this.e = MeasureSpec.getMode(i);
            if (this.e == 0 && !RecyclerView.b) {
                this.g = 0;
            }
            this.h = MeasureSpec.getSize(i2);
            this.f = MeasureSpec.getMode(i2);
            if (this.f == 0 && !RecyclerView.b) {
                this.h = 0;
            }
        }

        void d(int i, int i2) {
            int i3 = Integer.MAX_VALUE;
            int i4 = Integer.MIN_VALUE;
            int childCount = getChildCount();
            if (childCount == 0) {
                this.q.c(i, i2);
                return;
            }
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                Rect rect = this.q.j;
                getDecoratedBoundsWithMargins(childAt, rect);
                if (rect.left < i6) {
                    i6 = rect.left;
                }
                if (rect.right > i5) {
                    i5 = rect.right;
                }
                if (rect.top < i3) {
                    i3 = rect.top;
                }
                if (rect.bottom > i4) {
                    i4 = rect.bottom;
                }
            }
            this.q.j.set(i6, i3, i5, i4);
            setMeasuredDimension(this.q.j, i, i2);
        }

        public void setMeasuredDimension(Rect rect, int i, int i2) {
            setMeasuredDimension(chooseSize(i, (rect.width() + getPaddingLeft()) + getPaddingRight(), getMinimumWidth()), chooseSize(i2, (rect.height() + getPaddingTop()) + getPaddingBottom(), getMinimumHeight()));
        }

        public void requestLayout() {
            if (this.q != null) {
                this.q.requestLayout();
            }
        }

        public void assertInLayoutOrScroll(String str) {
            if (this.q != null) {
                this.q.a(str);
            }
        }

        public static int chooseSize(int i, int i2, int i3) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            switch (mode) {
                case Integer.MIN_VALUE:
                    return Math.min(size, Math.max(i2, i3));
                case 1073741824:
                    return size;
                default:
                    return Math.max(i2, i3);
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            if (this.q != null) {
                this.q.b(str);
            }
        }

        public void setAutoMeasureEnabled(boolean z) {
            this.w = z;
        }

        public boolean isAutoMeasureEnabled() {
            return this.w;
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public final void setItemPrefetchEnabled(boolean z) {
            if (z != this.d) {
                this.d = z;
                this.x = 0;
                if (this.q != null) {
                    this.q.d.a();
                }
            }
        }

        public final boolean isItemPrefetchEnabled() {
            return this.d;
        }

        public void collectAdjacentPrefetchPositions(int i, int i2, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        void b(RecyclerView recyclerView) {
            this.v = true;
            onAttachedToWindow(recyclerView);
        }

        void a(RecyclerView recyclerView, Recycler recycler) {
            this.v = false;
            onDetachedFromWindow(recyclerView, recycler);
        }

        public boolean isAttachedToWindow() {
            return this.v;
        }

        public void postOnAnimation(Runnable runnable) {
            if (this.q != null) {
                ViewCompat.postOnAnimation(this.q, runnable);
            }
        }

        public boolean removeCallbacks(Runnable runnable) {
            if (this.q != null) {
                return this.q.removeCallbacks(runnable);
            }
            return false;
        }

        @CallSuper
        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        @CallSuper
        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow(recyclerView);
        }

        public boolean getClipToPadding() {
            return this.q != null && this.q.h;
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state) {
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof MarginLayoutParams) {
                return new LayoutParams((MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public void scrollToPosition(int i) {
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            if (!(this.t == null || smoothScroller == this.t || !this.t.isRunning())) {
                this.t.e();
            }
            this.t = smoothScroller;
            this.t.a(this.q, this);
        }

        public boolean isSmoothScrolling() {
            return this.t != null && this.t.isRunning();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.q);
        }

        public void endAnimation(View view) {
            if (this.q.x != null) {
                this.q.x.endAnimation(RecyclerView.b(view));
            }
        }

        public void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public void addDisappearingView(View view, int i) {
            a(view, i, true);
        }

        public void addView(View view) {
            addView(view, -1);
        }

        public void addView(View view, int i) {
            a(view, i, false);
        }

        private void a(View view, int i, boolean z) {
            ViewHolder b = RecyclerView.b(view);
            if (z || b.m()) {
                this.q.g.e(b);
            } else {
                this.q.g.f(b);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (b.f() || b.d()) {
                if (b.d()) {
                    b.e();
                } else {
                    b.g();
                }
                this.p.a(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.q) {
                int b2 = this.p.b(view);
                if (i == -1) {
                    i = this.p.b();
                }
                if (b2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.q.indexOfChild(view) + this.q.a());
                } else if (b2 != i) {
                    this.q.m.moveView(b2, i);
                }
            } else {
                this.p.a(view, i, false);
                layoutParams.e = true;
                if (this.t != null && this.t.isRunning()) {
                    this.t.a(view);
                }
            }
            if (layoutParams.f) {
                b.itemView.invalidate();
                layoutParams.f = false;
            }
        }

        public void removeView(View view) {
            this.p.a(view);
        }

        public void removeViewAt(int i) {
            if (getChildAt(i) != null) {
                this.p.a(i);
            }
        }

        public void removeAllViews() {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                this.p.a(childCount);
            }
        }

        public int getBaseline() {
            return -1;
        }

        public int getPosition(View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getItemViewType(View view) {
            return RecyclerView.b(view).getItemViewType();
        }

        @Nullable
        public View findContainingItemView(View view) {
            if (this.q == null) {
                return null;
            }
            View findContainingItemView = this.q.findContainingItemView(view);
            if (findContainingItemView == null || this.p.c(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        public View findViewByPosition(int i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                ViewHolder b = RecyclerView.b(childAt);
                if (b != null && b.getLayoutPosition() == i && !b.c() && (this.q.B.isPreLayout() || !b.m())) {
                    return childAt;
                }
            }
            return null;
        }

        public void detachView(View view) {
            int b = this.p.b(view);
            if (b >= 0) {
                a(b, view);
            }
        }

        public void detachViewAt(int i) {
            a(i, getChildAt(i));
        }

        private void a(int i, View view) {
            this.p.e(i);
        }

        public void attachView(View view, int i, LayoutParams layoutParams) {
            ViewHolder b = RecyclerView.b(view);
            if (b.m()) {
                this.q.g.e(b);
            } else {
                this.q.g.f(b);
            }
            this.p.a(view, i, layoutParams, b.m());
        }

        public void attachView(View view, int i) {
            attachView(view, i, (LayoutParams) view.getLayoutParams());
        }

        public void attachView(View view) {
            attachView(view, -1);
        }

        public void removeDetachedView(View view) {
            this.q.removeDetachedView(view, false);
        }

        public void moveView(int i, int i2) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.q.toString());
            }
            detachViewAt(i);
            attachView(childAt, i2);
        }

        public void detachAndScrapView(View view, Recycler recycler) {
            a(recycler, this.p.b(view), view);
        }

        public void detachAndScrapViewAt(int i, Recycler recycler) {
            a(recycler, i, getChildAt(i));
        }

        public void removeAndRecycleView(View view, Recycler recycler) {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i, Recycler recycler) {
            View childAt = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(childAt);
        }

        public int getChildCount() {
            return this.p != null ? this.p.b() : 0;
        }

        public View getChildAt(int i) {
            return this.p != null ? this.p.b(i) : null;
        }

        public int getWidthMode() {
            return this.e;
        }

        public int getHeightMode() {
            return this.f;
        }

        public int getWidth() {
            return this.g;
        }

        public int getHeight() {
            return this.h;
        }

        public int getPaddingLeft() {
            return this.q != null ? this.q.getPaddingLeft() : 0;
        }

        public int getPaddingTop() {
            return this.q != null ? this.q.getPaddingTop() : 0;
        }

        public int getPaddingRight() {
            return this.q != null ? this.q.getPaddingRight() : 0;
        }

        public int getPaddingBottom() {
            return this.q != null ? this.q.getPaddingBottom() : 0;
        }

        public int getPaddingStart() {
            return this.q != null ? ViewCompat.getPaddingStart(this.q) : 0;
        }

        public int getPaddingEnd() {
            return this.q != null ? ViewCompat.getPaddingEnd(this.q) : 0;
        }

        public boolean isFocused() {
            return this.q != null && this.q.isFocused();
        }

        public boolean hasFocus() {
            return this.q != null && this.q.hasFocus();
        }

        public View getFocusedChild() {
            if (this.q == null) {
                return null;
            }
            View focusedChild = this.q.getFocusedChild();
            if (focusedChild == null || this.p.c(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public int getItemCount() {
            Adapter adapter = this.q != null ? this.q.getAdapter() : null;
            return adapter != null ? adapter.getItemCount() : 0;
        }

        public void offsetChildrenHorizontal(int i) {
            if (this.q != null) {
                this.q.offsetChildrenHorizontal(i);
            }
        }

        public void offsetChildrenVertical(int i) {
            if (this.q != null) {
                this.q.offsetChildrenVertical(i);
            }
        }

        public void ignoreView(View view) {
            if (view.getParent() != this.q || this.q.indexOfChild(view) == -1) {
                throw new IllegalArgumentException("View should be fully attached to be ignored" + this.q.a());
            }
            ViewHolder b = RecyclerView.b(view);
            b.b(128);
            this.q.g.g(b);
        }

        public void stopIgnoringView(View view) {
            ViewHolder b = RecyclerView.b(view);
            b.i();
            b.r();
            b.b(4);
        }

        public void detachAndScrapAttachedViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                a(recycler, childCount, getChildAt(childCount));
            }
        }

        private void a(Recycler recycler, int i, View view) {
            ViewHolder b = RecyclerView.b(view);
            if (!b.c()) {
                if (!b.j() || b.m() || this.q.l.hasStableIds()) {
                    detachViewAt(i);
                    recycler.b(view);
                    this.q.g.onViewDetached(b);
                    return;
                }
                removeViewAt(i);
                recycler.b(b);
            }
        }

        void a(Recycler recycler) {
            int c = recycler.c();
            for (int i = c - 1; i >= 0; i--) {
                View b = recycler.b(i);
                ViewHolder b2 = RecyclerView.b(b);
                if (!b2.c()) {
                    b2.setIsRecyclable(false);
                    if (b2.n()) {
                        this.q.removeDetachedView(b, false);
                    }
                    if (this.q.x != null) {
                        this.q.x.endAnimation(b2);
                    }
                    b2.setIsRecyclable(true);
                    recycler.a(b);
                }
            }
            recycler.d();
            if (c > 0) {
                this.q.invalidate();
            }
        }

        public void measureChild(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect c = this.q.c(view);
            int i3 = (c.left + c.right) + i;
            int i4 = (c.bottom + c.top) + i2;
            i3 = getChildMeasureSpec(getWidth(), getWidthMode(), i3 + (getPaddingLeft() + getPaddingRight()), layoutParams.width, canScrollHorizontally());
            i4 = getChildMeasureSpec(getHeight(), getHeightMode(), i4 + (getPaddingTop() + getPaddingBottom()), layoutParams.height, canScrollVertically());
            if (b(view, i3, i4, layoutParams)) {
                view.measure(i3, i4);
            }
        }

        boolean a(View view, int i, int i2, LayoutParams layoutParams) {
            return (this.c && a(view.getMeasuredWidth(), i, layoutParams.width) && a(view.getMeasuredHeight(), i2, layoutParams.height)) ? false : true;
        }

        boolean b(View view, int i, int i2, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.c && a(view.getWidth(), i, layoutParams.width) && a(view.getHeight(), i2, layoutParams.height)) ? false : true;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.c;
        }

        public void setMeasurementCacheEnabled(boolean z) {
            this.c = z;
        }

        private static boolean a(int i, int i2, int i3) {
            int mode = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            switch (mode) {
                case Integer.MIN_VALUE:
                    if (size < i) {
                        return false;
                    }
                    return true;
                case 0:
                    return true;
                case 1073741824:
                    if (size != i) {
                        return false;
                    }
                    return true;
                default:
                    return false;
            }
        }

        public void measureChildWithMargins(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect c = this.q.c(view);
            int i3 = (c.left + c.right) + i;
            int i4 = (c.bottom + c.top) + i2;
            i3 = getChildMeasureSpec(getWidth(), getWidthMode(), i3 + (((getPaddingLeft() + getPaddingRight()) + layoutParams.leftMargin) + layoutParams.rightMargin), layoutParams.width, canScrollHorizontally());
            i4 = getChildMeasureSpec(getHeight(), getHeightMode(), i4 + (((getPaddingTop() + getPaddingBottom()) + layoutParams.topMargin) + layoutParams.bottomMargin), layoutParams.height, canScrollVertically());
            if (b(view, i3, i4, layoutParams)) {
                view.measure(i3, i4);
            }
        }

        @Deprecated
        public static int getChildMeasureSpec(int i, int i2, int i3, boolean z) {
            int i4 = 1073741824;
            int max = Math.max(0, i - i2);
            if (z) {
                if (i3 < 0) {
                    i4 = 0;
                    i3 = 0;
                }
            } else if (i3 < 0) {
                if (i3 == -1) {
                    i3 = max;
                } else if (i3 == -2) {
                    i4 = Integer.MIN_VALUE;
                    i3 = max;
                } else {
                    i4 = 0;
                    i3 = 0;
                }
            }
            return MeasureSpec.makeMeasureSpec(i3, i4);
        }

        public static int getChildMeasureSpec(int i, int i2, int i3, int i4, boolean z) {
            int i5 = 0;
            int max = Math.max(0, i - i3);
            if (z) {
                if (i4 >= 0) {
                    i5 = 1073741824;
                    max = i4;
                } else if (i4 == -1) {
                    switch (i2) {
                        case Integer.MIN_VALUE:
                        case 1073741824:
                            i5 = max;
                            break;
                        case 0:
                            i2 = 0;
                            break;
                        default:
                            i2 = 0;
                            break;
                    }
                    max = i5;
                    i5 = i2;
                } else {
                    if (i4 == -2) {
                        max = 0;
                    }
                    max = 0;
                }
            } else if (i4 >= 0) {
                i5 = 1073741824;
                max = i4;
            } else if (i4 == -1) {
                i5 = i2;
            } else {
                if (i4 == -2) {
                    if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                        i5 = Integer.MIN_VALUE;
                    }
                }
                max = 0;
            }
            return MeasureSpec.makeMeasureSpec(max, i5);
        }

        public int getDecoratedMeasuredWidth(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            return rect.right + (view.getMeasuredWidth() + rect.left);
        }

        public int getDecoratedMeasuredHeight(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            return rect.bottom + (view.getMeasuredHeight() + rect.top);
        }

        public void layoutDecorated(View view, int i, int i2, int i3, int i4) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            view.layout(rect.left + i, rect.top + i2, i3 - rect.right, i4 - rect.bottom);
        }

        public void layoutDecoratedWithMargins(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.d;
            view.layout((rect.left + i) + layoutParams.leftMargin, (rect.top + i2) + layoutParams.topMargin, (i3 - rect.right) - layoutParams.rightMargin, (i4 - rect.bottom) - layoutParams.bottomMargin);
        }

        public void getTransformedBoundingBox(View view, boolean z, Rect rect) {
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).d;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, rect2.bottom + view.getHeight());
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.q != null) {
                Matrix matrix = view.getMatrix();
                if (!(matrix == null || matrix.isIdentity())) {
                    RectF rectF = this.q.k;
                    rectF.set(rect);
                    matrix.mapRect(rectF);
                    rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
                }
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void getDecoratedBoundsWithMargins(View view, Rect rect) {
            RecyclerView.a(view, rect);
        }

        public int getDecoratedLeft(View view) {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedTop(View view) {
            return view.getTop() - getTopDecorationHeight(view);
        }

        public int getDecoratedRight(View view) {
            return view.getRight() + getRightDecorationWidth(view);
        }

        public int getDecoratedBottom(View view) {
            return view.getBottom() + getBottomDecorationHeight(view);
        }

        public void calculateItemDecorationsForChild(View view, Rect rect) {
            if (this.q == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(this.q.c(view));
            }
        }

        public int getTopDecorationHeight(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.top;
        }

        public int getBottomDecorationHeight(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.bottom;
        }

        public int getLeftDecorationWidth(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.left;
        }

        public int getRightDecorationWidth(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.right;
        }

        @Nullable
        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
            return null;
        }

        public View onInterceptFocusSearch(View view, int i) {
            return null;
        }

        private int[] a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            int[] iArr = new int[2];
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width2 = left + rect.width();
            int height2 = top + rect.height();
            int min = Math.min(0, left - paddingLeft);
            int min2 = Math.min(0, top - paddingTop);
            int max = Math.max(0, width2 - width);
            height = Math.max(0, height2 - height);
            if (getLayoutDirection() == 1) {
                if (max == 0) {
                    max = Math.max(min, width2 - width);
                }
                min = max;
            } else {
                if (min != 0) {
                    max = min;
                } else {
                    max = Math.min(left - paddingLeft, max);
                }
                min = max;
            }
            if (min2 != 0) {
                max = min2;
            } else {
                max = Math.min(top - paddingTop, height);
            }
            iArr[0] = min;
            iArr[1] = max;
            return iArr;
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] a = a(recyclerView, view, rect, z);
            int i = a[0];
            int i2 = a[1];
            if (z2 && !a(recyclerView, i, i2)) {
                return false;
            }
            if (i == 0 && i2 == 0) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i, i2);
            } else {
                recyclerView.smoothScrollBy(i, i2);
            }
            return true;
        }

        public boolean isViewPartiallyVisible(@NonNull View view, boolean z, boolean z2) {
            boolean z3;
            if (this.r.a(view, 24579) && this.s.a(view, 24579)) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z) {
                return z3;
            }
            if (z3) {
                return false;
            }
            return true;
        }

        private boolean a(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            Rect rect = this.q.j;
            getDecoratedBoundsWithMargins(focusedChild, rect);
            if (rect.left - i >= width || rect.right - i <= paddingLeft || rect.top - i2 >= height || rect.bottom - i2 <= paddingTop) {
                return false;
            }
            return true;
        }

        @Deprecated
        public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public boolean onRequestChildFocus(RecyclerView recyclerView, State state, View view, View view2) {
            return onRequestChildFocus(recyclerView, view, view2);
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        }

        public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        public void onItemsChanged(RecyclerView recyclerView) {
        }

        public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
            onItemsUpdated(recyclerView, i, i2);
        }

        public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int computeHorizontalScrollExtent(State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(State state) {
            return 0;
        }

        public int computeVerticalScrollRange(State state) {
            return 0;
        }

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            this.q.c(i, i2);
        }

        public void setMeasuredDimension(int i, int i2) {
            this.q.setMeasuredDimension(i, i2);
        }

        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.q);
        }

        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.q);
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        void f() {
            if (this.t != null) {
                this.t.e();
            }
        }

        private void a(SmoothScroller smoothScroller) {
            if (this.t == smoothScroller) {
                this.t = null;
            }
        }

        public void onScrollStateChanged(int i) {
        }

        public void removeAndRecycleAllViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.b(getChildAt(childCount)).c()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            onInitializeAccessibilityNodeInfo(this.q.d, this.q.B, accessibilityNodeInfoCompat);
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.q.canScrollVertically(-1) || this.q.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.q.canScrollVertically(1) || this.q.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            onInitializeAccessibilityEvent(this.q.d, this.q.B, accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent(Recycler recycler, State state, AccessibilityEvent accessibilityEvent) {
            boolean z = true;
            if (this.q != null && accessibilityEvent != null) {
                if (!(this.q.canScrollVertically(1) || this.q.canScrollVertically(-1) || this.q.canScrollHorizontally(-1) || this.q.canScrollHorizontally(1))) {
                    z = false;
                }
                accessibilityEvent.setScrollable(z);
                if (this.q.l != null) {
                    accessibilityEvent.setItemCount(this.q.l.getItemCount());
                }
            }
        }

        void a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder b = RecyclerView.b(view);
            if (b != null && !b.m() && !this.p.c(b.itemView)) {
                onInitializeAccessibilityNodeInfoForItem(this.q.d, this.q.B, view, accessibilityNodeInfoCompat);
            }
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int position;
            int position2 = canScrollVertically() ? getPosition(view) : 0;
            if (canScrollHorizontally()) {
                position = getPosition(view);
            } else {
                position = 0;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(position2, 1, position, 1, false, false));
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.u = true;
        }

        public int getSelectionModeForAccessibility(Recycler recycler, State state) {
            return 0;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state) {
            if (this.q == null || this.q.l == null || !canScrollVertically()) {
                return 1;
            }
            return this.q.l.getItemCount();
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state) {
            if (this.q == null || this.q.l == null || !canScrollHorizontally()) {
                return 1;
            }
            return this.q.l.getItemCount();
        }

        public boolean isLayoutHierarchical(Recycler recycler, State state) {
            return false;
        }

        boolean a(int i, Bundle bundle) {
            return performAccessibilityAction(this.q.d, this.q.B, i, bundle);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean performAccessibilityAction(android.support.v7.widget.RecyclerView.Recycler r7, android.support.v7.widget.RecyclerView.State r8, int r9, android.os.Bundle r10) {
            /*
            r6 = this;
            r4 = -1;
            r2 = 1;
            r1 = 0;
            r0 = r6.q;
            if (r0 != 0) goto L_0x0008;
        L_0x0007:
            return r1;
        L_0x0008:
            switch(r9) {
                case 4096: goto L_0x004a;
                case 8192: goto L_0x0018;
                default: goto L_0x000b;
            };
        L_0x000b:
            r0 = r1;
            r3 = r1;
        L_0x000d:
            if (r3 != 0) goto L_0x0011;
        L_0x000f:
            if (r0 == 0) goto L_0x0007;
        L_0x0011:
            r1 = r6.q;
            r1.scrollBy(r0, r3);
            r1 = r2;
            goto L_0x0007;
        L_0x0018:
            r0 = r6.q;
            r0 = r0.canScrollVertically(r4);
            if (r0 == 0) goto L_0x007f;
        L_0x0020:
            r0 = r6.getHeight();
            r3 = r6.getPaddingTop();
            r0 = r0 - r3;
            r3 = r6.getPaddingBottom();
            r0 = r0 - r3;
            r0 = -r0;
        L_0x002f:
            r3 = r6.q;
            r3 = r3.canScrollHorizontally(r4);
            if (r3 == 0) goto L_0x007a;
        L_0x0037:
            r3 = r6.getWidth();
            r4 = r6.getPaddingLeft();
            r3 = r3 - r4;
            r4 = r6.getPaddingRight();
            r3 = r3 - r4;
            r3 = -r3;
            r5 = r3;
            r3 = r0;
            r0 = r5;
            goto L_0x000d;
        L_0x004a:
            r0 = r6.q;
            r0 = r0.canScrollVertically(r2);
            if (r0 == 0) goto L_0x007d;
        L_0x0052:
            r0 = r6.getHeight();
            r3 = r6.getPaddingTop();
            r0 = r0 - r3;
            r3 = r6.getPaddingBottom();
            r0 = r0 - r3;
        L_0x0060:
            r3 = r6.q;
            r3 = r3.canScrollHorizontally(r2);
            if (r3 == 0) goto L_0x007a;
        L_0x0068:
            r3 = r6.getWidth();
            r4 = r6.getPaddingLeft();
            r3 = r3 - r4;
            r4 = r6.getPaddingRight();
            r3 = r3 - r4;
            r5 = r3;
            r3 = r0;
            r0 = r5;
            goto L_0x000d;
        L_0x007a:
            r3 = r0;
            r0 = r1;
            goto L_0x000d;
        L_0x007d:
            r0 = r1;
            goto L_0x0060;
        L_0x007f:
            r0 = r1;
            goto L_0x002f;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.LayoutManager.performAccessibilityAction(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, int, android.os.Bundle):boolean");
        }

        boolean a(View view, int i, Bundle bundle) {
            return performAccessibilityActionForItem(this.q.d, this.q.B, view, i, bundle);
        }

        public boolean performAccessibilityActionForItem(Recycler recycler, State state, View view, int i, Bundle bundle) {
            return false;
        }

        public static Properties getProperties(Context context, AttributeSet attributeSet, int i, int i2) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, i2);
            properties.orientation = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        void c(RecyclerView recyclerView) {
            c(MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        boolean e() {
            return false;
        }

        boolean g() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                android.view.ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public static abstract class SmoothScroller {
        private int a = -1;
        private RecyclerView b;
        private LayoutManager c;
        private boolean d;
        private boolean e;
        private View f;
        private final Action g = new Action(0, 0);

        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int i);
        }

        public static class Action {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
            private int a;
            private int b;
            private int c;
            private int d;
            private Interpolator e;
            private boolean f;
            private int g;

            public Action(int i, int i2) {
                this(i, i2, Integer.MIN_VALUE, null);
            }

            public Action(int i, int i2, int i3) {
                this(i, i2, i3, null);
            }

            public Action(int i, int i2, int i3, Interpolator interpolator) {
                this.d = -1;
                this.f = false;
                this.g = 0;
                this.a = i;
                this.b = i2;
                this.c = i3;
                this.e = interpolator;
            }

            public void jumpTo(int i) {
                this.d = i;
            }

            boolean a() {
                return this.d >= 0;
            }

            void a(RecyclerView recyclerView) {
                if (this.d >= 0) {
                    int i = this.d;
                    this.d = -1;
                    recyclerView.a(i);
                    this.f = false;
                } else if (this.f) {
                    b();
                    if (this.e != null) {
                        recyclerView.y.smoothScrollBy(this.a, this.b, this.c, this.e);
                    } else if (this.c == Integer.MIN_VALUE) {
                        recyclerView.y.smoothScrollBy(this.a, this.b);
                    } else {
                        recyclerView.y.smoothScrollBy(this.a, this.b, this.c);
                    }
                    this.g++;
                    if (this.g > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                } else {
                    this.g = 0;
                }
            }

            private void b() {
                if (this.e != null && this.c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public int getDx() {
                return this.a;
            }

            public void setDx(int i) {
                this.f = true;
                this.a = i;
            }

            public int getDy() {
                return this.b;
            }

            public void setDy(int i) {
                this.f = true;
                this.b = i;
            }

            public int getDuration() {
                return this.c;
            }

            public void setDuration(int i) {
                this.f = true;
                this.c = i;
            }

            public Interpolator getInterpolator() {
                return this.e;
            }

            public void setInterpolator(Interpolator interpolator) {
                this.f = true;
                this.e = interpolator;
            }

            public void update(int i, int i2, int i3, Interpolator interpolator) {
                this.a = i;
                this.b = i2;
                this.c = i3;
                this.e = interpolator;
                this.f = true;
            }
        }

        protected abstract void a();

        protected abstract void a(int i, int i2, State state, Action action);

        protected abstract void a(View view, State state, Action action);

        protected abstract void b();

        void a(RecyclerView recyclerView, LayoutManager layoutManager) {
            this.b = recyclerView;
            this.c = layoutManager;
            if (this.a == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            this.b.B.p = this.a;
            this.e = true;
            this.d = true;
            this.f = findViewByPosition(getTargetPosition());
            a();
            this.b.y.a();
        }

        public void setTargetPosition(int i) {
            this.a = i;
        }

        @Nullable
        public LayoutManager getLayoutManager() {
            return this.c;
        }

        protected final void e() {
            if (this.e) {
                b();
                this.b.B.p = -1;
                this.f = null;
                this.a = -1;
                this.d = false;
                this.e = false;
                this.c.a(this);
                this.c = null;
                this.b = null;
            }
        }

        public boolean isPendingInitialRun() {
            return this.d;
        }

        public boolean isRunning() {
            return this.e;
        }

        public int getTargetPosition() {
            return this.a;
        }

        private void a(int i, int i2) {
            RecyclerView recyclerView = this.b;
            if (!this.e || this.a == -1 || recyclerView == null) {
                e();
            }
            this.d = false;
            if (this.f != null) {
                if (getChildPosition(this.f) == this.a) {
                    a(this.f, recyclerView.B, this.g);
                    this.g.a(recyclerView);
                    e();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.e) {
                a(i, i2, recyclerView.B, this.g);
                boolean a = this.g.a();
                this.g.a(recyclerView);
                if (!a) {
                    return;
                }
                if (this.e) {
                    this.d = true;
                    recyclerView.y.a();
                    return;
                }
                e();
            }
        }

        public int getChildPosition(View view) {
            return this.b.getChildLayoutPosition(view);
        }

        public int getChildCount() {
            return this.b.m.getChildCount();
        }

        public View findViewByPosition(int i) {
            return this.b.m.findViewByPosition(i);
        }

        @Deprecated
        public void instantScrollToPosition(int i) {
            this.b.scrollToPosition(i);
        }

        protected void a(View view) {
            if (getChildPosition(view) == getTargetPosition()) {
                this.f = view;
            }
        }

        protected void a(PointF pointF) {
            float sqrt = (float) Math.sqrt((double) ((pointF.x * pointF.x) + (pointF.y * pointF.y)));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }
    }

    public static abstract class OnFlingListener {
        public abstract boolean onFling(int i, int i2);
    }

    public static abstract class AdapterDataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int i, int i2) {
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            onItemRangeChanged(i, i2);
        }

        public void onItemRangeInserted(int i, int i2) {
        }

        public void onItemRangeRemoved(int i, int i2) {
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
        }
    }

    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i, int i2);
    }

    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(View view);

        void onChildViewDetachedFromWindow(View view);
    }

    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public static class RecycledViewPool {
        SparseArray<a> a = new SparseArray();
        private int b = 0;

        static class a {
            ArrayList<ViewHolder> a = new ArrayList();
            int b = 5;
            long c = 0;
            long d = 0;

            a() {
            }
        }

        public void clear() {
            for (int i = 0; i < this.a.size(); i++) {
                ((a) this.a.valueAt(i)).a.clear();
            }
        }

        public void setMaxRecycledViews(int i, int i2) {
            a a = a(i);
            a.b = i2;
            ArrayList arrayList = a.a;
            if (arrayList != null) {
                while (arrayList.size() > i2) {
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }

        public int getRecycledViewCount(int i) {
            return a(i).a.size();
        }

        public ViewHolder getRecycledView(int i) {
            a aVar = (a) this.a.get(i);
            if (aVar == null || aVar.a.isEmpty()) {
                return null;
            }
            ArrayList arrayList = aVar.a;
            return (ViewHolder) arrayList.remove(arrayList.size() - 1);
        }

        public void putRecycledView(ViewHolder viewHolder) {
            int itemViewType = viewHolder.getItemViewType();
            ArrayList arrayList = a(itemViewType).a;
            if (((a) this.a.get(itemViewType)).b > arrayList.size()) {
                viewHolder.r();
                arrayList.add(viewHolder);
            }
        }

        long a(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        void a(int i, long j) {
            a a = a(i);
            a.c = a(a.c, j);
        }

        void b(int i, long j) {
            a a = a(i);
            a.d = a(a.d, j);
        }

        boolean a(int i, long j, long j2) {
            long j3 = a(i).c;
            return j3 == 0 || j3 + j < j2;
        }

        boolean b(int i, long j, long j2) {
            long j3 = a(i).d;
            return j3 == 0 || j3 + j < j2;
        }

        void a(Adapter adapter) {
            this.b++;
        }

        void a() {
            this.b--;
        }

        void a(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                a();
            }
            if (!z && this.b == 0) {
                clear();
            }
            if (adapter2 != null) {
                a(adapter2);
            }
        }

        private a a(int i) {
            a aVar = (a) this.a.get(i);
            if (aVar != null) {
                return aVar;
            }
            aVar = new a();
            this.a.put(i, aVar);
            return aVar;
        }
    }

    public final class Recycler {
        final ArrayList<ViewHolder> a = new ArrayList();
        ArrayList<ViewHolder> b = null;
        final ArrayList<ViewHolder> c = new ArrayList();
        int d = 2;
        RecycledViewPool e;
        final /* synthetic */ RecyclerView f;
        private final List<ViewHolder> g = Collections.unmodifiableList(this.a);
        private int h = 2;
        private ViewCacheExtension i;

        public Recycler(RecyclerView recyclerView) {
            this.f = recyclerView;
        }

        public void clear() {
            this.a.clear();
            b();
        }

        public void setViewCacheSize(int i) {
            this.h = i;
            a();
        }

        void a() {
            this.d = (this.f.m != null ? this.f.m.x : 0) + this.h;
            for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.d; size--) {
                a(size);
            }
        }

        public List<ViewHolder> getScrapList() {
            return this.g;
        }

        boolean a(ViewHolder viewHolder) {
            if (viewHolder.m()) {
                return this.f.B.isPreLayout();
            }
            if (viewHolder.b < 0 || viewHolder.b >= this.f.l.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder + this.f.a());
            } else if (!this.f.B.isPreLayout() && this.f.l.getItemViewType(viewHolder.b) != viewHolder.getItemViewType()) {
                return false;
            } else {
                if (!this.f.l.hasStableIds() || viewHolder.getItemId() == this.f.l.getItemId(viewHolder.b)) {
                    return true;
                }
                return false;
            }
        }

        private boolean a(ViewHolder viewHolder, int i, int i2, long j) {
            viewHolder.l = this.f;
            int itemViewType = viewHolder.getItemViewType();
            long nanoTime = this.f.getNanoTime();
            if (j != Long.MAX_VALUE && !this.e.b(itemViewType, nanoTime, j)) {
                return false;
            }
            this.f.l.bindViewHolder(viewHolder, i);
            this.e.b(viewHolder.getItemViewType(), this.f.getNanoTime() - nanoTime);
            e(viewHolder);
            if (this.f.B.isPreLayout()) {
                viewHolder.f = i2;
            }
            return true;
        }

        public void bindViewToPosition(View view, int i) {
            ViewHolder b = RecyclerView.b(view);
            if (b == null) {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter" + this.f.a());
            }
            int b2 = this.f.e.b(i);
            if (b2 < 0 || b2 >= this.f.l.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + b2 + ")." + "state:" + this.f.B.getItemCount() + this.f.a());
            }
            LayoutParams layoutParams;
            a(b, b2, i, Long.MAX_VALUE);
            android.view.ViewGroup.LayoutParams layoutParams2 = b.itemView.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = (LayoutParams) this.f.generateDefaultLayoutParams();
                b.itemView.setLayoutParams(layoutParams);
            } else if (this.f.checkLayoutParams(layoutParams2)) {
                layoutParams = (LayoutParams) layoutParams2;
            } else {
                layoutParams = (LayoutParams) this.f.generateLayoutParams(layoutParams2);
                b.itemView.setLayoutParams(layoutParams);
            }
            layoutParams.e = true;
            layoutParams.c = b;
            layoutParams.f = b.itemView.getParent() == null;
        }

        public int convertPreLayoutPositionToPostLayout(int i) {
            if (i >= 0 && i < this.f.B.getItemCount()) {
                return !this.f.B.isPreLayout() ? i : this.f.e.b(i);
            } else {
                throw new IndexOutOfBoundsException("invalid position " + i + ". State " + "item count is " + this.f.B.getItemCount() + this.f.a());
            }
        }

        public View getViewForPosition(int i) {
            return a(i, false);
        }

        View a(int i, boolean z) {
            return a(i, z, Long.MAX_VALUE).itemView;
        }

        @Nullable
        ViewHolder a(int i, boolean z, long j) {
            boolean z2 = true;
            if (i < 0 || i >= this.f.B.getItemCount()) {
                throw new IndexOutOfBoundsException("Invalid item position " + i + "(" + i + "). Item count:" + this.f.B.getItemCount() + this.f.a());
            }
            ViewHolder c;
            boolean z3;
            ViewHolder viewHolder;
            boolean z4;
            LayoutParams layoutParams;
            if (this.f.B.isPreLayout()) {
                c = c(i);
                z3 = c != null;
                viewHolder = c;
            } else {
                viewHolder = null;
                z3 = false;
            }
            if (viewHolder == null) {
                viewHolder = b(i, z);
                if (viewHolder != null) {
                    if (a(viewHolder)) {
                        z3 = true;
                    } else {
                        if (!z) {
                            viewHolder.b(4);
                            if (viewHolder.d()) {
                                this.f.removeDetachedView(viewHolder.itemView, false);
                                viewHolder.e();
                            } else if (viewHolder.f()) {
                                viewHolder.g();
                            }
                            b(viewHolder);
                        }
                        viewHolder = null;
                    }
                }
            }
            if (viewHolder == null) {
                int b = this.f.e.b(i);
                if (b < 0 || b >= this.f.l.getItemCount()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + b + ")." + "state:" + this.f.B.getItemCount() + this.f.a());
                }
                boolean z5;
                View viewForPositionAndType;
                long nanoTime;
                RecyclerView d;
                int itemViewType = this.f.l.getItemViewType(b);
                if (this.f.l.hasStableIds()) {
                    viewHolder = a(this.f.l.getItemId(b), itemViewType, z);
                    if (viewHolder != null) {
                        viewHolder.b = b;
                        z5 = true;
                        if (viewHolder == null && this.i != null) {
                            viewForPositionAndType = this.i.getViewForPositionAndType(this, i, itemViewType);
                            if (viewForPositionAndType != null) {
                                viewHolder = this.f.getChildViewHolder(viewForPositionAndType);
                                if (viewHolder == null) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + this.f.a());
                                } else if (viewHolder.c()) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + this.f.a());
                                }
                            }
                        }
                        if (viewHolder == null) {
                            viewHolder = e().getRecycledView(itemViewType);
                            if (viewHolder != null) {
                                viewHolder.r();
                                if (RecyclerView.a) {
                                    f(viewHolder);
                                }
                            }
                        }
                        if (viewHolder == null) {
                            nanoTime = this.f.getNanoTime();
                            if (j == Long.MAX_VALUE && !this.e.a(itemViewType, nanoTime, j)) {
                                return null;
                            }
                            viewHolder = this.f.l.createViewHolder(this.f, itemViewType);
                            if (RecyclerView.K) {
                                d = RecyclerView.d(viewHolder.itemView);
                                if (d != null) {
                                    viewHolder.a = new WeakReference(d);
                                }
                            }
                            this.e.a(itemViewType, this.f.getNanoTime() - nanoTime);
                        }
                        c = viewHolder;
                        z4 = z5;
                    }
                }
                z5 = z3;
                viewForPositionAndType = this.i.getViewForPositionAndType(this, i, itemViewType);
                if (viewForPositionAndType != null) {
                    viewHolder = this.f.getChildViewHolder(viewForPositionAndType);
                    if (viewHolder == null) {
                        throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + this.f.a());
                    } else if (viewHolder.c()) {
                        throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + this.f.a());
                    }
                }
                if (viewHolder == null) {
                    viewHolder = e().getRecycledView(itemViewType);
                    if (viewHolder != null) {
                        viewHolder.r();
                        if (RecyclerView.a) {
                            f(viewHolder);
                        }
                    }
                }
                if (viewHolder == null) {
                    nanoTime = this.f.getNanoTime();
                    if (j == Long.MAX_VALUE) {
                    }
                    viewHolder = this.f.l.createViewHolder(this.f, itemViewType);
                    if (RecyclerView.K) {
                        d = RecyclerView.d(viewHolder.itemView);
                        if (d != null) {
                            viewHolder.a = new WeakReference(d);
                        }
                    }
                    this.e.a(itemViewType, this.f.getNanoTime() - nanoTime);
                }
                c = viewHolder;
                z4 = z5;
            } else {
                c = viewHolder;
                z4 = z3;
            }
            if (z4 && !this.f.B.isPreLayout() && c.a(8192)) {
                c.a(0, 8192);
                if (this.f.B.i) {
                    this.f.a(c, this.f.x.recordPreLayoutInformation(this.f.B, c, ItemAnimator.b(c) | 4096, c.q()));
                }
            }
            int i2;
            if (this.f.B.isPreLayout() && c.l()) {
                c.f = i;
                i2 = 0;
            } else if (!c.l() || c.k() || c.j()) {
                z3 = a(c, this.f.e.b(i), i, j);
            } else {
                i2 = 0;
            }
            android.view.ViewGroup.LayoutParams layoutParams2 = c.itemView.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = (LayoutParams) this.f.generateDefaultLayoutParams();
                c.itemView.setLayoutParams(layoutParams);
            } else if (this.f.checkLayoutParams(layoutParams2)) {
                layoutParams = (LayoutParams) layoutParams2;
            } else {
                layoutParams = (LayoutParams) this.f.generateLayoutParams(layoutParams2);
                c.itemView.setLayoutParams(layoutParams);
            }
            layoutParams.c = c;
            if (!z4 || r2 == 0) {
                z2 = false;
            }
            layoutParams.f = z2;
            return c;
        }

        private void e(ViewHolder viewHolder) {
            if (this.f.m()) {
                View view = viewHolder.itemView;
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                if (!ViewCompat.hasAccessibilityDelegate(view)) {
                    viewHolder.b(16384);
                    ViewCompat.setAccessibilityDelegate(view, this.f.F.getItemDelegate());
                }
            }
        }

        private void f(ViewHolder viewHolder) {
            if (viewHolder.itemView instanceof ViewGroup) {
                a((ViewGroup) viewHolder.itemView, false);
            }
        }

        private void a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, true);
                }
            }
            if (!z) {
                return;
            }
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }

        public void recycleView(View view) {
            ViewHolder b = RecyclerView.b(view);
            if (b.n()) {
                this.f.removeDetachedView(view, false);
            }
            if (b.d()) {
                b.e();
            } else if (b.f()) {
                b.g();
            }
            b(b);
        }

        void b() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                a(size);
            }
            this.c.clear();
            if (RecyclerView.K) {
                this.f.A.a();
            }
        }

        void a(int i) {
            a((ViewHolder) this.c.get(i), true);
            this.c.remove(i);
        }

        void b(ViewHolder viewHolder) {
            int i = 0;
            if (viewHolder.d() || viewHolder.itemView.getParent() != null) {
                throw new IllegalArgumentException("Scrapped or attached views may not be recycled. isScrap:" + viewHolder.d() + " isAttached:" + (viewHolder.itemView.getParent() != null) + this.f.a());
            } else if (viewHolder.n()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder + this.f.a());
            } else if (viewHolder.c()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + this.f.a());
            } else {
                int size;
                boolean a = viewHolder.v();
                boolean z = this.f.l != null && a && this.f.l.onFailedToRecycleView(viewHolder);
                if (z || viewHolder.isRecyclable()) {
                    if (this.d <= 0 || viewHolder.a(526)) {
                        z = false;
                    } else {
                        size = this.c.size();
                        if (size >= this.d && size > 0) {
                            a(0);
                            size--;
                        }
                        if (RecyclerView.K && size > 0 && !this.f.A.a(viewHolder.b)) {
                            int i2 = size - 1;
                            while (i2 >= 0) {
                                if (!this.f.A.a(((ViewHolder) this.c.get(i2)).b)) {
                                    break;
                                }
                                i2--;
                            }
                            size = i2 + 1;
                        }
                        this.c.add(size, viewHolder);
                        size = true;
                    }
                    if (!size != false) {
                        a(viewHolder, true);
                        i = 1;
                    }
                } else {
                    size = 0;
                }
                this.f.g.g(viewHolder);
                if (size == 0 && r2 == 0 && a) {
                    viewHolder.l = null;
                }
            }
        }

        void a(ViewHolder viewHolder, boolean z) {
            RecyclerView.c(viewHolder);
            if (viewHolder.a(16384)) {
                viewHolder.a(0, 16384);
                ViewCompat.setAccessibilityDelegate(viewHolder.itemView, null);
            }
            if (z) {
                d(viewHolder);
            }
            viewHolder.l = null;
            e().putRecycledView(viewHolder);
        }

        void a(View view) {
            ViewHolder b = RecyclerView.b(view);
            b.p = null;
            b.q = false;
            b.g();
            b(b);
        }

        void b(View view) {
            ViewHolder b = RecyclerView.b(view);
            if (!b.a(12) && b.s() && !this.f.b(b)) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                b.a(this, true);
                this.b.add(b);
            } else if (!b.j() || b.m() || this.f.l.hasStableIds()) {
                b.a(this, false);
                this.a.add(b);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + this.f.a());
            }
        }

        void c(ViewHolder viewHolder) {
            if (viewHolder.q) {
                this.b.remove(viewHolder);
            } else {
                this.a.remove(viewHolder);
            }
            viewHolder.p = null;
            viewHolder.q = false;
            viewHolder.g();
        }

        int c() {
            return this.a.size();
        }

        View b(int i) {
            return ((ViewHolder) this.a.get(i)).itemView;
        }

        void d() {
            this.a.clear();
            if (this.b != null) {
                this.b.clear();
            }
        }

        ViewHolder c(int i) {
            int i2 = 0;
            if (this.b != null) {
                int size = this.b.size();
                if (size != 0) {
                    ViewHolder viewHolder;
                    int i3 = 0;
                    while (i3 < size) {
                        viewHolder = (ViewHolder) this.b.get(i3);
                        if (viewHolder.f() || viewHolder.getLayoutPosition() != i) {
                            i3++;
                        } else {
                            viewHolder.b(32);
                            return viewHolder;
                        }
                    }
                    if (this.f.l.hasStableIds()) {
                        int b = this.f.e.b(i);
                        if (b > 0 && b < this.f.l.getItemCount()) {
                            long itemId = this.f.l.getItemId(b);
                            while (i2 < size) {
                                viewHolder = (ViewHolder) this.b.get(i2);
                                if (viewHolder.f() || viewHolder.getItemId() != itemId) {
                                    i2++;
                                } else {
                                    viewHolder.b(32);
                                    return viewHolder;
                                }
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        ViewHolder b(int i, boolean z) {
            int i2 = 0;
            int size = this.a.size();
            int i3 = 0;
            while (i3 < size) {
                ViewHolder viewHolder = (ViewHolder) this.a.get(i3);
                if (viewHolder.f() || viewHolder.getLayoutPosition() != i || viewHolder.j() || (!this.f.B.f && viewHolder.m())) {
                    i3++;
                } else {
                    viewHolder.b(32);
                    return viewHolder;
                }
            }
            if (!z) {
                View c = this.f.f.c(i);
                if (c != null) {
                    viewHolder = RecyclerView.b(c);
                    this.f.f.e(c);
                    i2 = this.f.f.b(c);
                    if (i2 == -1) {
                        throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + viewHolder + this.f.a());
                    }
                    this.f.f.e(i2);
                    b(c);
                    viewHolder.b(8224);
                    return viewHolder;
                }
            }
            i3 = this.c.size();
            while (i2 < i3) {
                viewHolder = (ViewHolder) this.c.get(i2);
                if (viewHolder.j() || viewHolder.getLayoutPosition() != i) {
                    i2++;
                } else if (z) {
                    return viewHolder;
                } else {
                    this.c.remove(i2);
                    return viewHolder;
                }
            }
            return null;
        }

        ViewHolder a(long j, int i, boolean z) {
            int size;
            for (size = this.a.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.a.get(size);
                if (viewHolder.getItemId() == j && !viewHolder.f()) {
                    if (i == viewHolder.getItemViewType()) {
                        viewHolder.b(32);
                        if (!viewHolder.m() || this.f.B.isPreLayout()) {
                            return viewHolder;
                        }
                        viewHolder.a(2, 14);
                        return viewHolder;
                    } else if (!z) {
                        this.a.remove(size);
                        this.f.removeDetachedView(viewHolder.itemView, false);
                        a(viewHolder.itemView);
                    }
                }
            }
            for (size = this.c.size() - 1; size >= 0; size--) {
                viewHolder = (ViewHolder) this.c.get(size);
                if (viewHolder.getItemId() == j) {
                    if (i == viewHolder.getItemViewType()) {
                        if (z) {
                            return viewHolder;
                        }
                        this.c.remove(size);
                        return viewHolder;
                    } else if (!z) {
                        a(size);
                        return null;
                    }
                }
            }
            return null;
        }

        void d(ViewHolder viewHolder) {
            if (this.f.n != null) {
                this.f.n.onViewRecycled(viewHolder);
            }
            if (this.f.l != null) {
                this.f.l.onViewRecycled(viewHolder);
            }
            if (this.f.B != null) {
                this.f.g.g(viewHolder);
            }
        }

        void a(Adapter adapter, Adapter adapter2, boolean z) {
            clear();
            e().a(adapter, adapter2, z);
        }

        void a(int i, int i2) {
            int i3;
            int i4;
            int i5;
            if (i < i2) {
                i3 = -1;
                i4 = i2;
                i5 = i;
            } else {
                i3 = 1;
                i4 = i;
                i5 = i2;
            }
            int size = this.c.size();
            for (int i6 = 0; i6 < size; i6++) {
                ViewHolder viewHolder = (ViewHolder) this.c.get(i6);
                if (viewHolder != null && viewHolder.b >= r3 && viewHolder.b <= r2) {
                    if (viewHolder.b == i) {
                        viewHolder.a(i2 - i, false);
                    } else {
                        viewHolder.a(i3, false);
                    }
                }
            }
        }

        void b(int i, int i2) {
            int size = this.c.size();
            for (int i3 = 0; i3 < size; i3++) {
                ViewHolder viewHolder = (ViewHolder) this.c.get(i3);
                if (viewHolder != null && viewHolder.b >= i) {
                    viewHolder.a(i2, true);
                }
            }
        }

        void a(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.c.get(size);
                if (viewHolder != null) {
                    if (viewHolder.b >= i3) {
                        viewHolder.a(-i2, z);
                    } else if (viewHolder.b >= i) {
                        viewHolder.b(8);
                        a(size);
                    }
                }
            }
        }

        void a(ViewCacheExtension viewCacheExtension) {
            this.i = viewCacheExtension;
        }

        void a(RecycledViewPool recycledViewPool) {
            if (this.e != null) {
                this.e.a();
            }
            this.e = recycledViewPool;
            if (recycledViewPool != null) {
                this.e.a(this.f.getAdapter());
            }
        }

        RecycledViewPool e() {
            if (this.e == null) {
                this.e = new RecycledViewPool();
            }
            return this.e;
        }

        void c(int i, int i2) {
            int i3 = i + i2;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.c.get(size);
                if (viewHolder != null) {
                    int i4 = viewHolder.b;
                    if (i4 >= i && i4 < i3) {
                        viewHolder.b(2);
                        a(size);
                    }
                }
            }
        }

        void f() {
            if (this.f.l == null || !this.f.l.hasStableIds()) {
                b();
                return;
            }
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                ViewHolder viewHolder = (ViewHolder) this.c.get(i);
                if (viewHolder != null) {
                    viewHolder.b(6);
                    viewHolder.a(null);
                }
            }
        }

        void g() {
            int i;
            int i2 = 0;
            int size = this.c.size();
            for (i = 0; i < size; i++) {
                ((ViewHolder) this.c.get(i)).a();
            }
            size = this.a.size();
            for (i = 0; i < size; i++) {
                ((ViewHolder) this.a.get(i)).a();
            }
            if (this.b != null) {
                i = this.b.size();
                while (i2 < i) {
                    ((ViewHolder) this.b.get(i2)).a();
                    i2++;
                }
            }
        }

        void h() {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) ((ViewHolder) this.c.get(i)).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.e = true;
                }
            }
        }
    }

    public interface RecyclerListener {
        void onViewRecycled(ViewHolder viewHolder);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new bu();
        Parcelable a;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                classLoader = LayoutManager.class.getClassLoader();
            }
            this.a = parcel.readParcelable(classLoader);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.a, 0);
        }

        void a(SavedState savedState) {
            this.a = savedState.a;
        }
    }

    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return false;
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }
    }

    public static class State {
        int a = 0;
        int b = 0;
        int c = 1;
        int d = 0;
        boolean e = false;
        boolean f = false;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        int k;
        long l;
        int m;
        int n;
        int o;
        private int p = -1;
        private SparseArray<Object> q;

        void a(int i) {
            if ((this.c & i) == 0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.c));
            }
        }

        void a(Adapter adapter) {
            this.c = 1;
            this.d = adapter.getItemCount();
            this.f = false;
            this.g = false;
            this.h = false;
        }

        public boolean isMeasuring() {
            return this.h;
        }

        public boolean isPreLayout() {
            return this.f;
        }

        public boolean willRunPredictiveAnimations() {
            return this.j;
        }

        public boolean willRunSimpleAnimations() {
            return this.i;
        }

        public void remove(int i) {
            if (this.q != null) {
                this.q.remove(i);
            }
        }

        public <T> T get(int i) {
            if (this.q == null) {
                return null;
            }
            return this.q.get(i);
        }

        public void put(int i, Object obj) {
            if (this.q == null) {
                this.q = new SparseArray();
            }
            this.q.put(i, obj);
        }

        public int getTargetScrollPosition() {
            return this.p;
        }

        public boolean hasTargetScrollPosition() {
            return this.p != -1;
        }

        public boolean didStructureChange() {
            return this.e;
        }

        public int getItemCount() {
            return this.f ? this.a - this.b : this.d;
        }

        public int getRemainingScrollHorizontal() {
            return this.n;
        }

        public int getRemainingScrollVertical() {
            return this.o;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.p + ", mData=" + this.q + ", mItemCount=" + this.d + ", mPreviousLayoutItemCount=" + this.a + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.b + ", mStructureChanged=" + this.e + ", mInPreLayout=" + this.f + ", mRunSimpleAnimations=" + this.i + ", mRunPredictiveAnimations=" + this.j + '}';
        }
    }

    public static abstract class ViewCacheExtension {
        public abstract View getViewForPositionAndType(Recycler recycler, int i, int i2);
    }

    static class a extends Observable<AdapterDataObserver> {
        a() {
        }

        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onChanged();
            }
        }

        public void notifyItemRangeChanged(int i, int i2) {
            notifyItemRangeChanged(i, i2, null);
        }

        public void notifyItemRangeChanged(int i, int i2, Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeChanged(i, i2, obj);
            }
        }

        public void notifyItemRangeInserted(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeInserted(i, i2);
            }
        }

        public void notifyItemRangeRemoved(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeRemoved(i, i2);
            }
        }

        public void notifyItemMoved(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeMoved(i, i2, 1);
            }
        }
    }

    private class b implements a {
        final /* synthetic */ RecyclerView a;

        b(RecyclerView recyclerView) {
            this.a = recyclerView;
        }

        public void onAnimationFinished(ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.g != null && viewHolder.h == null) {
                viewHolder.g = null;
            }
            viewHolder.h = null;
            if (!viewHolder.u() && !this.a.a(viewHolder.itemView) && viewHolder.n()) {
                this.a.removeDetachedView(viewHolder.itemView, false);
            }
        }
    }

    private class c extends AdapterDataObserver {
        final /* synthetic */ RecyclerView a;

        c(RecyclerView recyclerView) {
            this.a = recyclerView;
        }

        public void onChanged() {
            this.a.b(null);
            this.a.B.e = true;
            this.a.s();
            if (!this.a.e.d()) {
                this.a.requestLayout();
            }
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            this.a.b(null);
            if (this.a.e.a(i, i2, obj)) {
                a();
            }
        }

        public void onItemRangeInserted(int i, int i2) {
            this.a.b(null);
            if (this.a.e.b(i, i2)) {
                a();
            }
        }

        public void onItemRangeRemoved(int i, int i2) {
            this.a.b(null);
            if (this.a.e.c(i, i2)) {
                a();
            }
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            this.a.b(null);
            if (this.a.e.a(i, i2, i3)) {
                a();
            }
        }

        void a() {
            if (RecyclerView.c && this.a.q && this.a.p) {
                ViewCompat.postOnAnimation(this.a, this.a.i);
                return;
            }
            this.a.v = true;
            this.a.requestLayout();
        }
    }

    class d implements Runnable {
        Interpolator a = RecyclerView.H;
        final /* synthetic */ RecyclerView b;
        private int c;
        private int d;
        private OverScroller e;
        private boolean f = false;
        private boolean g = false;

        d(RecyclerView recyclerView) {
            this.b = recyclerView;
            this.e = new OverScroller(recyclerView.getContext(), RecyclerView.H);
        }

        public void run() {
            if (this.b.m == null) {
                stop();
                return;
            }
            b();
            this.b.d();
            OverScroller overScroller = this.e;
            SmoothScroller smoothScroller = this.b.m.t;
            if (overScroller.computeScrollOffset()) {
                int i;
                int i2;
                int i3;
                int i4;
                int itemCount;
                int[] a = this.b.aC;
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i5 = currX - this.c;
                int i6 = currY - this.d;
                this.c = currX;
                this.d = currY;
                if (this.b.dispatchNestedPreScroll(i5, i6, a, null, 1)) {
                    i = i6 - a[1];
                    i2 = i5 - a[0];
                } else {
                    i = i6;
                    i2 = i5;
                }
                if (this.b.l != null) {
                    int i7;
                    this.b.e();
                    this.b.k();
                    TraceCompat.beginSection("RV Scroll");
                    this.b.a(this.b.B);
                    if (i2 != 0) {
                        i6 = this.b.m.scrollHorizontallyBy(i2, this.b.d, this.b.B);
                        i3 = i2 - i6;
                    } else {
                        i3 = 0;
                        i6 = 0;
                    }
                    if (i != 0) {
                        i5 = this.b.m.scrollVerticallyBy(i, this.b.d, this.b.B);
                        i4 = i - i5;
                    } else {
                        i4 = 0;
                        i5 = 0;
                    }
                    TraceCompat.endSection();
                    this.b.u();
                    this.b.l();
                    this.b.a(false);
                    if (!(smoothScroller == null || smoothScroller.isPendingInitialRun() || !smoothScroller.isRunning())) {
                        itemCount = this.b.B.getItemCount();
                        if (itemCount == 0) {
                            smoothScroller.e();
                            itemCount = i4;
                            i7 = i5;
                            i5 = i6;
                            i6 = i7;
                        } else if (smoothScroller.getTargetPosition() >= itemCount) {
                            smoothScroller.setTargetPosition(itemCount - 1);
                            smoothScroller.a(i2 - i3, i - i4);
                            itemCount = i4;
                            i7 = i5;
                            i5 = i6;
                            i6 = i7;
                        } else {
                            smoothScroller.a(i2 - i3, i - i4);
                        }
                    }
                    itemCount = i4;
                    i7 = i5;
                    i5 = i6;
                    i6 = i7;
                } else {
                    itemCount = 0;
                    i3 = 0;
                    i6 = 0;
                    i5 = 0;
                }
                if (!this.b.o.isEmpty()) {
                    this.b.invalidate();
                }
                if (this.b.getOverScrollMode() != 2) {
                    this.b.a(i2, i);
                }
                if (!(this.b.dispatchNestedScroll(i5, i6, i3, itemCount, null, 1) || (i3 == 0 && itemCount == 0))) {
                    int i8;
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    if (i3 != currX) {
                        i4 = i3 < 0 ? -currVelocity : i3 > 0 ? currVelocity : 0;
                        i8 = i4;
                    } else {
                        i8 = 0;
                    }
                    if (itemCount == currY) {
                        currVelocity = 0;
                    } else if (itemCount < 0) {
                        currVelocity = -currVelocity;
                    } else if (itemCount <= 0) {
                        currVelocity = 0;
                    }
                    if (this.b.getOverScrollMode() != 2) {
                        this.b.b(i8, currVelocity);
                    }
                    if ((i8 != 0 || i3 == currX || overScroller.getFinalX() == 0) && (currVelocity != 0 || itemCount == currY || overScroller.getFinalY() == 0)) {
                        overScroller.abortAnimation();
                    }
                }
                if (!(i5 == 0 && i6 == 0)) {
                    this.b.f(i5, i6);
                }
                if (!this.b.awakenScrollBars()) {
                    this.b.invalidate();
                }
                Object obj = (i != 0 && this.b.m.canScrollVertically() && i6 == i) ? 1 : null;
                Object obj2 = (i2 != 0 && this.b.m.canScrollHorizontally() && i5 == i2) ? 1 : null;
                obj2 = (!(i2 == 0 && i == 0) && obj2 == null && obj == null) ? null : 1;
                if (overScroller.isFinished() || (obj2 == null && !this.b.hasNestedScrollingParent(1))) {
                    this.b.setScrollState(0);
                    if (RecyclerView.K) {
                        this.b.A.a();
                    }
                    this.b.stopNestedScroll(1);
                } else {
                    a();
                    if (this.b.z != null) {
                        this.b.z.a(this.b, i2, i);
                    }
                }
            }
            if (smoothScroller != null) {
                if (smoothScroller.isPendingInitialRun()) {
                    smoothScroller.a(0, 0);
                }
                if (!this.g) {
                    smoothScroller.e();
                }
            }
            c();
        }

        private void b() {
            this.g = false;
            this.f = true;
        }

        private void c() {
            this.f = false;
            if (this.g) {
                a();
            }
        }

        void a() {
            if (this.f) {
                this.g = true;
                return;
            }
            this.b.removeCallbacks(this);
            ViewCompat.postOnAnimation(this.b, this);
        }

        public void fling(int i, int i2) {
            this.b.setScrollState(2);
            this.d = 0;
            this.c = 0;
            this.e.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            a();
        }

        public void smoothScrollBy(int i, int i2) {
            smoothScrollBy(i, i2, 0, 0);
        }

        public void smoothScrollBy(int i, int i2, int i3, int i4) {
            smoothScrollBy(i, i2, a(i, i2, i3, i4));
        }

        private float a(float f) {
            return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
        }

        private int a(int i, int i2, int i3, int i4) {
            int round;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            Object obj = abs > abs2 ? 1 : null;
            int sqrt = (int) Math.sqrt((double) ((i3 * i3) + (i4 * i4)));
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            int width = obj != null ? this.b.getWidth() : this.b.getHeight();
            int i5 = width / 2;
            float a = (a(Math.min(1.0f, (((float) sqrt2) * 1.0f) / ((float) width))) * ((float) i5)) + ((float) i5);
            if (sqrt > 0) {
                round = Math.round(1000.0f * Math.abs(a / ((float) sqrt))) * 4;
            } else {
                if (obj != null) {
                    round = abs;
                } else {
                    round = abs2;
                }
                round = (int) (((((float) round) / ((float) width)) + 1.0f) * 300.0f);
            }
            return Math.min(round, 2000);
        }

        public void smoothScrollBy(int i, int i2, int i3) {
            smoothScrollBy(i, i2, i3, RecyclerView.H);
        }

        public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
            int a = a(i, i2, 0, 0);
            if (interpolator == null) {
                interpolator = RecyclerView.H;
            }
            smoothScrollBy(i, i2, a, interpolator);
        }

        public void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
            if (this.a != interpolator) {
                this.a = interpolator;
                this.e = new OverScroller(this.b.getContext(), interpolator);
            }
            this.b.setScrollState(2);
            this.d = 0;
            this.c = 0;
            this.e.startScroll(0, 0, i, i2, i3);
            if (VERSION.SDK_INT < 23) {
                this.e.computeScrollOffset();
            }
            a();
        }

        public void stop() {
            this.b.removeCallbacks(this);
            this.e.abortAnimation();
        }
    }

    static {
        boolean z = VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20;
        a = z;
        if (VERSION.SDK_INT >= 23) {
            z = true;
        } else {
            z = false;
        }
        b = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        c = z;
        if (VERSION.SDK_INT >= 21) {
            z = true;
        } else {
            z = false;
        }
        K = z;
        if (VERSION.SDK_INT <= 15) {
            z = true;
        } else {
            z = false;
        }
        L = z;
        if (VERSION.SDK_INT <= 15) {
            z = true;
        } else {
            z = false;
        }
        M = z;
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        boolean z = true;
        super(context, attributeSet, i);
        this.O = new c(this);
        this.d = new Recycler(this);
        this.g = new dg();
        this.i = new bm(this);
        this.j = new Rect();
        this.Q = new Rect();
        this.k = new RectF();
        this.o = new ArrayList();
        this.R = new ArrayList();
        this.T = 0;
        this.w = false;
        this.ab = 0;
        this.ac = 0;
        this.x = new DefaultItemAnimator();
        this.ah = 0;
        this.ai = -1;
        this.as = Float.MIN_VALUE;
        this.at = Float.MIN_VALUE;
        this.au = true;
        this.y = new d(this);
        this.A = K ? new a() : null;
        this.B = new State();
        this.C = false;
        this.D = false;
        this.ax = new b(this);
        this.E = false;
        this.az = new int[2];
        this.aB = new int[2];
        this.aC = new int[2];
        this.aD = new int[2];
        this.G = new ArrayList();
        this.aE = new bn(this);
        this.aF = new bp(this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, J, i, 0);
            this.h = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        } else {
            this.h = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.ao = viewConfiguration.getScaledTouchSlop();
        this.as = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.at = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.aq = viewConfiguration.getScaledMinimumFlingVelocity();
        this.ar = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.x.a(this.ax);
        b();
        x();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.W = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, 0);
            String string = obtainStyledAttributes2.getString(R.styleable.RecyclerView_layoutManager);
            if (obtainStyledAttributes2.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(262144);
            }
            this.r = obtainStyledAttributes2.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
            if (this.r) {
                a((StateListDrawable) obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            obtainStyledAttributes2.recycle();
            a(context, string, attributeSet, i, 0);
            if (VERSION.SDK_INT >= 21) {
                obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, I, i, 0);
                z = obtainStyledAttributes.getBoolean(0, true);
                obtainStyledAttributes.recycle();
            }
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z);
    }

    String a() {
        return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + super.toString() + ", adapter:" + this.l + ", layout:" + this.m + ", context:" + getContext();
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.F;
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.F = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, this.F);
    }

    private void a(Context context, String str, AttributeSet attributeSet, int i, int i2) {
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                String a = a(context, trim);
                try {
                    ClassLoader classLoader;
                    Object[] objArr;
                    Constructor constructor;
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class asSubclass = classLoader.loadClass(a).asSubclass(LayoutManager.class);
                    try {
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)};
                        constructor = asSubclass.getConstructor(N);
                    } catch (Throwable e) {
                        constructor = asSubclass.getConstructor(new Class[0]);
                        objArr = null;
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (Throwable e2) {
                    e2.initCause(e);
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + a, e2);
                } catch (Throwable e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + a, e3);
                } catch (Throwable e32) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a, e32);
                } catch (Throwable e322) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a, e322);
                } catch (Throwable e3222) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + a, e3222);
                } catch (Throwable e32222) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + a, e32222);
                }
            }
        }
    }

    private String a(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        return !str.contains(".") ? RecyclerView.class.getPackage().getName() + '.' + str : str;
    }

    private void x() {
        this.f = new ab(new bq(this));
    }

    void b() {
        this.e = new o(new br(this));
    }

    public void setHasFixedSize(boolean z) {
        this.q = z;
    }

    public boolean hasFixedSize() {
        return this.q;
    }

    public void setClipToPadding(boolean z) {
        if (z != this.h) {
            j();
        }
        this.h = z;
        super.setClipToPadding(z);
        if (this.s) {
            requestLayout();
        }
    }

    public boolean getClipToPadding() {
        return this.h;
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        switch (i) {
            case 0:
                break;
            case 1:
                this.ao = viewConfiguration.getScaledPagingTouchSlop();
                return;
            default:
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
                break;
        }
        this.ao = viewConfiguration.getScaledTouchSlop();
    }

    public void swapAdapter(Adapter adapter, boolean z) {
        setLayoutFrozen(false);
        a(adapter, true, z);
        requestLayout();
    }

    public void setAdapter(Adapter adapter) {
        setLayoutFrozen(false);
        a(adapter, false, true);
        requestLayout();
    }

    void c() {
        if (this.x != null) {
            this.x.endAnimations();
        }
        if (this.m != null) {
            this.m.removeAndRecycleAllViews(this.d);
            this.m.a(this.d);
        }
        this.d.clear();
    }

    private void a(Adapter adapter, boolean z, boolean z2) {
        if (this.l != null) {
            this.l.unregisterAdapterDataObserver(this.O);
            this.l.onDetachedFromRecyclerView(this);
        }
        if (!z || z2) {
            c();
        }
        this.e.a();
        Adapter adapter2 = this.l;
        this.l = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.O);
            adapter.onAttachedToRecyclerView(this);
        }
        if (this.m != null) {
            this.m.onAdapterChanged(adapter2, this.l);
        }
        this.d.a(adapter2, this.l, z);
        this.B.e = true;
        s();
    }

    public Adapter getAdapter() {
        return this.l;
    }

    public void setRecyclerListener(RecyclerListener recyclerListener) {
        this.n = recyclerListener;
    }

    public int getBaseline() {
        if (this.m != null) {
            return this.m.getBaseline();
        }
        return super.getBaseline();
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.aa == null) {
            this.aa = new ArrayList();
        }
        this.aa.add(onChildAttachStateChangeListener);
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.aa != null) {
            this.aa.remove(onChildAttachStateChangeListener);
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        if (this.aa != null) {
            this.aa.clear();
        }
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager != this.m) {
            stopScroll();
            if (this.m != null) {
                if (this.x != null) {
                    this.x.endAnimations();
                }
                this.m.removeAndRecycleAllViews(this.d);
                this.m.a(this.d);
                this.d.clear();
                if (this.p) {
                    this.m.a(this, this.d);
                }
                this.m.a(null);
                this.m = null;
            } else {
                this.d.clear();
            }
            this.f.a();
            this.m = layoutManager;
            if (layoutManager != null) {
                if (layoutManager.q != null) {
                    throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.q.a());
                }
                this.m.a(this);
                if (this.p) {
                    this.m.b(this);
                }
            }
            this.d.a();
            requestLayout();
        }
    }

    public void setOnFlingListener(@Nullable OnFlingListener onFlingListener) {
        this.ap = onFlingListener;
    }

    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.ap;
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.P != null) {
            savedState.a(this.P);
        } else if (this.m != null) {
            savedState.a = this.m.onSaveInstanceState();
        } else {
            savedState.a = null;
        }
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.P = (SavedState) parcelable;
            super.onRestoreInstanceState(this.P.getSuperState());
            if (this.m != null && this.P.a != null) {
                this.m.onRestoreInstanceState(this.P.a);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void e(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z = view.getParent() == this;
        this.d.c(getChildViewHolder(view));
        if (viewHolder.n()) {
            this.f.a(view, -1, view.getLayoutParams(), true);
        } else if (z) {
            this.f.d(view);
        } else {
            this.f.a(view, true);
        }
    }

    boolean a(View view) {
        e();
        boolean f = this.f.f(view);
        if (f) {
            ViewHolder b = b(view);
            this.d.c(b);
            this.d.b(b);
        }
        a(!f);
        return f;
    }

    public LayoutManager getLayoutManager() {
        return this.m;
    }

    public RecycledViewPool getRecycledViewPool() {
        return this.d.e();
    }

    public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
        this.d.a(recycledViewPool);
    }

    public void setViewCacheExtension(ViewCacheExtension viewCacheExtension) {
        this.d.a(viewCacheExtension);
    }

    public void setItemViewCacheSize(int i) {
        this.d.setViewCacheSize(i);
    }

    public int getScrollState() {
        return this.ah;
    }

    void setScrollState(int i) {
        if (i != this.ah) {
            this.ah = i;
            if (i != 2) {
                z();
            }
            b(i);
        }
    }

    public void addItemDecoration(ItemDecoration itemDecoration, int i) {
        if (this.m != null) {
            this.m.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.o.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.o.add(itemDecoration);
        } else {
            this.o.add(i, itemDecoration);
        }
        p();
        requestLayout();
    }

    public void addItemDecoration(ItemDecoration itemDecoration) {
        addItemDecoration(itemDecoration, -1);
    }

    public ItemDecoration getItemDecorationAt(int i) {
        int itemDecorationCount = getItemDecorationCount();
        if (i >= 0 && i < itemDecorationCount) {
            return (ItemDecoration) this.o.get(i);
        }
        throw new IndexOutOfBoundsException(i + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.o.size();
    }

    public void removeItemDecorationAt(int i) {
        int itemDecorationCount = getItemDecorationCount();
        if (i < 0 || i >= itemDecorationCount) {
            throw new IndexOutOfBoundsException(i + " is an invalid index for size " + itemDecorationCount);
        }
        removeItemDecoration(getItemDecorationAt(i));
    }

    public void removeItemDecoration(ItemDecoration itemDecoration) {
        if (this.m != null) {
            this.m.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.o.remove(itemDecoration);
        if (this.o.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        p();
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback != this.ay) {
            this.ay = childDrawingOrderCallback;
            setChildrenDrawingOrderEnabled(this.ay != null);
        }
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.av = onScrollListener;
    }

    public void addOnScrollListener(OnScrollListener onScrollListener) {
        if (this.aw == null) {
            this.aw = new ArrayList();
        }
        this.aw.add(onScrollListener);
    }

    public void removeOnScrollListener(OnScrollListener onScrollListener) {
        if (this.aw != null) {
            this.aw.remove(onScrollListener);
        }
    }

    public void clearOnScrollListeners() {
        if (this.aw != null) {
            this.aw.clear();
        }
    }

    public void scrollToPosition(int i) {
        if (!this.u) {
            stopScroll();
            if (this.m == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            this.m.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    void a(int i) {
        if (this.m != null) {
            this.m.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    public void smoothScrollToPosition(int i) {
        if (!this.u) {
            if (this.m == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                this.m.smoothScrollToPosition(this, this.B, i);
            }
        }
    }

    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollBy(int i, int i2) {
        if (this.m == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.u) {
            boolean canScrollHorizontally = this.m.canScrollHorizontally();
            boolean canScrollVertically = this.m.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i = 0;
                }
                if (!canScrollVertically) {
                    i2 = 0;
                }
                a(i, i2, null);
            }
        }
    }

    void d() {
        if (!this.s || this.w) {
            TraceCompat.beginSection("RV FullInvalidate");
            o();
            TraceCompat.endSection();
        } else if (!this.e.d()) {
        } else {
            if (this.e.a(4) && !this.e.a(11)) {
                TraceCompat.beginSection("RV PartialInvalidate");
                e();
                k();
                this.e.b();
                if (!this.t) {
                    if (y()) {
                        o();
                    } else {
                        this.e.c();
                    }
                }
                a(true);
                l();
                TraceCompat.endSection();
            } else if (this.e.d()) {
                TraceCompat.beginSection("RV FullInvalidate");
                o();
                TraceCompat.endSection();
            }
        }
    }

    private boolean y() {
        int b = this.f.b();
        for (int i = 0; i < b; i++) {
            ViewHolder b2 = b(this.f.b(i));
            if (b2 != null && !b2.c() && b2.s()) {
                return true;
            }
        }
        return false;
    }

    boolean a(int i, int i2, MotionEvent motionEvent) {
        int scrollHorizontallyBy;
        int i3;
        int i4;
        int i5;
        d();
        if (this.l != null) {
            int scrollVerticallyBy;
            e();
            k();
            TraceCompat.beginSection("RV Scroll");
            a(this.B);
            if (i != 0) {
                scrollHorizontallyBy = this.m.scrollHorizontallyBy(i, this.d, this.B);
                i3 = i - scrollHorizontallyBy;
            } else {
                scrollHorizontallyBy = 0;
                i3 = 0;
            }
            if (i2 != 0) {
                scrollVerticallyBy = this.m.scrollVerticallyBy(i2, this.d, this.B);
                i4 = i2 - scrollVerticallyBy;
            } else {
                scrollVerticallyBy = 0;
                i4 = 0;
            }
            TraceCompat.endSection();
            u();
            l();
            a(false);
            i5 = i4;
            i4 = scrollHorizontallyBy;
            scrollHorizontallyBy = scrollVerticallyBy;
        } else {
            scrollHorizontallyBy = 0;
            i4 = 0;
            i5 = 0;
            i3 = 0;
        }
        if (!this.o.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(i4, scrollHorizontallyBy, i3, i5, this.aB, 0)) {
            this.am -= this.aB[0];
            this.an -= this.aB[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float) this.aB[0], (float) this.aB[1]);
            }
            int[] iArr = this.aD;
            iArr[0] = iArr[0] + this.aB[0];
            iArr = this.aD;
            iArr[1] = iArr[1] + this.aB[1];
        } else if (getOverScrollMode() != 2) {
            if (!(motionEvent == null || MotionEventCompat.isFromSource(motionEvent, 8194))) {
                a(motionEvent.getX(), (float) i3, motionEvent.getY(), (float) i5);
            }
            a(i, i2);
        }
        if (!(i4 == 0 && scrollHorizontallyBy == 0)) {
            f(i4, scrollHorizontallyBy);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        if (i4 == 0 && scrollHorizontallyBy == 0) {
            return false;
        }
        return true;
    }

    public int computeHorizontalScrollOffset() {
        if (this.m != null && this.m.canScrollHorizontally()) {
            return this.m.computeHorizontalScrollOffset(this.B);
        }
        return 0;
    }

    public int computeHorizontalScrollExtent() {
        if (this.m != null && this.m.canScrollHorizontally()) {
            return this.m.computeHorizontalScrollExtent(this.B);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        if (this.m != null && this.m.canScrollHorizontally()) {
            return this.m.computeHorizontalScrollRange(this.B);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        if (this.m != null && this.m.canScrollVertically()) {
            return this.m.computeVerticalScrollOffset(this.B);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        if (this.m != null && this.m.canScrollVertically()) {
            return this.m.computeVerticalScrollExtent(this.B);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        if (this.m != null && this.m.canScrollVertically()) {
            return this.m.computeVerticalScrollRange(this.B);
        }
        return 0;
    }

    void e() {
        this.T++;
        if (this.T == 1 && !this.u) {
            this.t = false;
        }
    }

    void a(boolean z) {
        if (this.T < 1) {
            this.T = 1;
        }
        if (!z) {
            this.t = false;
        }
        if (this.T == 1) {
            if (!(!z || !this.t || this.u || this.m == null || this.l == null)) {
                o();
            }
            if (!this.u) {
                this.t = false;
            }
        }
        this.T--;
    }

    public void setLayoutFrozen(boolean z) {
        if (z != this.u) {
            b("Do not setLayoutFrozen in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.u = true;
                this.U = true;
                stopScroll();
                return;
            }
            this.u = false;
            if (!(!this.t || this.m == null || this.l == null)) {
                requestLayout();
            }
            this.t = false;
        }
    }

    public boolean isLayoutFrozen() {
        return this.u;
    }

    public void smoothScrollBy(int i, int i2) {
        smoothScrollBy(i, i2, null);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
        int i3 = 0;
        if (this.m == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.u) {
            if (!this.m.canScrollHorizontally()) {
                i = 0;
            }
            if (this.m.canScrollVertically()) {
                i3 = i2;
            }
            if (i != 0 || i3 != 0) {
                this.y.smoothScrollBy(i, i3, interpolator);
            }
        }
    }

    public boolean fling(int i, int i2) {
        if (this.m == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.u) {
            return false;
        } else {
            boolean canScrollHorizontally = this.m.canScrollHorizontally();
            boolean canScrollVertically = this.m.canScrollVertically();
            if (!canScrollHorizontally || Math.abs(i) < this.aq) {
                i = 0;
            }
            if (!canScrollVertically || Math.abs(i2) < this.aq) {
                i2 = 0;
            }
            if ((i == 0 && i2 == 0) || dispatchNestedPreFling((float) i, (float) i2)) {
                return false;
            }
            boolean z;
            if (canScrollHorizontally || canScrollVertically) {
                z = true;
            } else {
                z = false;
            }
            dispatchNestedFling((float) i, (float) i2, z);
            if (this.ap != null && this.ap.onFling(i, i2)) {
                return true;
            }
            if (!z) {
                return false;
            }
            int i3;
            if (canScrollHorizontally) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            if (canScrollVertically) {
                i3 |= 2;
            }
            startNestedScroll(i3, 1);
            this.y.fling(Math.max(-this.ar, Math.min(i, this.ar)), Math.max(-this.ar, Math.min(i2, this.ar)));
            return true;
        }
    }

    public void stopScroll() {
        setScrollState(0);
        z();
    }

    private void z() {
        this.y.stop();
        if (this.m != null) {
            this.m.f();
        }
    }

    public int getMinFlingVelocity() {
        return this.aq;
    }

    public int getMaxFlingVelocity() {
        return this.ar;
    }

    private void a(float f, float f2, float f3, float f4) {
        Object obj = 1;
        Object obj2 = null;
        if (f2 < 0.0f) {
            f();
            EdgeEffectCompat.onPull(this.ad, (-f2) / ((float) getWidth()), 1.0f - (f3 / ((float) getHeight())));
            obj2 = 1;
        } else if (f2 > 0.0f) {
            g();
            EdgeEffectCompat.onPull(this.af, f2 / ((float) getWidth()), f3 / ((float) getHeight()));
            int i = 1;
        }
        if (f4 < 0.0f) {
            h();
            EdgeEffectCompat.onPull(this.ae, (-f4) / ((float) getHeight()), f / ((float) getWidth()));
        } else if (f4 > 0.0f) {
            i();
            EdgeEffectCompat.onPull(this.ag, f4 / ((float) getHeight()), 1.0f - (f / ((float) getWidth())));
        } else {
            obj = obj2;
        }
        if (obj != null || f2 != 0.0f || f4 != 0.0f) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void A() {
        int i = 0;
        if (this.ad != null) {
            this.ad.onRelease();
            i = this.ad.isFinished();
        }
        if (this.ae != null) {
            this.ae.onRelease();
            i |= this.ae.isFinished();
        }
        if (this.af != null) {
            this.af.onRelease();
            i |= this.af.isFinished();
        }
        if (this.ag != null) {
            this.ag.onRelease();
            i |= this.ag.isFinished();
        }
        if (i != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void a(int i, int i2) {
        int i3 = 0;
        if (!(this.ad == null || this.ad.isFinished() || i <= 0)) {
            this.ad.onRelease();
            i3 = this.ad.isFinished();
        }
        if (!(this.af == null || this.af.isFinished() || i >= 0)) {
            this.af.onRelease();
            i3 |= this.af.isFinished();
        }
        if (!(this.ae == null || this.ae.isFinished() || i2 <= 0)) {
            this.ae.onRelease();
            i3 |= this.ae.isFinished();
        }
        if (!(this.ag == null || this.ag.isFinished() || i2 >= 0)) {
            this.ag.onRelease();
            i3 |= this.ag.isFinished();
        }
        if (i3 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void b(int i, int i2) {
        if (i < 0) {
            f();
            this.ad.onAbsorb(-i);
        } else if (i > 0) {
            g();
            this.af.onAbsorb(i);
        }
        if (i2 < 0) {
            h();
            this.ae.onAbsorb(-i2);
        } else if (i2 > 0) {
            i();
            this.ag.onAbsorb(i2);
        }
        if (i != 0 || i2 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void f() {
        if (this.ad == null) {
            this.ad = new EdgeEffect(getContext());
            if (this.h) {
                this.ad.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.ad.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void g() {
        if (this.af == null) {
            this.af = new EdgeEffect(getContext());
            if (this.h) {
                this.af.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.af.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void h() {
        if (this.ae == null) {
            this.ae = new EdgeEffect(getContext());
            if (this.h) {
                this.ae.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.ae.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void i() {
        if (this.ag == null) {
            this.ag = new EdgeEffect(getContext());
            if (this.h) {
                this.ag.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.ag.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void j() {
        this.ag = null;
        this.ae = null;
        this.af = null;
        this.ad = null;
    }

    public View focusSearch(View view, int i) {
        boolean z = true;
        View onInterceptFocusSearch = this.m.onInterceptFocusSearch(view, i);
        if (onInterceptFocusSearch != null) {
            return onInterceptFocusSearch;
        }
        boolean z2 = (this.l == null || this.m == null || isComputingLayout() || this.u) ? false : true;
        FocusFinder instance = FocusFinder.getInstance();
        if (z2 && (i == 2 || i == 1)) {
            int i2;
            if (this.m.canScrollVertically()) {
                boolean z3;
                i2 = i == 2 ? 130 : 33;
                if (instance.findNextFocus(this, view, i2) == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (L) {
                    i = i2;
                    z2 = z3;
                } else {
                    z2 = z3;
                }
            } else {
                z2 = false;
            }
            if (z2 || !this.m.canScrollHorizontally()) {
                z = z2;
            } else {
                int i3;
                if (this.m.getLayoutDirection() == 1) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                if (i == 2) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                i2 = (i2 ^ i3) != 0 ? 66 : 17;
                if (instance.findNextFocus(this, view, i2) != null) {
                    z = false;
                }
                if (L) {
                    i = i2;
                }
            }
            if (z) {
                d();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                e();
                this.m.onFocusSearchFailed(view, i, this.d, this.B);
                a(false);
            }
            onInterceptFocusSearch = instance.findNextFocus(this, view, i);
        } else {
            View findNextFocus = instance.findNextFocus(this, view, i);
            if (findNextFocus == null && z2) {
                d();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                e();
                onInterceptFocusSearch = this.m.onFocusSearchFailed(view, i, this.d, this.B);
                a(false);
            } else {
                onInterceptFocusSearch = findNextFocus;
            }
        }
        if (onInterceptFocusSearch == null || onInterceptFocusSearch.hasFocusable()) {
            if (!a(view, onInterceptFocusSearch, i)) {
                onInterceptFocusSearch = super.focusSearch(view, i);
            }
            return onInterceptFocusSearch;
        } else if (getFocusedChild() == null) {
            return super.focusSearch(view, i);
        } else {
            a(onInterceptFocusSearch, null);
            return view;
        }
    }

    private boolean a(View view, View view2, int i) {
        boolean z = true;
        boolean z2 = false;
        if (view2 == null || view2 == this) {
            return false;
        }
        if (view == null) {
            return true;
        }
        int i2;
        this.j.set(0, 0, view.getWidth(), view.getHeight());
        this.Q.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.j);
        offsetDescendantRectToMyCoords(view2, this.Q);
        int i3 = this.m.getLayoutDirection() == 1 ? -1 : 1;
        if ((this.j.left < this.Q.left || this.j.right <= this.Q.left) && this.j.right < this.Q.right) {
            i2 = 1;
        } else if ((this.j.right > this.Q.right || this.j.left >= this.Q.right) && this.j.left > this.Q.left) {
            i2 = -1;
        } else {
            i2 = 0;
        }
        if ((this.j.top < this.Q.top || this.j.bottom <= this.Q.top) && this.j.bottom < this.Q.bottom) {
            z = true;
        } else if ((this.j.bottom <= this.Q.bottom && this.j.top < this.Q.bottom) || this.j.top <= this.Q.top) {
            z = false;
        }
        switch (i) {
            case 1:
                if (z >= false || (!z && i3 * i2 <= 0)) {
                    z2 = true;
                }
                return z2;
            case 2:
                if (z <= false || (!z && i3 * i2 >= 0)) {
                    z2 = true;
                }
                return z2;
            case 17:
                if (i2 >= 0) {
                    return false;
                }
                return true;
            case 33:
                if (z < false) {
                    return false;
                }
                return true;
            case 66:
                if (i2 <= 0) {
                    return false;
                }
                return true;
            case 130:
                if (z > false) {
                    return false;
                }
                return true;
            default:
                throw new IllegalArgumentException("Invalid direction: " + i + a());
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!(this.m.onRequestChildFocus(this, this.B, view, view2) || view2 == null)) {
            a(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    private void a(@NonNull View view, @Nullable View view2) {
        boolean z = true;
        View view3 = view2 != null ? view2 : view;
        this.j.set(0, 0, view3.getWidth(), view3.getHeight());
        android.view.ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.e) {
                Rect rect = layoutParams2.d;
                Rect rect2 = this.j;
                rect2.left -= rect.left;
                rect2 = this.j;
                rect2.right += rect.right;
                rect2 = this.j;
                rect2.top -= rect.top;
                rect2 = this.j;
                rect2.bottom = rect.bottom + rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.j);
            offsetRectIntoDescendantCoords(view, this.j);
        }
        LayoutManager layoutManager = this.m;
        Rect rect3 = this.j;
        boolean z2 = !this.s;
        if (view2 != null) {
            z = false;
        }
        layoutManager.requestChildRectangleOnScreen(this, view, rect3, z2, z);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.m.requestChildRectangleOnScreen(this, view, rect, z);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (this.m == null || !this.m.onAddFocusables(this, arrayList, i, i2)) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onAttachedToWindow() {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        super.onAttachedToWindow();
        r4.ab = r1;
        r4.p = r0;
        r2 = r4.s;
        if (r2 == 0) goto L_0x0068;
    L_0x000d:
        r2 = r4.isLayoutRequested();
        if (r2 != 0) goto L_0x0068;
    L_0x0013:
        r4.s = r0;
        r0 = r4.m;
        if (r0 == 0) goto L_0x001e;
    L_0x0019:
        r0 = r4.m;
        r0.b(r4);
    L_0x001e:
        r4.E = r1;
        r0 = K;
        if (r0 == 0) goto L_0x0067;
    L_0x0024:
        r0 = android.support.v7.widget.ao.a;
        r0 = r0.get();
        r0 = (android.support.v7.widget.ao) r0;
        r4.z = r0;
        r0 = r4.z;
        if (r0 != 0) goto L_0x0062;
    L_0x0032:
        r0 = new android.support.v7.widget.ao;
        r0.<init>();
        r4.z = r0;
        r0 = android.support.v4.view.ViewCompat.getDisplay(r4);
        r1 = 1114636288; // 0x42700000 float:60.0 double:5.507034975E-315;
        r2 = r4.isInEditMode();
        if (r2 != 0) goto L_0x006a;
    L_0x0045:
        if (r0 == 0) goto L_0x006a;
    L_0x0047:
        r0 = r0.getRefreshRate();
        r2 = 1106247680; // 0x41f00000 float:30.0 double:5.465589745E-315;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x006a;
    L_0x0051:
        r1 = r4.z;
        r2 = 1315859240; // 0x4e6e6b28 float:1.0E9 double:6.50120845E-315;
        r0 = r2 / r0;
        r2 = (long) r0;
        r1.d = r2;
        r0 = android.support.v7.widget.ao.a;
        r1 = r4.z;
        r0.set(r1);
    L_0x0062:
        r0 = r4.z;
        r0.add(r4);
    L_0x0067:
        return;
    L_0x0068:
        r0 = r1;
        goto L_0x0013;
    L_0x006a:
        r0 = r1;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onAttachedToWindow():void");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.x != null) {
            this.x.endAnimations();
        }
        stopScroll();
        this.p = false;
        if (this.m != null) {
            this.m.a(this, this.d);
        }
        this.G.clear();
        removeCallbacks(this.aE);
        this.g.b();
        if (K) {
            this.z.remove(this);
            this.z = null;
        }
    }

    public boolean isAttachedToWindow() {
        return this.p;
    }

    void a(String str) {
        if (!isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + a());
            }
            throw new IllegalStateException(str + a());
        }
    }

    void b(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + a());
            }
            throw new IllegalStateException(str);
        } else if (this.ac > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + a()));
        }
    }

    public void addOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.R.add(onItemTouchListener);
    }

    public void removeOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.R.remove(onItemTouchListener);
        if (this.S == onItemTouchListener) {
            this.S = null;
        }
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.S = null;
        }
        int size = this.R.size();
        int i = 0;
        while (i < size) {
            OnItemTouchListener onItemTouchListener = (OnItemTouchListener) this.R.get(i);
            if (!onItemTouchListener.onInterceptTouchEvent(this, motionEvent) || action == 3) {
                i++;
            } else {
                this.S = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private boolean b(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.S != null) {
            if (action == 0) {
                this.S = null;
            } else {
                this.S.onTouchEvent(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.S = null;
                }
                return true;
            }
        }
        if (action != 0) {
            int size = this.R.size();
            for (int i = 0; i < size; i++) {
                OnItemTouchListener onItemTouchListener = (OnItemTouchListener) this.R.get(i);
                if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent)) {
                    this.S = onItemTouchListener;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.u) {
            return false;
        }
        if (a(motionEvent)) {
            C();
            return true;
        } else if (this.m == null) {
            return false;
        } else {
            boolean canScrollHorizontally = this.m.canScrollHorizontally();
            boolean canScrollVertically = this.m.canScrollVertically();
            if (this.aj == null) {
                this.aj = VelocityTracker.obtain();
            }
            this.aj.addMovement(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            int actionIndex = motionEvent.getActionIndex();
            int i;
            switch (actionMasked) {
                case 0:
                    if (this.U) {
                        this.U = false;
                    }
                    this.ai = motionEvent.getPointerId(0);
                    actionMasked = (int) (motionEvent.getX() + 0.5f);
                    this.am = actionMasked;
                    this.ak = actionMasked;
                    actionMasked = (int) (motionEvent.getY() + 0.5f);
                    this.an = actionMasked;
                    this.al = actionMasked;
                    if (this.ah == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                    }
                    int[] iArr = this.aD;
                    this.aD[1] = 0;
                    iArr[0] = 0;
                    if (canScrollHorizontally) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (canScrollVertically) {
                        i |= 2;
                    }
                    startNestedScroll(i, 0);
                    break;
                case 1:
                    this.aj.clear();
                    stopNestedScroll(0);
                    break;
                case 2:
                    actionMasked = motionEvent.findPointerIndex(this.ai);
                    if (actionMasked >= 0) {
                        actionIndex = (int) (motionEvent.getX(actionMasked) + 0.5f);
                        actionMasked = (int) (motionEvent.getY(actionMasked) + 0.5f);
                        if (this.ah != 1) {
                            int i2 = actionIndex - this.ak;
                            int i3 = actionMasked - this.al;
                            if (!canScrollHorizontally || Math.abs(i2) <= this.ao) {
                                canScrollHorizontally = false;
                            } else {
                                this.am = actionIndex;
                                canScrollHorizontally = true;
                            }
                            if (canScrollVertically && Math.abs(i3) > this.ao) {
                                this.an = actionMasked;
                                canScrollHorizontally = true;
                            }
                            if (canScrollHorizontally) {
                                setScrollState(1);
                                break;
                            }
                        }
                    }
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.ai + " not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case 3:
                    C();
                    break;
                case 5:
                    this.ai = motionEvent.getPointerId(actionIndex);
                    i = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.am = i;
                    this.ak = i;
                    i = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.an = i;
                    this.al = i;
                    break;
                case 6:
                    c(motionEvent);
                    break;
            }
            if (this.ah == 1) {
                canScrollHorizontally = true;
            } else {
                canScrollHorizontally = false;
            }
            return canScrollHorizontally;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.R.size();
        for (int i = 0; i < size; i++) {
            ((OnItemTouchListener) this.R.get(i)).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.u || this.U) {
            return false;
        }
        if (b(motionEvent)) {
            C();
            return true;
        } else if (this.m == null) {
            return false;
        } else {
            boolean canScrollHorizontally = this.m.canScrollHorizontally();
            boolean canScrollVertically = this.m.canScrollVertically();
            if (this.aj == null) {
                this.aj = VelocityTracker.obtain();
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            int actionIndex = motionEvent.getActionIndex();
            if (actionMasked == 0) {
                int[] iArr = this.aD;
                this.aD[1] = 0;
                iArr[0] = 0;
            }
            obtain.offsetLocation((float) this.aD[0], (float) this.aD[1]);
            switch (actionMasked) {
                case 0:
                    this.ai = motionEvent.getPointerId(0);
                    actionMasked = (int) (motionEvent.getX() + 0.5f);
                    this.am = actionMasked;
                    this.ak = actionMasked;
                    actionMasked = (int) (motionEvent.getY() + 0.5f);
                    this.an = actionMasked;
                    this.al = actionMasked;
                    if (canScrollHorizontally) {
                        actionMasked = 1;
                    } else {
                        actionMasked = 0;
                    }
                    if (canScrollVertically) {
                        actionMasked |= 2;
                    }
                    startNestedScroll(actionMasked, 0);
                    break;
                case 1:
                    this.aj.addMovement(obtain);
                    this.aj.computeCurrentVelocity(1000, (float) this.ar);
                    float f = canScrollHorizontally ? -this.aj.getXVelocity(this.ai) : 0.0f;
                    float f2;
                    if (canScrollVertically) {
                        f2 = -this.aj.getYVelocity(this.ai);
                    } else {
                        f2 = 0.0f;
                    }
                    if ((f == 0.0f && r0 == 0.0f) || !fling((int) f, (int) r0)) {
                        setScrollState(0);
                    }
                    B();
                    z = true;
                    break;
                case 2:
                    actionMasked = motionEvent.findPointerIndex(this.ai);
                    if (actionMasked >= 0) {
                        int x = (int) (motionEvent.getX(actionMasked) + 0.5f);
                        int y = (int) (motionEvent.getY(actionMasked) + 0.5f);
                        int i = this.am - x;
                        actionIndex = this.an - y;
                        if (dispatchNestedPreScroll(i, actionIndex, this.aC, this.aB, 0)) {
                            i -= this.aC[0];
                            actionIndex -= this.aC[1];
                            obtain.offsetLocation((float) this.aB[0], (float) this.aB[1]);
                            int[] iArr2 = this.aD;
                            iArr2[0] = iArr2[0] + this.aB[0];
                            iArr2 = this.aD;
                            iArr2[1] = iArr2[1] + this.aB[1];
                        }
                        if (this.ah != 1) {
                            boolean z2;
                            if (!canScrollHorizontally || Math.abs(i) <= this.ao) {
                                z2 = false;
                            } else {
                                if (i > 0) {
                                    actionMasked = i - this.ao;
                                } else {
                                    actionMasked = this.ao + i;
                                }
                                i = actionMasked;
                                z2 = true;
                            }
                            if (canScrollVertically && Math.abs(actionIndex) > this.ao) {
                                if (actionIndex > 0) {
                                    actionMasked = actionIndex - this.ao;
                                } else {
                                    actionMasked = this.ao + actionIndex;
                                }
                                actionIndex = actionMasked;
                                z2 = true;
                            }
                            if (z2) {
                                setScrollState(1);
                            }
                        }
                        if (this.ah == 1) {
                            this.am = x - this.aB[0];
                            this.an = y - this.aB[1];
                            int i2 = canScrollHorizontally ? i : 0;
                            if (canScrollVertically) {
                                actionMasked = actionIndex;
                            } else {
                                actionMasked = 0;
                            }
                            if (a(i2, actionMasked, obtain)) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            if (!(this.z == null || (i == 0 && actionIndex == 0))) {
                                this.z.a(this, i, actionIndex);
                                break;
                            }
                        }
                    }
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.ai + " not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case 3:
                    C();
                    break;
                case 5:
                    this.ai = motionEvent.getPointerId(actionIndex);
                    actionMasked = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.am = actionMasked;
                    this.ak = actionMasked;
                    actionMasked = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.an = actionMasked;
                    this.al = actionMasked;
                    break;
                case 6:
                    c(motionEvent);
                    break;
            }
            if (!z) {
                this.aj.addMovement(obtain);
            }
            obtain.recycle();
            return true;
        }
    }

    private void B() {
        if (this.aj != null) {
            this.aj.clear();
        }
        stopNestedScroll(0);
        A();
    }

    private void C() {
        B();
        setScrollState(0);
    }

    private void c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.ai) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.ai = motionEvent.getPointerId(actionIndex);
            int x = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.am = x;
            this.ak = x;
            actionIndex = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.an = actionIndex;
            this.al = actionIndex;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!(this.m == null || this.u || motionEvent.getAction() != 8)) {
            float f;
            float f2;
            if ((motionEvent.getSource() & 2) != 0) {
                if (this.m.canScrollVertically()) {
                    f = -motionEvent.getAxisValue(9);
                } else {
                    f = 0.0f;
                }
                if (this.m.canScrollHorizontally()) {
                    f2 = f;
                    f = motionEvent.getAxisValue(10);
                } else {
                    f2 = f;
                    f = 0.0f;
                }
            } else if ((motionEvent.getSource() & 4194304) != 0) {
                f = motionEvent.getAxisValue(26);
                if (this.m.canScrollVertically()) {
                    f2 = -f;
                    f = 0.0f;
                } else if (this.m.canScrollHorizontally()) {
                    f2 = 0.0f;
                } else {
                    f = 0.0f;
                    f2 = 0.0f;
                }
            } else {
                f = 0.0f;
                f2 = 0.0f;
            }
            if (!(f2 == 0.0f && f == 0.0f)) {
                a((int) (f * this.as), (int) (this.at * f2), motionEvent);
            }
        }
        return false;
    }

    protected void onMeasure(int i, int i2) {
        boolean z = false;
        if (this.m == null) {
            c(i, i2);
        } else if (this.m.w) {
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.m.onMeasure(this.d, this.B, i, i2);
            if (!z && this.l != null) {
                if (this.B.c == 1) {
                    K();
                }
                this.m.c(i, i2);
                this.B.h = true;
                L();
                this.m.d(i, i2);
                if (this.m.e()) {
                    this.m.c(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.B.h = true;
                    L();
                    this.m.d(i, i2);
                }
            }
        } else if (this.q) {
            this.m.onMeasure(this.d, this.B, i, i2);
        } else {
            if (this.v) {
                e();
                k();
                F();
                l();
                if (this.B.j) {
                    this.B.f = true;
                } else {
                    this.e.e();
                    this.B.f = false;
                }
                this.v = false;
                a(false);
            } else if (this.B.j) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            if (this.l != null) {
                this.B.d = this.l.getItemCount();
            } else {
                this.B.d = 0;
            }
            e();
            this.m.onMeasure(this.d, this.B, i, i2);
            a(false);
            this.B.f = false;
        }
    }

    void c(int i, int i2) {
        setMeasuredDimension(LayoutManager.chooseSize(i, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(i2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            j();
        }
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        if (this.x != null) {
            this.x.endAnimations();
            this.x.a(null);
        }
        this.x = itemAnimator;
        if (this.x != null) {
            this.x.a(this.ax);
        }
    }

    void k() {
        this.ab++;
    }

    void l() {
        b(true);
    }

    void b(boolean z) {
        this.ab--;
        if (this.ab < 1) {
            this.ab = 0;
            if (z) {
                D();
                v();
            }
        }
    }

    boolean m() {
        return this.W != null && this.W.isEnabled();
    }

    private void D() {
        int i = this.V;
        this.V = 0;
        if (i != 0 && m()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, i);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    public boolean isComputingLayout() {
        return this.ab > 0;
    }

    boolean a(AccessibilityEvent accessibilityEvent) {
        int i = 0;
        if (!isComputingLayout()) {
            return false;
        }
        int contentChangeTypes;
        if (accessibilityEvent != null) {
            contentChangeTypes = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
        } else {
            contentChangeTypes = 0;
        }
        if (contentChangeTypes != 0) {
            i = contentChangeTypes;
        }
        this.V = i | this.V;
        return true;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!a(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public ItemAnimator getItemAnimator() {
        return this.x;
    }

    void n() {
        if (!this.E && this.p) {
            ViewCompat.postOnAnimation(this, this.aE);
            this.E = true;
        }
    }

    private boolean E() {
        return this.x != null && this.m.supportsPredictiveItemAnimations();
    }

    private void F() {
        boolean z;
        boolean z2 = true;
        if (this.w) {
            this.e.a();
            this.m.onItemsChanged(this);
        }
        if (E()) {
            this.e.b();
        } else {
            this.e.e();
        }
        boolean z3;
        if (this.C || this.D) {
            z3 = true;
        } else {
            z3 = false;
        }
        State state = this.B;
        if (!this.s || this.x == null || (!(this.w || r0 || this.m.u) || (this.w && !this.l.hasStableIds()))) {
            z = false;
        } else {
            z = true;
        }
        state.i = z;
        State state2 = this.B;
        if (!(this.B.i && r0 && !this.w && E())) {
            z2 = false;
        }
        state2.j = z2;
    }

    void o() {
        if (this.l == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        } else if (this.m == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {
            this.B.h = false;
            if (this.B.c == 1) {
                K();
                this.m.c(this);
                L();
            } else if (!this.e.f() && this.m.getWidth() == getWidth() && this.m.getHeight() == getHeight()) {
                this.m.c(this);
            } else {
                this.m.c(this);
                L();
            }
            M();
        }
    }

    private void G() {
        View focusedChild;
        if (this.au && hasFocus() && this.l != null) {
            focusedChild = getFocusedChild();
        } else {
            focusedChild = null;
        }
        ViewHolder findContainingViewHolder = focusedChild == null ? null : findContainingViewHolder(focusedChild);
        if (findContainingViewHolder == null) {
            H();
            return;
        }
        int i;
        this.B.l = this.l.hasStableIds() ? findContainingViewHolder.getItemId() : -1;
        State state = this.B;
        if (this.w) {
            i = -1;
        } else if (findContainingViewHolder.m()) {
            i = findContainingViewHolder.c;
        } else {
            i = findContainingViewHolder.getAdapterPosition();
        }
        state.k = i;
        this.B.m = g(findContainingViewHolder.itemView);
    }

    private void H() {
        this.B.l = -1;
        this.B.k = -1;
        this.B.m = -1;
    }

    @Nullable
    private View I() {
        int i = this.B.k != -1 ? this.B.k : 0;
        int itemCount = this.B.getItemCount();
        int i2 = i;
        while (i2 < itemCount) {
            ViewHolder findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(i2);
            if (findViewHolderForAdapterPosition == null) {
                break;
            } else if (findViewHolderForAdapterPosition.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition.itemView;
            } else {
                i2++;
            }
        }
        for (i = Math.min(itemCount, i) - 1; i >= 0; i--) {
            ViewHolder findViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i);
            if (findViewHolderForAdapterPosition2 == null) {
                return null;
            }
            if (findViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition2.itemView;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void J() {
        /*
        r6 = this;
        r4 = -1;
        r1 = 0;
        r0 = r6.au;
        if (r0 == 0) goto L_0x0027;
    L_0x0007:
        r0 = r6.l;
        if (r0 == 0) goto L_0x0027;
    L_0x000b:
        r0 = r6.hasFocus();
        if (r0 == 0) goto L_0x0027;
    L_0x0011:
        r0 = r6.getDescendantFocusability();
        r2 = 393216; // 0x60000 float:5.51013E-40 double:1.942745E-318;
        if (r0 == r2) goto L_0x0027;
    L_0x0019:
        r0 = r6.getDescendantFocusability();
        r2 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        if (r0 != r2) goto L_0x0028;
    L_0x0021:
        r0 = r6.isFocused();
        if (r0 == 0) goto L_0x0028;
    L_0x0027:
        return;
    L_0x0028:
        r0 = r6.isFocused();
        if (r0 != 0) goto L_0x0056;
    L_0x002e:
        r0 = r6.getFocusedChild();
        r2 = M;
        if (r2 == 0) goto L_0x004e;
    L_0x0036:
        r2 = r0.getParent();
        if (r2 == 0) goto L_0x0042;
    L_0x003c:
        r2 = r0.hasFocus();
        if (r2 != 0) goto L_0x004e;
    L_0x0042:
        r0 = r6.f;
        r0 = r0.b();
        if (r0 != 0) goto L_0x0056;
    L_0x004a:
        r6.requestFocus();
        goto L_0x0027;
    L_0x004e:
        r2 = r6.f;
        r0 = r2.c(r0);
        if (r0 == 0) goto L_0x0027;
    L_0x0056:
        r0 = r6.B;
        r2 = r0.l;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x00b3;
    L_0x005e:
        r0 = r6.l;
        r0 = r0.hasStableIds();
        if (r0 == 0) goto L_0x00b3;
    L_0x0066:
        r0 = r6.B;
        r2 = r0.l;
        r0 = r6.findViewHolderForItemId(r2);
    L_0x006e:
        if (r0 == 0) goto L_0x0082;
    L_0x0070:
        r2 = r6.f;
        r3 = r0.itemView;
        r2 = r2.c(r3);
        if (r2 != 0) goto L_0x0082;
    L_0x007a:
        r2 = r0.itemView;
        r2 = r2.hasFocusable();
        if (r2 != 0) goto L_0x00ae;
    L_0x0082:
        r0 = r6.f;
        r0 = r0.b();
        if (r0 <= 0) goto L_0x008e;
    L_0x008a:
        r1 = r6.I();
    L_0x008e:
        if (r1 == 0) goto L_0x0027;
    L_0x0090:
        r0 = r6.B;
        r0 = r0.m;
        r2 = (long) r0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x00b1;
    L_0x0099:
        r0 = r6.B;
        r0 = r0.m;
        r0 = r1.findViewById(r0);
        if (r0 == 0) goto L_0x00b1;
    L_0x00a3:
        r2 = r0.isFocusable();
        if (r2 == 0) goto L_0x00b1;
    L_0x00a9:
        r0.requestFocus();
        goto L_0x0027;
    L_0x00ae:
        r1 = r0.itemView;
        goto L_0x008e;
    L_0x00b1:
        r0 = r1;
        goto L_0x00a9;
    L_0x00b3:
        r0 = r1;
        goto L_0x006e;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.J():void");
    }

    private int g(View view) {
        int id = view.getId();
        View view2 = view;
        while (!view2.isFocused() && (view2 instanceof ViewGroup) && view2.hasFocus()) {
            int id2;
            view = ((ViewGroup) view2).getFocusedChild();
            if (view.getId() != -1) {
                id2 = view.getId();
            } else {
                id2 = id;
            }
            id = id2;
            view2 = view;
        }
        return id;
    }

    final void a(State state) {
        if (getScrollState() == 2) {
            OverScroller a = this.y.e;
            state.n = a.getFinalX() - a.getCurrX();
            state.o = a.getFinalY() - a.getCurrY();
            return;
        }
        state.n = 0;
        state.o = 0;
    }

    private void K() {
        int b;
        int i;
        ViewHolder b2;
        boolean z = true;
        this.B.a(1);
        a(this.B);
        this.B.h = false;
        e();
        this.g.a();
        k();
        F();
        G();
        State state = this.B;
        if (!(this.B.i && this.D)) {
            z = false;
        }
        state.g = z;
        this.D = false;
        this.C = false;
        this.B.f = this.B.j;
        this.B.d = this.l.getItemCount();
        a(this.az);
        if (this.B.i) {
            b = this.f.b();
            for (i = 0; i < b; i++) {
                b2 = b(this.f.b(i));
                if (!b2.c() && (!b2.j() || this.l.hasStableIds())) {
                    this.g.a(b2, this.x.recordPreLayoutInformation(this.B, b2, ItemAnimator.b(b2), b2.q()));
                    if (!(!this.B.g || !b2.s() || b2.m() || b2.c() || b2.j())) {
                        this.g.a(a(b2), b2);
                    }
                }
            }
        }
        if (this.B.j) {
            q();
            z = this.B.e;
            this.B.e = false;
            this.m.onLayoutChildren(this.d, this.B);
            this.B.e = z;
            for (i = 0; i < this.f.b(); i++) {
                b2 = b(this.f.b(i));
                if (!(b2.c() || this.g.d(b2))) {
                    b = ItemAnimator.b(b2);
                    boolean a = b2.a(8192);
                    if (!a) {
                        b |= 4096;
                    }
                    ItemHolderInfo recordPreLayoutInformation = this.x.recordPreLayoutInformation(this.B, b2, b, b2.q());
                    if (a) {
                        a(b2, recordPreLayoutInformation);
                    } else {
                        this.g.b(b2, recordPreLayoutInformation);
                    }
                }
            }
            r();
        } else {
            r();
        }
        l();
        a(false);
        this.B.c = 2;
    }

    private void L() {
        boolean z;
        e();
        k();
        this.B.a(6);
        this.e.e();
        this.B.d = this.l.getItemCount();
        this.B.b = 0;
        this.B.f = false;
        this.m.onLayoutChildren(this.d, this.B);
        this.B.e = false;
        this.P = null;
        State state = this.B;
        if (!this.B.i || this.x == null) {
            z = false;
        } else {
            z = true;
        }
        state.i = z;
        this.B.c = 4;
        l();
        a(false);
    }

    private void M() {
        this.B.a(4);
        e();
        k();
        this.B.c = 1;
        if (this.B.i) {
            for (int b = this.f.b() - 1; b >= 0; b--) {
                ViewHolder b2 = b(this.f.b(b));
                if (!b2.c()) {
                    long a = a(b2);
                    ItemHolderInfo recordPostLayoutInformation = this.x.recordPostLayoutInformation(this.B, b2);
                    ViewHolder a2 = this.g.a(a);
                    if (a2 == null || a2.c()) {
                        this.g.c(b2, recordPostLayoutInformation);
                    } else {
                        boolean a3 = this.g.a(a2);
                        boolean a4 = this.g.a(b2);
                        if (a3 && a2 == b2) {
                            this.g.c(b2, recordPostLayoutInformation);
                        } else {
                            ItemHolderInfo b3 = this.g.b(a2);
                            this.g.c(b2, recordPostLayoutInformation);
                            ItemHolderInfo c = this.g.c(b2);
                            if (b3 == null) {
                                a(a, b2, a2);
                            } else {
                                a(a2, b2, b3, c, a3, a4);
                            }
                        }
                    }
                }
            }
            this.g.a(this.aF);
        }
        this.m.a(this.d);
        this.B.a = this.B.d;
        this.w = false;
        this.B.i = false;
        this.B.j = false;
        this.m.u = false;
        if (this.d.b != null) {
            this.d.b.clear();
        }
        if (this.m.y) {
            this.m.x = 0;
            this.m.y = false;
            this.d.a();
        }
        this.m.onLayoutCompleted(this.B);
        l();
        a(false);
        this.g.a();
        if (g(this.az[0], this.az[1])) {
            f(0, 0);
        }
        J();
        H();
    }

    private void a(long j, ViewHolder viewHolder, ViewHolder viewHolder2) {
        int b = this.f.b();
        int i = 0;
        while (i < b) {
            ViewHolder b2 = b(this.f.b(i));
            if (b2 == viewHolder || a(b2) != j) {
                i++;
            } else if (this.l == null || !this.l.hasStableIds()) {
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + b2 + " \n View Holder 2:" + viewHolder + a());
            } else {
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + b2 + " \n View Holder 2:" + viewHolder + a());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + viewHolder2 + " cannot be found but it is necessary for " + viewHolder + a());
    }

    void a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        viewHolder.a(0, 8192);
        if (this.B.g && viewHolder.s() && !viewHolder.m() && !viewHolder.c()) {
            this.g.a(a(viewHolder), viewHolder);
        }
        this.g.a(viewHolder, itemHolderInfo);
    }

    private void a(int[] iArr) {
        int b = this.f.b();
        if (b == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = 0;
        while (i3 < b) {
            int i4;
            ViewHolder b2 = b(this.f.b(i3));
            if (b2.c()) {
                i4 = i;
            } else {
                i4 = b2.getLayoutPosition();
                if (i4 < i) {
                    i = i4;
                }
                if (i4 > i2) {
                    i2 = i4;
                    i4 = i;
                } else {
                    i4 = i;
                }
            }
            i3++;
            i = i4;
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    private boolean g(int i, int i2) {
        a(this.az);
        if (this.az[0] == i && this.az[1] == i2) {
            return false;
        }
        return true;
    }

    protected void removeDetachedView(View view, boolean z) {
        ViewHolder b = b(view);
        if (b != null) {
            if (b.n()) {
                b.h();
            } else if (!b.c()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + b + a());
            }
        }
        view.clearAnimation();
        e(view);
        super.removeDetachedView(view, z);
    }

    long a(ViewHolder viewHolder) {
        return this.l.hasStableIds() ? viewHolder.getItemId() : (long) viewHolder.b;
    }

    void a(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.x.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            n();
        }
    }

    void b(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2) {
        e(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.x.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            n();
        }
    }

    private void a(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2, boolean z, boolean z2) {
        viewHolder.setIsRecyclable(false);
        if (z) {
            e(viewHolder);
        }
        if (viewHolder != viewHolder2) {
            if (z2) {
                e(viewHolder2);
            }
            viewHolder.g = viewHolder2;
            e(viewHolder);
            this.d.c(viewHolder);
            viewHolder2.setIsRecyclable(false);
            viewHolder2.h = viewHolder;
        }
        if (this.x.animateChange(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            n();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceCompat.beginSection("RV OnLayout");
        o();
        TraceCompat.endSection();
        this.s = true;
    }

    public void requestLayout() {
        if (this.T != 0 || this.u) {
            this.t = true;
        } else {
            super.requestLayout();
        }
    }

    void p() {
        int c = this.f.c();
        for (int i = 0; i < c; i++) {
            ((LayoutParams) this.f.d(i).getLayoutParams()).e = true;
        }
        this.d.h();
    }

    public void draw(Canvas canvas) {
        int i;
        int i2;
        int i3 = 1;
        int i4 = 0;
        super.draw(canvas);
        int size = this.o.size();
        for (i = 0; i < size; i++) {
            ((ItemDecoration) this.o.get(i)).onDrawOver(canvas, this, this.B);
        }
        if (this.ad == null || this.ad.isFinished()) {
            i2 = 0;
        } else {
            i = canvas.save();
            i2 = this.h ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) (i2 + (-getHeight())), 0.0f);
            if (this.ad == null || !this.ad.draw(canvas)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            canvas.restoreToCount(i);
        }
        if (!(this.ae == null || this.ae.isFinished())) {
            size = canvas.save();
            if (this.h) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            if (this.ae == null || !this.ae.draw(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.af == null || this.af.isFinished())) {
            size = canvas.save();
            int width = getWidth();
            if (this.h) {
                i = getPaddingTop();
            } else {
                i = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float) (-i), (float) (-width));
            if (this.af == null || !this.af.draw(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.ag == null || this.ag.isFinished())) {
            i = canvas.save();
            canvas.rotate(180.0f);
            if (this.h) {
                canvas.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            if (this.ag != null && this.ag.draw(canvas)) {
                i4 = 1;
            }
            i2 |= i4;
            canvas.restoreToCount(i);
        }
        if (i2 != 0 || this.x == null || this.o.size() <= 0 || !this.x.isRunning()) {
            i3 = i2;
        }
        if (i3 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            ((ItemDecoration) this.o.get(i)).onDraw(canvas, this, this.B);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.m.checkLayoutParams((LayoutParams) layoutParams);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.m != null) {
            return this.m.generateDefaultLayoutParams();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        if (this.m != null) {
            return this.m.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (this.m != null) {
            return this.m.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    public boolean isAnimating() {
        return this.x != null && this.x.isRunning();
    }

    void q() {
        int c = this.f.c();
        for (int i = 0; i < c; i++) {
            ViewHolder b = b(this.f.d(i));
            if (!b.c()) {
                b.b();
            }
        }
    }

    void r() {
        int c = this.f.c();
        for (int i = 0; i < c; i++) {
            ViewHolder b = b(this.f.d(i));
            if (!b.c()) {
                b.a();
            }
        }
        this.d.g();
    }

    void d(int i, int i2) {
        int i3;
        int c = this.f.c();
        int i4;
        int i5;
        if (i < i2) {
            i3 = -1;
            i4 = i2;
            i5 = i;
        } else {
            i3 = 1;
            i4 = i;
            i5 = i2;
        }
        for (int i6 = 0; i6 < c; i6++) {
            ViewHolder b = b(this.f.d(i6));
            if (b != null && b.b >= r3 && b.b <= r2) {
                if (b.b == i) {
                    b.a(i2 - i, false);
                } else {
                    b.a(i3, false);
                }
                this.B.e = true;
            }
        }
        this.d.a(i, i2);
        requestLayout();
    }

    void e(int i, int i2) {
        int c = this.f.c();
        for (int i3 = 0; i3 < c; i3++) {
            ViewHolder b = b(this.f.d(i3));
            if (!(b == null || b.c() || b.b < i)) {
                b.a(i2, false);
                this.B.e = true;
            }
        }
        this.d.b(i, i2);
        requestLayout();
    }

    void a(int i, int i2, boolean z) {
        int i3 = i + i2;
        int c = this.f.c();
        for (int i4 = 0; i4 < c; i4++) {
            ViewHolder b = b(this.f.d(i4));
            if (!(b == null || b.c())) {
                if (b.b >= i3) {
                    b.a(-i2, z);
                    this.B.e = true;
                } else if (b.b >= i) {
                    b.a(i - 1, -i2, z);
                    this.B.e = true;
                }
            }
        }
        this.d.a(i, i2, z);
        requestLayout();
    }

    void a(int i, int i2, Object obj) {
        int c = this.f.c();
        int i3 = i + i2;
        for (int i4 = 0; i4 < c; i4++) {
            View d = this.f.d(i4);
            ViewHolder b = b(d);
            if (b != null && !b.c() && b.b >= i && b.b < i3) {
                b.b(2);
                b.a(obj);
                ((LayoutParams) d.getLayoutParams()).e = true;
            }
        }
        this.d.c(i, i2);
    }

    boolean b(ViewHolder viewHolder) {
        return this.x == null || this.x.canReuseUpdatedViewHolder(viewHolder, viewHolder.q());
    }

    void s() {
        this.w = true;
        t();
    }

    void t() {
        int c = this.f.c();
        for (int i = 0; i < c; i++) {
            ViewHolder b = b(this.f.d(i));
            if (!(b == null || b.c())) {
                b.b(6);
            }
        }
        p();
        this.d.f();
    }

    public void invalidateItemDecorations() {
        if (this.o.size() != 0) {
            if (this.m != null) {
                this.m.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }
            p();
            requestLayout();
        }
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.au;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.au = z;
    }

    public ViewHolder getChildViewHolder(View view) {
        Object parent = view.getParent();
        if (parent == null || parent == this) {
            return b(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Nullable
    public View findContainingItemView(View view) {
        RecyclerView parent = view.getParent();
        View view2 = view;
        while (parent != null && parent != this && (parent instanceof View)) {
            View view3 = parent;
            view2 = view3;
            ViewParent parent2 = view3.getParent();
        }
        return parent == this ? view2 : null;
    }

    @Nullable
    public ViewHolder findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        return findContainingItemView == null ? null : getChildViewHolder(findContainingItemView);
    }

    static ViewHolder b(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).c;
    }

    @Deprecated
    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public int getChildAdapterPosition(View view) {
        ViewHolder b = b(view);
        return b != null ? b.getAdapterPosition() : -1;
    }

    public int getChildLayoutPosition(View view) {
        ViewHolder b = b(view);
        return b != null ? b.getLayoutPosition() : -1;
    }

    public long getChildItemId(View view) {
        if (this.l == null || !this.l.hasStableIds()) {
            return -1;
        }
        ViewHolder b = b(view);
        if (b != null) {
            return b.getItemId();
        }
        return -1;
    }

    @Deprecated
    public ViewHolder findViewHolderForPosition(int i) {
        return a(i, false);
    }

    public ViewHolder findViewHolderForLayoutPosition(int i) {
        return a(i, false);
    }

    public ViewHolder findViewHolderForAdapterPosition(int i) {
        if (this.w) {
            return null;
        }
        int c = this.f.c();
        int i2 = 0;
        ViewHolder viewHolder = null;
        while (i2 < c) {
            ViewHolder b = b(this.f.d(i2));
            if (b == null || b.m() || d(b) != i) {
                b = viewHolder;
            } else if (!this.f.c(b.itemView)) {
                return b;
            }
            i2++;
            viewHolder = b;
        }
        return viewHolder;
    }

    ViewHolder a(int i, boolean z) {
        int c = this.f.c();
        ViewHolder viewHolder = null;
        for (int i2 = 0; i2 < c; i2++) {
            ViewHolder b = b(this.f.d(i2));
            if (!(b == null || b.m())) {
                if (z) {
                    if (b.b != i) {
                        continue;
                    }
                } else if (b.getLayoutPosition() != i) {
                    continue;
                }
                if (!this.f.c(b.itemView)) {
                    return b;
                }
                viewHolder = b;
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForItemId(long j) {
        if (this.l == null || !this.l.hasStableIds()) {
            return null;
        }
        int c = this.f.c();
        int i = 0;
        ViewHolder viewHolder = null;
        while (i < c) {
            ViewHolder b = b(this.f.d(i));
            if (b == null || b.m() || b.getItemId() != j) {
                b = viewHolder;
            } else if (!this.f.c(b.itemView)) {
                return b;
            }
            i++;
            viewHolder = b;
        }
        return viewHolder;
    }

    public View findChildViewUnder(float f, float f2) {
        for (int b = this.f.b() - 1; b >= 0; b--) {
            View b2 = this.f.b(b);
            float translationX = b2.getTranslationX();
            float translationY = b2.getTranslationY();
            if (f >= ((float) b2.getLeft()) + translationX && f <= translationX + ((float) b2.getRight()) && f2 >= ((float) b2.getTop()) + translationY && f2 <= ((float) b2.getBottom()) + translationY) {
                return b2;
            }
        }
        return null;
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void offsetChildrenVertical(int i) {
        int b = this.f.b();
        for (int i2 = 0; i2 < b; i2++) {
            this.f.b(i2).offsetTopAndBottom(i);
        }
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    public void offsetChildrenHorizontal(int i) {
        int b = this.f.b();
        for (int i2 = 0; i2 < b; i2++) {
            this.f.b(i2).offsetLeftAndRight(i);
        }
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        a(view, rect);
    }

    static void a(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.d;
        rect.set((view.getLeft() - rect2.left) - layoutParams.leftMargin, (view.getTop() - rect2.top) - layoutParams.topMargin, (view.getRight() + rect2.right) + layoutParams.rightMargin, layoutParams.bottomMargin + (rect2.bottom + view.getBottom()));
    }

    Rect c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.e) {
            return layoutParams.d;
        }
        if (this.B.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid())) {
            return layoutParams.d;
        }
        Rect rect = layoutParams.d;
        rect.set(0, 0, 0, 0);
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            this.j.set(0, 0, 0, 0);
            ((ItemDecoration) this.o.get(i)).getItemOffsets(this.j, view, this, this.B);
            rect.left += this.j.left;
            rect.top += this.j.top;
            rect.right += this.j.right;
            rect.bottom += this.j.bottom;
        }
        layoutParams.e = false;
        return rect;
    }

    public void onScrolled(int i, int i2) {
    }

    void f(int i, int i2) {
        this.ac++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(i, i2);
        if (this.av != null) {
            this.av.onScrolled(this, i, i2);
        }
        if (this.aw != null) {
            for (scrollY = this.aw.size() - 1; scrollY >= 0; scrollY--) {
                ((OnScrollListener) this.aw.get(scrollY)).onScrolled(this, i, i2);
            }
        }
        this.ac--;
    }

    public void onScrollStateChanged(int i) {
    }

    void b(int i) {
        if (this.m != null) {
            this.m.onScrollStateChanged(i);
        }
        onScrollStateChanged(i);
        if (this.av != null) {
            this.av.onScrollStateChanged(this, i);
        }
        if (this.aw != null) {
            for (int size = this.aw.size() - 1; size >= 0; size--) {
                ((OnScrollListener) this.aw.get(size)).onScrollStateChanged(this, i);
            }
        }
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.s || this.w || this.e.d();
    }

    void u() {
        int b = this.f.b();
        for (int i = 0; i < b; i++) {
            View b2 = this.f.b(i);
            ViewHolder childViewHolder = getChildViewHolder(b2);
            if (!(childViewHolder == null || childViewHolder.h == null)) {
                View view = childViewHolder.h.itemView;
                int left = b2.getLeft();
                int top = b2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Nullable
    static RecyclerView d(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View d = d(viewGroup.getChildAt(i));
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    static void c(@NonNull ViewHolder viewHolder) {
        if (viewHolder.a != null) {
            View view = (View) viewHolder.a.get();
            while (view != null) {
                if (view != viewHolder.itemView) {
                    ViewParent parent = view.getParent();
                    view = parent instanceof View ? (View) parent : null;
                } else {
                    return;
                }
            }
            viewHolder.a = null;
        }
    }

    long getNanoTime() {
        if (K) {
            return System.nanoTime();
        }
        return 0;
    }

    void e(View view) {
        ViewHolder b = b(view);
        onChildDetachedFromWindow(view);
        if (!(this.l == null || b == null)) {
            this.l.onViewDetachedFromWindow(b);
        }
        if (this.aa != null) {
            for (int size = this.aa.size() - 1; size >= 0; size--) {
                ((OnChildAttachStateChangeListener) this.aa.get(size)).onChildViewDetachedFromWindow(view);
            }
        }
    }

    void f(View view) {
        ViewHolder b = b(view);
        onChildAttachedToWindow(view);
        if (!(this.l == null || b == null)) {
            this.l.onViewAttachedToWindow(b);
        }
        if (this.aa != null) {
            for (int size = this.aa.size() - 1; size >= 0; size--) {
                ((OnChildAttachStateChangeListener) this.aa.get(size)).onChildViewAttachedToWindow(view);
            }
        }
    }

    @VisibleForTesting
    boolean a(ViewHolder viewHolder, int i) {
        if (isComputingLayout()) {
            viewHolder.k = i;
            this.G.add(viewHolder);
            return false;
        }
        ViewCompat.setImportantForAccessibility(viewHolder.itemView, i);
        return true;
    }

    void v() {
        for (int size = this.G.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.G.get(size);
            if (viewHolder.itemView.getParent() == this && !viewHolder.c()) {
                int i = viewHolder.k;
                if (i != -1) {
                    ViewCompat.setImportantForAccessibility(viewHolder.itemView, i);
                    viewHolder.k = -1;
                }
            }
        }
        this.G.clear();
    }

    int d(ViewHolder viewHolder) {
        if (viewHolder.a(524) || !viewHolder.l()) {
            return -1;
        }
        return this.e.applyPendingUpdatesToPosition(viewHolder.b);
    }

    @VisibleForTesting
    void a(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + a());
        }
        Resources resources = getContext().getResources();
        al alVar = new al(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }

    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i);
    }

    public boolean startNestedScroll(int i, int i2) {
        return getScrollingChildHelper().startNestedScroll(i, i2);
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopNestedScroll(int i) {
        getScrollingChildHelper().stopNestedScroll(i);
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int i) {
        return getScrollingChildHelper().hasNestedScrollingParent(i);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ay == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        return this.ay.onGetChildDrawingOrder(i, i2);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.aA == null) {
            this.aA = new NestedScrollingChildHelper(this);
        }
        return this.aA;
    }
}
