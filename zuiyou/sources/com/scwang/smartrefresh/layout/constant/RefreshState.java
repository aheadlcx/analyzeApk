package com.scwang.smartrefresh.layout.constant;

public enum RefreshState {
    None(0, false),
    PullDownToRefresh(1, true),
    PullToUpLoad(2, true),
    PullDownCanceled(1, false),
    PullUpCanceled(2, false),
    ReleaseToRefresh(1, true),
    ReleaseToLoad(2, true),
    ReleaseToTwoLevel(1, true),
    TwoLevelReleased(1, false),
    RefreshReleased(1, false),
    LoadReleased(2, false),
    Refreshing(1, false, true),
    Loading(2, false, true),
    TwoLevel(1, false, true),
    RefreshFinish(1, false, false, true),
    LoadFinish(2, false, false, true),
    TwoLevelFinish(1, false, false, true);
    
    public final boolean draging;
    public final boolean finishing;
    public final boolean opening;
    private final int role;

    private RefreshState(int i, boolean z) {
        this.role = i;
        this.draging = z;
        this.opening = false;
        this.finishing = false;
    }

    private RefreshState(int i, boolean z, boolean z2) {
        this.role = i;
        this.draging = z;
        this.opening = z2;
        this.finishing = false;
    }

    private RefreshState(int i, boolean z, boolean z2, boolean z3) {
        this.role = i;
        this.draging = z;
        this.opening = z2;
        this.finishing = z3;
    }

    public boolean isHeader() {
        return this.role == 1;
    }

    public boolean isFooter() {
        return this.role == 2;
    }
}
