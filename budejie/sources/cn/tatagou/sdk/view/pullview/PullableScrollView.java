package cn.tatagou.sdk.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class PullableScrollView extends ScrollView implements d {
    private static boolean a = true;
    private static boolean b = true;

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullableScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static void setCanPullUp(boolean z) {
        a = z;
    }

    public static void setCanPullDown(boolean z) {
        b = z;
    }

    public boolean canPullDown() {
        if (getScrollY() == 0) {
            return b;
        }
        return false;
    }

    public boolean canPullUp() {
        if (getScrollY() >= getChildAt(0).getHeight() - getMeasuredHeight()) {
            return a;
        }
        return false;
    }
}
