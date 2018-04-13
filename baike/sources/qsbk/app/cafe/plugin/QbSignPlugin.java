package qsbk.app.cafe.plugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.PayPasswordSetActivity;
import qsbk.app.activity.pay.ItemSignCardBuyActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.utils.ToastUtil;

public class QbSignPlugin extends Plugin {
    public static final String ACTION_QB_SIGN_CARD_PAY = "qbfSignCardPay";
    public static final String MODUL = "qbfSign";
    public static final int REQUEST_BIND_PHONE = 10002;
    public static final int REQUEST_ITEM_BUY = 10001;

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (!ACTION_QB_SIGN_CARD_PAY.equals(str)) {
            return;
        }
        if (QsbkApp.currentUser == null) {
            ActionBarLoginActivity.launch(getContext().getCurActivity());
        } else if (!QsbkApp.currentUser.hasPhone()) {
            AlertDialog create = new Builder(getContext().getCurActivity()).setTitle("账号安全系数低，请先绑定手机，并设置支付密码").setPositiveButton("绑定手机", new b(this)).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else if (!QsbkApp.currentUser.hasPaypass()) {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(getContext().getCurActivity());
        } else if (jSONObject != null) {
            int optInt = jSONObject.optInt("count");
            String optString = jSONObject.optString("card");
            String optString2 = jSONObject.optString("name");
            if (optInt > 0) {
                ItemSignCardBuyActivity.launch(getContext().getCurActivity(), optString, optString2, optInt, 10001);
            }
        }
    }

    public void onDestroy() {
    }
}
