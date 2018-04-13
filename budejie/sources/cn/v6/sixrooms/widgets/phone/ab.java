package cn.v6.sixrooms.widgets.phone;

import android.content.Intent;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class ab implements DialogListener {
    final /* synthetic */ OpenGuardPage a;

    ab(OpenGuardPage openGuardPage) {
        this.a = openGuardPage;
    }

    public final void positive(int i) {
        String str;
        if (V6Coop.getUserCoopPaySDK()) {
            str = CommonStrs.COOPRECHARGEACTIVITY;
        } else {
            str = CommonStrs.V6RECHARGEACTIVITY;
        }
        try {
            this.a.a.startActivity(new Intent(this.a.a, Class.forName(str)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void negative(int i) {
    }
}
