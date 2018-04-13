package qsbk.app.cafe.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.cafe.Jsnativeinteraction.ui.WebActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.ShareUtils;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.Base64;
import qsbk.app.utils.ToastAndDialog;

public class SharePlugin extends Plugin {
    public static final String ACTION_QQ = "share_qq";
    public static final String ACTION_QZONE = "share_qzone";
    public static final String ACTION_SHARE = "share";
    public static final String ACTION_SHARE_INTERIOR = "shareInterior";
    public static final String ACTION_WEXIN = "share_weixin";
    public static final String MODEL = "share";
    public static final int SHARE_REQ = 133;
    private IWXAPI a;
    private ShareMsgItem c;

    private IWXAPI a() {
        if (this.a == null) {
            this.a = WXAPIFactory.createWXAPI(this.b.getCurActivity(), Constants.APP_ID);
        }
        return this.a;
    }

    private void a(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", str2);
        bundle.putString("summary", str3);
        bundle.putString("targetUrl", str);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(str4);
        }
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        Context curActivity = getContext().getCurActivity();
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, curActivity).shareToQzone(curActivity, bundle, new c(this));
    }

    private void a(int i, String str, String str2, String str3, byte[] bArr) {
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        wXMediaMessage.title = str2;
        wXMediaMessage.description = str3;
        if (bArr != null) {
            wXMediaMessage.thumbData = bArr;
        }
        BaseReq req = new Req();
        req.transaction = System.currentTimeMillis() + "";
        req.message = wXMediaMessage;
        req.scene = i;
        a().sendReq(req);
    }

    private void b(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", str2);
        bundle.putString("summary", str3);
        bundle.putString("targetUrl", str);
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString("imageUrl", str4);
        }
        Context curActivity = getContext().getCurActivity();
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, curActivity).shareToQQ(curActivity, bundle, new d(this));
    }

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        byte[] bArr = null;
        if (TextUtils.isEmpty(str) || "share".equals(str)) {
            ShareMsgItem parseJson = ShareMsgItem.parseJson(jSONObject);
            Activity curActivity = getContext().getCurActivity();
            this.c = parseJson;
            ShareUtils.openShareDialog(null, curActivity, SHARE_REQ, parseJson);
            getContext().setFocusPlugin(this);
        } else if (ACTION_QZONE.equals(str) || ACTION_QQ.equals(str)) {
            String optString = jSONObject.optString("url");
            String optString2 = jSONObject.optString("title");
            String optString3 = jSONObject.optString("description");
            if (TextUtils.isEmpty(optString)) {
                callback.sendResult(1, "url参数为空", "");
            } else if (TextUtils.isEmpty(optString3)) {
                callback.sendResult(1, "description参数为空", "");
            } else if (TextUtils.isEmpty(optString2)) {
                callback.sendResult(1, "title参数为空", "");
            } else {
                String optString4 = jSONObject.optString("imgurl");
                if (str.equals(ACTION_QQ)) {
                    b(optString, optString2, optString3, optString4);
                    callback.sendResult(0, "", "");
                    return;
                }
                a(optString, optString2, optString3, optString4);
                callback.sendResult(0, "", "");
            }
        } else if (ACTION_WEXIN.equals(str)) {
            if (a().isWXAppInstalled()) {
                Object optString5 = jSONObject.optString("url");
                Object optString6 = jSONObject.optString("title");
                Object optString7 = jSONObject.optString("description");
                int optInt = jSONObject.optInt("scene", 1);
                if (TextUtils.isEmpty(optString5)) {
                    callback.sendResult(1, "url参数为空", "");
                    return;
                } else if (TextUtils.isEmpty(optString7)) {
                    callback.sendResult(1, "description参数为空", "");
                    return;
                } else if (TextUtils.isEmpty(optString6)) {
                    callback.sendResult(1, "title参数为空", "");
                    return;
                } else {
                    Object optString8 = jSONObject.optString("thumbdata");
                    if (!TextUtils.isEmpty(optString8)) {
                        bArr = Base64.decode(optString8);
                    }
                    a(optInt, optString5, optString6, optString7, bArr);
                    callback.sendResult(0, "", "");
                    return;
                }
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请先安装微信", Integer.valueOf(0)).show();
            callback.sendResult(1, "请先安装微信", "");
        } else if (TextUtils.equals(ACTION_SHARE_INTERIOR, str)) {
            ShareMsgItem parseJson2 = ShareMsgItem.parseJson(jSONObject);
            WebActivity webActivity = (WebActivity) getContext().getCurActivity();
            this.c = parseJson2;
            if (webActivity != null) {
                webActivity.startActivityForResult(this, ShareUtils.getIntent(webActivity, parseJson2), SHARE_REQ);
            }
        } else {
            callback.sendResult(1, "", "");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == SHARE_REQ) {
            ShareUtils.doWebShare(i2, getContext().getCurActivity(), null, this.c);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
    }
}
