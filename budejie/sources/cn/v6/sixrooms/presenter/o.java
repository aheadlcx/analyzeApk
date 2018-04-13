package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SendBroadcastUtils;

final class o implements CallBack {
    final /* synthetic */ RemoteLoginPresenter a;

    o(RemoteLoginPresenter remoteLoginPresenter) {
        this.a = remoteLoginPresenter;
    }

    public final void handleInfo(UserBean userBean) {
        this.a.a.hideLoading();
        GlobleValue.setUserBean(userBean);
        if (this.a.isRemoteLogin) {
            SendBroadcastUtils.sendUserInfoBroadcast(this.a.a.getActivity());
            this.a.qqRemoteLogin = true;
            this.a.a.remoteLoginSucceed(this.a.msg);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a.hideLoading();
        if (this.a.isRemoteLogin) {
            this.a.a.logOut();
            this.a.a.handleErrorInfo(str, str2);
        }
    }

    public final void error(int i) {
        this.a.a.hideLoading();
        if (this.a.isRemoteLogin) {
            this.a.a.logOut();
            this.a.a.error(i);
        }
    }
}
