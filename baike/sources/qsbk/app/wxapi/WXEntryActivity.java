package qsbk.app.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import qsbk.app.Constants;
import qsbk.app.activity.RedirectActivity;
import qsbk.app.live.share.LiveShareActivity;
import qsbk.app.utils.image.issue.Logger;

public class WXEntryActivity extends FragmentActivity implements IWXAPIEventHandler {
    private static final String a = WXEntryActivity.class.getSimpleName();
    private IWXAPI b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        this.b.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (this.b == null) {
            this.b = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        }
        Logger.getInstance().debug(a, "onNewIntent", this.b.handleIntent(intent, this) + " : ");
    }

    public void onReq(BaseReq baseReq) {
        Logger.getInstance().debug(a, "onReq", "" + baseReq);
        switch (baseReq.getType()) {
            case 4:
                a((Req) baseReq);
                break;
        }
        finish();
    }

    public void onResp(BaseResp baseResp) {
        Intent intent;
        Logger.getInstance().debug(a, "onResp ", baseResp + " - " + baseResp.errCode + " - " + baseResp.errStr);
        switch (baseResp.getType()) {
            case 2:
                intent = new Intent(LiveShareActivity.WX_STATE);
                if (baseResp.errCode == 0) {
                    intent.putExtra("share_result", "success");
                } else if (baseResp.errCode == -2) {
                    intent.putExtra("share_result", "cancel");
                }
                sendBroadcast(intent);
                break;
        }
        if (baseResp.errCode == 0 && (baseResp instanceof Resp)) {
            intent = new Intent(WXAuthHelper.WX_STATE);
            Resp resp = (Resp) baseResp;
            intent.putExtra("code", resp.code);
            intent.putExtra("state", resp.state);
            intent.putExtra("url", resp.url);
            Logger.getInstance().debug(a, "onResp ", String.format("errCode:%s, errStr:/%s, openId:%s, transaction:%s, code:%s, state:%s, url:%s", new Object[]{Integer.valueOf(resp.errCode), resp.errStr, resp.openId, resp.transaction, resp.code, resp.state, resp.url}));
            sendBroadcast(intent);
        }
        finish();
    }

    private void a(Req req) {
        RedirectActivity.navigateToActivity(this, ((WXAppExtendObject) req.message.mediaObject).extInfo);
    }
}
