package qsbk.app.cafe.plugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.PayPasswordSetActivity;
import qsbk.app.activity.pay.ItemUnivasualBuyActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;

public class PayUniversalPlugin extends Plugin {
    public static final String ACTION = "goPay";
    public static final String CALL_BACK_ID = "payNativeCallback";
    public static final String MODUL = "goPay";
    public static final int REQUEST_PAY = 1234;

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if ("goPay".equals(str)) {
            Object string = jSONObject.getString("order_id");
            if (TextUtils.isEmpty(string)) {
                ToastAndDialog.makeText(getContext().getCurActivity(), "订单信息错误，请稍后重试").show();
            } else if (QsbkApp.currentUser == null) {
                ToastUtil.Short("请先登录");
            } else if (!QsbkApp.currentUser.hasPhone()) {
                AlertDialog create = new Builder(getContext().getCurActivity()).setTitle("账号安全系数低，请先绑定手机，并设置支付密码").setPositiveButton("绑定手机", new a(this)).create();
                create.setCanceledOnTouchOutside(true);
                create.show();
            } else if (QsbkApp.currentUser.hasPaypass()) {
                ItemUnivasualBuyActivity.launch(getContext().getCurActivity(), string, REQUEST_PAY);
            } else {
                ToastUtil.Short("为了您的资金安全，请先设置支付密码");
                PayPasswordSetActivity.launch(getContext().getCurActivity());
            }
        }
    }

    public void onDestroy() {
    }
}
