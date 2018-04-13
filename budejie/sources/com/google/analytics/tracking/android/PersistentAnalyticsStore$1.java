package com.google.analytics.tracking.android;

class PersistentAnalyticsStore$1 implements Clock {
    final /* synthetic */ PersistentAnalyticsStore this$0;

    PersistentAnalyticsStore$1(PersistentAnalyticsStore persistentAnalyticsStore) {
        this.this$0 = persistentAnalyticsStore;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
