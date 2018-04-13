package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserLogoutHelper;

public class ReAuthActivity extends FragmentActivity {
    public static Intent getIntent(Context context) {
        return getIntent(context, null);
    }

    public static Intent getIntent(Context context, String str) {
        Intent intent = new Intent(context, ReAuthActivity.class);
        intent.addFlags(65536);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("message", str);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        return intent;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        a();
        super.onCreate(bundle);
        CharSequence charSequence = null;
        if (getIntent() != null) {
            charSequence = getIntent().getStringExtra("message");
        }
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = "你的帐号在其他设备上登录, 请重新登录";
        }
        View frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        setContentView(frameLayout);
        new UserLogoutHelper(this).doLogout();
        AlertDialog create = new Builder(this, R.style.MyDialogStyleNormal).setTitle("提示").setMessage(charSequence).setPositiveButton(17039370, new aaq(this)).create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
    }

    protected void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night.Transparent);
        } else {
            setTheme(R.style.Day.Transparent);
        }
    }
}
