package cn.tatagou.sdk.view;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class a implements OnScrollListener {
    private int a;
    private int b;
    private boolean c;

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                onStopScroll(false, this.a, this.b);
                this.c = false;
                return;
            case 1:
                onScrollState(true);
                this.c = true;
                return;
            case 2:
                onScrollState(true);
                this.c = true;
                return;
            default:
                return;
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.a = (i + i2) - 1;
        this.b = i3 - 1;
        onScrollList(absListView, this.a, this.b, this.c);
    }

    public void onStopScroll(boolean z, int i, int i2) {
    }

    public void onScrollState(boolean z) {
    }

    public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
    }
}
