package com.tencent.tauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.xiaochuankeji.aop.permission.c;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.g;
import com.tencent.open.utils.i;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class AuthActivity extends Activity {
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static int a = 0;
    private static final /* synthetic */ a ajc$tjp_0 = null;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            AuthActivity.onCreate_aroundBody0((AuthActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    private static /* synthetic */ void ajc$preClinit() {
        b bVar = new b("ProGuard", AuthActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.tencent.tauth.AuthActivity", "android.os.Bundle", "arg0", "", "void"), 55);
    }

    static {
        ajc$preClinit();
    }

    static final /* synthetic */ void onCreate_aroundBody0(AuthActivity authActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        if (authActivity.getIntent() == null) {
            f.d("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
            authActivity.finish();
            return;
        }
        Uri uri = null;
        try {
            uri = authActivity.getIntent().getData();
        } catch (Exception e) {
            f.e("openSDK_LOG.AuthActivity", "-->onCreate, getIntent().getData() has exception! " + e.getMessage());
        }
        f.a("openSDK_LOG.AuthActivity", "-->onCreate, uri: " + uri);
        authActivity.a(uri);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void a(Uri uri) {
        f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
        if (uri == null || uri.toString() == null || uri.toString().equals("")) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
            finish();
            return;
        }
        String uri2 = uri.toString();
        Bundle a = i.a(uri2.substring(uri2.indexOf("#") + 1));
        if (a == null) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
            finish();
            return;
        }
        String string = a.getString(ACTION_KEY);
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
            string = a.getString("response");
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
