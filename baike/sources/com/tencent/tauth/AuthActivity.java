package com.tencent.tauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.g;
import com.tencent.open.utils.i;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.activity.security.ActionBarSecurityBindActivity;

public class AuthActivity extends Activity {
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static int a = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            f.d("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
            finish();
            return;
        }
        Uri uri = null;
        try {
            uri = getIntent().getData();
        } catch (Exception e) {
            f.e("openSDK_LOG.AuthActivity", "-->onCreate, getIntent().getData() has exception! " + e.getMessage());
        }
        f.a("openSDK_LOG.AuthActivity", "-->onCreate, uri: " + uri);
        a(uri);
    }

    private void a(Uri uri) {
        f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
        if (uri == null || uri.toString() == null || uri.toString().equals("")) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
            finish();
            return;
        }
        String uri2 = uri.toString();
        Bundle a = i.a(uri2.substring(uri2.indexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1));
        if (a == null) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
            finish();
            return;
        }
        String string = a.getString("action");
        f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, action: " + string);
        if (string == null) {
            finish();
        } else if (string.equals("shareToQQ") || string.equals("shareToQzone") || string.equals("sendToMyComputer") || string.equals("shareToTroopBar")) {
            if (string.equals("shareToQzone") && g.a((Context) this, "com.tencent.mobileqq") != null && g.c(this, "5.2.0") < 0) {
                a++;
                if (a == 2) {
                    a = 0;
                    finish();
                    return;
                }
            }
            f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, most share action, start assistactivity");
            Intent intent = new Intent(this, AssistActivity.class);
            intent.putExtras(a);
            intent.setFlags(603979776);
            startActivity(intent);
            finish();
        } else if (string.equals("addToQQFavorites")) {
            r2 = getIntent();
            r2.putExtras(a);
            r2.putExtra(Constants.KEY_ACTION, "action_share");
            IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string);
            if (listnerWithAction != null) {
                UIListenerManager.getInstance().handleDataToListener(r2, listnerWithAction);
            }
            finish();
        } else if (string.equals(ACTION_SHARE_PRIZE)) {
            r2 = getPackageManager().getLaunchIntentForPackage(getPackageName());
            string = a.getString(ActionBarSecurityBindActivity.KEY_RESPONSE);
            Object obj = "";
            try {
                obj = i.d(string).getString("activityid");
            } catch (Throwable e) {
                f.b("openSDK_LOG.AuthActivity", "sharePrize parseJson has exception.", e);
            }
            if (!TextUtils.isEmpty(obj)) {
                r2.putExtra(ACTION_SHARE_PRIZE, true);
                Bundle bundle = new Bundle();
                bundle.putString("activityid", obj);
                r2.putExtras(bundle);
            }
            startActivity(r2);
            finish();
        } else {
            finish();
        }
    }
}
