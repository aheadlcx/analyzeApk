package qsbk.app.pay.ui;

import android.support.v4.app.NotificationCompat;
import com.facebook.common.util.UriUtil;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.Constants;

class h extends Callback {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ String c;
    final /* synthetic */ PayActivity d;

    h(PayActivity payActivity, float f, float f2, String str) {
        this.d = payActivity;
        this.a = f;
        this.b = f2;
        this.c = str;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("bycoin", "2");
        hashMap.put("reward", Float.toString(this.a));
        if (Constants.SOURCE == 1) {
            hashMap.put("appid", Integer.toString(this.d.i));
            hashMap.put(PayPWDUniversalActivity.MONEY, Float.toString(this.b));
        }
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        super.onSuccess(baseResponse);
        int simpleDataInt = baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR);
        if (simpleDataInt == 0) {
            this.d.f = baseResponse.getSimpleDataLong("record_id");
            this.d.a(simpleDataInt, baseResponse.getSimpleDataStr(UriUtil.LOCAL_RESOURCE_SCHEME), this.b, this.a, this.c);
            return;
        }
        String format = String.format("返回码不为0，而是%s", new Object[]{Integer.valueOf(simpleDataInt)});
        this.d.l.post(new i(this, format));
        this.d.a("alipay", this.c, simpleDataInt, format);
    }

    public void onFinished() {
        this.d.hideSavingDialog(this.d.m);
    }

    public void onFailed(int i, String str) {
        this.d.a("alipay", this.c, i, str);
    }
}
