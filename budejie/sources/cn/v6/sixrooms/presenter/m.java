package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.OtherPlaceLoginEngine.CallBack;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class m implements CallBack {
    final /* synthetic */ RemoteLoginPresenter a;

    m(RemoteLoginPresenter remoteLoginPresenter) {
        this.a = remoteLoginPresenter;
    }

    public final void loginsucceed(String str, String str2) {
        SaveUserInfoUtils.saveEncpass(V6Coop.getInstance().getContext(), str2);
        this.a.isRemoteLogin = true;
        this.a.msg = str;
        this.a.d.getUserInfo(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), "");
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        this.a.a.hideLoading();
        this.a.a.error(i);
    }
}
