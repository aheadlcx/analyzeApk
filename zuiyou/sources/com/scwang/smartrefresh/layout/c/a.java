package com.scwang.smartrefresh.layout.c;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import com.scwang.smartrefresh.layout.a.c;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.a.i;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class a implements c {
    protected int a = Integer.MAX_VALUE;
    protected int b = (this.a - 1);
    protected View c;
    protected View d;
    protected View e;
    protected View f;
    protected View g;
    protected boolean h = true;
    protected boolean i = true;
    protected MotionEvent j;
    protected d k = new d();

    public a(View view) {
        this.d = view;
        this.c = view;
    }

    public a(Context context) {
        View view = new View(context);
        this.d = view;
        this.c = view;
    }

    protected void a(View view, g gVar) {
        this.e = null;
        while (true) {
            if (this.e == null || ((this.e instanceof NestedScrollingParent) && !(this.e instanceof NestedScrollingChild))) {
                View a = a(view, this.e == null);
                if (a != this.e) {
                    try {
                        if (a instanceof CoordinatorLayout) {
                            gVar.a().f(false);
                            a((ViewGroup) a, gVar.a());
                        }
                    } catch (Throwable th) {
                    }
                    this.e = a;
                    view = a;
                } else {
                    return;
                }
            }
            return;
        }
    }

    protected void a(ViewGroup viewGroup, final h hVar) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof AppBarLayout) {
                ((AppBarLayout) childAt).addOnOffsetChangedListener(new OnOffsetChangedListener(this) {
                    final /* synthetic */ a b;

                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        boolean z;
                        boolean z2 = true;
                        a aVar = this.b;
                        if (i >= 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        aVar.h = z;
                        a aVar2 = this.b;
                        if (!hVar.r() || appBarLayout.getTotalScrollRange() + i > 0) {
                            z2 = false;
                        }
                        aVar2.i = z2;
                    }
                });
            }
        }
    }

    protected View a(View view, boolean z) {
        View view2 = null;
        Queue linkedBlockingQueue = new LinkedBlockingQueue(Collections.singletonList(view));
        while (!linkedBlockingQueue.isEmpty() && view2 == null) {
            View view3 = (View) linkedBlockingQueue.poll();
            if (view3 != null) {
                if ((z || view3 != view) && a(view3)) {
                    view2 = view3;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        linkedBlockingQueue.add(viewGroup.getChildAt(i));
                    }
                }
            }
            view3 = view2;
            view2 = view3;
        }
        return view2 == null ? view : view2;
    }

    protected boolean a(View view) {
        return (view instanceof AbsListView) || (view instanceof ScrollView) || (view instanceof ScrollingView) || (view instanceof NestedScrollingChild) || (view instanceof NestedScrollingParent) || (view instanceof WebView) || (view instanceof ViewPager);
    }

    protected View a(View view, MotionEvent motionEvent, View view2) {
        if (!(view instanceof ViewGroup) || motionEvent == null) {
            return view2;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        PointF pointF = new PointF();
        int i = childCount;
        while (i > 0) {
            View childAt = viewGroup.getChildAt(i - 1);
            if (!com.scwang.smartrefresh.layout.f.c.a(viewGroup, childAt, motionEvent.getX(), motionEvent.getY(), pointF)) {
                i--;
            } else if (!(childAt instanceof ViewPager) && a(childAt)) {
                return childAt;
            } else {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.offsetLocation(pointF.x, pointF.y);
                return a(childAt, obtain, view2);
            }
        }
        return view2;
    }

    @NonNull
    public View e() {
        return this.c;
    }

    public void a(int i) {
        this.d.setTranslationY((float) i);
        if (this.f != null) {
            this.f.setTranslationY((float) Math.max(0, i));
        }
        if (this.g != null) {
            this.g.setTranslationY((float) Math.min(0, i));
        }
    }

    public boolean a() {
        return this.h && this.k.a(this.c);
    }

    public boolean b() {
        return this.i && this.k.b(this.c);
    }

    public void a(int i, int i2) {
        this.c.measure(i, i2);
    }

    public LayoutParams g() {
        return this.c.getLayoutParams();
    }

    public int c() {
        return this.c.getMeasuredWidth();
    }

    public int d() {
        return this.c.getMeasuredHeight();
    }

    public void a(int i, int i2, int i3, int i4) {
        this.c.layout(i, i2, i3, i4);
    }

    public View f() {
        return this.e;
    }

    public void a(MotionEvent motionEvent) {
        this.j = MotionEvent.obtain(motionEvent);
        this.j.offsetLocation((float) (-this.c.getLeft()), (float) (-this.c.getTop()));
        this.k.a(this.j);
        this.e = a(this.c, this.j, this.e);
    }

    public void h() {
        this.j = null;
    }

    public void b(int i) {
        if (this.e instanceof ScrollView) {
            ((ScrollView) this.e).fling(i);
        } else if (this.e instanceof AbsListView) {
            if (VERSION.SDK_INT >= 21) {
                ((AbsListView) this.e).fling(i);
            }
        } else if (this.e instanceof WebView) {
            ((WebView) this.e).flingScroll(0, i);
        } else if (this.e instanceof RecyclerView) {
            ((RecyclerView) this.e).fling(0, i);
        } else if (this.e instanceof NestedScrollView) {
            ((NestedScrollView) this.e).fling(i);
        }
    }

    public void a(g gVar, View view, View view2) {
        a(this.c, gVar);
        if (view != null || view2 != null) {
            LayoutParams layoutParams;
            ViewGroup viewGroup;
            int indexOfChild;
            this.f = view;
            this.g = view2;
            View frameLayout = new FrameLayout(this.c.getContext());
            gVar.a().getLayout().removeView(this.c);
            LayoutParams layoutParams2 = this.c.getLayoutParams();
            frameLayout.addView(this.c, -1, -1);
            gVar.a().getLayout().addView(frameLayout, layoutParams2);
            this.c = frameLayout;
            if (view != null) {
                view.setClickable(true);
                layoutParams = view.getLayoutParams();
                viewGroup = (ViewGroup) view.getParent();
                indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeView(view);
                layoutParams.height = b(view);
                viewGroup.addView(new Space(this.c.getContext()), indexOfChild, layoutParams);
                frameLayout.addView(view);
            }
            if (view2 != null) {
                view2.setClickable(true);
                layoutParams = view2.getLayoutParams();
                viewGroup = (ViewGroup) view2.getParent();
                indexOfChild = viewGroup.indexOfChild(view2);
                viewGroup.removeView(view2);
                LayoutParams layoutParams3 = new FrameLayout.LayoutParams(layoutParams);
                layoutParams.height = b(view2);
                viewGroup.addView(new Space(this.c.getContext()), indexOfChild, layoutParams);
                layoutParams3.gravity = 80;
                frameLayout.addView(view2, layoutParams3);
            }
        }
    }

    public void b(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void a(i iVar) {
        if (iVar instanceof d) {
            this.k = (d) iVar;
        } else {
            this.k.a(iVar);
        }
    }

    public void a(boolean z) {
        this.k.a(z);
    }

    public AnimatorUpdateListener c(final int i) {
        return (this.e == null || i == 0 || ((i >= 0 || !com.scwang.smartrefresh.layout.f.c.b(this.e)) && (i <= 0 || !com.scwang.smartrefresh.layout.f.c.a(this.e)))) ? null : new AnimatorUpdateListener(this) {
            int a = i;
            final /* synthetic */ a c;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                try {
                    if (this.c.e instanceof AbsListView) {
                        a.a((AbsListView) this.c.e, intValue - this.a);
                    } else {
                        this.c.e.scrollBy(0, intValue - this.a);
                    }
                } catch (Throwable th) {
                }
                this.a = intValue;
            }
        };
    }

    protected static int b(View view) {
        int makeMeasureSpec;
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        if (layoutParams.height > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
        return view.getMeasuredHeight();
    }

    protected static void a(@NonNull AbsListView absListView, int i) {
        if (VERSION.SDK_INT >= 19) {
            absListView.scrollListBy(i);
        } else if (absListView instanceof ListView) {
            int firstVisiblePosition = absListView.getFirstVisiblePosition();
            if (firstVisiblePosition != -1) {
                View childAt = absListView.getChildAt(0);
                if (childAt != null) {
                    ((ListView) absListView).setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
                }
            }
        } else {
            absListView.smoothScrollBy(i, 0);
        }
    }
}
