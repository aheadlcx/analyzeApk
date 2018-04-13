package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.SplashAdManager.SplashAdItem;

class eo implements OnClickListener {
    final /* synthetic */ SplashAdItem a;
    final /* synthetic */ a b;

    eo(a aVar, SplashAdItem splashAdItem) {
        this.b = aVar;
        this.a = splashAdItem;
    }

    public void onClick(View view) {
        if ("web".equals(this.a.type)) {
            SimpleWebActivity.launch(this.b.b, this.a.webLink);
        } else if ("download".equals(this.a.type)) {
            SimpleWebActivity.launch(this.b.b, this.a.webLink);
        }
    }
}
