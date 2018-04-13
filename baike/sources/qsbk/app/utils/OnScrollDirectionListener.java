package qsbk.app.utils;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import qsbk.app.QsbkApp;
import qsbk.app.widget.PtrLayout.OnScrollOffsetListener;

public abstract class OnScrollDirectionListener implements OnScrollListener, OnScrollOffsetListener {
    protected final int e = ((int) (7.0f * QsbkApp.getInstance().getResources().getDisplayMetrics().density));
    protected float f = 0.0f;

    public abstract void onScrollDown();

    public abstract void onScrollUp();

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        float f = 1.0f;
        if (absListView.getChildCount() != 0) {
            View childAt = absListView.getChildAt(0);
            float height = (float) childAt.getHeight();
            if (height == 0.0f) {
                height = 1.0f;
            }
            float f2 = ((float) this.e) / height;
            if (f2 <= 1.0f) {
                f = f2;
            }
            height = ((float) i) - (((float) childAt.getTop()) / height);
            if (height > this.f + f) {
                this.f = height;
                onScrollUp();
            } else if (height < this.f - f) {
                this.f = height;
                onScrollDown();
            }
        }
    }

    public void onScrollOffsetChange(float f, int i, int i2) {
        float f2 = 1.0f;
        float f3 = ((float) this.e) / ((float) i2);
        if (f3 <= 1.0f) {
            f2 = f3;
        }
        if (f < 0.0f) {
            this.f = 0.0f;
            onScrollDown();
        } else if (f > this.f + f2) {
            this.f = f;
            onScrollUp();
        } else if (f < this.f - f2) {
            this.f = f;
            onScrollDown();
        }
    }
}
