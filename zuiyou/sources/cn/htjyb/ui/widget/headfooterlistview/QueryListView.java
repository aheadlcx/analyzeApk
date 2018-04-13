package cn.htjyb.ui.widget.headfooterlistview;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import cn.htjyb.b.a.a.a;
import cn.htjyb.b.a.b;
import cn.htjyb.ui.widget.headfooterlistview.header.c;
import cn.htjyb.ui.widget.headfooterlistview.header.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;

public class QueryListView extends FrameLayout implements a, b.a, b.b, cn.htjyb.ui.a, b, cn.htjyb.ui.widget.headfooterlistview.header.b {
    protected HeaderFooterListView a;
    protected Context b;
    protected BaseAdapter c;
    private CustomEmptyView d;
    private CustomEmptyView e;
    private boolean f = false;
    private boolean g;
    private boolean h;
    private boolean i;
    private b j;
    private cn.htjyb.ui.widget.headfooterlistview.header.b k;
    private volatile boolean l;
    private long m = 0;
    private long n;

    public enum EmptyPaddingStyle {
        PADDING20,
        GoldenSection
    }

    public QueryListView(Context context) {
        super(context);
        this.b = context;
        getViews();
        d();
    }

    public QueryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        getViews();
        d();
    }

    private void getViews() {
        inflate(this.b, R.layout.view_query_list, this);
        this.a = (HeaderFooterListView) findViewById(R.id.viewListInQueryList);
        this.d = (CustomEmptyView) findViewById(R.id.custom_empty_view_none);
        this.e = (CustomEmptyView) findViewById(R.id.custom_empty_view_net);
        this.a.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setPadding(0, 0, 0, 0);
        this.a.setClipToPadding(false);
        setClipToPadding(true);
    }

    protected void d() {
        d e = e();
        if (e != null) {
            this.a.a(e, (cn.htjyb.ui.widget.headfooterlistview.header.b) this);
        }
        this.a.a(new ViewLoadMoreFooter(this.b), (b) this);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QueryListView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h();
            }
        });
    }

    protected d e() {
        return new c(this.b);
    }

    public void f() {
        this.g = true;
    }

    public void g() {
        this.h = true;
    }

    public void a(b bVar, BaseAdapter baseAdapter) {
        a(bVar);
        this.c = baseAdapter;
        this.a.setAdapter(baseAdapter);
    }

    private void a(b bVar) {
        if (this.j != bVar) {
            p();
            this.j = bVar;
            this.a.b();
            this.l = false;
            bVar.registerOnListUpdateListener(this);
            bVar.registerOnQueryFinishListener(this);
            bVar.registerOnClearListener(this);
        }
    }

    public void h() {
        if (!this.l) {
            this.a.a();
            k();
        }
    }

    public void setRefreshStayMinTime(long j) {
        this.m = j;
    }

    public boolean i() {
        return this.l;
    }

    public boolean j() {
        return !this.g;
    }

    public void k() {
        if (!this.l) {
            this.e.setVisibility(4);
            this.n = System.currentTimeMillis();
            this.l = true;
            if (this.j != null) {
                this.j.refresh();
            }
            if (this.k != null) {
                this.k.k();
            }
        }
    }

    public void l() {
        this.l = false;
        this.a.b();
    }

    public HeaderFooterListView m() {
        return this.a;
    }

    public boolean n() {
        if (this.h || this.j == null) {
            return false;
        }
        return this.j.hasMore();
    }

    public void o() {
        if (!this.h && this.j != null) {
            this.j.queryMore();
        }
    }

    public void a() {
        if (this.c != null && this.b != null && (this.b instanceof Activity) && !((Activity) this.b).isFinishing()) {
            ((Activity) this.b).runOnUiThread(new Runnable(this) {
                final /* synthetic */ QueryListView a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (VERSION.SDK_INT < 19) {
                        this.a.c.notifyDataSetChanged();
                    } else if (this.a.isAttachedToWindow()) {
                        this.a.c.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public void a(final boolean z, final boolean z2, final String str) {
        if (!z2 || this.m == 0) {
            b(z, z2, str);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.n;
        if (currentTimeMillis < this.m) {
            getHandler().postDelayed(new Runnable(this) {
                final /* synthetic */ QueryListView d;

                public void run() {
                    this.d.b(z, z2, str);
                }
            }, this.m - currentTimeMillis);
        } else {
            b(z, z2, str);
        }
    }

    private void b(boolean z, boolean z2, String str) {
        this.a.d();
        this.a.b();
        this.l = false;
        this.a.a(z, n());
        if (this.f) {
            m().setEmptyView(this.d);
        }
        if (z) {
            this.e.setVisibility(4);
        } else if (!this.i) {
            if (!TextUtils.isEmpty(str)) {
                cn.htjyb.ui.widget.a.a(this.b, (CharSequence) str, 0).show();
            }
            if (this.j != null && this.j.itemCount() == 0) {
                View emptyView = m().getEmptyView();
                if (emptyView != null) {
                    emptyView.setVisibility(8);
                }
                this.e.b();
            }
        }
    }

    public void b() {
        this.a.b();
        this.l = false;
    }

    public void c() {
        p();
        if (this.c instanceof cn.htjyb.ui.a) {
            ((cn.htjyb.ui.a) this.c).c();
            this.c = null;
        }
    }

    public void setRefreshHeaderCallBack(cn.htjyb.ui.widget.headfooterlistview.header.b bVar) {
        this.k = bVar;
    }

    private void p() {
        if (this.j != null) {
            this.j.cancelQuery();
            this.j.unregisterOnListUpdateListener(this);
            this.j.unregisterOnQueryFinishedListener(this);
            this.j.unregisterOnClearListener(this);
            this.j = null;
        }
    }

    public void a(String str, int i, EmptyPaddingStyle emptyPaddingStyle) {
        a(str, i, emptyPaddingStyle, false);
    }

    public void a(final String str, @DrawableRes final int i, EmptyPaddingStyle emptyPaddingStyle, final boolean z) {
        post(new Runnable(this) {
            final /* synthetic */ QueryListView d;

            public void run() {
                this.d.f = true;
                this.d.d.b(i, str);
                if (z) {
                    this.d.m().setEmptyView(this.d.d);
                }
            }
        });
    }

    public void a(cn.htjyb.b.a.d dVar) {
        a((b) dVar);
    }

    public int getLastVisiblePosition() {
        if (this.a == null) {
            return 0;
        }
        return this.a.getLastVisiblePosition();
    }
}
