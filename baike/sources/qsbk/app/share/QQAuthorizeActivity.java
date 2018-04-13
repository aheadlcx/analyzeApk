package qsbk.app.share;

import android.content.Intent;
import android.os.Bundle;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class QQAuthorizeActivity extends BaseActionBarActivity {
    private static ThirdOauth2AccessToken b;
    int a = 0;
    private String c;
    private String d;
    private Tencent e;
    private IUiListener f;
    public String mAppid = ThirdPartyConstants.QQ_CONSUMER_KEY;

    private class a implements IUiListener {
        final /* synthetic */ QQAuthorizeActivity a;

        private a(QQAuthorizeActivity qQAuthorizeActivity) {
            this.a = qQAuthorizeActivity;
        }

        public void doComplete(JSONObject jSONObject) {
            try {
                this.a.c = jSONObject.getString("access_token");
                this.a.d = jSONObject.getString("expires_in");
                QQAuthorizeActivity.b = new ThirdOauth2AccessToken(this.a.c, this.a.d);
                AccessTokenKeeper.keepAccessToken(this.a, QQAuthorizeActivity.b);
                this.a.a(this.a.c, this.a.d, QsbkApp.currentUser.userId + "_qq_access_token");
                this.a.setResult(this.a.a, new Intent());
                this.a.finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError uiError) {
        }

        public void onCancel() {
            ToastAndDialog.makeNegativeToast(this.a.getApplicationContext(), "取消认证", Integer.valueOf(1)).show();
            this.a.finish();
        }

        public void onComplete(Object obj) {
            doComplete((JSONObject) obj);
        }
    }

    public String getCustomTitle() {
        return "糗事百科-腾讯分享";
    }

    protected int a() {
        return R.layout.weibo_authorize;
    }

    protected void a(Bundle bundle) {
        LogUtil.d("in QQAuthroziActivity");
        this.a = getIntent().getIntExtra("resultCode", 0);
        this.f = new a();
        this.e = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, getApplicationContext());
        this.e.login(this, "get_user_info,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album,add_t,add_pic_t", this.f);
        setActionbarBackable();
    }

    private void a(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer("accessToken=");
        stringBuffer.append(str);
        stringBuffer.append(com.alipay.sdk.sys.a.b);
        stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.e != null) {
            Tencent tencent = this.e;
            Tencent.onActivityResultData(i, i2, intent, this.f);
        }
    }
}
