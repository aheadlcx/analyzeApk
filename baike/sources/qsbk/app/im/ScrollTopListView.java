package qsbk.app.im;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.QListView;

public class ScrollTopListView extends QListView {
    int a = 0;
    boolean b = false;
    private OnScrollListener c;
    private OnScrollToTopListener d = null;
    private View e;
    public int headHeight = 0;

    public interface OnScrollToTopListener {
        void onPull();
    }

    private class a implements OnScrollListener {
        final /* synthetic */ ScrollTopListView a;
        private int b;

        private a(ScrollTopListView scrollTopListView) {
            this.a = scrollTopListView;
            this.b = -1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.a.c != null) {
                this.a.c.onScrollStateChanged(absListView, i);
            }
            LogUtil.d("int state:" + i);
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a.c != null) {
                this.a.c.onScroll(absListView, i, i2, i3);
            }
            if (this.a.b && this.b != i) {
                this.b = i;
                if (i == 0) {
                    LogUtil.e("list view scroll to top");
                    if (this.a.d != null) {
                        this.a.d.onPull();
                    }
                }
            }
        }
    }

    public ScrollTopListView(Context context) {
        super(context, null);
        a();
    }

    public ScrollTopListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ScrollTopListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        setTranscriptMode(1);
        super.setOnScrollListener(new a());
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.headHeight == 0 && this.e != null && this.e.getHeight() != 0) {
            this.headHeight = this.e.getHeight();
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.c = onScrollListener;
    }

    public void setOnPullListener(OnScrollToTopListener onScrollToTopListener) {
        this.d = onScrollToTopListener;
    }

    public void setTopLoadingView(View view, int i) {
        this.e = view;
        this.a = i;
        super.addHeaderView(view);
    }

    public void removeHeadView() {
        if (this.e != null) {
            super.removeHeaderView(this.e);
            this.e = null;
        }
    }

    public void setCanRefresh(boolean z) {
        this.b = z;
    }

    public void stopPullToRefresh() {
        removeHeadView();
        this.b = false;
    }

    public int getHeadHeight() {
        if (this.e != null) {
            return this.a;
        }
        return 0;
    }

    public void setListSelection(int i) {
        if (this.e != null) {
            LogUtil.d("headHeight:" + this.headHeight);
            setSelectionFromTop(i + 1, getHeadHeight());
            return;
        }
        setSelection(i);
    }
}
