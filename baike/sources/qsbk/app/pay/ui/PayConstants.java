package qsbk.app.pay.ui;

import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;

public class PayConstants {
    public static String WECHAT_APP_ID = "";
    public static long remix_id;

    public interface CallBack {
        void call();
    }

    public static void resetWeChatAppId(String str) {
        WECHAT_APP_ID = str;
    }

    public static void convertUserIdIfQsbk(CallBack callBack) {
        if (Constants.SOURCE == 2) {
            User user = AppUtils.getInstance().getUserInfoProvider().getUser();
            if (user != null) {
                remix_id = user.id;
            }
            callBack.call();
        } else if (Constants.SOURCE == 1) {
            NetRequest.getInstance().get(UrlConstants.QSBK_CONVERT_UID, new p(callBack));
        }
    }
}
