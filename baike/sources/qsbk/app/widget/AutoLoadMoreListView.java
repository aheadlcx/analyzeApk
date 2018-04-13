package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutoLoadMoreListView extends QListView implements OnScrollListener {
    private AtomicBoolean a = new AtomicBoolean(Boolean.FALSE.booleanValue());
    private OnScrollListener b = null;
    private boolean c = true;
    private int d = 3;
    private OnLoadMoreListener e = null;

    public interface OnLoadMoreListener {
        void onLoadMore(AbsListView absListView, int i, int i2, int i3);
    }

    public AutoLoadMoreListView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public AutoLoadMoreListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public AutoLoadMoreListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.b = onScrollListener;
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        super.setOnScrollListener(this);
    }

    public void setLoadMoreFinished() {
        this.a.set(Boolean.FALSE.booleanValue());
    }

    public void setLoadingMore(boolean z) {
        this.a.set(z);
    }

    public void setLoadMoreEnable(boolean z) {
        this.c = z;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.e = null;
        this.e = onLoadMoreListener;
    }

    public void setTheLastButXItem(int i) {
        if (i >= getCount()) {
            throw new IllegalArgumentException(String.format("x must be letter than getCount() %s. 2 is recomended if item's height is large and 5 is recomended if item's height is normalwhen item count >= 30", new Object[]{Integer.valueOf(getCount())}));
        } else {
            this.d = i;
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.b != null) {
            this.b.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.b != null) {
            this.b.onScroll(absListView, i, i2, i3);
        }
        if (this.c && !this.a.get() && i2 != i3 && i != 0 && getLastVisiblePosition() >= i3 - this.d && this.e != null) {
            setLoadingMore(true);
            this.e.onLoadMore(absListView, i, i2, i3);
        }
    }
}
