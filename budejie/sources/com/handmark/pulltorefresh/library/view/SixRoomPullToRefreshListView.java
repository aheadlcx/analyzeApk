package com.handmark.pulltorefresh.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.handmark.pulltorefresh.library.internal.SixroomLayout;

public class SixRoomPullToRefreshListView extends PullToRefreshListView {
    public SixRoomPullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SixRoomPullToRefreshListView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public SixRoomPullToRefreshListView(Context context, Mode mode) {
        super(context, mode);
    }

    public SixRoomPullToRefreshListView(Context context) {
        super(context);
    }

    protected LoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray typedArray) {
        LoadingLayout loadingLayout = null;
        switch (a.a[mode.ordinal()]) {
            case 1:
                loadingLayout = new SixroomLayout(context, mode, getPullToRefreshScrollDirection(), typedArray);
                break;
            case 2:
                loadingLayout = new SixroomLayout(context, mode, getPullToRefreshScrollDirection(), typedArray);
                break;
        }
        loadingLayout.setVisibility(4);
        return super.createLoadingLayout(context, mode, typedArray);
    }
}
