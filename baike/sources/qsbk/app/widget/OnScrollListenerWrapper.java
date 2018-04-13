package qsbk.app.widget;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import java.util.ArrayList;
import java.util.List;

public class OnScrollListenerWrapper implements OnScrollListener {
    private List<OnScrollListener> a = new ArrayList();

    public OnScrollListenerWrapper(OnScrollListener onScrollListener) {
        if (onScrollListener != null) {
            this.a.add(onScrollListener);
        }
    }

    public void addOnScrollListener(OnScrollListener onScrollListener) {
        if (onScrollListener != null) {
            this.a.add(onScrollListener);
        }
    }

    public void removeOnScrollListener(OnScrollListener onScrollListener) {
        this.a.remove(onScrollListener);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            OnScrollListener onScrollListener = (OnScrollListener) this.a.get(i2);
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChanged(absListView, i);
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        int size = this.a.size();
        for (int i4 = 0; i4 < size; i4++) {
            OnScrollListener onScrollListener = (OnScrollListener) this.a.get(i4);
            if (onScrollListener != null) {
                onScrollListener.onScroll(absListView, i, i2, i3);
            }
        }
    }
}
