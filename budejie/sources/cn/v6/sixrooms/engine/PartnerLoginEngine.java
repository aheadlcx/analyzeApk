package cn.v6.sixrooms.engine;

import cn.v6.sdk.sixrooms.coop.CoopBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import java.net.URLEncoder;
import java.util.Random;

public class PartnerLoginEngine {
    protected static final String TAG = PartnerLoginEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void bundlePhone(String str, String str2);

        void error(int i);

        void handleErrorInfo(String str, String str2);

        void loginSuccess(String str, String str2);
    }

    public PartnerLoginEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void login3(String str, CoopBean coopBean) {
        String encode;
        int nextInt = new Random().nextInt(10);
        try {
            encode = URLEncoder.encode(coopBean.getCoopNick());
        } catch (Exception e) {
            e.printStackTrace();
            encode = "..";
        }
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ah(this), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-cooperateLogin.php&coop=" + str + "&uid=" + coopBean.getCoopUid() + "&time=" + coopBean.getTime() + "&flag=" + coopBean.getFlag() + "&nickname=" + encode + "&av=2.7&index=" + nextInt + "&token=" + coopBean.getToken() + "&from_module=" + StatisticValue.getInstance().getFromRegisterPageModule() + "&page=" + StatisticValue.getInstance().getRegisterPage() + "&module=" + StatisticValue.getInstance().getRegisterModule() + "&user_pic=" + coopBean.getUser_pic(), "");
    }
}
