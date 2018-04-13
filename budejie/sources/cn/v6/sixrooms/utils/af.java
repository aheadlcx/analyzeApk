package cn.v6.sixrooms.utils;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class af implements DialogListener {
    af() {
    }

    public final void positive(int i) {
        Utility.a.dismiss();
        V6Coop.closeAll();
    }

    public final void negative(int i) {
        Utility.a.dismiss();
        V6Coop.closeAll();
    }
}
