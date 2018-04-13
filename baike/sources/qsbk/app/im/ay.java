package qsbk.app.im;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;

class ay implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ at b;

    ay(at atVar, String str) {
        this.b = atVar;
        this.a = str;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
        intent.putExtra("link", this.a);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        AppUtils.getInstance().getAppContext().startActivity(intent);
    }
}
