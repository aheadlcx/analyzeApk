package qsbk.app.fragments;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.im.GameWebViewActivity;
import qsbk.app.utils.SplashAdManager.SplashAdItem;

class gg implements OnClickListener {
    final /* synthetic */ MyProfileFragment a;

    gg(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onClick(View view) {
        if (this.a.q == 0) {
            SimpleWebActivity.launch(view.getContext(), this.a.o);
        } else if (this.a.q == 1) {
            if (!(QsbkApp.currentUser == null || TextUtils.isEmpty(this.a.o))) {
                if (this.a.o.contains("?")) {
                    this.a.o = this.a.o + "&token=" + QsbkApp.currentUser.token;
                } else {
                    this.a.o = this.a.o + "?token=" + QsbkApp.currentUser.token;
                }
            }
            GameWebViewActivity.launch(view.getContext(), this.a.o, "", SplashAdItem.AD_WEB_GAME);
        } else if (this.a.q == 2) {
            Intent intent = new Intent();
            intent.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
            intent.putExtra("link", this.a.o);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUtils.getInstance().getAppContext().startActivity(intent);
        }
    }
}
