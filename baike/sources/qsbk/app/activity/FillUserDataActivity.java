package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.alipay.sdk.sys.a;
import java.util.HashMap;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.NameLengthFilter;
import qsbk.app.utils.SharePreferenceUtils;

public class FillUserDataActivity extends BaseActionBarActivity {
    private static final String a = FillUserDataActivity.class.getSimpleName();
    private ImageView b;
    private EditText c;
    private Button d;
    private UserHeaderHelper e;
    private boolean f = false;
    private ScrollView g = null;
    private String h = "";
    private String i;
    private String j;
    private String k;
    private ProgressDialog l;
    private Handler m = new kv(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    private void f() {
        if (this.g != null) {
            this.g.postDelayed(new kw(this), 200);
        }
    }

    protected String e() {
        return "完善资料";
    }

    protected int a() {
        return R.layout.fill_userdata_layout;
    }

    protected void a(Bundle bundle) {
        b(bundle);
        i();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.e != null) {
            this.e.onSaveInstanceState(bundle);
        }
    }

    private void b(Bundle bundle) {
        setActionbarBackable();
        this.e = new UserHeaderHelper(this, bundle);
        this.b = (ImageView) findViewById(R.id.avatar);
        FrescoImageloader.displayAvatar(this.b, this.h);
        this.b.setOnClickListener(new kx(this));
        this.g = (ScrollView) findViewById(R.id.id_scroll_view);
        this.c = (EditText) findViewById(R.id.username);
        this.c.setFilters(new InputFilter[]{new NameLengthFilter(16, this, "昵称最多为8个汉字")});
        this.c.setCursorVisible(false);
        this.c.setOnClickListener(new la(this));
        this.c.setOnFocusChangeListener(new lb(this));
        this.d = (Button) findViewById(R.id.finish);
        this.d.setOnClickListener(new lc(this));
    }

    private void a(String str, String str2, String str3) {
        if (!this.j.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            StringBuffer stringBuffer = new StringBuffer("accessToken=");
            stringBuffer.append(str);
            stringBuffer.append(a.b);
            stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
            SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
        }
    }

    private void g() {
        IMNotifyManager.getSettingFromCloud();
        new le(this, "qbk-LoginAct-2").start();
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    private void i() {
        String str;
        Intent intent = getIntent();
        this.j = intent.getStringExtra("type");
        this.i = intent.getStringExtra("token");
        this.k = intent.getStringExtra("expires_in");
        if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(this.j)) {
            str = "https://graph.qq.com/user/get_user_info?access_token=" + this.i + "&oauth_consumer_key=" + ThirdPartyConstants.QQ_CONSUMER_KEY + "&openid=" + intent.getStringExtra("openid");
        } else if (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equalsIgnoreCase(this.j)) {
            str = "https://api.weibo.com/2/users/show.json?access_token=" + this.i + "&uid=" + intent.getLongExtra("uid", 0);
        } else {
            str = intent.getStringExtra("openid");
            str = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", new Object[]{this.i, str});
        }
        new lf(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 1:
                    if (!(intent == null || intent.getData() == null)) {
                        this.e.doCropPhoto(intent.getData());
                        break;
                    }
                case 2:
                    if (!TextUtils.isEmpty(this.e.savePickedBitmap(intent))) {
                        this.f = true;
                        this.b.setImageBitmap(this.e.getPickedBitmap());
                        break;
                    }
                    break;
                case 3:
                    this.e.doCropPhotoWithCaptured();
                    break;
            }
            super.onActivityResult(i, i2, intent);
        }
    }
}
