package qsbk.app.pay.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.text.style.ClickableSpan;
import android.view.View;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PackageUtils;

class q extends ClickableSpan {
    final /* synthetic */ WithdrawActivity a;

    q(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onClick(View view) {
        if (PackageUtils.isPackageInstalled("com.tencent.mm")) {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            intent.setComponent(componentName);
            this.a.startActivity(intent);
            AppUtils.copyToClipboard(this.a, "热猫直播");
        }
    }
}
