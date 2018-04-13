package android.support.v7.widget.helper;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ChildDrawingOrderCallback;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelper extends ItemDecoration implements OnChildAttachStateChangeListener {
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int START = 16;
    public static final int UP = 1;
    private a A;
    private final OnItemTouchListener B = new b(this);
    private Rect C;
    private long D;
    final List<View> a = new ArrayList();
    ViewHolder b = null;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    float j;
    int k = -1;
    Callback l;
    int m = 0;
    int n;
    List<b> o = new ArrayList();
    RecyclerView p;
    final Runnable q = new a(this);
    VelocityTracker r;
    View s = null;
    int t = -1;
    GestureDetectorCompat u;
    private final float[] v = new float[2];
    private int w;
    private List<ViewHolder> x;
    private List<Integer> y;
    private ChildDrawingOrderCallback z = null;

    public interface ViewDropHandler {
        void prepareForDrop(View view, View view2, int i, int i2);
    }

    public static abstract class Callback {
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final ItemTouchUIUtil a;
        private static final Interpolator b = new f();
        private static final Interpolator c = new g();
        private int d = -1;

        public abstract int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder);

        public abstract boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2);

        public abstract void onSwiped(ViewHolder viewHolder, int i);

        static {
            if (VERSION.SDK_INT >= 21) {
                a = new a();
            } else {
                a = new b();
            }
        }

        public static ItemTouchUIUtil getDefaultUIUtil() {
            return a;
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int i3 = i & 789516;
            if (i3 == 0) {
                return i;
            }
            int i4 = (i3 ^ -1) & i;
            if (i2 == 0) {
                return i4 | (i3 << 2);
            }
            return (i4 | ((i3 << 1) & -789517)) | (((i3 << 1) & 789516) << 2);
        }

        public static int makeMovementFlags(int i, int i2) {
            return (makeFlag(0, i2 | i) | makeFlag(1, i2)) | makeFlag(2, i);
        }

        public static int makeFlag(int i, int i2) {
            return i2 << (i * 8);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            int i3 = i & 3158064;
            if (i3 == 0) {
                return i;
            }
            int i4 = (i3 ^ -1) & i;
            if (i2 == 0) {
                return i4 | (i3 >> 2);
            }
            return (i4 | ((i3 >> 1) & -3158065)) | (((i3 >> 1) & 3158064) >> 2);
        }

        final int a(RecyclerView recyclerView, ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        boolean b(RecyclerView recyclerView, ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & 16711680) != 0;
        }

        boolean c(RecyclerView recyclerView, ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) != 0;
        }

        public boolean canDropOver(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getSwipeThreshold(ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getMoveThreshold(ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        public ViewHolder chooseDropTarget(ViewHolder viewHolder, List<ViewHolder> list, int i, int i2) {
            int width = i + viewHolder.itemView.getWidth();
            int height = i2 + viewHolder.itemView.getHeight();
            ViewHolder viewHolder2 = null;
            int i3 = -1;
            int left = i - viewHolder.itemView.getLeft();
            int top = i2 - viewHolder.itemView.getTop();
            int size = list.size();
            int i4 = 0;
            while (i4 < size) {
                int i5;
                ViewHolder viewHolder3;
                int i6;
                ViewHolder viewHolder4;
                int i7;
                ViewHolder viewHolder5 = (ViewHolder) list.get(i4);
                if (left > 0) {
                    int right = viewHolder5.itemView.getRight() - width;
                    if (right < 0 && viewHolder5.itemView.getRight() > viewHolder.itemView.getRight()) {
                        right = Math.abs(right);
                        if (right > i3) {
                            i5 = right;
                            viewHolder3 = viewHolder5;
                            if (left < 0) {
                                i3 = viewHolder5.itemView.getLeft() - i;
                                if (i3 > 0 && viewHolder5.itemView.getLeft() < viewHolder.itemView.getLeft()) {
                                    i3 = Math.abs(i3);
                                    if (i3 > i5) {
                                        viewHolder3 = viewHolder5;
                                        if (top < 0) {
                                            i5 = viewHolder5.itemView.getTop() - i2;
                                            if (i5 > 0 && viewHolder5.itemView.getTop() < viewHolder.itemView.getTop()) {
                                                i5 = Math.abs(i5);
                                                if (i5 > i3) {
                                                    viewHolder3 = viewHolder5;
                                                    if (top > 0) {
                                                        i3 = viewHolder5.itemView.getBottom() - height;
                                                        if (i3 < 0 && viewHolder5.itemView.getBottom() > viewHolder.itemView.getBottom()) {
                                                            i3 = Math.abs(i3);
                                                            if (i3 > i5) {
                                                                i6 = i3;
                                                                viewHolder4 = viewHolder5;
                                                                i7 = i6;
                                                                i4++;
                                                                viewHolder2 = viewHolder4;
                                                                i3 = i7;
                                                            }
                                                        }
                                                    }
                                                    i7 = i5;
                                                    viewHolder4 = viewHolder3;
                                                    i4++;
                                                    viewHolder2 = viewHolder4;
                                                    i3 = i7;
                                                }
                                            }
                                        }
                                        i5 = i3;
                                        if (top > 0) {
                                            i3 = viewHolder5.itemView.getBottom() - height;
                                            i3 = Math.abs(i3);
                                            if (i3 > i5) {
                                                i6 = i3;
                                                viewHolder4 = viewHolder5;
                                                i7 = i6;
                                                i4++;
                                                viewHolder2 = viewHolder4;
                                                i3 = i7;
                                            }
                                        }
                                        i7 = i5;
                                        viewHolder4 = viewHolder3;
                                        i4++;
                                        viewHolder2 = viewHolder4;
                                        i3 = i7;
                                    }
                                }
                            }
                            i3 = i5;
                            if (top < 0) {
                                i5 = viewHolder5.itemView.getTop() - i2;
                                i5 = Math.abs(i5);
                                if (i5 > i3) {
                                    viewHolder3 = viewHolder5;
                                    if (top > 0) {
                                        i3 = viewHolder5.itemView.getBottom() - height;
                                        i3 = Math.abs(i3);
                                        if (i3 > i5) {
                                            i6 = i3;
                                            viewHolder4 = viewHolder5;
                                            i7 = i6;
                                            i4++;
                                            viewHolder2 = viewHolder4;
                                            i3 = i7;
                                        }
                                    }
                                    i7 = i5;
                                    viewHolder4 = viewHolder3;
                                    i4++;
                                    viewHolder2 = viewHolder4;
                                    i3 = i7;
                                }
                            }
                            i5 = i3;
                            if (top > 0) {
                                i3 = viewHolder5.itemView.getBottom() - height;
                                i3 = Math.abs(i3);
                                if (i3 > i5) {
                                    i6 = i3;
                                    viewHolder4 = viewHolder5;
                                    i7 = i6;
                                    i4++;
                                    viewHolder2 = viewHolder4;
                                    i3 = i7;
                                }
                            }
                            i7 = i5;
                            viewHolder4 = viewHolder3;
                            i4++;
                            viewHolder2 = viewHolder4;
                            i3 = i7;
                        }
                    }
                }
                viewHolder3 = viewHolder2;
                i5 = i3;
                if (left < 0) {
                    i3 = viewHolder5.itemView.getLeft() - i;
                    i3 = Math.abs(i3);
                    if (i3 > i5) {
                        viewHolder3 = viewHolder5;
                        if (top < 0) {
                            i5 = viewHolder5.itemView.getTop() - i2;
                            i5 = Math.abs(i5);
                            if (i5 > i3) {
                                viewHolder3 = viewHolder5;
                                if (top > 0) {
                                    i3 = viewHolder5.itemView.getBottom() - height;
                                    i3 = Math.abs(i3);
                                    if (i3 > i5) {
                                        i6 = i3;
                                        viewHolder4 = viewHolder5;
                                        i7 = i6;
                                        i4++;
                                        viewHolder2 = viewHolder4;
                                        i3 = i7;
                                    }
                                }
                                i7 = i5;
                                viewHolder4 = viewHolder3;
                                i4++;
                                viewHolder2 = viewHolder4;
                                i3 = i7;
                            }
                        }
                        i5 = i3;
                        if (top > 0) {
                            i3 = viewHolder5.itemView.getBottom() - height;
                            i3 = Math.abs(i3);
                            if (i3 > i5) {
                                i6 = i3;
                                viewHolder4 = viewHolder5;
                                i7 = i6;
                                i4++;
                                viewHolder2 = viewHolder4;
                                i3 = i7;
                            }
                        }
                        i7 = i5;
                        viewHolder4 = viewHolder3;
                        i4++;
                        viewHolder2 = viewHolder4;
                        i3 = i7;
                    }
                }
                i3 = i5;
                if (top < 0) {
                    i5 = viewHolder5.itemView.getTop() - i2;
                    i5 = Math.abs(i5);
                    if (i5 > i3) {
                        viewHolder3 = viewHolder5;
                        if (top > 0) {
                            i3 = viewHolder5.itemView.getBottom() - height;
                            i3 = Math.abs(i3);
                            if (i3 > i5) {
                                i6 = i3;
                                viewHolder4 = viewHolder5;
                                i7 = i6;
                                i4++;
                                viewHolder2 = viewHolder4;
                                i3 = i7;
                            }
                        }
                        i7 = i5;
                        viewHolder4 = viewHolder3;
                        i4++;
                        viewHolder2 = viewHolder4;
                        i3 = i7;
                    }
                }
                i5 = i3;
                if (top > 0) {
                    i3 = viewHolder5.itemView.getBottom() - height;
                    i3 = Math.abs(i3);
                    if (i3 > i5) {
                        i6 = i3;
                        viewHolder4 = viewHolder5;
                        i7 = i6;
                        i4++;
                        viewHolder2 = viewHolder4;
                        i3 = i7;
                    }
                }
                i7 = i5;
                viewHolder4 = viewHolder3;
                i4++;
                viewHolder2 = viewHolder4;
                i3 = i7;
            }
            return viewHolder2;
        }

        public void onSelectedChanged(ViewHolder viewHolder, int i) {
            if (viewHolder != null) {
                a.onSelected(viewHolder.itemView);
            }
        }

        private int a(RecyclerView recyclerView) {
            if (this.d == -1) {
                this.d = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.d;
        }

        public void onMoved(RecyclerView recyclerView, ViewHolder viewHolder, int i, ViewHolder viewHolder2, int i2, int i3, int i4) {
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i3, i4);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
        }

        void a(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, List<b> list, int i, float f, float f2) {
            int i2;
            int size = list.size();
            for (i2 = 0; i2 < size; i2++) {
                b bVar = (b) list.get(i2);
                bVar.update();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, bVar.h, bVar.k, bVar.l, bVar.i, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                i2 = canvas.save();
                onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, true);
                canvas.restoreToCount(i2);
            }
        }

        void b(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, List<b> list, int i, float f, float f2) {
            int i2;
            int size = list.size();
            for (i2 = 0; i2 < size; i2++) {
                b bVar = (b) list.get(i2);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, bVar.h, bVar.k, bVar.l, bVar.i, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                i2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, true);
                canvas.restoreToCount(i2);
            }
            Object obj = null;
            int i3 = size - 1;
            while (i3 >= 0) {
                Object obj2;
                bVar = (b) list.get(i3);
                if (bVar.n && !bVar.mIsPendingCleanup) {
                    list.remove(i3);
                    obj2 = obj;
                } else if (bVar.n) {
                    obj2 = obj;
                } else {
                    obj2 = 1;
                }
                i3--;
                obj = obj2;
            }
            if (obj != null) {
                recyclerView.invalidate();
            }
        }

        public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
            a.clearView(viewHolder.itemView);
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            a.onDraw(canvas, recyclerView, viewHolder.itemView, f, f2, i, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            a.onDrawOver(canvas, recyclerView, viewHolder.itemView, f, f2, i, z);
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                return i == 8 ? 200 : 250;
            } else {
                if (i == 8) {
                    return itemAnimator.getMoveDuration();
                }
                return itemAnimator.getRemoveDuration();
            }
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            float f = 1.0f;
            int a = (int) (((float) (a(recyclerView) * ((int) Math.signum((float) i2)))) * c.getInterpolation(Math.min(1.0f, (((float) Math.abs(i2)) * 1.0f) / ((float) i))));
            if (j <= 2000) {
                f = ((float) j) / 2000.0f;
            }
            int interpolation = (int) (b.getInterpolation(f) * ((float) a));
            if (interpolation == 0) {
                return i2 > 0 ? 1 : -1;
            } else {
                return interpolation;
            }
        }
    }

    public static abstract class SimpleCallback extends Callback {
        private int a;
        private int b;

        public SimpleCallback(int i, int i2) {
            this.a = i2;
            this.b = i;
        }

        public void setDefaultSwipeDirs(int i) {
            this.a = i;
        }

        public void setDefaultDragDirs(int i) {
            this.b = i;
        }

        public int getSwipeDirs(RecyclerView recyclerView, ViewHolder viewHolder) {
            return this.a;
        }

        public int getDragDirs(RecyclerView recyclerView, ViewHolder viewHolder) {
            return this.b;
        }

        public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
            return Callback.makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
        }
    }

    private class a extends SimpleOnGestureListener {
        final /* synthetic */ ItemTouchHelper a;
        private boolean b = true;

        a(ItemTouchHelper itemTouchHelper) {
            this.a = itemTouchHelper;
        }

        void a() {
            this.b = false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.b) {
                View a = this.a.a(motionEvent);
                if (a != null) {
                    ViewHolder childViewHolder = this.a.p.getChildViewHolder(a);
                    if (childViewHolder != null && this.a.l.b(this.a.p, childViewHolder) && motionEvent.getPointerId(0) == this.a.k) {
                        int findPointerIndex = motionEvent.findPointerIndex(this.a.k);
                        float x = motionEvent.getX(findPointerIndex);
                        float y = motionEvent.getY(findPointerIndex);
                        this.a.c = x;
                        this.a.d = y;
                        ItemTouchHelper itemTouchHelper = this.a;
                        this.a.h = 0.0f;
                        itemTouchHelper.g = 0.0f;
                        if (this.a.l.isLongPressDragEnabled()) {
                            this.a.a(childViewHolder, 2);
                        }
                    }
                }
            }
        }
    }

    private static class b implements AnimatorListener {
        private final ValueAnimator a;
        private float b;
        final float d;
        final float e;
        final float f;
        final float g;
        final ViewHolder h;
        final int i;
        final int j;
        float k;
        float l;
        boolean m = false;
        public boolean mIsPendingCleanup;
        boolean n = false;

        b(ViewHolder viewHolder, int i, int i2, float f, float f2, float f3, float f4) {
            this.i = i2;
            this.j = i;
            this.h = viewHolder;
            this.d = f;
            this.e = f2;
            this.f = f3;
            this.g = f4;
            this.a = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.a.addUpdateListener(new h(this));
            this.a.setTarget(viewHolder.itemView);
            this.a.addListener(this);
            setFraction(0.0f);
        }

        public void setDuration(long j) {
            this.a.setDuration(j);
        }

        public void start() {
            this.h.setIsRecyclable(false);
            this.a.start();
        }

        public void cancel() {
            this.a.cancel();
        }

        public void setFraction(float f) {
            this.b = f;
        }

        public void update() {
            if (this.d == this.f) {
                this.k = this.h.itemView.getTranslationX();
            } else {
                this.k = this.d + (this.b * (this.f - this.d));
            }
            if (this.e == this.g) {
                this.l = this.h.itemView.getTranslationY();
            } else {
                this.l = this.e + (this.b * (this.g - this.e));
            }
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.n) {
                this.h.setIsRecyclable(true);
            }
            this.n = true;
        }

        public void onAnimationCancel(Animator animator) {
            setFraction(1.0f);
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    public ItemTouchHelper(Callback callback) {
        this.l = callback;
    }

    private static boolean a(View view, float f, float f2, float f3, float f4) {
        return f >= f3 && f <= ((float) view.getWidth()) + f3 && f2 >= f4 && f2 <= ((float) view.getHeight()) + f4;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        if (this.p != recyclerView) {
            if (this.p != null) {
                e();
            }
            this.p = recyclerView;
            if (this.p != null) {
                Resources resources = recyclerView.getResources();
                this.e = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
                this.f = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
                d();
            }
        }
    }

    private void d() {
        this.w = ViewConfiguration.get(this.p.getContext()).getScaledTouchSlop();
        this.p.addItemDecoration(this);
        this.p.addOnItemTouchListener(this.B);
        this.p.addOnChildAttachStateChangeListener(this);
        f();
    }

    private void e() {
        this.p.removeItemDecoration(this);
        this.p.removeOnItemTouchListener(this.B);
        this.p.removeOnChildAttachStateChangeListener(this);
        for (int size = this.o.size() - 1; size >= 0; size--) {
            this.l.clearView(this.p, ((b) this.o.get(0)).h);
        }
        this.o.clear();
        this.s = null;
        this.t = -1;
        h();
        g();
    }

    private void f() {
        this.A = new a(this);
        this.u = new GestureDetectorCompat(this.p.getContext(), this.A);
    }

    private void g() {
        if (this.A != null) {
            this.A.a();
            this.A = null;
        }
        if (this.u != null) {
            this.u = null;
        }
    }

    private void a(float[] fArr) {
        if ((this.n & 12) != 0) {
            fArr[0] = (this.i + this.g) - ((float) this.b.itemView.getLeft());
        } else {
            fArr[0] = this.b.itemView.getTranslationX();
        }
        if ((this.n & 3) != 0) {
            fArr[1] = (this.j + this.h) - ((float) this.b.itemView.getTop());
        } else {
            fArr[1] = this.b.itemView.getTranslationY();
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        float f;
        float f2 = 0.0f;
        if (this.b != null) {
            a(this.v);
            f = this.v[0];
            f2 = this.v[1];
        } else {
            f = 0.0f;
        }
        this.l.b(canvas, recyclerView, this.b, this.o, this.m, f, f2);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        float f;
        float f2 = 0.0f;
        this.t = -1;
        if (this.b != null) {
            a(this.v);
            f = this.v[0];
            f2 = this.v[1];
        } else {
            f = 0.0f;
        }
        this.l.a(canvas, recyclerView, this.b, this.o, this.m, f, f2);
    }

    void a(ViewHolder viewHolder, int i) {
        if (viewHolder != this.b || i != this.m) {
            this.D = Long.MIN_VALUE;
            int i2 = this.m;
            a(viewHolder, true);
            this.m = i;
            if (i == 2) {
                this.s = viewHolder.itemView;
                i();
            }
            int i3 = (1 << ((i * 8) + 8)) - 1;
            Object obj = null;
            if (this.b != null) {
                ViewHolder viewHolder2 = this.b;
                if (viewHolder2.itemView.getParent() != null) {
                    int i4;
                    float f;
                    float signum;
                    int i5;
                    if (i2 == 2) {
                        i4 = 0;
                    } else {
                        i4 = c(viewHolder2);
                    }
                    h();
                    switch (i4) {
                        case 1:
                        case 2:
                            f = 0.0f;
                            signum = Math.signum(this.h) * ((float) this.p.getHeight());
                            break;
                        case 4:
                        case 8:
                        case 16:
                        case 32:
                            signum = 0.0f;
                            f = Math.signum(this.g) * ((float) this.p.getWidth());
                            break;
                        default:
                            f = 0.0f;
                            signum = 0.0f;
                            break;
                    }
                    if (i2 == 2) {
                        i5 = 8;
                    } else if (i4 > 0) {
                        i5 = 2;
                    } else {
                        i5 = 4;
                    }
                    a(this.v);
                    float f2 = this.v[0];
                    float f3 = this.v[1];
                    b cVar = new c(this, viewHolder2, i5, i2, f2, f3, f, signum, i4, viewHolder2);
                    cVar.setDuration(this.l.getAnimationDuration(this.p, i5, f - f2, signum - f3));
                    this.o.add(cVar);
                    cVar.start();
                    obj = 1;
                } else {
                    a(viewHolder2.itemView);
                    this.l.clearView(this.p, viewHolder2);
                }
                this.b = null;
            }
            Object obj2 = obj;
            if (viewHolder != null) {
                this.n = (this.l.a(this.p, viewHolder) & i3) >> (this.m * 8);
                this.i = (float) viewHolder.itemView.getLeft();
                this.j = (float) viewHolder.itemView.getTop();
                this.b = viewHolder;
                if (i == 2) {
                    this.b.itemView.performHapticFeedback(0);
                }
            }
            ViewParent parent = this.p.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(this.b != null);
            }
            if (obj2 == null) {
                this.p.getLayoutManager().requestSimpleAnimationsInNextLayout();
            }
            this.l.onSelectedChanged(this.b, this.m);
            this.p.invalidate();
        }
    }

    void a(b bVar, int i) {
        this.p.post(new d(this, bVar, i));
    }

    boolean a() {
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            if (!((b) this.o.get(i)).n) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean b() {
        /*
        r14 = this;
        r12 = -9223372036854775808;
        r0 = 0;
        r5 = 0;
        r1 = r14.b;
        if (r1 != 0) goto L_0x000b;
    L_0x0008:
        r14.D = r12;
    L_0x000a:
        return r0;
    L_0x000b:
        r10 = java.lang.System.currentTimeMillis();
        r2 = r14.D;
        r1 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1));
        if (r1 != 0) goto L_0x00bb;
    L_0x0015:
        r6 = 0;
    L_0x0017:
        r1 = r14.p;
        r1 = r1.getLayoutManager();
        r2 = r14.C;
        if (r2 != 0) goto L_0x0028;
    L_0x0021:
        r2 = new android.graphics.Rect;
        r2.<init>();
        r14.C = r2;
    L_0x0028:
        r2 = r14.b;
        r2 = r2.itemView;
        r3 = r14.C;
        r1.calculateItemDecorationsForChild(r2, r3);
        r2 = r1.canScrollHorizontally();
        if (r2 == 0) goto L_0x00e6;
    L_0x0037:
        r2 = r14.i;
        r3 = r14.g;
        r2 = r2 + r3;
        r2 = (int) r2;
        r3 = r14.C;
        r3 = r3.left;
        r3 = r2 - r3;
        r4 = r14.p;
        r4 = r4.getPaddingLeft();
        r4 = r3 - r4;
        r3 = r14.g;
        r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r3 >= 0) goto L_0x00c1;
    L_0x0051:
        if (r4 >= 0) goto L_0x00c1;
    L_0x0053:
        r1 = r1.canScrollVertically();
        if (r1 == 0) goto L_0x010e;
    L_0x0059:
        r1 = r14.j;
        r2 = r14.h;
        r1 = r1 + r2;
        r1 = (int) r1;
        r2 = r14.C;
        r2 = r2.top;
        r2 = r1 - r2;
        r3 = r14.p;
        r3 = r3.getPaddingTop();
        r8 = r2 - r3;
        r2 = r14.h;
        r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x00e9;
    L_0x0073:
        if (r8 >= 0) goto L_0x00e9;
    L_0x0075:
        if (r4 == 0) goto L_0x0117;
    L_0x0077:
        r1 = r14.l;
        r2 = r14.p;
        r3 = r14.b;
        r3 = r3.itemView;
        r3 = r3.getWidth();
        r5 = r14.p;
        r5 = r5.getWidth();
        r4 = r1.interpolateOutOfBoundsScroll(r2, r3, r4, r5, r6);
        r9 = r4;
    L_0x008e:
        if (r8 == 0) goto L_0x0115;
    L_0x0090:
        r1 = r14.l;
        r2 = r14.p;
        r3 = r14.b;
        r3 = r3.itemView;
        r3 = r3.getHeight();
        r4 = r14.p;
        r5 = r4.getHeight();
        r4 = r8;
        r1 = r1.interpolateOutOfBoundsScroll(r2, r3, r4, r5, r6);
    L_0x00a7:
        if (r9 != 0) goto L_0x00ab;
    L_0x00a9:
        if (r1 == 0) goto L_0x0111;
    L_0x00ab:
        r2 = r14.D;
        r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1));
        if (r0 != 0) goto L_0x00b3;
    L_0x00b1:
        r14.D = r10;
    L_0x00b3:
        r0 = r14.p;
        r0.scrollBy(r9, r1);
        r0 = 1;
        goto L_0x000a;
    L_0x00bb:
        r2 = r14.D;
        r6 = r10 - r2;
        goto L_0x0017;
    L_0x00c1:
        r3 = r14.g;
        r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r3 <= 0) goto L_0x00e6;
    L_0x00c7:
        r3 = r14.b;
        r3 = r3.itemView;
        r3 = r3.getWidth();
        r2 = r2 + r3;
        r3 = r14.C;
        r3 = r3.right;
        r2 = r2 + r3;
        r3 = r14.p;
        r3 = r3.getWidth();
        r4 = r14.p;
        r4 = r4.getPaddingRight();
        r3 = r3 - r4;
        r4 = r2 - r3;
        if (r4 > 0) goto L_0x0053;
    L_0x00e6:
        r4 = r0;
        goto L_0x0053;
    L_0x00e9:
        r2 = r14.h;
        r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x010e;
    L_0x00ef:
        r2 = r14.b;
        r2 = r2.itemView;
        r2 = r2.getHeight();
        r1 = r1 + r2;
        r2 = r14.C;
        r2 = r2.bottom;
        r1 = r1 + r2;
        r2 = r14.p;
        r2 = r2.getHeight();
        r3 = r14.p;
        r3 = r3.getPaddingBottom();
        r2 = r2 - r3;
        r8 = r1 - r2;
        if (r8 > 0) goto L_0x0075;
    L_0x010e:
        r8 = r0;
        goto L_0x0075;
    L_0x0111:
        r14.D = r12;
        goto L_0x000a;
    L_0x0115:
        r1 = r8;
        goto L_0x00a7;
    L_0x0117:
        r9 = r4;
        goto L_0x008e;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.helper.ItemTouchHelper.b():boolean");
    }

    private List<ViewHolder> b(ViewHolder viewHolder) {
        if (this.x == null) {
            this.x = new ArrayList();
            this.y = new ArrayList();
        } else {
            this.x.clear();
            this.y.clear();
        }
        int boundingBoxMargin = this.l.getBoundingBoxMargin();
        int round = Math.round(this.i + this.g) - boundingBoxMargin;
        int round2 = Math.round(this.j + this.h) - boundingBoxMargin;
        int width = (viewHolder.itemView.getWidth() + round) + (boundingBoxMargin * 2);
        int height = (viewHolder.itemView.getHeight() + round2) + (boundingBoxMargin * 2);
        int i = (round + width) / 2;
        int i2 = (round2 + height) / 2;
        LayoutManager layoutManager = this.p.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = layoutManager.getChildAt(i3);
            if (childAt != viewHolder.itemView && childAt.getBottom() >= round2 && childAt.getTop() <= height && childAt.getRight() >= round && childAt.getLeft() <= width) {
                ViewHolder childViewHolder = this.p.getChildViewHolder(childAt);
                if (this.l.canDropOver(this.p, this.b, childViewHolder)) {
                    int abs = Math.abs(i - ((childAt.getLeft() + childAt.getRight()) / 2));
                    boundingBoxMargin = Math.abs(i2 - ((childAt.getBottom() + childAt.getTop()) / 2));
                    int i4 = (abs * abs) + (boundingBoxMargin * boundingBoxMargin);
                    int size = this.x.size();
                    int i5 = 0;
                    abs = 0;
                    while (abs < size && i4 > ((Integer) this.y.get(abs)).intValue()) {
                        i5++;
                        abs++;
                    }
                    this.x.add(i5, childViewHolder);
                    this.y.add(i5, Integer.valueOf(i4));
                }
            }
        }
        return this.x;
    }

    void a(ViewHolder viewHolder) {
        if (!this.p.isLayoutRequested() && this.m == 2) {
            float moveThreshold = this.l.getMoveThreshold(viewHolder);
            int i = (int) (this.i + this.g);
            int i2 = (int) (this.j + this.h);
            if (((float) Math.abs(i2 - viewHolder.itemView.getTop())) >= ((float) viewHolder.itemView.getHeight()) * moveThreshold || ((float) Math.abs(i - viewHolder.itemView.getLeft())) >= moveThreshold * ((float) viewHolder.itemView.getWidth())) {
                List b = b(viewHolder);
                if (b.size() != 0) {
                    ViewHolder chooseDropTarget = this.l.chooseDropTarget(viewHolder, b, i, i2);
                    if (chooseDropTarget == null) {
                        this.x.clear();
                        this.y.clear();
                        return;
                    }
                    int adapterPosition = chooseDropTarget.getAdapterPosition();
                    int adapterPosition2 = viewHolder.getAdapterPosition();
                    if (this.l.onMove(this.p, viewHolder, chooseDropTarget)) {
                        this.l.onMoved(this.p, viewHolder, adapterPosition2, chooseDropTarget, adapterPosition, i, i2);
                    }
                }
            }
        }
    }

    public void onChildViewAttachedToWindow(View view) {
    }

    public void onChildViewDetachedFromWindow(View view) {
        a(view);
        ViewHolder childViewHolder = this.p.getChildViewHolder(view);
        if (childViewHolder != null) {
            if (this.b == null || childViewHolder != this.b) {
                a(childViewHolder, false);
                if (this.a.remove(childViewHolder.itemView)) {
                    this.l.clearView(this.p, childViewHolder);
                    return;
                }
                return;
            }
            a(null, 0);
        }
    }

    int a(ViewHolder viewHolder, boolean z) {
        for (int size = this.o.size() - 1; size >= 0; size--) {
            b bVar = (b) this.o.get(size);
            if (bVar.h == viewHolder) {
                bVar.m |= z;
                if (!bVar.n) {
                    bVar.cancel();
                }
                this.o.remove(size);
                return bVar.j;
            }
        }
        return 0;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        rect.setEmpty();
    }

    void c() {
        if (this.r != null) {
            this.r.recycle();
        }
        this.r = VelocityTracker.obtain();
    }

    private void h() {
        if (this.r != null) {
            this.r.recycle();
            this.r = null;
        }
    }

    private ViewHolder c(MotionEvent motionEvent) {
        LayoutManager layoutManager = this.p.getLayoutManager();
        if (this.k == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(this.k);
        float x = motionEvent.getX(findPointerIndex) - this.c;
        float y = motionEvent.getY(findPointerIndex) - this.d;
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < ((float) this.w) && y < ((float) this.w)) {
            return null;
        }
        if (x > y && layoutManager.canScrollHorizontally()) {
            return null;
        }
        if (y > x && layoutManager.canScrollVertically()) {
            return null;
        }
        View a = a(motionEvent);
        if (a != null) {
            return this.p.getChildViewHolder(a);
        }
        return null;
    }

    boolean a(int i, MotionEvent motionEvent, int i2) {
        if (this.b != null || i != 2 || this.m == 2 || !this.l.isItemViewSwipeEnabled() || this.p.getScrollState() == 1) {
            return false;
        }
        ViewHolder c = c(motionEvent);
        if (c == null) {
            return false;
        }
        int a = (this.l.a(this.p, c) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (a == 0) {
            return false;
        }
        float x = motionEvent.getX(i2);
        x -= this.c;
        float y = motionEvent.getY(i2) - this.d;
        float abs = Math.abs(x);
        float abs2 = Math.abs(y);
        if (abs < ((float) this.w) && abs2 < ((float) this.w)) {
            return false;
        }
        if (abs > abs2) {
            if (x < 0.0f && (a & 4) == 0) {
                return false;
            }
            if (x > 0.0f && (a & 8) == 0) {
                return false;
            }
        } else if (y < 0.0f && (a & 1) == 0) {
            return false;
        } else {
            if (y > 0.0f && (a & 2) == 0) {
                return false;
            }
        }
        this.h = 0.0f;
        this.g = 0.0f;
        this.k = motionEvent.getPointerId(0);
        a(c, 1);
        return true;
    }

    View a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.b != null) {
            View view = this.b.itemView;
            if (a(view, x, y, this.i + this.g, this.j + this.h)) {
                return view;
            }
        }
        for (int size = this.o.size() - 1; size >= 0; size--) {
            b bVar = (b) this.o.get(size);
            View view2 = bVar.h.itemView;
            if (a(view2, x, y, bVar.k, bVar.l)) {
                return view2;
            }
        }
        return this.p.findChildViewUnder(x, y);
    }

    public void startDrag(ViewHolder viewHolder) {
        if (!this.l.b(this.p, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
        } else if (viewHolder.itemView.getParent() != this.p) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            c();
            this.h = 0.0f;
            this.g = 0.0f;
            a(viewHolder, 2);
        }
    }

    public void startSwipe(ViewHolder viewHolder) {
        if (!this.l.c(this.p, viewHolder)) {
            Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
        } else if (viewHolder.itemView.getParent() != this.p) {
            Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
        } else {
            c();
            this.h = 0.0f;
            this.g = 0.0f;
            a(viewHolder, 1);
        }
    }

    b b(MotionEvent motionEvent) {
        if (this.o.isEmpty()) {
            return null;
        }
        View a = a(motionEvent);
        for (int size = this.o.size() - 1; size >= 0; size--) {
            b bVar = (b) this.o.get(size);
            if (bVar.h.itemView == a) {
                return bVar;
            }
        }
        return null;
    }

    void a(MotionEvent motionEvent, int i, int i2) {
        float x = motionEvent.getX(i2);
        float y = motionEvent.getY(i2);
        this.g = x - this.c;
        this.h = y - this.d;
        if ((i & 4) == 0) {
            this.g = Math.max(0.0f, this.g);
        }
        if ((i & 8) == 0) {
            this.g = Math.min(0.0f, this.g);
        }
        if ((i & 1) == 0) {
            this.h = Math.max(0.0f, this.h);
        }
        if ((i & 2) == 0) {
            this.h = Math.min(0.0f, this.h);
        }
    }

    private int c(ViewHolder viewHolder) {
        if (this.m == 2) {
            return 0;
        }
        int movementFlags = this.l.getMovementFlags(this.p, viewHolder);
        int convertToAbsoluteDirection = (this.l.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.p)) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i = (movementFlags & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (Math.abs(this.g) > Math.abs(this.h)) {
            movementFlags = b(viewHolder, convertToAbsoluteDirection);
            if (movementFlags > 0) {
                return (i & movementFlags) == 0 ? Callback.convertToRelativeDirection(movementFlags, ViewCompat.getLayoutDirection(this.p)) : movementFlags;
            } else {
                movementFlags = c(viewHolder, convertToAbsoluteDirection);
                if (movementFlags > 0) {
                    return movementFlags;
                }
                return 0;
            }
        }
        movementFlags = c(viewHolder, convertToAbsoluteDirection);
        if (movementFlags > 0) {
            return movementFlags;
        }
        movementFlags = b(viewHolder, convertToAbsoluteDirection);
        if (movementFlags > 0) {
            return (i & movementFlags) == 0 ? Callback.convertToRelativeDirection(movementFlags, ViewCompat.getLayoutDirection(this.p)) : movementFlags;
        } else {
            return 0;
        }
    }

    private int b(ViewHolder viewHolder, int i) {
        int i2 = 8;
        if ((i & 12) != 0) {
            int i3 = this.g > 0.0f ? 8 : 4;
            if (this.r != null && this.k > -1) {
                this.r.computeCurrentVelocity(1000, this.l.getSwipeVelocityThreshold(this.f));
                float xVelocity = this.r.getXVelocity(this.k);
                float yVelocity = this.r.getYVelocity(this.k);
                if (xVelocity <= 0.0f) {
                    i2 = 4;
                }
                float abs = Math.abs(xVelocity);
                if ((i2 & i) != 0 && i3 == i2 && abs >= this.l.getSwipeEscapeVelocity(this.e) && abs > Math.abs(yVelocity)) {
                    return i2;
                }
            }
            float width = ((float) this.p.getWidth()) * this.l.getSwipeThreshold(viewHolder);
            if ((i & i3) != 0 && Math.abs(this.g) > width) {
                return i3;
            }
        }
        return 0;
    }

    private int c(ViewHolder viewHolder, int i) {
        int i2 = 2;
        if ((i & 3) != 0) {
            int i3 = this.h > 0.0f ? 2 : 1;
            if (this.r != null && this.k > -1) {
                this.r.computeCurrentVelocity(1000, this.l.getSwipeVelocityThreshold(this.f));
                float xVelocity = this.r.getXVelocity(this.k);
                float yVelocity = this.r.getYVelocity(this.k);
                if (yVelocity <= 0.0f) {
                    i2 = 1;
                }
                float abs = Math.abs(yVelocity);
                if ((i2 & i) != 0 && i2 == i3 && abs >= this.l.getSwipeEscapeVelocity(this.e) && abs > Math.abs(xVelocity)) {
                    return i2;
                }
            }
            float height = ((float) this.p.getHeight()) * this.l.getSwipeThreshold(viewHolder);
            if ((i & i3) != 0 && Math.abs(this.h) > height) {
                return i3;
            }
        }
        return 0;
    }

    private void i() {
        if (VERSION.SDK_INT < 21) {
            if (this.z == null) {
                this.z = new e(this);
            }
            this.p.setChildDrawingOrderCallback(this.z);
        }
    }

    void a(View view) {
        if (view == this.s) {
            this.s = null;
            if (this.z != null) {
                this.p.setChildDrawingOrderCallback(null);
            }
        }
    }
}
