package qsbk.app.widget;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.model.Banner;
import qsbk.app.widget.LiveBannerCell.LiveBannerAdapter;

class cj implements OnClickListener {
    final /* synthetic */ Banner a;
    final /* synthetic */ Intent b;
    final /* synthetic */ int c;
    final /* synthetic */ LiveBannerAdapter d;

    cj(LiveBannerAdapter liveBannerAdapter, Banner banner, Intent intent, int i) {
        this.d = liveBannerAdapter;
        this.a = banner;
        this.b = intent;
        this.c = i;
    }

    public void onClick(View view) {
        if ("web".equalsIgnoreCase(this.a.redirect_type)) {
            LiveBannerCell.a(this.b, this.a, this.d.a.getContext());
        } else if ("live".equalsIgnoreCase(this.a.redirect_type)) {
            LiveBannerCell.a(this.b, this.a, this.d.a.getContext(), this.c);
        } else if ("user".equalsIgnoreCase(this.a.redirect_type)) {
            LiveBannerCell.b(this.b, this.a, this.d.a.getContext());
        }
        StatSDK.onEvent(AppUtils.getInstance().getAppContext(), "banner_event", this.a.redirect_type, LiveBannerCell.a(this.a), "click", "");
    }
}
