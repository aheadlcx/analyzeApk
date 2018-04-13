package com.budejie.www.busevent;

public class LoadMoreEvent {
    LoadMoreAction a;
    String b;

    public enum LoadMoreAction {
        LOAD_START,
        LOAD_FINISH
    }

    public LoadMoreEvent(LoadMoreAction loadMoreAction) {
        this.a = loadMoreAction;
    }

    public LoadMoreEvent(LoadMoreAction loadMoreAction, String str) {
        this.a = loadMoreAction;
        this.b = str;
    }

    public LoadMoreAction a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
