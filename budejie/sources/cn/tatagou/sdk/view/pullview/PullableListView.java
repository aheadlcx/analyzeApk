package cn.tatagou.sdk.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class PullableListView extends ListView implements d {
    private boolean a = true;
    private boolean b = true;

    public PullableListView(Context context) {
        super(context);
    }

    public PullableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setCanPullUp(boolean z) {
        this.a = z;
    }

    public void setCanPullDown(boolean z) {
        this.b = z;
    }

    public boolean canPullDown() {
        if (getCount() == 0) {
            return this.b;
        }
        if (!this.b || getFirstVisiblePosition() != 0 || getChildAt(0) == null || getChildAt(0).getTop() < 0) {
            return false;
        }
        return this.b;
    }

    public boolean canPullUp() {
        if (getCount() == 0) {
            return this.a;
        }
        if (getLastVisiblePosition() != getCount() - 1 || !this.a || getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) == null || getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()).getBottom() > getMeasuredHeight()) {
            return false;
        }
        return this.a;
    }
}
