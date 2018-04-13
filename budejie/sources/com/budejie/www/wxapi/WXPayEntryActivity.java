package com.budejie.www.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.budejie.www.R;
import com.budejie.www.busevent.AliPayAction;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import de.greenrobot.event.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splashscreen);
        this.a = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290");
        this.a.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.a.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 5) {
            if (baseResp.errCode == 0) {
                EventBus.getDefault().post(AliPayAction.OK);
            } else {
                EventBus.getDefault().post(AliPayAction.CANCEL);
            }
        }
        finish();
    }
}
