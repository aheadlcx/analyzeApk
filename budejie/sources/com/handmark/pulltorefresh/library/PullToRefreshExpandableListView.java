package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

public class PullToRefreshExpandableListView extends PullToRefreshAdapterViewBase<ExpandableListView> {

    class a extends ExpandableListView implements EmptyViewMethodAccessor {
        final /* synthetic */ PullToRefreshExpandableListView a;

        public a(PullToRefreshExpandableListView pullToRefreshExpandableListView, Context context, AttributeSet attributeSet) {
            this.a = pullToRefreshExpandableListView;
            super(context, attributeSet);
        }

        public void setEmptyView(View view) {
            this.a.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    @TargetApi(9)
    final class b extends a {
        final /* synthetic */ PullToRefreshExpandableListView b;

        public b(PullToRefreshExpandableListView pullToRefreshExpandableListView, Context context, AttributeSet attributeSet) {
            this.b = pullToRefreshExpandableListView;
            super(pullToRefreshExpandableListView, context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(this.b, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshExpandableListView(Context context) {
        super(context);
    }

    public PullToRefreshExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshExpandableListView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshExpandableListView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected ExpandableListView createRefreshableView(Context context, AttributeSet attributeSet) {
        ExpandableListView bVar;
        if (VERSION.SDK_INT >= 9) {
            bVar = new b(this, context, attributeSet);
        } else {
            bVar = new a(this, context, attributeSet);
        }
        bVar.setId(16908298);
        return bVar;
    }
}
