package qsbk.app.model;

import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

public class PayOrder {
    public static final String STATUS_INIT = "INIT";
    public static final String STATUS_PAYING = "PAYING";
    public static final String STATUS_SUCCESS = "SUCESS";
    public double balance;
    public double money;
    public String orderId;
    public String payDesc;
    public String status;

    public static PayOrder parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        PayOrder payOrder = new PayOrder();
        payOrder.orderId = jSONObject.optString("order_id");
        payOrder.status = jSONObject.optString("status");
        payOrder.balance = jSONObject.optDouble("balance") / 100.0d;
        payOrder.payDesc = jSONObject.optString("pay_desc");
        payOrder.money = jSONObject.optDouble(PayPWDUniversalActivity.MONEY) / 100.0d;
        return payOrder;
    }
}
