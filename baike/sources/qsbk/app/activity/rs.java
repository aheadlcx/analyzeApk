package qsbk.app.activity;

import qsbk.app.utils.SharePreferenceUtils;

class rs implements Runnable {
    final /* synthetic */ MainActivity a;

    rs(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        if ((!SharePreferenceUtils.getSharePreferencesBoolValue("circle_first_in") ? 1 : null) != null && this.a.a != null) {
            this.a.setSmallTips(MainActivity.TAB_QIUYOUCIRCLE_ID);
        }
    }
}
