package qsbk.app.activity.group;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;

class b implements OnClickListener {
    final /* synthetic */ SplashAdActivity a;

    b(SplashAdActivity splashAdActivity) {
        this.a = splashAdActivity;
    }

    public void onClick(View view) {
        this.a.a.removeCallbacks(this.a.e);
        this.a.a();
        this.a.finish();
        StatSDK.onEvent(view.getContext(), "splash_ad_skip", String.valueOf(this.a.c.id));
        StatService.onEvent(view.getContext(), "splash_ad_skip", String.valueOf(this.a.c.id));
    }
}
