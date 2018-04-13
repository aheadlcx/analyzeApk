package com.google.analytics.tracking.android;

class GAServiceManager$1 implements AnalyticsStoreStateListener {
    final /* synthetic */ GAServiceManager this$0;

    GAServiceManager$1(GAServiceManager gAServiceManager) {
        this.this$0 = gAServiceManager;
    }

    public void reportStoreIsEmpty(boolean z) {
        this.this$0.updatePowerSaveMode(z, GAServiceManager.access$000(this.this$0));
    }
}
