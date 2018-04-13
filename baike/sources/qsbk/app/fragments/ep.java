package qsbk.app.fragments;

import qsbk.app.core.utils.AppUtils;

class ep implements Runnable {
    final /* synthetic */ LiveTabsFragment a;

    ep(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void run() {
        AppUtils.getInstance().loadConfig();
    }
}
