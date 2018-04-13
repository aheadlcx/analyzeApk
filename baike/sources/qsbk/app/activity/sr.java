package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

class sr implements OnClickListener {
    final /* synthetic */ MainActivity a;

    sr(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        LogUtil.d("验证失败删除当前用户信息,用户名和密码并未删除");
        SharePreferenceUtils.remove("loginUser");
        QsbkApp.currentUser = null;
        Intent intent = new Intent(this.a, ActionBarLoginActivity.class);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.putExtra("wrongType", "VERIFY_FAILED");
        this.a.startActivity(intent);
    }
}
