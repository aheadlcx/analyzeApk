package cn.v6.sixrooms.utils;

import android.app.Activity;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.BundleInfoEngine;
import cn.v6.sixrooms.event.BundleMobileEvent;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class m implements DialogListener {
    final /* synthetic */ Activity a;

    m(Activity activity) {
        this.a = activity;
    }

    public final void positive(int i) {
        if (i == 1000) {
            new BundleInfoEngine(new n(this)).getBundleInfo(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), LoginUtils.getLoginUID());
        }
    }

    public final void negative(int i) {
        EventManager.getDefault().nodifyObservers(new BundleMobileEvent(), BundleMobileEvent.BUNDLE_CANCEL);
    }
}
