package qsbk.app.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import qsbk.app.widget.recyclerview.EndlessRecyclerOnScrollListener;

public class PtrLayout extends PtrFrameLayout {
    public static final int AUTO = 1;
    public static final int DISABLE = 0;
    public static final int MANUAL = 2;
    public static final int STOP = 3;
    EndlessRecyclerOnScrollListener c = new df(this);
    private int d = 14;
    private PtrListener e;
    private int f;
    private boolean g = false;
    private long h;
    private long i = 500;
    private PtrHeader j;
    private PtrFooter k;
    private int l;
    private OnScrollOffsetListener m;
    private OnScrollListener n;
    private final OnScrollListener o = new de(this);
    private PtrHandler p = new dg(this);

    public interface PtrListener {
        void onLoadMore();

        void onRefresh();
    }

    public interface OnScrollOffsetListener {
        void onScrollOffsetChange(float f, int i, int i2);
    }

    public PtrLayout(Context context) {
        super(context);
        c();
    }

    public PtrLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public void setLeftItemWhenTrickLoadMore(int i) {
        this.d = i;
    }

    private void c() {
        this.l = (int) (20.0f * getResources().getDisplayMetrics().density);
        this.j = new PtrHeader(getContext());
        setHeaderView(this.j);
        addPtrUIHandler(this.j);
    }

    protected int getPtrHeaderHeight() {
        if (this.j == null && this.mPtrIndicator == null) {
            return 0;
        }
        return this.j != null ? this.j.getHeight() : this.mPtrIndicator.getHeaderHeight();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.b != null && (this.b instanceof ListView)) {
            this.k = new PtrFooter();
            this.k.onInit((ListView) this.b);
            this.k.getContainer().setOnClickListener(new dh(this));
            ((ListView) this.b).setOnScrollListener(this.o);
        }
        if (this.b != null && (this.b instanceof RecyclerView)) {
            ((RecyclerView) this.b).addOnScrollListener(this.c);
        }
    }

    public void refresh() {
        autoRefresh();
    }

    public boolean isLoadingMore() {
        return this.g;
    }

    public boolean isEnableLoadMore() {
        return this.f == 1;
    }

    public long getLoadingStartTime() {
        return this.h;
    }

    public long getLoadingMinTime() {
        return this.i;
    }

    public void setLoadingMinTime(long j) {
        this.i = j;
    }

    public void updateLoadingStartTime() {
        this.h = System.currentTimeMillis();
    }

    private boolean d() {
        return Math.abs(System.currentTimeMillis() - getLoadingStartTime()) - getLoadingMinTime() >= 0;
    }

    public void loadMore() {
        if (d()) {
            updateLoadingStartTime();
            this.g = true;
            setLoadMoreState(1, null);
            if (this.e != null) {
                this.e.onLoadMore();
            }
        }
    }

    public void setRefreshEnable(boolean z) {
        setEnabled(z);
    }

    public void setLoadMoreEnable(boolean z) {
        if (z) {
            setLoadMoreState(1, null);
        } else {
            setLoadMoreState(0, null);
        }
    }

    public void refreshDone() {
        refreshDone(true);
    }

    public void refreshDone(boolean z) {
        refreshComplete();
    }

    public void loadMoreDone(boolean z) {
        loadMoreDone(z, "上拉看更多");
    }

    public void loadMoreDone(boolean z, String str) {
        if (this.g) {
            this.g = false;
            tryResetScroll();
        }
        if (z) {
            setLoadMoreState(1, null);
        } else {
            setLoadMoreState(2, str);
        }
    }

    public void setLoadedAll() {
        setLoadMoreState(3, "全部加载完毕");
    }

    public void setLoadMoreState(int i, String str) {
        boolean z = true;
        if (i != 1) {
            this.g = false;
        }
        this.f = i;
        if (this.k != null) {
            View container = this.k.getContainer();
            if (i != 2) {
                z = false;
            }
            container.setEnabled(z);
            this.k.onStateChange(i, str);
        }
    }

    protected void a(boolean z, byte b, PtrIndicator ptrIndicator) {
        if (this.f == 2 && ptrIndicator.getCurrentPosY() < (-this.l) && !isRefreshing() && !isLoadingMore()) {
            loadMore();
        }
        if (ptrIndicator.getCurrentPosY() > 0 && this.m != null) {
            this.m.onScrollOffsetChange(((float) (-ptrIndicator.getCurrentPosY())) / ((float) ptrIndicator.getHeaderHeight()), -ptrIndicator.getCurrentPosY(), ptrIndicator.getHeaderHeight());
        }
    }

    public void setPtrListener(PtrListener ptrListener) {
        this.e = ptrListener;
        setPtrHandler(this.p);
    }

    public OnScrollListener getOnScrollListener() {
        return this.n;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.n = onScrollListener;
    }

    public void setOnScrollOffsetListener(OnScrollOffsetListener onScrollOffsetListener) {
        this.m = onScrollOffsetListener;
    }

    public OnScrollOffsetListener getScrollOffsetListener() {
        return this.m;
    }
}
