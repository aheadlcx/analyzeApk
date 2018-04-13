package qsbk.app.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import qsbk.app.Constants;
import qsbk.app.activity.pay.ItemUnivasualBuyActivity;
import qsbk.app.pay.ui.PayActivity;
import qsbk.app.pay.ui.PayConstants;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String a = WXPayEntryActivity.class.getSimpleName();
    private IWXAPI b;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = WXAPIFactory.createWXAPI(this, PayConstants.WECHAT_APP_ID);
        this.b.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.b.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 5 && (baseResp instanceof PayResp)) {
            Object a = a(((PayResp) baseResp).extData, "source");
            Intent intent;
            Bundle bundle;
            if (TextUtils.equals(a, "qiubai_wallet")) {
                intent = new Intent(Constants.ACTION_LAISEE_PAY_DONE);
                bundle = new Bundle();
                bundle.putInt("errCode", baseResp.errCode);
                if (baseResp instanceof PayResp) {
                    bundle.putString("extData", ((PayResp) baseResp).extData);
                }
                intent.putExtras(bundle);
                sendBroadcast(intent);
            } else if (TextUtils.isEmpty(a) || !a.startsWith(ItemUnivasualBuyActivity.PAY_SOURCE)) {
                intent = new Intent(PayActivity.WX_STATE);
                bundle = new Bundle();
                bundle.putInt("errCode", baseResp.errCode);
                if (baseResp instanceof PayResp) {
                    bundle.putString("extData", ((PayResp) baseResp).extData);
                }
                intent.putExtras(bundle);
                sendBroadcast(intent);
            } else {
                intent = new Intent(Constants.ACTION_UNIVERSAL_PAY_DONE);
                bundle = new Bundle();
                bundle.putInt("errCode", baseResp.errCode);
                if (baseResp instanceof PayResp) {
                    bundle.putString("extData", ((PayResp) baseResp).extData);
                }
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
            finish();
        }
    }

    private String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        String substring;
        String[] split = str.split(a.b);
        int i = 0;
        while (i < split.length) {
            if (split[i] != null && split[i].startsWith(str2)) {
                substring = split[i].substring(str2.length() + 2, split[i].length() - 1);
                break;
            }
            i++;
        }
        substring = null;
        return substring;
    }
}
