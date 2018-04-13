package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {

    @TargetApi(9)
    final class a extends ScrollView {
        final /* synthetic */ PullToRefreshScrollView a;

        public a(PullToRefreshScrollView pullToRefreshScrollView, Context context, AttributeSet attributeSet) {
            this.a = pullToRefreshScrollView;
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            PullToRefreshBase pullToRefreshBase = this.a;
            int i9 = 0;
            if (getChildCount() > 0) {
                i9 = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            }
            OverscrollHelper.overScrollBy(pullToRefreshBase, i, i3, i2, i4, i9, z);
            return overScrollBy;
        }
    }

    public PullToRefreshScrollView(Context context) {
        super(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshScrollView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected ScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        ScrollView aVar;
        if (VERSION.SDK_INT >= 9) {
            aVar = new a(this, context, attributeSet);
        } else {
            aVar = new ScrollView(context, attributeSet);
        }
        aVar.setId(R.id.scrollview);
        return aVar;
    }

    protected boolean isReadyForPullStart() {
        return ((ScrollView) this.a).getScrollY() == 0;
    }

    protected boolean isReadyForPullEnd() {
        View childAt = ((ScrollView) this.a).getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (((ScrollView) this.a).getScrollY() >= childAt.getHeight() - getHeight()) {
            return true;
        }
        return false;
    }
}
