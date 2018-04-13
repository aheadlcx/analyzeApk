package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;
import java.util.List;

public class b extends FrameLayout implements i, cn.xiaochuankeji.tieba.ui.widget.indicator.m.a {
    private HorizontalScrollView a;
    private LinearLayout b;
    private LinearLayout c;
    private h d;
    private c e;
    private m f = new m();
    private a g;
    private boolean h;
    private boolean i;
    private float j = 0.5f;
    private boolean k = true;
    private boolean l = true;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r = true;
    private boolean s = true;
    private List<n> t = new ArrayList();
    private DataSetObserver u = new DataSetObserver(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onChanged() {
            this.a.f.c(this.a.e.a());
            this.a.c();
        }

        public void onInvalidated() {
        }
    };

    public interface a {
        void a();

        void a(boolean z);

        void b();
    }

    public b(Context context) {
        super(context);
        this.f.a((cn.xiaochuankeji.tieba.ui.widget.indicator.m.a) this);
    }

    public void setAdjustMode(boolean z) {
        this.h = z;
    }

    public c getAdapter() {
        return this.e;
    }

    public void setAdapter(c cVar) {
        if (this.e != cVar) {
            if (this.e != null) {
                this.e.b(this.u);
            }
            this.e = cVar;
            if (this.e != null) {
                this.e.a(this.u);
                this.f.c(this.e.a());
                if (this.b != null) {
                    this.e.b();
                    return;
                }
                return;
            }
            this.f.c(0);
            c();
        }
    }

