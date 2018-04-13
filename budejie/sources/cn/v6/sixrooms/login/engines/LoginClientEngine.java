package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.login.interfaces.LoginClientCallback;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.ShuMeiParameterUtils;
import java.net.URLEncoder;

public class LoginClientEngine {
    private LoginClientCallback a;

    public void setLoginClientCallback(LoginClientCallback loginClientCallback) {
        this.a = loginClientCallback;
    }

    public void loginClientOfRegister(String str, String str2) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new c(this, str), (UrlStrs.LOGIN_CLIENT + "?ticket=" + URLEncoder.encode(str) + "&av=1.5&p2=" + str2 + "&from_module=" + StatisticValue.getInstance().getFromRegisterPageModule() + "&page=" + StatisticValue.getInstance().getRegisterPage() + "&module=" + StatisticValue.getInstance().getRegisterModule() + "&pageid=&watchid=" + StatisticValue.getInstance().getWatchid() + "&reg=register" + ShuMeiParameterUtils.getParameterStr()).replace("|", "%7C"), "");
    }
}
