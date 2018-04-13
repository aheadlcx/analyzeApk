package qsbk.app.fragments;

import qsbk.app.utils.SharePreferenceUtils;

class ie implements Runnable {
    final /* synthetic */ QiushiListFragment a;

    ie(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void run() {
        SharePreferenceUtils.setSharePreferencesValue(QiushiListFragment.VIDEO_PLAY_MODE_TIP_SHOW, true);
    }
}
