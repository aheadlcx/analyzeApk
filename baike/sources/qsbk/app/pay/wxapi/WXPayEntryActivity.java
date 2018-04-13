package qsbk.app.pay.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
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
        if (baseResp.getType() == 5) {
            Intent intent = new Intent(PayActivity.WX_STATE);
            Bundle bundle = new Bundle();
            bundle.putInt("errCode", baseResp.errCode);
            if (baseResp instanceof PayResp) {
                bundle.putString("extData", ((PayResp) baseResp).extData);
            }
            intent.putExtras(bundle);
            sendBroadcast(intent);
            finish();
        }
    }
}
