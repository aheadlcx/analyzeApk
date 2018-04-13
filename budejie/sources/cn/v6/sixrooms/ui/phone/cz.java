package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.presenter.PropListPresenter;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class cz implements CallBack {
    final /* synthetic */ ShopActivity a;

    cz(ShopActivity shopActivity) {
        this.a = shopActivity;
    }

    public final void handleInfo(UserBean userBean) {
        this.a.hideLoadingScreen();
        new DialogUtils(this.a).createDiaglog(this.a.getResources().getString(R.string.shop_buy_succeed_updateInfoSucceed)).show();
        GlobleValue.setUserBean(userBean);
        WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
        if (localRoomInfo != null) {
            PropListPresenter.getInstance().getNetData(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this.a), localRoomInfo.getRoominfoBean().getId());
        } else {
            PropListPresenter.getInstance().getNetData(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this.a), "");
        }
    }

    public final void error(int i) {
        switch (i) {
            case 1006:
                if (this.a.l < 3) {
                    this.a.k.getUserInfo(SaveUserInfoUtils.getEncpass(this.a), "");
                    this.a.l = this.a.l + 1;
                    return;
                }
                this.a.hideLoadingScreen();
                new DialogUtils(this.a).createDiaglog(this.a.getResources().getString(R.string.shop_buy_succeed_updateInfoFailed)).show();
                this.a.l = 0;
                return;
            default:
                this.a.hideLoadingScreen();
                new DialogUtils(this.a).createDiaglog(this.a.getResources().getString(R.string.shop_buy_succeed_updateInfoFailed)).show();
                return;
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.hideLoadingScreen();
        this.a.handleErrorResult(str, str2, this.a);
    }
}
