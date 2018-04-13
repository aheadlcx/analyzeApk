package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebViewClient;
import com.alipay.sdk.util.j;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.cafe.Jsnativeinteraction.ui.WebActivity;
import qsbk.app.cafe.plugin.AccountPlugin;
import qsbk.app.cafe.plugin.AlertPlugin;
import qsbk.app.cafe.plugin.JumpPlugin;
import qsbk.app.cafe.plugin.LocationPlugin;
import qsbk.app.cafe.plugin.OthersPlugin;
import qsbk.app.cafe.plugin.PasteboardPlugin;
import qsbk.app.cafe.plugin.PayUniversalPlugin;
import qsbk.app.cafe.plugin.QbSignPlugin;
import qsbk.app.cafe.plugin.SharePlugin;
import qsbk.app.cafe.plugin.ShortcutPlugin;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.UIHelper;

public class SimpleWebActivity extends WebActivity {
    private String a;
    private boolean b;
    private ShareMsgItem c = null;

    public static void launch(Context context, String str) {
        launch(context, str, false);
    }

    public static void launch(Context context, String str, String str2) {
        Intent intent = new Intent(context, SimpleWebActivity.class);
        intent.putExtra("link", str);
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, boolean z) {
        Intent intent = new Intent(context, SimpleWebActivity.class);
        intent.putExtra("link", str);
        intent.putExtra("need_share", z);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, SimpleWebActivity.class);
        intent.putExtra("link", str);
        intent.putExtra("need_share", z);
        intent.putExtra("needShowActionbar", z2);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, ShareMsgItem shareMsgItem, boolean z) {
        Intent intent = new Intent(context, SimpleWebActivity.class);
        intent.putExtra("link", str);
        intent.putExtra("shareItem", shareMsgItem);
        intent.putExtra("need_share", z);
        context.startActivity(intent);
    }

    protected void f() {
        this.o.put("location", LocationPlugin.class);
        this.o.put(ShortcutPlugin.MODEL, ShortcutPlugin.class);
        this.o.put("account", AccountPlugin.class);
        this.o.put("share", SharePlugin.class);
        this.o.put("jump", JumpPlugin.class);
        this.o.put(QbSignPlugin.MODUL, QbSignPlugin.class);
        this.o.put(PasteboardPlugin.MODEL, PasteboardPlugin.class);
        this.o.put("alert", AlertPlugin.class);
        this.o.put("goPay", PayUniversalPlugin.class);
        this.o.put("others", OthersPlugin.class);
    }

    public boolean needShowActionBar() {
        return getIntent().getBooleanExtra("needShowActionbar", true);
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        setActionbarBackable();
        this.a = getIntent().getStringExtra("link");
        this.b = getIntent().getBooleanExtra("need_share", false);
        if (this.b) {
            this.c = (ShareMsgItem) getIntent().getSerializableExtra("shareItem");
        }
        this.k.addJavascriptInterface(new SimpleWebActivity$WebResult(this), "_temp_webresult");
        this.k.addJavascriptInterface(new SimpleWebActivity$setStatusBarTintColor(this), "_set_status_bar_color");
        CharSequence stringExtra = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(stringExtra)) {
            getSupportActionBar().setTitle(stringExtra);
        }
        reloadUrl();
    }

    private boolean i() {
        try {
            return new URL(this.a).getHost().endsWith(UrlConstants.QSBK_DOMAIN);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void reloadUrl() {
        if (!TextUtils.isEmpty(this.a)) {
            if (QsbkApp.currentUser == null || !i()) {
                a(this.a);
                return;
            }
            Map hashMap = new HashMap();
            hashMap.put("Qbtoken", QsbkApp.currentUser.token);
            hashMap.put("user_id", QsbkApp.currentUser.userId);
            a(this.a, hashMap);
        }
    }

    protected WebViewClient g() {
        return new acs(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_share);
        boolean z = this.b && this.c != null;
        findItem.setVisible(z);
        findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.icon_share_night : R.drawable.icon_share);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_share:
                if (QsbkApp.currentUser != null) {
                    if (this.c != null) {
                        ShareUtils.openShareDialog(null, this, 100, this.c);
                        break;
                    }
                }
                startActivityForResult(new Intent(this, ActionBarLoginActivity.class), 101);
                break;
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            ShareUtils.doWebShare(i2, this, null, this.c);
        } else if (i == 101 || i == 123 || i == 124) {
            reloadUrl();
        } else if (i == 10001 && i2 == -1) {
            finish();
        } else if (i == QbSignPlugin.REQUEST_BIND_PHONE && i2 == -1) {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(this);
        } else if (i != PayUniversalPlugin.REQUEST_PAY) {
        } else {
            if (i2 == -1) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(j.c, "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                reqCallback(PayUniversalPlugin.CALL_BACK_ID, jSONObject.toString());
                ToastAndDialog.makeText(this, "支付成功").show();
            } else if (i2 == 0) {
                ToastAndDialog.makeText(this, "支付取消").show();
            } else if (i2 == 1) {
                ToastAndDialog.makeText(this, "支付失败").show();
            }
        }
    }

    public void onBackPressed() {
        if (this.k == null || !this.k.canGoBack()) {
            super.onBackPressed();
        } else {
            this.k.goBack();
        }
    }
}
