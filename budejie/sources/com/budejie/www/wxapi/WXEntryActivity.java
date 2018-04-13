package com.budejie.www.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.HotCommentShareActivity;
import com.budejie.www.activity.MyAccountActivity;
import com.budejie.www.activity.OAuthWeiboActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.g.b;
import com.budejie.www.util.an;
import com.budejie.www.util.ay;
import com.budejie.www.util.m;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static OAuthWeiboActivity c;
    private static MyAccountActivity d;
    private final String a = "WXEntryActivity";
    private IWXAPI b;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", false);
        this.b.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.b.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
        }
    }

    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case 0:
                String str = ay.c;
                String str2;
                if (ay.a == null) {
                    if (ay.b == null) {
                        str2 = "";
                        try {
                            str2 = ((Resp) baseResp).code;
                        } catch (Exception e) {
                        }
                        if (str2.length() > 0) {
                            if (c != null) {
                                c.a(str2);
                            }
                            if (d != null) {
                                d.c(str2);
                                break;
                            }
                        }
                    }
                    b.a(getApplicationContext(), ay.b.getTheme_id() + "", str, "theme");
                    Intent intent = new Intent("action.share.huodong.success");
                    intent.putExtra("shareType", ay.b.getType());
                    sendBroadcast(intent);
                    break;
                } else if (!ay.a.getIfPP()) {
                    String wid = ay.a.getWid();
                    Boolean a = ((BudejieApplication) getApplication()).g().ae.a();
                    boolean isForwardNoCollect = ay.a.isForwardNoCollect();
                    if (a.booleanValue() && !isForwardNoCollect) {
                        str2 = "分享并收藏成功";
                        if (ay.a.isCollect()) {
                            str2 = "分享成功";
                        } else {
                            ay.a.setForwardAndCollect_isweixin(true);
                            m.a((Activity) this, new Handler(), ay.a);
                        }
                    }
                    if (!TextUtils.isEmpty(wid)) {
                        Log.d("WXEntryActivity", "onResp: ");
                        b.a((Context) this, wid, str, "topic");
                        if (!(b.a == null || b.a.isFinishing())) {
                            Log.d("WXEntryActivity", "onResp: ListenerEx.mTopActivity != null && !ListenerEx.mTopActivity.isFinishing()");
                            TipPopUp.a(b.a, TypeControl.share, TypeControl.tiezi);
                        }
                        if (b.a instanceof HotCommentShareActivity) {
                            b.a.finish();
                            break;
                        }
                    }
                    b.a((Context) this, wid, str, "app");
                    break;
                } else {
                    an.a((Activity) this, "分享成功", -1).show();
                    b.a((Context) this, ay.a.getWid(), str, StatisticCodeTable.PROFILE);
                    break;
                }
                break;
        }
        ay.a = null;
        ay.b = null;
        finish();
    }

    public static void a(Context context, IWXAPI iwxapi) {
        if (iwxapi.isWXAppInstalled()) {
            BaseReq req = new Req();
            req.scope = "snsapi_userinfo";
            req.state = "app_wechat";
            iwxapi.sendReq(req);
            if (context instanceof OAuthWeiboActivity) {
                c = (OAuthWeiboActivity) context;
                d = null;
            }
            if (context instanceof MyAccountActivity) {
                d = (MyAccountActivity) context;
                c = null;
                return;
            }
            return;
        }
        Toast.makeText(context.getApplicationContext(), "您还未安装微信客户端！", 0).show();
    }
}
