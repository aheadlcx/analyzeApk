package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.constant.WBConstants.Response;
import com.sina.weibo.sdk.constant.WBConstants.SDK;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;

public class WbShareTransActivity extends Activity {
    boolean a = false;
    private String b;
    private Handler c = new a(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (bundle != null) {
            this.b = bundle.getString(WBConstants.SHARE_START_ACTIVITY);
            this.a = bundle.getBoolean(WBConstants.SHARE_START_RESULT_FLAG, false);
            return;
        }
        this.a = true;
        this.b = intent.getStringExtra(WBConstants.SHARE_START_ACTIVITY);
        intent.putExtra(WBConstants.SHARE_START_FLAG, -1);
        Intent intent2 = new Intent(WBConstants.ACTIVITY_WEIBO);
        intent2.putExtras(intent.getExtras());
        intent2.setPackage(intent.getStringExtra(WBConstants.SHARE_START_PACKAGE));
        intent2.setAction(intent.getStringExtra(WBConstants.SHARE_START_ACTION));
        String packageName = getPackageName();
        intent2.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent2.putExtra(Base.APP_PKG, packageName);
        intent2.putExtra(Base.APP_KEY, WbSdk.getAuthInfo().getAppKey());
        intent2.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent2.putExtra(WBConstants.SIGN, MD5.hexdigest(Utility.getSign(this, packageName)));
        try {
            if (TextUtils.isEmpty(intent.getStringExtra(WBConstants.SHARE_START_GOTO_ACTIVITY))) {
                startActivityForResult(intent2, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
                return;
            }
            intent2.setClassName(this, intent.getStringExtra(WBConstants.SHARE_START_GOTO_ACTIVITY));
            startActivity(intent2);
        } catch (Exception e) {
            intent = new Intent();
            Bundle bundle2 = new Bundle();
            bundle2.putInt(Response.ERRCODE, 2);
            intent.putExtras(bundle2);
            intent.setFlags(131072);
            intent.setClassName(this, this.b);
            startActivity(intent);
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.c.sendEmptyMessageDelayed(0, 100);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra(WBConstants.SHARE_START_FLAG, -1) != 0) {
            this.c.removeMessages(0);
            Bundle extras = intent.getExtras();
            Intent intent2 = new Intent();
            intent2.putExtras(extras);
            intent2.setFlags(131072);
            intent2.setClassName(this, this.b);
            startActivity(intent2);
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove(WBConstants.SHARE_START_FLAG);
        bundle.putBoolean(WBConstants.SHARE_START_RESULT_FLAG, true);
        bundle.putString(WBConstants.SHARE_START_ACTIVITY, this.b);
    }
}
