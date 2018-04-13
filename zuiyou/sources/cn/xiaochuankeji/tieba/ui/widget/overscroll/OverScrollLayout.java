package cn.xiaochuankeji.tieba.ui.widget.overscroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.ui.widget.overscroll.a.b;

public class OverScrollLayout extends FrameLayout implements c {
    RefreshTopView a;
    RefreshTopView b;
    RefreshBottomView c;
    RefreshBottomView d;
    RecyclerView e;
    View f;
    final float g;
    final float h;
    final float i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private a n;

    public interface a {
        void j();

        void k();
    }

    public OverScrollLayout(@NonNull Context context) {
        this(context, null);
    }

    public OverScrollLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverScrollLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        this.g = (float) getResources().getDimensionPixelOffset(R.dimen.base_over_scroll_size);
        this.h = (float) getResources().getDimensionPixelOffset(R.dimen.base_over_scroll_top_size);
        this.i = (float) getResources().getDimensionPixelOffset(R.dimen.base_over_scroll_bottom_size);
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = false;
        LayoutInflater.from(context).inflate(R.layout.layout_overscroll, this, true);
        this.f = findViewById(R.id.progress);
        this.b = (RefreshTopView) findViewById(R.id.top);
        this.d = (RefreshBottomView) findViewById(R.id.bottom);
        this.a = (RefreshTopView) findViewById(R.id.top_buoy);
        this.c = (RefreshBottomView) findViewById(R.id.bottom_buoy);
        this.e = (RecyclerView) findViewById(R.id.recycler_view);
        this.e.setHasFixedSize(false);
        i.a(this.e);
        this.e.setItemAnimator(new cn.xiaochuankeji.tieba.ui.widget.a.a());
        LayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setInitialPrefetchItemCount(4);
        this.e.setLayoutManager(linearLayoutManager);
        new f(new b(this, this.e) {
            final /* synthetic */ OverScrollLayout a;

            public boolean a() {
                return super.a() && !this.a.j && this.a.k;
            }

            public boolean b() {
                return super.b() && !this.a.j && this.a.l;
            }
        }).a(this);
        this.b.b();
        this.d.setHasMore(true);
        this.c.setHasMore(true);
    }

    public void a() {
        if (this.f != null) {
            this.f.setVisibility(0);
        }
    }

    public void b() {
        if (this.f != null) {
            this.f.setVisibility(4);
        }
    }

    public void c() {
    }

    public void a(a aVar, int i, float f) {
        if (!this.j) {
            ViewCompat.setTranslationY(this.a, this.h + f);
            ViewCompat.setTranslationY(this.c, this.i + f);
            if (i == 3 && Math.abs(f) > this.g) {
                this.j = true;
                this.a.b();
                if (f > 0.0f) {
                    this.b.setVisibility(0);
                    this.d.setVisibility(8);
                    if (this.n != null) {
                        this.n.j();
                    }
                } else if (this.m && f < 0.0f) {
                    this.b.setVisibility(8);
                    this.d.setVisibility(0);
                    if (this.n != null) {
                        this.n.k();
                    }
                } else if (!this.m && f < 0.0f) {
                    d();
                }
                ViewCompat.setTranslationY(this.a, this.h);
                ViewCompat.setTranslationY(this.c, this.i);
            }
        }
    }

    private void d() {
        this.j = false;
        if (this.b.isShown()) {
            this.b.animate().translationY(this.h).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ OverScrollLayout a;

                {
                    this.a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewCompat.setTranslationY(this.a.b, 0.0f);
                    this.a.b.setVisibility(8);
                }
            }).start();
        }
        if (this.d.isShown()) {
            this.d.animate().translationY(this.i).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ OverScrollLayout a;

                {
                    this.a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewCompat.setTranslationY(this.a.d, 0.0f);
                    this.a.d.setVisibility(8);
                }
            }).start();
        }
        this.a.a();
    }

    public void a(final boolean z) {
        b();
        this.m = z;
        d();
        postDelayed(new Runnable(this) {
            final /* synthetic */ OverScrollLayout b;

            public void run() {
                this.b.d.setHasMore(z);
                this.b.c.setHasMore(z);
            }
        }, 300);
    }

    public RecyclerView getScrollView() {
        return this.e;
    }

    public void setOnOverScrollListener(@NonNull a aVar) {
        this.n = aVar;
    }

    public void setEnableStart(boolean z) {
        this.k = z;
    }

    public void setEnableEnd(boolean z) {
        this.l = z;
    }
}
