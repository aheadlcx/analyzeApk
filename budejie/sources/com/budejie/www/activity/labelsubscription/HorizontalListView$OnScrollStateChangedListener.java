package com.budejie.www.activity.labelsubscription;

public interface HorizontalListView$OnScrollStateChangedListener {

    public enum ScrollState {
        SCROLL_STATE_IDLE,
        SCROLL_STATE_TOUCH_SCROLL,
        SCROLL_STATE_FLING
    }

    void a(ScrollState scrollState);
}
