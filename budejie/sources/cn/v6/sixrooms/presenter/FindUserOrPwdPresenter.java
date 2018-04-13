package cn.v6.sixrooms.presenter;

import android.content.Intent;
import cn.v6.sixrooms.engine.GetAuthCodeEngine;
import cn.v6.sixrooms.engine.GetAuthCodeForResetPwdEngine;
import cn.v6.sixrooms.mvp.interfaces.IFindUserOrPwdRunnable;
import cn.v6.sixrooms.ui.phone.FindUsernameActivity;
import cn.v6.sixrooms.ui.phone.ResetPasswordActivity;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;

public class FindUserOrPwdPresenter {
    private IFindUserOrPwdRunnable a;
    private GetAuthCodeForResetPwdEngine b = new GetAuthCodeForResetPwdEngine(new b(this));
    private GetAuthCodeEngine c = new GetAuthCodeEngine(new a(this));
    public String uid;

    public FindUserOrPwdPresenter(IFindUserOrPwdRunnable iFindUserOrPwdRunnable) {
        this.a = iFindUserOrPwdRunnable;
    }

    public void findUsernameOrPwd() {
        this.a.hideSystemInputManager();
        if (this.a.isRetrieveName()) {
            Intent intent = new Intent(this.a.getActivity(), FindUsernameActivity.class);
            intent.putExtra("authCode", this.a.getAuthCode());
            intent.putExtra("mobileNumber", this.a.getPhoneNumber());
            this.a.getActivity().startActivityForResult(intent, 1);
            return;
        }
        intent = new Intent(this.a.getActivity(), ResetPasswordActivity.class);
        intent.putExtra("authCode", this.a.getAuthCode());
        intent.putExtra("mobileNumber", this.a.getPhoneNumber());
        intent.putExtra("userName", this.a.getUserName());
        intent.putExtra(HistoryOpenHelper.COLUMN_UID, this.uid);
        this.a.getActivity().startActivityForResult(intent, 0);
    }

    public boolean getVerificationCode() {
        if (this.a.isRetrieveName()) {
            this.a.showLoading();
            this.c.getFinduNameVerifyCode(this.a.getPhoneNumber(), "finduname");
        } else {
            this.a.showLoading();
            this.b.getFindPswVerifyCode(this.a.getPhoneNumber(), this.a.getUserName(), "rpw");
        }
        return true;
    }
}
