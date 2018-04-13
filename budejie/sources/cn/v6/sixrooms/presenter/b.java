package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.GetAuthCodeForResetPwdEngine.CallBack;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.Map;

final class b implements CallBack {
    final /* synthetic */ FindUserOrPwdPresenter a;

    b(FindUserOrPwdPresenter findUserOrPwdPresenter) {
        this.a = findUserOrPwdPresenter;
    }

    public final void verifyCodeSucceed(Map<String, String> map) {
        FindUserOrPwdPresenter.a(this.a).hideLoading();
        this.a.uid = (String) map.get(HistoryOpenHelper.COLUMN_UID);
        ToastUtils.showToast((String) map.get("msg"));
    }

    public final void handleErrorInfo(String str, String str2) {
        FindUserOrPwdPresenter.a(this.a).hideLoading();
        FindUserOrPwdPresenter.a(this.a).handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        FindUserOrPwdPresenter.a(this.a).hideLoading();
        FindUserOrPwdPresenter.a(this.a).error(i);
    }
}
