package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.BuyItemInShopEngine.CallBack;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class cx implements CallBack {
    final /* synthetic */ ShopActivity a;

    cx(ShopActivity shopActivity) {
        this.a = shopActivity;
    }

    public final void result(String str) {
        ShopActivity.b(this.a);
        this.a.k.getUserInfo(SaveUserInfoUtils.getEncpass(this.a), "");
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.hideLoadingScreen();
        if ("105".equals(str)) {
            String str3 = str2;
            new DialogUtils(this.a).createConfirmDialog(1, this.a.getResources().getString(R.string.tip_show_tip_title), str3, this.a.getResources().getString(R.string.shop_dialog_cancel), this.a.getResources().getString(R.string.guard_charge_now), new cy(this)).show();
            return;
        }
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.hideLoadingScreen();
        new DialogUtils(this.a).createDiaglog(this.a.getErrorCodeString(i)).show();
    }
}
