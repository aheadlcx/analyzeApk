package qsbk.app.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;

class ade implements OnClickListener {
    final /* synthetic */ SpringFestivalDialogActivity a;

    ade(SpringFestivalDialogActivity springFestivalDialogActivity) {
        this.a = springFestivalDialogActivity;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.o)) {
            Intent intent = new Intent();
            intent.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
            intent.putExtra("link", this.a.o);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUtils.getInstance().getAppContext().startActivity(intent);
        }
        this.a.finish();
    }
}