    private void c() {
        View inflate;
        removeAllViews();
        if (this.h) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_no_scroll, this);
        } else {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout, this);
            this.a = (HorizontalScrollView) inflate.findViewById(R.id.scroll_view);
            this.a.setSaveFromParentEnabled(false);
            final int[] iArr = new int[]{0};
            this.a.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ b b;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                        case 2:
                            iArr[0] = 1;
                            if (this.b.g != null) {
                                this.b.g.b();
                                break;
                            }
                            break;
                        case 1:
                            iArr[0] = 0;
                            if (this.b.g != null) {
                                this.b.g.a();
                                break;
                            }
                            break;
                    }
                    return false;
                }
            });
            final int[] iArr2 = new int[]{0};
            this.a.getViewTreeObserver().addOnScrollChangedListener(new OnScrollChangedListener(this) {
                final /* synthetic */ b c;

                public void onScrollChanged() {
                    if (iArr[0] != 0 && this.c.g != null) {
                        int scrollX = iArr2[0] - this.c.a.getScrollX();
                        if (scrollX > 0 && scrollX > 6) {
                            this.c.g.a(true);
                        }
                        if (scrollX < 0 && scrollX < -6) {
                            this.c.g.a(false);
                        }
                        iArr2[0] = this.c.a.getScrollX();
                    }
                }
            });
        }
        this.b = (LinearLayout) inflate.findViewById(R.id.title_container);
        this.b.setPadding(this.n, 0, this.m, 0);
        this.c = (LinearLayout) inflate.findViewById(R.id.indicator_container);
        if (this.p) {
            this.c.getParent().bringChildToFront(this.c);
        }
        d();
    }

    private void d() {
        LayoutParams layoutParams;
        int a = this.f.a();
        for (int i = 0; i < a; i++) {
            j a2 = this.e.a(getContext(), i);
            if (a2 instanceof View) {
                View view = (View) a2;
                if (this.h) {
                    layoutParams = new LinearLayout.LayoutParams(0, -1);
                    layoutParams.weight = this.e.a(i);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                    layoutParams.leftMargin = this.o;
                    layoutParams.rightMargin = this.o;
                }
                this.b.addView(view, layoutParams);
            }
        }
        if (this.e != null) {
            this.d = this.e.a(getContext());
            if (this.d instanceof View) {
                layoutParams = new LinearLayout.LayoutParams(-1, -1);
                if (this.s) {
                    layoutParams.setMargins(0, 0, 0, e.a(5.0f));
                }
                this.c.addView((View) this.d, layoutParams);
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.e != null) {
            e();
            if (this.d != null) {
                this.d.a(this.t);
            }
            if (this.r && this.f.c() == 0) {
                a(this.f.b());
                a(this.f.b(), 0.0f, 0);
            }
        }
    }

    private void e() {
        this.t.clear();
        int a = this.f.a();
        for (int i = 0; i < a; i++) {
            n nVar = new n();
            View childAt = this.b.getChildAt(i);
            if (childAt != null) {
                nVar.a = childAt.getLeft();
                nVar.b = childAt.getTop();
                nVar.c = childAt.getRight();
                nVar.d = childAt.getBottom();
                if (childAt instanceof g) {
                    g gVar = (g) childAt;
                    nVar.e = gVar.getContentLeft();
                    nVar.f = gVar.getContentTop();
                    nVar.g = gVar.getContentRight();
                    nVar.h = gVar.getContentBottom();
                } else {
                    nVar.e = nVar.a;
                    nVar.f = nVar.b;
                    nVar.g = nVar.c;
                    nVar.h = nVar.d;
                }
            }
            this.t.add(nVar);
        }
    }

    public void a(int i, float f, int i2) {
        if (this.e != null) {
            this.f.a(i, f, i2);
            if (this.d != null) {
                this.d.a(i, f, i2);
            }
            if (this.a != null && !this.t.isEmpty() && i >= 0 && i < this.t.size()) {
                if (this.l) {
                    int min = Math.min(this.t.size() - 1, i);
                    float b = ((float) ((n) this.t.get(min)).b()) - (((float) this.a.getWidth()) * this.j);
                    this.a.scrollTo((int) (b + (((((float) ((n) this.t.get(Math.min(this.t.size() - 1, i + 1))).b()) - (((float) this.a.getWidth()) * this.j)) - b) * f)), 0);
                } else if (!this.i) {
                }
            }
        }
    }

    public float getScrollPivotX() {
        return this.j;
    }

    public void setScrollPivotX(float f) {
        this.j = f;
    }

    public void a(int i) {
        if (this.e != null) {
            this.f.a(i);
            if (this.d != null) {
                this.d.a(i);
            }
        }
    }

    public void b(int i) {
        if (this.e != null) {
            this.f.b(i);
            if (this.d != null) {
                this.d.b(i);
            }
        }
    }

    public void a() {
        c();
    }

    public void b() {
    }

    public h getPagerIndicator() {
        return this.d;
    }

    public void setEnablePivotScroll(boolean z) {
        this.i = z;
    }

    public void setIsNeedMargin(boolean z) {
        this.s = z;
    }

    public void a(int i, int i2, float f, boolean z) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof j) {
                ((j) childAt).b(i, i2, f, z);
            }
        }
    }

    public void b(int i, int i2, float f, boolean z) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof j) {
                ((j) childAt).a(i, i2, f, z);
            }
        }
    }

    public void setSmoothScroll(boolean z) {
        this.k = z;
    }

    public void setFollowTouch(boolean z) {
        this.l = z;
    }

    public void setSkimOver(boolean z) {
        this.q = z;
        this.f.a(z);
    }

    public void a(int i, int i2) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof j) {
                ((j) childAt).a(i, i2);
            }
            if (!this.h && !this.l && this.a != null && !this.t.isEmpty()) {
                float b;
                n nVar = (n) this.t.get(Math.min(this.t.size() - 1, i));
                if (this.i) {
                    b = ((float) nVar.b()) - (((float) this.a.getWidth()) * this.j);
                } else if (this.a.getScrollX() > nVar.a) {
                    b = (float) nVar.a;
                } else if (this.a.getScrollX() + getWidth() < nVar.c) {
                    b = (float) (nVar.c - getWidth());
                } else {
                    b = 0.0f;
                }
                if (this.k) {
                    this.a.smoothScrollTo((int) b, 0);
                } else {
                    this.a.scrollTo((int) b, 0);
                }
            }
        }
    }

    public void b(int i, int i2) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof j) {
                ((j) childAt).b(i, i2);
            }
        }
    }

    public LinearLayout getTitleContainer() {
        return this.b;
    }

    public int getRightPadding() {
        return this.m;
    }

    public void setRightPadding(int i) {
        this.m = i;
    }

    public int getLeftPadding() {
        return this.n;
    }

    public void setLeftPadding(int i) {
        this.n = i;
    }

    public int getSpace() {
        return this.o;
    }

    public void setSpace(int i) {
        this.o = i;
    }

    public void setIndicatorOnTop(boolean z) {
        this.p = z;
    }

    public void setReselectWhenLayout(boolean z) {
        this.r = z;
    }

    public void setScrollListener(a aVar) {
        this.g = aVar;
    }
}
